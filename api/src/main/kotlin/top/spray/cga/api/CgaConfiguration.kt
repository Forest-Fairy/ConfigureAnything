package top.spray.cga.api

import top.spray.cga.api.result.CgaCheckResult
import top.spray.cga.api.model.CgaVersionedModel
import top.spray.cga.api.service.CgaWriterAdapter
import top.spray.cga.api.value.CgaValueConfig
import top.spray.cga.api.value.CgaValueList
import java.io.InputStream
import java.util.*
import kotlin.Comparator
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


interface CgaConfiguration<Value: CgaValue<*>, Type: CgaType, Result : CgaCheckResult<Result>>
    : Comparable<CgaConfiguration<*, *, *>>,
    CgaVersionedModel {
    override fun ID(): CgaID
    fun ParentId(): CgaID
    fun SortNum(): Int
    override fun Name(): String
    override fun Description(): String
    fun Type(): Type
    fun Value(): Value
    override fun getLastVersionId(): CgaID
    override fun status(): Int
    override fun compareTo(other: CgaConfiguration<*, *, *>): Int {
        return Comparator.comparingInt<CgaConfiguration<*, *, *>> { it.SortNum() }.compare(this, other)
    }

    @Suppress("UNCHECKED_CAST")
    @OptIn(ExperimentalUuidApi::class)
    fun check(valueCheck: Boolean): Result {
        // 判断 this.Value()  不是 CgaValueList<*>
        if (! valueCheck || this.Value() !is CgaValueList<*>) {
            return checkResultProvide(Uuid.random().toString(),
                CgaUtils.Const.CheckResult.SUCCESS_STATUS,
                CgaUtils.Const.CheckResult.OK,
                null)
        }
        var status = CgaUtils.Const.CheckResult.SUCCESS_STATUS
        val message = StringBuilder()
        val sonCheckResult = LinkedList<Result>()
        for (son in this.Value() as CgaValueList<*>) {
            if (son is CgaValueConfig<*>) {
                val result = son.get().check(true)
                sonCheckResult.add(result as Result)
                if (!CgaUtils.CheckResult.isSuccess(result)) {
                    status = -1
                    message.append(",").append(result.getMessage())
                }
            }
        }
        return checkResultProvide(UUID.randomUUID().toString(), status,
            if (message.isEmpty()) CgaUtils.Const.CheckResult.OK else message.substring(1),
            sonCheckResult)
    }

    fun checkResultProvide(id: String, status: Int, message: String, children: Collection<Result>?): Result;

    fun export(writer: CgaWriterAdapter)

    // 新增方法：从输入流导入配置对象
    fun importFromStream(inputStream: InputStream)
}
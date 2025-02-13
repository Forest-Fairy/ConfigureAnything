package top.spray.cga.api

import top.spray.cga.api.result.CgaCheckResult
import top.spray.cga.api.model.CgaVersionedModel
import top.spray.cga.api.service.CgaContextWriter
import java.io.InputStream


interface CgaConfiguration<
        Config: CgaConfiguration<Config, Value, Type, Result>,
        Value: CgaValue<*>, Type: CgaType, Result : CgaCheckResult<Result>>
    : Comparable<CgaConfiguration<*, *, *, *>>,
    CgaVersionedModel {
    override fun ID(): CgaID;
    fun ParentId(): CgaID;
    fun SortNum(): Int;
    override fun Name(): String;
    override fun Description(): String;
    fun Type(): Type;
    fun Value(): Value;
    override fun getLastVersionId(): CgaID;
    override fun compareTo(other: CgaConfiguration<*, *, *, *>): Int {
        return Comparator.comparingInt<CgaConfiguration<*, *, *, *>> { it.SortNum() }.compare(this, other)
    }

    fun check(valueCheck: Boolean): Result

    fun export(writer: CgaContextWriter)

    // 新增方法：从输入流导入配置对象
    fun importFromStream(inputStream: InputStream)
}
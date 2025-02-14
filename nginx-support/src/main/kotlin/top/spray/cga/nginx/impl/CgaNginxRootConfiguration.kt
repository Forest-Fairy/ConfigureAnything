package top.forestfairy.top.spray.cga.nginx.impl

import top.spray.cga.api.CgaID
import top.spray.cga.api.CgaUtils
import top.spray.cga.api.service.CgaWriterAdapter
import top.spray.cga.api.value.CgaValueList
import top.forestfairy.cga.nginx.api.CgaNginxConfiguration
import top.forestfairy.cga.nginx.api.CgaNginxType
import top.spray.cga.api.value.CgaValueConfig
import java.io.InputStream
import java.util.LinkedList

open class CgaNginxRootConfiguration : CgaNginxConfiguration<CgaValueList<*>> {
    lateinit var lastVersionID: CgaID
    lateinit var id: CgaID
    var status: Int = CgaUtils.Const.Configuration.Status.Normal
    var sortNum: Int = 0
    lateinit var name: String
    lateinit var desc: String
    lateinit var values: CgaValueList<CgaValueConfig<CgaNginxConfiguration<*>>>

    override fun export(writer: CgaWriterAdapter) {
        try {
            for (value in this.values) {
                value.get().export(writer)
            }
            writer.close()
        } catch (e: Throwable) {
            writer.closeWithException(e)
        }
    }

    override fun importFromStream(inputStream: InputStream) {
        // 通过joni开源的正则匹配库 实现类型和值的匹配获取
        val list = LinkedList<String>()

        TODO("Not yet implemented")
    }

    override fun getLastVersionId(): CgaID = lastVersionID
    override fun status(): Int = status

    override fun checkResultProvide(
        id: String,
        status: Int,
        message: String,
        children: Collection<CgaNginxCheckResult>?
    ): CgaNginxCheckResult {
        return CgaNginxCheckResult(id, status, message, children)
    }

    override fun ParentId(): CgaID = CgaUtils.Const.ID.NULL
    override fun ID(): CgaID = id
    override fun SortNum(): Int = sortNum
    override fun Name(): String = name
    override fun Description(): String = desc
    override fun Value(): CgaValueList<*> = values

    override fun Type(): CgaNginxType = CgaNginxType.ROOT
}
package top.forestfairy.top.spray.cga.nginx.impl.configuration

import top.forestfairy.cga.nginx.api.CgaNginxConfiguration
import top.forestfairy.cga.nginx.api.CgaNginxType
import top.forestfairy.top.spray.cga.nginx.impl.CgaNginxCheckResult
import top.spray.cga.api.CgaID
import top.spray.cga.api.CgaUtils
import top.spray.cga.api.service.CgaWriterAdapter
import top.spray.cga.api.value.CgaValueConfig
import top.spray.cga.api.value.CgaValueList
import java.io.InputStream

open class CgaNginxHttpConfiguration : CgaNginxConfiguration<CgaValueList<CgaValueConfig<*>>> {
    lateinit var lastVersionID: CgaID
    lateinit var parentId: CgaID
    lateinit var id: CgaID
    var status: Int = CgaUtils.Const.Configuration.Status.Normal
    var sortNum: Int = 0
    lateinit var name: String
    lateinit var desc: String
    lateinit var value: CgaValueList<CgaValueConfig<*>>
    override fun checkResultProvide(
        id: String,
        status: Int,
        message: String,
        children: Collection<CgaNginxCheckResult>?
    ): CgaNginxCheckResult {
        TODO("Not yet implemented")
    }

    override fun export(writer: CgaWriterAdapter): InputStream? {
        TODO("Not yet implemented")
    }

    override fun importFromStream(inputStream: InputStream) {
        TODO("Not yet implemented")
    }

    override fun getLastVersionId(): CgaID = lastVersionID
    override fun status(): Int = status

    override fun ParentId(): CgaID = parentId
    override fun ID(): CgaID = id
    override fun SortNum(): Int = sortNum
    override fun Name(): String = name
    override fun Description(): String = desc
    override fun Type() = CgaNginxType.Http
    override fun Value(): CgaValueList<CgaValueConfig<*>> = value
}
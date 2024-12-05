package top.forestfairy.cga.nginx

import top.forestfairy.cga.CgaID
import top.forestfairy.cga.CgaValueList


open class CgaNginxHttpServerConfiguration : CgaNginxConfiguration<CgaValueList<CgaNginxConfiguration<*>>> {
    lateinit var lastVersionID: CgaID
    lateinit var parentId: CgaID
    lateinit var id: CgaID
    var sortNum: Int = 0
    lateinit var name: String
    lateinit var desc: String
    lateinit var value: CgaValueList<CgaNginxConfiguration<*>>
    override fun getLastVersionId(): CgaID = lastVersionID
    override fun ParentId(): CgaID = parentId
    override fun ID(): CgaID = id
    override fun SortNum(): Int = sortNum
    override fun Name(): String = name
    override fun Description(): String = desc
    override fun Type() = CgaNginxStreamType
    override fun Value(): CgaValueList<CgaNginxConfiguration<*>> = value
}
object CgaNginxHttpServerType : CgaNginxType {
    private const val TYPE_NAME = "http_server"
    private const val TYPE_DESC = "nginx server"
    override fun get(): String = TYPE_NAME
    override fun desc(): String = TYPE_DESC
}
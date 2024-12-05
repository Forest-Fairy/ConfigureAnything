package top.forestfairy.cga.nginx

import top.forestfairy.cga.*

interface CgaNginxConfiguration<Value: CgaValue<*>>: CgaConfiguration<Value, CgaNginxType> {
    override fun Type(): CgaNginxType
}
interface CgaNginxType: CgaType<CgaNginxType, CgaNginxConfiguration<*>> {
    override fun checkAvailableCount(config: CgaNginxConfiguration<*>): Int = -1
}
open class CgaNginxRootConfiguration: CgaNginxConfiguration<CgaValueList<CgaNginxConfiguration<*>>> {
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
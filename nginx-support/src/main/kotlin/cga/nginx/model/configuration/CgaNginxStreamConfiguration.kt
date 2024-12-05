package top.forestfairy.cga.nginx.model.configuration

import top.forestfairy.cga.model.*
import top.forestfairy.cga.nginx.model.type.CgaNginxStreamType
import top.forestfairy.cga.nginx.model.type.CgaNginxType

class CgaNginxStreamConfiguration : CgaNginxConfiguration<CgaValueList<CgaNginxConfiguration<*>>> {
    private var type = CgaNginxStreamType
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
    override fun Type(): CgaNginxType = type
    override fun Value(): CgaValueList<CgaNginxConfiguration<*>> = value
}
package top.forestfairy.cga.nginx.model.configuration

import top.forestfairy.cga.model.*
import top.forestfairy.cga.nginx.model.type.CgaNginxType

abstract class CgaNginxTextConfiguration : CgaNginxConfiguration<CgaValueText> {
    lateinit var lastVersionID: CgaID
    lateinit var parentId: CgaID
    lateinit var id: CgaID
    lateinit var type: CgaNginxType
    var sortNum: Int = 0
    lateinit var name: String
    lateinit var desc: String
    lateinit var value: CgaValueText
    override fun getLastVersionId(): CgaID = lastVersionID
    override fun ParentId(): CgaID = parentId
    override fun ID(): CgaID = id
    override fun SortNum(): Int = sortNum
    override fun Name(): String = name
    override fun Description(): String = desc
    override fun Type(): CgaNginxType = type
    override fun Value(): CgaValueText = value
}
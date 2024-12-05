package top.forestfairy.cga.nginx.model.configuration

import top.forestfairy.cga.model.*
import top.forestfairy.cga.nginx.model.type.CgaNginxType

abstract class CgaNginxTextConfiguration : CgaNginxConfiguration<CgaValueText> {
    lateinit var lastVersionID: CgaID
    lateinit var parentId: CgaID
    lateinit var id: CgaID
    lateinit var type: CgaNginxType
    var sort: Long = 0L
    lateinit var name: String
    lateinit var desc: String
    lateinit var value: CgaValueText
    override fun getLastVersionId(): CgaID = lastVersionID
    override fun getParentId(): CgaID = parentId
    override fun getId(): CgaID = id
    override fun getSort(): Long = sort
    override fun getName(): String = name
    override fun getDesc(): String = desc
    override fun getType(): CgaNginxType = type
    override fun getValue(): CgaValueText = value
}
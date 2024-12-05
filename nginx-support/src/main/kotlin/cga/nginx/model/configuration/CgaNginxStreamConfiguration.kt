package top.forestfairy.cga.nginx.model.configuration

import top.forestfairy.cga.model.*
import top.forestfairy.cga.nginx.model.type.CgaNginxStreamType
import top.forestfairy.cga.nginx.model.type.CgaNginxType

class CgaNginxStreamConfiguration : CgaNginxConfiguration<CgaValueList<CgaNginxConfiguration<*>>> {
    private var type = CgaNginxStreamType
    lateinit var lastVersionID: CgaID
    lateinit var parentId: CgaID
    lateinit var id: CgaID
    var sort: Long = 0L
    lateinit var name: String
    lateinit var desc: String
    lateinit var value: CgaValueList<CgaNginxConfiguration<*>>
    override fun getLastVersionId(): CgaID = lastVersionID
    override fun getParentId(): CgaID = parentId
    override fun getId(): CgaID = id
    override fun getSort(): Long = sort
    override fun getName(): String = name
    override fun getDesc(): String = desc
    override fun getType(): CgaNginxType = type
    override fun getValue(): CgaValueList<CgaNginxConfiguration<*>> = value
}
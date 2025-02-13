package cga.api

import cga.api.model.CgaVersionedModel
import cga.api.service.CgaContextWriter


interface CgaConfiguration<Value: CgaValue<*>, Type: CgaType<Type, *>>: Comparable<CgaConfiguration<*, *>>,
    CgaVersionedModel {
    override fun ID(): CgaID;
    fun ParentId(): CgaID;
    fun SortNum(): Int;
    fun Name(): String;
    fun Description(): String;
    fun Type(): Type;
    fun Value(): Value;
    override fun getLastVersionId(): CgaID;
    override fun compareTo(other: CgaConfiguration<*, *>): Int {
        return Comparator.comparingInt<CgaConfiguration<*, *>> { it.SortNum() }.compare(this, other)
    }

    fun check(valueCheck: Boolean): CgaCheckResult

    fun export(writer: CgaContextWriter)

}
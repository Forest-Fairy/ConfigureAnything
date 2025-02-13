package top.spray.cga.api

interface CgaEntity {
    fun ID(): CgaID
    fun Name(): String;
    fun Description(): String;
}
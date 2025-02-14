package top.spray.cga.api.model;

import top.spray.cga.api.CgaEntity
import top.spray.cga.api.CgaID

interface CgaVersionedModel: CgaEntity {
    fun getLastVersionId(): CgaID
    fun status(): Int
}
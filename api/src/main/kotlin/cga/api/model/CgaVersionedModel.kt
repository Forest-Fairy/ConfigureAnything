package cga.api.model;

import cga.api.CgaEntity
import cga.api.CgaID

interface CgaVersionedModel: CgaEntity {
    fun getLastVersionId(): CgaID;
}
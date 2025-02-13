package top.spray.cga.api.model;

import top.spray.cga.api.CgaEntity

interface CgaRichModel<Entity: CgaRichModel<Entity>>: CgaEntity {
    /**
     * it means upsert with self when the other is null
     */
    fun upsert(other: Entity?): Entity;
    fun delete(): Long
}
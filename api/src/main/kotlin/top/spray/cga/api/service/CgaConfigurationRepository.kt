package top.spray.cga.api.service

import top.spray.cga.api.CgaConfiguration
import top.spray.cga.api.CgaID
import top.spray.cga.api.CgaType
import top.spray.cga.api.CgaUtils
import top.spray.cga.api.result.CgaCheckResult

interface CgaConfigurationRepository<Config: CgaConfiguration<*, *, *>, Type: CgaType> {
    fun getConfiguration(id: CgaID, containChildren: Boolean, status: Int): Config
    fun listConfigurationByParentId(parentId: CgaID, containChildren: Boolean?, status: Int?, type: Type?) : List<Config>
}
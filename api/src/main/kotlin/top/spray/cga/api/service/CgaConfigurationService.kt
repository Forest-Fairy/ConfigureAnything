package top.spray.cga.api.service

import top.spray.cga.api.CgaConfiguration
import top.spray.cga.api.CgaID
import top.spray.cga.api.CgaType
import top.spray.cga.api.CgaUtils
import top.spray.cga.api.result.CgaCheckResult

interface CgaConfigurationService<Config: CgaConfiguration<*, *, *>, Type: CgaType, Result: CgaCheckResult<*>> {
    fun validate(configuration: Config): Result
    fun resolve(configuration: Config): String
    fun listAvailableConfigurationTypes(curConfiguration: Config) : Map<Type, Int>
}
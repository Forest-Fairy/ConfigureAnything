package top.spray.cga.api.service

import top.spray.cga.api.CgaConfiguration
import top.spray.cga.api.CgaID
import top.spray.cga.api.CgaType
import top.spray.cga.api.CgaUtils
import top.spray.cga.api.result.CgaCheckResult

interface CgaConfigurationService<Config: CgaConfiguration<*, *, *>, Type: CgaType, Result: CgaCheckResult<*>> {
    fun validate(configuration: Config): Result
    fun resolve(configuration: Config): String
    fun listTypes() : Array<Type>
    fun listAvailableConfigurationTypes(curConfiguration: Config) : Map<Type, Int> {
        val types = listTypes()
        val result = HashMap<Type, Int>(types.size)
        for (type in types) {
            result[type] = type.checkAvailableCount(curConfiguration)
        }
        return result
    }
}
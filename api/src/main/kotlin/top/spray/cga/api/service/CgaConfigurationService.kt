package top.spray.cga.api.service

import top.spray.cga.api.CgaConfiguration
import top.spray.cga.api.CgaID
import top.spray.cga.api.CgaType

interface CgaConfigurationService<Config: CgaConfiguration<*, *, *, *>, Type: CgaType> {
    fun getConfiguration(id: CgaID, containChildren: Boolean): Config
    fun validate(configuration: Config): Boolean
    fun resolve(configuration: Config): String

    /**
     * Such as {'set': -1, 'server': 1, 'resolver': 0}
     * It means limitless set and 1 server only and none resolver
     * can be added inside of current configuration
     */
    fun listAvailableConfigurationTypes(curConfiguration: Config) : Map<Type, Int>
}
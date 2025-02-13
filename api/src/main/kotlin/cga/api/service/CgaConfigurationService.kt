package cga.api.service

import cga.api.CgaConfiguration
import cga.api.CgaID
import cga.api.CgaType

interface CgaConfigurationService<Config: CgaConfiguration<*, *>, Type: CgaType<Type, *>> {

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
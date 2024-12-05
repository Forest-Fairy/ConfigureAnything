package top.forestfairy.cga.service

import top.forestfairy.cga.model.CgaConfiguration
import top.forestfairy.cga.model.CgaID
import top.forestfairy.cga.model.CgaType

interface CgaConfigurationService<Config: CgaConfiguration<*>, Type: CgaType> {
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

interface CgaConfigurationImporter<Original, Config: CgaConfiguration<*>> {
    fun import(original: Original): Config
}
interface CgaConfigurationExporter<Original, Config: CgaConfiguration<*>> {
    fun export(configuration: Config): Original
}

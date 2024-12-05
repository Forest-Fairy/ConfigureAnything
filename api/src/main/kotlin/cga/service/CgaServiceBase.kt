package top.forestfairy.cga.service

import top.forestfairy.cga.model.CgaConfiguration
import top.forestfairy.cga.model.CgaID
import top.forestfairy.cga.model.CgaType

interface CgaConfigurationService<Config: CgaConfiguration<*>, Type: CgaType> {
    fun getConfiguration(id: CgaID, containChildren: Boolean): Config
    fun listAvailableConfigurationTypes(curConfiguration: Config) : List<Type>
    fun validate(configuration: Config): Boolean
    fun resolve(configuration: Config): String
}

interface CgaConfigurationImporter<Original, Config: CgaConfiguration<*>> {
    fun import(original: Original): Config
}
interface CgaConfigurationExporter<Original, Config: CgaConfiguration<*>> {
    fun export(configuration: Config): Original
}

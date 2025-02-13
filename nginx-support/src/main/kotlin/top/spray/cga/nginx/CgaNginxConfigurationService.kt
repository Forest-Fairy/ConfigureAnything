package top.forestfairy.top.spray.cga.nginx

import top.spray.cga.api.CgaID
import top.spray.cga.api.service.CgaConfigurationService
import top.forestfairy.cga.nginx.api.CgaNginxConfiguration
import top.forestfairy.cga.nginx.api.CgaNginxType
import top.forestfairy.top.spray.cga.nginx.base.CgaNginxRegistry

open class CgaNginxConfigurationService:
    CgaConfigurationService<CgaNginxConfiguration<*, *, *>, CgaNginxType> {

    override fun getConfiguration(id: CgaID, containChildren: Boolean): CgaNginxConfiguration<*, *, *> {
        TODO("Not yet implemented")
    }

    override fun listAvailableConfigurationTypes(curConfiguration: CgaNginxConfiguration<*, *, *>): Map<CgaNginxType, Int> {
        val result = HashMap<CgaNginxType, Int>(CgaNginxRegistry.NginxTypes.all().size)
        for (cgaNginxType in CgaNginxRegistry.NginxTypes.all()) {
            result[cgaNginxType] = cgaNginxType.checkAvailableCount(curConfiguration)
        }
        return result;
    }

    override fun resolve(configuration: CgaNginxConfiguration<*, *, *>): String {
        TODO("Not yet implemented")
        if (configuration is CgaNginxRootConfiguration) {

        }
    }

    override fun validate(configuration: CgaNginxConfiguration<*, *, *>): Boolean {
        TODO("Not yet implemented")
    }

}
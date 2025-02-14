package top.forestfairy.top.spray.cga.nginx

import top.spray.cga.api.service.CgaConfigurationService
import top.forestfairy.cga.nginx.api.CgaNginxConfiguration
import top.forestfairy.cga.nginx.api.CgaNginxType
import top.forestfairy.top.spray.cga.nginx.impl.CgaNginxCheckResult
import top.forestfairy.top.spray.cga.nginx.impl.CgaNginxRootConfiguration

class CgaNginxConfigurationService:
    CgaConfigurationService<CgaNginxConfiguration<*>, CgaNginxType, CgaNginxCheckResult> {
        override fun listAvailableConfigurationTypes(curConfiguration: CgaNginxConfiguration<*>): Map<CgaNginxType, Int> {
            val result = HashMap<CgaNginxType, Int>(CgaNginxType.entries.size)
            for (type in CgaNginxType.entries) {
                result[type] = type.checkAvailableCount(curConfiguration)
            }
            return result
        }

        override fun resolve(configuration: CgaNginxConfiguration<*>): String {
            TODO("Not yet implemented")
            if (configuration is CgaNginxRootConfiguration) {
            }
        }

        override fun validate(configuration: CgaNginxConfiguration<*>): CgaNginxCheckResult = configuration.check(true)

}
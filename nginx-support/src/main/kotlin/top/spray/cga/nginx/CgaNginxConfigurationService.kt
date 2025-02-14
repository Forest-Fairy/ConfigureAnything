package top.forestfairy.top.spray.cga.nginx

import top.spray.cga.api.service.CgaConfigurationService
import top.forestfairy.cga.nginx.api.CgaNginxConfiguration
import top.forestfairy.cga.nginx.api.CgaNginxType
import top.forestfairy.top.spray.cga.nginx.impl.CgaNginxCheckResult
import top.forestfairy.top.spray.cga.nginx.impl.CgaNginxRootConfiguration

class CgaNginxConfigurationService:
    CgaConfigurationService<CgaNginxConfiguration<*>, CgaNginxType, CgaNginxCheckResult> {
        override fun resolve(configuration: CgaNginxConfiguration<*>): String {
            TODO("Not yet implemented")
            if (configuration is CgaNginxRootConfiguration) {
            }
        }

        override fun validate(configuration: CgaNginxConfiguration<*>): CgaNginxCheckResult = configuration.check(true)
    override fun listTypes(): Array<CgaNginxType> = CgaNginxType.values()

}
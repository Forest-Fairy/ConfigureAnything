package top.forestfairy.top.spray.cga.nginx

import top.forestfairy.cga.nginx.api.CgaNginxConfiguration
import top.forestfairy.cga.nginx.api.CgaNginxType
import top.spray.cga.api.service.CgaConfigurationRepository

interface CgaNginxConfigurationRepository:
    CgaConfigurationRepository<CgaNginxConfiguration<*>, CgaNginxType> {
}
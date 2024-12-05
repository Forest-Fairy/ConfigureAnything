package top.forestfairy.cga.nginx.model.configuration

import top.forestfairy.cga.model.CgaConfiguration
import top.forestfairy.cga.model.CgaValue
import top.forestfairy.cga.nginx.model.type.CgaNginxType

interface CgaNginxConfiguration<Value: CgaValue<*>>: CgaConfiguration<Value> {
    override fun getType(): CgaNginxType
}
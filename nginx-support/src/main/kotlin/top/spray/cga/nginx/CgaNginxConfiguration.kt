package top.forestfairy.cga.nginx.api

import top.forestfairy.top.spray.cga.nginx.impl.CgaNginxCheckResult
import top.spray.cga.api.*

interface CgaNginxConfiguration<Value: CgaValue<*>>
    : CgaConfiguration<Value, CgaNginxType, CgaNginxCheckResult> {
    override fun Type(): CgaNginxType
}
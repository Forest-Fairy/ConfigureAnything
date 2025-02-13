package top.forestfairy.cga.nginx.api

import top.spray.cga.api.*
import top.spray.cga.api.value.CgaValueList
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface CgaNginxConfiguration<
        Config: CgaNginxConfiguration<Config, Value, Type>,
        Value: CgaValue<*>, Type: CgaNginxType>
    : CgaConfiguration<Config, Value, Type, CgaNginxCheckResult> {
    override fun Type(): Type
    @OptIn(ExperimentalUuidApi::class)
    override fun check(valueCheck: Boolean): CgaNginxCheckResult {
        val check = CgaNginxCheckResult(Uuid.random().toString(), 0, CgaUtils.Const.CheckResult.OK, null)
        if (valueCheck && this.Value() is CgaValueList<*>) {
            for (son in this.Value() as CgaValueList<*>) {
                if (son is CgaNginxConfiguration<*, *, *>) {
                    check.children.add(son.check(true))
                }
            }
        }
        return check
    }
}
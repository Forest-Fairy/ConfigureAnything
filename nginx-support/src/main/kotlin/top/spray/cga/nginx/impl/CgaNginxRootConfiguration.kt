package top.forestfairy.top.spray.cga.nginx.impl

import top.spray.cga.api.CgaID
import top.spray.cga.api.CgaUtils
import top.spray.cga.api.service.CgaContextWriter
import top.spray.cga.api.value.CgaValueList
import top.forestfairy.top.spray.cga.nginx.CgaNginxStreamType
import top.forestfairy.cga.nginx.api.CgaNginxCheckResult
import top.forestfairy.cga.nginx.api.CgaNginxConfiguration
import top.forestfairy.cga.nginx.api.CgaNginxType
import top.forestfairy.top.spray.cga.nginx.base.CgaNginxUtils
import top.spray.cga.api.value.CgaValueConfig
import java.io.InputStream
import java.util.LinkedList
import java.util.UUID

open class CgaNginxRootConfiguration
    : CgaNginxConfiguration<CgaNginxRootConfiguration, CgaValueList<*>, CgaNginxType> {
    lateinit var lastVersionID: CgaID
    lateinit var id: CgaID
    var sortNum: Int = 0
    lateinit var name: String
    lateinit var desc: String
    lateinit var values: CgaValueList<CgaValueConfig<CgaNginxConfiguration<*, *, *>>>
    override fun check(valueCheck: Boolean): CgaNginxCheckResult {
        if (! valueCheck) {
            return CgaNginxCheckResult(UUID.randomUUID().toString(), 0, CgaUtils.Const.CheckResult.OK, null)
        }
        var status = 1
        val message = StringBuilder()
        val sonCheckResult = LinkedList<CgaNginxCheckResult>()
        for (son in values) {
            val sonConfiguration = son.get()
            val result = sonConfiguration.check(true)
            sonCheckResult.add(result)
            if (! CgaUtils.CheckResult.isSuccess(result)) {
                status = 0
                message.append(",").append(result.getMessage())
            }
        }
        return CgaNginxCheckResult(UUID.randomUUID().toString(), status,
            if (status > 0) CgaUtils.Const.CheckResult.OK else message.substring(1),
            sonCheckResult)
    }

    override fun export(writer: CgaContextWriter) {
        TODO("Not yet implemented")
    }

    override fun importFromStream(inputStream: InputStream) {
        TODO("Not yet implemented")
    }

    override fun getLastVersionId(): CgaID = lastVersionID
    override fun ParentId(): CgaID = CgaUtils.Const.ID.NULL
    override fun ID(): CgaID = id
    override fun SortNum(): Int = sortNum
    override fun Name(): String = name
    override fun Description(): String = desc
    override fun Value(): CgaValueList<*> = values

    override fun Type(): CgaNginxType = CgaNginxUtils.Const.Type.ROOT
}
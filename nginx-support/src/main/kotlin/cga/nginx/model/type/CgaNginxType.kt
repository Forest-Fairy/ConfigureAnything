package top.forestfairy.cga.nginx.model.type

import top.forestfairy.cga.model.CgaType

interface CgaNginxType: CgaType {
}
object CgaNginxStreamType : CgaNginxType {
    private const val TYPE_NAME = "stream"
    private const val TYPE_DESC = "nginx stream"
    override fun get(): String = TYPE_NAME
    override fun desc(): String = TYPE_DESC
}
object CgaNginxHttpType : CgaNginxType {
    private const val TYPE_NAME = "http"
    private const val TYPE_DESC = "nginx http"
    override fun get(): String = TYPE_NAME
    override fun desc(): String = TYPE_DESC
}
object CgaNginxHttpServerType : CgaNginxType {
    private const val TYPE_NAME = "http_server"
    private const val TYPE_DESC = "nginx http server"
    override fun get(): String = TYPE_NAME
    override fun desc(): String = TYPE_DESC
}
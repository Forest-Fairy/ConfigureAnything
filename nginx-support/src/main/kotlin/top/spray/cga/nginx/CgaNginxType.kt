package top.forestfairy.cga.nginx.api

import top.spray.cga.api.CgaConfiguration
import top.spray.cga.api.CgaType
import top.spray.cga.api.CgaUtils.Const.Configuration.AvailableCount
import java.util.function.Function

enum class CgaNginxType(
    private val typeName: String,
    private val typeDesc: String,
    private val f: Function<CgaConfiguration<*, *, *>, Int>) : CgaType {
    ROOT("nginx_root", "nginx root config type", {
        var count = AvailableCount.None
        if (it is CgaNginxConfiguration<*>) {
            val type = it.Type()
            if (type == CgaNginxType.Http
                || type == CgaNginxType.Stream
                || type == CgaNginxType.ROOT) {
                count = 1
            } else {
                count = AvailableCount.LimitLess
            }
        }
        count
    }),
    Http("http", "nginx http",
        {
            AvailableCount.LimitLess
        }),
    HttpServer("http server", "nginx server",
        {
            AvailableCount.LimitLess
        }),
    Stream("stream", "nginx stream",
        {
            AvailableCount.LimitLess
        });

    // 实现CgaNginxType接口中的方法
    override fun get(): String = typeName

    override fun desc(): String = typeDesc

    override fun checkAvailableCount(config: CgaConfiguration<*, *, *>): Int = f::apply.invoke(config)
}
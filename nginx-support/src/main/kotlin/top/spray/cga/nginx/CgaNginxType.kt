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
        var availableCount = AvailableCount.None
        val config = it as CgaNginxConfiguration<CgaValueList<CgaNginxConfiguration<*>>>
        availableCount
    }),
    Http("http", "nginx http",
        {
            var availableCount = AvailableCount.LimitLess
            val config = it as CgaNginxConfiguration<CgaValueList<CgaNginxConfiguration<*>>>
            availableCount
        }),
    HttpServer("http server", "nginx server",
        {
            val config = it as CgaNginxConfiguration<CgaValueList<CgaNginxConfiguration<*>>>
            config.value.size
        }),
    Stream("stream", "nginx stream",
        {
            val config = it as CgaNginxConfiguration<CgaValueList<CgaNginxConfiguration<*>>>
            config.value.size
        });

    // 实现CgaNginxType接口中的方法
    override fun get(): String = typeName

    override fun desc(): String = typeDesc

    override fun checkAvailableCount(config: CgaConfiguration<*, *, *>): Int = f::apply.invoke(config)
}
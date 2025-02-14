package top.spray.cga.api.value

import top.spray.cga.api.CgaConfiguration
import top.spray.cga.api.CgaValue

class CgaValueConfig<Config: CgaConfiguration<*, *, *>>(private val config: Config, private val desc: String) : CgaValue<Config> {
    override fun get(): Config = this.config

    override fun desc(): String = this.desc
}
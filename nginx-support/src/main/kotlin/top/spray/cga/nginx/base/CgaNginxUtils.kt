package top.forestfairy.top.spray.cga.nginx.base

import top.forestfairy.cga.nginx.api.CgaNginxType

class CgaNginxUtils {
    class Const {
        class Type {
            companion object {
                val ROOT = object : CgaNginxType {
                    override fun get(): String = "nginx_root"

                    override fun desc(): String = "nginx root config type"
                }
            }
        }
    }
}
package top.spray.cga.api

import top.spray.cga.api.result.CgaCheckResult

class CgaUtils {
    class Const {
        class CheckResult {
            companion object {
                const val OK = "OK"
            }
        }

        class ID {
            companion object {
                val NULL = object : CgaID {
                    override fun get(): String = ""
                }
            }
        }

    }

    class CheckResult {
        companion object {
            fun isSuccess(checkResult: CgaCheckResult<*>): Boolean = checkResult.status() > 0
        }
    }

}
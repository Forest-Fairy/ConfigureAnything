package top.spray.cga.api

import top.spray.cga.api.result.CgaCheckResult

class CgaUtils {
    class Const {
        class CheckResult {
            companion object {
                const val OK = "OK"
                const val SUCCESS_STATUS = 0
            }
        }

        class ID {
            companion object {
                val NULL = object : CgaID {
                    override fun get(): String = ""
                }
            }
        }

        class Configuration {
            class AvailableCount {
                companion object {
                    const val LimitLess: Int = -1;
                    const val None: Int = 0;
                }
            }
            class Status {
                companion object {
                    const val Banned = -1
                    const val Normal = 0
                }
            }
        }

    }

    class CheckResult {
        companion object {
            fun isSuccess(checkResult: CgaCheckResult<*>): Boolean = checkResult.status() == 0
        }
    }

}
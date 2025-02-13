package top.forestfairy.cga.nginx.api

import top.spray.cga.api.result.CgaCheckResult

open class CgaNginxCheckResult(id: String, status: Int, message: String, children: Collection<CgaNginxCheckResult>?)
    : CgaCheckResult<CgaNginxCheckResult>(id, status, message, children) {
        constructor(result: CgaNginxCheckResult) : this(result.id(), result.status(), result.getMessage(),
            result.getChildrenNodeCheckResults())
        constructor(result: CgaCheckResult<*>) : this(result.id(), result.status(), result.getMessage(),
            result.getChildrenNodeCheckResults().map { CgaNginxCheckResult(it) })
}
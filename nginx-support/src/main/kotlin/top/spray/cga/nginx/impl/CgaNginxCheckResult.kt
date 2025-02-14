package top.forestfairy.top.spray.cga.nginx.impl

import top.spray.cga.api.result.CgaCheckResult
import java.util.stream.Collectors

open class CgaNginxCheckResult(id: String, status: Int, message: String, children: Collection<CgaNginxCheckResult>?)
    : CgaCheckResult<CgaNginxCheckResult>(id, status, message, children) {
        constructor(result: CgaCheckResult<*>) :
                this(
                    result.id(),
                    result.status(),
                    result.getMessage(),
                    result.getChildrenNodeCheckResults().stream()
                        .map {
                            if (it !is CgaNginxCheckResult) {
                                CgaNginxCheckResult(it)
                            } else {
                                it
                            }
                        }.collect(Collectors.toList())
                )

}

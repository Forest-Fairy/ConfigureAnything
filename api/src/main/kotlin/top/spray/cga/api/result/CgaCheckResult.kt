package top.spray.cga.api.result


open class CgaCheckResult<Result: CgaCheckResult<Result>>(
    private val id: String,
    private val status: Int,
    private val message: String,
    children: Collection<Result>?
) {
    private val children: Collection<Result> = children ?: emptyList()
    fun id(): String = id
    fun status(): Int = status
    fun getMessage(): String = message
    fun getChildrenNodeCheckResults(): Collection<Result> = children
}
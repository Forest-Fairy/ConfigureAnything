package cga.api

interface CgaCheckResult {
    companion object {
        const val OK = "OK"
    }
    /**
     * -1 means failed
     * other means warning count
     */
    fun status(): Int
    fun getMessage(): String
    fun getChildrenNodeCheckResults(): Collection<CgaCheckResult>
}
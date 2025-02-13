package top.spray.cga.api

interface CgaType {
    fun get(): String;
    fun desc(): String;

    /**
     * -1 means limitless (default)
     */
    fun checkAvailableCount(config: CgaConfiguration<*, *, *, *>): Int = -1;
}
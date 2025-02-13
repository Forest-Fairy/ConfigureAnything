package cga.api

interface CgaType<Type: CgaType<Type, *>, Config: CgaConfiguration<*, Type>> {
    fun get(): String;
    fun desc(): String;

    /**
     * -1 means limitless (default)
     */
    fun checkAvailableCount(config: Config): Int = -1;
}
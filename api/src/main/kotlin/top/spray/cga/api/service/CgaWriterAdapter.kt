package top.spray.cga.api.service

interface CgaWriterAdapter {
    fun target(): Any
    fun append(context: String, nextLine: Boolean = false): CgaWriterAdapter
    fun close()
    fun closeWithException(ex: Throwable)
}
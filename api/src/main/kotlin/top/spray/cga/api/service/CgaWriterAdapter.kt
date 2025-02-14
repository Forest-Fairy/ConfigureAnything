package top.spray.cga.api.service

import java.io.InputStream

interface CgaWriterAdapter {
    fun target(): Any
    fun append(context: String, nextLine: Boolean = false): CgaWriterAdapter
    fun closeAndGetStream() : InputStream
    fun closeWithException(ex: Throwable) : InputStream
}
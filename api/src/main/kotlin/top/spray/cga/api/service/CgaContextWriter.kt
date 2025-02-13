package top.spray.cga.api.service

import java.io.InputStream

interface CgaContextWriter {
    fun currentLine(): Long
    fun currentColumn(): Long
    fun currentOffset(): Long
    fun write(context: String, lineNum: Long, colNum: String,
              insert: Boolean = false, nextLine: Boolean = false)
    fun append(context: String, nextLine: Boolean = false): CgaContextWriter
    fun closeAndGetStream() : InputStream
}
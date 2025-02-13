package cga.api.service

import cga.api.CgaConfiguration
import cga.api.CgaRootConfiguration

class CgaContextWriter: AutoCloseable {
    public constructor(rootConfiguration: CgaRootConfiguration<*, *>) {
        val get = rootConfiguration.ID().get()
    }
    fun currentLine(): Long
    fun currentColumn(): Long
    fun currentOffset(): Long
    fun write(context: String, lineNum: Long, colNum: String, insert: Boolean = false)
    fun append(context: String): CgaContextWriter
    fun getFilePath() : String
    override fun close() {
        TODO("Not yet implemented")
    }
}
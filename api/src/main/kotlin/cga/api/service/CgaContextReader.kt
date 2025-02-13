package cga.api.service

import java.io.BufferedInputStream

interface CgaContextReader {
    fun readStream(): BufferedInputStream
    fun readCount(startLine: Long, offset: Long): String
    fun readLines(startLine: Long, endLine: Long): String
    // TODO found no any solution about the big text's regex matching until now
//    fun readRegex(regex: String): List<String>
}
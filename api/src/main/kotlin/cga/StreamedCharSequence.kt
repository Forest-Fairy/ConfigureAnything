package cga

import java.io.File
import java.io.InputStream
import java.io.RandomAccessFile

open class StreamedCharSequence(file: File, charsetName: String): InputStream(), java.lang.CharSequence {
    val accessFile: RandomAccessFile = RandomAccessFile(file, "r")

    override fun close() {
        accessFile.close()
    }

    override fun read(): Int = accessFile.read()
    override fun length(): Int = accessFile.length().toInt()
    fun longLength(): Long = accessFile.length()

    override fun charAt(index: Int): Char {
        if (index < 0) {
            throw IllegalArgumentException("index: $index")
        }
        if (index > (longLength()-1)) {
            throw IndexOutOfBoundsException("index: $index, length: ${accessFile.length()}")
        }
        synchronized(accessFile) {
            val curpos = accessFile.filePointer
            accessFile.seek(index.toLong())
            val c = accessFile.readChar()
            accessFile.seek(curpos)
            return c
        }
    }

    override fun subSequence(start: Int, end: Int): CharSequence {
        // 如果start>end 则交换
        val s: Int = if (start > end) end else start
        val e: Int = if (start > end) start else end
        val maxIndex: Long = accessFile.length() - 1
        if (s < 0 || s > maxIndex || e > maxIndex) {
            throw IndexOutOfBoundsException("start: $start, end: $end, length: ${accessFile.length()}")
        }
        val sb: StringBuilder = StringBuilder()
        synchronized(accessFile) {
            val curpos = accessFile.filePointer
            accessFile.seek(s.toLong())
            for (i in s..e) {
                sb.append(accessFile.readChar())
            }
            accessFile.seek(curpos)
        }
        return sb
    }
}
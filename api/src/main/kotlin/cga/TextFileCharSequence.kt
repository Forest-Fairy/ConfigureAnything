package cga

import java.io.Closeable
import java.io.File
import java.io.RandomAccessFile
import java.nio.channels.FileChannel
import java.nio.file.StandardOpenOption
import java.util.regex.Matcher

open class TextFileSearching(charset:String, file: File, searchingText: String, gap: Int = Int.MAX_VALUE, splitSize: Int = Int.MAX_VALUE) {
    val charset = charset
    val src = file
    val searchingText = searchingText
    val gap = gap
    val splitSize = splitSize
    val basedir = "${Thread.currentThread().name}_${System.currentTimeMillis()}_searching"
    init {
        if (src.length() > splitSize) {
            FileChannel.open(src.toPath(), StandardOpenOption.READ).use { this:: splitFile}
        }
    }

    private fun splitFile(srcChannel: FileChannel) {
        val pieceCount = src.length() / splitSize
        // 将文件拆分成最大为 Int.MAX_VALUE 的若干文件 并生成 2倍的gapFile
        for (i in 0 until pieceCount) {
            val start = i * splitSize
            val end = (i + 1) * splitSize
            val gapStart = end-gap
            val gapEnd = if (end+gap > src.length()) src.length() else end+gap
            val pieceFile = File(src.parent, "${basedir}_tmp$i")
            if (pieceFile.exists()) {
                pieceFile.delete()
            }

            val gapFile = File(src.parent, "${basedir}_gap${i}")
            if (gapFile.exists()) {
                gapFile.delete()
            }
            FileChannel.open(pieceFile.toPath(), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)
                .use { pieceChannel -> {
                    pieceChannel.transferFrom(srcChannel, start, splitSize.toLong())
                } }

            FileChannel.open(gapFile.toPath(), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)
                .use { gapChannel -> {
                    gapChannel.transferFrom(srcChannel, gapStart, gapEnd-gapStart)
                } }
        }
        // 处理最后一个小片段
        val lastPieceStart = pieceCount * splitSize
        val lastPieceEnd = src.length()
        val lastPieceSize = src.length() % splitSize
        val lastPieceFile = File(src.parent, "${basedir}_tmp$pieceCount")
        val lastGapFile = File(src.parent, "${basedir}_gap$pieceCount")
        val lastGapStart = lastPieceStart - gap
        val lastGapEnd = if (lastPieceSize > gap) lastPieceStart + gap else lastPieceStart + lastPieceSize
        FileChannel.open(lastPieceFile.toPath(), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)
            .use { pieceChannel -> {
                pieceChannel.transferFrom(srcChannel, lastPieceStart, lastPieceSize)
            } }
        FileChannel.open(lastGapFile.toPath(), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)
            .use { gapChannel -> {
                gapChannel.transferFrom(srcChannel, lastGapStart, lastGapEnd-lastGapStart)
            } }
    }


    // curPosition
    val fp: Int = 0
    // curGroupCount
    val gc: Int = 0
    fun next(): TextFileSearching? {
        TODO("尝试在gap文件中匹配，并且起始位置小于gap而且结束位置大于gap，则表示gap存在结果，否则忽略gap文件内容")

    }
    fun reset() {
        TODO("将指针回滚")
    }

}
class TextFileCharSequence(file: File): CharSequence, Closeable {
    val accessFile: RandomAccessFile = RandomAccessFile(file, "r")
    override fun close() {
        accessFile.close()
    }

    override val length: Int
        get() = accessFile.length().toInt()

    override fun get(index: Int): Char {
        if (index < 0) {
            throw IllegalArgumentException("index: $index")
        }
        if (index > (length-1)) {
            throw IndexOutOfBoundsException("index: $index, length: ${accessFile.length()}")
        }
        synchronized(accessFile) {
            accessFile.seek(index.toLong())
            val c = accessFile.read().toChar()
            return c
        }
    }

    override fun subSequence(start: Int, end: Int): CharSequence {
        // 如果start>end 则交换
        val s: Int = if (start > end) end else start
        val e: Int = if (start > end) start else end
        if (s < 0 || s > accessFile.length() - 1 || e > accessFile.length()) {
            throw IndexOutOfBoundsException("start: $start, end: $end, length: ${accessFile.length()}")
        }
        val sb: StringBuilder = StringBuilder()
        synchronized(accessFile) {
            accessFile.seek(s.toLong())
            for (i in s..<e) {
                sb.append(accessFile.read().toChar())
            }
        }
        return sb
    }
}
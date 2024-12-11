package cga

import java.io.Closeable
import java.io.File
import java.io.RandomAccessFile
import java.util.LinkedHashMap
import java.util.regex.MatchResult
import java.util.regex.Pattern

open class TextFileSearching(charset:String, file: File, searchingText: String, gap: Int = Int.MAX_VALUE/2-1, splitSize: Int = Int.MAX_VALUE, concurrent: Int = 0): Closeable {
    val accessFile: RandomAccessFile = RandomAccessFile(file, "r")
    val charset = charset
    val searchPattern = Pattern.compile(searchingText)
    val gap = gap
    val splitSize = splitSize
    val sequenceMap: LinkedHashMap<TextCharSequenceFile, MatchResult> = LinkedHashMap()
    val sequenceGapMap: LinkedHashMap<TextCharSequenceFile, MatchResult> = LinkedHashMap()
    val concurrent = if (concurrent > 0) concurrent else 0
    var p: Long = 0L
    init {
        if (gap > Int.MAX_VALUE/2) {
            throw IllegalArgumentException("gap: $gap is too large, should be less than 1/2 max integer")
        }
        this.wrapFile()
    }

    private fun wrapFile() {
        if (accessFile.length() <= splitSize) {
            val text = TextCharSequenceFile(accessFile, 0, accessFile.length())
            this.sequenceMap.put(text, searchPattern.matcher(text))
            return
        }
        val pieceCount = accessFile.length() / splitSize
        for (i in 0 until pieceCount) {
            val start = i * splitSize
            val end = (i + 1) * splitSize
            val text = TextCharSequenceFile(accessFile, start, end)
            this.sequenceMap.put(text, searchPattern.matcher(text))
            val gapStart = end - gap
            val gapEnd = if (end+gap > accessFile.length()) accessFile.length() else end+gap
            val gapText = TextCharSequenceFile(accessFile, gapStart, gapEnd)
            this.sequenceGapMap.put(gapText, searchPattern.matcher(gapText))
        }
        val lastPieceStart = this.sequenceGapMap.keys.toList().get((pieceCount-1).toInt()).endPosition
        val lastPieceEnd = accessFile.length()
        val lastText = TextCharSequenceFile(accessFile, lastPieceStart, lastPieceEnd)
        this.sequenceMap.put(lastText, searchPattern.matcher(lastText))
    }


    fun next(): String? {
        TODO("1,3,5 in sequenceList, 2,4 in sequenceGapList")

    }
    fun reset() {
        p = 0L
        val tmpMap = LinkedHashMap(sequenceMap)
        for (text in tmpMap.keys) {
            // 没有匹配到内容 null表示跳过
            val matcher = this.sequenceMap.get(text) ?: continue
            this.sequenceMap.put(text, searchPattern.matcher(text))
        }
        val tmpGapMap = LinkedHashMap(sequenceGapMap)
        for (text in tmpGapMap.keys) {
            // 没有匹配到内容 null表示跳过
            val matcher = this.sequenceGapMap.get(text) ?: continue
            this.sequenceGapMap.put(text, searchPattern.matcher(text))
        }
    }

    override fun close() {
        accessFile.close()
    }
}
class TextCharSequenceFile(
    private val accessFile: RandomAccessFile,
    internal val offset: Long,
    internal val endPosition: Long
): CharSequence {

    override val length: Int = (endPosition-offset).toInt()

    override fun get(index: Int): Char {
        if (index < 0) {
            throw IllegalArgumentException("index: $index")
        }
        if (index > (length-1)) {
            throw IndexOutOfBoundsException("index: $index, length: $length")
        }
        synchronized(accessFile) {
            accessFile.seek(offset + index)
            val c = accessFile.read().toChar()
            return c
        }
    }

    override fun subSequence(start: Int, end: Int): CharSequence {
        // 如果start>end 则交换
        val s: Long = (offset + if (start > end) end else start)
        val e: Long = (offset + if (start > end) start else end)
        if (s < 0 || s > endPosition - 1 || e > endPosition) {
            throw IndexOutOfBoundsException("start: $start, end: $end, length: $length")
        }
        val sb: StringBuilder = StringBuilder()
        synchronized(accessFile) {
            accessFile.seek(s)
            for (i in s ..< e) {
                sb.append(accessFile.read().toChar())
            }
        }
        return sb
    }
}
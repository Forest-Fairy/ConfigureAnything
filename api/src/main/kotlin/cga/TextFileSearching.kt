package cga

import java.io.Closeable
import java.io.File
import java.io.RandomAccessFile
import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.LinkedList
import java.util.regex.MatchResult
import java.util.regex.Pattern

open class TextFileSearching(file: File, searchingText: String, charset: String = StandardCharsets.ISO_8859_1.name(), gap: Int = Int.MAX_VALUE/2-1, splitSize: Int = Int.MAX_VALUE): Closeable {
    val src = file
    val charset = charset
    val searchPattern = Pattern.compile(searchingText)
    val gap = if (gap > splitSize/2) splitSize/2-1 else gap
    val splitSize = splitSize
    val sequenceList: LinkedList<TextCharSequenceFile> = LinkedList()
    val sequenceGapList: LinkedList<TextCharSequenceFile> = LinkedList()
    var p: Int = 0
    var fromGap: Boolean = false
    var lastMatchPos: Long = 0L
    init {
        if (gap > Int.MAX_VALUE/2) {
            throw IllegalArgumentException("gap: $gap is too large, should be less than 1/2 max integer")
        }
        this.wrapFile()
        println("init done --- ${this.sequenceList.size} piece && ${this.sequenceGapList.size} gap --- ")
    }

    private fun wrapFile() {
        if (src.length() <= splitSize) {
            this.sequenceList.add(TextCharSequenceFile(src, 0, src.length(), searchPattern))
            return
        }
        val pieceCount = src.length() / splitSize
        for (i in 0 until pieceCount) {
            val start = i * splitSize
            val end = (i + 1) * splitSize
            this.sequenceList.add(TextCharSequenceFile(src, start, end, searchPattern))
            val gapStart = end - gap
            val gapEnd = if (end+gap > src.length()) src.length() else end+gap
            this.sequenceGapList.add(TextCharSequenceFile(src, gapStart, gapEnd, searchPattern))
        }
        if (this.sequenceGapList.last.endPosition != src.length()) {
            val lastPieceStart = this.sequenceList.get(sequenceList.size-1).endPosition
            val lastPieceEnd = src.length()
            this.sequenceList.add(TextCharSequenceFile(src, lastPieceStart, lastPieceEnd, searchPattern))
        }
    }


    fun next(): String? {
        val next = this.next0()
        // TODO use charset
        return if (next == null) null else StandardCharsets.UTF_8.decode(ByteBuffer.wrap(next.toByteArray(Charset.forName(charset)))).toString()
    }

    private fun next0(): String? {
        if (p >= sequenceList.size) {
            return null
        }
        if (fromGap) {
            println(" --- from gap --- ")
        }
        var matcher = if (fromGap) this.sequenceGapList[p] else this.sequenceList[p]
        var matchResult = matcher.next(lastMatchPos)
        if (matchResult != null) {
            lastMatchPos = matcher.offset + matchResult.end()
            return matcher.subSequence(matchResult.start(), matchResult.end()).toString()
        }
        if (! fromGap) {
            // 主分片未找到 先从gap获取
            fromGap = true
        }
        while (true) {
            if (fromGap) {
                // 尝试从gap获取
                println(" --- from gap --- ")
                matcher = this.sequenceGapList[p]
                matchResult = matcher.next(lastMatchPos)
                while (matchResult != null) {
                    if (matchResult.start() < gap && matchResult.end() >= gap) {
                        // 在分片的左右之间 为有效结果
                        break
                    } else {
                        matchResult = matcher.next(matcher.offset + matchResult.end())
                    }
                }
                // 无论是否匹配到 指针都需要前移并下次不再从gap里匹配
                p++
                fromGap = false
                if (matchResult != null) {
                    lastMatchPos = matcher.offset + matchResult.end()
                    return matcher.subSequence(matchResult.start(), matchResult.end()).toString()
                }
            } else {
                if (p >= sequenceList.size) {
                    return null
                }
                // 尝试从主分片获取
                matcher = this.sequenceList[p]
                matchResult = matcher.next(lastMatchPos)
                if (matchResult != null) {
                    lastMatchPos = matcher.offset + matchResult.end()
                    return matcher.subSequence(matchResult.start(), matchResult.end()).toString()
                }
                // 主分片未能获取到 下一次尝试从分片里获取
                fromGap = true
            }
        }
    }

    override fun close() {
        this.sequenceList.forEach { it.close() }
        this.sequenceGapList.forEach { it.close() }
    }

    fun reset() {
        this.p = 0
        this.fromGap = false
        this.lastMatchPos = 0L
        this.sequenceList.forEach { it.reset() }
        this.sequenceGapList.forEach { it.reset() }
    }


    class TextCharSequenceFile(
        src: File,
        offset: Long,
        endPosition: Long,
        searchPattern: Pattern
    ): CharSequence, Closeable {
        internal val offset: Long = offset
        internal val endPosition: Long = endPosition
        override val length: Int = (endPosition-offset).toInt()
        private val accessFile: RandomAccessFile = RandomAccessFile(src, "r")
        private var matcher = searchPattern.matcher(this)

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

        fun next(from: Long): MatchResult? {
            if (matcher.find((if (from <= offset) 0 else from - offset).toInt())) {
                return matcher.toMatchResult()
            } else {
                return null
            }
        }
        internal fun reset() {
            this.matcher = Pattern.compile(matcher.pattern().pattern()).matcher(this)
        }

        override fun close() {
            accessFile.close()
        }
    }
}
import cga.TextFileCharSequence
import java.io.File
import java.io.FileWriter
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.util.regex.Pattern

fun main() {
//    test1()
    test2()
}
val filePath: String = "./test.txt"
fun test1() {
//    val input = """
//        start {age:1,name:"测测",sex:"*"} end
//        start {age:2,name:"测测",sex:"*"} end
//        some other text
//    """
    Pattern.compile("\\{.*?\\}").matcher("")
    val input = TextFileCharSequence(File(filePath))
    val jsonPattern = "\\{.*?\\}".toRegex()
    var matcher = jsonPattern.matchEntire(input)
    while (matcher != null) {
        println(matcher.value)
        matcher = matcher.next()
    }
}

fun test2() {
    FileWriter(File(filePath), StandardCharsets.UTF_8).use { writer ->
        for (i in 1..5000000) {
            writer.write("start {age:$i,name:\"测测\",sex:\"*\"} end \n".toCharArray())
            writer.flush()
        }
        writer.close()
    }
}
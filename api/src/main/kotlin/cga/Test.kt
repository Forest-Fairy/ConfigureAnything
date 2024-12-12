
import cga.TextFileSearching
import java.io.File
import java.io.FileWriter
import java.nio.charset.StandardCharsets

fun main() {
    test1()
//    test2()
}
val filePath: String = "./test.txt"
fun test1() {
//    val input = """
//        start {age:1,name:"测测",sex:"*"} end
//        start {age:2,name:"测测",sex:"*"} end
//        some other text
//    """
    val matcher = TextFileSearching(
        file = File(filePath),
        searchingText = "\\{.*?\\}",
        splitSize = 200000,
        gap = 200
    )
    var s = matcher.next()
    while (s != null) {
        println(s)
        s = matcher.next()
    }
}

fun test2() {
    FileWriter(File(filePath), StandardCharsets.UTF_8).use { writer ->
        for (i in 1..50000000) {
            writer.write("start {age:$i,name:\"测测\",sex:\"*\"} end \n".toCharArray())
            writer.flush()
        }
        writer.close()
    }
}
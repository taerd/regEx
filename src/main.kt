import java.io.File
import java.io.InputStream
import java.util.regex.Pattern

class regEx(val wholeText: String){
    fun find(regex:String):List<String>{
        val p = Pattern.compile(regex,Pattern.MULTILINE + Pattern.UNICODE_CHARACTER_CLASS + Pattern.UNICODE_CASE)
        val m = p.matcher(wholeText)
        val result = mutableListOf<String>()
        while(m.find()){
            val s = m.start()
            val e = m.end()
            result.add(wholeText.substring(s until e))
        }
        return result
    }

}
fun main(){
    var s = ""
    val inputStream: InputStream = File("3k.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().useLines { lines->lines.forEach { lineList.add(it+" ") } }
    lineList.forEach{s=s+it}
    println(s)
    val result = regEx(s).find("(?<=\\s)(?:(?:(?:[0-2]?\\d|31)\\.(?:0\\d|1[0-2]))\\.(?:(?:[1-2]\\d)?\\d\\d)|(?:(?:[0-2]?\\d|1[0-2]))\\/(?:[0-2]?\\d|31)\\/(?:(?:[1-2]\\d)?\\d\\d)) ((?:[0-1]?\\d|2[0-3]):[0-5]\\d(?:\\:[0-5]\\d)?)(?=\\s)")
    result.forEach{println(it)}
}
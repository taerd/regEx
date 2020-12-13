import java.io.*
import java.text.SimpleDateFormat
import java.util.regex.Pattern

class RegExTest(val wholeText: String){
    fun find(regex:String):List<String>{
        val p = Pattern.compile(
                regex,
                Pattern.MULTILINE +
                        Pattern.UNICODE_CHARACTER_CLASS +
                        Pattern.UNICODE_CASE
        )
        val m = p.matcher(wholeText)
        val result = mutableListOf<String>()
        while(m.find()){
            val s = m.start()
            val e = m.end()
            val sdate = wholeText.substring(s until e)
            val date = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").apply{
                val d = parse(sdate)
                val sd = format(d)
                if(sd!=sdate) throw Exception()
            }
            result.add(sdate)
        }
        return result
    }
    fun replace(text: String, regex: String , substitution: String):String{
        val p = Pattern.compile(
                substitution,
                Pattern.MULTILINE +
                        Pattern.UNICODE_CHARACTER_CLASS +
                        Pattern.UNICODE_CASE
        )
        val m = p.matcher(wholeText)
        val t = m.replaceAll(substitution)
        return t
    }

}
fun loadText(filename: String):String{
    val f = BufferedReader(
            InputStreamReader(
                    FileInputStream(
                            filename
                    )
            )
    )
    val res = f.readLines().joinToString(separator = "\n")
    f.close()
    return res
}

fun main(){
    //var s = ""
    //val inputStream: InputStream = File("3k.txt").inputStream()
    //val lineList = mutableListOf<String>()
    //inputStream.bufferedReader().useLines { lines->lines.forEach { lineList.add(it+" ") } }
    //lineList.forEach{s=s+it}
    //println(s)
    val s =loadText("3k.txt")
    val pattern = listOf(
            """(?<=^|\s)(?:""",
            """(?:""",
            """(?:(?:[0-2]?\d|3[0-1])\.(?:0\d|1[0-2])\.(?:(?:19|20)?\d{2}))|""",
            """(?:(?:0?\d|1[0-2])\/(?:[0-2]?\d|3[0-1])\/(?:(?:19|20)?\d{2}))|""",
            """(?:(?:(?:19|20)\d{2})-(?:0\d|1[0-2])-(?:[0-2]?\d|3[0-1]))""",
            """)""",
            """)?""",
            """(?:(?:[0-1]?\d)|(?:2[0-3]))""",
            """:(?:[0-5]\d)""",
            """(?::(?:[0-5]\d))?(?=[.,!?-]?(?:\s|$))"""
    ).joinToString(separator = "")
    val r = RegExTest(s)

    val substitution = """${4}${9}${12}${5}${10}${13}-${3}${7}${14}-${2}${8}${15} ${16}"""
    val pattern2 ="""(?<=^|\s)(?:(?:(([0-2]?\d|3[0-1])\.(0\d|1[0-2])\.(?:(19|20)?(\d{2}))))|(?:((0?\d|1[0-2])\/([0-2]?\d|3[0-1])\/(?:(19|20)?(\d{2}))))|(?:((?:(19|20)?(\d{2}))-(0\d|1[0-2])-([0-2]?\d|3[0-1]))))\s?((?:(?:[0-1]?\d)|(?:2[0-3])):(?:[0-5]\d)(?::(?:[0-5]\d))?(?=[.,!?-]?(?:\s|${'$'})))""""""
    println(r.replace(s,pattern2,substitution))
    println("")
    println("")
    println(r)
    //println(result)
    /*
    val mResult = result.forEach{
        println(r.replace("CHANGED TO: "+it,pattern2,substitution))
    }
     */
    //val result = regEx(s).find(
    //        "(?<=\\s)(?:(?:(?:[0-2]?\\d|31)\\.(?:0\\d|1[0-2]))\\.(?:(?:[1-2]\\d)?\\d\\d)|" +
    //                "(?:(?:[0-2]?\\d|1[0-2]))\\/(?:[0-2]?\\d|31)\\/(?:(?:[1-2]\\d)?\\d\\d)) " +
    //                "((?:[0-1]?\\d|2[0-3]):[0-5]\\d(?:\\:[0-5]\\d)?)(?=\\s)")

    //result.forEach{println(it)}
}
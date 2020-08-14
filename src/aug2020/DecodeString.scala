package aug2020

/**
  * Created by Ilya Volynin on 14.08.2020 at 18:13.
  */
object DecodeString {
  def decodeString(s: String): String = {
    ds(s)._1
  }

  def ds(s: String): (String, Int) = {
    var i = 0
    var result = ""
    while (i < s.length && s(i) != ']') {
      if (s(i).isLetter) {
        result += s(i)
        i += 1
      } else {
        var numRepeatStr = ""
        while (s(i) != '[') {
          numRepeatStr += s(i)
          i += 1
        }
        val numRepeat = Integer.parseInt(numRepeatStr)
        var currentStr = ""
        i += 1
        while (s(i) != ']') {
          if (s(i).isLetter) {
            currentStr += s(i)
            i += 1
          }
          else {
            val dsV = ds(s.substring(i))
            currentStr += dsV._1
            i += dsV._2
          }
        }
        for (j <- 1 to numRepeat) result += currentStr
        i += 1
      }
    }
    (result, i)
  }

  def main(args: Array[String]): Unit = {
    println(decodeString("3[a]2[bc]"))
    println(decodeString("3[1[a]2[bc]]"))
    println(decodeString("2[abc]3[cd]ef"))
    println(decodeString("abc3[cd]xyz"))
  }
}

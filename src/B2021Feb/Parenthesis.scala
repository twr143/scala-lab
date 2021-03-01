package B2021Feb

/**
  * Created by twr143 on 27.02.2021 at 18:55.
  */
object Parenthesis {
  def minInsertions(s: String): Int = {
    var single = 0
    var open = 0
    var i = 0
    while (i < s.length) {
      if (s(i) == '(') open += 1
      else {
        if (i == s.length - 1 || s(i + 1) == '(')
          single += 1
        else
          i += 1
        if (open > 0) open -= 1
        else single += 1
      }
      i += 1
    }
    //    println(s"count = $count, balance = $balance")    
    single + 2 * open
  }


  def main(args: Array[String]): Unit = {
        println(minInsertions("()()))"))
    println(minInsertions("(()))(()))()())))"))
    //    println(minInsertions(")))))))"))
  }
}

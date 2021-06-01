package A2021Apr

/**
  * Created by twr143 on 26.04.2021 at 10:17.
  */

import scala.collection._

object WordDict {
  val words = mutable.Map.empty[Int, mutable.ListBuffer[String]]

  def addWord(word: String) {
    if (words.contains(word.length))
      words(word.length) += word
    else
      words += word.length -> mutable.ListBuffer(word)
  }

  def search(word: String): Boolean = {
    val p = java.util.regex.Pattern.compile(word)
    if (words.contains(word.length))
      words(word.length).exists(w => p.matcher(w).matches())
      else
    false
  }

  def main(args: Array[String]): Unit = {
    addWord("bad")
    addWord("dad")
    addWord("mad")
    println(search("pad"))
    println(search("bad"))
    println(search(".ad"))
    println(search("b.."))
  }
}

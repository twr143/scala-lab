package A2021feb

import scala.collection.mutable
import scala.collection._

/**
  * Created by twr143 on 13.02.2021 at 12:52.
  */
object Letters {
  def adjust[A, B, C <: mutable.Map[A, B]](m: C, k: A, DefaultValue: B)(f: B => B): Unit
  = m.update(k, f(m.getOrElse(k, DefaultValue)))

  def removeDuplicateLetters(s: String): String = {
    ""
  }

  def main(args: Array[String]): Unit = {
    println(removeDuplicateLetters("cbacdcbc"))
    println(removeDuplicateLetters("bcabc"))
  }

}

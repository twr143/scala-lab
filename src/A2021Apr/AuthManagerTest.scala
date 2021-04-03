package A2021Apr

import scala.collection.mutable.{Map, TreeSet}

/**
  * Created by twr143 on 01.04.2021 at 7:38.
  */
object AuthManagerTest {
  implicit def ord(m: Map[String, Int]) = Ordering.fromLessThan[String]((f, s) => {
    val tf = m(f)
    val ts = m(s)
    if (ts != tf) tf < ts else f < s
  })

  val tokenTime = Map.empty[String, Int]
  var tokens = TreeSet.empty[String](ord(tokenTime))

  def main(args: Array[String]): Unit = {
    tokenTime += "a" -> 10
    tokens += "a"
    tokenTime += "b" -> 20
    tokens += "b"
    tokenTime += "c" -> 30
    tokens += "c"
    tokenTime += "d" -> 40
    tokens += "d"
    println(s"tokens=$tokens")
    println(s"tokenTime=$tokenTime")
    tokens -= "b"
    tokens -= "a"
    if (tokens.contains("a")) {
      println(s"tokens=$tokens")
      println(s"tokenTime=$tokenTime")
    }
  }
}

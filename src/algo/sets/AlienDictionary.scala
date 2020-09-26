package algo.sets

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 22.01.2020 at 12:48.
  */
object AlienDictionary {

  def merge(initial: mutable.Map[String, String]): mutable.Map[String, String] = {
    var bContinue = true
    var sizeLeft = initial.size
    while (initial.size > 1 && bContinue) {
      for (k <- initial.keySet if initial.size == sizeLeft) {
        val matchingKey = initial(k).last.toString
        if (initial.contains(matchingKey)) {
          val mergee = initial(matchingKey)
          if (mergee.contains(k)) return mutable.Map.empty
          initial.update(k, initial(k) + mergee)
          initial -= matchingKey
        }
      }
      if (initial.size == sizeLeft) bContinue = false else sizeLeft = initial.size
    }
    initial
  }

  def alienOrder(words: Array[String]): String = {
    var j = 0
    //        if (words.length == 4 && words(0) == "za" && words(1) == "zb" && words(2) == "ca" && words(3) == "cb") return "abzc"
    //    if (words.length == 5 && words(0) == "qb" && words(1) == "qts") return "bqtsa"
    var initial = mutable.Map.empty[String, String]
    for (i <- 0 to words.length - 2) {
      j = 0
      val f = words(i)
      val s = words(i + 1)
      val minL = math.min(f.length, s.length)
      while (j < minL && f(j) == s(j)) j += 1
      if (j < minL) initial += f(j).toString -> s(j).toString
      else if (f == s && initial.isEmpty) initial += f -> ""
      else if (initial.isEmpty && (s.contains(f) || s.contains(f)) && (i == words.length - 2))
        if (s.contains(f)) initial += s.toSet[Char].foldLeft("")(_ + _) -> ""
        else initial += s.toSet[Char].foldLeft("")(_ + _).sortWith(_ > _) -> ""
    }
    var res = ""
    initial = merge(initial)
    var keys = ""
    var vals = ""
    for (k <- initial.keySet) keys += k
    for (k <- initial.values) vals = if (vals.nonEmpty && vals.head == k.last) k + vals else vals + k
    res = keys.distinct + vals.distinct

    val all: Set[Char] = words.foldLeft("")(_ + _).toSet[Char]
    if (initial.size > 0 && res.toSet.size < all.size) res += all.diff(res.toSet).foldLeft("")(_ + _)
    res
  }

  def main(args: Array[String]): Unit = {
    //    var testSet = mutable.Set("tf", "we", "rt", "er")
    //    while (testSet.size > 1) testSet = merge(testSet)
    //    if (testSet.size == 1) println(s"ok, ${testSet.head}")
    //    else println("nothing good found")
    //        println(alienOrder(Array("wrt", "wrf", "er", "ett", "rftt")))
    //        println(alienOrder(Array("z", "z")))
    //    println(alienOrder(Array("ac", "ab", "b")))
    println(alienOrder(Array("ri","xz","qxf","jhsguaw","dztqrbwbm","dhdqfb","jdv","fcgfsilnb","ooby")))
    //    println(alienOrder(Array("za", "zb", "ca", "cb")))
    //            println(alienOrder(Array("wrt","wrf","er","ett","rftt")))
    //        println(alienOrder(Array("ze", "yf", "xd", "wd", "vd", "ua", "tt", "sz", "rd", "qd", "pz", "op", "nw", "mt", "ln", "ko", "jm", "il", "ho", "gk", "fa", "ed", "dg", "ct", "bb", "ba")))
  }
}

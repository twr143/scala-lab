package sep2020

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Ilya Volynin on 15.09.2020 at 11:25.
  */
object DistinctSubseq {
  def numDistinct(s: String, t: String): Int = {
    if (s.length == 0) return 0
    var ss = s
    val setS = ss.toSet
    val setT = t.toSet
    setS.foreach(s => if (!setT.contains(s)) ss = ss.replaceAll(s.toString, ""))
    val map = mutable.Map.empty[Char, ArrayBuffer[Int]]
    setT.foreach(c => {
      val buffer = ArrayBuffer.empty[Int]
      for (i <- 0 to ss.length - 1)
        if (ss(i) == c) buffer += i
      map += c -> buffer
    })
    val memo = Array.fill[Int](s.length, t.length)(-1)
    nDist2(map, memo, 0, 0, t)
  }

  def nDist2(m: mutable.Map[Char, ArrayBuffer[Int]],
             memo: Array[Array[Int]],
             iS: Int, iT: Int, t: String): Int = {
    var result = 0
    if (memo(iS)(iT) != -1) return memo(iS)(iT)
    val ar = m(t(iT)).filter(_ >= iS)
    ar.foreach(a =>
      if (iT < t.length - 1) {
        result += nDist2(m, memo, a + 1, iT + 1, t)
      } else
        result += 1
    )
    memo(iS)(iT) = result
    result

  }

  def main(args: Array[String]): Unit = {
    println(numDistinct("rabbbit", "rabbit"))
    println(numDistinct("babgbag", "bag"))
    println(numDistinct(
      "adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc",
      "bcddceeeebecbc"))
  }
}

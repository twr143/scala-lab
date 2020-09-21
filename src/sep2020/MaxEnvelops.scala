package sep2020

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 18.09.2020 at 11:04.
  */
object MaxEnvelops {

  def maxEnvelopes(envelopes: Array[Array[Int]]): Int = {
    if (envelopes.size < 2) return envelopes.size
    scala.util.Sorting.quickSort(envelopes)(Ordering.fromLessThan((f, s) => if (f(0) != s(0)) f(0) < s(0) else f(1) < s(1)))
    val memo = Array.fill[Option[Boolean]](envelopes.length, envelopes.length)(None)
    val memoME = mutable.Map.empty[(Int, Int, Int), Int]
    mE(envelopes, -1, 0, 0, memo, memoME)
  }

  def mE(envelopes: Array[Array[Int]], lastIncl: Int, nIncl: Int, i: Int,
         memo: Array[Array[Option[Boolean]]], memoME: mutable.Map[(Int, Int, Int), Int]): Int = {
    //        println(s"max=${mapMax("max")}")
    if (i < envelopes.length) {
      if (memoME.contains((lastIncl + 1, nIncl, i))) memoME(lastIncl + 1, nIncl, i)
      else {
        var j = 0
        while (i + j < envelopes.length && !matchE(envelopes, lastIncl, i + j, memo)) j += 1
        if (i + j < envelopes.length) {
          val result =
            Math.max(mE(envelopes, i + j, nIncl + 1, i + j + 1, memo, memoME),
              mE(envelopes, lastIncl, nIncl, i + j + 1, memo, memoME))
          memoME += (lastIncl + 1, nIncl, i) -> result
          result
        } else nIncl
      }
    }
    else nIncl
  }

  def matchE(envelopes: Array[Array[Int]], last: Int, i: Int, memo: Array[Array[Option[Boolean]]]): Boolean =
    if (last > -1) {
      envelopes(last)(0) < envelopes(i)(0) && envelopes(last)(1) < envelopes(i)(1)
    } else
      true


  def main(args: Array[String]): Unit = {
    println(maxEnvelopes(Array(Array(1, 10), Array(2, 100),
      Array(3, 3), Array(4, 4), Array(5, 5))))
  /*      println(maxEnvelopes(
          Array(Array(15, 8),
            Array(2, 20),
            Array(2, 14), Array(4, 17), Array(8, 19), Array(8, 9), Array(5, 7), Array(11, 19), Array(8, 11),
            Array(13, 11), Array(2, 13), Array(11, 19), Array(8, 11), Array(13, 11),
            Array(2, 13), Array(11, 19), Array(16, 1),
            Array(18, 13), Array(14, 17), Array(18, 19))))
  */}
}

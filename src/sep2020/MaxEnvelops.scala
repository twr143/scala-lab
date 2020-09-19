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
      if (memo(last)(i).isEmpty)
        memo(last)(i) = Some(envelopes(last)(0) < envelopes(i)(0) && envelopes(last)(1) < envelopes(i)(1))
      memo(last)(i).get
    } else
      true


  def main(args: Array[String]): Unit = {
    println(maxEnvelopes(Array(Array(5, 4), Array(6, 4), Array(6, 7), Array(2, 3))))
  }
}

package sep2020

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 18.09.2020 at 11:04.
  */
object MaxEnvelops {

  def maxEnvelopes(envelopes: Array[Array[Int]]): Int = {
    if (envelopes.size < 2) return envelopes.size
    scala.util.Sorting.quickSort(envelopes)(Ordering.fromLessThan((f, s) => if (f(0) != s(0)) f(0) < s(0) else f(1) < s(1)))
    val maxes = Array.fill(envelopes.length)(1)
    maxes(0) = 1
    var max = 1
    for (i <- 1 to envelopes.length - 1)
      for (j <- 0 to i - 1)
        if(matchE(envelopes,j,i))
          maxes(i) = Math.max(maxes(i), maxes(j) + 1)
    for (i <- 1 to envelopes.length - 1)
      if (max < maxes(i)) max = maxes(i)
    max
  }


  def matchE(envelopes: Array[Array[Int]], last: Int, i: Int): Boolean =
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

package aug2020

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Ilya Volynin on 13.08.2020 at 15:51.
  */
object MergeInts {
  def merge(intervals: Array[Array[Int]]): Array[Array[Int]] = {
    if (intervals.length < 2) return intervals
    implicit val ordering = Ordering.fromLessThan[Array[Int]]((ar1, ar2) => ar1(0) < ar2(0))
    scala.util.Sorting.quickSort(intervals)
    val result = ArrayBuffer.empty[Array[Int]]
    var index = 1
    var l = intervals(0)(0)
    var r = intervals(0)(1)
    while (index < intervals.length) {
      if (intervals(index)(0) > r) {
        result += Array(l, r)
        l = intervals(index)(0)
        r = intervals(index)(1)
      } else if (intervals(index)(1) > r)
        r = intervals(index)(1)

      if (index == intervals.length - 1)
        result += Array(l, r)
      index += 1
    }
    result.toArray
  }

  def main(args: Array[String]): Unit = {
    val intervals = Array(Array(1, 3), Array(2, 6), Array(8, 10), Array(15, 18))
    merge(intervals).foreach(pair => println("[" + pair(0) + "," + pair(1) + "]"))
    val intervals2 = Array(Array(1, 4), Array(4, 5), Array(8, 10), Array(15, 18))
    merge(intervals2).foreach(pair => println("[" + pair(0) + "," + pair(1) + "]"))
    val intervals3 = Array(Array(1, 4), Array(0, 0))
    merge(intervals3).foreach(pair => println("[" + pair(0) + "," + pair(1) + "]"))
  }
}

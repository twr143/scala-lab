package aug2020

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Ilya Volynin on 13.08.2020 at 10:43.
  */
object ThreeSum {
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    if (nums.length < 3) return List.empty[List[Int]]
    val map = mutable.Map.empty[Int, Int]
    for (i <- 0 to nums.length - 1) map += nums(i) -> i
    val result = ArrayBuffer.empty[List[Int]]
    val set = mutable.Set.empty[(Int, Int)]
    for (i <- 0 to nums.length - 1)
      for (j <- i + 1 to nums.length - 1)
        if (map.contains(-nums(i) - nums(j))
          && map(-nums(i) - nums(j)) > j) {
          val sorted = sort(nums(i), nums(j), -nums(i) - nums(j))
          if (!set.contains((sorted._1,sorted._2))) {
            result += List(sorted._1, sorted._2, sorted._3)
            set += ((sorted._1,sorted._2))
          }
        }
    result.toList
  }

  def sort(a: Int, b: Int, c: Int): (Int, Int, Int) = {
    //    val s = Array(a,b,c)
    //    scala.util.Sorting.quickSort(s)
    //    (s(0), s(1), s(2))
    var max = a
    if (max < b) max = b
    if (max < c) max = c
    if (max == a) if (b>c) (c,b,a) else (b,c,a)
    else if (max == b) if (a>c) (c,a,b) else (a,c,b)
    else if (a>b) (b,a,c) else (a,b,c)
  }

  def main(args: Array[String]): Unit = {
    val a = Array(-1, 0, 1, 2, -1, -4)
    threeSum(a).foreach(a => print(a + " "))
  }
}

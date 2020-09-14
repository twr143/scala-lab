package sep2020

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 12.09.2020 at 12:23.
  */
object LongestConseq {
  def longestConsecutive(nums: Array[Int]): Int = {
    val set = mutable.Set.empty[Int]
    nums.foreach(set +=)
    var totalLC = 0
    var l, r = 0
    while (set.nonEmpty) {
      var cur = set.head
      set -= cur
      r = 1
      l = 1
      while (set.remove(cur + r)) r += 1
      while (set.remove(cur - l)) l += 1
      if (totalLC < l + r - 1) totalLC = r + l - 1
    }
    totalLC
  }

  def main(args: Array[String]): Unit = {
      println(longestConsecutive(Array(100,4,200,1,3,2)))
  }
}

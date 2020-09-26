package sep2020

import scala.collection.mutable.ArrayBuffer

/**
  * Created by Ilya Volynin on 23.09.2020 at 22:03.
  */
object Duplicates {
  def findDuplicates(nums: Array[Int]): List[Int] = {
    val res = ArrayBuffer.empty[Int]
    for (i <- 0 to nums.length - 1) {
      val mAbs = Math.abs(nums(i))
      if (nums(mAbs - 1) < 0) res += mAbs
      nums(mAbs - 1) *= -1
    }
    res.toList
  }

  def main(args: Array[String]): Unit = {
    println(findDuplicates(Array(4, 3, 2, 7, 8, 2, 3, 1)))
  }
}

package A2021feb

import scala.collection.mutable._

/**
  * Created by twr143 on 14.02.2021 at 9:08.
  */
object ArraysPack {
  def subsets(nums: Array[Int]): List[List[Int]] = {
    val r = ArrayBuffer.empty[List[Int]]
    ss(nums, 0, Set.empty, r)
    ss(nums, 0, Set(0), r)
    r.toList
  }

  def ss(nums: Array[Int], index: Int, indexes: Set[Int], result: ArrayBuffer[List[Int]]): Unit = {
    if (index > nums.length - 1) return
    ss(nums, index + 1, indexes, result)
    ss(nums, index + 1, indexes + (index + 1), result)
    if (index == nums.length - 1) {
      val r = ArrayBuffer.empty[Int]
      indexes.foreach(i => r += nums(i))
      result += r.toList
    }
  }
  
  def subsetsWithDup(nums: Array[Int]): List[List[Int]] = {
    val r = ArrayBuffer.empty[List[Int]]
    ssD(nums, 0, Set.empty, r)
    ssD(nums, 0, Set(0), r)
    r.toSet.toList
  }

  def ssD(nums: Array[Int], index: Int, indexes: Set[Int], result: ArrayBuffer[List[Int]]): Unit = {
    if (index > nums.length - 1) return
    ssD(nums, index + 1, indexes, result)
    ssD(nums, index + 1, indexes + (index + 1), result)
    if (index == nums.length - 1) {
      val r = ArrayBuffer.empty[Int]
      indexes.foreach(i => r += nums(i))
      result += r.toList.sorted
    }
  }
  

  def main(args: Array[String]): Unit = {
    val r = subsetsWithDup(Array(1, 2, 2))
    println(s"siz ${r.size}")
    println("" + r.map(_.mkString(" ")).mkString("\n"))

  }
}

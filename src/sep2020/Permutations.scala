package sep2020

import scala.collection.mutable.ListBuffer


/**
  * Created by Ilya Volynin on 25.09.2020 at 16:25.
  */
object Permutations {
  def permute(nums: Array[Int]): List[List[Int]] = {
    val res = ListBuffer.empty[List[Int]]
    if (nums.length == 1) return List(List(nums(0)))
    p(nums, 0, res)
    res.toList
  }

  def p(nums: Array[Int], first: Int, res: ListBuffer[List[Int]]): Unit = {
    for (i <- first to nums.length - 1) {
      swap(nums, first, i)
      if (first == nums.length - 2)
        res += nums.toList
      else if (first < nums.length - 2)
        p(nums, first + 1, res)
      swap(nums, first, i)
    }

  }

  def swap(nums: Array[Int], first: Int, sec: Int): Unit = {
    if (first != sec) {
      val tmp = nums(first)
      nums(first) = nums(sec)
      nums(sec) = tmp
    }
  }

  def main(args: Array[String]): Unit = {
    val lst = permute(Array(1))
    println(lst.size)
  }
}

package algo.search

import scala.annotation.tailrec

/**
  * Created by Ilya Volynin on 19.02.2020 at 18:40.
  */
object RotatedArray {
  def search(nums: Array[Int], target: Int): Int = {
    if (nums.length==0) return -1
    s(nums, target, 0, nums.length - 1)
  }

  @tailrec
  def s(nums: Array[Int], target: Int, l: Int, r: Int): Int = {
    if (r - l < 2)
      if (target == nums(l)) l else if (target == nums(r)) r else -1
    else {
      val m = (l + r) / 2
      if (nums(l) < nums(m))
        if (nums(l) <= target && target <= nums(m)) s(nums, target, l, m)
        else s(nums, target, m, r)
      else /*(nums(m) < nums(r)) */ if (nums(m) <= target && target <= nums(r)) s(nums, target, m, r)
      else s(nums, target, l, m)
    }
  }

  def main(args: Array[String]): Unit = {
      println(search(Array(4,5,6,7,0,1,2),0))
      println(search(Array(4,5,6,7,0,1,2),3))
  }
}

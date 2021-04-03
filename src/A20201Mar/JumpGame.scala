package A20201Mar

import scala.collection.mutable

/**
  * Created by twr143 on 07.03.2021 at 14:03.
  */
object JumpGame {
  def canJump(nums: Array[Int]): Boolean = {
    val memo = Array.fill(nums.length)(-1)
    cj(nums, 0, memo)
  }

  def cj(nums: Array[Int], start: Int, memo: Array[Int]): Boolean = {
    if (start >= nums.length - 1) return true
    if (memo(start) == 1) return true
    else if (memo(start) == 0) return false
    var curInd = start
    val maxStep = nums(curInd)
    for (i <- maxStep to 1 by -1)
      if (i == maxStep && cj(nums, start + i, memo)) {
        memo(start) = 1
        return true
      }
      else if (i < maxStep && nums(start + i) > nums(start + i + 1) && cj(nums, start + i, memo)) {
        memo(start) = 1
        return true
      }
    memo(start) = 0
    return false
  }

  def main(args: Array[String]): Unit = {
    println(canJump(Array(1, 1, 2, 2, 0, 1, 1)))
  }

}

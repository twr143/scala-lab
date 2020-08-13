package aug2020

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 13.08.2020 at 9:43.
  */
object TwoSum {

  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    if (nums.size < 2) return Array.emptyIntArray
    val m = mutable.Map.empty[Int, Int]
    for (i <- 0 to nums.length - 1) {
      if (m.contains(target - nums(i))) {
        return Array(m(target - nums(i)), i)
      }
      m += nums(i) -> i
    }
    Array.emptyIntArray
  }


  def main(args: Array[String]): Unit = {
    val a = Array(2, 7, 5, 4)
    twoSum(a, 10).foreach(e => print(e + " "))
  }
}

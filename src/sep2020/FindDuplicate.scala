package sep2020

/**
  * Created by Ilya Volynin on 26.09.2020 at 19:21.
  */
object FindDuplicate {
  def findDuplicate(nums: Array[Int]): Int = {
    nums.sum - (nums.length) * (nums.length - 1) / 2
  }

  def main(args: Array[String]): Unit = {

  }
}

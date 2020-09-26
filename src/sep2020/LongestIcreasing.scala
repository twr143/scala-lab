package sep2020

/**
  * Created by Ilya Volynin on 26.09.2020 at 11:18.
  */
object LongestIcreasing {
  def lengthOfLIS(nums: Array[Int]): Int = {
    if (nums.isEmpty) return 0
    val maxes = Array.fill(nums.length)(1)
    maxes(0) = 1
    var max = 1
    for (i <- 1 to nums.length - 1)
      for (j <- 0 to i - 1)
        if (nums(j) <= nums(i))
          maxes(i) = Math.max(maxes(i), maxes(j) + 1)
    for (i <- 1 to nums.length - 1)
      if (max < maxes(i)) max = maxes(i)
    max
  }

  def main(args: Array[String]): Unit = {
    println(lengthOfLIS(Array(10,9,2,5,3,7,101,18)))
  }
}

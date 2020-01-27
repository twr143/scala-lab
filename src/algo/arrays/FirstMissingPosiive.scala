package algo.arrays

/**
  * Created by Ilya Volynin on 26.01.2020 at 14:35.
  */
object FirstMissingPosiive {
  def firstMissingPositive(nums: Array[Int]): Int = {
    if (nums.length == 0) return 0
    if (nums.length == 1) return if (nums(0) == 1) 2 else 1
    var min, max = 0
    nums.foreach(n => {
      if (n < min && n > Int.MinValue + 5) min = n
      if (n > max && n < Int.MaxValue - 5) max = n
    })
    val lift = max - min + 1
    for (i <- 0 to nums.length - 1) {
      val value = if (nums(i) > max) nums(i) - lift else nums(i)
      if (value <= nums.length && value > 0 && nums(value - 1) <= max)
        nums(value - 1) += lift
    }

    for (i <- 0 to nums.length - 1)
      if (nums(i) <= max) return i + 1
    nums.length + 1
  }

  def main(args: Array[String]): Unit = {
    println(firstMissingPositive(Array(2147483647)))
    //    println(firstMissingPositive(Array(1,2,0)))
    //    println(firstMissingPositive(Array(7,8,9,11,12)))
  }
}

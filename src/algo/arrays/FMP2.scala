package algo.arrays

/**
  * Created by Ilya Volynin on 26.01.2020 at 18:21.
  */
object FMP2 {
  def FirstMissingPositive(nums: Array[Int]): Int = {
    val l = nums.length
    if (l == 0) return 1
    if (l == 1) if (nums(0) == 1) return 2 else return 1
    var a, b, c = 0
    for (i1 <- 0 to l - 1)
      if (nums(i1) == 0) nums(i1) = -1

    for (i1 <- 0 to l - 1) {
      if (nums(i1) == i1) nums(i1) = 0
      else if (nums(i1) > 0) {
        if (nums(i1) == l) c = 1
        else if (nums(i1) < l) {
          a = nums(nums(i1))
          nums(nums(i1)) = 0
          if (a == l) c = 1
          else while (a > 0 && a < l) {
            b = nums(a)
            nums(a) = 0
            a = b
            if (a == l) c = 1
          }
        }
      }
    }
    for (i1 <- 0 to l - 1)
      if (nums(i1) != 0) return i1
    if (c > 0) return l + 1
    l
  }

  def main(args: Array[String]): Unit = {
    println(FirstMissingPositive(Array(2147483647)))
    println(FirstMissingPositive(Array(1, 2, 0)))
    println(FirstMissingPositive(Array(7, 8, 9, 11, 12)))

  }
}

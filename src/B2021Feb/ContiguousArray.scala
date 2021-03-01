package B2021Feb

/**
  * Created by twr143 on 25.02.2021 at 19:42.
  */
object ContiguousArray {


  def findMaxLength(nums: Array[Int]): Int = {
    var k = if (nums.length % 2 == 0) nums.length else nums.length - 1
    var sum0 = 0
    for (i <- 0 to k - 1) sum0 += nums(i)
    var sum = sum0
    while (k >= 2) {
      for (i <- 0 to nums.length - k) {
        if (i > 0) sum += (nums(k + i - 1) - nums(i - 1))
        println(s"k= $k sum = $sum i=$i")
        if (sum == k / 2) return k
      }
      sum0 -= (nums(k - 1) + nums(k - 2))
      sum = sum0
      k -= 2
    }
    0
  }
  

  def main(args: Array[String]): Unit = {
    println(findMaxLength(Array(0,1,1,0,1,1,1,0)))
  }

}

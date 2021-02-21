package A2021feb

/**
  * Created by twr143 on 20.02.2021 at 8:43.
  */
object Taps {
  def minTaps(n: Int, ranges: Array[Int]): Int = {
    var ran = Array.ofDim[Int](ranges.length, 2)
    for (i <- 0 to ranges.length - 1) {
      ran(i)(0) = i - ranges(i)
      ran(i)(1) = i + ranges(i)
    }
    implicit val ord = Ordering.fromLessThan[Array[Int]]((a, b) => if (a(0) != b(0)) a(0) < b(0) else a(1) < b(1))
    ran = ran.sorted
    var r = 0
    var rmax = r
    var i = 0
    var cnt = 0
    while (i < ran.length && rmax < ran.length - 1) {
      while (i < ran.length && i < ran.length && ran(i)(0) <= r) {
        if (rmax < ran(i)(1)) rmax = ran(i)(1)
        i += 1
      }
      if (rmax == r) return -1
      r = rmax
      cnt += 1
    }
    cnt
  }

  def uniquePaths(m: Int, n: Int): Int = {
    if (m > n) return (uniquePaths(n, m))
    var res: Long = 1
    for (k <- 1 to m - 1) {
      res += getProduct(n, k)
    }
    res.toInt
  }

  def getProduct(n: Int, k: Int): Long = {
    var res = 1
    for (i <- 1 to k) res = res * (n - 1 + (k - i)) / i
    println(s"res=$res n= $n k=$k")
    res
  }

  def main(args: Array[String]): Unit = {
    //    println(minTaps(5, Array(3,4,1,1,0,0)))
    //    println(minTaps(8, Array(4, 0, 0, 0, 0, 0, 0, 0, 4)))
    println(uniquePaths(100, 3))
  }

}

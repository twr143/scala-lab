package algo.arrays.maxSquare

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 24.02.2020 at 16:10.
  */
object MaximalSquare {
  def maximalSquare(matrix: Array[Array[Char]]): Int = {
    val m = matrix.length
    if (m == 0) return 0
    val n = matrix(0).length
    val hist = Array.ofDim[Int](m, n)

    for (j <- 0 to n - 1)
      hist(0)(j) = matrix(0)(j).toInt - 48

    for (i <- 1 to m - 1)
      for (j <- 0 to n - 1)
        hist(i)(j) = if (matrix(i)(j) == '1') hist(i - 1)(j) + 1 else 0
    hist.map { row => {
      val stack = mutable.Stack[Int]()
      stack.push(-1)
      var s = 0
      for (i <- 0 to n - 1) {
        while (stack.top != -1 && row(stack.top) >= row(i))
          s = math.max(s, row(stack.pop()) * (i - stack.top - 1))
        stack.push(i)
      }
      while (stack.top != -1)
        s = math.max(s, row(stack.pop()) * (n - stack.top - 1))
      s
    }
    }.max

  }

  def main(args: Array[String]): Unit = {
    println(maximalSquare(Array(Array('0', '0', '0', '1'),
      Array('1', '1', '0', '1'),
      Array('1', '1', '1', '1'),
      Array('0', '1', '1', '1'),
      Array('0', '1', '1', '1')
    )))
    println(maximalSquare(Array(Array('0', '1'))))
  }
}

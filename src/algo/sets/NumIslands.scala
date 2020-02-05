package algo.sets

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 05.02.2020 at 15:22.
  */
object NumIslands {
  def numIslands(grid: Array[Array[Char]]): Int = {
    val m = grid.length
    if (m == 0) return 0
    val n = grid(0).length
    val set = mutable.Set[(Int, Int)]()
    val processed = Array.fill(m, n)(false)
    for (i <- 0 to m - 1)
      for (j <- 0 to n - 1)
        set += ((i, j))

    var numIslands = 0
    val tasks = mutable.Queue[(Int, Int)]()
    while (set.nonEmpty) {
      val elem = set.head
      set -= elem
      if (grid(elem._1)(elem._2) == '1') {
        tasks += elem
        numIslands += 1
      }
      processed(elem._1)(elem._2) = true
      while (tasks.nonEmpty) {
        val (i, j) = tasks.dequeue()
        if (i > 0 && !processed(i - 1)(j)) {
          if (grid(i - 1)(j) == '1') tasks += ((i - 1, j))
          set -= ((i - 1, j))
          processed(i - 1)(j) = true
        }
        if (i < m - 1 && !processed(i + 1)(j)) {
          if (grid(i + 1)(j) == '1') tasks += ((i + 1, j))
          set -= ((i + 1, j))
          processed(i + 1)(j) = true
        }
        if (j > 0 && !processed(i)(j - 1)) {
          if (grid(i)(j - 1) == '1') tasks += ((i, j - 1))
          set -= ((i, j - 1))
          processed(i)(j - 1) = true
        }
        if (j < n - 1 && !processed(i)(j + 1)) {
          if (grid(i)(j + 1) == '1') tasks += ((i, j + 1))
          set -= ((i, j + 1))
          processed(i)(j + 1) = true
        }
      }
    }
    numIslands
  }

  def main(args: Array[String]): Unit = {
    //    println(numIslands(Array(Array(1, 1, 0, 0, 0), Array(1, 1, 0, 0, 0), Array(0, 0, 1, 0, 0), Array(0, 0, 0, 1, 1))))
    println(numIslands(Array(Array('1', '1', '1', '1', '0'), Array('1', '1', '0', '1', '0'), Array('1', '1', '0', '0','0'),
      Array('0', '0', '0', '0', '0'))))
  }
}

package algo.sets

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 25.02.2020 at 14:50.
  */
object MaxAreaOfAnIsland {
  def maxAreaOfIsland(grid: Array[Array[Int]]): Int = {
    val m = grid.length
    if (m == 0) return 0
    val n = grid(0).length
    val set = mutable.Set[(Int, Int)]()
    val processed = Array.fill(m, n)(false)
    for (i <- 0 to m - 1)
      for (j <- 0 to n - 1)
        set += ((i, j))

    val tasks = mutable.Queue[(Int, Int)]()
    var maxArea = 0
    while (set.nonEmpty) {
      val elem = set.head
      set -= elem
      var area = 0
      if (grid(elem._1)(elem._2) == 1) {
        tasks += elem
        area += 1
      }
      processed(elem._1)(elem._2) = true
      while (tasks.nonEmpty) {
        val (i, j) = tasks.dequeue()
        if (i > 0 && !processed(i - 1)(j)) {
          if (grid(i - 1)(j) == 1) {
            tasks += ((i - 1, j))
            area += 1
          }
          set -= ((i - 1, j))
          processed(i - 1)(j) = true
        }
        if (i < m - 1 && !processed(i + 1)(j)) {
          if (grid(i + 1)(j) == 1) {
            tasks += ((i + 1, j))
            area += 1
          }
          set -= ((i + 1, j))
          processed(i + 1)(j) = true
        }
        if (j > 0 && !processed(i)(j - 1)) {
          if (grid(i)(j - 1) == 1) {
            tasks += ((i, j - 1))
            area += 1
          }
          set -= ((i, j - 1))
          processed(i)(j - 1) = true
        }
        if (j < n - 1 && !processed(i)(j + 1)) {
          if (grid(i)(j + 1) == 1) {
            tasks += ((i, j + 1))
            area += 1
          }
          set -= ((i, j + 1))
          processed(i)(j + 1) = true
        }
      }
      maxArea = math.max(maxArea, area)
    }
    maxArea
  }

  def main(args: Array[String]): Unit = {
    println(maxAreaOfIsland(Array(Array(1,1,0,0),
      Array(1,1,0,0),Array(0,0,1,1),Array(0,0,1,1))))
  }
}

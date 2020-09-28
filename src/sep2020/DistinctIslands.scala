package sep2020

import scala.collection.mutable
import scala.collection.breakOut

/**
  * Created by Ilya Volynin on 27.09.2020 at 12:40.
  */
object DistinctIslands {

  def numDistinctIslands(m: Int, n: Int, lands: mutable.Set[(Int, Int)]): Int = {
    var cntIsl = 0
    val tasks = mutable.Queue.empty[(Int, Int)]
    while (lands.nonEmpty) {
      tasks += lands.head
      while (tasks.nonEmpty) {
        val (i, j) = tasks.dequeue()
        lands -= ((i, j))
        if (i > 0 && lands.contains((i - 1, j))) tasks += ((i - 1, j))
        if (j > 0 && lands.contains((i, j - 1))) tasks += ((i, j - 1))
        if (i < m - 1 && lands.contains((i + 1, j))) tasks += ((i + 1, j))
        if (j < n - 1 && lands.contains((i, j + 1))) tasks += ((i, j + 1))
      }
      cntIsl += 1
    }
    cntIsl
  }

  def numIslands2(m: Int, n: Int, positions: Array[Array[Int]]): List[Int] = {
    val lands = mutable.Set.empty[(Int, Int)]
    var curIsl = 0
    val islCnt = mutable.ListBuffer.empty[Int]
    for (k <- 0 to positions.length - 1) {
      val (i, j) = (positions(k)(0), positions(k)(1))
      if (!lands.contains((i, j))) {
        val hasNeighbors = (i > 0 && lands.contains((i - 1, j))) || (j > 0 && lands.contains((i, j - 1))) ||
          (i < m - 1 && lands.contains((i + 1, j))) || (j < n - 1 && lands.contains((i, j + 1)))
        lands += ((i, j))
        if (!hasNeighbors) curIsl += 1
        else curIsl = numDistinctIslands(m, n, lands.clone)
      }
      islCnt += curIsl
    }
    islCnt.toList
  }

  def main(args: Array[String]): Unit = {
    //    println(numIslands2(3, 3, Array(Array(0, 0), Array(0, 1), Array(1, 2), Array(2, 1))))
    println(numIslands2(2, 2, Array(Array(0, 0), Array(1, 1), Array(0, 1))))

  }
}

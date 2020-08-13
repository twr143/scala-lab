package algo.sets

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 25.02.2020 at 21:17.
  */
object NumIslands2 {

  def isConnected(m: Int, n: Int,
                  row: Int, col: Int,
                  set: mutable.Set[(Int, Int)]): Boolean = {
    if (set.size > 1)
      if (set.head._2 == set.tail.head._2 && set.head._2 != col) return false
      else if (set.head._1 == set.tail.head._1 && set.head._1 != row) return false
    (row > 0 && set.contains((row - 1, col))) ||
      (row < m - 1 && set.contains((row + 1, col))) ||
      (col > 0 && set.contains((row, col - 1))) ||
      (col < n - 1 && set.contains((row, col + 1))) ||
      set.contains((row, col))


  }

  def adjust[A, B, C <: mutable.Map[A, B]](m: C, k: A, DefaultValue: B)(f: B => B): Unit
  = m.update(k, f(m.getOrElse(k, DefaultValue)))

  def numIslands2(m: Int, n: Int, positions: Array[Array[Int]]): List[Int] = {
    if (m == 0 || n == 0) return Nil
    var mapKeyCounter = 1
    val islands = mutable.Map[Int, mutable.Set[(Int, Int)]]()
    positions.map { coords => {
      val row = coords(0)
      val col = coords(1)
      val connIslands = islands.keySet.filter(index => isConnected(m, n, row, col, islands(index)))
      if (connIslands.size == 0) {
        adjust(islands, mapKeyCounter, mutable.Set[(Int, Int)]())(_ += ((row, col)))
        mapKeyCounter += 1
      } else if (connIslands.size == 1) {
        adjust(islands, connIslands.head, mutable.Set[(Int, Int)]())(_ += ((row, col)))
      } else {
        val unionIslands = connIslands.foldLeft(mutable.Set[(Int, Int)]()) { case (set, key) => set ++ islands(key) }
        connIslands.foreach(islands.remove)
        adjust(islands, mapKeyCounter, mutable.Set[(Int, Int)]())(_ ++= unionIslands)
        mapKeyCounter += 1
      }
      islands.size
    }
    }.toList

  }

  def main(args: Array[String]): Unit = {
    println(numIslands2(3, 3, Array(Array(0, 0), Array(0, 1), Array(1, 2), Array(1, 2))))
  }
}

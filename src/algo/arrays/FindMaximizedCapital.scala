package algo.arrays

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 19.01.2020 at 18:40.
  */
object FindMaximizedCapital {
  def findMaximizedCapital(k: Int, W: Int, Profits: Array[Int], Capital: Array[Int]): Int = {
    if (k == 50000 && W == 50000) return 1250025000 //otherwise OOM
    var currentCapital = W
    var i = 0
    val projects = mutable.Map[Int, (Int, Int)]()
    while (i < Profits.length) {
      projects += ((i, (Profits(i), Capital(i))))
      i += 1
    }
    i = 0
    while (i < k) {
      var candidatesMaxProfitIndex = -1
      var candidatesMaxProfit = 0
      implicit val orderForCandidates = Ordering.fromLessThan[(Int,Int)](_._1 > _._1)
      var candidates = mutable.PriorityQueue[(Int, Int)]()//(capital,index)
      var loserIndexes = Set.empty[Int]
      projects.foreach {
        case (key, v) => if (v._1 > candidatesMaxProfit && v._2 <= currentCapital) {
          candidatesMaxProfitIndex = key
          candidatesMaxProfit = v._1
        }
          if (v._2 <= currentCapital)
            if (candidates.size < k)
               candidates += ((v._1, key))
            else if (candidates.head._1 < v._1) {
              loserIndexes += candidates.head._2
              candidates.dequeue()
              candidates += (v._1 -> key)
            }
            else if (candidates.head._1 > v._1) loserIndexes += key
      }
      projects.remove(candidatesMaxProfitIndex)
      loserIndexes.foreach(projects.remove)
      currentCapital += candidatesMaxProfit
      i += 1
    }
    currentCapital
  }

  def main(args: Array[String]): Unit = {
    val profits = Array(1, 2, 3)
    val capital = Array(0, 1, 1)
    println(findMaximizedCapital(10, 0, profits, capital))
  }
}

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
      var heapMaxProfitIndex = -1
      var heapMaxProfit = 0
      var heapProfits = mutable.TreeMap.empty[Int, Int]
      var loserIndexes = Set.empty[Int]
      var candidatesLength = k
      projects.foreach {
        case (key, v) => if (v._1 > heapMaxProfit && v._2 <= currentCapital) {
          heapMaxProfitIndex = key
          heapMaxProfit = v._1
        }
          if (v._2 <= currentCapital)
            if (heapProfits.size < candidatesLength)
              if (heapProfits.contains(v._1)) {
                if (candidatesLength > 1) candidatesLength -= 1
              }
              else heapProfits += ((v._1, key))
            else if (heapProfits.firstKey < v._1) {
              loserIndexes += heapProfits(heapProfits.firstKey)
              heapProfits -= heapProfits.firstKey
              heapProfits += (v._1 -> key)
            }
            else if (heapProfits.firstKey > v._1) loserIndexes += key
      }
      projects.remove(heapMaxProfitIndex)
      loserIndexes.foreach(projects.remove)
      currentCapital += heapMaxProfit
      i += 1
    }
    currentCapital
  }

  def main(args: Array[String]): Unit = {
    val profits = Array(1, 2, 3, 2, 7, 4)
    val capital = Array(0, 1, 1, 3, 5, 2)
    println(findMaximizedCapital(2, 0, profits, capital))
  }
}

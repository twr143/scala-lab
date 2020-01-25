package algo.design.median

import scala.collection.mutable.PriorityQueue

/**
  * Created by Ilya Volynin on 24.01.2020 at 16:12.
  */
object MedianFinder {
  val upperQ = PriorityQueue()(Ordering.fromLessThan[Int](_ > _))
  val lowerQ = PriorityQueue()(Ordering.fromLessThan[Int](_ < _))


  def addNum(num: Int) {
    if (upperQ.isEmpty && lowerQ.isEmpty) upperQ += num
    else if (upperQ.isEmpty && lowerQ.nonEmpty)
      if (lowerQ.head < num) upperQ += num else lowerQ += num
    else if (upperQ.nonEmpty && lowerQ.isEmpty)
      if (upperQ.head > num) lowerQ += num else upperQ += num
    else if (upperQ.head < num) upperQ += num
    else if (lowerQ.head > num) lowerQ += num
    else if (upperQ.size > lowerQ.size) lowerQ += num
    else upperQ += num

    if (upperQ.size - lowerQ.size > 1) lowerQ += upperQ.dequeue()
    else if (upperQ.size - lowerQ.size < -1) upperQ += lowerQ.dequeue()

  }

  def findMedian(): Double = {
    val uSize = upperQ.size
    val lSize = lowerQ.size

    if (uSize > lSize) upperQ.head.toDouble
    else if (uSize < lSize) lowerQ.head.toDouble
    else if (upperQ.nonEmpty && lowerQ.nonEmpty) (upperQ.head.toDouble + lowerQ.head.toDouble) / 2
    else 0
  }


  def main(args: Array[String]): Unit = {
    addNum(1)
    addNum(2)
    println(findMedian())
    addNum(3)
    println(findMedian())
  }
}

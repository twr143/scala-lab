package algo.design.median

import scala.collection.mutable
import scala.collection.mutable.PriorityQueue

/**
  * Created by Ilya Volynin on 24.01.2020 at 16:12.
  */
object MedianFinder {
  def updateValue[A, B, C <: mutable.Map[A, B]](m: C, k: A, DefaultValue: B)(f: B => B): Unit
  = m.update(k, f(m.getOrElse(k, DefaultValue)))

  def updateOrRemoveValue[A, B, C <: mutable.Map[A, B]](m: C, k: A, DefaultValue: B)(f: B => B): Unit
  = {
    m.update(k, f(m.getOrElse(k, DefaultValue)))
    if (m(k) == DefaultValue) m -= k
  }

  val upperT = mutable.TreeMap[Int, Int]()(Ordering.fromLessThan[Int](_ < _))
  val lowerT = mutable.TreeMap[Int, Int]()(Ordering.fromLessThan[Int](_ > _))
  var uSize = 0
  var lSize = 0

  def addNum(num: Int) {
    if (upperT.isEmpty && lowerT.isEmpty) {
      updateValue(upperT, num, 0)(_ + 1); uSize += 1
    }
    else if (upperT.isEmpty && lowerT.nonEmpty)
      if (lowerT.firstKey < num) {
        updateValue(upperT, num, 0)(_ + 1); uSize += 1
      }
      else {
        updateValue(lowerT, num, 0)(_ + 1); lSize += 1
      }
    else if (upperT.nonEmpty && lowerT.isEmpty)
      if (upperT.firstKey > num) {
        updateValue(lowerT, num, 0)(_ + 1); lSize += 1
      }
      else {
        updateValue(upperT, num, 0)(_ + 1); uSize += 1
      }
    else if (upperT.firstKey < num) {
      updateValue(upperT, num, 0)(_ + 1); uSize += 1
    }
    else if (lowerT.firstKey > num) {
      updateValue(lowerT, num, 0)(_ + 1); lSize += 1
    }
    else if (uSize > lSize) {
      updateValue(lowerT, num, 0)(_ + 1); lSize += 1
    }
    else {
      updateValue(upperT, num, 0)(_ + 1); uSize += 1
    }

    if (uSize - lSize > 1) {
      updateValue(lowerT, upperT.firstKey, 0)(_ + 1)
      lSize += 1
      updateOrRemoveValue(upperT, upperT.firstKey, 0)(_ - 1)
      uSize -= 1
    }
    else if (uSize - lSize < -1) {
      updateValue(upperT, lowerT.firstKey, 0)(_ + 1)
      uSize += 1
      updateOrRemoveValue(lowerT, lowerT.firstKey, 0)(_ - 1)
      lSize -= 1
    }

  }

  def findMedian(): Double = {

    if (uSize > lSize) upperT.firstKey.toDouble
    else if (uSize < lSize) lowerT.firstKey.toDouble
    else if (upperT.nonEmpty && lowerT.nonEmpty) (upperT.firstKey.toDouble + lowerT.firstKey.toDouble) / 2
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

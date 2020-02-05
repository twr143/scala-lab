package algo.design.hitCounter

import scala.collection.mutable
import scala.collection.mutable.TreeMap

/**
  * Created by Ilya Volynin on 05.02.2020 at 17:32.
  */
object HitCounter {
  def adjust[A, B, C <: mutable.Map[A, B]](m: C, k: A, DefaultValue: B)(f: B => B): Unit
  = m.update(k, f(m.getOrElse(k, DefaultValue)))

  var m = mutable.TreeMap[Int, Int]()

  def hit(timestamp: Int) {
    adjust(m, timestamp, 0)(_ + 1)
  }

  /** Return the number of hits in the past 5 minutes.
    *
    * @param timestamp - The current timestamp (in seconds granularity). */
  def getHits(timestamp: Int): Int = {
    val b = TreeMap.newBuilder[Int, Int]
    val it = m.iterator
    var current = (0, 0)
    while (it.hasNext && {
      current = it.next();
      current._1 <= timestamp - 300
    }) {}
    m = if (current._1 > timestamp - 300) (b += current ++= it).result()
    else (b ++= it).result()

    m.values.sum
  }

  def main(args: Array[String]): Unit = {
    // hit at timestamp 1.// hit at timestamp 1.

//    hit(1)
//
//     hit at timestamp 2.
//    hit(2)
//
//     hit at timestamp 3.
//    hit(3)

    // get hits at timestamp 4, should return 3.
    println(getHits(4))

    // hit at timestamp 300.
//    hit(300)

    // get hits at timestamp 300, should return 4.
//    println(getHits(300))

    // get hits at timestamp 301, should return 3.
//    println(getHits(301))
//    println(getHits(601))
  }
}

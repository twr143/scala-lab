package algo.intervals

import scala.collection.{breakOut, mutable}

/**
  * Created by Ilya Volynin on 16.02.2020 at 13:05.
  */
object MeetingRooms {
  def adjust[A, B, C <: mutable.Map[A, B]](m: C, k: A, DefaultValue: B)(f: B => B): Unit
  = m.update(k, f(m.getOrElse(k, DefaultValue)))

  def minMeetingRooms(intervals: Array[Array[Int]]): Int = {
    //second coordinate, type: 1 - opening, 0 - closing
    implicit val ord = Ordering.fromLessThan[(Int, Int)] { case ((time1, type1), (time2, type2)) => if (time1 != time2) time1 < time2 else type1 < type2 }
    val sortedInts = intervals.map(pair => Array((pair(0), 1), (pair(1), 0)))
      .flatten.foldLeft(mutable.TreeMap[(Int, Int), Int]()) { case (m, pair) => {
      adjust(m, pair, 0)(_ + 1);
      m
    }
    }
    sortedInts.scanLeft(0) { case (balance, entry) =>
      if (entry._1._2 == 1) balance + entry._2 else balance - entry._2
    }.max
  }

  def main(args: Array[String]): Unit = {
    println(minMeetingRooms(Array(Array(9, 10), Array(4, 9), Array(4, 17))))
    println(minMeetingRooms(Array(Array(7, 10), Array(2, 8))))
  }
}

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

  def minMeetingRooms2(intervals: Array[Array[Int]]): Int = {
    var endmeetings = 0;
    var startMeetings = 0;

    var starts = new Array[Int](intervals.length)
    var ends = new Array[Int](intervals.length)

    for (i <- 0 until intervals.length) {
      starts(i) = intervals(i)(0)
      ends(i) = intervals(i)(1)
    }

    scala.util.Sorting.quickSort(starts)
    scala.util.Sorting.quickSort(ends)
    var endidx = 0
    println(starts.foldLeft("")(_ + _ + " "))
    println(ends.foldLeft("")(_ + _ + " "))
    for (i <- 0 until intervals.length) {

      var start = starts(i)
      var end = ends(endidx)


      if (end > start) {
        startMeetings += 1
      } else {
        endidx += 1
      }

    }

    startMeetings
  }

  def main(args: Array[String]): Unit = {
    //    println(minMeetingRooms(Array(Array(0, 1), Array(0, 1), Array(2, 3), Array(2, 3), Array(2, 3))))
    println(minMeetingRooms2(Array(Array(0, 1), Array(0, 1), Array(2, 3), Array(2, 3), Array(2, 3))))
    //    println(minMeetingRooms(Array(Array(7, 10), Array(2, 8))))
  }
}

package oct2020

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 30.09.2020 at 9:46.
  */
object TopKFrequent {

  def adjust[A, B, C <: mutable.Map[A, B]](m: C, k: A, DefaultValue: B)(f: B => B): Unit
  = m.update(k, f(m.getOrElse(k, DefaultValue)))

  def topKFrequent(words: Array[String], k: Int): List[String] = {
    val tSet = mutable.TreeSet.empty[(String, Int)](Ordering.fromLessThan((f, s) => if (f._2 != s._2) s._2 < f._2 else f._1 < s._1))
    val hMap = mutable.Map.empty[String, Int]
    words.foreach(adjust(hMap, _, 0)(_ + 1))
    hMap.foreach(tSet += _)
    tSet.take(k).toList.map(_._1)
  }
  def topKFrequent(nums: Array[Int], k: Int): Array[Int] = {
    val tSet = mutable.TreeSet.empty[(Int, Int)](Ordering.fromLessThan((f, s) => if (f._2 != s._2) s._2 < f._2 else f._1 < s._1))
    val hMap = mutable.Map.empty[Int, Int]
    nums.foreach(adjust(hMap, _, 0)(_ + 1))
    hMap.foreach(tSet += _)
    tSet.take(k).toArray.map(_._1)
  }

  def main(args: Array[String]): Unit = {
   println(topKFrequent(Array("i","love","leetcode","i","love","coding"),3))
  }
}

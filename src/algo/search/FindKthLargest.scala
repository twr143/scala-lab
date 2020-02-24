package algo.search

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 24.02.2020 at 13:21.
  */
object FindKthLargest {
  def findKthLargest(nums: Array[Int], k: Int): Int = {
    val q = mutable.PriorityQueue()(Ordering.fromLessThan[Int](_ > _))
    nums.foldLeft(q) { case (qq, elem) =>
      if (qq.size < k) qq += elem
      else if (qq.head < elem) {
        qq.dequeue()
        qq += elem
      } else qq
    }.head
  }

  def main(args: Array[String]): Unit = {
    println(findKthLargest(Array(3,2,1,5,6,4),2))
    println(findKthLargest(Array(3,2,3,1,2,4,5,5,6),4))
  }
}

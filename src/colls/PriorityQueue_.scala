package colls

import scala.collection.mutable.PriorityQueue

/**
  * Created by Ilya Volynin on 20.01.2020 at 21:25.
  */
object PriorityQueue_ {
  def main(args: Array[String]): Unit = {
    implicit val ord = Ordering.fromLessThan[Int](_ < _)
    //new Ordering[Int] {
    //      def compare(o1: Int, o2: Int): Int = {
    //        if (o1 - o2 != 0) o1 - o2 else 1
    //     }
    //  }
    val pq = PriorityQueue.empty[Int]
    pq.enqueue(1)
    pq.enqueue(2)
    pq.enqueue(5)
    pq.enqueue(3)
    pq.enqueue(3)
    println(s"size= ${pq.size}")
    println(pq.head)
    println(pq.dequeue)
    println(pq.dequeueAll[Int, List[Int]])
  }
}

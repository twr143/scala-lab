package algo.mergeKlists
import algo.mergeKlists.Merge.ListNode
import scala.collection.mutable.TreeSet
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Ilya Volynin on 27.11.2019 at 15:14.
  */
object Merge {

  case class ListNode(var x: Int = 0, var next: ListNode = null)

  def appendToTheList(tail: ListNode, ln: ListNode): ListNode = {
    if (tail == null) return ln
    tail.next = ln
    ln
  }

  def print(head: ListNode): String = head.next match {
    case nn if nn != null => s"${head.x} -> " + print(nn)
    case _ => s"${head.x}"
  }

  def add(head: ListNode, ln: ListNode): Unit = {
    head.next match {
      case nn if nn != null => add(nn, ln)
      case _ => head.next = ln
    }
  }

  def mergeKLists(lists: Array[ListNode]): ListNode = {
    if (lists.isEmpty || (lists.filter(_ != null).isEmpty)) return null
    if (lists.filter(head => head != null && head.next == null).length == lists.length) {
      val ordering = new Ordering[Int] {
        def compare(o1: Int, o2: Int): Int = {
          if (o1 - o2 != 0) o1 - o2 else 1;
        }
      }
      var current = mutable.TreeSet.empty[Int](ordering)
      lists.foreach(h => current += h.x)
      var headResult: ListNode = null
      var TailResult: ListNode = null
      current.foreach(c => {
        val ln = new ListNode(c)
        if (TailResult == null) {
          headResult = ln
          TailResult = ln
        } else {
          TailResult.next = ln
          TailResult = ln
        }
      })
      return headResult
    }
    val iters = new Array[ListNode](lists.length)
    var headResult: ListNode = null
    var TailResult: ListNode = null
    for (i <- 0 to iters.length - 1)
      iters(i) = lists(i)
    val currOrdering = new Ordering[(Int, ListNode)] {
      def compare(o1: (Int, ListNode), o2: (Int, ListNode)): Int = {
        if (o1._2.x != o2._2.x) o1._2.x - o2._2.x else 1
      }
    }
    var current = mutable.TreeSet.empty[(Int, ListNode)](currOrdering)
    for (i <- 0 to iters.length - 1)
      if (iters(i) != null)
        current += ((i, iters(i)))
    headResult = current.head._2
    while (current.nonEmpty) {
      val i = current.head._1
      TailResult = appendToTheList(TailResult, current.head._2)
      current = current.tail
      if (iters(i).next != null) {
        iters(i) = iters(i).next
        current += ((i, iters(i)))
      }
    }
    if (TailResult != null) TailResult.next = null
    headResult
  }

  def main(args: Array[String]): Unit = {
    val head = ListNode(1)
    add(head, ListNode(2))
    add(head, ListNode(3))
    println(print(head))
    val head2 = ListNode(1)
    add(head2, ListNode(2))
    add(head2, ListNode(4))
    println(print(mergeKLists(Array(head, head2))))
  }
}

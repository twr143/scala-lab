package types.lists
import scala.collection.mutable

/**
  * Created by Ilya Volynin on 28.11.2019 at 18:05.
  */
trait ListCommons {

  case class ListNode[T](var x: T, var next: ListNode[T] = null)

  def appendToTheList[T](tail: ListNode[T], ln: ListNode[T]): ListNode[T] = {
    if (tail == null) return ln
    tail.next = ln
    ln
  }

  def print[T](head: ListNode[T]): String = head.next match {
    case nn if nn != null => s"${head.x} -> " + print(nn)
    case _ => s"${head.x}"
  }

  def add[T](head: ListNode[T], ln: ListNode[T]): Unit = {
    head.next match {
      case nn if nn != null => add(nn, ln)
      case _ => head.next = ln
    }
  }

  def sortCommons[T](h: ListNode[T])(implicit ord: Ordering[T]): ListNode[T] = {
    var current = mutable.TreeSet.empty[T](ord)
    var _h = h
    while (_h != null) {
      current += _h.x
      _h = _h.next
    }
    var headResult: ListNode[T] = null
    var TailResult: ListNode[T] = null
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
    headResult
  }
}

package B2021Feb

import scala.collection.mutable

/**
  * Created by twr143 on 22.02.2021 at 8:58.
  */
class CustomStack(_maxSize: Int) {

  case class ListNode(var x: Int, var next: ListNode = null, var prev: ListNode = null)
  var head: ListNode = null
  var tail: ListNode = null
  var size = 0

  def push(x: Int) {
    if (size < _maxSize) {
      if (head == null) {
        val l = ListNode(x)
        head = l
        tail = l
      }
      else {
        val l = ListNode(x, head)
        head.prev = l
        head = l
      }
      size += 1
    }
  }

  def pop(): Int = {
    if (size > 0) {
      val r = head.x
      val h = head
      head = h.next
      size -= 1
      if (h.next != null) {
        h.next.prev = null
        if (size == 0) {
          tail = null
          head = null
        }
      }
      r
    }
    else
      -1
  }

  def increment(k: Int, v: Int) {
    var i = 0
    var t = tail
    while (i < k && t != null) {
      t.x += v
      t = t.prev
      i += 1
    }

  }
}
package A2021feb

/**
  * Created by twr143 on 06.02.2021 at 20:09.
  */

import scala.collection.mutable

object ListCycle {
  class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x
  }
  def hasCycle(head: ListNode): Boolean = {
    if (head == null) return false
    var h = head
    val m = mutable.Set.empty[ListNode]
    while (h.next != null) {
      if (m.contains(h)) return true
      m += h
      h = h.next
    }
    false
  }

  def hasCycle2(head: ListNode): ListNode = {
    if (head == null) return null
    var h = head
    var h2 = head.next
    var h3 = head
    var isCyclic = false
    while (h.next != null && h2.next != null && h2.next.next != null && !isCyclic) {
      if (h == h2) isCyclic = true
      h = h.next
      h2 = h2.next.next
    }
    while (isCyclic && h != h3) {
      h = h.next
      h3 = h3.next
    }
    if (isCyclic) h else null
  }


}

package A2021feb

/**
  * Created by twr143 on 12.02.2021 at 20:59.
  */
object ListProblems {
  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }
  def removeNthFromEnd(head: ListNode, n: Int): ListNode = {
    var k = 0
    var h = head
    var h2 = head
    while (h != null) {
      h = h.next
      k = k + 1
      if (k > n + 1)
        h2 = h2.next
    }
    if (h2.next != null && h2.next.next != null && n<k) {
      val t = h2.next.next
      h2.next.next = null
      h2.next = t
    } else if (h2.next != null && h2.next.next != null && n==k){
      return h2.next
    } else if (h2.next != null && h2 != head) {
      h2.next = null
    } else if (h2.next != null && h2 == head) {
      return if (n==k) h2.next else {h2.next=null;h2} 
    } else if (h2.next == null)
      return null
    head
  }

}

package A2021June

/**
  * Created by twr143 on 07.06.2021 at 13:03.
  */
object Partition {
  class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x
  }
  def partition(head: ListNode, x: Int): ListNode = {
    if (head == null || head.next == null)
      return head
    var hSmaller:ListNode = null
    var lSmaller:ListNode = null
    var hEqGreater:ListNode = null
    var lEqGreater:ListNode = null
    var h = head
    while (h != null) {
      val hLoc = h
      h = h.next
      hLoc.next=null
      if (hLoc.x < x)
        if (hSmaller == null){
           hSmaller = hLoc
           lSmaller = hLoc
        } else { 
          lSmaller.next=hLoc
          lSmaller=hLoc
        }
      else
        if (hEqGreater == null){
          hEqGreater = hLoc
          lEqGreater = hLoc
        } else { 
          lEqGreater.next=hLoc
          lEqGreater=hLoc
        }
    }
    merge(hSmaller, lSmaller,hEqGreater)
  }


  def merge(sHead: ListNode, sLast: ListNode, gHead: ListNode): ListNode ={
    if (sHead == null) gHead else {
      sLast.next = gHead
      sHead
    }
  }

}

package sep2020

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 28.09.2020 at 10:38.
  */
object ListReverse {

  case class ListNode(var _x: Int = 0) {
    var next: Option[ListNode] = None
    var x: Int = _x

    def add(_x: ListNode): ListNode = {
      next match {
        case Some(_val) => _val.add(_x)
        case None => {
          next = Some(_x)
        }
      }
      this
    }

    def print(): String = next match {
      case Some(nn) => s"${this.x} -> " + print(nn)
      case None => s"$x"
    }

    def print(node: ListNode): String = {
      node.next match {
        case Some(nn) => s"${node.x} -> ${print(nn)}"
        case None => s"${node.x}"
      }
    }

    def reverseList(): ListNode = {
      var h = this
      if (h.next.isEmpty) return h
      var next = h.next
      h.next = None
      while (next.nonEmpty) {
        val hloc = h
        h = next.get
        next = h.next
        h.next = Some(hloc)
      }
      h
    }

    def middleNode(head: ListNode): ListNode = {
      var fast, slow = head
      while (fast.next.nonEmpty) {
        fast = fast.next.get
        slow = slow.next.get
        if (fast.next.nonEmpty)
          fast = fast.next.get
      }
      slow
    }

    def swapPairs(head: ListNode): ListNode = {
      var front, back = head
      var h, last: ListNode = null
      while (back.next.nonEmpty) {
        front = front.next.get
        back.next = front.next
        front.next = Some(back)
        if (last != null)
          last.next = Some(front)
        if (h == null) h = front
        if (back.next.nonEmpty) {
          last = back
          back = back.next.get
          front = back
        }
      }
      h
    }

    def nextLargerNodes(head: ListNode): Array[Int] = {
      if (head == null) return Array.emptyIntArray
      else if (head.next == null) return Array(0)
      var cnt = 1
      var h = head
      while (h.next.nonEmpty) {
        h = h.next.get
        cnt += 1
      }
      val nextLarger = Array.ofDim[Int](cnt)
      nextLarger(nextLarger.length - 1) = 0
      h = head
      val stacked = mutable.Stack[Int]()
      var i = 0
      while (h.next.nonEmpty) {
        if (h.next.get.x > h.x) {
          nextLarger(i) = h.next.get.x
          while (stacked.nonEmpty && nextLarger(stacked.top) < h.next.get.x)
            nextLarger(stacked.pop) = h.next.get.x
        } else {
          nextLarger(i) = h.x
          stacked.push(i)
        }
        i += 1
        h = h.next.get
      }
      while (stacked.nonEmpty)
        nextLarger(stacked.pop) = 0
      nextLarger
    }

  }

  def main(args: Array[String]): Unit = {
    val l = ListNode(2).add(ListNode(7)).add(ListNode(4)).add(ListNode(3)).add(ListNode(5)).add(ListNode(5))
    println(l.print())
    println(l.nextLargerNodes(l).foldLeft("")((s,i)=>s+i+" "))
//    println(l.swapPairs(l).print())
    //    println(l.reverseList().print())
    //    val l2 = ListNode(0)
    //    println(l2.print())
    //    println(l2.reverseList().print())
  }

}

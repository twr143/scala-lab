package colls
import coContraVar.TypeBounds.ListNode

/**
  * Created by Ilya Volynin on 16.10.2019 at 16:46.
  */
object ListReverse {

  class ListNode(var _x: Int = 0) {

    var next: Option[ListNode] = None

    var x: Int = _x

    def add(_x: ListNode): Unit = {
      next match {
        case Some(_val) => _val.add(_x)
        case None => {
          next = Some(_x)
        }
      }
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
      var hnext = h.next
      next = None
      while (hnext.isDefined) {
        val loch = h
        h = hnext.get
        hnext = h.next
        h.next = Some(loch)
      }
      h
    }

    def reverseBetween(start: Int, end: Int): ListNode = {
      var head = this
      var prevHead = this
      var s = start
      while (s > 1 && head.next.isDefined) {
        prevHead = head
        head = head.next.get
        s -= 1
      }
      var h = head
      var hnext = h.next
      head.next = None
      var e = end - start + 1
      while (hnext.isDefined && e > 1) {
        val loch = h
        h = hnext.get
        hnext = h.next
        h.next = Some(loch)
        e -= 1
      }
      if (start > 1) {
        head.next = hnext
        prevHead.next = Some(h)
        this
      } else {
        prevHead.next = hnext
//        next = h.next
//        x = h.x
        h
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val l = new ListNode(10)
    l.add(new ListNode(1))
    l.add(new ListNode(2))
    l.add(new ListNode(3))
    l.add(new ListNode(4))
    println(l.print())
    //    println(l.reverseBetween(2, 3).print())
    //    println(l.reverseBetween(2, 4).print())
    println(l.reverseBetween(1, 3).print())
    println(l.reverseBetween(1, 3).print())
    println(l.reverseBetween(1, 3).print())
    //        println(l.reverseBetween(2, 5).print())
    //    println(l.reverseBetween(3, 5).reverseBetween(3, 5).print())
    //    val o = Some(1)
    //    println(o.fold(0)(_ => 1))
  }
}

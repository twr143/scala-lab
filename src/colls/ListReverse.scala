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
      this.next = None
      while (hnext.isDefined) {
        val loch = h
        h = hnext.get
        hnext = h.next
        h.next = Some(loch)
      }
      h
    }
  }

  def main(args: Array[String]): Unit = {
    val l = new ListNode(10)
    l.add(new ListNode(1))
    l.add(new ListNode(2))
    l.add(new ListNode(3))
    println(l.print())
    println(l.reverseList().print())
  }
}

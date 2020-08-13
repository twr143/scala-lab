package algo.lists

/**
  * Created by Ilya Volynin on 04.01.2020 at 15:52.
  */
object InverseLList {

  case class Node[T](v: T, var next: Option[Node[T]] = None)

  def printList[T](head: Node[T]): String = head.next match {
    case Some(node) => s"${head.v}->${printList(head.next.get)}"
    case None => s"${head.v}"
  }

  def prependToList[T](head: Node[T], elem: Node[T]): Node[T] = {
    val h = head;
    elem.next = Some(h)
    elem
  }

  def addToList[T](head: Node[T], elem: Node[T]): Unit = head.next match {
    case Some(_) => addToList(head.next.get, elem)
    case None => head.next = Some(elem); elem.next = None
  }

  def reverseList[T](head: Node[T]): Node[T] = {
    var h = head
    if (head.next.isEmpty) return head
    var hnext = h.next
    h.next = None
    while (hnext.isDefined) {
      val locH = h
      h = hnext.get
      hnext = hnext.get.next
      h.next = Some(locH)
    }
    h
  }

  def main(args: Array[String]): Unit = {
    val e1 = Node(1)
    val e2 = Node(2)
    val e3 = Node(3)
    addToList(e1, e2)
    addToList(e1, e3)
    println(printList(e1))
    println(printList(reverseList(e1)))
    val e4 = Node(4)
    val e5 = Node(5)
    addToList(e4, e5)
    println(printList(e4))
    println(printList(reverseList(e4)))
  }
}

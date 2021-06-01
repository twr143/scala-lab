package A2021May

/**
  * Created by twr143 on 29.05.2021 at 12:32.
  */
object ListEntry {
  case class ListNode[A](x: A, var next: Option[ListNode[A]] = None)

  def prepend[A](head: ListNode[A], node: ListNode[A]): ListNode[A] = {
    head match {
      case null => node
      case h => {
        node.next = Some(h)
        node
      }
    }
  }

  def printList[A](head: ListNode[A]): String = {
    head.next match {
      case Some(node) => s"${head.x} -> ${printList(node)}"
      case None => s"${head.x}"
    }
  }
  def reverse[A](head:ListNode[A]):ListNode[A] = {
 		if (head.next.isEmpty) return head
 		var h = head
 		var hn = head.next
 		while(hn.nonEmpty){
 		  val temp = hn.get.next
 		  hn.get.next = Some(h)
 		  h = hn.get
 		  hn = temp
 		}
 		head.next = None
 		h
 	}
  
  def main(args: Array[String]): Unit = {
    val n1 = prepend(null, ListNode(1))
    val n2 = prepend(n1, ListNode(2))
    val n3 = prepend(n2, ListNode(3))
    val l = prepend(n3, ListNode(4))
    println(printList(l))
    println(printList(reverse(l)))
  }
}

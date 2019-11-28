package types.lists
import scala.collection.mutable

/**
  * Created by Ilya Volynin on 28.11.2019 at 17:50.
  */
object ListOrdering extends ListCommons {

  trait Sortable[T] {

    def sort(h: ListNode[T]): ListNode[T]
  }

  def sort[T](head: ListNode[T])(implicit ev: Sortable[T]): ListNode[T] = ev.sort(head)

  implicit val intSortable: Sortable[Int] = new Sortable[Int] {
    override def sort(h: ListNode[Int]): ListNode[Int] = {
      implicit val ordering = new Ordering[Int] {
        def compare(o1: Int, o2: Int): Int = {
          if (o1 - o2 != 0) o1 - o2 else 1;
        }
      }
      sortCommons(h)
    }
  }
  implicit val strSortable: Sortable[String] = new Sortable[String] {
    override def sort(h: ListNode[String]): ListNode[String] = {
      implicit val ordering = new Ordering[String] {
        def compare(o1: String, o2: String): Int = {
          o1.compareTo(o2)
        }
      }
      sortCommons(h)
    }
  }


  def main(args: Array[String]): Unit = {
    val head = ListNode(3)
    add(head, ListNode(2))
    add(head, ListNode(1))

    println(print(sort(head)))
    val head2 = ListNode("cc")
    add(head2, ListNode("bb"))
    add(head2, ListNode("aa"))
    println(print(sort(head2)))


  }
}

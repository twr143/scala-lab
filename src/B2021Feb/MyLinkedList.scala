package B2021Feb

/**
  * Created by twr143 on 26.02.2021 at 18:02.
  */
object MyLinkedList {

  case class ListNode(var x: Int, var next: ListNode = null, var prev: ListNode = null)
  var head: ListNode = null
  var tail: ListNode = null
  var size = 0


  /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
  def get(index: Int): Int = {
    println(s"size = $size")
    if (index == 0) head.x
    else if (index == size - 1) tail.x
    else if (index > 0 && index <= size / 2) {
      var h = head
      for (i <- 0 to index - 1) h = h.next
      h.x
    }
    else if (index > size / 2 && index < size - 1) {
      var t = tail
      for (i <- size - 2 to index by -1) t = t.prev
      t.x
    } else
      -1
  }

  /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
  def addAtHead(x: Int) {
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

  /** Append a node of value val to the last element of the linked list. */
  def addAtTail(x: Int) {
    if (head == null) {
      val l = ListNode(x)
      head = l
      tail = l
    }
    else {
      val l = ListNode(x, null, tail)
      tail.next = l
      tail = l
    }
    size += 1
  }

  /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
  def addAtIndex(index: Int, x: Int) {
    if (index == 0) addAtHead(x)
    else if (index == size) addAtTail(x)
    else if (index > 0 && index < size) {
      var h = head
      for (i <- 0 to index - 2) h = h.next
      val l = ListNode(x, h.next, h)
      h.next.prev = l
      h.next = l
      size += 1
    }
  }

  /** Delete the index-th node in the linked list, if the index is valid. */
  def deleteAtIndex(index: Int) {
    if (index == 0 && size > 0) {
      if (size > 1) {
        head.next.prev = null
        head = head.next
      } else {
        head = null
        tail = null
      }
      size -= 1
    } else if (index > 0 && index == size - 1) {
      if (size > 1) {
        tail.prev.next = null
        tail = tail.prev
      } else {
        head = null
        tail = null
      }
      size -= 1
    } else if (index > 0 && index < size - 1) {
      var h = head
      for (i <- 0 to index - 1) h = h.next
      h.prev.next = h.next
      h.next.prev = h.prev
      h = null
      size -= 1
    }
  }

  def main(args: Array[String]): Unit = {
    addAtHead(1)
    addAtTail(3)
    addAtIndex(1, 2)
    val r = get(1)
    println(s"r=$r")

  }

}
  

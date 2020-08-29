package aug2020

import scala.collection.mutable

/**
  * Created by Ilya Volynin on 15.08.2020 at 14:09.
  */
object DeepCopy {

  class Node(var _value: Int) {
    var value: Int = _value
    var next: Node = null
    var random: Node = null
  }

  def copyRandomList(head: Node): Node = {
    if (head == null) return null
    var sourceNodeIndexMap = mutable.Map.empty[Node, Int]
    var indexesMap = mutable.Map.empty[Int, Option[Int]]
    var destIndexNodeMap = mutable.Map.empty[Int, Node]
    var h = head
    var newHead: Node = null
    var newTail: Node = null
    var index = 0
    while (h != null) {
      sourceNodeIndexMap += h -> index
      h = h.next
      index += 1
    }
    h = head
    index = 0
    while (h != null) {
      if (h.random != null) indexesMap += index -> Some(sourceNodeIndexMap(h.random))
      else indexesMap += index -> None
      val newNode = new Node(h.value)
      if (newHead == null) {
        newHead = newNode
        newTail = newNode
      } else {
        newTail.next = newNode
        newTail = newNode
      }
      destIndexNodeMap += index -> newNode
      h = h.next
      index += 1
    }

    h = newHead
    index = 0
    while (h != null) {
      h.random = if (indexesMap(index).nonEmpty) destIndexNodeMap(indexesMap(index).get) else null
      h = h.next
      index += 1
    }
    newHead
  }

  def addToList(head: Node, elem: Node): Unit = {
    if (head.next != null) addToList(head.next, elem)
    else
      head.next = elem
  }

  def printList(head: Node): Unit = {
    var h = head
    while (h != null) {
      if (h.random != null) print("[" + h.value + "," + h.random.value + "] ") else print("[" + h.value + "," + "null] ")
      h = h.next
    }
    print("\n")
  }


  def main(args: Array[String]): Unit = {
    val a = new Node(1)
    val b = new Node(2)
    val c = new Node(3)
    a.random = b
    b.random = c
    c.random = a
    addToList(a, b)
    addToList(a, c)
    printList(a)
    val cp = copyRandomList(a)
    printList(cp)

    val d = new Node(1)
    d.random = d
    printList(d)
    printList(copyRandomList(d))


  }
}

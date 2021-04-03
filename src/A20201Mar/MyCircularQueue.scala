package A20201Mar

/**
  * Created by twr143 on 01.03.2021 at 14:31.
  */
class MyCircularQueue(_k: Int) {

  case class Node(x: Int, var next: Node = null, var prev: Node = null)
  var head: Node = null
  var tail: Node = null
  var size = 0

  def enQueue(value: Int): Boolean = {
    if (size < _k) {
      if (size == 0) {
        head = Node(value)
        tail = head
      } else {
        val n = Node(value, null, tail)
        tail.next = n
        tail = n
      }
      size += 1
      true
    } else
      false
  }

  def deQueue(): Boolean = {
    if (size > 0) {
      if (size > 1) {
        head.next.prev = null
        var h = head
        head = h.next
        h.next = null
      } else {
        head = null
        tail = null
      }
      size -= 1
      true
    } else false

  }

  def Front(): Int = {
    if (size > 0) head.x else -1
  }

  def Rear(): Int = {
    if (size > 0) tail.x else -1
  }

  def isEmpty(): Boolean = {
    size == 0
  }
  def isFull(): Boolean = {
    size == _k
  }
  
}
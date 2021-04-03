package A20201Mar

/**
  * Created by twr143 on 01.03.2021 at 13:46.
  */
class MyCircularDeque(_k: Int) {

  case class Node(x: Int, var next: Node = null, var prev: Node = null)
  var head: Node = null
  var tail: Node = null
  var size = 0

  /** Adds an item at the front of Deque. Return true if the operation is successful. */
  def insertFront(value: Int): Boolean = {
    if (size < _k) {
      if (size == 0) {
        head = Node(value)
        tail = head
      } else {
        val n = Node(value, head)
        head.prev = n
        head = n
      }
      size += 1
      true
    } else
      false
  }

  /** Adds an item at the rear of Deque. Return true if the operation is successful. */
  def insertLast(value: Int): Boolean = {
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

  /** Deletes an item from the front of Deque. Return true if the operation is successful. */
  def deleteFront(): Boolean = {
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

  /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
  def deleteLast(): Boolean = {
    if (size > 0) {
      if (size > 1) {
        tail.prev.next = null
        var t = tail
        tail = t.prev
        t.prev = null
      } else {
        head = null
        tail = null
      }
      size -= 1
      true
    } else false

  }

  /** Get the front item from the deque. */
  def getFront(): Int = {
    if (size > 0) head.x else -1
  }

  /** Get the last item from the deque. */
  def getRear(): Int = {
    if (size < 0) tail.x else -1
  }

  /** Checks whether the circular deque is empty or not. */
  def isEmpty(): Boolean = {
    size == 0
  }

  /** Checks whether the circular deque is full or not. */
  def isFull(): Boolean = {
    size == _k
  }

}
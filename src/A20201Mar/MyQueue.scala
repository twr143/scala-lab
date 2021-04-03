package A20201Mar

/**
  * Created by twr143 on 12.03.2021 at 10:26.
  */
import scala.collection.mutable._
object MyQueue {
  /** Initialize your data structure here. */
  val s1 = Stack[Int]()
  val s2 = Stack[Int]()

  /** Push element x to the back of queue. */
  def push(x: Int) {
    while(s2.nonEmpty) s1.push(s2.pop())
    s1.push(x)
  }

  /** Removes the element from in front of queue and returns that element. */
  def pop(): Int = {
    while(s1.nonEmpty) s2.push(s1.pop())
    s2.pop()
  }

  /** Get the front element. */
  def peek(): Int = {
    while(s1.nonEmpty) s2.push(s1.pop())
    s2.top
  }

  def empty(): Boolean = {
    s1.isEmpty && s2.isEmpty
  }
}

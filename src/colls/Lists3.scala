package colls

import scala.collection.mutable._

/*
created by Ilya Volynin at 13.02.18
*/
object Lists3 extends App {
  val l1 = LinkedHashSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val l2 = LinkedHashSet(4, 5, 6)
  val l3 = List[A](new B(), new B())
  val l4 = List[B](new B(), new B())
  println("set sum:" + (l1 ++: l2))
  println("another version of set sum:" + (l1 | l2))
  println("intersection of sets:" + (l1 & l2))
  println("grouped\n" + l1.grouped(2).toSet)
  println("sliding\n" + l1.sliding(3, 2).toList)
  printlst(l3)
  printlst(l4)

  def printlst[T >: B](lst: List[T]): Unit = {
  }
}
class A
class B extends A

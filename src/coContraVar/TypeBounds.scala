package coContraVar
/**
  * Created by Ilya Volynin on 12.10.2018 at 16:52.
  */
object TypeBounds extends App {

  trait Node[+B] {

    def prepend[U >: B](elem: U): Node[U]
  }

  case class ListNode[+B](h: B, t: Node[B]) extends Node[B] {

    def prepend[U >: B](elem: U): ListNode[U] = ListNode[U](elem, this)

    def head: B = h

    def tail: Node[B] = t
  }

  case class Nil[+B]() extends Node[B] {

    def prepend[U >: B](elem: U): ListNode[U] = ListNode(elem, this)
  }

  trait Bird

  case class AfricanSwallow() extends Bird

  class EuropeanSwallow() extends Bird

  case class RSwallow() extends EuropeanSwallow

  val africanSwallowList = ListNode[AfricanSwallow](AfricanSwallow(), Nil())

  val birdList: Node[Bird] = africanSwallowList

  //тип в prepend надо указывать, иначе он Any
  val birdList2: Node[Bird] = birdList.prepend[EuropeanSwallow](RSwallow())

  //  val birdList3 = birdList2.prepend("1")
  println(birdList2)
}

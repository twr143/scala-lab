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

  val rSwallowList = ListNode[RSwallow](RSwallow(), Nil())

  val birdList: Node[RSwallow] = rSwallowList

  //тип в prepend надо указывать, иначе он Any
  val birdList2 = birdList.prepend[EuropeanSwallow](new EuropeanSwallow())

  //      val birdList3 = birdList2.prepend[RSwallow](RSwallow()) // error
  val birdList3 = birdList2.prepend[Bird](AfricanSwallow()) //ok
  println(birdList3)
}

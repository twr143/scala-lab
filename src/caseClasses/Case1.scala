package caseClasses

/**
  * Created by Ilya Volynin on 04.01.2020 at 17:42.
  */
object Case1 {

  class A(s: String)

  case class B(s: String)

  def main(args: Array[String]): Unit = {
    val a = new A("a")
    val a1 = new A("a")
    val b = new B("b")
    val b1 = new B("b")
    println(s"$a ${a.toString()}")
    println(s"$b ${b.copy()}")
    println(s"a1==a? ${a == a1}")
    println(s"b1==b? ${b == b1}")
    println(s"a1 equals a? ${a .equals( a1)}")
  }
}

package colls
/**
  * Created by Ilya Volynin on 12.10.2018 at 12:54.
  */
object Colls5 extends App {



  val l1 = List(1)
  val l2 = 2::l1
  val l3 = l2:+3
  println(l3)
  val lst = List (3,5,7,9)
  lst.zipWithIndex.map{case (v,i) => v*i}
}

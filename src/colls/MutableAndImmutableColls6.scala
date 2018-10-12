package colls

import scala.collection.mutable.ListBuffer

/**
  * Created by Ilya Volynin on 12.10.2018 at 15:47.
  */
object MutableAndImmutableColls6 extends App {
  var m = Map[Int, ListBuffer[String]]()

  m += (1 -> ListBuffer.empty)

  m += (2 -> ListBuffer.empty)

  m += (3 -> ListBuffer.empty)

  println(s"m=$m")

  m = m.transform { (k,v) => v += "!123" }//maoValues doesn't work

  //m(1) = m(1) += "1"
  println(s"immut m after=$m")

  println(s"immut m(2)=${m.get(2)}")

  val m2 = scala.collection.mutable.Map[Int, ListBuffer[String]]()

  m2 += (1 -> ListBuffer.empty)

  m2 += (2 -> ListBuffer.empty)

  m2 += (3 -> ListBuffer.empty)

  println(s"m2=$m2")

  m2.transform { (k,v) => v += "!456" }//maoValues doesn't work

  //m(1) = m(1) += "1"
  println(s"mutable m after=$m2")

  println(s"mutable m(2)=${m2.get(2)}")


}

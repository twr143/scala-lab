package colls
/**
  * Created by Ilya Volynin on 18.06.2018 at 13:58.
  */
object Colls4 extends App {
  var l1 = List(1)
  l1 :+= 2
  l1 :+= 3
  var l2 = l1.tail
  var l3 = l1.dropRight(1)

  println(s"$l1")
  println(s"$l2")
  println(s"$l3")
}

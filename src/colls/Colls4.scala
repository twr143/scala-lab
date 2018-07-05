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
  var l4 = l1.drop(2)
  var l5 = l1.take(2)
  println(s"$l1")
  println(s"$l2")
  println(s"$l3")
  println(s"h:${l1.head} t:${l1.tail} l:${l1.last}")
  println(s"l4:$l4")
  println(s"l5:$l5")
}

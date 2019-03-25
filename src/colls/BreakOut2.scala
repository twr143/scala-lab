package colls
import scala.collection.breakOut
import scala.collection.immutable.TreeMap

/**
  * Created by Ilya Volynin on 25.03.2019 at 15:21.
  */
object BreakOut2 {

  def adjust[A, B, C <: Map[A, B]](m: C, k: A, DefaultValue: B)(f: B => B): C
  = m.updated(k, f(m.getOrElse(k, DefaultValue))).asInstanceOf[C]

  def main(args: Array[String]): Unit = {
    implicit object orderingForTreemap extends Ordering[String] {

      def compare(o1: String, o2: String): Int = o2.compareTo(o1)
    }
    val list = List("London", "Paris", "London")
    println(list)
    val map = list.foldLeft(TreeMap.empty[String, Int])((current, elem) =>
      adjust(current, elem, 0)(_ + elem.length))
    map match {
      case m: TreeMap[_, Int] => println(s"acceptable type $m")
      case m: TreeMap[_, _] => println("general acceptable type")
      case _ => println("unacc type")
    }
  }
}

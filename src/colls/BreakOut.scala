package colls
import scala.collection.{breakOut, mutable}
import scala.collection.immutable.TreeMap
import scala.reflect.runtime.universe._

object BreakOut {

  def main(args: Array[String]): Unit = {
    implicit object orderingForTreemap extends Ordering[Int] {

      def compare(o1: Int, o2: Int): Int = o2 - o1
    }
    val map = Array("London", "Paris").map(x => (x.length, x))(breakOut[Array[String], (Int, String), TreeMap[Int, String]])
    println(map)
    map match {
      case m: TreeMap[_, String] => println("acceptable type")
      case m: TreeMap[_, _] => println("general acceptable type")
      case _ => println("unacc type")
    }
    val map2 = List("London", "Paris").map(x => (x.length, x))
    println(map2)
    case class Wrapper(x: Int, y: Int)
    implicit object orderingForTreeset extends Ordering[Wrapper] {

      def compare(o1: Wrapper, o2: Wrapper): Int = o2.y - o1.y
    }
    val set = Array(Array(1, 1), Array(2, 2), Array(3, 3))
      .map(a => Wrapper(a(0), a(1)))(breakOut[Array[Array[Int]], Wrapper, mutable.TreeSet[Wrapper]])
    println(set)
    println(set.filter(_.y==2))
    println(set.tail)
  }
}

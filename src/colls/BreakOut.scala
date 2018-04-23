package colls

import scala.collection.breakOut
import scala.collection.immutable.TreeMap
import scala.reflect.runtime.universe._

object BreakOut {
  def main(args: Array[String]): Unit = {
    implicit object orderingForTreemap extends Ordering[Int] {
      def compare(o1: Int, o2: Int): Int = o2 - o1
    }
    val map = List("London", "Paris").map(x => (x.length, x))(breakOut[List[String], (Int, String), TreeMap[Int, String]])
    println(map)
    map match {
      case m: TreeMap[_, String] => println("acceptable type")
      case m: TreeMap[_, _] => println("general acceptable type")
      case _ => println("unacc type")
    }
    val map2 = List("London", "Paris").map(x => (x.length, x))
    println(map2)
  }
}

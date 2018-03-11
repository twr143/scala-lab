package colls
import scala.collection.breakOut
object BreakOut {
  def main(args: Array[String]): Unit = {
    val map = List("London", "Paris").map(x => (x.length, x))(breakOut[List[String],(Int,String),Map[Int, String]])
    println(map)
    val map2 = List("London", "Paris").map(x => (x.length, x))
    println(map2)
  }
}

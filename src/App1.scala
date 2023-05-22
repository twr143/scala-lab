
import java.util.Locale
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success
import scala.concurrent.duration._
import java.util.regex.Pattern
import scala.collection.immutable.TreeSet
import scala.collection.mutable

object App1 {
  def f(arr: List[Int]): List[Int] = {
    arr.zipWithIndex.filter(_._2 % 2 == 0).map(_._1)
  }

  def sS(a: Integer) = String.format("%4s", new Integer(a / 1024))

  def printNum(s: String) = println(s)

  def main(args: Array[String]): Unit = {
     val ts = TreeSet(sS(1000*1024),sS(11*1024),sS(9*1024))
     println(ts)
    //Await.result(sumF2, 5.seconds)
    //    val p = Pattern.compile("^user\\d{1,2}$")
    //    println(p.matcher("user12e").matches())
  }
}

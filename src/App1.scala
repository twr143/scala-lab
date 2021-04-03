
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success
import scala.concurrent.duration._
import java.util.regex.Pattern

object App1 {
  def f(arr:List[Int]):List[Int] = {
     arr.zipWithIndex.filter(_._2%2==0).map(_._1)
  }
  
  def main(args: Array[String]): Unit = {
    /*val sumF = Future {
      (1L to 10L).sum
    }

    val fivetimes= for {
     double   <- sumF.flatMap{s => Future { s*2} }
     triple  <- Future(double).flatMap{s => Future { s*3} }
    }yield triple

    fivetimes onComplete     {
      case Success(s) => println(s"5 times= $s")
      case any => println(s"not expected"+any.toString)
    }
      */

//    Await.result(fivetimes, 5.seconds)
   // println(s"5 times= $result")

    /*val sumF2 = Future {
      (1L to 1000L).sum
    }.map{
      s=>
        println(s"1 to 1000 = $s")
        s+500
    }.map{
      s=>
        println(s"increased s by 500 = $s")
        throw new Exception("except happened")
    }.recover{ case e:Exception =>
        println(s" exception:$e.")

    } */
    //Await.result(sumF2, 5.seconds)
        val p = Pattern.compile("^user\\d{1,2}$")
    println(p.matcher("user12e").matches())
  }
}

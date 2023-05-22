package A2021Nov

import java.util.concurrent.TimeUnit
import scala.collection.mutable
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object FutureMap {

  def main(args: Array[String]): Unit = {
    val m = mutable.Map.empty[String, String]
    for (i <- 0 to 2) {
      Await.result(Future {
        m += (s"$i" -> "1")
      }, Duration(10, TimeUnit.SECONDS))
    }
    println(s"m=$m")
  }
}

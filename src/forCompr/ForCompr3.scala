package forCompr

import scala.concurrent.{Await, Future}
import scala.util.control.NoStackTrace
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._


/*
created by Ilya Volynin at 19.04.18
*/
object ForCompr3 {
  def main(args: Array[String]): Unit = {
    val result = for {
      i <- foo(2)
    } yield i
    Await.ready(result, 1.seconds)

    println(result)
  }

  def foo(i: Int): Future[Int] = {
    if (i == 1) Future(1) else Future.failed(WrongI)
  }
  case object WrongI extends Exception with NoStackTrace
}

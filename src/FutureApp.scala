
import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.util.control.NonFatal
import scala.concurrent.duration._

/*
created by Ilya Volynin at 09.10.17
*/
object FutureApp {
  def main(args: Array[String]): Unit = {
    val f1 = Future {
      1
    }
    val f2 = Future {
      2
    }
    val fExc = Future.failed(new Exception("my new exc"))
    val f4 = Future {
      4
    }
    val attempts = Future.sequence(Seq(f1, f2, fExc, f4))
    val att2 = attempts.map(_.map {
      case i: Int => i + 1
    }
    ).recover {
      case NonFatal(e) ⇒ 0
    }
    att2.onComplete {
      case Success(seq) => println(s"success $seq")
      case Failure(seq) => println(s"failure $seq")
    }
    Await.ready(att2, 1.seconds)
    //    Thread.sleep(1000)
    //    println(att2)
  }
}

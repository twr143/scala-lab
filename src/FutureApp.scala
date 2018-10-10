
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
    /*   val f1 = Future {
         1
       }
       val f2 = Future {
         3
       }.flatMap {
         case 2 => Future("2, as in should be")
         case _ => Future.failed(new Exception("abnormal termination"))
       }.recover{
         case NonFatal(e) ⇒ println(e)
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
                   */
    val r = (for {
      _ <- Future(1)
      _ <- Future {
        2
      }.flatMap {
        case 2 => Future("2, as in should be")
        case _ => Future.failed(new Exception("abnormal termination"))
      }
      res <- Future(4)
    } yield res).map(x => println(s"mapping: $x")).recover {
      case NonFatal(e) ⇒ println(e)
    }
    Await.ready(r, 1.seconds)
    println(1,2,3,4)
   // println(r)
  }
}

package A2021Nov

import scala.collection.generic.CanBuildFrom
import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.DurationInt

object Futures{
  def seq[A, M[X] <: TraversableOnce[X]](in: M[() => Future[A]])(implicit cbf: CanBuildFrom[M[()=>Future[A]], A, M[A]]): Future[M[A]] =
      in.foldLeft(Future.successful(cbf(in))) {
         (fr, ffa) => for (r <- fr; a <- ffa()) yield r += a
      } map (_.result())

  def main(args: Array[String]): Unit = {
    val s = Seq(1,2,3)
    Await.result(seq(s.map(s=>() =>Future.successful(s)
    )).map(println),1 seconds)


  }
}

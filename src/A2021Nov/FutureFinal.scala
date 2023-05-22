package A2021Nov

import A2021Nov.domain.A

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object FutureFinal {

  def main(args: Array[String]): Unit = {
    val a = new A()
    for {
      _ <- a.m(1)()
      _ <- a.m(2)()
      _ <- a.m(3)()
      _ <- a.m(4)()
      _ <- a.m(5)()
    } yield ()
  }
}

package forCompr

import scala.util.{Failure, Success, Try}

/*
created by Ilya Volynin at 01.02.18
*/
case class TrySeq[R](run : Try[Seq[R]]) {
  def map[B](f : R => B): TrySeq[B] = TrySeq(run map { _ map f })
  def flatMap[B](f : R => TrySeq[B]): TrySeq[B] = TrySeq {
    run match {
      case Success(s) => sequence(s map f map { _.run }).map { _.flatten }
      case Failure(e) => Failure(e)
    }
  }

  def sequence[R](seq : Seq[Try[R]]): Try[Seq[R]] = {
    seq match {
      case Success(h) :: tail =>
        tail.foldLeft(Try(h :: Nil)) {
          case (Success(acc), Success(elem)) => Success(elem :: acc)
          case (e : Failure[R], _) => e
          case (_, Failure(e)) => Failure(e)
        }
      case Failure(e) :: _  => Failure(e)
      case Nil => Try { Nil }
    }
  }
}

object TrySeq {
  def withTry[R](run : Seq[R]): TrySeq[R] = new TrySeq(Try { run })
  def withSeq[R](run : Try[R]): TrySeq[R] = new TrySeq(run map (_ :: Nil))

  implicit def toTrySeqT[R](run : Try[Seq[R]]) = TrySeq(run)
  implicit def fromTrySeqT[R](trySeqT : TrySeq[R]) = trySeqT.run
}

object Entry extends App{
  def foo : Try[Seq[String]] = Try { List("hello", "world") }
  def bar(s : String) : Try[String] = Try { s + "! " }

  val x = for {
    item1  <- TrySeq { foo }
    item2  <- TrySeq { foo }
    result <- TrySeq.withSeq { bar(item2) }
  } yield item1 + result

  println(x.run)
}
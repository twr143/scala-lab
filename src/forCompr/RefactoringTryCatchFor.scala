package forCompr

import scala.util.control.NonFatal

/*
created by Ilya Volynin at 02.02.18
*/
object RefactoringTryCatchFor extends App {
  /////////////////////////////////////////
  val lst = for {
    x <- List(10, 20, 30)
    y <- List(0, 1, 2)
    zOpt = try {
      Some(x / y)
    } catch {
      case NonFatal(e) => None
    } if zOpt.isDefined
  } yield {
    zOpt.get
  }
  println(lst)
}
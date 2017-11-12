/**
  * Created by ilya on 11.11.2017.
  */
object Fold {
  def main(args: Array[String]): Unit = {
    val incoming = if (args.length > 0) Some(args(0).toString.toInt) else None
    goodOrBad(incoming).fold(
      b => println(s" bad: ${b.code}"),
      g => println(s" good: ${g.code}")
    )
  }

  def goodOrBad(param: Option[Int]): Either[Bad1, Good1] = {
    if (param.exists(_ > 0)) Right(Good1(param.get)) else Left(Bad1(param.getOrElse(0)))
  }
}
sealed trait Result {
  def code: Int
}
sealed case class Good(code: Int) extends Result
sealed case class Bad(code: Int) extends Result


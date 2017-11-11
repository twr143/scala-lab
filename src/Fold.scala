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

  def goodOrBad(param: Option[Int]): Either[Bad, Good] = {
    if (param.exists(_ > 0)) Right(Good(param.get)) else Left(Bad(param.getOrElse(0)))
  }
}
sealed trait Result {
  def code: Int
}
case class Good(code: Int) extends Result
case class Bad(code: Int) extends Result


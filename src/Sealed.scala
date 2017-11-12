/**
  * Created by ilya on 12.11.2017.
  */
object Sealed {
  def main(args: Array[String]): Unit = {
    val testResult:Result1 = Good1(5)
    testResult match {
      case Bad1(code) =>  println(s" bad: $code")
      case Neutral(code) =>  println(s" neutral: $code")
    }
  }
}
sealed trait Result1 {
  def code: Int
}
case class Good1(code: Int) extends Result1
case class Bad1(code: Int) extends Result1
case class Neutral(code: Int = 0) extends Result1


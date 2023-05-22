package A2021Nov

object OptionSum {

  def main(args: Array[String]): Unit = {

    val one: Option[String] = Some("one")
    val two: Option[String] = Some("two")
    val three: Option[String] = Some("three")

    val res = (one ++ two ++ three).reduceOption(_ + " " + _)
    println(s"res = $res")
  }
}

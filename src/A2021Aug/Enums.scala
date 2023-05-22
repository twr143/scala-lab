package A2021Aug

trait CustomEnum[T] {

  val values: Seq[T]

  def apply(value: String): T = valueOf(value)

  def valueOf(value: String): T = values.find(_.toString == value).getOrElse(throw new IllegalArgumentException(s"Invalid value: '$value'. Expected one of: ${values.mkString(", ")}"))

}
sealed trait Company{
  override def toString: String = getClass.getSimpleName.toLowerCase().replace("$","")
}

object Company extends CustomEnum[Company] {

  val values = Seq(APPLE, GOOGLE)

  case object APPLE extends Company 

  case object GOOGLE extends Company 
}

object Enums {
  def main(args: Array[String]): Unit = {
    val c:Company = Company.APPLE
    println(s"$c")
  }
}

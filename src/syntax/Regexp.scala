package syntax

/**
  * Created by twr143 on 13.06.2021 at 12:52.
  */
object Regexp extends App{
  val s = "-2 12"
  val r = s.split("\\W-")
      println(r.mkString(" "))
}

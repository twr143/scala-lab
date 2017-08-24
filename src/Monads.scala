import scala.util.Try

object Monads {


  def main(args: Array[String]): Unit = {
    println(f(Some(3)))
    println(f(None))
  }
  def f(a:Option[Int]):Option[Int] ={
    a.map{
      case 3⇒5
      case other⇒ 6
    }
  }
}

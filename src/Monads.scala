import scala.util.Try

object Monads {


  def main(args: Array[String]): Unit = {
//    println(f(Some(3)))
//    println(f(None))
      forCompr
  }

  def forCompr:Option[Int]={
      val result= for    {
          a   ← Some(1L)
          b   ← Some(2L)
          c   ← if (b==0) Some(0) else None
          d   ← Some(4+c)
      }   yield d
      println(result)
      result
  }
  def f(a:Option[Int]):Option[Int] ={
    a.map{
      case 3⇒5
      case other⇒ 6
    }
  }
}

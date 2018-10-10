package syntax
/**
  * Created by Ilya Volynin on 10.10.2018 at 13:32.
  */
object ChainingFunctions {

  def main(args: Array[String]): Unit = {

    val fgh = (f _).andThen(g).andThen(h)
    println(applicator("abc",fgh))
  }

  def f(s: String): String = s.toUpperCase

  def g(s: String): String = s.substring(1)

  def h(s: String): String = s.substring(1)


  def applicator(incoming: String, z: String => String): String = z(incoming)
}

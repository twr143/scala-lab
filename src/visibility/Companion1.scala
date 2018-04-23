package visibility


/*
created by Ilya Volynin at 13.04.18
*/
class Companion1(param1: Integer) {
  def foo = 8
}
object Companion1 extends Companion1(0) {//not exactly what I need
  def main(args: Array[String]) = {
    Console.println(foo)
  }
}
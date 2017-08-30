import scala.reflect.ClassTag

object Types {
  class Pair[T : Ordering](val first: T, val second: T) {
    def smaller(implicit ord: Ordering[T]):T =
      if (ord.compare(first, second) < 0) first else second
  }
  def makePair[T : ClassTag](first: T, second: T): Array[T] = {
    val r = new Array[T](2); r(0) = first; r(1) = second; r
  }
  def swapInPair[T :Ordering: ClassTag](toSwap:Pair[T]):Pair[T]={
    new Pair(toSwap.second,toSwap.first)
  }
  class Pair2[+T](val first: T, val second: T){

  }
  def check[A](error: String, value: A)(f: A => Boolean)(g: A => Boolean) = if (f(value)&&g(value)) Seq.empty[String] else Seq(error)
  def main(args: Array[String]): Unit = {
    println(makePair(1,2))
  }
}

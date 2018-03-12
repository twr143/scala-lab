package syntax

import scala.reflect.ClassTag

/*
created by Ilya Volynin at 12.03.18
*/
object ContextBounds {
  def f[A: Ordering : ClassTag](a: A, b: A) = if (implicitly[Ordering[A]].lt(a, b)) a else b

  def main(args: Array[String]): Unit = {
  }
}

import scala.reflect.ClassTag

object Types {
  class Pair[T: Ordering](val first: T, val second: T) {
    def smaller(implicit ord: Ordering[T]): T =
      if (ord.compare(first, second) < 0) first else second
  }
  def makePair[T: ClassTag](first: T, second: T): Array[T] = {
    val r = new Array[T](2);
    r(0) = first;
    r(1) = second;
    r
  }

  def swapInPair[T: Ordering : ClassTag](toSwap: Pair[T]): Pair[T] = {
    new Pair(toSwap.second, toSwap.first)
  }
  class Pair2[+T](val first: T, val second: T) {
  }
  def check[A](error: String, value: A)(f: A => Boolean)(g: A => Boolean) = if (f(value) && g(value)) Seq.empty[String] else Seq(error)

  def main(args: Array[String]): Unit = {
    //    println(makePair(1, 2))
    println(instantiate[Foo](classOf[Foo])(Int.box(1),"1"))
  }

  def test[T: ClassTag](init_args: Object) {
    implicitly[ClassTag[T]].runtimeClass.getConstructors.head.newInstance(init_args).asInstanceOf[T]
//    match {
//      case o: T => print(s"T:$o")
//      case _ => print(s"AnyRef_")
//    }
  }
  def instantiate[T](clazz: java.lang.Class[T])(args:AnyRef*): T = {
    val constructor = clazz.getConstructors()(0)
    constructor.newInstance(args:_*).asInstanceOf[T]
  }
  case class Foo(id:Long, name:String)
}

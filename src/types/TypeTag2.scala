package types
object TypeTag2 {
  import scala.reflect.runtime.universe._
  def main(args: Array[String]): Unit = {
    foo(List(1, 2, 3))
    foo(List(1, "2", 3))
    foo(List("1", "2", "3"))
    foo(List(new Foo(), new Foo()))
    foo(List(new Foo(), new Bar()))
    foo(List(new Bar()))
    foo[Foo](List(new Bar()))
  }

  def foo[T: TypeTag](x: List[T]): Unit = {
    if (typeOf[T] =:= typeOf[String])
      println("Hey, this list is full of strings")
    else if (typeOf[T] =:= typeOf[Foo])
      println("Hey, this is a list of Foos")
    else if (typeOf[T] <:< typeOf[Foo])
      println("This is a list of Foos or subtypes")
    else
      println("Non-stringy list")
  }
  class Foo
  class Bar extends Foo
}

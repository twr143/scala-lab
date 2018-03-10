package types
object UnionTypes {
  def main(args: Array[String]): Unit = {
    foo("a")
  }
  
  def foo[T: StringOrInt](x: T): Unit = x match {
    case s: String => println(s"str $s")
    case i: Int => println(s"int $i")
  }
}


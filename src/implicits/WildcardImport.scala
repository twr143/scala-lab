package implicits

/**
  * Created by Ilya Volynin on 26.09.2019 at 13:32.
  */
object WildcardImport {

  def sum[T : Numeric](list: List[T]): T = {
      val integral = implicitly[Numeric[T]]
      import integral._   // get the implicits in question into scope
      list.foldLeft(integral.zero)(_ + _)
  }
  def main(args: Array[String]): Unit = {
    println(sum(List(1.0,2.0)))
  }
}

package syntax
object Eta {
  def methodA(s: String) = s

  def methodB(f: () => String) = f

  def methodC(f: (String) => String) = f

  def f2() = "foo"

  def f3(a: String)() = "foo"

  def f4(a: String)(b: String) = "foo"

  def main(args: Array[String]): Unit = {
    methodA(f2)
    methodB(f2)
    val f2fun = f2 _
    val f3fun = f3("b") _
    methodB(f2fun)
    methodB(f3fun)

    val f4fun = f4("b") _
    methodC(f4fun)

  }
}

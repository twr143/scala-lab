package syntax
object Eta {

  def methodA(s: String) = s

  def methodB(f: () => String) = f

  def methodC(f: (String) => String) = f

  def methodD(f: (String, Int) => String) = f

  def f2() = "foo"

  def f3(a: String)() = "foo"

  def f4(a: String)(b: String) = "foo"

  def f5(a: String, b: Int) = "foo"

  def main(args: Array[String]): Unit = {
    methodA(f2)
    methodB(f2)
    val f2fun = f2 _
    val f3fun = f3("b") _
    methodB(f2fun)
    methodB(f3fun)
    val f4fun = f4("b") _
    methodC(f4fun)
    val f5fun = f5(_: String, _: Int)
    methodD(f5fun)
    println(methodD(f5fun)("1", 1))
  }
}

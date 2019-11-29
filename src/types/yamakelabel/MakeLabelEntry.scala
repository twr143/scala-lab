package types.yamakelabel
/**
  * Created by Ilya Volynin on 29.11.2019 at 11:59.
  */
object MakeLabelEntry {

  trait LabelMaker[T] {

    def makeLabel(t: T): String
  }

  def make[T](t: T)(implicit lm: LabelMaker[T]): String = lm.makeLabel(t)

  implicit val strLabelMaker = new LabelMaker[String] {
    def makeLabel(s: String): String = "str " + s
  }

  implicit val intLabelMaker = new LabelMaker[Int] {
    def makeLabel(s: Int): String = "int " + s
  }

  implicit def commonLabelMaker[U] = new LabelMaker[U] {
    def makeLabel(s: U): String = "common " + s
  }

  case class A(f: Int, sec: String)

  trait ABean {

    def field1: String

    def field2: String
  }

  case class Bean(field1: String,
                  field2: String
                 ) extends ABean {

    override def toString: String = "[ 1:" + field1 + ",2:" + field2 + "]"
  }

  implicit def beanLabelMaker[T <: ABean] = new LabelMaker[T] {
    override def makeLabel(t: T): String = "bean " + t
  }

  def main(args: Array[String]): Unit = {
    println(make("blah"))
    println(make(234))
    println(make(A(1, "2")))
    println(make(Bean("1", "2")))
  }
}

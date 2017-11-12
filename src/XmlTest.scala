/**
  * Created by ilya on 12.11.2017.
  */
object XmlTest {
  def main(args: Array[String]): Unit = {
    val list = <dl><dt>Java</dt><dd>Gosling</dd><dt>Scala</dt><dd>Odersky</dd></dl>
    val languages = (list \ "dt").map(_.text)
    println(languages)
  }
}

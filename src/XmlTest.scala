import scala.xml.{Elem, NodeSeq}

/**
  * Created by ilya on 12.11.2017.
  */
object XmlTest {
  class ChildSelectable(ns: NodeSeq) {
    def \* : NodeSeq = ns flatMap {
      _ match {
        case e: Elem => e.child
        case _ => NodeSeq.Empty
      }
    }
  }
  implicit def nodeSeqIsChildSelectable(xml: NodeSeq): ChildSelectable = new ChildSelectable(xml)

  def main(args: Array[String]): Unit = {
    val list = <dl>
      <dt>Java</dt> <dd>Gosling</dd> <dt>Scala</dt> <dd>Odersky</dd>
    </dl>
    val languages = (list \ "dt").map(_.text)
    println(languages)
    val emptyList = <dl><a></a></dl>
    emptyList \* match {
      case l if l.nonEmpty =>
        println(s"non-empty list: $l")
        l\* match{
          case l2 if l2.nonEmpty => println(s"non-empty child list: $l2")
          case l2 if l2.isEmpty => println(s"empty child list")
        }
      case l if l.isEmpty => println("empty list")
    }
  }
}

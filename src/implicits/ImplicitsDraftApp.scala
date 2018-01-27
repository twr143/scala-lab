package implicits
trait Document {
  def id: Int

  def contents: String
}
case class Draft(id: Int, contents: String) extends Document {
  override def toString: String = s" draft id::$id contents:: $contents"
}
case class Application(id: Int, contents: String) extends Document {
  override def toString: String = s" app id::$id contents:: $contents"
}
object DraftAppService {
  trait DraftAppLabeling[T <: Document] {
    def toLabel(doc: T): String
  }
/*  implicit object DraftLabeling extends DraftAppLabeling[Draft] {
    def toLabel(doc: Draft): String = doc.toString
  }
  implicit object AppLabeling extends DraftAppLabeling[Application] {
    def toLabel(doc: Application): String = doc.toString
  }*/
  def makeLabel[T <: Document](doc: T)/*(implicit dal: DraftAppLabeling[T])*/: String = /*dal.toLabel(doc)*/doc.toString
}
object ImplicitsDraftApp {
  def main(args: Array[String]) {
    import DraftAppService.makeLabel
    val d = Draft(1, "draft")
    val a = Application(2, "app")
    println(makeLabel(d))
    println(makeLabel(a))
  }
}

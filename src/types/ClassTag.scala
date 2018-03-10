package types
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
object ClassTag {
  def main(args: Array[String]): Unit = {
    func[String](1)
    func[Int](1)
  }
  import scala.reflect.ClassTag

  def func[T: ClassTag](o: Any): Unit = {
    o match {
      case x: T => println(s"of the type: ")
      case _ => println(s"not of the type: ")
    }
    Future(o).mapTo[T].onComplete{
      case Success(o) => println(s"successfull mapping $o")
      case Failure(t) => println(s"unsuccessfull: $t")
    }
    Unit
  }
}

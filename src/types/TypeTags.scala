package types
import scala.reflect.runtime.universe._
/**
  * Created by ilya on 18.11.2017.
  */
object TypeTags {
  def main(args: Array[String]): Unit = {
    processGenericMap(Map("aaa" -> "bbb"))
    processGenericMap(Map("ccc" -> List(Bar(), Bar())))
  }
    //1
  def processMap[T: TypeTag](json: Map[String, T]) = {
    typeOf[T] match {
      case t if t =:= typeOf[String] =>
        println("Map of strings")
      case t if t =:= typeOf[List[String]] =>
        println("Map of list of strings")
    }
  }
   //2 1 eq 2
  def processMap2[T](json: Map[String, T])(implicit tag: TypeTag[T]) = {
    typeOf[T] match {
      case t if t =:= typeOf[String] =>
        println("Map of strings")
      case t if t =:= typeOf[List[String]] =>
        println("Map of list of strings")
    }
  }
     //3
     sealed trait Foo
     case class Bar() extends Foo

  def processGenericMap[T](json: Map[String, T])(implicit tag: TypeTag[T]) {
      typeOf[T] match {
        case t if t =:= typeOf[String] =>
          println("Map of strings")
        case t if t <:< typeOf[List[Foo]] =>
          println("Map of list of foos")
      }
    }
}

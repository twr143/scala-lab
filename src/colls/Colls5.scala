package colls
/**
  * Created by Ilya Volynin on 12.10.2018 at 12:54.
  */
object Colls5 extends App {

  def insert[T](list: List[T], after_i: Int, value: T): List[T] = {
    val index = list.indexWhere(_ == after_i)
    list.take(index + 1) ++ List(value) ++ list.drop(index + 1)
  }

  def insert[T](list: List[T], after_i: Int, value: List[T]): List[T] = {
    val index = list.indexWhere(_ == after_i)
    list.take(index + 1)  ++ value ++ list.drop(index + 1)
  }

  var list = List(1, 2)

  list = insert(list, 1, List(3, 4))

  println(list)
}

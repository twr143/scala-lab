package A2021feb


import scala.collection.mutable.ArrayBuffer

/**
  * Created by twr143 on 07.02.2021 at 17:14.
  */
object NAryOrderTraversal {
  class Node(var _value: Int) {
    var value: Int = _value
    var children: List[Node] = List()
  }

  def levelOrder(root: Node): List[List[Int]] = {
    val a = new ArrayBuffer[List[Int]]()
    if (root != null)
      lOrder(root, 0, a)
      a.toList
  }

  def lOrder(root: Node, level: Int, b: ArrayBuffer[List[Int]]): Unit = {
    if (b.size < level + 1)
      b.insert(level, List(root.value))
      else
    b.update(level, b(level) ++ List(root.value))
    root.children.foreach(c => if (c != null) lOrder(c, level + 1, b))
  }

}

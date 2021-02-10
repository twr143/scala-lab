package A2021feb

/**
  * Created by twr143 on 08.02.2021 at 7:06.
  */

import scala.collection.mutable._

object PopulateNextRightPointers {
  class Node(var _value: Int) {
    var value: Int = _value
    var left: Node = null
    var right: Node = null
    var next: Node = null
  }

  def connect(root: Node): Node = {
    if (root == null) return null
    val a = ArrayBuffer[ListBuffer[Node]]()
    if (root != null)
      Order(root, 0, a)
    root
  }


  def Order(root: Node, level: Int, b: ArrayBuffer[ListBuffer[Node]]): Unit = {
      if (b.size < level + 1) b += ListBuffer.empty[Node]
      if (b(level).nonEmpty)
        b(level).last.next = root
      b.update(level, b(level).+=(root))
      if (root.left != null)
        Order(root.left, level + 1, b)
      if (root.right != null)
        Order(root.right, level + 1, b)
  }


}

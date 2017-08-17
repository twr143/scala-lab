object BinTreeEntry {
  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(left: BinaryTree, right: BinaryTree, op: (Int,Int) => Int) extends BinaryTree
  def evalTree(tree: Node):Int ={
    tree match {
      case t:Node if t.left.isInstanceOf[Leaf] && t.right.isInstanceOf[Leaf] =>
        t.op(t.left.asInstanceOf[Leaf].value,t.right.asInstanceOf[Leaf].value)
      case t:Node if t.left.isInstanceOf[Leaf] && t.right.isInstanceOf[Node] =>
        t.op(t.left.asInstanceOf[Leaf].value,evalTree(t.right.asInstanceOf[Node]))
      case t:Node if t.left.isInstanceOf[Node] && t.right.isInstanceOf[Leaf] =>
        t.op(evalTree(t.left.asInstanceOf[Node]),t.right.asInstanceOf[Leaf].value)
      case t:Node if t.left.isInstanceOf[Node] && t.right.isInstanceOf[Node] =>
        t.op(evalTree(t.left.asInstanceOf[Node]),evalTree(t.right.asInstanceOf[Node]))
    }
  }
  def main(args: Array[String]): Unit = {
    val f  = (x:Int,y:Int) => x+y
    val g  = (x:Int,y:Int) => x*y
    val h  = (x:Int,y:Int) => x-y
//    println(evalTree(Node(Node(Leaf(2),Leaf(3),f),Leaf(4),g)))
    println(evalTree(Node(Node(Leaf(3),Leaf(8),g),Node(Leaf(2),Leaf(5),h),f))) //3*8 + (2-5)
  }


}

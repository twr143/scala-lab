package A2021feb

/**
  * Created by twr143 on 08.02.2021 at 16:00.
  */
object PreorderTraversal {
  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }

  def bstFromPreorder(preorder: Array[Int]): TreeNode = {
    if (preorder.length == 0) return null
    bst(preorder, new TreeNode(preorder(0)))
  }

  def bst(preorder: Array[Int], root: TreeNode): TreeNode = {
    for (i <- 1 to preorder.length - 1)
      insert(new TreeNode(preorder(i)), root)
    root
  }

  def insert(n: TreeNode, p: TreeNode): Unit = {
    if (n.value < p.value)
      if (p.left != null) insert(n, p.left)
      else p.left = n
    else if (p.right != null) insert(n, p.right)
    else p.right = n
  }

  def main(args: Array[String]): Unit = {
    //    val a = Array[(Int, Int)]((0, 1), (1, 2), (2, 3), (2, 4), (1, 5), (2, 6), (2, 7))
    //    val root = new TreeNode(a.head._2)
    //    var curRoot = root
    //    var i = 1
    //    while (i < a.length) {
    //      if (a(i)._1 == a(i - 1)._1 + 1 && a(i + 1)._1 == a(i)) {
    //         v
    //      }
    //    }
  }

}

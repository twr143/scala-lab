package A2021feb

/**
  * Created by twr143 on 10.02.2021 at 11:20.
  */
object BinaryTree {
  case class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
  def findDepth(root: TreeNode, cur: Int): Int = {
    val l = if (root.left != null) findDepth(root.left, cur + 1) else cur
    val r = if (root.right != null) findDepth(root.right, cur + 1) else cur
    Math.max(l, r)
  }
  case class LastNull(var isTrue: Boolean = false)

  def isCompleteTree(root: TreeNode): Boolean = {
    if (root == null) return true
    val d = findDepth(root, 1)
    isComplete(root, d, 1, LastNull())
  }

  def isComplete(root: TreeNode, depth: Int, level: Int, ln: LastNull): Boolean = {
    if (level < depth) (root != null
      && isComplete(root.left, depth, level + 1, ln)
      && isComplete(root.right, depth, level + 1, ln))
    else if (root != null) 
      !ln.isTrue
    else if (root == null && !ln.isTrue) {
      ln.isTrue = true
      true
    }
    else if (root == null && ln.isTrue) true
    else false
  }

  def main(args: Array[String]): Unit = {
    val ll = TreeNode(4)
    val lr = TreeNode(5)
    val rr = TreeNode(7)

    val l = TreeNode(2, ll, lr)
    val r = TreeNode(3, null, rr)
    val root = TreeNode(1, l, r)
    val res = isCompleteTree(root)
    println(res)
  }

}

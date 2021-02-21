package A2021feb

/**
  * Created by twr143 on 19.02.2021 at 12:12.
  */
object Trees2 {
  case class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }


  def isSameTree(p: TreeNode, q: TreeNode): Boolean = {
    if (p == null && q == null) true
    else if (p != null && q != null) {
      p.value == q.value && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    } else false
  }

  def swap[T <: TreeNode](t1: T): T = {
    val temp = t1.left
    t1.left = t1.right
    t1.right = temp
    t1
  }

  def isSymmetric(root: TreeNode): Boolean = {
    if (root == null) true
    else isSymmetric(root.left, root.right)
  }

  def isSymmetric(p: TreeNode, q: TreeNode): Boolean = {
    if (p == null && q == null) true
    else if (p != null && q != null) {
      p.value == q.value && isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left)
    } else false
  }


}

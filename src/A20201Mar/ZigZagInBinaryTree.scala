package A20201Mar

/**
  * Created by twr143 on 08.03.2021 at 17:03.
  */
object ZigZagInBinaryTree {
  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
  def longestZigZag(root: TreeNode): Int = {
    val d = findDepth(root, 0)
    traverse(root, d, 0)
  }

  def traverse(root: TreeNode, depth: Int, current: Int): Int = {
    if (root == null) -1
    else {
      val r = lzz(root, 0, false)
      if (depth - current > r) {
        val a = Math.max(r, traverse(root.left, depth, current + 1))
        Math.max(a, traverse(root.right, depth, current + 1))
      } else r
    }
  }

  def lzz(root: TreeNode, level: Int, currentLeft: Boolean): Int = {
    if (root == null) return level - 1
    else if (level == 0) Math.max(lzz(root.left, 1, true), lzz(root.right, 1, false))
    else if (currentLeft) lzz(root.right, level + 1, false)
    else lzz(root.left, level + 1, true)
  }

  def findDepth(root: TreeNode, cur: Int): Int = {
    val l = if (root.left != null) findDepth(root.left, cur + 1) else cur
    val r = if (root.right != null) findDepth(root.right, cur + 1) else cur
    Math.max(l, r)
  }


}

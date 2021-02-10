package A2021feb

import scala.collection.mutable._
import scala.collection.mutable._

/**
  * Created by twr143 on 07.02.2021 at 14:58.
  */
object RootToleaf {

  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }

  def sumNumbers(root: TreeNode): Int = {
    if (root == null) return 0
    sn(root, "")
  }

  def sn(root: TreeNode, cur: String): Int = {
    val left = if (root.left != null) sn(root.left, cur + root.value) else 0
    val right = if (root.right != null) sn(root.right, cur + root.value) else 0
    if (left + right == 0) (cur + root.value).toInt else left + right
  }

  def smallestFromLeaf(root: TreeNode): String = {
    smallest(root, Nil).map(_ + 97).map(_.toChar).mkString("")
  }

  def smallest(root: TreeNode, lst: List[Int]): List[Int] = {
    if (root.left == null && root.right == null) return root.value :: lst
    if (root.left != null && root.right == null) return smallest(root.left, root.value :: lst)
    if (root.left == null && root.right != null) return smallest(root.right, root.value :: lst)
    smaller(smallest(root.left, root.value :: lst), smallest(root.right, root.value :: lst))
  }

  def smaller(left: List[Int], right: List[Int]): List[Int] = {
    val l = left.iterator
    val r = right.iterator
    while (l.hasNext && r.hasNext) {
      val ln = l.next()
      val rn = r.next()
      if (ln < rn) return left
      else if (rn < ln) return right
    }
    if (!l.hasNext) left else right
  }

  def levelOrder(root: TreeNode): List[List[Int]] = {
    val a = new ArrayBuffer[List[Int]]()
    if (root != null)
      lOrder(root, 0, a)
      a.toList
  }

  def lOrder(root: TreeNode, level: Int, b: ArrayBuffer[List[Int]]): Unit = {
    if (b.size < level + 1)
      b.insert(level, List(root.value))
      else
    b.update(level, b(level) ++ List(root.value))
    if (root.left != null)
      lOrder(root.left, level + 1, b)
    if (root.right != null)
      lOrder(root.right, level + 1, b)
  }

  def zigzagLevelOrder(root: TreeNode): List[List[Int]] = {
    val a = new ArrayBuffer[List[Int]]()
    if (root != null)
      zigzag(root, 0, a)
      a.toList
  }

  def zigzag(root: TreeNode, level: Int, b: ArrayBuffer[List[Int]]): Unit = {
    if (b.size < level + 1)
      b.insert(level, List(root.value))
      else
    if (level % 2 == 0)
      b.update(level, b(level) ++ List(root.value))
      else
      b.update(level, root.value :: b(level))
      if (root.left != null)
        zigzag(root.left, level + 1, b)
      if (root.right != null)
        zigzag(root.right, level + 1, b)
  }

  def levelOrderBottom(root: TreeNode): List[List[Int]] = {
    val d = findDepth(root, 1)
    val a = ArrayBuffer[List[Int]]()
    a.sizeHint(d)
    for (i <- 0 to d - 1)
      a += List.empty[Int]
    if (root != null)
      OrderBottom(root, 0, a, d)
      a.toList
  }


  def OrderBottom(root: TreeNode, level: Int, b: ArrayBuffer[List[Int]], d: Int): Unit = {
    if (root.left != null)
      OrderBottom(root.left, level + 1, b, d)
    if (root.right != null)
      OrderBottom(root.right, level + 1, b, d)
      b.update(d - level - 1, b(d - level - 1) ++ List(root.value))
  }

  def minDepth(root: TreeNode): Int = {
    if (root == null) return 0
    minDepth(root, 1)
  }

  def minDepth(root: TreeNode, cur: Int): Int = {
    val l = if (root.left != null) minDepth(root.left, cur + 1) else cur
    val r = if (root.right != null) minDepth(root.right, cur + 1) else cur
    if (root.left != null && root.right != null)
      Math.min(l, r)
      else
    Math.max(l, r)
  }

  def findDepth(root: TreeNode, cur: Int): Int = {
    val l = if (root.left != null) findDepth(root.left, cur + 1) else cur
    val r = if (root.right != null) findDepth(root.right, cur + 1) else cur
    Math.max(l, r)
  }

  def isBalanced(root: TreeNode): Boolean = {
    isB(root)
  }

  def isB(root: TreeNode): Boolean = {
    if (root == null) return true
    val l = if (root.left != null) findDepth(root.left, 1) else 0
    val r = if (root.right != null) findDepth(root.right, 1) else 0
    if (r - l < 1 && r - l > -1)
      isB(root.left) && isB(root.right)
      else
    false
  }


}
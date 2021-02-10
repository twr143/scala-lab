package A2021feb

import oct2020.TreeSerial.TreeNode

import scala.collection.mutable._

/**
  * Created by twr143 on 09.02.2021 at 9:15.
  */
object BinarySearchTrees {
  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }

  //  def generateTrees(n: Int): List[TreeNode] = gen(0, n + 1).toList

  //  def gen(l: Int, r: Int): Seq[TreeNode] =
  //    if (l == r) Seq(null)
  //    else for (m <- l until r; tl <- gen(l, m); tr <- gen(m + 1, r))
  //      yield new TreeNode(m, tl, tr)

  def isValidBST(root: TreeNode): Boolean = {
    if (root == null) return true
    val lb = ArrayBuffer.empty[Int]
    isValidNode(root, lb)
    for (i <- 1 to lb.size - 1)
      if (lb(i) <= lb(i - 1)) return false
    true
  }

  def isValidNode(root: TreeNode, nodes: ArrayBuffer[Int]): Unit = {
    if (root.left != null) isValidNode(root.left, nodes)
    nodes += root.value
    if (root.right != null) isValidNode(root.right, nodes)
  }


  //  def sortedArrayToBST(nums: Array[Int]): TreeNode = {
  //    if (nums == null || nums.length == 0) return null
  //    sortedToBST(nums, 0, nums.length - 1, null, false)
  //  }

  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }

  def sortedListToBST(head: ListNode): TreeNode = {
    var h = head
    val b = ArrayBuffer[Int]()
    while (h != null) {
      b += h.x
      h = h.next
    }
    if (b.length == 0) return null
    sortedToBST(b, 0, b.length - 1, null, false)

  }

  def sortedToBST(nums: ArrayBuffer[Int], l: Int, r: Int, parent: TreeNode, isLeft: Boolean): TreeNode = {
    if (r == l) {
      val root = new TreeNode(nums(r))
      if (parent != null)
        if (isLeft) parent.left = root else parent.right = root
      root
    } else if (r == l + 1) {
      val root = new TreeNode(nums(r))
      if (parent != null)
        if (isLeft) parent.left = root else parent.right = root
      val lroot = new TreeNode(nums(l))
      root.left = lroot
      root
    } else {
      val m = (l + r) / 2
      val root = new TreeNode(nums(m))
      if (parent != null)
        if (isLeft) parent.left = root else parent.right = root
      sortedToBST(nums, l, Math.max(l, m - 1), root, true)
      sortedToBST(nums, m + 1, Math.max(r, m + 1), root, false)
      root
    }
  }

  def insertIntoBST(root: TreeNode, `val`: Int): TreeNode = {
    if (root != null)
      insert(new TreeNode(`val`), root)
      else
    new TreeNode(`val`)
  }

  def insert(n: TreeNode, p: TreeNode): TreeNode = {
    if (n.value < p.value)
      if (p.left != null) insert(n, p.left)
      else p.left = n
    else if (p.right != null) insert(n, p.right)
    else p.right = n
    p
  }
  class BSTIterator(_root: TreeNode) {

    def indexNode(root: TreeNode, nodes: ArrayBuffer[Int]): Unit = {
      if (root.left != null) indexNode(root.left, nodes)
      nodes += root.value
      if (root.right != null) indexNode(root.right, nodes)
    }
    val lb = ArrayBuffer.empty[Int]
    indexNode(_root, lb)
      val it = lb.iterator 
      def next(): Int = {
        it.next()  
      }
  
      def hasNext(): Boolean = {
        it.hasNext
      }
  
  }
  def main(args: Array[String]): Unit = {
    //    sortedArrayToBST(Array(-10, -3, 0, 5, 9))
  }
}



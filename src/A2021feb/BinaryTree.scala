package A2021feb

import scala.collection.mutable._

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

  def inorderTraversal(root: TreeNode): List[Int] = {
    val b = ListBuffer.empty[Int]
    if (root == null) return List.empty[Int]
    io(root, b)
    b.toList
  }

  def io(root: TreeNode, b: ListBuffer[Int]): Unit = {
    if (root == null) return
    io(root.left, b)
    b += root.value
    io(root.right, b)
  }

  def preorderTraversal(root: TreeNode): List[Int] = {
    val b = ListBuffer.empty[Int]
    if (root == null) return List.empty[Int]
    po(root, b)
    b.toList
  }

  def po(root: TreeNode, b: ListBuffer[Int]): Unit = {
    if (root == null) return
    b += root.value
    po(root.left, b)
    po(root.right, b)
  }

  def postorderTraversal(root: TreeNode): List[Int] = {
    val b = ListBuffer.empty[Int]
    if (root == null) return List.empty[Int]
    post(root, b)
    b.toList
  }

  def post(root: TreeNode, b: ListBuffer[Int]): Unit = {
    if (root == null) return
    post(root.left, b)
    post(root.right, b)
    b += root.value
  }

  def isUnivalTree(root: TreeNode): Boolean = {
    isUnival(root)
  }

  def isUnival(root: TreeNode): Boolean = {
    if (root == null) return true
    val `val` = (root.value == (if (root.left != null) root.left.value else root.value)) &&
      (root.value == (if (root.right != null) root.right.value else root.value))
    `val` && isUnival(root.left) && isUnival(root.right)
  }

  class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }
  def isSubPath(head: ListNode, root: TreeNode): Boolean = {
    containsHead(head, root).foldLeft(false)((b, node) => b || checkNext(head, node))
  }

  def containsHead(head: ListNode, root: TreeNode): List[TreeNode] = {
    if (root == null) return List.empty
    val c = if (root.value == head.x) List(root) else List.empty
    c ++ containsHead(head, root.left) ++ containsHead(head, root.right)
  }

  def checkNext(head: ListNode, root: TreeNode): Boolean = {
    if (head == null) true
    else if (root == null) false
    else
      head.x == root.value && (checkNext(head.next, root.left) || checkNext(head.next, root.right))
  }

  def flipEquiv(root1: TreeNode, root2: TreeNode): Boolean = {
    fE(root1, root2)
  }

  def fE(root1: TreeNode, root2: TreeNode): Boolean = {
    //    println(s"1:${if (root1 != null) root1.value else "null"} 2:${if (root2 != null) root2.value else "null"}")
    if (root1 == null && root2 == null) true
    else if ((root1 != null && root2 != null) && (root1.value == root2.value)
      && ((fE(root1.left, root2.left) && fE(root1.right, root2.right))
      || (fE(root1.left, swap(root2).left) && fE(root1.right, root2.right)))) true
    else false
  }

  def swap[T <: TreeNode](t1: T): T = {
    val temp = t1.left
    t1.left = t1.right
    t1.right = temp
    t1
  }
  class CBTInserter(_root: TreeNode) {

    var d = findDepth(_root, 1)

    def insert(v: Int): Int = {
      var i = insert(_root, 1, v, d)
      if (i < 0) {
        d += 1
        i = insert(_root, 1, v, d)
      }
      i
    }

    def insert(root: TreeNode, level: Int, value: Int, depth: Int): Int = {
      if (level < depth)
        if (root.left == null) {
          root.left = new TreeNode(value)
          root.value
        } else if (root.right == null) {
          root.right = new TreeNode(value)
          root.value
        } else {
          val tResult = insert(root.left, level + 1, value, depth)
          if (tResult < 0) insert(root.right, level + 1, value, depth) else tResult
        }
        else
      -1
    }

    def get_root(): TreeNode = {
      _root
    }

  }
  def fDepth(root: TreeNode, cur: Int): Int = {
    val l = if (root.left != null) fDepth(root.left, cur + 1) else cur
    val r = if (root.right != null) fDepth(root.right, cur + 1) else cur
    Math.max(l, r)
  }

  def rightSideView(root: TreeNode): List[Int] = {
    if (root == null) return List.empty[Int]
    val b = ArrayBuffer.empty[Int]
    val d = fDepth(root, 1)
    b.sizeHint(d)
    for (i <- 1 to d) b += -101
    rsv(root, b, 0)
    b.toList
  }

  def rsv(root: TreeNode, b: ArrayBuffer[Int], level: Int): Unit = {
    if (root == null) return
    b(level) = root.value
    rsv(root.left, b, level + 1)
    rsv(root.right, b, level + 1)
  }


  def main(args: Array[String]): Unit = {
    val rr1 = TreeNode(2)

    val l1 = TreeNode(4, null, null)
    val r1 = TreeNode(1, null, rr1)
    val root1 = TreeNode(0, l1, r1)

    val rl2 = TreeNode(2)

    val l2 = TreeNode(1, null, null)
    val r2 = TreeNode(4, null, rl2)
    val root2 = TreeNode(0, l2, r2)

    val res = flipEquiv(root1, root2)
    println(res)
  }

}

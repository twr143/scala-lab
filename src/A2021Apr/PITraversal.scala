package A2021Apr

/**
  * Created by twr143 on 26.04.2021 at 15:50.
  */
import scala.collection.mutable.TreeMap
class PITraversal {
  class TreeNode(_value: Int = 0,
                 _left: TreeNode = null,
                 _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
  def buildTree(preorder: Array[Int],
                inorder: Array[Int]): TreeNode = {

    var m = TreeMap.empty[Int, Int]
    for (i <- 0 to inorder.length - 1)
      m += inorder(i) -> i
    build(preorder, m, 1, preorder.length, new TreeNode(preorder(0)))
  }

  def build(preorder: Array[Int],
            inorderMap: TreeMap[Int, Int],
            s: Int,
            e: Int,
            root: TreeNode): TreeNode = {
    if (s >= e)
      return root
    val node = new TreeNode(preorder(s))
    if (inorderMap(root.value) > inorderMap(node.value)) {
      var k = s + 1
      
//      while (k < e && (inorderMap(root.value) > inorderMap.maxBy(preorder(k))))
//        k += 1
      root.left = node
      build(preorder, inorderMap, s + 1, k, root.left)
      if (k < e) {
        val node = new TreeNode(preorder(k))
        root.right = node
        build(preorder, inorderMap, k + 1, e, root.right)
      }
    } else {
      root.right = node
      build(preorder, inorderMap, s + 1, e, root.right)
    }
    root
  }
}

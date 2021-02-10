package A2021feb

/**
  * Created by twr143 on 08.02.2021 at 9:07.
  */
object DuplicateSubtrees {
  class TreeNode(_value: Int = 0, _left: TreeNode = null, _right: TreeNode = null) {
    var value: Int = _value
    var left: TreeNode = _left
    var right: TreeNode = _right
  }
  object Solution {
    def findDuplicateSubtrees(root: TreeNode): List[TreeNode] = {
      null
    }

    def equal(one: TreeNode, two: TreeNode): Boolean = {
      ((one == null && two == null) || (one != null && two != null
        && one.value == two.value && equal(one.left, two.left) && equal(one.right, two.right)))
    }
  }

}

package algo.trees.btdiameter;

/**
 * Created by Ilya Volynin on 26.11.2019 at 10:31.
 */
public class BTDiameterJ {
    public int diameterOfBinaryTree(TreeNode root){
      if ((root == null) || (root.left == null && root.right == null)) return 0;
      root.val=0;
      diameterOfBinaryTree(root, null, root);
      return root.val;
    }

    int diameterOfBinaryTree(TreeNode root, TreeNode parent, TreeNode rootCopy) {
      if (root.left == null && root.right == null) {
        return 0;
      }
      int lmax = root.left != null?
        diameterOfBinaryTree(root.left, root, rootCopy) :0;
      int rmax = root.right != null?
        diameterOfBinaryTree(root.right, root, rootCopy) :0;
      if (root.left != null && root.right != null) {
        rootCopy.val = Math.max(rootCopy.val, lmax + rmax + 2);
      }
      else if (parent == null)
        rootCopy.val = Math.max(rootCopy.val, lmax + rmax + 1);

      return Math.max(lmax, rmax) + 1 ;
    }

}

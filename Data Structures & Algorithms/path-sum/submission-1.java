/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 0-case => empty tree, if remaining sum is 0 true, else false
        // 1-case => if left subree hasPathSum of targetSum - node.val true, else same right, else false

        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        int nextTargetSum = targetSum - root.val;

        if (root.left != null && hasPathSum(root.left, nextTargetSum)) {
            return true;
        }
        if (root.right != null && hasPathSum(root.right, nextTargetSum)) {
            return true;
        }

        return false;
    }
}
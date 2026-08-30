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
    record Result(int height, boolean balanced) {}

    public boolean isBalanced(TreeNode root) {
        return dfs(root).balanced;
    }

    private Result dfs(TreeNode root) {
        if (root == null) {
            return new Result(0, true);
        }

        var leftResult = dfs(root.left);
        var rightResult = dfs(root.right);

        return new Result(
            1 + Math.max(leftResult.height, rightResult.height),
            leftResult.balanced && rightResult.balanced && Math.abs(leftResult.height - rightResult.height) < 2
        );
    }
}

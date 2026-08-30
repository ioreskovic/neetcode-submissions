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
    record Acc(int found, int maxK) {}

    public int kthSmallest(TreeNode root, int k) {
        return dfs(root, k).found;
    }

    private Acc dfs(TreeNode root, int k) {
        if (root == null) {
            return new Acc(-1, 0);
        }

        var leftResult = dfs(root.left, k);
        if (leftResult.found > -1) {
            return leftResult;
        }

        var currK = leftResult.maxK + 1;
        if (k == currK) {
            return new Acc(root.val, currK);
        }

        var rightResult = dfs(root.right, k - currK);
        if (rightResult.found > -1) {
            return rightResult;
        }

        return new Acc(-1, currK + rightResult.maxK);
    }
}

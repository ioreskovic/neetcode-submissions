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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> acc = new ArrayList<>();
        dfs(root, acc);
        return acc;
    }

    private void dfs(TreeNode root, List<Integer> acc) {
        if (root == null) {
            return;
        }

        dfs(root.left, acc);
        acc.add(root.val);
        dfs(root.right, acc);
    }
}
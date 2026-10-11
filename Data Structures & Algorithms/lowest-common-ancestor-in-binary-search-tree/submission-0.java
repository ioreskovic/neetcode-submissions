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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode smaller = p;
        TreeNode bigger = q;

        if (p.val > q.val) {
            smaller = q;
            bigger = p;
        }

        if (bigger.val < root.val) return lowestCommonAncestor(root.left, smaller, bigger);
        if (smaller.val > root.val) return lowestCommonAncestor(root.right, smaller, bigger);
        return root;
    }
}

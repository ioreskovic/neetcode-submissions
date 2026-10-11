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
    record NodeInfo(int height, int diameter) { }
    // d(null) = 0
    // d[x] = 1
    public int diameterOfBinaryTree(TreeNode root) {
        return heightDiameter(root).diameter;
    }

    private NodeInfo heightDiameter(TreeNode node) {
        if (node == null) return new NodeInfo(0, 0);

        var leftInfo = heightDiameter(node.left);
        var rightInfo = heightDiameter(node.right);

        var height = 1 + Math.max(leftInfo.height, rightInfo.height);
        var diameterChildren = Math.max(leftInfo.diameter, rightInfo.diameter);
        var diameterRoot = leftInfo.height + rightInfo.height;
        var diameter = Math.max(diameterChildren, diameterRoot);

        return new NodeInfo(height, diameter);
    }
}

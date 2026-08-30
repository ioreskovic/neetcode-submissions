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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return reconstruct(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode reconstruct(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        if (preEnd < preStart) {
            return null;
        }

        TreeNode node = new TreeNode(pre[preStart]);

        int pivot = inStart;
        while (in[pivot] != node.val) {
            pivot++;
        }

        // left amount of pivot goes left, right amount of pivot goes right
        int leftSize = pivot - inStart;
        int rightSize = inEnd - pivot;

        node.left = reconstruct(pre, preStart + 1, preStart + leftSize, in, inStart, pivot - 1);
        node.right = reconstruct(pre, preStart + leftSize + 1, preEnd, in, pivot + 1, inEnd);

        return node;
    }
}

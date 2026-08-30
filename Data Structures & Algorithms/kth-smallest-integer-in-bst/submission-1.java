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
    sealed interface Result permits Found, NotFound {}

    record Found(int val) implements Result {}
    record NotFound(int explored) implements Result {}

    public int kthSmallest(TreeNode root, int k) {
        return switch (dfs(root, k)) {
            case Found found -> found.val;
            case NotFound __ -> -1;
        };
    }

    private Result dfs(TreeNode root, int k) {
        if (root == null) {
            return new NotFound(0);
        }

        return switch (dfs(root.left, k)) {
            case Found found -> found;
            case NotFound notFound when (notFound.explored + 1 == k) -> new Found(root.val);
                case NotFound notFound -> {
                    yield switch (dfs(root.right, k - notFound.explored - 1)) {
                        case Found found2 -> found2;
                        case NotFound notFound2 -> new NotFound(notFound.explored + 1 + notFound2.explored);
                    };
                }
        };
    }
}

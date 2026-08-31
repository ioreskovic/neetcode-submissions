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
    public List<Integer> rightSideView(TreeNode root) {
        // Result can be restates as:
        // 1st result of each level with level-order-traversal focusing on right node 1st

        if (root == null) {
            return List.of();
        }

        Deque<TreeNode> toProcess = new LinkedList<>();
        toProcess.offerLast(root);
        
        List<List<Integer>> levels = new ArrayList<>();
        while (!toProcess.isEmpty()) {
            List<Integer> levelOrder = new ArrayList<>();
            int levelSize = toProcess.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = toProcess.pollFirst();
                levelOrder.add(current.val);

                if (current.right != null) {
                    toProcess.offerLast(current.right);
                }
                if (current.left != null) {
                    toProcess.offerLast(current.left);
                }
            }
            levels.add(levelOrder);
        }

        return levels.stream().map(it -> it.get(0)).toList();
    }
}

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        // have a queue
        // push root Node
        
        // repeat until q empty
        // get node
        // report(node)
        // push left, right

        // 0-case - empty tree
        // 0-case - new list at each level
        // 1-case - reusing current level list -> 2nd level cant happen after 3rd level -> reuse variable


        // [] ->
        // [1] -> <0], [0], [[0]]
        // [1,2,4] -> <2] 2, [0][2]
        List<List<Integer>> results = new ArrayList<>();

        if (root == null) {
            return results; // empty
        }

        Deque<TreeNode> toProcess = new LinkedList<>();
        toProcess.offerLast(root);

        while (!toProcess.isEmpty()) {
            List<Integer> levelResults = new ArrayList<>();
            int levelSize = toProcess.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = toProcess.pollFirst();
                levelResults.add(current.val);
                if (current.left != null) {
                    toProcess.offerLast(current.left);
                }
                if (current.right != null) {
                    toProcess.offerLast(current.right);
                }
            }
            results.add(levelResults);
        }

        return results;
    }
}

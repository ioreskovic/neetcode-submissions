/*
Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        // 0-case: new Node(node.val)
        // upstram.add(node)
        // node.neighbours.map(clone, upstream)

        // 1 -> [2]
        // 2 -> [1, 3]
        // 3 -> [2]

        // 1' -> [clone(2)], {1})
        //   2' -> [clone(3), upstream{1}] {1, 2}
        //     3' -> [upsptream{2}] {1, 2, 3}

        return cloneGraph(node, new HashMap<>());
    }

    private Node cloneGraph(Node node, HashMap<Integer, Node> cloned) {
        if (node == null) return null;
        Node clonedNode = cloned.get(node.val);
        if (clonedNode != null) return clonedNode;

        ArrayList<Node> clonedNeighbors = new ArrayList<>();
        clonedNode = new Node(node.val, clonedNeighbors);
        cloned.put(node.val, clonedNode);

        node.neighbors.forEach(n -> clonedNeighbors.add(cloneGraph(n, cloned)));

        return clonedNode;
    }

    // 
    //  cloneGraph( 1, {} )
    //      cloneGraph( 2, { 1' -> [] } )
    //          cloneGraph( 1, { 1' -> [], 2' -> [1'] } )
    //          cloneGraph( 3, { 1' -> [], 2' -> [1'], 3' -> [2'] } )
 
}
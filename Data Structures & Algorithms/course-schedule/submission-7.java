class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // input will form a dependency graph
        // if there is a circular dependency, that means impossible
        // if there is a disconnected component that means impossible
        // find a component with zero-inlets, thats our start
        // run dfs on from it
        // if at the end there is something unvisited, the answer is no
        // [1,4],[2,4],[3,1],[3,2]
        // 0 -> 1 <- 2
        //  \_/

        HashMap<Integer, HashSet<Integer>> outlets = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> inlets = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            outlets.putIfAbsent(i, new HashSet<>());
            inlets.putIfAbsent(i, new HashSet<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            int to = prerequisites[i][0];
            int from = prerequisites[i][1];

            outlets.get(from).add(to);
            inlets.get(to).add(from);
        }

        final var starts = inlets.keySet().stream().filter(i -> inlets.get(i).size() == 0).toList();
        HashSet<Integer> visited = new HashSet<>();
        for (Integer start : starts) {
            dfs(outlets, inlets, start, visited);
        }
        return visited.size() == numCourses;
    }

    private void dfs(HashMap<Integer, HashSet<Integer>> outlets, HashMap<Integer, HashSet<Integer>> inlets, Integer start, HashSet<Integer> visited) {
        if (start == null) return;
        if (visited.contains(start)) return;
        if (!inlets.get(start).isEmpty()) return;
        visited.add(start);
        outlets.getOrDefault(start, new HashSet<>()).forEach(next -> {
            inlets.get(next).remove(start);
            dfs(outlets, inlets, next, visited);
        });
    }
}

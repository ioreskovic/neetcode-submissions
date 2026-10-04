class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        final var courses = new Courses(prerequisites);

        var result = new Result();

        for (int course = 0; course < numCourses; course++) {
            result = traverse(course, courses, result);
        }

        if (result.invalid.get()) return new int[0];
        return result.order.stream().mapToInt(Integer::intValue).toArray();
    }

    private Result traverse(int course, Courses courses, Result result) {
        if (result.invalid.get()) return result;
        if (result.path.contains(course)) return result.invalid();
        if (result.seen.contains(course)) return result;

        result.seen.add(course);
        result.path.add(course);

        for (int required : courses.reqsOf(course)) {
            traverse(required, courses, result);
        }

        result.order.add(course);
        result.path.remove(course);

        return result;
    }

    static class Result {
        public final AtomicBoolean invalid;
        public final ArrayList<Integer> order;
        public final HashSet<Integer> seen;
        public final HashSet<Integer> path;

        public Result() {
            this.invalid = new AtomicBoolean(false);
            this.order = new ArrayList<>();
            this.seen = new HashSet<>();
            this.path = new HashSet<>();
        }

        public Result invalid() {
            this.invalid.set(true);
            return this;
        }
    }

    static class Courses {
        private final HashMap<Integer, HashSet<Integer>> reqs;

        public Courses(int[][] prerequisites) {
            this.reqs = new HashMap<>();

            for (int[] relation : prerequisites) {
                int later = relation[0];
                int earlier = relation[1];
                reqs.computeIfAbsent(later, __ -> new HashSet<Integer>()).add(earlier);
            }
        }

        public HashSet<Integer> reqsOf(int i) {
            return this.reqs.getOrDefault(i, new HashSet<Integer>());
        }
    }
}

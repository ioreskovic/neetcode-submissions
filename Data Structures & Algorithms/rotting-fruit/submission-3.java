class Solution {
    record Location(int r, int c) { }

    static class Grid {
        private final int[][] raw;
        final int rows;
        final int cols;

        Grid(int[][] raw) {
            this.raw = raw;
            this.rows = raw.length;
            this.cols = raw[0].length;
        }

        boolean contains(Location loc) {
            return (0 <= loc.r && loc.r < rows) && (0 <= loc.c && loc.c < cols);
        }

        boolean empty(Location loc) {
            return raw[loc.r][loc.c] == 0;
        }

        boolean fresh(Location loc) {
            return raw[loc.r][loc.c] == 1;
        }

        boolean rotten(Location loc) {
            return raw[loc.r][loc.c] == 2;
        }

        List<Location> adjacent(Location loc, java.util.function.Function<Location, Boolean> ignored) {
            return List.of(
                new Location(loc.r + 1, loc.c),
                new Location(loc.r - 1, loc.c),
                new Location(loc.r, loc.c + 1),
                new Location(loc.r, loc.c - 1)
            ).stream().filter(l -> {
                return
                    contains(l) &&
                    !empty(l) &&
                    !rotten(l) &&
                    !ignored.apply(l);
            }).toList();
        }

        HashSet<Location> allRotten() {
            HashSet<Location> rottenOnes = new HashSet<>();
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    Location loc = new Location(r, c);
                    if (rotten(loc)) {
                        rottenOnes.add(loc);
                    }
                }
            }
            return rottenOnes;
        }

        HashSet<Location> allFresh() {
            HashSet<Location> freshOnes = new HashSet<>();
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    Location loc = new Location(r, c);
                    if (fresh(loc)) {
                        freshOnes.add(loc);
                    }
                }
            }
            return freshOnes;
        }
    }

    // i can only move to fresh fruit to rot
    // there can be multiple rotten ones, so the start is not Just 1 but all rotten
    // each tick is a multi-bfs exploration

    // f 0 f
    // 0 R 0
    // f 0 f
    public int orangesRotting(int[][] raw) {
        Grid grid = new Grid(raw);
        HashSet<Location> rotten = grid.allRotten();
        HashSet<Location> fresh = grid.allFresh();
        int minutesPassed = 0;

        if (rotten.isEmpty() && fresh.isEmpty()) return minutesPassed;
        if (rotten.isEmpty()) return -1;


        Deque<Location> rotting = new LinkedList<>(rotten);
        while (true) {
            if (rotting.isEmpty() && fresh.isEmpty()) return minutesPassed;
            if (rotting.isEmpty()) return -1;

            int rottingSize = rotting.size();
            int toRot = 0;
            for (int i = 0; i < rottingSize; i++) {
                Location loc = rotting.pollFirst();

                for(Location adj : grid.adjacent(loc, rotten::contains)) {
                    rotting.offerLast(adj);
                    rotten.add(adj);
                    fresh.remove(adj);
                    toRot++;
                };
            }

            if (toRot > 0) {
                minutesPassed++;
            }
        }
    }
}

/**
 * Definition of Interval:
 * public class Interval {
 *     public int start, end;
 *     public Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */

class Solution {
    static boolean disjoint(Interval first, Interval second) {
        return (second.end <= first.start) || (second.start >= first.end);
    }

    static void mergeInto(Interval first, Interval second) {
        first.start = Math.min(first.start, second.start);
        first.end = Math.max(first.end, second.end);
    }

    static class UnionFind {
        Map<Interval, Interval> parent;
        Map<Interval, Integer> rank;

        UnionFind() {
            this.parent = new HashMap<>();
            this.rank = new HashMap<>();
        }

        Interval find(Interval interval) {
            parent.putIfAbsent(interval, interval);

            if (parent.get(interval) != interval) {
                parent.put(interval, find(parent.get(interval)));
            }
            return parent.get(interval);
        }

        boolean union(Interval a, Interval b) {
            Interval pa = find(a);
            Interval pb = find(b);

            if (pa == pb) return false;
            if (disjoint(pa, pb)) return false;

            int ra = rank.getOrDefault(pa, 0);
            int rb = rank.getOrDefault(pb, 0);

            if (ra > rb) {
                mergeInto(pa, pb);
                parent.put(pb, pa);
            } else if (ra < rb) {
                mergeInto(pb, pa);
                parent.put(pa, pb);
            } else {
                mergeInto(pa, pb);
                parent.put(pb, pa);
                rank.compute(pa, (__, r) -> r == null ? 0 : r + 1);
            }

            return true;
        }
    }

    public boolean canAttendMeetings(List<Interval> intervals) {
        int size = intervals.size();
        if (size < 2) return true;

        Comparator<Interval> cmp = Comparator.comparingInt((Interval i) -> i.start).thenComparingInt((Interval i) -> i.end);
        intervals.sort(cmp);

        for (int i = 0; i < size - 1; i++) {
            if (!disjoint(intervals.get(i), intervals.get(i + 1))) return false;
        }

        return true;
    }
}

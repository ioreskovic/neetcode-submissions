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

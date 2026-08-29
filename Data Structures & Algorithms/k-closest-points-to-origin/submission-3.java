class Solution {
    static class Point implements Comparable<Point> {
        final int x;
        final int y;
        final int d;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.d = x*x + y*y;
        }

        public int[] asArray() {
            return new int[] { x, y };
        }

        @Override
        public int compareTo(Point other) {
            return Integer.compare(this.d, other.d);
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    public int[][] kClosest(int[][] raw, int k) {
        Point[] points = new Point[raw.length];
        for (int i = 0; i < raw.length; i++) {
            points[i] = new Point(raw[i][0], raw[i][1]);
        }

        sort(points);

        int[][] results = new int[k][2];
        for (int i = 0; i < k; i++) {
            results[i] = points[i].asArray();
        }

        return results;
    }

    public <T extends Comparable<T>> void sort(T[] data) {
        qSort(data, 0, data.length - 1);
    }

    public <T extends Comparable<T>> void qSort(T[] data, int s, int e) {
        if (e - s < 1) {
            return;
        }

        int w = s;
        for (int r = s; r < e; r++) {
            if (data[r].compareTo(data[e]) <= 0) {
                swap(data, r, w++);
            }
        }
        swap(data, e, w);

        qSort(data, s, w - 1);
        qSort(data, w + 1, e);
    }

    public <T> void swap(T[] data, int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}

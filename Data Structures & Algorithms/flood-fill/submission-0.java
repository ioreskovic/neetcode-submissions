class Solution {
    static class Image {
        final int[][] raw;
        final int rows;
        final int cols;

        Image(int[][] raw) {
            this.raw = raw;
            this.rows = raw.length;
            this.cols = raw[0].length;
        }

        boolean contains(Location location) {
            int r = location.r;
            int c = location.c;
            
            return (0 <= r && r < rows) && (0 <= c && c < cols);
        }

        int colorAt(Location location) {
            return this.raw[location.r][location.c];
        }

        void setColor(Location location, int color) {
            this.raw[location.r][location.c] = color;
        }
    }

    record Location(int r, int c) { 
        Location up() {
            return new Location(r - 1, c);
        }

        Location down() {
            return new Location(r + 1, c);
        }

        Location left() {
            return new Location(r, c - 1);
        }

        Location right() {
            return new Location(r, c + 1);
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        Image img = new Image(image);
        Location start = new Location(sr, sc);
        int originalColor = img.colorAt(start);
        HashSet<Location> visited = new HashSet<>();

        ff(img, originalColor, start, color, visited);

        return img.raw;
    }

    private void ff(Image image, int originalColor, Location location, int color, HashSet<Location> visited) {
        if (!image.contains(location)) return;
        if (image.colorAt(location) != originalColor) return;
        if (visited.contains(location)) return;

        visited.add(location);
        image.setColor(location, color);

        ff(image, originalColor, location.right(), color, visited);
        ff(image, originalColor, location.down(), color, visited);
        ff(image, originalColor, location.left(), color, visited);
        ff(image, originalColor, location.up(), color, visited);
    }
}
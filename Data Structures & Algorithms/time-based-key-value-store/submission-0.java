class TimeMap {
    static record Timestamped<V>(V value, int t) { }
    private HashMap<String, ArrayList<Timestamped<String>>> map;

    public TimeMap() {
        this.map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        this.map.computeIfAbsent(key, __ -> new ArrayList<>()).add(new Timestamped<>(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        return search(this.map.getOrDefault(key, new ArrayList<>()), timestamp);
    }

    private String search(ArrayList<Timestamped<String>> list, int t) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            var tv = list.get(mid);

            if (t == tv.t) {
                return tv.value;
            } else if (t < tv.t) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (0 <= right && right < list.size()) return list.get(right).value;
        return "";
    }
}

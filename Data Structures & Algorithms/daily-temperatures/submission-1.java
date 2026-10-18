class Solution {
    static record DailyTemp(int temp, int at) { }
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] result = new int[len];

        LinkedList<DailyTemp> maxTemps = new LinkedList<>();

        for (int i = len - 1; i >= 0; i--) {
            DailyTemp today = new DailyTemp(temperatures[i], i);
            while (!maxTemps.isEmpty() && today.temp >= maxTemps.peekLast().temp) { maxTemps.pollLast(); }
            if (maxTemps.isEmpty()) {
                result[i] = 0;
            } else {
                result[i] = maxTemps.peekLast().at - today.at;
            }
            maxTemps.offerLast(today);
        }

        return result;
    }
}

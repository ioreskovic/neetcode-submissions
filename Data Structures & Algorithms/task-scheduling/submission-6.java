class Solution {
    static int ALPHABET = 'Z' - 'A' + 1;
    static int OFFSET = 'A';
    static char IDLE = '0';

    static record Task(char task, int freq) implements Comparable<Task> {
        @Override
        public int compareTo(Task other) {
            int freqCmp = -Integer.compare(this.freq, other.freq);
            if (freqCmp != 0) return freqCmp;

            return Character.compare(this.task, other.task);
        }

        public Optional<Task> decrement() {
            if (freq == 1) return Optional.empty();
            return Optional.of(new Task(task, freq - 1));
        }
    }

    static record ScheduledTask(Task task, int availableAt) { }

    public int leastInterval(char[] tasks, int n) {
        int nTasks = tasks.length;
        List<Task> taskList = counts(tasks); // O(n) time, O(k) space
        PriorityQueue<Task> ready = new PriorityQueue(taskList); // O(k) time
        LinkedList<ScheduledTask> waiting = new LinkedList<>();

        int time = 0;
        while (!ready.isEmpty() || !waiting.isEmpty()) {
            if (!waiting.isEmpty() && time >= waiting.peekFirst().availableAt) {
                ready.offer(waiting.pollFirst().task);
            }

            if (!ready.isEmpty()) {
                var task = ready.poll();
                
                // we used it
                // so scheduled if not exhausted
                final var availableAt = time + n + 1;
                task.decrement().ifPresent(t -> waiting.offerLast(new ScheduledTask(t, availableAt)));
            }

            // i have scheduled it or idling, so time advances a tick
            time++;
        }

        return time;
    }

    private List<Task> counts(char[] tasks) {
        int[] result = new int[ALPHABET];
        for (int i = 0; i < ALPHABET; i++) {
            result[i] = -1;
        }

        for (char c : tasks) {
            int i = c - OFFSET;
            if (result[i] == -1) result[i] = 0;
            result[i]++;
        }

        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < ALPHABET; i++) {
            if (result[i] > 0) {
                taskList.add(new Task((char) (i + OFFSET), result[i]));
            }
        }

        return taskList;
    }
}

class Solution {
    record Sandwich(int type, int position) { }
    record Student(int type, Optional<Sandwich> ignored) {
        public boolean wants(Sandwich sandwich) {
            return type == sandwich.type;
        }

        public Student ignoring(Sandwich sandwich) {
            return new Student(type, Optional.of(sandwich));
        }
    }

    public int countStudents(int[] sts, int[] sws) {
        Deque<Sandwich> sandwiches = new LinkedList<>();
        Deque<Student> students = new LinkedList<>();

        for (int type : sts) {
            students.offerLast(new Student(type, Optional.empty()));
        }

        for (int i = 0; i < sws.length; i++) {
            sandwiches.offerLast(new Sandwich(sws[i], i));
        }

        var student = students.peekFirst();
        var sandwich = sandwiches.peekFirst();

        while (student != null && sandwich != null && !alreadyIgnoredSandwich(student, sandwich)) {
            if (student.wants(sandwich)) {
                students.removeFirst();
                sandwiches.removeFirst();
            } else {
                students.offerLast(students.removeFirst().ignoring(sandwich));
            }

            student = students.peekFirst();
            sandwich = sandwiches.peekFirst();
        }

        return students.size();
    }

    private boolean alreadyIgnoredSandwich(Student student, Sandwich sandwich) {
        return student.ignored.map(it -> it.equals(sandwich)).orElse(false);
    }
}
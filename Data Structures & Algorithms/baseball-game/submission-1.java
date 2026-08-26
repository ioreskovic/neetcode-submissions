class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> records = new Stack<>();
        for (String rawOp: operations) {
            operation(rawOp).ifPresent(it -> it.apply(records));
        }

        return records.stream().mapToInt(Integer::intValue).sum();
    }

    private static Optional<Operation> operation(String rawOp) {
        return switch (rawOp) {
            case "+" -> {
                 yield Optional.of(new Add());
            }
            case "D" -> {
                yield Optional.of(new Duble());
            }
            case "C" -> {
                yield Optional.of(new Clear());
            }
            case String x -> {
                try {
                    yield Optional.of(new Insert(Integer.parseInt(x)));
                } catch (NumberFormatException e) {
                    yield Optional.empty();
                }
            }
        };
    }

    sealed interface Operation permits Insert, Add, Duble, Clear {
        Stack<Integer> apply(Stack<Integer> records);
    }

    record Insert(int x) implements Operation {

        @Override
        public Stack<Integer> apply(Stack<Integer> records) {
            records.push(x);
            return records;
        }
    }

    record Add() implements Operation {

        @Override
        public Stack<Integer> apply(Stack<Integer> records) {
            int idx = records.size() - 1;
            records.add(records.get(idx) + records.get(idx - 1));
            return records;
        }
    }

    record Duble() implements Operation {

        @Override
        public Stack<Integer> apply(Stack<Integer> records) {
            records.add(records.peek() * 2);
            return records;
        }
    }

    record Clear() implements Operation {

        @Override
        public Stack<Integer> apply(Stack<Integer> records) {
            records.pop();
            return records;
        }
    }
}
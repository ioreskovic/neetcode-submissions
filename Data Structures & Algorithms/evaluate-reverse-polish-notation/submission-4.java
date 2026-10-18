class Solution {
    sealed interface Token permits Num, Add, Sub, Mul, Div {
        void accept(LinkedList<Integer> stack);
    }

    static record Num(int num) implements Token {
        @Override
        public void accept(LinkedList<Integer> stack) {
            stack.offerLast(num);
        }
    }

    static record Add() implements Token {
        @Override
        public void accept(LinkedList<Integer> stack) {
            int a = stack.pollLast();
            int b = stack.pollLast();
            stack.offerLast(b + a);
        }
    }

    static record Sub() implements Token {
        @Override
        public void accept(LinkedList<Integer> stack) {
            int a = stack.pollLast();
            int b = stack.pollLast();
            stack.offerLast(b - a);
        }

    }

    static record Mul() implements Token {
        @Override
        public void accept(LinkedList<Integer> stack) {
            int a = stack.pollLast();
            int b = stack.pollLast();
            stack.offerLast(b * a);
        }

    }

    static record Div() implements Token {
        @Override
        public void accept(LinkedList<Integer> stack) {
            int a = stack.pollLast();
            int b = stack.pollLast();
            stack.offerLast(b / a);
        }

    }

    static Token parse(String s) {
        return switch (s) {
            case "+" -> { yield new Add(); }
            case "-" -> { yield new Sub(); }
            case "*" -> { yield new Mul(); }
            case "/" -> { yield new Div(); }
            default -> { yield new Num(Integer.parseInt(s)); }
        };
    }

    public int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (String s : tokens) {
            parse(s).accept(stack);
        }

        return stack.peekLast();
    }
}

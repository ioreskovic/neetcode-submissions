class Solution {
    public boolean isValid(String s) {
        final Stack<Bracket> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            final var curr = stack.isEmpty() ? null : stack.peek();
            final var next = Bracket.of(c);

            if (Bracket.invalidNext(curr, next)) {
                return false;
            } else if (Bracket.matchingPair(curr, next)) {
                stack.pop();
            } else {
                stack.push(next);
            }
        }

        return stack.isEmpty();
    }

    enum Bracket {
        ROUND_LEFT,
        ROUND_RIGHT,
        SQUARE_LEFT,
        SQUARE_RIGHT,
        CURLY_LEFT,
        CURLY_RIGHT;

        public static Bracket of(char c) {
            return switch (c) {
                case '(' -> ROUND_LEFT;
                case ')' -> ROUND_RIGHT;
                case '[' -> SQUARE_LEFT;
                case ']' -> SQUARE_RIGHT;
                case '{' -> CURLY_LEFT;
                case '}' -> CURLY_RIGHT;
                default -> throw new IllegalArgumentException();
            };
        }

        public static boolean invalidNext(Bracket left, Bracket right) {
            return switch (left) {
                case ROUND_LEFT -> ROUND_LEFT_INVALID.contains(right);
                case SQUARE_LEFT -> SQUARE_LEFT_INVALID.contains(right);
                case CURLY_LEFT -> CURLY_LEFT_INVALID.contains(right);
                case null -> START_INVALID.contains(right);
                default -> throw new IllegalStateException();
            };
        }

        public static boolean matchingPair(Bracket left, Bracket right) {
            return switch (left) {
                case Bracket.ROUND_LEFT -> Bracket.ROUND_RIGHT == right;
                case Bracket.SQUARE_LEFT -> Bracket.SQUARE_RIGHT == right;
                case Bracket.CURLY_LEFT -> Bracket.CURLY_RIGHT == right;
                case null -> false;
                default -> throw new IllegalStateException();
            };
        }
    }

    private static Set<Bracket> ROUND_LEFT_INVALID = Set.of(Bracket.SQUARE_RIGHT, Bracket.CURLY_RIGHT);
    private static Set<Bracket> SQUARE_LEFT_INVALID = Set.of(Bracket.ROUND_RIGHT, Bracket.CURLY_RIGHT);
    private static Set<Bracket> CURLY_LEFT_INVALID = Set.of(Bracket.ROUND_RIGHT, Bracket.SQUARE_RIGHT);
    private static Set<Bracket> START_INVALID = Set.of(Bracket.ROUND_RIGHT, Bracket.SQUARE_RIGHT, Bracket.CURLY_RIGHT);
}

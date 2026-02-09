package project20280.stacksqueues;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public void check() {
        // TODO
        LinkedStack<Character> stack = new LinkedStack<>();

        for (Character letter : input.toCharArray()) {
            if (letter == '[' || letter == '{' || letter == '(') {
                stack.push(letter);
            }
            else if (!stack.isEmpty() && letter == ']' && stack.top() == '[') {
                stack.pop();
            }
            else if (!stack.isEmpty() && letter == '}' && stack.top() == '{') {
                stack.pop();
            }
            else if (!stack.isEmpty() && letter == ')' && stack.top() == '(') {
                stack.pop();
            }
        }
        if (!stack.isEmpty())
        {
            System.out.println("Not correct");
        }
        System.out.println("Is correct");
    }

    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            checker.check();
        }
    }
}
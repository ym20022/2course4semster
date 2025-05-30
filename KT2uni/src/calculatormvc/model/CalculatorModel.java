package calculatormvc.model;

import java.util.Stack;

public class CalculatorModel {
    public double calculate(String expression) throws IllegalArgumentException {
        validateExpression(expression);
        String rpn = convertToRPN(expression);
        return evaluateRPN(rpn);
    }

    private void validateExpression(String expression) {
        
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("Пустое выражение");
        }

        
        Stack<Character> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Несбалансированные скобки");
                }
                stack.pop();
            }
        }
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Несбалансированные скобки");
        }

        
        if (!expression.matches("^[0-9+\\-*/.^!()elogxps ]+$")) {
            throw new IllegalArgumentException("Недопустимые символы в выражении");
        }
    }

    private String convertToRPN(String expression) {
        StringBuilder output = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();

        expression = expression.replace("**", "^")
                .replace(" ", "");

        int i = 0;
        while (i < expression.length()) {
            char c = expression.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                
                while (i < expression.length() &&
                        (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    output.append(expression.charAt(i++));
                }
                output.append(' ');
                continue;
            } else if (Character.isLetter(c)) {
               
                StringBuilder func = new StringBuilder();
                while (i < expression.length() && Character.isLetter(expression.charAt(i))) {
                    func.append(expression.charAt(i++));
                }
                String function = func.toString();

                if (function.equals("log")) {
                    operatorStack.push('l');
                } else if (function.equals("exp")) {
                    operatorStack.push('e');
                } else {
                    throw new IllegalArgumentException("Неизвестная функция: " + function);
                }
              
                if (i < expression.length() && expression.charAt(i) == '(') {
                    i++;
                }
                continue;
            }

            if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    output.append(operatorStack.pop()).append(' ');
                }
                if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                    operatorStack.pop();
                }
            } else if (isOperator(c)) {
               
                if (c == '-' && (i == 0 || expression.charAt(i-1) == '(' || isOperator(expression.charAt(i-1)))) {
                    output.append("0 "); // Push 0 before unary minus
                }
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(c)) {
                    output.append(operatorStack.pop()).append(' ');
                }
                operatorStack.push(c);
            } else if (c == '!') {
                output.append("! ");
            }
            i++;
        }

        while (!operatorStack.isEmpty()) {
            output.append(operatorStack.pop()).append(' ');
        }

        return output.toString().trim();
    }

    private double evaluateRPN(String rpn) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = rpn.split("\\s+");

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            try {
                if (token.matches("-?\\d+(\\.\\d+)?")) {
                    stack.push(Double.parseDouble(token));
                } else if (token.equals("!")) {
                    double num = stack.pop();
                    stack.push(factorial(num));
                } else if (token.equals("l")) { // log
                    double num = stack.pop();
                    if (num <= 0) throw new IllegalArgumentException("Логарифм от неположительного числа");
                    stack.push(Math.log(num) / Math.log(2));
                } else if (token.equals("e")) { // exp
                    stack.push(Math.exp(stack.pop()));
                } else {
                    double b = stack.pop();
                    double a = stack.isEmpty() ? 0 : stack.pop(); // Handle unary operators
                    switch (token) {
                        case "+": stack.push(a + b); break;
                        case "-": stack.push(a - b); break;
                        case "*": stack.push(a * b); break;
                        case "/":
                            if (b == 0) throw new IllegalArgumentException("Деление на ноль");
                            stack.push(a / b);
                            break;
                        case "^": stack.push(Math.pow(a, b)); break;
                        default: throw new IllegalArgumentException("Неизвестный оператор: " + token);
                    }
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Ошибка вычисления: " + e.getMessage());
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Ошибка в выражении");
        }
        return stack.pop();
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private int precedence(char op) {
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^': return 3;
            case 'l': case 'e': return 4;
            default: return 0;
        }
    }

    private double factorial(double n) {
        if (n < 0) throw new IllegalArgumentException("Факториал отрицательного числа");
        if (n != (int)n) throw new IllegalArgumentException("Факториал только для целых чисел");
        int num = (int)n;
        if (num > 20) throw new IllegalArgumentException("Факториал слишком большого числа");
        int result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}

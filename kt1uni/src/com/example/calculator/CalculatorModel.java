package com.example.calculator;

import java.util.Stack;

public class CalculatorModel {
    public double calculate(String expression) throws IllegalArgumentException {
        if (!validateBrackets(expression)) {
            throw new IllegalArgumentException("Неверное количество скобок в выражении");
        }
        return evaluateExpression(expression);
    }

    private boolean validateBrackets(String expression) {
        Stack<Character> stack = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private double evaluateExpression(String expression) {
        expression = expression.replaceAll("\\s+", "");
        char[] tokens = expression.toCharArray();

        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') continue;

            // Обработка чисел
            if ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < tokens.length &&
                        ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.' ||
                                tokens[i] == 'E' || tokens[i] == 'e' || tokens[i] == '-')) {
                    sb.append(tokens[i++]);
                }
                values.push(Double.parseDouble(sb.toString()));
                i--;
            }
            // Обработка функций (exp, log, факториал)
            else if (Character.isLetter(tokens[i])) {
                StringBuilder func = new StringBuilder();
                while (i < tokens.length && Character.isLetter(tokens[i])) {
                    func.append(tokens[i++]);
                }

                if (tokens[i] != '(') {
                    throw new IllegalArgumentException("Неверный синтаксис функции " + func);
                }

                // Получаем аргумент функции
                int bracketCount = 1;
                StringBuilder arg = new StringBuilder();
                i++; // Пропускаем открывающую скобку

                while (i < tokens.length && bracketCount > 0) {
                    if (tokens[i] == '(') bracketCount++;
                    if (tokens[i] == ')') bracketCount--;
                    if (bracketCount > 0) {
                        arg.append(tokens[i]);
                    }
                    i++;
                }
                i--; // Возвращаемся на последний символ

                double argument = evaluateExpression(arg.toString());
                double result = applyFunction(func.toString(), argument);
                values.push(result);
            }
            // Обработка скобок
            else if (tokens[i] == '(') {
                operators.push(tokens[i]);
            }
            else if (tokens[i] == ')') {
                while (operators.peek() != '(') {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop();
            }
            // Обработка операторов
            else if (isOperator(tokens[i])) {
                while (!operators.isEmpty() && hasPrecedence(tokens[i], operators.peek())) {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(tokens[i]);
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        if (op1 == '^' && (op2 == '*' || op2 == '/')) return false;
        return true;
    }

    private double applyOperation(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Деление на ноль");
                return a / b;
            case '^': return Math.pow(a, b);
            default: throw new IllegalArgumentException("Неизвестный оператор: " + op);
        }
    }

    private double applyFunction(String func, double arg) {
        switch (func.toLowerCase()) {
            case "exp":
                return Math.exp(arg);
            case "log":
                if (arg <= 0) throw new IllegalArgumentException("Логарифм определен только для положительных чисел");
                return Math.log(arg) / Math.log(2);
            case "fact":
            case "!":
                if (arg < 0 || arg != (int)arg) {
                    throw new IllegalArgumentException("Факториал определен только для целых неотрицательных чисел");
                }
                return factorial((int)arg);
            default:
                throw new IllegalArgumentException("Неизвестная функция: " + func);
        }
    }

    private double factorial(int n) {
        if (n < 0) return 0;
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
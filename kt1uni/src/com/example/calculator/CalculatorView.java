package com.example.calculator;

public class CalculatorView {
    public void displayResult(double result) {
        System.out.printf("Результат: %.4f%n", result);
    }

    public void displayError(String message) {
        System.out.println("Ошибка: " + message);
    }

    public void displayWelcomeMessage() {
        System.out.println("Калькулятор математических выражений");
        System.out.println("Поддерживаемые операции: +, -, *, /, ^ (степень)");
        System.out.println("Поддерживаемые функции: exp(), log(), fact() или !");
        System.out.println("Пример ввода: -3234+((exp(2)+843/log(3234)-4232123)/(34+123+32+5))*3234");
        System.out.println("Введите 'exit' для выхода");
    }

    public void displayInputPrompt() {
        System.out.print("Введите выражение: ");
    }
}
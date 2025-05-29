package calculatormvc.view;

import java.util.Scanner;

public class CalculatorView {
    private Scanner scanner;

    public CalculatorView() {
        scanner = new Scanner(System.in);
    }

    public String getExpression() {
        System.out.println("Введите математическое выражение:");
        return scanner.nextLine();
    }

    public void displayResult(double result) {
        System.out.println("Результат: " + result);
    }

    public void displayError(String message) {
        System.err.println("Ошибка: " + message);
    }

    public boolean askToContinue() {
        System.out.println("Хотите продолжить? (y/n)");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }
}
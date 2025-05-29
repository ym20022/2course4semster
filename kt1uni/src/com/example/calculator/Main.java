package com.example.calculator;

public class Main {

    public static void main(String[] args) {
        com.example.calculator.CalculatorModel model = new com.example.calculator.CalculatorModel();
        com.example.calculator.CalculatorView view = new com.example.calculator.CalculatorView();
        com.example.calculator.CalculatorController controller = new com.example.calculator.CalculatorController(model, view);

        String expression = "3234+((exp(2)+843/log(3234)-4232123)/(34+123+32+5))*3234";
        controller.processExpression(expression);
    }
}

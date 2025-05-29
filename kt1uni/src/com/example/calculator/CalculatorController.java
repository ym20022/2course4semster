package com.example.calculator;

public class CalculatorController {

    private com.example.calculator.CalculatorModel model;
    private com.example.calculator.CalculatorView view;

    public CalculatorController(com.example.calculator.CalculatorModel model, com.example.calculator.CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void processExpression(String expression) {
        try {
            double result = model.calculate(expression);
            view.displayResult(result);
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }
}
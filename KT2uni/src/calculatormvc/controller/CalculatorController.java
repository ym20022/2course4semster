package calculatormvc.controller;

import calculatormvc.model.CalculatorModel;
import calculatormvc.view.CalculatorView;

public class CalculatorController {
    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void process() {
        boolean continueCalculation = true;

        while (continueCalculation) {
            try {
                String expression = view.getExpression();
                double result = model.calculate(expression);
                view.displayResult(result);
            } catch (IllegalArgumentException e) {
                view.displayError(e.getMessage());
            }

            continueCalculation = view.askToContinue();
        }
    }
}
package calculatormvc;

import calculatormvc.model.CalculatorModel;

public class CalculatorApp {
    public static void main(String[] args) {
        CalculatorModel model = new CalculatorModel();
        try {
            double result = model.calculate("-3234+((exp(2)*843/log(3234)-4232123)/(34+123+32+5))*3234");
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
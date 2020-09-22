

import java.text.DecimalFormat;

/**
 *
 * @author Shmuel
 */
enum LogicOperators {

    PLUS, MINUS, DIVIDE, TIMES, PERCENTAGE, RECIPROCAL, EXPONENT, SQUAREROOT, SQUARED, NULL;
}

public class GuiCalcLogic {

    private double num1;
    private double num2;

    private StringBuilder strNum1;
    private StringBuilder strNum2;

    private StringBuilder equationDisplay;
    private StringBuilder currentNumber;

    boolean isFirstNumber;
    boolean equalsCalled;
    boolean calledFromEquals;

    private double memory;

    boolean isNegative;

    LogicOperators logicOps;

    public GuiCalcLogic() {
        equationDisplay = new StringBuilder();
        currentNumber = new StringBuilder();
        isFirstNumber = true;
        strNum1 = new StringBuilder();
        strNum2 = new StringBuilder();
        logicOps = LogicOperators.NULL;
    }

    public void addEquationDisplay(String str) {
        equationDisplay.append(str);
    }

    public void addCurrentNumDisplay(String str) {
        currentNumber.append(str);
    }

    public String getEquationDisplay() {
        return equationDisplay.toString();
    }

    public String getCurrentNumDisplay() {
        return currentNumber.toString();
    }

    public void setStrNum1(String input) {
        strNum1.append(input);
        num1 = Double.parseDouble(strNum1.toString());
    }

    public void setStrNum2(String input) {
        if (strNum2.length() == 0) {
            clearEntry();
        }
        strNum2.append(input);
        num2 = Double.parseDouble(strNum2.toString());
    }

    public void parseInput(String input) {
       
        if (input.matches("\\d")) {
            numberParse(input);
            return;
        }
        switch (input) {
            case "MC":
                memoryClear();
                break;
            case "MR":
                memoryRecall();
                break;
            case "MS":
                memorySave();
                break;
            case "M+":
                memoryAdd();
                break;
            case "M-":
                memorySubtract();
                break;
            case "<--":
                backSpace();
                break;
            case "CE":
                clearEntry();
                break;
            case "C":
                clear();
                break;
            case "\u00B1":
                negate();
                break;
            case "Sqr":
                squareRoot();
                break;
            case "/":
                devide();
                break;
            case "\u0025":
                percentage();
                break;
            case "\u002A":
                times();
                break;
            case "1/x":
                reciprocal();
                break;
            case "-":
                minus();
                break;
            case "=":
                calledFromEquals = true;
                equalsM();
                break;
            case ".":
                decimal();
                break;
            case "+":
                plus();
                break;

        }

    }

    private void numberParse(String input) {
        if (equalsCalled) {
            clearEntry();
            equalsCalled = false;
        }
        if (isFirstNumber) {
            setStrNum1(input);
        } else {
            setStrNum2(input);
        }

        addCurrentNumDisplay(input);
    }

    //Memory
    //M+

    public void memoryAdd() {
        memory = memory + Double.parseDouble(currentNumber.toString());
    }

    //M-

    public void memorySubtract() {
        memory = (double) memory - Double.parseDouble(currentNumber.toString());
    }

    private void memoryClear() {
        memory = 0.0;
    }

    private void memoryRecall() {
        clear();
        if (memory == 0) {
            return;
        }
        numberParse(doubleToString(memory));

    }

    private void memorySave() {
        memory = Double.parseDouble(currentNumber.toString());
    }

    private void backSpace() {
        if (currentNumber.length() < 1) {
            return;
        }
        currentNumber.deleteCharAt(currentNumber.length() - 1);
        if (isFirstNumber) {
            strNum1 = new StringBuilder(currentNumber);
            num1 = currentNumber.length() == 0 ? 0
                    : Double.parseDouble(strNum1.toString());
            GuiCalc.setPanel1(strNum1.toString());
        } else {
            strNum2 = new StringBuilder(currentNumber);
            num2 = currentNumber.length() == 0 ? 0
                    : Double.parseDouble(strNum2.toString());
            GuiCalc.setPanel1(strNum2.toString());
        }

    }

    private void clearEntry() {
        currentNumber = new StringBuilder();
        GuiCalc.setPanel1(currentNumber.toString());
    }

    private void negate() {
        if (currentNumber.charAt(0) == '0') {
            return;
        }
        if (isFirstNumber) {
            num1 *= -1;
            currentNumber = new StringBuilder(doubleToString(num1));
        }else{
            num2 *= -1;
            currentNumber = new StringBuilder(doubleToString(num2));
        }
    }

    private void clear() {
        num1 = 0;
        num2 = 0;
        strNum1 = new StringBuilder();
        strNum2 = new StringBuilder();
        equationDisplay = new StringBuilder();
        currentNumber = new StringBuilder();
        logicOps = LogicOperators.NULL;
        
        isFirstNumber = true;
        isNegative = false;
        
        GuiCalc.setPanel(null);
        GuiCalc.setPanel1(null);
        

    }

    private void squareRoot() {
        if (isFirstNumber) {
            equationDisplay = new StringBuilder("sqrt (" + doubleToString(num1) + ")");
            num1 = Math.sqrt(Double.parseDouble(currentNumber.toString()));
            currentNumber = new StringBuilder(doubleToString(num1));
        } else {
            addEquationDisplay("sqrt (" + doubleToString(num2) + ")");
            num2 = Math.sqrt(num2);
            clearEntry();
            addCurrentNumDisplay(doubleToString(num2));
        }
    }

    private void devide() {
        operateOperands("/");
        logicOps = LogicOperators.DIVIDE;
    }

    private void percentage() {
        operateOperands("\u0025");
        if (isFirstNumber) {
            clear();
        } else {
            num2 *= .1;
            logicOps = LogicOperators.PERCENTAGE;
        }
    }

    private void times() {
        operateOperands("*");
        logicOps = LogicOperators.TIMES;
    }

    private void reciprocal() {
        if (isFirstNumber) {
            addEquationDisplay("recip (" + doubleToString(num1) +")");
            num1 = 1/num1;
            currentNumber = new StringBuilder(doubleToString(num1));
        }else{
            addEquationDisplay("recip (" + doubleToString(num2) +")");
            num2 = 1/num2;
           currentNumber = new StringBuilder(doubleToString(num2));
        }
    }

    private void minus() {
        operateOperands("-");
        logicOps = LogicOperators.MINUS;
    }

    private void equalsM() {
        equalsCalled = true;
        clearEntry();

        switch (logicOps) {
            case PLUS:
                addCurrentNumDisplay(doubleToString(num1 + num2));
                num1 += num2;
                break;
            case MINUS:
                addCurrentNumDisplay(doubleToString(num1 - num2));
                num1 -= num2;
                break;
            case DIVIDE:
                addCurrentNumDisplay(doubleToString(num1 / num2));
                num1 /= num2;
                break;
            case TIMES:
                addCurrentNumDisplay(doubleToString(num1 * num2));
                num1 *= num2;
                break;
            //method percentage will num2*.1 and then call equals
            case PERCENTAGE:
                addCurrentNumDisplay(doubleToString(num1));
                break;
                

            default:
                throw new IllegalArgumentException("No Proper Logic Operator was called");
        }
        if (calledFromEquals) {
            double storeNum = num1;
            clear();
            addCurrentNumDisplay(doubleToString(storeNum));
            numberParse(doubleToString(storeNum));
            calledFromEquals = false;
        }
        num2 = 0;

    }

    private void decimal() {
        numberParse(".");
    }

    private void plus() {
        operateOperands("+");
        logicOps = LogicOperators.PLUS;
    }

    public String doubleToString(double dts) {
        if (dts == 0) {
            return "";
        }
        if (dts % 1 == 0) {
            Double storeD = dts;
            int i = storeD.intValue();
            return i + "";
        }
        DecimalFormat numbFormat = new DecimalFormat("#0.#####");
        return numbFormat.format(dts);
    }

    public void operateOperands(String operand) {
        if (isFirstNumber) {
            addEquationDisplay(doubleToString(num1) + " " + operand + " ");
            isFirstNumber = false;

        } else {
            addEquationDisplay(doubleToString(num2) + " " + operand + " ");
            equalsM();
            num2 = 0.0;
            strNum2 = new StringBuilder();
        }
    }

}

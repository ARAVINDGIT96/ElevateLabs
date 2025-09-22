import java.util.Scanner;

public class ConsoleCalculator {
    
    public static double performAddition(double num1, double num2) {
        return num1 + num2;
    }
    
    public static double performSubtraction(double num1, double num2) {
        return num1 - num2;
    }
    
    public static double performMultiplication(double num1, double num2) {
        return num1 * num2;
    }
    
    public static double performDivision(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Division by zero is not allowed!");
        }
        return num1 / num2;
    }
    
    public static void displayMenu() {
        System.out.println("\n=== CONSOLE CALCULATOR ===");
        System.out.println("Select an operation:");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.println("5. Exit");
        System.out.print("Enter your choice (1-5): ");
    }
    
    public static double getNumberInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input! Please enter a valid number.");
            System.out.print(prompt);
            scanner.next();
        }
        return scanner.nextDouble();
    }
    
    public static void executeCalculation(int choice, Scanner scanner) {
        double firstNumber = getNumberInput(scanner, "Enter first number: ");
        double secondNumber = getNumberInput(scanner, "Enter second number: ");
        double result = 0;
        String operation = "";
        
        try {
            switch (choice) {
                case 1:
                    result = performAddition(firstNumber, secondNumber);
                    operation = "Addition";
                    break;
                case 2:
                    result = performSubtraction(firstNumber, secondNumber);
                    operation = "Subtraction";
                    break;
                case 3:
                    result = performMultiplication(firstNumber, secondNumber);
                    operation = "Multiplication";
                    break;
                case 4:
                    result = performDivision(firstNumber, secondNumber);
                    operation = "Division";
                    break;
                default:
                    System.out.println("Invalid operation selected!");
                    return;
            }
            
            System.out.println("\n--- RESULT ---");
            System.out.printf("%s: %.2f %s %.2f = %.2f%n", 
                operation, firstNumber, getOperatorSymbol(choice), secondNumber, result);
            
        } catch (ArithmeticException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
    
    public static String getOperatorSymbol(int choice) {
        switch (choice) {
            case 1: return "+";
            case 2: return "-";
            case 3: return "*";
            case 4: return "/";
            default: return "?";
        }
    }
    
    public static boolean askToContinue(Scanner scanner) {
        System.out.print("\nDo you want to perform another calculation? (y/n): ");
        String response = scanner.next().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }
    
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        boolean continueCalculating = true;
        
        System.out.println("Welcome to the Console Calculator!");
        System.out.println("This calculator supports basic arithmetic operations.");
        
        while (continueCalculating) {
            displayMenu();
            
            while (!inputScanner.hasNextInt()) {
                System.out.println("Please enter a valid number (1-5)!");
                System.out.print("Enter your choice: ");
                inputScanner.next();
            }
            
            int userChoice = inputScanner.nextInt();
            
            if (userChoice == 5) {
                System.out.println("\nThank you for using the Console Calculator!");
                System.out.println("Goodbye!");
                continueCalculating = false;
            } else if (userChoice >= 1 && userChoice <= 4) {
                executeCalculation(userChoice, inputScanner);
                continueCalculating = askToContinue(inputScanner);
            } else {
                System.out.println("\nInvalid choice! Please select a number between 1-5.");
            }
        }
        
        inputScanner.close();
    }
}
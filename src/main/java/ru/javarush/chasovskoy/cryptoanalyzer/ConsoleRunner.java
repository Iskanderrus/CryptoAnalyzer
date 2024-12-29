package ru.javarush.chasovskoy.cryptoanalyzer;

import ru.javarush.chasovskoy.cryptoanalyzer.entity.Result;

import java.util.Scanner;

public class ConsoleRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Application application = new Application();

        System.out.println("Welcome to the CryptoAnalyzer Console Application!");
        System.out.println("Available operations:");
        System.out.println("1. Encode");
        System.out.println("2. Decode");
        System.out.println("3. Brute Force");

        // Select operation
        int choice;
        while (true) {
            System.out.print("Enter your choice (1, 2, or 3): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        String operation = switch (choice) {
            case 1 -> "encode";
            case 2 -> "decode";
            case 3 -> "bruteforce";
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };

        String inputFile, outputFile = null, key = null, sampleTextFile = null;

        // Inputs and Outputs
        System.out.print("Enter the input file path: ");
        inputFile = scanner.nextLine().trim();

        if (!operation.equals("bruteforce")) {
            System.out.print("Enter the output file path: ");
            outputFile = scanner.nextLine().trim();

            while (true) {
                System.out.print("Enter the key (integer): ");
                key = scanner.nextLine().trim();
                try {
                    Integer.parseInt(key);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid key. Please enter a valid integer.");
                }
            }
        } else {
            System.out.print("Enter the sample text file path: ");
            sampleTextFile = scanner.nextLine().trim();
        }

        // Combine args into an array
        String[] commandArgs = switch (operation) {
            case "encode", "decode" -> new String[]{operation, inputFile, outputFile, key};
            case "bruteforce" -> new String[]{operation, inputFile, sampleTextFile};
            default -> throw new IllegalStateException("Unexpected operation: " + operation);
        };

        // Run application
        Result result = application.run(commandArgs);
        System.out.println("Operation Result: " + result);
    }
}

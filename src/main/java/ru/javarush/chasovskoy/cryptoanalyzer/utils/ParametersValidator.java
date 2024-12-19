package ru.javarush.chasovskoy.cryptoanalyzer.utils;

import ru.javarush.chasovskoy.cryptoanalyzer.constants.Constants;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ParametersValidator {

    /**
     * Validates the input parameters for the encoder/decoder and creates paths.
     *
     * @param parameters Array of parameters.
     * @return Validation result with success or error messages.
     */
    public static ValidationResult validate(String[] parameters) {
        if (parameters.length < 3) {
            return ValidationResult.error("Insufficient parameters. Expected: [outputFile, inputFile, shift]");
        }

        String inputFile = parameters[0];
        String outputFile = parameters[1];
        String shiftValue = parameters[2];

        // Validate file extensions
        if (!outputFile.endsWith(".txt")) {
            return ValidationResult.error("Invalid output file format. Must be a .txt file: " + outputFile);
        }
        if (!inputFile.endsWith(".txt")) {
            return ValidationResult.error("Invalid input file format. Must be a .txt file: " + inputFile);
        }

        // Validate shift value
        try {
            Integer.parseInt(shiftValue);
        } catch (NumberFormatException e) {
            return ValidationResult.error("Invalid shift value. Must be an integer: " + shiftValue);
        }

        // Paths creation logic moved here
        Path inputFilePath = Paths.get(Constants.TXT_FOLDER, inputFile);
        Path outputFilePath = Paths.get(Constants.TXT_FOLDER, outputFile);

        return ValidationResult.success(inputFilePath, outputFilePath, Integer.parseInt(shiftValue));
    }

    /**
     * A simple wrapper to encapsulate validation results.
     */
    public static class ValidationResult {
        private final boolean valid;
        private final String errorMessage;
        private final Path inputFilePath;
        private final Path outputFilePath;
        private final int shift;

        private ValidationResult(boolean valid, String errorMessage, Path inputFilePath, Path outputFilePath, int shift) {
            this.valid = valid;
            this.errorMessage = errorMessage;
            this.inputFilePath = inputFilePath;
            this.outputFilePath = outputFilePath;
            this.shift = shift;
        }

        public static ValidationResult success(Path inputFilePath, Path outputFilePath, int shift) {
            return new ValidationResult(true, null, inputFilePath, outputFilePath, shift);
        }

        public static ValidationResult error(String errorMessage) {
            return new ValidationResult(false, errorMessage, null, null, 0);
        }

        public boolean isValid() {
            return valid;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public Path getInputFilePath() {
            return inputFilePath;
        }

        public Path getOutputFilePath() {
            return outputFilePath;
        }

        public int getShift() {
            return shift;
        }
    }
}

package ru.javarush.chasovskoy.cryptoanalyzer.utils;

import ru.javarush.chasovskoy.cryptoanalyzer.constants.Constants;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.Result;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.ResultCode;

import java.nio.file.Path;

public class ParametersValidator {

    public static ValidationResult validateParameters(String[] parameters) {
        if (parameters.length != 3) {
            return new ValidationResult(
                    new Result("Invalid number of parameters. Expected: [outputFile.txt, inputFile.txt, shift]", ResultCode.ERROR),
                    null,
                    null,
                    0
            );
        }

        String outputFile = parameters[0];
        String inputFile = parameters[1];
        String shiftValue = parameters[2];

        if (!outputFile.endsWith(".txt") || !inputFile.endsWith(".txt")) {
            return new ValidationResult(
                    new Result("File names must end with '.txt'.", ResultCode.ERROR),
                    null,
                    null,
                    0
            );
        }

        Path inputPath = Path.of(Constants.TXT_FOLDER, inputFile);
        Path outputPath = Path.of(Constants.TXT_FOLDER, outputFile);

        int shift;
        try {
            shift = Integer.parseInt(shiftValue);
        } catch (NumberFormatException e) {
            return new ValidationResult(
                    new Result("Invalid shift value. Must be an integer.", ResultCode.ERROR),
                    null,
                    null,
                    0
            );
        }

        return new ValidationResult(null, inputPath, outputPath, shift);
    }

    public record ValidationResult(Result error, Path inputPath, Path outputPath, int shift) {

        public boolean hasError() {
                return error != null;
            }
        }
}

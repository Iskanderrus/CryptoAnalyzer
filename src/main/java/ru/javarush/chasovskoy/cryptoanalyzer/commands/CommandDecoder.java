package ru.javarush.chasovskoy.cryptoanalyzer.commands;

import ru.javarush.chasovskoy.cryptoanalyzer.entity.Result;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.ResultCode;
import ru.javarush.chasovskoy.cryptoanalyzer.utils.FileProcessor;
import ru.javarush.chasovskoy.cryptoanalyzer.utils.ParametersValidator;

import java.nio.file.Path;

public class CommandDecoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        // Validate parameters
        ParametersValidator.ValidationResult validationResult = ParametersValidator.validate(parameters);
        if (!validationResult.isValid()) {
            return new Result(validationResult.getErrorMessage(), ResultCode.ERROR);
        }
        Path inputFilePath = validationResult.getInputFilePath();
        Path outputFilePath = validationResult.getOutputFilePath();
        int shift = -validationResult.getShift();

        // Process the file (encoding logic)
        FileProcessor.processFile(inputFilePath, outputFilePath, shift);

        return new Result("Encoding completed successfully. Output written to: " + outputFilePath, ResultCode.OK);
    }
}

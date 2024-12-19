package ru.javarush.chasovskoy.cryptoanalyzer.commands;

import ru.javarush.chasovskoy.cryptoanalyzer.constants.Constants;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.Result;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.ResultCode;
import ru.javarush.chasovskoy.cryptoanalyzer.exceptions.AppException;
import ru.javarush.chasovskoy.cryptoanalyzer.utils.CommandCharShifter;
import ru.javarush.chasovskoy.cryptoanalyzer.utils.ParametersValidator;

import java.io.*;
import java.nio.file.Path;

public class CommandEncoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        // Validate parameters
        ParametersValidator.ValidationResult validationResult = ParametersValidator.validate(parameters);
        if (!validationResult.isValid()) {
            return new Result(validationResult.getErrorMessage(), ResultCode.ERROR);
        }

        Path textPath = validationResult.getInputFilePath();
        Path encodedPath = validationResult.getOutputFilePath();
        int shift = validationResult.getShift();

        // Ensure the output directory exists
        File outputDir = encodedPath.getParent().toFile();
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(textPath.toString()));
             BufferedWriter writer = new BufferedWriter(new FileWriter(encodedPath.toString()))) {

            char[] buffer = new char[Constants.CHUNK_SIZE];
            int bytesRead;

            while ((bytesRead = reader.read(buffer)) != -1) {
                char[] encodedBuffer = new char[bytesRead];
                for (int i = 0; i < bytesRead; i++) {
                    encodedBuffer[i] = CommandCharShifter.shiftCharacter(buffer[i], shift);
                }
                writer.write(encodedBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new AppException("Error accessing file: " + textPath, e);
        }

        return new Result("Encoding completed successfully. Output written to: " + encodedPath, ResultCode.OK);
    }

}

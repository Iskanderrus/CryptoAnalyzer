package ru.javarush.chasovskoy.cryptoanalyzer.commands;

import ru.javarush.chasovskoy.cryptoanalyzer.constants.Constants;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.Result;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.ResultCode;
import ru.javarush.chasovskoy.cryptoanalyzer.exceptions.AppException;
import ru.javarush.chasovskoy.cryptoanalyzer.utils.CommandCharShifter;
import ru.javarush.chasovskoy.cryptoanalyzer.utils.ParametersValidator;

import java.io.*;
import java.nio.file.Path;

public class CommandDecoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        // Validate parameters
        ParametersValidator.ValidationResult validationResult = ParametersValidator.validate(parameters);
        if (!validationResult.isValid()) {
            return new Result(validationResult.getErrorMessage(), ResultCode.ERROR);
        }

        Path textPath = validationResult.getInputFilePath();
        Path decodedPath = validationResult.getOutputFilePath();
        int shift = -validationResult.getShift();


        try (BufferedReader reader = new BufferedReader(new FileReader(textPath.toString()));
             BufferedWriter writer = new BufferedWriter(new FileWriter(decodedPath.toString()))) {

            char[] buffer = new char[Constants.CHUNK_SIZE];
            int bytesRead;

            while ((bytesRead = reader.read(buffer)) != -1) {
                // Transform the read characters using Caesar cipher
                char[] decodedBuffer = new char[bytesRead];
                for (int i = 0; i < bytesRead; i++) {
                    decodedBuffer[i] = CommandCharShifter.shiftCharacter(buffer[i], shift);
                }
                // Write transformed characters to the output file
                writer.write(decodedBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new AppException("Error accessing file: " + textPath, e);
        }

        return new Result("Encoding completed successfully. Output written to: " + decodedPath, ResultCode.OK);
    }
}

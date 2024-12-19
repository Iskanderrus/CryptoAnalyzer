package ru.javarush.chasovskoy.cryptoanalyzer.commands;

import ru.javarush.chasovskoy.cryptoanalyzer.constants.Constants;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.Result;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.ResultCode;
import ru.javarush.chasovskoy.cryptoanalyzer.exceptions.AppException;
import ru.javarush.chasovskoy.cryptoanalyzer.utils.CommandCharShifter;

import java.io.*;
import java.nio.file.Path;


public class CommandEncoder implements Action{
    @Override
    public Result execute(String[] parameters) {
        if (parameters.length < 3) {
            return new Result("Insufficient parameters. Expected: [inputFile, outputFile, shift]", ResultCode.ERROR);
        }

        Path textPath = Path.of(Constants.TXT_FOLDER, parameters[0]);
        Path encodedPath = Path.of(Constants.TXT_FOLDER, parameters[1]);
        int shift;

        try {
            shift = Integer.parseInt(parameters[2]);
        } catch (NumberFormatException e) {
            return new Result("Invalid shift value. Must be an integer.", ResultCode.ERROR);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(textPath.toString()));
             BufferedWriter writer = new BufferedWriter(new FileWriter(encodedPath.toString()))) {

            char[] buffer = new char[Constants.CHUNK_SIZE];
            int bytesRead;

            while ((bytesRead = reader.read(buffer)) != -1) {
                // Transform the read characters using Caesar cipher
                char[] encodedBuffer = new char[bytesRead];
                for (int i = 0; i < bytesRead; i++) {
                    encodedBuffer[i] = CommandCharShifter.shiftCharacter(buffer[i], shift);
                }
                // Write transformed characters to the output file
                writer.write(encodedBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new AppException("Error accessing file: " + textPath, e);
        }

        return new Result("Encoding completed successfully. Output written to: " + encodedPath, ResultCode.OK);
    }
}

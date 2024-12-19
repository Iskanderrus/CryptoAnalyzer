package ru.javarush.chasovskoy.cryptoanalyzer.utils;

import ru.javarush.chasovskoy.cryptoanalyzer.exceptions.AppException;
import ru.javarush.chasovskoy.cryptoanalyzer.constants.Constants;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileProcessor {

    public static void processFile(Path inputFilePath, Path outputFilePath, int shift) {
        // Ensure the output directory exists
        File outputDir = outputFilePath.getParent().toFile();
        if (!outputDir.exists()) {
            boolean created = outputDir.mkdirs();
            if (!created) {
                // Handle the error, log it, or throw an exception if necessary
                throw new AppException("Failed to create output directory: " + outputDir);
            }
        }

        try (BufferedReader reader = Files.newBufferedReader(inputFilePath);
             BufferedWriter writer = Files.newBufferedWriter(outputFilePath)) {

            char[] buffer = new char[Constants.CHUNK_SIZE];
            int bytesRead;

            while ((bytesRead = reader.read(buffer)) != -1) {
                // Apply the shift to characters
                char[] shiftedBuffer = new char[bytesRead];
                for (int i = 0; i < bytesRead; i++) {
                    shiftedBuffer[i] = CommandCharShifter.shiftCharacter(buffer[i], shift);
                }
                // Write the shifted characters to the output file
                writer.write(shiftedBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new AppException("Error processing file: " + inputFilePath, e);
        }
    }
}

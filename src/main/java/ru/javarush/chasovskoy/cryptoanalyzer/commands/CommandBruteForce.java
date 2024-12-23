package ru.javarush.chasovskoy.cryptoanalyzer.commands;

import ru.javarush.chasovskoy.cryptoanalyzer.constants.Constants;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.Result;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.ResultCode;
import ru.javarush.chasovskoy.cryptoanalyzer.exceptions.AppException;
import ru.javarush.chasovskoy.cryptoanalyzer.utils.FileProcessor;
import ru.javarush.chasovskoy.cryptoanalyzer.utils.TextTokenizer;
import ru.javarush.chasovskoy.cryptoanalyzer.utils.WordFrequencyAnalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandBruteForce implements Action {
    @Override
    public Result execute(String[] parameters) {
        if (parameters.length != 2) {
            return new Result("Invalid parameters. Provide encoded_text.txt and sample_text.txt.", ResultCode.ERROR);
        }
        Path encodedFilePath = Paths.get(Constants.TXT_FOLDER, parameters[0]);
        Path sampleFilePath = Paths.get(Constants.TXT_FOLDER, parameters[1]);
        Path bruteForceOutputPath = Path.of(Constants.TXT_FOLDER, "brutforce.txt");

        Path tempOutputPath = Path.of(Constants.TXT_FOLDER, "temp_decoded.txt"); // Declare tempOutputPath outside the loop

        try {
            // Ensure the directory for bruteForceOutputPath exists
            FileProcessor.directoryCheck(bruteForceOutputPath);

            // Step 1: Tokenize sample text
            List<String> sampleTokens = TextTokenizer.tokenize(sampleFilePath);

            // Step 2: Analyze word frequencies, define top 10 words with length 3, 4, 5 and 6 letters
            Map<Integer, List<String>> topWordsByLength = new HashMap<>();
            for (int wordLength = 3; wordLength <= 6; wordLength++) {
                topWordsByLength.put(wordLength, WordFrequencyAnalyzer.getTopWords(sampleTokens, wordLength, 10));
            }

            // Count all values in the topWordsByLength map
            int totalWordCount = 0;
            for (List<String> words : topWordsByLength.values()) {
                totalWordCount += words.size();
            }


            // Step 3: Decode in a loop
            int bestShift = 0;
            int bestMatchCount = 0;

            for (int shift = 1; shift <= Constants.ALPHABET.length(); shift++) {
                FileProcessor.processFile(encodedFilePath, tempOutputPath, -shift);

                // Read decoded content
                String decodedContent = Files.readString(tempOutputPath);

                // Step 4: Check similarity
                int matchCount = 0;
                for (int length = 3; length <= 6; length++) {
                    for (String word : topWordsByLength.get(length)) {
                        if (decodedContent.contains(word)) {
                            matchCount++;
                        }
                    }
                }

                // Step 5: Save best result
                if (matchCount > bestMatchCount) {
                    bestMatchCount = matchCount;
                    bestShift = shift;

                    if (bestMatchCount > totalWordCount/2) { // Over 50% of the 40 possible words matched
                        FileProcessor.processFile(encodedFilePath, bruteForceOutputPath, -bestShift);
                        cleanupTempFile(tempOutputPath); // Cleanup temp file
                        System.out.println("Shift code is: " + bestShift);
                        return new Result("Brute force complete." +
                                "\nDecoded text saved to: " + bruteForceOutputPath, ResultCode.OK);
                    }
                }
            }

            // Save the best result
            FileProcessor.processFile(encodedFilePath, bruteForceOutputPath, -bestShift);
            // Cleanup temporary file
            cleanupTempFile(tempOutputPath);
            return new Result("Brute force complete." +
                    "\nDecoded text saved to: " + bruteForceOutputPath, ResultCode.OK);

        } catch (Exception e) {
            throw new AppException("Error processing file: " + tempOutputPath, e);
        }
    }

    // Method to delete the temporary file
    private void cleanupTempFile(Path tempFilePath) {
        try {
            if (Files.exists(tempFilePath)) {
                Files.delete(tempFilePath);
            }
        } catch (IOException e) {
            throw new AppException("Error processing file: " + tempFilePath, e);
        }
    }
}

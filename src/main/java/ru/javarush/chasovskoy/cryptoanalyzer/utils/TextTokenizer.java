package ru.javarush.chasovskoy.cryptoanalyzer.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class TextTokenizer {
    // exclude punctuation and numbers
    private static final Pattern WORD_PATTERN = Pattern.compile("[а-яА-Яa-zA-Z]+");

    public static ArrayList<String> tokenize(Path filePath) {
        ArrayList<String> words = new ArrayList<>();
        try {
            String content = Files.readString(filePath).toLowerCase();
            String[] tokens = content.split("\\s+");
            for (String token : tokens) {
                if (WORD_PATTERN.matcher(token).matches()) {
                    words.add(token);
                }
            }

            // USED FOR DEBUG: Save the tokens to dict_tokens.txt
            //saveTokensToFile(words, Path.of("texts", "dict_tokens.txt"));

        } catch (IOException e) {
            throw new RuntimeException("Error reading sample text file: " + filePath, e);
        }
        return words;
    }

    private static void saveTokensToFile(ArrayList<String> tokens, Path outputPath) {
        try {
            // Ensure the output directory exists
            Files.createDirectories(outputPath.getParent());

            // Write tokens to file, one token per line
            Files.write(outputPath, tokens);
            System.out.println("Tokens saved to: " + outputPath);
        } catch (IOException e) {
            throw new RuntimeException("Error saving tokens to file: " + outputPath, e);
        }
    }
}

package ru.javarush.chasovskoy.cryptoanalyzer.utils;

import java.util.*;

public class WordFrequencyAnalyzer {

    public static List<String> getTopWords(List<String> tokens, int wordLength, int topCount) {
        HashMap<String, Integer> wordFrequency = new HashMap<>();

        // Count frequencies of words with the specified length
        for (String word : tokens) {
            if (word.length() == wordLength) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        // Create a list of entries and sort it
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(wordFrequency.entrySet());
        for (int i = 0; i < entries.size() - 1; i++) {
            for (int j = i + 1; j < entries.size(); j++) {
                if (entries.get(i).getValue() < entries.get(j).getValue()) {
                    Map.Entry<String, Integer> temp = entries.get(i);
                    entries.set(i, entries.get(j));
                    entries.set(j, temp);
                }
            }
        }

        // Collect the top words
        List<String> sortedWords = new ArrayList<>();
        for (int i = 0; i < Math.min(topCount, entries.size()); i++) {
            sortedWords.add(entries.get(i).getKey());
        }

        return sortedWords;
    }
}


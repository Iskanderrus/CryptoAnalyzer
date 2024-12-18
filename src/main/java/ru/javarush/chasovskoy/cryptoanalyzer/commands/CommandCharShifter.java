package ru.javarush.chasovskoy.cryptoanalyzer.commands;

import ru.javarush.chasovskoy.cryptoanalyzer.constants.Constants;


public class CommandCharShifter {
    /**
     * Shifts a character by the specified number of positions within the given alphabet.
     * If the character is not in the alphabet, it is returned unchanged.
     *
     * @param ch    the character to shift
     * @param shift the number of positions to shift
     * @return the shifted character or the original character if not in the alphabet
     */

    static char shiftCharacter(char ch, int shift) {
        String alphabet = Constants.ALPHABET;
        // Find the index of the character in alphabet
        int index = alphabet.indexOf(ch);
        if (index != -1) {
            // Calculate the new index with wrapping (if shift is over the alphabet length,
            // we make a turn and start from the beginning)
            int newIndex = (index + shift) % alphabet.length();
            if (newIndex < 0) {
                newIndex += alphabet.length(); // Ensure positive index for negative shifts
            }
            return alphabet.charAt(newIndex);
        }
        // Return the character unchanged if it's not in the custom alphabet (whitespace for example)
        return ch;
    }

}

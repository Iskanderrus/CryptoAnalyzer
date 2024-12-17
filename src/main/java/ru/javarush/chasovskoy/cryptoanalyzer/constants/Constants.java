package ru.javarush.chasovskoy.cryptoanalyzer.constants;

import java.io.File;

public class Constants {
    private static final String rus = "ЁЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ";
    private static final String eng = "QWERTYUIOPASDFGHJKLZXCVBNM";
    private static final String numbers = "0123456789";
    private static final String punctuation = "~!@#$%^&*()_-+={}[];:'\"/?.>,<\\";
    public static final String ALPHABET = rus + rus.toLowerCase() + eng + eng.toLowerCase() + numbers + punctuation;

    public static final String TXT_FOLDER = System.getProperty("user.dir") + File.separator + "texts" + File.separator;
}
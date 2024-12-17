package ru.javarush.chasovskoy.cryptoanalyzer;

import ru.javarush.chasovskoy.cryptoanalyzer.entity.Result;

import java.util.Arrays;

public class ConsoleRunner {
    public static void main(String[] args) {

        int key = 15;

        System.out.println(Arrays.toString(args));
        Application application = new Application();
        Result result = application.run(args);
        System.out.println(result);
    }
}
package ru.javarush.chasovskoy.cryptoanalyzer.controllers;

import ru.javarush.chasovskoy.cryptoanalyzer.commands.CommandBruteForce;
import ru.javarush.chasovskoy.cryptoanalyzer.commands.CommandDecoder;
import ru.javarush.chasovskoy.cryptoanalyzer.commands.CommandEncoder;
import ru.javarush.chasovskoy.cryptoanalyzer.commands.Action;
import ru.javarush.chasovskoy.cryptoanalyzer.exceptions.AppException;

public enum Actions {
    ENCODE(new CommandEncoder()),
    DECODE(new CommandDecoder()),
    BRUTEFORCE(new CommandBruteForce());

    private final Action action;

    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String actionName) {
        try {
            Actions value = Actions.valueOf(actionName.toUpperCase());
            return value.action;
        } catch (IllegalArgumentException e) {
            throw new AppException("not found" + actionName, e);
        }
    }
}

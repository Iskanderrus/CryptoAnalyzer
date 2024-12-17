package ru.javarush.chasovskoy.cryptoanalyzer.commands;

import ru.javarush.chasovskoy.cryptoanalyzer.entity.Result;

public interface Action {
    Result execute(String[] parameters);

}

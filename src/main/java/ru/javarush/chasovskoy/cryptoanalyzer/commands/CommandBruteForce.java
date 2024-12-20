package ru.javarush.chasovskoy.cryptoanalyzer.commands;

import ru.javarush.chasovskoy.cryptoanalyzer.entity.Result;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.ResultCode;

public class CommandBruteForce implements Action {
    @Override
    public Result execute(String[] parameters) {
        return new Result("BrutForce complete", ResultCode.OK);
    }
}

package ru.javarush.chasovskoy.cryptoanalyzer.commands;

import ru.javarush.chasovskoy.cryptoanalyzer.entity.Result;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.ResultCode;

public class CommandBruteForce implements Action {
    // runs decoding in a cycle from 0 to ALPHABET length
    // on each iteration check and remember similarity
    // choose the best similarity and save file with text decoded with this shift

    @Override
    public Result execute(String[] parameters) {
        return new Result("BrutForce complete", ResultCode.OK);
    }
}

package ru.javarush.chasovskoy.cryptoanalyzer.commands;

import ru.javarush.chasovskoy.cryptoanalyzer.entity.Result;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.ResultCode;

public class CommandDecoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        return new Result("decode all right", ResultCode.OK);
    }
}

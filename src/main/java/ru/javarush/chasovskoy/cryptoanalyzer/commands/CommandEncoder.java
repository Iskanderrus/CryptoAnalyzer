package ru.javarush.chasovskoy.cryptoanalyzer.commands;

import ru.javarush.chasovskoy.cryptoanalyzer.entity.Result;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.ResultCode;

public class CommandEncoder implements Action{
    @Override
    public Result execute(String[] parameters) {
        return new Result("encode all right", ResultCode.OK);
    }
}
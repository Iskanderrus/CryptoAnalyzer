package ru.javarush.chasovskoy.cryptoanalyzer.controllers;

import ru.javarush.chasovskoy.cryptoanalyzer.commands.Action;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.Result;

public class MainController {
    public Result doAction(String actionName, String[] params){
        //actionName == encode
        //parameters == [text.txt, encode.txt, 12]

        Action action = Actions.find(actionName);
        return action.execute(params);
    }
}

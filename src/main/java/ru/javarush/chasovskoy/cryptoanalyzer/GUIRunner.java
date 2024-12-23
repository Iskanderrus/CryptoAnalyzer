package ru.javarush.chasovskoy.cryptoanalyzer;

import ru.javarush.chasovskoy.cryptoanalyzer.entity.Result;

import javax.swing.*;


public class GUIRunner {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setTitle("Caesar Cipher - JavaRush - Alexander Chasovskoy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(900, 600);
        frame.setVisible(true);
//        Application application = new Application();
//        Result result = application.run(args);
//        System.out.println(result);
    }
}
package ru.javarush.chasovskoy.cryptoanalyzer.gui;

import ru.javarush.chasovskoy.cryptoanalyzer.GUIRunner;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class MyFrame extends JFrame {

    public MyFrame() {
        this.setTitle("Caesar Cipher - JavaRush - Alexander Chasovskoy");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(900, 600);

        // Load image from resources
        ImageIcon image = new ImageIcon(Objects.requireNonNull(GUIRunner.class.getResource("/images/caesar-cipher.png")));
        this.setIconImage(image.getImage());

        this.setVisible(true);
        this.getContentPane().setBackground(new Color(170, 130, 100));
    }

}

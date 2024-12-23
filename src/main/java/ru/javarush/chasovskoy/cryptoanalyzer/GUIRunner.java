package ru.javarush.chasovskoy.cryptoanalyzer;

import ru.javarush.chasovskoy.cryptoanalyzer.gui.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import javax.swing.border.Border;

public class GUIRunner {
    public static void main(String[] args) {
        ImageIcon image = new ImageIcon(Objects.requireNonNull(GUIRunner.class.getResource("/images/cipher.png")));
        Border border = BorderFactory.createLineBorder(Color.GRAY, 3);

        // Main label with image and text
        JLabel label = new JLabel();
        label.setText("Welcome to Crypto World");
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Georgia", Font.BOLD, 26));
        label.setIconTextGap(65);
        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(0, 0, 420, 470);

        // Radio buttons for options
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));

        JRadioButton encryptButton = new JRadioButton("Encrypt");
        JRadioButton decryptButton = new JRadioButton("Decrypt");
        JRadioButton bruteForceButton = new JRadioButton("Brute Force");

        // Group the radio buttons so only one can be selected at a time
        ButtonGroup group = new ButtonGroup();
        group.add(encryptButton);
        group.add(decryptButton);
        group.add(bruteForceButton);

        // Add radio buttons to the panel
        radioPanel.add(encryptButton);
        radioPanel.add(decryptButton);
        radioPanel.add(bruteForceButton);

        radioPanel.setBounds(450, 50, 200, 150); // Positioning the radio panel

        // Button at the bottom-right corner
        JButton nextButton = new JButton("Next");
        nextButton.setBounds(730, 470, 120, 40); // Positioning the "Next" button

        // Create and configure the frame
        MyFrame frame = new MyFrame();
        frame.setLayout(null);
        frame.add(label);
        frame.add(radioPanel);
        frame.add(nextButton);

        frame.setVisible(true);
    }
}

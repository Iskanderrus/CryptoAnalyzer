package ru.javarush.chasovskoy.cryptoanalyzer;

import ru.javarush.chasovskoy.cryptoanalyzer.gui.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import javax.swing.border.Border;

public class GUIRunner {
    public static void main(String[] args) {
        // Ensure image loading doesn't cause NullPointerException
        ImageIcon image = new ImageIcon(
                Objects.requireNonNull(
                        GUIRunner.class.getResource("/images/cipher.png"),
                        "Image not found: /images/cipher.png"
                )
        );

        // Create a border for the components
        Border border = BorderFactory.createLineBorder(Color.GRAY, 3);

        // Main label with image and text
        JLabel label = createMainLabel(image, border);

        // Panel with radio buttons
        JPanel radioPanel = createRadioPanel(border);

        // Button at the bottom-right corner
        JButton nextButton = createNextButton();

        // Create and configure the frame
        MyFrame frame = new MyFrame();
        frame.setLayout(null);
        frame.add(label);
        frame.add(radioPanel);
        frame.add(nextButton);
        frame.setVisible(true);
    }

    private static JLabel createMainLabel(ImageIcon image, Border border) {
        JLabel label = new JLabel();
        label.setText("Welcome to Crypto World");
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Georgia", Font.BOLD, 26));
        label.setIconTextGap(45);
//        label.setBorder(border);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setBounds(25, 10, 420, 470); // Position on frame
        return label;
    }

    private static JPanel createRadioPanel(Border border) {
        JPanel radioPanel = new JPanel();
        radioPanel.setBackground(new Color(170, 130, 100));
//        radioPanel.setBorder(border);

        JLabel radioLabel = new JLabel("Select operation to perform");
        radioLabel.setFont(new Font("Georgia", Font.BOLD, 18));

        // Use BoxLayout for vertical stacking
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        radioPanel.add(radioLabel);

        // Add space between the label and the radio buttons
        radioPanel.add(Box.createRigidArea(new Dimension(0, 15))); // 15px vertical space

        // Radio buttons
        JRadioButton encryptButton = createRadioButton("Encrypt");
        JRadioButton decryptButton = createRadioButton("Decrypt");
        JRadioButton bruteForceButton = createRadioButton("Brute Force");
        JRadioButton analyticsButton = createRadioButton("Analytic Decoding");

        // Group the radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(encryptButton);
        group.add(decryptButton);
        group.add(bruteForceButton);
        group.add(analyticsButton);

        // Add radio buttons to the panel
        radioPanel.add(encryptButton);
        radioPanel.add(decryptButton);
        radioPanel.add(bruteForceButton);
        radioPanel.add(analyticsButton);

        radioPanel.setBounds(480, 180, 300, 200); // Position on frame
        return radioPanel;
    }


    private static JRadioButton createRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setBackground(new Color(170, 130, 100));
        radioButton.setFont(new Font("Georgia", Font.BOLD, 16));
        return radioButton;
    }

    private static JButton createNextButton() {
        JButton nextButton = new JButton("Next >>");
        nextButton.setBounds(730, 470, 120, 40); // Position on frame
        nextButton.setFont(new Font("Georgia", Font.BOLD, 16));
        nextButton.setBackground(Color.LIGHT_GRAY);
        nextButton.setFocusPainted(false); // Better aesthetics
        return nextButton;
    }
}

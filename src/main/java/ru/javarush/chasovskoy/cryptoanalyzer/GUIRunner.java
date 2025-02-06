package ru.javarush.chasovskoy.cryptoanalyzer;

import ru.javarush.chasovskoy.cryptoanalyzer.commands.CommandBruteForce;
import ru.javarush.chasovskoy.cryptoanalyzer.commands.CommandDecoder;
import ru.javarush.chasovskoy.cryptoanalyzer.commands.CommandEncoder;
import ru.javarush.chasovskoy.cryptoanalyzer.gui.MyFrame;
import ru.javarush.chasovskoy.cryptoanalyzer.entity.Result;
import ru.javarush.chasovskoy.cryptoanalyzer.utils.ParametersValidator;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class GUIRunner {
    private static JPanel operationPanel;
    private static MyFrame frame;

    public static void main(String[] args) {
        frame = new MyFrame();
        frame.setLayout(null);

        // Initialize components
        JLabel label = createMainLabel();
        JPanel radioPanel = createRadioPanel();
        JButton nextButton = createNextButton(radioPanel);
        JButton cancelButton = createCancelButton();

        // Add components to frame
        frame.add(label);
        frame.add(radioPanel);
        frame.add(nextButton);
        frame.add(cancelButton);

        // Initialize the operation panel (hidden initially)
        operationPanel = createOperationPanel();
        frame.add(operationPanel);
        frame.setVisible(true);

        frame.revalidate();
        frame.repaint();

        frame.setVisible(true);
    }

    private static JLabel createMainLabel() {
        JLabel label = new JLabel("Welcome to Crypto World", SwingConstants.CENTER);
        label.setBounds(25, 10, 420, 470);
        label.setFont(new Font("Georgia", Font.BOLD, 26));
        label.setIcon(new ImageIcon(
                Objects.requireNonNull(GUIRunner.class.getResource("/images/cipher.png"))
        ));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setIconTextGap(45);
        return label;
    }

    private static JPanel createRadioPanel() {
        JPanel radioPanel = new JPanel();
        radioPanel.setBounds(480, 180, 300, 200);
        radioPanel.setBackground(new Color(170, 130, 100));
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));

        JLabel radioLabel = new JLabel("Select operation");
        radioLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        radioLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        radioPanel.add(radioLabel);
        radioPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        JRadioButton encryptButton = createRadioButton("Encrypt");
        JRadioButton decryptButton = createRadioButton("Decrypt");
        JRadioButton bruteForceButton = createRadioButton("Brute Force");

        // remove frame from the labels
        encryptButton.setFocusable(false);
        decryptButton.setFocusable(false);
        bruteForceButton.setFocusable(false);

        // add tooltips
        encryptButton.setToolTipText("<html><b style='font-size:12'>Protect your text!</b></html>");
        decryptButton.setToolTipText("<html><b style='font-size:12'>Encode an encrypted text if you know the key.</b></html>");
        bruteForceButton.setToolTipText("<html><b style='font-size:12'>No key needed, just any text of the same author.</b></html>");

        ButtonGroup group = new ButtonGroup();
        group.add(encryptButton);
        group.add(decryptButton);
        group.add(bruteForceButton);

        radioPanel.add(encryptButton);
        radioPanel.add(decryptButton);
        radioPanel.add(bruteForceButton);

        // Store ButtonGroup for access in Next button's action listener
        radioPanel.putClientProperty("buttonGroup", group);
        return radioPanel;
    }

    private static JRadioButton createRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setBackground(new Color(170, 130, 100));
        radioButton.setFont(new Font("Georgia", Font.BOLD, 16));
        radioButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        radioButton.setActionCommand(text);
        return radioButton;
    }

    private static JButton createNextButton(JPanel radioPanel) {
        JButton nextButton = new JButton("Next >>");
        nextButton.setBounds(730, 470, 120, 40);
        nextButton.setFont(new Font("Georgia", Font.BOLD, 16));
        nextButton.setBackground(Color.LIGHT_GRAY);
        nextButton.setFocusPainted(false);
        nextButton.setToolTipText("Proceed with setup");

        nextButton.addActionListener(e -> {
            ButtonGroup group = (ButtonGroup) radioPanel.getClientProperty("buttonGroup");
            if (group.getSelection() == null) {
                JOptionPane.showMessageDialog(frame, "Please select an operation!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            setupOperationPanel(group.getSelection().getActionCommand());
        });

        return nextButton;
    }

    private static JButton createCancelButton() {
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(390, 470, 120, 40);
        cancelButton.setFont(new Font("Georgia", Font.BOLD, 16));
        cancelButton.setBackground(Color.LIGHT_GRAY);
        cancelButton.setFocusPainted(false);
        cancelButton.setToolTipText("Close without saving results");

        cancelButton.addActionListener(e -> frame.dispose());
        return cancelButton;
    }

    private static JPanel createOperationPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(170, 130, 100));
        panel.setBounds(0, 0, 900, 600);
        panel.setVisible(false);
        return panel;
    }

    private static void setupOperationPanel(String operation) {
        operationPanel.removeAll();

        JLabel operationLabel = new JLabel("Operation: " + operation, SwingConstants.CENTER);
        operationLabel.setFont(new Font("Georgia", Font.BOLD, 22));
        operationLabel.setBounds(10, 10, 880, 40);
        operationPanel.add(operationLabel);

        JTextField inputFileField = createTextField("Input File:", 80, operationPanel);
        inputFileField.setFont(new Font("Georgia", Font.ITALIC, 18));
        inputFileField.setText("input_file.txt");
        inputFileField.setForeground(new Color(0x00FF00));
        inputFileField.setBackground(Color.BLACK);
        inputFileField.setCaretColor(Color.WHITE);
        inputFileField.setToolTipText("Replace with your file name.");

        JTextField outputFileField;
        JTextField shiftField = null;

        if (!operation.equals("Brute Force")) {
            outputFileField = createTextField("Output File:", 140, operationPanel);
            outputFileField.setText("output_file.txt");
            outputFileField.setToolTipText("Replace with your file name.");

            shiftField = createTextField("Shift as integer:", 200, operationPanel);
            shiftField.setBounds(300, 200, 100, 30);
            shiftField.setFont(new Font("Georgia", Font.ITALIC, 18));
            shiftField.setForeground(new Color(0x00FF00));
            shiftField.setBackground(Color.BLACK);
            shiftField.setCaretColor(Color.WHITE);
            shiftField.setToolTipText("Add key value as an integer.");

        } else {
            outputFileField = createTextField("Sample text file:", 140, operationPanel);
            outputFileField.setText("sample_text.txt");
            outputFileField.setToolTipText("Replace with sample text file name.");
        }

        outputFileField.setFont(new Font("Georgia", Font.ITALIC, 18));
        outputFileField.setForeground(new Color(0x00FF00));
        outputFileField.setBackground(Color.BLACK);
        outputFileField.setCaretColor(Color.WHITE);


        JButton executeButton = createExecuteButton(operation, inputFileField, outputFileField, shiftField);
        JButton backButton = createBackButton();
        JButton cancelButton = createCancelButton();

        operationPanel.add(executeButton);
        operationPanel.add(backButton);
        operationPanel.add(cancelButton);

        mainPanelVisibility(false);
        operationPanel.setVisible(true);
    }

    private static JTextField createTextField(String labelText, int y, JPanel parentPanel) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Georgia", Font.BOLD, 18));
        label.setBounds(100, y, 200, 30);
        parentPanel.add(label);

        JTextField textField = new JTextField();
        textField.setBounds(300, y, 500, 30);
        parentPanel.add(textField);

        return textField;
    }

    private static JButton createExecuteButton(String operation, JTextField inputFileField, JTextField outputFileField, JTextField shiftField) {
        JButton executeButton = new JButton("Execute");
        executeButton.setBounds(730, 470, 120, 40);
        executeButton.setFont(new Font("Georgia", Font.BOLD, 16));
        executeButton.setBackground(Color.LIGHT_GRAY);
        executeButton.setFocusPainted(false);
        executeButton.setToolTipText("Let's make this text great again");

        executeButton.addActionListener(e -> {
            String inputFile = inputFileField.getText().trim();
            String outputFile = outputFileField.getText().trim();
            String[] parameters;

            try {
                if (operation.equals("Brute Force")) {
                    parameters = new String[]{inputFile, outputFile};
                    Result result = new CommandBruteForce().execute(parameters);

                    // Correctly pass the result message and output file path
                    showResultDialog(result.getMessage(), result.getOutputFilePath());
                } else {
                    int shift = Integer.parseInt(shiftField.getText().trim());
                    parameters = new String[]{inputFile, outputFile, String.valueOf(shift)};

                    // Validate the parameters
                    ParametersValidator.ValidationResult validationResult = ParametersValidator.validate(parameters);

                    if (!validationResult.isValid()) {
                        JOptionPane.showMessageDialog(frame, validationResult.getErrorMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Use the output file path from the validation result
                    String validOutputFilePath = validationResult.getOutputFilePath().toString();

                    // Perform the operation (encoding or decoding)
                    Result result = operation.equals("Encrypt") ?
                            new CommandEncoder().execute(parameters) :
                            new CommandDecoder().execute(parameters);

                    showResultDialog(result.getMessage(), validOutputFilePath);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Execution Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        return executeButton;
    }


    private static JButton createBackButton() {
        JButton backButton = new JButton("<< Back");
        backButton.setBounds(50, 470, 120, 40);
        backButton.setFont(new Font("Georgia", Font.BOLD, 16));
        backButton.setBackground(Color.LIGHT_GRAY);
        backButton.setFocusPainted(false);
        backButton.setToolTipText("Reconsider operation");

        backButton.addActionListener(e -> {
            mainPanelVisibility(true);
            operationPanel.setVisible(false);
        });

        return backButton;
    }

    private static void showResultDialog(String message, String outputFilePath) {
        Object[] options = {"Open File", "Close"};
        int choice = JOptionPane.showOptionDialog(
                frame,
                message,
                "Execution Complete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[1]
        );

        if (choice == JOptionPane.YES_OPTION) {
            try {
                Desktop.getDesktop().open(new File(outputFilePath));
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Unable to open file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void mainPanelVisibility(boolean visible) {
        Component[] components = frame.getContentPane().getComponents();
        for (Component component : components) {
            if (component != operationPanel) {
                component.setVisible(visible);
            }
        }
    }
}

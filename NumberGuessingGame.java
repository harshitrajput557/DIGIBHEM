package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessingGame extends JFrame {
    private int targetNumber;
    private int remainingAttempts;
    private int userScore;

    private JLabel infoLabel;
    private JTextField guessTextField;
    private JButton guessButton;
    private JLabel resultLabel;
    private JLabel scoreLabel;

    public NumberGuessingGame() {
        targetNumber = generateRandomNumber();
        remainingAttempts = 10;
        userScore = 0;

        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(4, 1));

        infoLabel = new JLabel("Guess a number between 1 and 100:");
        guessTextField = new JTextField();
        guessButton = new JButton("Guess");
        resultLabel = new JLabel("");
        scoreLabel = new JLabel("Score: " + userScore);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        add(infoLabel);
        add(guessTextField);
        add(guessButton);
        add(resultLabel);
        add(scoreLabel);

        setVisible(true);
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 100) + 1;
    }

    private void checkGuess() {
        int userGuess = Integer.parseInt(guessTextField.getText());
        remainingAttempts--;

        if (userGuess == targetNumber) {
            userScore += remainingAttempts + 1;
            resultLabel.setText("Congratulations! You guessed the number.");
            scoreLabel.setText("Score: " + userScore);
            guessButton.setEnabled(false);
        } else if (remainingAttempts == 0) {
            resultLabel.setText("Sorry, you're out of attempts. The correct number was " + targetNumber + ".");
            guessButton.setEnabled(false);
        } else if (userGuess < targetNumber) {
            resultLabel.setText("Try a higher number. Remaining attempts: " + remainingAttempts);
        } else {
            resultLabel.setText("Try a lower number. Remaining attempts: " + remainingAttempts);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame();
            }
        });
    }
}

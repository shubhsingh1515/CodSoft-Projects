import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGame extends JFrame {
    private int target;
    private JLabel messageLabel;
    private JTextField guessField;
    private JButton guessButton;
    private int attempts;

    public NumberGame() {
        setTitle("Number Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(173, 216, 230));

        target = (int) (Math.random() * 100) + 1;
        messageLabel = new JLabel("Guess a number between 1 and 100:");
        guessField = new JTextField(20);
        guessButton = new JButton("Guess");

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        add(messageLabel);
        add(guessField);
        add(guessButton);

        setVisible(true);
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            if (guess < target) {
                messageLabel.setText("Try a higher number:  ");
            } else if (guess > target) {
                messageLabel.setText("Try a lower number:  ");
            } else {
                messageLabel.setText("Congratulations! You guessed the number " + "in " + attempts + " attempts.");
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText(" Enter a valid number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberGame();
            }
        });
    }
}

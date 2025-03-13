import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.UUID;

public class NumberGuessingGameGUI extends JFrame {
    private int targetNumber;
    private int attempts;
    private String gameID;
    private JTextField guessField;
    private JLabel messageLabel, attemptsLabel, gameIDLabel;

    public NumberGuessingGameGUI() {
        //set background color
        getContentPane().setBackground( new Color(255,129,0));

        //Set Image Icon
        ImageIcon image = new ImageIcon("guessnum.jpg");
        setIconImage(image.getImage());

        // Generate a unique game ID
        gameID = UUID.randomUUID().toString();
        targetNumber = new Random().nextInt(100) + 1; // Random number between 1-100
        attempts = 0;

        // Setup GUI
        setTitle("Number Guessing Game");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        // Game ID Label
        gameIDLabel = new JLabel("Game ID: " + gameID);
        gameIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameIDLabel.setFont(new Font("SansSerif",Font.PLAIN, 24));
        add(gameIDLabel);

        // Instructions
        messageLabel = new JLabel("Guess a number between 1 and 100:");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setFont(new Font("SansSerif",Font.PLAIN, 36));
        add(messageLabel);

        // Input Field
        guessField = new JTextField();
        guessField.setHorizontalAlignment(SwingConstants.CENTER);
        guessField.setForeground(new Color(0xffffff));
        guessField.setBackground( new Color(0x33302c));
        guessField.setFont( new Font("SansSerif",Font.PLAIN, 30));
        guessField.setCaretColor(new Color(0xffffff));
        add(guessField);

        // Attempts Label
        attemptsLabel = new JLabel("Attempts: 0");
        attemptsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        attemptsLabel.setFont( new Font("SensSerif",Font.PLAIN,36));
        add(attemptsLabel);

        // Submit Button
        JButton guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessHandler());
        guessButton.setFont(new Font("SensSerif",Font.PLAIN,24));
        guessButton.setBackground(new Color(0x22345f));
        guessButton.setForeground(new  Color(0xffffff));
        guessButton.setSize(40, 20);
        add(guessButton);
    }

    private class GuessHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;
                if (guess > targetNumber) {
                    messageLabel.setText("Too high! Try again.");
                } else if (guess < targetNumber) {
                    messageLabel.setText("Too low! Try again.");
                } else {
                    messageLabel.setText("Congratulations! You guessed it!");
                    JOptionPane.showMessageDialog(null, "You guessed it in " + attempts + " attempts!\nGame ID: " + gameID);
                    resetGame();
                }
                attemptsLabel.setText("Attempts: " + attempts);
                guessField.setText("");
            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid number.");
            }
        }
    }

    private void resetGame() {
        targetNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        gameID = UUID.randomUUID().toString();
        gameIDLabel.setText("Game ID: " + gameID);
        messageLabel.setText("Guess a number between 1 and 100:");
        attemptsLabel.setText("Attempts: 0");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NumberGuessingGameGUI game = new NumberGuessingGameGUI();
            game.setVisible(true);
        });
    }
}
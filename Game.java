import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game implements ActionListener {

    JOptionPane joOptionPane = new JOptionPane();
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textField = new JLabel();
    JButton button[] = new JButton[9];
    boolean playerTurn;

    Game() {
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());
        frame.add(button_panel);
        frame.add(title, BorderLayout.NORTH);
        frame.setVisible(true);

        textField.setForeground(Color.black);
        textField.setBackground(Color.CYAN);
        textField.setFont(new Font("NV Boli", Font.PLAIN, 50));
        textField.setText("Tic-Tac-Toe");
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setOpaque(false);

        title.setBounds(0, 150, 800, 200);
        title.setBackground(Color.CYAN);
        title.setLayout(new BorderLayout());
        title.add(textField);
        button_panel.setLayout(new GridLayout(3, 3));

        for (int i = 0; i <= 8; i++) {  
            button[i] = new JButton();
            button_panel.add(button[i]);
            button[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            button[i].setFocusable(false);
            button[i].setBackground(Color.BLACK);
            button[i].addActionListener(this);
        }

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i <= 8; i++) {  
            if (e.getSource() == button[i]) {
                if (playerTurn) {
                    if (button[i].getText() == "") {
                        button[i].setForeground(Color.CYAN);
                        button[i].setText("X");
                        textField.setText("O Turn");
                        playerTurn = false;
                        check();
                    }
                } else {
                    if (button[i].getText() == "") {
                        button[i].setForeground(Color.WHITE);
                        button[i].setText("O");
                        textField.setText("X Turn");
                        playerTurn = true;
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn() {
        if (random.nextInt(2) == 0) {
            playerTurn = true;
            textField.setText("X Turn");
        } else {
            playerTurn = false;
            textField.setText("O Turn");
        }
    }

    public void check() {
       
        if (button[0].getText() == "X" && button[1].getText() == "X" && button[2].getText() == "X") Xwin(0, 1, 2);
        if (button[3].getText() == "X" && button[4].getText() == "X" && button[5].getText() == "X") Xwin(3, 4, 5);
        if (button[6].getText() == "X" && button[7].getText() == "X" && button[8].getText() == "X") Xwin(6, 7, 8);
        if (button[0].getText() == "X" && button[3].getText() == "X" && button[6].getText() == "X") Xwin(0, 3, 6);
        if (button[1].getText() == "X" && button[4].getText() == "X" && button[7].getText() == "X") Xwin(1, 4, 7);
        if (button[2].getText() == "X" && button[5].getText() == "X" && button[8].getText() == "X") Xwin(2, 5, 8);
        if (button[0].getText() == "X" && button[4].getText() == "X" && button[8].getText() == "X") Xwin(0, 4, 8);
        if (button[2].getText() == "X" && button[4].getText() == "X" && button[6].getText() == "X") Xwin(2, 4, 6);

        // Check horizontal, vertical and diagonal wins for O
        if (button[0].getText() == "O" && button[1].getText() == "O" && button[2].getText() == "O") Owin(0, 1, 2);
        if (button[3].getText() == "O" && button[4].getText() == "O" && button[5].getText() == "O") Owin(3, 4, 5);
        if (button[6].getText() == "O" && button[7].getText() == "O" && button[8].getText() == "O") Owin(6, 7, 8);
        if (button[0].getText() == "O" && button[3].getText() == "O" && button[6].getText() == "O") Owin(0, 3, 6);
        if (button[1].getText() == "O" && button[4].getText() == "O" && button[7].getText() == "O") Owin(1, 4, 7);
        if (button[2].getText() == "O" && button[5].getText() == "O" && button[8].getText() == "O") Owin(2, 5, 8);
        if (button[0].getText() == "O" && button[4].getText() == "O" && button[8].getText() == "O") Owin(0, 4, 8);
        if (button[2].getText() == "O" && button[4].getText() == "O" && button[6].getText() == "O") Owin(2, 4, 6);
    }

    public void Xwin(int a, int b, int c) {
        button[a].setBackground(Color.GRAY);
        button[b].setBackground(Color.GRAY);
        button[c].setBackground(Color.GRAY);
        JOptionPane.showMessageDialog(null, "Congratulation You Won! 🎉🎉🎉", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        textField.setText("X Win");
        for (int i = 0; i <= 8; i++) {
            button[i].setEnabled(false);
        }

        // Restart game after 10 seconds
        restartGameAfterDelay();
    }

    public void Owin(int a, int b, int c) {
        button[a].setBackground(Color.GRAY);
        button[b].setBackground(Color.GRAY);
        button[c].setBackground(Color.GRAY);
        textField.setText("O Win");
        JOptionPane.showMessageDialog(null, "Congratulation You Won! 🎉🎉🎉", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        for (int i = 0; i <= 8; i++) {
            button[i].setEnabled(false);
        }

        // Restart game after 10 seconds
        restartGameAfterDelay();
    }

    private void restartGameAfterDelay() {
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        timer.setRepeats(false);  
        timer.start();
    }

    
    private void resetGame() {
        
        for (int i = 0; i <= 8; i++) {
            button[i].setEnabled(true);
            button[i].setText("");
            button[i].setBackground(Color.BLACK);
        }
        textField.setText("Tic-Tac-Toe");
        firstTurn();  
    }

    public static void main(String[] ar) {
        new Game();
    }
}

package JavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private char[][] boundary;
    private JButton[][] buttons;
    private boolean xTurn;

    public TicTacToeGUI() {
        boundary = new char[3][3];
        buttons = new JButton[3][3];
        xTurn = true;

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        initializeBoard();
        initializeButtons();
        pack(); // By using pack(), you ensure that all components within the frame are properly
                // laid out and visible without unnecessary empty spaces.
       setLocationRelativeTo(null); //
        setVisible(true);
    }

    private void initializeBoard() {
        for (int i = 0; i < boundary.length; i++) {
            for (int j = 0; j < boundary[i].length; j++) {
                boundary[i][j] = ' ';
            }
        }
    }

    private void initializeButtons() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                JButton button = new JButton();
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
                button.addActionListener(new ButtonClickListener(i, j));
                buttons[i][j] = button;
                add(button); // This method call adds a JButton component
            }
        }
    }

    private void placeMark(int row, int col) {
        if (boundary[row][col] == ' ') {
            if (xTurn) {
                buttons[row][col].setText("X");
                boundary[row][col] = 'X';
            } else {
                buttons[row][col].setText("O");
                boundary[row][col] = 'O';
            }
            xTurn = !xTurn; // Switch
        }
    }

    private boolean checkWin() {

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (boundary[i][0] != ' ' && boundary[i][0] == boundary[i][1] && boundary[i][1] == boundary[i][2]) {
                return true;
            }
            // Check columns
            if (boundary[0][i] != ' ' && boundary[0][i] == boundary[1][i] && boundary[1][i] == boundary[2][i]) {
                return true;
            }
        }

        // Check diagonals
        if ((boundary[0][0] != ' ' && boundary[0][0] == boundary[1][1] && boundary[1][1] == boundary[2][2]) ||
                (boundary[0][2] != ' ' && boundary[0][2] == boundary[1][1] && boundary[1][1] == boundary[2][0])) {
            return true;
        }

        return false;

    }

    private class ButtonClickListener implements ActionListener {
        private int row, col;

        ButtonClickListener(int row, int col) { // Constructor
            this.row = row;
            this.col = col;
        }

        public void actionPerformed(ActionEvent e) {
            placeMark(row, col);
            if (checkWin()) {
                JOptionPane.showMessageDialog(null, (xTurn ? "Player O" : "Player X") + " wins!"); // ternary operator
                initializeBoard(); // to reset the state.
                for (int i = 0; i < buttons.length; i++) {
                    for (int j = 0; j < buttons[i].length; j++) {
                        buttons[i][j].setText(""); // clear the buttons
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeGUI::new); 
     /*
     * can be used to perform a task asynchronously in the AWT Event
     * dispatcher thread.
      */
    }

}

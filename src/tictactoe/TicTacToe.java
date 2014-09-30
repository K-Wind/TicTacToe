/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Kavu
 */
public class TicTacToe {

    static int[][] board;

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        board = new int[3][3];
        game.play(game);

        System.out.println(game.toString());
    }

    public void play(TicTacToe game) {
        game.resetBoard();
        while(gameOver() < 1){
            
        }
    }

    public int gameOver() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != 0) {
                if (board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                    return board[i][0];
                }
                if (board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                    return board[0][i];
                }
            }
        }
        if (board[1][1] != 0) {
            if(board[1][1] == board[0][0] && board[1][1] == board[2][2]){
                return board[1][1];
            }
            if(board[1][1] == board[2][0] && board[1][1] == board[0][2]){
                return board[1][1];
            }
        }
        for (int h = 0; h < 3; h++) {
            for (int w = 0; w < 3; w++) {
                if(board[h][w] == 0){
                    return 0;
                }
            }
        }
        return 42;
    }

    public void resetBoard() {
        for (int h = 0; h < 3; h++) {
            for (int w = 0; w < 3; w++) {
                board[h][w] = 0;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder print = new StringBuilder();
        print.append("-0-1-2-x\n");
        for (int h = 0; h < 3; h++) {
            print.append(h);
            for (int w = 0; w < 3; w++) {
                int pos = board[h][w];
                if (pos < 1) {
                    print.append(" ");
                } else if (pos == 2) {
                    print.append("O");
                } else if (pos == 1) {
                    print.append("X");
                }
                print.append("|");
            }
            print.append("\n");
            if (h == 2) {
                print.append("-------\n");
                print.append("y");
            } else {
                print.append("|-----|\n");
            }
        }
        return print.toString();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Kavu
 */
public class TicTacToe {

    static int[][] board;
    static int player;

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        board = new int[3][3];
        game.play(game);

        System.out.println(game.toString());
    }

    public void play(TicTacToe game) {
        game.resetBoard();
        System.out.println("Write input as x,y");
        while (gameOver() < 1) {
            System.out.println("Player " + player + " Move:");
            int[] move = getInput();
            if (doMove(player, move[0], move[1])) {
                switchPlayer();
                System.out.println(game.toString());
            } else {
                System.out.println("Illegal move");
            }
        }
        if (gameOver() == 42) {
            System.out.println("It's a draw...");
        } else {
            System.out.println("Player " + player + " wins!");
        }
    }
    
    public void doAlphaBeta(){
        /*If node is a leaf
            return static value
          If node is MAX
            While a < b
            V=value of next childnode.AlphaBeta(á, â)
            If V > a let a = V
            return a
          If node is MIN
            While a < b
            V=value of next childnode.AlphaBeta(á, â)
            If V < b let b = V
            return b*/
    }
    
    public int alphabetaAlg(int alpha, int beta){
        return 0;
    }

    public boolean doMove(int player, int x, int y) {
        if (board[y][x] == 0) {
            board[y][x] = player;
            return true;
        } else {
            return false;
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
            if (board[1][1] == board[0][0] && board[1][1] == board[2][2]) {
                return board[1][1];
            }
            if (board[1][1] == board[2][0] && board[1][1] == board[0][2]) {
                return board[1][1];
            }
        }
        for (int h = 0; h < 3; h++) {
            for (int w = 0; w < 3; w++) {
                if (board[h][w] == 0) {
                    return 0;
                }
            }
        }
        return 42;
    }

    public void switchPlayer() {
        if (player == 1) {
            player = 2;
        } else if (player == 2) {
            player = 1;
        }
    }

    public void resetBoard() {
        for (int h = 0; h < 3; h++) {
            for (int w = 0; w < 3; w++) {
                board[h][w] = 0;
            }
        }
        player = 1;
    }

    public int[] getInput() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String move = br.readLine();
            if (move.length() != 3 && !move.contains(",")) {
            }
            String[] cor = move.split(",");
            int[] ret = {Integer.parseInt(cor[0]), Integer.parseInt(cor[1])};
            return ret;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder print = new StringBuilder();
        print.append("-------\n");
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
                print.append("-0-1-2-");
            } else {
                print.append("|-----|\n");
            }
        }
        return print.toString();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Kavu
 */
public class TicTacToe {

    static int[][] board;
    static int player, counter;
    static int[] compMove;

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        board = new int[3][3];
        compMove = new int[2];
        game.play(game);
    }

    public void play(TicTacToe game) {
        game.resetBoard();
        System.out.println("Write input as x,y");
        int depth = 0;
        while (gameState(board) < 1) {
            
            System.out.println(printBoard(board));
            System.out.println("Player " + player + " Move:");
            int[] move = getInput();
            if (doMove(player, move[0], move[1])) {
                switchPlayer();
                System.out.println(game.toString());
            } else {
                System.out.println("Illegal move");
            }
            depth++;
            int[][] tBoard = game.cloneBoard(board);
            counter = 0;
            printBoard(tBoard);
            System.out.println(alphabetaAlg(Integer.MIN_VALUE, Integer.MAX_VALUE, depth, tBoard));
        }
        if (gameState(board) == 42) {
            System.out.println("It's a draw...");
        } else {
            System.out.println("Player " + player + " wins!");
        }
    }

    public void doAlphaBeta() {
        alphabetaAlg(0, 0, 0, board);
    }

    public int alphabetaAlg(int alpha, int beta, int depth, int[][] tempBoard) {
        counter++;
        System.out.println("recursion:" + counter + " at depth: " + depth);
        int best = Integer.MIN_VALUE;
        ArrayList<int[]> moves = new ArrayList();
        if (depth == 9) {
            return calculateValue(tempBoard);
        } else if (alpha < beta) {
            moves = calculateMoves(tempBoard);
            int[][] newState;
            for (int[] move : moves) {
                System.out.println("move to: " + move[0] + "," + move[1]);
                System.out.println(printBoard(tempBoard));
                newState = cloneBoard(tempBoard);
                if (depth % 2 == 0) {
                    newState[move[0]][move[1]] = 1;
                    printBoard(newState);
                } else {
                    newState[move[0]][move[1]] = 2;
                    printBoard(newState);
                }
                int v = alphabetaAlg(alpha, beta, depth + 1, newState);
                if (v > best && depth == 1) {
                    compMove[0] = move[0];
                    compMove[1] = move[1];
                    best = v;
                }
                if (depth % 2 == 0) {
                    if (v > alpha) {
                        alpha = v;
                    }
                } else {
                    if (v < beta) {
                        beta = v;
                    }
                }
            }

            /*int d = 0;
             for (int[] h : tempBoard) {
             for (int w : h) {
             int[][] tt = cloneBoard(tempBoard);
             if (tt[d][w] == 0) {
             if (depth % 2 == 0) {
             tt[d][w] = 1;
             } else {
             tt[d][w] = 2;
             }

             System.out.println(printBoard(tt));
             int v = alphabetaAlg(alpha, beta, depth + 1, tt);
             moves.add(v + "," + h + "," + w);
             //System.out.println(v);
             //System.out.println(toString());

             }
             }
             d++;
             }
             }
             if (depth == 1) {
             String[] bMove = null;
             for (int[] m : moves) {
             if (v > best) {
             best = Integer.parseInt(sR[0]);
             bMove = sR;
             }
             }
             compMove = new String[]{bMove[1], bMove[2]};
             return Integer.MAX_VALUE;
             } else if (depth % 2 == 0) {
             if (moves.size() > 0) {
             String[] cV = moves.get(moves.size() - 1).split(",");
             int i = Integer.parseInt(cV[0]);
             if (i < beta) {
             beta = i;
             }
             }
             return beta;
             } else {
             if (moves.size() > 0) {
             String[] cV = moves.get(moves.size()).split(",");
             int i = Integer.parseInt(cV[0]);
             if (i > alpha) {
             alpha = i;
             }
             }
             return alpha;
             }*/
        }
        if (depth == 1) {
            return 42;
        } else if (depth % 2 == 0) {
            return alpha;
        } else {
            return beta;
        }
    }

    private int calculateValue(int[][] board) {
        int state = gameState(board);
        if (state == 1) {
            return -100;
        } else if (state == 2) {
            return 100;
        } else {
            return 0;
        }
    }

    private ArrayList<int[]> calculateMoves(int[][] tempBoard) {
        ArrayList<int[]> moves = new ArrayList();
        if (gameState(tempBoard) < 1) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (tempBoard[r][c] < 1) {
                        System.out.println(r + "," + c);
                        moves.add(new int[]{r, c});
                    }
                }
            }
        }
        return moves;
    }

    public boolean doMove(int player, int x, int y) {
        if (board[y][x] == 0) {
            board[y][x] = player;
            return true;
        } else {
            return false;
        }
    }

    public int gameState(int[][] board) {
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

    public int[][] cloneBoard(int[][] board) {
        int[][] t = new int[3][3];
        for (int h = 0; h < 3; h++) {
            for (int w = 0; w < 3; w++) {
                t[h][w] = board[h][w];
            }
        }
        return t;
    }

    public String printBoard(int[][] board) {
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

    public class Board {

        int[][] board;

        public Board(int[][] board) {
            this.board = board;
        }

        public void reset() {
            for (int h = 0; h < 3; h++) {
                for (int w = 0; w < 3; w++) {
                    board[h][w] = 0;
                }
            }
            player = 1;
        }

        public void setValue(int x, int y, int value) {
            board[y][x] = value;
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
    /*
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
     }*/
}

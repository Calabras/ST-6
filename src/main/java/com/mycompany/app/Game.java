package com.mycompany.app;

import java.util.ArrayList;
import java.util.Random;

class Game {
    public State state;
    public Player player1;
    public Player player2;
    public Player cplayer;
    public int nmove;
    public char symbol;
    public static final int INF = 100;
    public int q;
    public char[] board;

    public Game() {
        player1 = new Player();
        player2 = new Player();
        player1.symbol = 'X';
        player2.symbol = 'O';
        state = State.PLAYING;
        board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
    }

    public State checkState(char[] board) {
        State state = State.PLAYING;
        if ((board[0] == symbol && board[1] == symbol && board[2] == symbol)
                || (board[3] == symbol && board[4] == symbol && board[5] == symbol)
                || (board[6] == symbol && board[7] == symbol && board[8] == symbol)
                || (board[0] == symbol && board[3] == symbol && board[6] == symbol)
                || (board[1] == symbol && board[4] == symbol && board[7] == symbol)
                || (board[2] == symbol && board[5] == symbol && board[8] == symbol)
                || (board[0] == symbol && board[4] == symbol && board[8] == symbol)
                || (board[2] == symbol && board[4] == symbol && board[6] == symbol)) {
            if (symbol == 'X') {
                state = State.XWIN;
            } else if (symbol == 'O') {
                state = State.OWIN;
            }
        } else {
            state = State.DRAW;
            for (int i = 0; i < 9; i++) {
                if (board[i] == ' ') {
                    state = State.PLAYING;
                    break;
                }
            }
        }
        return state;
    }

    void generateMoves(char[] board, ArrayList<Integer> moveList) {
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                moveList.add(i);
            }
        }
    }

    int evaluatePosition(char[] board, Player player) {
        State state = checkState(board);
        if ((state == State.XWIN || state == State.OWIN || state == State.DRAW)) {
            if ((state == State.XWIN && player.symbol == 'X') || (state == State.OWIN && player.symbol == 'O')) {
                return +Game.INF;
            } else if ((state == State.XWIN && player.symbol == 'O') || (state == State.OWIN && player.symbol == 'X')) {
                return -Game.INF;
            } else if (state == State.DRAW) {
                return 0;
            }
        }
        return -1;
    }

    int miniMax(char[] board, Player player) {
        int bestVal = -Game.INF;
        int index = 0;
        ArrayList<Integer> moveList = new ArrayList<>();
        int[] bestMoves = new int[9];

        generateMoves(board, moveList);

        if (moveList.isEmpty()) {
            return 0;
        }

        while (moveList.size() != 0) {
            board[moveList.get(0)] = player.symbol;
            symbol = player.symbol;

            int val = minMove(board, player);

            if (val > bestVal) {
                bestVal = val;
                index = 0;
                bestMoves[index] = moveList.get(0) + 1;
            } else if (val == bestVal) {
                bestMoves[++index] = moveList.get(0) + 1;
            }

            System.out.printf("%nminimax: %3d(%1d) ", 1 + moveList.get(0), val);
            board[moveList.get(0)] = ' ';
            moveList.remove(0);
        }
        if (index > 0) {
            Random r = new Random();
            index = r.nextInt(index + 1);
        }

        System.out.printf("%nminimax best: %3d(%1d) ", bestMoves[index], bestVal);
        System.out.printf("Steps counted: %d", q);
        q = 0;
        return bestMoves[index];
    }

    int minMove(char[] board, Player player) {
        int posValue = evaluatePosition(board, player);
        if (posValue != -1) {
            return posValue;
        }
        q++;
        int bestVal = +Game.INF;
        ArrayList<Integer> moveList = new ArrayList<>();

        generateMoves(board, moveList);

        while (moveList.size() != 0) {
            symbol = (player.symbol == 'X') ? 'O' : 'X';
            board[moveList.get(0)] = symbol;

            int val = maxMove(board, player);

            if (val < bestVal) {
                bestVal = val;
            }
            board[moveList.get(0)] = ' ';
            moveList.remove(0);
        }
        return bestVal;
    }

    int maxMove(char[] board, Player player) {
        int posValue = evaluatePosition(board, player);
        if (posValue != -1) {
            return posValue;
        }
        q++;
        int bestVal = -Game.INF;
        ArrayList<Integer> moveList = new ArrayList<>();
        generateMoves(board, moveList);
        while (moveList.size() != 0) {
            symbol = (player.symbol == 'X') ? 'X' : 'O';
            board[moveList.get(0)] = symbol;
            int val = minMove(board, player);
            if (val > bestVal) {
                bestVal = val;
            }
            board[moveList.get(0)] = ' ';
            moveList.remove(0);
        }
        return bestVal;
    }
}

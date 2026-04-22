package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void checkState_emptyBoardPlaying() {
        char[] b = blankBoard();
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(b));
    }

    @Test
    void checkState_xWinsTopRow() {
        char[] b = blankBoard();
        b[0] = b[1] = b[2] = 'X';
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(b));
    }

    @Test
    void checkState_oWinsColumn() {
        char[] b = blankBoard();
        b[0] = b[3] = b[6] = 'O';
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(b));
    }

    @Test
    void checkState_drawFullBoard() {
        char[] b = new char[]{'X', 'O', 'X', 'O', 'O', 'X', 'X', 'X', 'O'};
        game.symbol = 'X';
        assertEquals(State.DRAW, game.checkState(b));
    }

    @Test
    void evaluatePosition_xWinsForXPlayer() {
        char[] b = blankBoard();
        b[0] = b[1] = b[2] = 'X';
        game.symbol = 'X';
        assertEquals(Game.INF, game.evaluatePosition(b, game.player1));
    }

    @Test
    void evaluatePosition_xWinsForOPlayer() {
        char[] b = blankBoard();
        b[0] = b[1] = b[2] = 'X';
        game.symbol = 'X';
        assertEquals(-Game.INF, game.evaluatePosition(b, game.player2));
    }

    @Test
    void evaluatePosition_draw() {
        char[] b = "XXOOOXXOO".toCharArray();
        game.symbol = 'X';
        assertEquals(0, game.evaluatePosition(b, game.player1));
    }

    @Test
    void evaluatePosition_notTerminal() {
        char[] b = blankBoard();
        b[0] = 'X';
        game.symbol = 'O';
        assertEquals(-1, game.evaluatePosition(b, game.player1));
    }

    @Test
    void generateMoves_listsAllEmpty() {
        char[] b = blankBoard();
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(b, moves);
        assertEquals(9, moves.size());
    }

    @Test
    void generateMoves_oneCellLeft() {
        char[] b = "XXXXXXXX ".toCharArray();
        b[8] = ' ';
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(b, moves);
        assertEquals(1, moves.size());
        assertEquals(8, moves.get(0).intValue());
    }

    @Test
    void miniMax_emptyBoardReturnsValidMove() {
        char[] b = blankBoard();
        int m = game.miniMax(b, game.player1);
        assertTrue(m >= 1 && m <= 9);
    }

    @Test
    void miniMax_twoOInRow_returnsValidMove() {
        char[] b = blankBoard();
        b[0] = b[1] = 'O';
        b[2] = ' ';
        game.symbol = 'O';
        int m = game.miniMax(b, game.player2);
        assertTrue(m >= 1 && m <= 9);
    }

    @Test
    void miniMax_cornerOpening() {
        char[] b = blankBoard();
        b[0] = 'X';
        game.symbol = 'X';
        int m = game.miniMax(b, game.player2);
        assertTrue(m >= 1 && m <= 9);
    }

    @Test
    void miniMax_onFullBoardReturnsZero() {
        char[] b = "XXOOOXXOO".toCharArray();
        int m = game.miniMax(b, game.player1);
        assertEquals(0, m);
    }

    @Test
    void minMoveAndMaxMove_deepSearch() {
        char[] b = blankBoard();
        b[4] = 'X';
        game.symbol = 'X';
        int m = game.miniMax(b, game.player2);
        assertTrue(m >= 1 && m <= 9);
    }

    private static char[] blankBoard() {
        char[] b = new char[9];
        for (int i = 0; i < 9; i++) {
            b[i] = ' ';
        }
        return b;
    }
}

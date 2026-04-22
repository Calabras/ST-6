package com.mycompany.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TicTacToeCellTest {

    @Test
    void cellStoresPosition() {
        TicTacToeCell c = new TicTacToeCell(5, 2, 1);
        assertEquals(5, c.getNum());
        assertEquals(2, c.getCol());
        assertEquals(1, c.getRow());
        assertEquals(' ', c.getMarker());
    }

    @Test
    void setMarkerUpdatesText() {
        TicTacToeCell c = new TicTacToeCell(0, 0, 0);
        c.setMarker("X");
        assertEquals('X', c.getMarker());
    }
}

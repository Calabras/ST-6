package com.mycompany.app;

import org.junit.jupiter.api.Test;

import java.awt.GridLayout;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TicTacToePanelTest {

    @Test
    void panelConstructsInHeadless() {
        TicTacToePanel p = new TicTacToePanel(new GridLayout(3, 3));
        assertNotNull(p);
    }
}

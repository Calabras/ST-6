package com.mycompany.app;

import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;

import javax.swing.JFrame;

/** Entry point: tic-tac-toe with minimax ({@link Game}). */
public class Program {

    public static void main(String[] args) {
        if (GraphicsEnvironment.isHeadless()) {
            runHeadlessDemo();
            return;
        }
        JFrame frame = new JFrame("Demo");
        frame.add(new TicTacToePanel(new GridLayout(3, 3)));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(5, 5, 500, 500);
        frame.setVisible(true);
    }

    static void runHeadlessDemo() {
        Game g = new Game();
        g.symbol = 'X';
        char[] b = new char[9];
        for (int i = 0; i < 9; i++) {
            b[i] = ' ';
        }
        b[4] = 'X';
        int move = g.miniMax(b, g.player2);
        System.out.println("Headless demo: suggested O move index (1-based) = " + move);
    }
}

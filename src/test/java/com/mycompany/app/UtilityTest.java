package com.mycompany.app;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilityTest {

    @Test
    void printCharBoard() {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(buf));
        try {
            Utility.print(new char[]{'X', 'O', 'X', 'O', 'O', 'X', 'X', 'X', 'O'});
        } finally {
            System.setOut(old);
        }
        assertTrue(buf.toString().contains("X"));
    }

    @Test
    void printIntBoard() {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(buf));
        try {
            Utility.print(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
        } finally {
            System.setOut(old);
        }
        assertTrue(buf.toString().contains("1"));
    }

    @Test
    void printMoveList() {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(buf));
        try {
            List<Integer> m = new ArrayList<>();
            m.add(0);
            m.add(4);
            Utility.print(new ArrayList<>(m));
        } finally {
            System.setOut(old);
        }
        assertTrue(buf.toString().contains("0"));
    }
}

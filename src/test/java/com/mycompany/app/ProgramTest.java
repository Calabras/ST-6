package com.mycompany.app;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProgramTest {

    @Test
    void runHeadlessDemoPrints() {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(buf));
        try {
            Program.runHeadlessDemo();
        } finally {
            System.setOut(old);
        }
        assertTrue(buf.toString().contains("Headless demo"));
    }

    @Test
    void mainHeadlessDoesNotThrow() {
        assertDoesNotThrow(() -> Program.main(new String[] {}));
    }
}

package com.example.geektrust;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("App Test")
public class MainTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("#1 Integration test 1")
    public void Application_Test() throws Exception {
        // Arrange
        String arguements = "sample_input/input1.txt";

        // Act
        Main.run(arguements);

        // Assert
        Assertions.assertTrue("Game won by: PLAYER_A".equals(
                outputStreamCaptor.toString().trim())
                || "Game won by: PLAYER_B".equals(
                        outputStreamCaptor.toString().trim()));
    }
}
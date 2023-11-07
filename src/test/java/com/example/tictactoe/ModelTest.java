package com.example.tictactoe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    @Test
    public void testValidMove() {
        Model model = new Model();
        assertTrue(model.isEmpty(0, 0));

        model.set(0, 0, '0');

        assertFalse(model.isEmpty(0, 0));
    }

    @Test
    public void testInvalidMove() {
        Model model = new Model();
        model.set(0, 0, '0');

        assertFalse(model.isEmpty(0, 0));
    }

    @Test
    public void testRoundCompletionGameOver() {
        Model model = new Model();

        model.set(0, 0, '0');
        model.set(0, 1, '1');
        model.set(0, 2, '0');
        model.set(1, 0, '1');
        model.set(1, 1, '0');
        model.set(1, 2, '1');
        model.set(2, 0, '0');
        model.set(2, 1, '0');
        model.set(2, 2, '1');

        assertTrue(model.isFull());
    }

    @Test
    public void testRoundCompletionWin(){
        Model model = new Model();

        model.set(0, 0, '0');
        model.set(0, 1, '1');
        model.set(0, 2, '1');
        model.set(1, 0, '0');
        model.set(1, 1, '0');
        model.set(1, 2, '1');
        model.set(2, 0, '1');
        model.set(2, 1, '1');
        model.set(2, 2, '0');

        String[] expectedSequence = {"0,0", "1,1", "2,2"};

        assertArrayEquals(expectedSequence, model.detectWin());
    }
}
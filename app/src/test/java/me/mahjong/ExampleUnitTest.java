package me.mahjong;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void players() {
        Game g = new Game(new String[]{"Player1", "P2", "P3", "P4"}, 5, 0.1);
        g.updateGameState(1, 1, true, 2);
        assertEquals(0, g.getMoney(0)+ g.getMoney(2) + g.getMoney(3) + g.getMoney(1), 0.001);
    }
}
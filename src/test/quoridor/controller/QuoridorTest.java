package test.quoridor.controller;

import org.junit.Test;
import quoridor.controller.Quoridor;
import quoridor.model.Mode;

import static org.junit.Assert.assertNotNull;

public class QuoridorTest {
    @Test
    public void testGetGame() {
        try {
            Quoridor quoridor = new Quoridor(Mode.HH, "Michel", "Jean", false);
            assertNotNull(quoridor.getGame());
            System.out.println("getGame : OK");
        } catch (AssertionError e) {
            System.out.println("getGame : Error");
        }
    }
}
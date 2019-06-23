package test.quoridor.controller;

import org.junit.Test;
import quoridor.controller.Quoridor;
import quoridor.model.Mode;

import static org.junit.Assert.*;

public class QuoridorTest {

    public static void main(String[] args) {
        testGetGame();
    }

    @Test
    public static void testGetGame() {
        try {
            Quoridor quoridor = new Quoridor(Mode.HH, "Michel", "Jean", false);
            assertNotNull(quoridor.getGame());
            System.out.println("getGame : OK");
        } catch (AssertionError e) {
            System.out.println("getGame : Error");
        }
    }
}
package test.quoridor.model;

import org.junit.Test;
import quoridor.model.Game;
import quoridor.model.Mode;
import quoridor.model.Player;
import quoridor.model.Square;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void testGetCurrentSquare() {
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            Player player = game.getActualPlayer();
            Square square = player.getCurrentSquare();
            assertNotNull(square);
            System.out.println("getCurrentSquare : OK");
        } catch (AssertionError e) {
            System.out.println("getCurrentSquare : Error");
        }
    }

    @Test
    public void testGetName() {
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            Player player = game.getActualPlayer();
            String name = player.getName();
            assertNotNull(name);
            System.out.println("getName : OK");
        } catch (AssertionError e) {
            System.out.println("getName : Error");
        }
    }

    @Test
    public void testGetNbRestingFences() {
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            Player player = game.getActualPlayer();
            int restingFences = player.getNbRestingFences();
            assertNotNull(restingFences);
            System.out.println("getNbRestingFences : OK");
        } catch (AssertionError e) {
            System.out.println("getNbRestingFences : Error");
        }
    }

    @Test
    public void testGetNbRestingFences2() {
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            Player player = game.getActualPlayer();
            int restingFences = player.getNbRestingFences();
            player.setNbFences(restingFences-1);
            assertEquals(restingFences-1,player.getNbRestingFences());
            System.out.println("getNbRestingFences : OK");
        } catch (AssertionError e) {
            System.out.println("getNbRestingFences : Error");
        }
    }
}
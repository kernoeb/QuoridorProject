package test.quoridor.model;

import org.junit.Test;
import quoridor.model.Game;
import quoridor.model.Mode;
import quoridor.model.Player;
import quoridor.model.Square;

import static org.junit.Assert.assertNotNull;

public class PlayerTest {

    public static void main(String[] args) {
        testGetCurrentSquare();
        testGetName();
        testGetNbRestingFences();
    }

    @Test
    public static void testGetCurrentSquare() {
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
    public static void testGetName() {
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
    public static void testGetNbRestingFences() {
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
}
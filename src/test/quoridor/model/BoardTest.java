package test.quoridor.model;

import org.junit.Test;
import quoridor.model.*;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class BoardTest {
    public static void main(String[] args) {
        System.out.println("Test Board");
        testBoard();
        testListOfPossibilitiesFence();
        testListOfPossibilitiesPawn();
        testGetSIZE();
        testGetGrid();
        testGetTotalSize();
    }

    @Test
    public static void testBoard() {
        try {
            Board board = new Board();
            assertNotNull(board);
            System.out.println("testBoard : OK");
        } catch (AssertionError e) {
            System.out.println("testBoard : Error");
        }
    }

    @Test
    public static void testListOfPossibilitiesFence() {
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            Player player = new AutoPlayer(game, "Michel", game.getBoard(), 0, 0, false);
            Object[][] arrayPossibilitiesFence = game.getBoard().listOfPossibilitiesFence(player);
            assertNotNull(arrayPossibilitiesFence);
            System.out.println("testListOfPossibilitiesFence : OK");
        } catch (AssertionError e) {
            System.out.println("testListOfPossibilitiesFence : Error");
        }
    }

    @Test
    public static void testListOfPossibilitiesPawn() {
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            Player player = new AutoPlayer(game, "Michel", game.getBoard(), 0, 0, false);
            ArrayList<Square> arrayPossibilitiesPawn = game.getBoard().listOfPossibilitiesPawn(player);
            assertNotNull(arrayPossibilitiesPawn);
            System.out.println("testListOfPossibilitiesPawn : OK");
        } catch (AssertionError e) {
            System.out.println("testListOfPossibilitiesPawn : Error");
        }
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            assertNull(game.getBoard().listOfPossibilitiesPawn(null));
            System.out.println("testListOfPossibilitiesPawn : Error");
        } catch (NullPointerException e) {
            System.out.println("testListOfPossibilitiesPawn : OK");
        }
    }

    @Test
    public static void testGetSIZE() {
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            int size = game.getBoard().getSIZE();
            assertNotNull(size);
            System.out.println("getSIZE : OK");
        } catch (AssertionError e) {
            System.out.println("getSIZE : Error");
        }
    }

    @Test
    public static void testGetGrid() {
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            assertNotNull(game.getBoard().getGrid());
            System.out.println("getGrid : OK");
        } catch (AssertionError e) {
            System.out.println("getGrid : Error");
        }
    }

    @Test
    public static void testGetTotalSize() {
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            int size = game.getBoard().getTotalSize();
            assertNotNull(size);
            System.out.println("getTotalSize : OK");
        } catch (AssertionError e) {
            System.out.println("getTotalSize : Error");
        }
    }
}
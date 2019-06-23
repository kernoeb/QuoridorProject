package test.quoridor.model;

import org.junit.Test;
import quoridor.model.Board;
import quoridor.model.Game;
import quoridor.model.Mode;
import quoridor.model.Player;

import static org.junit.Assert.assertNotNull;

public class GameTest {

    public static void main(String[] args) {
        getBoard();
        getPlayer1();
        getPlayer2();
        getActualPlayer();
    }

    @Test
    public static void getBoard() {
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            Board board = game.getBoard();
            assertNotNull(board);
            System.out.println("getBoard : Ok");
        } catch (AssertionError e) {
            System.out.println("getBoard : Error");
        }
    }

    @Test
    public static void getPlayer1() {
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            Player player = game.getPlayer1();
            assertNotNull(player);
            System.out.println("getPlayer1 : Ok");
        } catch (AssertionError e) {
            System.out.println("getPlayer1 : Error");
        }
    }

    @Test
    public static void getPlayer2() {
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            Player player = game.getPlayer2();
            assertNotNull(player);
            System.out.println("getPlayer2 : Ok");
        } catch (AssertionError e) {
            System.out.println("getPlayer2 : Error");
        }
    }

    @Test
    public static void getActualPlayer() {
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            Player player = game.getActualPlayer();
            assertNotNull(player);
            System.out.println("getActualPlayer : Ok");
        } catch (AssertionError e) {
            System.out.println("getActualPlayer : Error");
        }
    }
}
package test.quoridor.model;

import org.junit.Test;
import quoridor.model.Game;
import quoridor.model.Mode;
import quoridor.model.Player;

import static org.junit.Assert.assertEquals;

public class HumanPlayerTest {

    public static void main(String[] args) {
        play();
    }

    @Test
    public static void play() {
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            Player player = game.getPlayer1();
            player.movePawn(5, 4);

            assertEquals(true, player.getCurrentSquare().getX() == 5);
            assertEquals(true, player.getCurrentSquare().getY() == 4);
            System.out.println("play : OK");
        } catch (AssertionError e) {
            System.out.println("play : Error");
        }
    }
}
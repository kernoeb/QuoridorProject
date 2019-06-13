package test;

import org.junit.*;
import quoridor.*;

import static org.junit.Assert.*;

public class TestHumanPlayer {
    @Test
    public static void testHumanPlayer(){
        HumanPlayer humanPlayer = new HumanPlayer("joueur");
        assertNotNull(humanPlayer);
        assertEquals(humanPlayer,new HumanPlayer("joueur"));
        humanPlayer = new HumanPlayer(null);
        assertNull(humanPlayer);
    }
    @Test
    public static void testcheckNbRestingFences() {
        HumanPlayer humanPlayer = new HumanPlayer("joueur");
        humanPlayer.setNbFences(20);
        assertEquals(humanPlayer.checkNbRestingFences(),20);
        humanPlayer.setNbFences(0);
        assertEquals(humanPlayer.checkNbRestingFences(),0);
    }
    @Test
    public static void testCheckHasFinished(){
        Game game = new Game();
        assertTrue(game.getPlayer1().checkHasFinished());
        game.getPlayer2().getPawn().setPosition(0,0);
        assertFalse(game.getPlayer2().checkHasFinished());
    }
    @Test
    public static void testCheckExistingPath(){
        Game game = new Game();
        assertTrue(game.getPlayer1().checkExistingPath());
        game.getPlayer1().getPawn().setPosition(0,0);
        game.getBoard().getGrid().get(0).setFenceN();
        game.getBoard().getGrid().get(0).setFenceE();
        game.getBoard().getGrid().get(0).setFenceS();
        game.getBoard().getGrid().get(0).setFenceW();
        assertFalse(game.getPlayer1().checkExistingPath());
    }
}

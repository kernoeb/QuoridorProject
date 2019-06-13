package test;

import org.junit.*;
import quoridor.*;

import static org.junit.Assert.*;

public class TestAutoPlayer {
    @Test
    public static void testAutoplayer(){
        AutoPlayer autoPlayer = new AutoPlayer("joueur");
        assertNotNull(autoPlayer);
        assertEquals(autoPlayer,new AutoPlayer("joueur"));
        autoPlayer = new AutoPlayer(null);
        assertNull(autoPlayer);
    }
    @Test
    public static void testcheckNbRestingFences() {
        AutoPlayer autoPlayer = new AutoPlayer("joueur");
        autoPlayer.setNbFences(20);
        assertEquals(autoPlayer.checkNbRestingFences(),20);
        autoPlayer.setNbFences(0);
        assertEquals(autoPlayer.checkNbRestingFences(),0);
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

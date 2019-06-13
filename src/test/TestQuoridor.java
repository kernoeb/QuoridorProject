package test;

import org.junit.*;
import quoridor.*;

import static org.junit.Assert.*;

public class TestQuoridor {
    @Test
    public static void testQuoridor(){
        Quoridor quoridor = new Quoridor("./test/");
        assertNotNull(quoridor);
        quoridor = new Quoridor(null);
        assertNotNull(quoridor);
    }
    @Test
    public static void testLoadOldGameAndTestSaveGame(){
        Quoridor quoridor = new Quoridor("./test/");
        quoridor.saveGame(quoridor.getGame());
        assertNotNull(quoridor.loadOldGame());
    }
}

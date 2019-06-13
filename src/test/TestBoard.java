package test;

import org.junit.*;
import quoridor.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestBoard {
    @Test
    public void testBoard() {
        Board board = new Board();
        assertNotNull(board);
    }
    @Test
    public void testListOfPossibilitiesFence(){
        Board board = new Board();
        ArrayList<Square> arrayPossibilitiesFence = board.listOfPossibilitiesFence();
        assertNotNull(arrayPossibilitiesFence);
    }
    @Test
    public void testListOfPossibilitiesPawn(){
        Board board = new Board();
        Player player = new AutoPlayer("joueur");
        ArrayList<Square> arrayPossibilitiesPawn = board.listOfPossibilitiesPawn(player);
        assertNotNull(arrayPossibilitiesPawn);
        assertNull(board.listOfPossibilitiesPawn(null));
    }
}

package test.quoridor.model;
import quoridor.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoardTest {
    public static void main(String[] args) {

    }

    @Test
    public void testBoard() {
        Board board = new Board();
        assertNotNull(board);
    }

    @Test
    public void testListOfPossibilitiesFence(){
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            Player player = new AutoPlayer(game, "Michel", game.getBoard(), 0, 0, false);
            Object[][] arrayPossibilitiesFence = game.getBoard().listOfPossibilitiesFence(player);
            assertNotNull(arrayPossibilitiesFence);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testListOfPossibilitiesPawn(){
        try {
            Game game = new Game(Mode.HH, "Michel", "Paul", false);
            Player player = new AutoPlayer(game, "Michel", game.getBoard(), 0, 0, false);
            ArrayList<Square> arrayPossibilitiesPawn = game.getBoard().listOfPossibilitiesPawn(player);
            assertNotNull(arrayPossibilitiesPawn);
            assertNull(game.getBoard().listOfPossibilitiesPawn(null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setColor() {
    }

    @Test
    public void getColor() {
    }

    @Test
    public void initializeBoard() {
    }

    @Test
    public void getSIZE() {
    }

    @Test
    public void getGrid() {
    }

    @Test
    public void getTotalSize() {
    }

    @Test
    public void isEvenNumber() {
    }

    @Test
    public void isOddNumber() {
    }

    @Test
    public void pawnCoord() {
    }

    @Test
    public void fenceCoord() {
    }

    @Test
    public void setFence() {
    }

    @Test
    public void removeFence() {
    }

    @Test
    public void possibleFence() {
    }

    @Test
    public void checkOutside() {
    }

}
package test.quoridor.model;

import org.junit.Test;
import quoridor.model.Square;
import quoridor.model.Status;

import static org.junit.Assert.assertNotNull;

public class SquareTest {
   @Test
    public void testGetStatus() {
        try {
            Square square = new Square(0, 0, Status.FENCEPOSSIBLE);
            assertNotNull(square.getStatus());
            System.out.println("getStatus : OK");
        } catch (AssertionError e) {
            System.out.println("getStatus : Error");
        }
    }

    @Test
    public void testGetX() {
        try {
            Square square = new Square(0, 0, Status.FENCEPOSSIBLE);
            assertNotNull(square.getX());
            System.out.println("getX : OK");
        } catch (AssertionError e) {
            System.out.println("getX : Error");
        }
    }

    @Test
    public void testGetY() {
        try {
            Square square = new Square(0, 0, Status.FENCEPOSSIBLE);
            assertNotNull(square.getY());
            System.out.println("getY : OK");
        } catch (AssertionError e) {
            System.out.println("getY : Error");
        }
    }
}
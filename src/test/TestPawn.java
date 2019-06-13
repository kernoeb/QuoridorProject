package test;

import org.junit.*;
import quoridor.*;

import static org.junit.Assert.*;

public class TestPawn {
    @Test
    public static void testPawn(){
        Pawn pawn = new Pawn(0,0);
        assertNotNull(pawn);
        pawn = new Pawn(10,10);
        assertNotNull(pawn);
        pawn = new Pawn(-1,-1);
        assertNull(pawn);
        pawn = new Pawn(100,100);
        assertNull(pawn);
    }
    @Test
    public static void testSetPosition(){
        Pawn pawn = new Pawn(0,0);
        pawn.setPosition(5,5);
        assertEquals(pawn,new Pawn(5,5));
        pawn = new Pawn(0,0);
        pawn.setPosition(-1,-1);
        assertEquals(pawn,new Pawn(0,0));
        pawn = new Pawn(0,0);
        pawn.setPosition(100,100);
        assertEquals(pawn,new Pawn(0,0));
    }
}

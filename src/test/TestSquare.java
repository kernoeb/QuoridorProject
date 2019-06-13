package test;

import org.junit.*;
import quoridor.*;

import static org.junit.Assert.*;

public class TestSquare {
    @Test
    public static void TestSquare(){
        Square square = new Square();
        assertNotNull(square);
    }
}

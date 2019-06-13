import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestJunit {
    public static void main(String[] args){
        System.out.println("sa marche !");
    }
   @Test
	
   public void testAdd() {
      String str = "Junit is working fine";
      assertEquals("Junit is working fine",str);
   }
}
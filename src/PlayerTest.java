package bowling_scoring_system;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PlayerTest{
    @Test
    public void testPlayer(){
        Player p = new Player("Bob");
        assertEquals("Bob", p.name);
    }
    
    public void testScore() {
    	Player p = new Player("Bob");
    	p.balls[0][0] = 3;
    	p.balls[1][0] = 4; 
    	
    	int expected = 7;
    	p.calculateFrame(0);
    	int actual = p.frames[0]; 
    	assertEquals(expected, actual);
    	
    }
}
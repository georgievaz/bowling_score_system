package bowling_score_system;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PlayerTest{
    Player p;

    @Before
    public void init(){
        p = new Player("Bob");
    }


    @Test
    public void testPlayer(){
        assertEquals("Bob", p.name);
    }

    @Test
    public void testScore() {
    	p.balls[0][0] = 3;
    	p.balls[1][0] = 4; 
    	
    	int expected = 7;
    	p.calculateFrame(0);
    	int actual = p.frames[0]; 
    	assertEquals(expected, actual);
    	
    }
}
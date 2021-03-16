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
        p.setBall(3);
        p.setBall(4);
    	
    	int expected = 7;
    	p.calculateFrame(0);
    	int actual = p.frames[0]; 
    	assertEquals(expected, actual);
    	
    }

    @Test
    public void calcScore(){
        p.setBall(2);
        p.setBall(3);
        p.setBall(2);
        p.setBall(4);

        int expected = 11;
        int actual = p.frames[1];
        assertEquals(expected, actual);
    }

    @Test
    public void calcSpare(){
        p.balls[0][0] = 2;
        p.balls[1][0] = 8;
        p.balls[0][1] = 2;
        p.balls[1][1] = 4;

        int expected = 18;
        p.calculateFrame(0);
        p.calculateFrame(1);
        int actual = p.frames[1];
        assertEquals(expected, actual);
    }

    @Test
    public void calcStrike(){
        p.setBall(10);
        p.setBall(2);
        p.setBall(4);

        int expected = 22;
        int actual = p.frames[1];
        assertEquals(expected, actual);
    }

    @Test
    public void calcMultStrike(){
        p.balls[0][0] = 10;
        p.balls[0][1] = 10;
        p.balls[0][2] = 10;

        int expected = 60;
        p.calculateFrame(0);
        p.calculateFrame(1);
        p.calculateFrame(2);
        int actual = p.frames[2];
        assertEquals(expected, actual);
    }

    @Test
    public void calcPerfGame(){

        for(int i = 0; i < 11; i++){
            p.balls[0][i] = 10;
            if(i == 10) p.balls[1][i] = 10;
            p.calculateFrame(i);
        }
        int expected = 300;
        int actual = p.frames[10];
        assertEquals(expected, actual);
    }



}
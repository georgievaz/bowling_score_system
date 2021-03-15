package bowling_score_system;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Assert;

import java.util.Scanner;

public class GameTest {
    Scanner scan = new Scanner(System.in);
    Game game;

    @Before
    public void init(){
        game = new Game(scan);
    }

    @Test
    public void addTest(){
        game.add("Bob");
       Assert.assertFalse(game.players.isEmpty());
    }

    @Test
    public void addName(){
        game.add("Bob");
        String expected = "Bob";
        String actual = game.players.get(0).name;
        Assert.assertEquals(expected, actual);
    }


}

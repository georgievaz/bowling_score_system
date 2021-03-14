package bowling_scoring_system;

import java.util.Scanner;

public class Bowling {


    public static void main (String [] args) {
        Scanner scan = new Scanner(System.in);
       
       loop: do{
            Game game = new Game(scan);
            //start game 
            game.start();

            System.out.println("\nStart another game? (y/n)");
            String answer = scan.nextLine();
            switch (answer){
                case "y": break; //restart do/while with another game
                case "n": break loop; //escape do/while and close scanner
                default: break; // ask the question again, if the user doesn't answer with y/n
            }
        }while(true);  

        scan.close();   
    }
}

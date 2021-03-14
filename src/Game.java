
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public List<Player> players = new ArrayList<Player>();
    Scanner scan;

    public Game(Scanner scan){
        this.scan = scan; 
    }

    public void listPlayers(){
        System.out.println("\nThis game includes: ");
        players.forEach(player -> System.out.println(player.name));
    }

    public void add(String name){
        players.add(new Player(name));
    }

    public void addPlayers(Scanner scan){
        
        System.out.println("\nFirst player is called:"); 
        String playerName = scan.nextLine();
        add(playerName); 
        String answer = "";
        

        // loop until all players are added
       loop: do { 
            System.out.println("\nAdd another player? (y/n)");
            answer = scan.nextLine();
            switch (answer){
                case "y":
                    System.out.println("\nNext player is called:");
                    playerName = scan.nextLine();
                    add(playerName);
                    break;
                case "n":
                    listPlayers();
                    break loop;
                default: break; // ask the question again, if the user doesn't answer with y/n
            }
        }while(true);
    }

    public void frame(int frame, Scanner scan){
        players.forEach(player -> {
           player.balls[0][frame - 1] = player.throwBall(1, frame, scan);
           // case for extra toss after the 10th frame
           if(player.ball == 10 && frame == 10){
                int extra[] = player.throwExtra(2, "X", scan);
                player.balls[0][10] = extra[0];
                player.balls[1][10] = extra[1];
           }else if (player.ball < 10){
                int last = player.ball;
                player.balls[1][frame - 1] = player.throwBall(2, frame, scan);
                // case for extra toss after the 10th frame 
                if((player.ball + last) == 10 && frame == 10){
                        player.balls[0][10] = player.throwExtra(1, "X", scan)[0];
                }
           }
           player.calculateFrame(frame - 1);
           player.printScore();
        });
    }

    public void start(){
        //set up players
        System.out.println("\nThis is a bowling score system");
        addPlayers(scan);

        //start game
        for(int i = 0; i < 10; i++){
            int frame = i  + 1;
            frame(frame, scan);
        }
    }
}
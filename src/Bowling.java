import java.util.Scanner;

public class Bowling {

    public  static void nl(){System.out.println();}

    public static void main (String [] args) {
        Scanner scan = new Scanner(System.in);
        
        //set up the game and the players 
        Game game = new Game();
        nl(); 
        System.out.println("This is a bowling score system");
        nl();
        System.out.println("First player is called:"); 
        String playerName = scan.nextLine();
        game.players.add(new Player(playerName)); 
        String answer = "";
        

        // loop until all players are added
       loop: do {
            nl(); 
            System.out.println("Add another player? (y/n)");
            answer = scan.nextLine();
            switch (answer){
                case "y":
                    nl();
                    System.out.println("Next player is called:");
                    playerName = scan.nextLine();
                    game.players.add(new Player(playerName));
                    break;
                case "n":
                    nl();
                    System.out.println("This game includes: ");
                    game.players.forEach(player -> System.out.println(player.name));
                    break loop;
                default: break; // ask the question again, if the user doesn't answer with y/n
            }
        }while(true);

        //start game 

        for(int i = 0; i < 10; i++){
            int frame = i + 1; 
            game.players.forEach(player -> {     
                nl();
                //only accepts int [0; 10] 
                do{
                    System.out.println("Ball 1, Frame " + frame + "\n" + player.name + " scored: ");
                    player.ball = scan.nextInt();
                    if(player.ball <= 10 && player.ball > 0)break;
                    else System.out.println("Please enter a valid number (0-10"); 
                }while(true); 
                player.balls[0][frame - 1] = player.ball;

                //case for extra throws after the 10th frame
                if(player.ball == 10 && frame == 10){                        
                        nl(); //only accepts int [0; 10]
                        do{
                            System.out.println("Extra throw 1/2, Frame" + frame + "\n" + player.name + " scored: ");
                            player.ball = scan.nextInt();
                            if(player.ball <= 10 && player.ball > 0)break;
                        else System.out.println("Please enter a valid number (0-10"); 
                        }while(true);
                        player.balls[0][10] = player.ball;

                        nl(); //only accepts int [0; 10]
                        do{
                            System.out.println("Extra throw 2/2, Frame" + frame + "\n" + player.name + " scored: ");
                            player.ball = scan.nextInt();
                            if(player.ball <= 10 && player.ball > 0)break;
                            else System.out.println("Please enter a valid number (0-10"); 
                        }while(true);
                        player.balls[1][10] = player.ball;

                }else if (player.ball < 10){
                    int last = player.ball;
                    nl(); //only accepts int [0; 10] && the sum of the last 2 balls should be <= 10
                    do{
                        System.out.println("Ball 2, Frame " + frame + "\n" + player.name + " scored:");
                        player.ball = scan.nextInt();
                        if(player.ball <= 10 && player.ball > 0 && (player.ball + last) <= 10)break;
                        else System.out.println("Please enter a valid number (0-10"); 
                    }while(true);
                    player.balls[1][frame - 1] = player.ball;

                    //case for extra throw after the 10th frame
                    if(frame == 10 && (last + player.ball) == 10){
                        nl(); //only accepts int [0; 10]
                        do{
                            System.out.println("Extra throw, Frame" + frame + "\n" + player.name + " scored: ");
                            player.ball = scan.nextInt();
                            if(player.ball <= 10 && player.ball > 0)break;
                        else System.out.println("Please enter a valid number (0-10"); 
                        }while(true);
                        player.balls[0][10] = player.ball;
                    }
                }    
                player.printScore();
            });
        }
    }
}

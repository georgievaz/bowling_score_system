public class Player {
    // 10 + 1 frames per game per player
    int frames[] = new int[11]; 
    // 2-dim Array for 2 balls per frame + 1 extra frame
    int balls[][] = new int[2][11];
    int score; //the player's current score 
    String name;
    int ball; 

    public Player(String name){
        this.name = name;
    }

    public void printScore(){

        //calculate score of the player 
        for(int i = 0; i < 10; i++){
            if(balls[0][i] == 10){
                // handles two consecutive strikes 
                if(balls[0][i+1] == 10){
                    frames[i] = score + 10 + balls[0][i+1] + balls[0][i+2];
                }else{
                    frames[i] = score + 10 + balls[0][i+1] + balls[1][i+1];
                }                 
                continue;
            }else if(balls[0][i] + balls[1][i] == 10){
                frames[i] = score + 10 + balls[0][i+1]; 
                
            }else {
                frames[i] = balls[0][i] + balls[1][i];
            }
            score = frames[i];
        }

        //print score 
        System.out.println();
        System.out.println(name);
        System.out.printf ("%n %10s", "Frame");
        for(int j = 1; j <= 11; j++){
            //extra throws 
            if(j == 11) System.out.printf("%6s", "X");
            else System.out.printf("%6d", j);
        }
        System.out.printf("%n %10s", "Ball 1");
        for(int j = 0; j < 11; j++){
            System.out.format("%6d", balls[0][j]);
        }
        System.out.printf("%n %10s", "Ball 2");
        for(int j = 0; j < 11; j++){
            System.out.format("%6d", balls[1][j]);
        }
        System.out.printf("%n %10s", "Score");
        for(int j = 0; j < 11; j++){
            System.out.format("%6d", frames[j]);
        }
        System.out.println();
    }
}
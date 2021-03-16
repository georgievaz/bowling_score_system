package bowling_score_system;

import java.util.Scanner;

public class Player {
    // 10 + 1 frames per game per player
    int[] frames = new int[11];
    // 2-dim Array for 2 balls per frame + 1 extra frame
    int[][] balls = new int[2][11];
    int score; //the player's current score 
    String name;
    int ball;
    int currFrame;
    int currBall;

    public Player(String name){
        this.name = name;
    }

    public void setBall(int toss){
        if(currBall == 0) {
            balls[currBall][currFrame] = toss;
            if(isStrike(currFrame)){
                calculateFrame(currFrame);
                currFrame += 1;
            }
            else currBall += 1;
        }
        else{
            balls[currBall][currFrame] = toss;
            calculateFrame(currFrame);

            currBall -= 1;
            currFrame += 1;

        }
    }

    public boolean isValid(int ball){
        if(currBall == 0) return(ball <= 10 && ball >= 0);
        else {
            if(isNotLastFrame(currFrame)){
                int last = balls[0][currFrame];
                return(ball + last <= 10);
            }else return(ball <= 10 && ball >= 0); //allow 2 strikes in the extra frame
        }
    }


    private boolean isStrike(int frame) {

        return (balls[0][frame] == 10);

    }
    private boolean isSpare(int frame) {

        return ((balls[0][frame] + balls[1][frame]) == 10);

    }


    public static boolean isNotLastFrame(int frame) {
        return frame != 10;
    }

    public void throwBall(Scanner scan){
            //only accepts int [0; 10] && the sum of the last 2 balls should be <= 10
            for(int i = 0; i < 2; i++){
                do{
                    System.out.println("\n Ball" + (currBall+1) + ", Frame " + (currFrame + 1) + "\n" + name + " scored:");
                    ball = scan.nextInt();
                    if(isValid(ball))break;
                    else System.out.println("Please enter a valid number (0-10)");
                }while(true);
                setBall(ball);
            }
    }

    public void throwExtra(Scanner scan ) {
        if(isStrike(currBall)){
            for(int i = 0; i < 2; i++){
                do{
                    System.out.println("Extra throw" + (i+1) +"/2, Frame " + currFrame + "\n" + name + " scored: ");
                    ball = scan.nextInt();
                    if(isValid(ball))break;
                    else System.out.println("Please enter a valid number (0-10)");
                }while(true);
                setBall(ball);
            }

        }else {
            do{
                System.out.println("Extra throw, Frame " + currFrame + "\n" + name + " scored: ");
                ball = scan.nextInt();
                if(isValid(ball))break;
                else System.out.println("Please enter a valid number (0-10)");
            }while(true);
            setBall(ball);
        }
    }

    public void calculateFrame(int frame){
    	
        if(frame == 0)score = 0;
        else {

	        //add bonus points for strike/spare 
	        if(isStrike(frame-1) || isSpare(frame-1) ){
	        	calculateFrame(frame - 1);
	        }
            score = frames[frame - 1];
        }        
	
        if(isStrike(frame) && isNotLastFrame(frame)){
            // handles two consecutive strikes
            if(isStrike(frame + 1) && frame < 9){
                frames[frame] = score + 10 + balls[0][frame+1] + balls[0][frame+2];
            }else{
                frames[frame] = score + 10 + balls[0][frame+1] + balls[1][frame+1];
            }       
        }else if(isSpare(frame) && isNotLastFrame(frame)){
            frames[frame] = score + 10 + balls[0][frame+1];
            
        }else {
            frames[frame] = score + balls[0][frame] + balls[1][frame];
        }        
        
    }


    public void printScore(){
         
        System.out.println();
        System.out.println(name);
        System.out.printf ("%n %10s", "Frame");
        for(int j = 1; j <= 11; j++){
            //extra throws 
            if(j == 11) System.out.printf("%6s", "X");
            else System.out.printf("%6d", j);
        }
        System.out.printf("%n %10s", "Ball 1");
        for(int j = 0; j <= 10; j++){
            System.out.format("%6d", balls[0][j]);
        }
        System.out.printf("%n %10s", "Ball 2");
        for(int j = 0; j <= 10; j++){
            System.out.format("%6d", balls[1][j]);
        }
        System.out.printf("%n %10s", "Score");
        for(int j = 0; j <= 10; j++){
            System.out.format("%6d", frames[j]);
        }
        System.out.println();
    }
}
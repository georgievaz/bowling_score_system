package bowling_scoring_system;

import java.util.Scanner;

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

    public int throwBall(int toss, int frame, Scanner scan){
        switch(toss){
            case 1: 
                    //only accepts int [0; 10] 
                    do{
                        System.out.println("\n Ball 1, Frame " + frame + "\n" + name + " scored: ");
                        ball = scan.nextInt();
                        if(ball <= 10 && ball >= 0)break;
                        else System.out.println("Please enter a valid number (0-10)"); 
                    }while(true); 
                    return ball;
            case 2: int last = ball;
                    //only accepts int [0; 10] && the sum of the last 2 balls should be <= 10
                    do{
                        System.out.println("\n Ball 2, Frame " + frame + "\n" + name + " scored:");
                        ball = scan.nextInt();
                        if(ball <= 10 && ball >= 0 && (ball + last) <= 10)break;
                        else System.out.println("Please enter a valid number (0-10)"); 
                    }while(true);
                    return ball;
            default: return 0;
        }
    }

    public int throwExtra(int toss, String frame, Scanner scan )[]{
        switch(toss){
            case 1: do{
                        System.out.println("Extra throw, Frame " + frame + "\n" + name + " scored: ");
                        ball = scan.nextInt();
                        if(ball <= 10 && ball >= 0)break;
                    else System.out.println("Please enter a valid number (0-10)"); 
                    }while(true);

                    return new int[] {ball, 0};
            case 2: do{
                        System.out.println("Extra throw 1/2, Frame " + frame + "\n" + name + " scored: ");
                        ball = scan.nextInt();
                        if(ball <= 10 && ball >= 0)break;
                    else System.out.println("Please enter a valid number (0-10)"); 
                    }while(true);

                    int last = ball; 
                    do{
                        System.out.println("Extra throw 2/2, Frame " + frame + "\n" + name + " scored: ");
                        ball = scan.nextInt();
                        if(last == 10 && ball <= 10 && ball >= 0)break;
                        else if(ball <= 10 && ball >= 0 && (ball + last) <= 10)break;
                        else System.out.println("Please enter a valid number (0-10)"); 
                    }while(true);

                    return new int[]{last, ball};
            
            default: return new int[2];

        }
    }

    public void calculateFrame(int i){
        if(i == 0)score = 0;
        else score = frames[i - 1];

        if(balls[0][i] == 10 && i != 10){
            // handles two consecutive strikes
            if(balls[0][i+1] == 10){
                frames[i] = score + 10 + balls[0][i+1] + balls[0][i+2];
            }else{
                frames[i] = score + 10 + balls[0][i+1] + balls[1][i+1];
            }       
        }else if((balls[0][i] + balls[1][i]) == 10 && i != 10){
            frames[i] = score + 10 + balls[0][i+1]; 
            
        }else {
            frames[i] = score + balls[0][i] + balls[1][i];
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
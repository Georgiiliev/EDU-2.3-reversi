package AI.controller;

import java.util.Random;

public class TicTacToeAI {
    public static int counter = 1;
    public static String firstMove;
    public static Integer[] coord = new Integer[1];

    public static void move(){
        Random rand = new Random();
        int i = rand.nextInt() + 1;
        if(counter == 1){
            if(i == 1){
                firstMove = "00";
                coord[0] = 0;
                coord[1] = 0;
            } else if(i == 2){
                firstMove = "02";
                coord[0] = 0;
                coord[1] = 2;
            } else if(i == 3){
                firstMove = "20";
                coord[0] = 2;
                coord[1] = 0;
            } else if(i == 4){
                firstMove = "22";
                coord[0] = 2;
                coord[1] = 0;
            }
        }
        counter++;
    }
}

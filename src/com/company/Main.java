package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    PacMan player = new PacMan();
        movePacMan(player);
    }

    public static void movePacMan(PacMan player){
        Scanner userInput = new Scanner(System.in);
        String moveToDo;
        do {
            System.out.print("Type the move for PacMan (end to end game): ");
            moveToDo = userInput.next();
            moveToDo = moveToDo.toLowerCase();
            if (moveToDo.contentEquals("up"))
                player.moveUp();
            else if (moveToDo.contentEquals("down"))
                player.moveDown();
            else if (moveToDo.contentEquals("left"))
                player.moveLeft();
            else if (moveToDo.contentEquals("right"))
                player.moveRight();
            System.out.println("Player is at x = " + player.getXCoord() + " and y = " + player.getYCoord());
        } while (!moveToDo.contentEquals("end"));


    }
}

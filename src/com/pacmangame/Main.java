package com.pacmangame;
import com.pacmangame.character.*;
import com.pacmangame.map_elements.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	    PacMan player = new PacMan();
        Board gameBoard = new Board(3,3);
        Ghost ghost1 = new Ghost(player, gameBoard);
        System.out.println(ghost1.getName());
	    //movePacMan(player);
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
            System.out.println("Player: [" + player.getXCoord() + " ," + player.getYCoord()+ "]");
        } while (!moveToDo.contentEquals("end"));


    }
}

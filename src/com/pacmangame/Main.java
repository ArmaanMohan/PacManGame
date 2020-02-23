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


}

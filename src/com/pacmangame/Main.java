package com.pacmangame;
import com.pacmangame.character.*;
import com.pacmangame.dependencies.Game;
import com.pacmangame.map_elements.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	    Game game1 = new Game("EasyMap"); //create a new game object
	    game1.playGame(); //launch the game by accessing the playGame method in the game class
    }


}

package com.pacmangame;

import com.pacmangame.character.Ghost;
import com.pacmangame.dependencies.Game;
import com.pacmangame.dependencies.MapSelector;
import com.pacmangame.map_elements.Point;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class PacManTextBased {
    public static void main(String [] args) {
        //Getting map selection from user
        String selectedMap = selectMap();

        //Creating a new game and printing all of the start positions
        Game game1 = new Game(selectedMap);
        printInfo(game1);

        //Prompting and doing the user's move
        String chosenMove = promptUser(game1);
        game1.doMove(chosenMove);

        //As long as the game is supposed to continue, prints the info to console, prompts for moves and does the move
        while (game1.continueGame()) {
            printInfo(game1);
            chosenMove = promptUser(game1);
            int previousLives = game1.getPlayer().getLives();
            game1.doMove(chosenMove);
            int afterLives = game1.getPlayer().getLives();

            //If you have lost a life, uses the findkiller method to print the ghost that killed the player
            if (previousLives > afterLives){
                findKiller(game1);
            }
        }

        //If you have lost, print game over to console and if you have won, print congratulatory message
        if (game1.getPlayer().getLives() <= 0)
            System.out.println("Game over!");
        else
            System.out.println("Congratulations! You have won!");


    }


    /**
     *
     * @return selectedMap
     */
    //Method for selecting map based on user input
    public static String selectMap() {
        MapSelector mapSelector = new MapSelector("src/com/pacmangame/map_elements/Maps/");
        String userChosenMap;

        //Sets up a scanner to allow for console input of desired map
        Scanner userMapInput = new Scanner(System.in);
        System.out.println("Available maps are: " + "" + mapSelector.getPossibleMaps());
        System.out.println("Enter your desired map: ");
        userChosenMap = userMapInput.nextLine();
        mapSelector.selectMap(userChosenMap);

        //Continue to prompt for map until a valid map is selected
        while(!mapSelector.isMapIsSelected()){
            System.out.println("That map does not exist");
            System.out.println("Enter your desired map: ");
            userChosenMap = userMapInput.nextLine();
            mapSelector.selectMap(userChosenMap);
        }
        return mapSelector.getSelectedMap();
    }

    //Method used to print relevant info to player after each turn
    public static void printInfo(Game currentGame){
        //Printing player's coordinates, score and number of lives
        System.out.println("Player: " + "[" + currentGame.getPlayer().getxCoord() + ", " +
                currentGame.getPlayer().getyCoord() + "]" );
        System.out.println("Score: " + currentGame.getPlayer().getScore());
        System.out.println("Lives: " + currentGame.getPlayer().getLives() + "\n");

        //Printing the ghosts's name and its coordinates for each ghost
        for (Ghost g: currentGame.getGhostList()){
            System.out.println(g.getName() + " is at [" + g.getxCoord() + ", " + g.getyCoord() + "]");
        }
        System.out.print("\n");
    }


    //Method for prompting user for each turn
    public static String promptUser(Game currentGame){
        //Creating a scanner object for console input
        String userChosenMove;
        Scanner userMove = new Scanner(System.in);
        System.out.println("Enter your desired move for PacMan: ");

        //Takes the move entered by the user (as lowercase), and if it is not valid prompts again in a loop until
        //user's move is valid
        userChosenMove = userMove.nextLine().toLowerCase();
        while(!currentGame.isValidMove(userChosenMove)){
            System.out.println("That wasn't a valid move please try again");
            System.out.println("Enter your desired move for PacMan: ");
            userChosenMove = userMove.nextLine();
        }

        //Once valid returns the user's chosen move
        return userChosenMove;

    }

    //Method to figure out which ghost "killed" the player
    public static void findKiller(Game currentGame){
        //In a loop, checks each ghost's coordinates and if they are the same as the player, prints that you have
        //been killed by that ghost
        for (Ghost g : currentGame.getGhostList()){
            if (g.getxCoord() == currentGame.getPlayer().getxCoord() && g.getyCoord() == currentGame.getPlayer().getyCoord())
                System.out.println("You've been killed by " + g.getName());
        }
    }


}

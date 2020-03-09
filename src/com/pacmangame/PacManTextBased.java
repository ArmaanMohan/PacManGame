package com.pacmangame;

import com.pacmangame.character.Ghost;
import com.pacmangame.dependencies.Game;
import com.pacmangame.dependencies.MapSelector;
import com.pacmangame.map_elements.Point;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class PacManTextBased {
    public static void main(String [] args) throws IOException {
        String selectedMap = selectMap();
        Game game1 = new Game(selectedMap);
        printInfo(game1);
        String chosenMove = promptUser(game1);
        game1.doMove(chosenMove);
        while (game1.continueGame()) {
            printInfo(game1);
            chosenMove = promptUser(game1);
            int previousLives = game1.player.getLives();
            game1.doMove(chosenMove);
            int afterLives = game1.player.getLives();
            if (previousLives > afterLives){
                findKiller(game1);
            }
        }

        if (game1.player.getLives() <= 0)
            System.out.println("Game over!");
        else
            System.out.println("Congratulations! You have won!");


    }


    public static String selectMap() {
        MapSelector mapSelector = new MapSelector("src/com/pacmangame/map_elements/Maps/");
        String userChosenMap;
        Scanner userMapInput = new Scanner(System.in);
        System.out.println("Available maps are: " + "" + mapSelector.getPossibleMaps());
        System.out.println("Enter your desired map: ");
        userChosenMap = userMapInput.nextLine();
        mapSelector.selectMap(userChosenMap);
        while(!mapSelector.isMapIsSelected()){
            System.out.println("That map does not exist");
            System.out.println("Enter your desired map: ");
            userChosenMap = userMapInput.nextLine();
            mapSelector.selectMap(userChosenMap);
        }
        return mapSelector.getSelectedMap();
    }

    public static void printInfo(Game currentGame){
        System.out.println("Player: " + "[" + currentGame.player.getxCoord() + ", " +
                currentGame.player.getyCoord() + "]" );
        System.out.println("Score: " + currentGame.player.getScore());
        System.out.println("Lives: " + currentGame.player.getLives() + "\n");
        for (Ghost g: currentGame.ghostList){
            System.out.println(g.getName() + " is at [" + g.getxCoord() + ", " + g.getyCoord() + "]");
        }
        System.out.print("\n");
    }



    public static String promptUser(Game currentGame){
        String userChosenMove;
        Scanner userMove = new Scanner(System.in);
        System.out.println("Enter your desired move for PacMan: ");
        userChosenMove = userMove.nextLine().toLowerCase();
        while(!currentGame.isValidMove(userChosenMove)){
            System.out.println("That wasn't a valid move please try again");
            System.out.println("Enter your desired move for PacMan: ");
            userChosenMove = userMove.nextLine();
        }
        return userChosenMove;

    }

    public static void findKiller(Game currentGame){
        for (Ghost g : currentGame.ghostList){
            if (g.getxCoord() == currentGame.player.getxCoord() && g.getyCoord() == currentGame.player.getyCoord())
                System.out.println("You've been killed by " + g.getName());
        }
    }


}

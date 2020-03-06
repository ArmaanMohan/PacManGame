package com.pacmangame.dependencies;

import com.pacmangame.character.*;
import com.pacmangame.map_elements.*;

import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

public class Game  {
	//Instance Variables
    public  PacMan player;
    public  Map currentMap;
    public  ArrayList<Ghost> ghostList;
    public  ArrayList<Point> pointsList;
    public  ArrayList<Obstacle> obstacleList;
    public  ArrayList<Point> gottenPoints;

    public Game(String selectedMap)   {
    	//Right now it is hard coded, locations of obstacles, points, ghosts and map
        String pointsFileName = "PointsLocations.txt";
        String obstaclesFileName = "ObstacleLocations.txt";
        String ghostLocationsFileName = "GhostLocations.txt";
        try {
            if (selectedMap == "Map1") {
                String baseFilePath = "src/com/pacmangame/map_elements/Maps/Map1/";
                //Create new board with appropriate dimensions (what we've decided)
                Board mapBoard = new Board(17, 17);
                //Place the player (pacman) at the centre of the board
                player = new PacMan(8, 8);
                //Create new map (Board with everything on it) with all of the previous data
                currentMap = new Map(mapBoard, baseFilePath + pointsFileName,
                        baseFilePath + obstaclesFileName, baseFilePath + ghostLocationsFileName);
                //Assigns the arrayLists with the appropriate data, from the new map
                ghostList = currentMap.getGhostList();
                pointsList = currentMap.getPointList();
                obstacleList = currentMap.getObstacleList();
            }
            //This next section does essentially the same thing, just with different board, data
            if (selectedMap == null || selectedMap == "EasyMap") {
                String baseFilePath = "src/com/pacmangame/map_elements/Maps/EasyMap/";
                Board mapBoard = new Board(4, 4);
                player = new PacMan(2, 2);
                currentMap = new Map(mapBoard, baseFilePath + pointsFileName,
                        baseFilePath + obstaclesFileName, baseFilePath + ghostLocationsFileName);
                ghostList = currentMap.getGhostList();
                pointsList = currentMap.getPointList();
                obstacleList = currentMap.getObstacleList();
            }
        }
        catch ( IOException io )
        {
            System.out.println("Unable to load file: " + io);
            System.exit(1);
        }
        //Ignore this
        gottenPoints = new ArrayList<>();
    }

    // Does everything to play the game
    public  void playGame(){
    	// Get the users move
        String desiredMove;
        desiredMove = promptUser();
        //Move the pacman and the ghosts
        movePacMan(player, desiredMove);
        moveGhosts();
        update();
        //Unless the pacman has run into a ghost or obtained all the points, the same steps repeat
        while (continueGame()) {
            desiredMove = promptUser();
            movePacMan(player, desiredMove);
            moveGhosts();
            update();
        }



    }

    //Prompt user for their move of pacman (up, down, right or left)
    public  String promptUser(){
        String userChosenMove;
        Scanner userMove = new Scanner(System.in);
        System.out.println("Enter your desired move for PacMan: ");
        userChosenMove = userMove.nextLine().toLowerCase();
        while(!isValidMove(userChosenMove)){
            System.out.println("That wasn't a valid move please try again");
            System.out.println("Enter your desired move for PacMan: ");
            userChosenMove = userMove.nextLine();
        }
        return userChosenMove;

    }

    //Checks to see if any special condition has been met
    public  void update(){
    	//If any of the ghosts are in the same location as pacman (loses a life)
        for (Ghost ghost : ghostList){
            if (player.getxCoord() == ghost.getxCoord() && player.getyCoord() == ghost.getyCoord()){
                player.die();
                System.out.println("You've been killed by " + ghost.getName());
            }
        }
        //If pacman is in the same location as a point, add a point to score and elliminate the 
        //location of that point from the list
        for (int i = 0; i < pointsList.size(); i++){
            Point pointToCheck = pointsList.get(i);
            if (player.getyCoord() == pointToCheck.getyCoord() &&
                    player.getxCoord() == pointToCheck.getxCoord()){
                player.addPoint();
                pointsList.remove(pointToCheck);
            }
        }
        //Prints to consul all the details, will be taken out when GUI is added
        System.out.println("PacMan is at " + "[" + player.getxCoord() + ", " + player.getyCoord() + "]\n");
        System.out.println("Your score is: " + player.getScore() + "\n");
        System.out.println("You have " + player.getLives() + " lives remaining \n");
        for (Ghost ghost : ghostList){
            System.out.println("" + ghost.getName() + " is at " + "[" + ghost.getxCoord() + ", "
                    + ghost.getyCoord() + "]");
        }

    }

    // Checks if the proposed players move is valid, not into an obstacle or board edge
    public  boolean isValidMove(String desiredMove){
    	// Create a copy of the pacman and have it move to the new location
        PacMan dummyPlayer = new PacMan(player);
        //Check to make sure the proposed move is up, down, right or left
        if (!desiredMove.contentEquals("up") && !desiredMove.contentEquals("down") &&
                !desiredMove.contentEquals("left") && !desiredMove.contentEquals("right")){
            return false;}
        else{
            movePacMan(dummyPlayer, desiredMove);
        }
        int playerX = dummyPlayer.getxCoord();
        int playerY = dummyPlayer.getyCoord();
        // If the new location is outside board boundaries or inside an object, return false
        if (playerX < 0 || playerX > currentMap.getGameBoard().getLength()){
            return false;}
        if (playerY < 0 || playerY > currentMap.getGameBoard().getHeight())
            return false;
        ArrayList<Obstacle> obstacleList = currentMap.getObstacleList();
        for (int i = 0; i < obstacleList.size(); i++){
            Obstacle obstacleToCheck = obstacleList.get(i);
            if (playerX == obstacleToCheck.getxCoord() && playerY == obstacleToCheck.getyCoord()){
                return false;}
        }
        return true;

    }

    //Checks if the proposed ghosts move is valid, not into an obstacle or board edge 
    //Same as above function except with ghosts
    public  boolean isValidMove(Ghost ghost, String desiredMove){
    	//Create a copy of a ghost and have it move to the new location
        Ghost dummyGhost = new Ghost(ghost);
        //Check to make sure the ghosts move is up, down, right or left
        if (!desiredMove.contentEquals("up") && !desiredMove.contentEquals("down") &&
                !desiredMove.contentEquals("left") && !desiredMove.contentEquals("right")){
            return false;}
        else{
            moveGhost(dummyGhost, desiredMove);
        }
        int ghostX = dummyGhost.getxCoord();
        int ghostY = dummyGhost.getyCoord();
        // If the new location is outside board boundaries or inside an object, return false
        if (ghostX < 0 || ghostX > currentMap.getGameBoard().getLength())
            return false;
        if (ghostY < 0 || ghostY > currentMap.getGameBoard().getLength())
            return false;
        ArrayList<Obstacle> obstacleList = currentMap.getObstacleList();
        for (int i = 0; i < obstacleList.size(); i++){
            Obstacle obstacleToCheck = obstacleList.get(i);
            if (ghostX == obstacleToCheck.getxCoord() && ghostY == obstacleToCheck.getyCoord())
                return false;
        }
        return true;

    }

    //Moves the pacman the desired direction imputed by the user
    //contentEquals just compares strings as they are objects
    public  void movePacMan(PacMan player, String toMove){
        if (toMove.contentEquals("up"))
            player.moveUp();
        else if (toMove.contentEquals("down"))
            player.moveDown();
        else if (toMove.contentEquals("left"))
            player.moveLeft();
        else if (toMove.contentEquals("right"))
            player.moveRight();
    }

    //Moves the specific ghost the desired direction (will be random, right now has an order)
    public  void moveGhost(Ghost ghost, String toMove){
        if (toMove.contentEquals("up"))
            ghost.moveUp();
        else if (toMove.contentEquals("down"))
            ghost.moveDown();
        else if (toMove.contentEquals("left"))
            ghost.moveLeft();
        else if (toMove.contentEquals("right"))
            ghost.moveRight();
    }

    //Tries to move all the ghosts in the order: up, down, left and right
    //Will eventually make it random
    public  void moveGhosts(){
        ArrayList<String> possibleMoves = new ArrayList<>();
        possibleMoves.add("up");
        possibleMoves.add("down");
        possibleMoves.add("left");
        possibleMoves.add("right");
        ArrayList<Ghost> ghostList = currentMap.getGhostList();
        for (Ghost ghost: ghostList){
            if (isValidMove(ghost, "up"))
                moveGhost(ghost, "up");
            else if (isValidMove(ghost, "down"))
                moveGhost(ghost, "down");
            else if (isValidMove(ghost, "left"))
                moveGhost(ghost, "left");
            else
                moveGhost(ghost, "right");
        }
    }

    //Checks to see if the game should continue 
    //Game will end if there are no points left or no lives left
    public  boolean continueGame(){
        if (pointsList.size() == 0)
            return false;
        if (player.getLives() <= 0)
            return false;
        return true;
    }

}

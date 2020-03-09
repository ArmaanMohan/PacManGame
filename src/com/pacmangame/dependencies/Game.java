package com.pacmangame.dependencies;

import com.pacmangame.character.*;
import com.pacmangame.map_elements.*;
import java.io.IOException;
import java.util.ArrayList;

public class Game {
	//Instance Variables
    public PacMan player;
    public Map currentMap;
    public ArrayList<Ghost> ghostList;
    public ArrayList<Point> pointsList;
    public ArrayList<Obstacle> obstacleList;
    String baseFilePath = "src/com/pacmangame/map_elements/Maps/";
    String pointsFileName = "/PointsLocations.txt";
    String obstaclesFileName = "/ObstacleLocations.txt";
    String ghostLocationsFileName = "/GhostLocations.txt";

    public Game(String selectedMap) {
    	try{
    	    currentMap = new Map(baseFilePath + selectedMap + pointsFileName,
                baseFilePath + selectedMap + obstaclesFileName,
                baseFilePath + selectedMap + ghostLocationsFileName);
    	ToArray dimensionBuilder = new ToArray(baseFilePath + selectedMap + "/Dimensions.txt");
    	ArrayList<String> dimensions = dimensionBuilder.getFileAsString();
        int xDimension = Integer.parseInt(dimensions.get(0));
        int yDimension = Integer.parseInt(dimensions.get(1));
        Board gameBoard = new Board(xDimension-1, yDimension-1);
        currentMap.setGameBoard(gameBoard);
        ghostList = currentMap.getGhostList();
        obstacleList = currentMap.getObstacleList();
        pointsList = currentMap.getPointList();
        int startingX = (int) (Math.floor(xDimension/2));
        int startingY = (int) (Math.floor(yDimension/2));
        player = new PacMan(startingX, startingY);
    	}
    	catch (IOException io){
    	    System.out.println("Unable to load file: " + io);
    	    System.exit(1);
        }

    }
    // Does everything to play the game
    public void doMove(String desiredMove){
        //Move the pacman and the ghosts
        System.out.println("We are moving");
        movePacMan(player, desiredMove);
        moveGhosts();
        update();
    }

    public void update(){
        for (Ghost ghost : ghostList){
            if (player.getxCoord() == ghost.getxCoord() && player.getyCoord() == ghost.getyCoord()){
                player.die();
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
    }


    // Checks if the proposed players move is valid, not into an obstacle or board edge
    public boolean isValidMove(String desiredMove){
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
    public boolean isValidMove(Ghost ghost, String desiredMove){
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
    public void movePacMan(PacMan player, String toMove){
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
    public void moveGhost(Ghost ghost, String toMove){
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
    public void moveGhosts(){
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
    public boolean continueGame(){
        if (pointsList.size() == 0)
            return false;
        if (player.getLives() <= 0)
            return false;
        return true;
    }

}



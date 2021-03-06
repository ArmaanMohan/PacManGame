package com.pacmangame.dependencies;

import com.pacmangame.character.*;
import com.pacmangame.map_elements.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    //Instance Variables
    private PacMan player;
    private  Map currentMap;
    private ArrayList<Ghost> ghostList;
    private ArrayList<Point> pointsList;
    private ArrayList<Obstacle> obstacleList;
    protected final String baseFilePath = "src/com/pacmangame/map_elements/Maps/";
    protected final String pointsFileName = "/PointsLocations.txt";
    protected final String obstaclesFileName = "/ObstacleLocations.txt";
    protected final String ghostLocationsFileName = "/GhostLocations.txt";

    public Game(String selectedMap) {
        try{
            //Creates currentMap based by looking at the files of the selected map's folder
            currentMap = new Map(baseFilePath + selectedMap + pointsFileName,
                    baseFilePath + selectedMap + obstaclesFileName,
                    baseFilePath + selectedMap + ghostLocationsFileName);

            //Getting the dimensions textfile and putting the dimensions in the file into an array using
            //the ToArray class
            ToArray dimensionBuilder = new ToArray(baseFilePath + selectedMap + "/Dimensions.txt");
            ArrayList<String> dimensions = dimensionBuilder.getFileAsString();

            //Converting strings from text file to integers
            int xDimension = Integer.parseInt(dimensions.get(0));
            int yDimension = Integer.parseInt(dimensions.get(1));

            //Correcting for 0-based coordinate system
            Board gameBoard = new Board(xDimension-1, yDimension-1);

            //Setting the current map's board to the gameBoard just created using the dimensions from the text file
            currentMap.setGameBoard(gameBoard);

            //Getting the ghost list, obstacle list, and points list from the map
            ghostList = currentMap.getGhostList();
            obstacleList = currentMap.getObstacleList();
            pointsList = currentMap.getPointList();

            //Sets starting position of player in the middle of the map
            int startingX = (int) (Math.floor(xDimension/2));
            int startingY = (int) (Math.floor(yDimension/2));
            player = new PacMan(startingX, startingY);
        }
        //Catches an IOException by printing the io and exiting using error code 1
        catch (IOException io){
            System.out.println("Unable to load file: " + io);
            System.exit(1);
        }

    }
    // Does everything to play the game
    public void doMove(String desiredMove){
        //Move the pacman and the ghosts
        //System.out.println("We are moving");
        if (isValidMove(desiredMove)) {
            movePacMan(player, desiredMove);
        }
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
        if (playerX < 0 || playerX > currentMap.getGameBoard().getLength() - 1){
            return false;}
        if (playerY < 0 || playerY > currentMap.getGameBoard().getHeight() - 1)
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
        if (ghostX < 0 || ghostX >= currentMap.getGameBoard().getLength())
            return false;
        if (ghostY < 0 || ghostY >= currentMap.getGameBoard().getHeight())
            return false;
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
    public void moveGhosts(){

        Random r = new Random();
        for (Ghost ghost: ghostList){

            //Creating and filling ArrayList with the possible moves
            ArrayList<String> possibleMoves = new ArrayList<>();
            possibleMoves.add("up");
            possibleMoves.add("down");
            possibleMoves.add("left");
            possibleMoves.add("right");

            //Going through the possible moves arrayList and if the move is not valid deleting it from the arrayList
            for (int i = 0; i < possibleMoves.size(); i++){
                if (isValidMove(ghost, possibleMoves.get(i)) == false){
                    possibleMoves.remove(i);
                    i--;
                }
            }

            //If there is a valid move remaining in the list, randomly choose one move from the list and move a ghost
            //that way
            if (possibleMoves.size() > 0){
                int moveIndex = r.nextInt(possibleMoves.size());
                String move = possibleMoves.get(moveIndex);
                this.moveGhost(ghost, move);
            }
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

    public PacMan getPlayer() {
        return player;
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public ArrayList<Ghost> getGhostList() {
        return ghostList;
    }

    public ArrayList<Point> getPointsList() {
        return pointsList;
    }

    public ArrayList<Obstacle> getObstacleList() {
        return obstacleList;
    }
}



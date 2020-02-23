package com.pacmangame.dependencies;

import com.pacmangame.character.*;
import com.pacmangame.map_elements.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    public static PacMan player;
    public static Map currentMap;
    public static ArrayList<Ghost> ghostList;
    public static ArrayList<Point> pointsList;
    public static ArrayList<Obstacle> obstacleList;

    public static void Game(String selectedMap) throws IOException {
        String pointsFileName = "PointsLocations.txt";
        String obstaclesFileName = "ObstaclesLocations.txt";
        String ghostLocationsFileName = "GhostLocations.txt";
        if (selectedMap == "Map1") {
            String baseFilePath = "/src/com/pacmangame/map_elements/Maps/Map1/";
            Board mapBoard = new Board(17, 17);
            player = new PacMan(8,8);
            currentMap = new Map(mapBoard,baseFilePath+pointsFileName,
                    baseFilePath+obstaclesFileName,baseFilePath+ghostLocationsFileName);
            ghostList = currentMap.getGhostList();
            pointsList = currentMap.getPointList();
            obstacleList = currentMap.getObstacleList();

        }
    }

    public static void doMove(){

    }

    public static void prompUser(){

    }

    public static boolean isValidMove(String desiredMove){
        PacMan dummyPlayer = new PacMan(player);
        if (desiredMove != "up" && desiredMove != "down" && desiredMove != "left" && desiredMove != "right")
            return false;
        else{
            movePacMan(dummyPlayer, desiredMove);
        }
        int playerX = dummyPlayer.getXCoord();
        int playerY = dummyPlayer.getYCoord();
        if (playerX < 0 || playerX > currentMap.getGameBoard().getLength())
            return false;
        if (playerY < 0 || playerY > currentMap.getGameBoard().getHeight())
            return false;
        ArrayList<Obstacle> obstacleList = currentMap.getObstacleList();
        for (int i = 0; i < obstacleList.size(); i++){
            Obstacle obstacleToCheck = obstacleList.get(i);
            if (playerX == obstacleToCheck.getxCoord() || playerY == obstacleToCheck.getyCoord())
                return false;
        }
        return true;

    }

    public static boolean isValidMove(Ghost ghost, String desiredMove){
        Ghost dummyGhost = new Ghost(ghost);
        if (desiredMove != "up" && desiredMove != "down" && desiredMove != "left" && desiredMove != "right")
            return false;
        else{
            moveGhost(dummyGhost, desiredMove);
        }
        int ghostX = dummyGhost.getXCoord();
        int ghostY = dummyGhost.getYCoord();
        ArrayList<Obstacle> obstacleList = currentMap.getObstacleList();
        for (int i = 0; i < obstacleList.size(); i++){
            Obstacle obstacleToCheck = obstacleList.get(i);
            if (ghostX == obstacleToCheck.getxCoord() || ghostY == obstacleToCheck.getyCoord())
                return false;
        }
        return true;

    }

    public static void movePacMan(PacMan player, String toMove){
        if (toMove == "up")
            player.moveUp();
        else if (toMove == "down")
            player.moveDown();
        else if (toMove == "left")
            player.moveLeft();
        else if (toMove == "right")
            player.moveRight();
    }

    public static void moveGhost(Ghost ghost, String toMove){
        if (toMove == "up")
            ghost.moveUp();
        else if (toMove == "down")
            ghost.moveDown();
        else if (toMove == "left")
            ghost.moveLeft();
        else if (toMove == "right")
            ghost.moveRight();
    }

    public static void moveGhosts(){
        ArrayList<String> possibleMoves = new ArrayList<>();
        possibleMoves.add("up");
        possibleMoves.add("down");
        possibleMoves.add("left");
        possibleMoves.add("right");
        ArrayList<Ghost> ghostList = currentMap.getGhostList();
        for (Ghost ghost: ghostList){
            Random moveGenerator = new Random();
            int moveIndex = moveGenerator.nextInt(4);
            while (!isValidMove(ghost, possibleMoves.get(moveIndex))){
                moveIndex = moveGenerator.nextInt(4);
            }
            moveGhost(ghost, possibleMoves.get(moveIndex));
        }
    }

}

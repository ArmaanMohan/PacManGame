package com.pacmangame.dependencies;

import com.pacmangame.character.*;
import com.pacmangame.map_elements.*;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    public static PacMan player;
    public static Map currentMap;
    public static ArrayList<Ghost> ghostList;
    public static ArrayList<Point> pointsList;
    public static ArrayList<Obstacle> obstacleList;
    public static ArrayList<Point> gottenPoints;

    public Game(String selectedMap) throws IOException {
        String pointsFileName = "PointsLocations.txt";
        String obstaclesFileName = "ObstacleLocations.txt";
        String ghostLocationsFileName = "GhostLocations.txt";
        if (selectedMap == "Map1") {
            String baseFilePath = "src/com/pacmangame/map_elements/Maps/Map1/";
            Board mapBoard = new Board(17, 17);
            player = new PacMan(8, 8);
            currentMap = new Map(mapBoard, baseFilePath + pointsFileName,
                    baseFilePath + obstaclesFileName, baseFilePath + ghostLocationsFileName);
            ghostList = currentMap.getGhostList();
            pointsList = currentMap.getPointList();
            obstacleList = currentMap.getObstacleList();
        }
        if (selectedMap == "EasyMap") {
            String baseFilePath = "src/com/pacmangame/map_elements/Maps/EasyMap/";
            Board mapBoard = new Board(4, 4);
            player = new PacMan(2, 2);
            currentMap = new Map(mapBoard, baseFilePath + pointsFileName,
                    baseFilePath + obstaclesFileName, baseFilePath + ghostLocationsFileName);
            ghostList = currentMap.getGhostList();
            pointsList = currentMap.getPointList();
            obstacleList = currentMap.getObstacleList();
        }
        gottenPoints = new ArrayList<>();
    }

    public static void playGame(){
        String desiredMove;
        desiredMove = promptUser();
        movePacMan(player, desiredMove);
        moveGhosts();
        update();
        while (continueGame()) {
            desiredMove = promptUser();
            movePacMan(player, desiredMove);
            moveGhosts();
            update();
        }



    }

    public static String promptUser(){
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

    public static void update(){
        for (Ghost ghost : ghostList){
            if (player.getXCoord() == ghost.getXCoord() && player.getYCoord() == ghost.getYCoord()){
                player.die();
                System.out.println("You've been killed by " + ghost.getName());
            }
        }
        for (int i = 0; i < pointsList.size(); i++){
            Point pointToCheck = pointsList.get(i);
            System.out.println("[" + pointToCheck.getxCoord() + ", " + pointToCheck.getyCoord() + "]");
            if (player.getYCoord() == pointToCheck.getyCoord() &&
                    player.getXCoord() == pointToCheck.getxCoord()){
                player.addPoint();
                pointsList.remove(pointToCheck);
            }
        }
        System.out.println("PacMan is at " + "[" + player.getXCoord() + ", " + player.getYCoord() + "]\n");
        System.out.println("Your score is: " + player.getScore() + "\n");
        System.out.println("You have " + player.getLives() + " lives remaining \n");
        for (Ghost ghost : ghostList){
            System.out.println("" + ghost.getName() + " is at " + "[" + ghost.getXCoord() + ", "
                    + ghost.getYCoord() + "]");
        }

    }

    public static boolean isValidMove(String desiredMove){
        PacMan dummyPlayer = new PacMan(player);
        if (!desiredMove.contentEquals("up") && !desiredMove.contentEquals("down") &&
                !desiredMove.contentEquals("left") && !desiredMove.contentEquals("right")){
            return false;}
        else{
            movePacMan(dummyPlayer, desiredMove);
        }
        int playerX = dummyPlayer.getXCoord();
        int playerY = dummyPlayer.getYCoord();
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

    public static boolean isValidMove(Ghost ghost, String desiredMove){
        Ghost dummyGhost = new Ghost(ghost);
        if (!desiredMove.contentEquals("up") && !desiredMove.contentEquals("down") &&
                !desiredMove.contentEquals("left") && !desiredMove.contentEquals("right")){
            return false;}
        else{
            moveGhost(dummyGhost, desiredMove);
        }
        int ghostX = dummyGhost.getXCoord();
        int ghostY = dummyGhost.getYCoord();
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

    public static void movePacMan(PacMan player, String toMove){
        if (toMove.contentEquals("up"))
            player.moveUp();
        else if (toMove.contentEquals("down"))
            player.moveDown();
        else if (toMove.contentEquals("left"))
            player.moveLeft();
        else if (toMove.contentEquals("right"))
            player.moveRight();
    }

    public static void moveGhost(Ghost ghost, String toMove){
        if (toMove.contentEquals("up"))
            ghost.moveUp();
        else if (toMove.contentEquals("down"))
            ghost.moveDown();
        else if (toMove.contentEquals("left"))
            ghost.moveLeft();
        else if (toMove.contentEquals("right"))
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

    public static boolean continueGame(){
        if (pointsList.size() == 0)
            return false;
        if (player.getLives() <= 0)
            return false;
        return true;
    }

}

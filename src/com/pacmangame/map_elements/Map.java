package com.pacmangame.map_elements;
import com.pacmangame.character.Ghost;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Map {
    public Board getGameBoard() {
        return gameBoard;
    }

    public Board gameBoard;

    public ArrayList<Point> getPointList() {
        return pointList;
    }

    public ArrayList<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public ArrayList<Ghost> getGhostList() {
        return ghostList;
    }

    public ArrayList<Ghost> ghostList;
    public ArrayList<Point> pointList;
    public ArrayList<Obstacle> obstacleList;

    public Map(Board b, String pointsFilePath, String obstacleFilePath, String ghostsFilePath)
            throws IOException {
        this.gameBoard = new Board(b);
        pointList = new ArrayList<>();
        obstacleList = new ArrayList<>();
        ghostList = new ArrayList<>();
        int[][] pointsCoords = getCoords(pointsFilePath);
        int[][] obstacleCoords = getCoords(obstacleFilePath);
        int[][] ghostCoords = getCoords(ghostsFilePath);
        int i = 0;
        while (i < pointsCoords.length){
            pointList.add(new Point(pointsCoords[i][0], pointsCoords[i][1]));
            i++;
        }
        i = 0;
        while (i < obstacleCoords.length){
            obstacleList.add(new Obstacle(obstacleCoords[i][0], obstacleCoords[i][1]));
            i++;
        }
        i = 0;
        while (i < ghostCoords.length){
            ghostList.add(new Ghost(ghostCoords[i][0], ghostCoords[i][1]));
            i++;
        }


    }



    private static int[][] getCoords(String filepath) throws FileNotFoundException {
        File file = new File(filepath);
        Scanner lineCounter = new Scanner(file);
        int numOfLines = 0;
        while (lineCounter.hasNextLine()){
            lineCounter.nextLine();
            numOfLines++;
        }
        int[][] coords = new int[numOfLines][2];
        String[] coordsAsString = new String[2];
        Scanner fileReader = new Scanner(file);
        int i = 0;
        while (fileReader.hasNextLine()){
            String line = fileReader.nextLine();
            coordsAsString = line.split(",");
            coords[i][0] = Integer.parseInt(coordsAsString[0]);
            coords[i][1] = Integer.parseInt(coordsAsString[1]);
            i++;
        }
        return coords;
    }

}

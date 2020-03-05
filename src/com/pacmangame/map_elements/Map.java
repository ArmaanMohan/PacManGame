package com.pacmangame.map_elements;
import com.pacmangame.character.Ghost;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Map {
    public Board getGameBoard() { //getter that returns the gameBoard
        return gameBoard;
    }

    public Board gameBoard;

    public ArrayList<Point> getPointList() { //getter that returns the Point list
        return pointList;
    }

    public ArrayList<Obstacle> getObstacleList() { //getter that returns the Obstacle list
        return obstacleList;
    }

    public ArrayList<Ghost> getGhostList() { //getter that returns the ghost list
        return ghostList;
    }

    public ArrayList<Ghost> ghostList;
    public ArrayList<Point> pointList;
    public ArrayList<Obstacle> obstacleList;

    //points, obstacles, and ghosts locations are stored in three separate, 2-dimensional arrays
    //these coordinates are obtained from a pre-existing text file with coordinates
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
        //create a point object and add each row of the 2D list to the newly created object
        while (i < pointsCoords.length){ 
            pointList.add(new Point(pointsCoords[i][0], pointsCoords[i][1]));
            i++;
        }
        
        i = 0;
        //create a point object and add each row of the 2D list to the newly created object
        while (i < obstacleCoords.length){
            obstacleList.add(new Obstacle(obstacleCoords[i][0], obstacleCoords[i][1]));
            i++;
        }
        
        i = 0;
        //create a point object and add each row of the 2D list to the newly created object
        while (i < ghostCoords.length){
            ghostList.add(new Ghost(ghostCoords[i][0], ghostCoords[i][1]));
            i++;
        }


    }


    // this method reads the appropriate text file and creates a 2-dimensional array 
    // each file has an "x,y" value in each line
    private static int[][] getCoords(String filepath) throws FileNotFoundException {
        File file = new File(filepath);
        Scanner lineCounter = new Scanner(file);
        int numOfLines = 0;
        while (lineCounter.hasNextLine()){ //count the number of lines in the file
            lineCounter.nextLine();
            numOfLines++;
        }
        int[][] coords = new int[numOfLines][2];
        String[] coordsAsString = new String[2];
        Scanner fileReader = new Scanner(file);
        int i = 0;
        while (fileReader.hasNextLine()){ //for each line, split the line based on the "," delimiter
            String line = fileReader.nextLine();
            coordsAsString = line.split(",");
            coords[i][0] = Integer.parseInt(coordsAsString[0]); //for the current line, store the x coordinate in the array
            coords[i][1] = Integer.parseInt(coordsAsString[1]); //for the current line, store the y coordinate in the array
            i++;
        }
        return coords; //return the array
    }

}

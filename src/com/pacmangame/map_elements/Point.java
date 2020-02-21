package com.pacmangame.map_elements;

// Ashar Siddiqui
// 02/20/2020

//Class: Point
//Description: Point object - takes xCoord and yCoord as arguments to create a Point object. All encapsulated.

public class Point {
	
	private int xCoord;
	private int yCoord;
	
	//Constructor: Point
	//This constructor takes two values, xCoord and yCoord, and adds these properties to the current point object based on the arguments entered
	
	Point (int xCoord, int yCoord){
		this.xCoord = xCoord; //make the x-coordinate of the point object, xCoord
		this.yCoord = yCoord; //make the y-coordinate of the point object, yCoord
	}
	
	//Getter that returns the x-coordinate of the point
	public int getxCoord() {
		return xCoord;
	}
	
	//Getter that returns the y-coordinate of the point
	public int getyCoord() {
		return yCoord;
	}
	
}

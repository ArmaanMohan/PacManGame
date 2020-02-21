package com.company;


//Class: Obstacle
//Description: takes the xCoord and yCoord as arguments to create an obstacle object. All encapsulated.
public class Obstacle {
	
	private int xCoord;
	private int yCoord;
	
	//Constructor: obstacle
	//This constructor takes the xCoord and yCoord as arguments to create an obstacle (object)
	Obstacle(int xCoord, int yCoord){
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	//Getter that returns the x-coordinate of the obstacle
	public int getxCoord(){
		return xCoord;
	}
	
	//Getter that returns the y-coordinate of the obstacle
	public int getyCoord(){
		return yCoord;
	}
	
}

package com.pacmangame.map_elements;


//Class: Obstacle
//Description: takes the xCoord and yCoord as arguments to create an obstacle object. All encapsulated.
public class Obstacle {
	
	private int xCoord;
	private int yCoord;
	
	//Constructor: obstacle
	//This constructor takes the xCoord and yCoord as arguments to create an obstacle (object)
	public Obstacle(int xCoord, int yCoord){
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}

	public Obstacle(Obstacle toCopy){
		xCoord = toCopy.getxCoord();
		yCoord = toCopy.getyCoord();
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

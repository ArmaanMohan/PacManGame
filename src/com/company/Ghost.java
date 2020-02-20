package com.company;
import java.util.Random;
import java.util.ArrayList;
import java.lang.Math;
public class Ghost {
	
//Instance Variables
	public int xCoord;
	public int yCoord;
	public String name;
	public String color;
	
//Default Constructor (creates a ghost at a random location)
	public Ghost(PacMan player){
		//Getting the coordinates of the board and pacman
		int lengthOfB = Board.getLength();
		int heightOfB = Board.getHeight();
		int pmX = player.getXCoord();
		int pmY = player.getYCoord();
		
		//Assigns a random name and color to the ghost
		Random rand = new Random();
		this.name = names.get(rand.nextInt(names.size()));
		Random rand2 = new Random();
		this.color = colors.get(rand.nextInt(colors.size()));
		
		//Puts the ghost at a random length coordinate location where pacman is not
		int minX = 0;
		int maxX = lengthOfB;
		int lCoord = pmX;
		while (lCoord == pmX) {
		lCoord = (int) (Math.random() * ((maxX - minX) + 1)) + minX; }
		this.xCoord = lCoord;
		
		//Puts the ghost at a random height coordinate location where pacman is not
		int minY = 0;
		int maxY = heightOfB;
		int hCoord = pmX;
		while (hCoord == pmY) {
		hCoord = (int) (Math.random() * ((maxY - minY) + 1)) + minY; }
		this.yCoord = hCoord;
	
	}
	
//Constructor (initializing instance variables)
	public Ghost(int xCoord, int yCoord, String name, String color) {
		if (xCoord >= 0) this.xCoord = xCoord;
		if (yCoord >= 0) this.yCoord = yCoord;
		this.name = name;
		this.color = color;
	}
	
//Copy constructor
	public Ghost(Ghost G) {
		xCoord = G.xCoord;
		yCoord = G.yCoord;
		name = G.name;
		color = G.color;
	}
	
//Method Getter xCoord
	public int getXCoord() {
		return this.xCoord;
	}

//Method Getter yCoord
	public int getYCoord() {
		return this.yCoord;
	}

//Method Getter name
	public String getName() {
		return this.name;
	}
	
//Method Getter color
	public String getColour() {
		return this.color;	
	}
	
//Method Setter xCoord
	public void setXCoord(int xCoord) {
		if (xCoord >= 0) {
		this.xCoord = xCoord; }
	}
	
//Method Setter yCoord
	public void setYCoord(int yCoord) {
		this.yCoord = yCoord;
	}	
	
//Method Setter name
	public void setName(String name) {
		this.name = name;
	}
	
//Method Setter color
	public void setColour(String colour) {
		this.color = colour;
	}
	
//Method moveUP
	public void moveUp(int numCoord) {
		this.yCoord =- numCoord;
	}
	
//Method moveDown 
	public void moveDown(int numCoord) {
		this.yCoord =+ numCoord;
	}
	
//Method moveRight
	public void moveRight(int numCoord) {
		this.xCoord =+ numCoord;
	}
	
//Method moveLeft
	public void moveLeft(int numCoord) {
			this.xCoord =- numCoord;
	}

}






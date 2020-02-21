package com.company;

//Ashar Siddiqui
//02/19/2020

//Class: Board
//"Board" Description: This class creates a board object with length and width properties. All encapsulated.
public class Board {
	
	private int length; // variable which will hold the length of the board
	private int width; // variable which will hold the width of the board
	
	//Constructor: Board
	//This constructor takes two values, length & width, and adds these as properties to the current object (board)
	Board (int length, int width){
		this.length = length; //make the length of the object "length"
		this.width = width; //make the width of the object "width"
	}
	
	//Getter that returns the length of the board
	public int getLength(){
		return length;
	}
	// Getter that returns the width of the board
	public int getWidth() {
		return width;
	}	
	
}
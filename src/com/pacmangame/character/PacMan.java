package com.pacmangame.character;

public class PacMan {
	
	//Initializing instance variables
	int xCoord ;
	int yCoord ;
	int score ;
	int lives ;

	// Default constructor, no arguments
	public PacMan() {
		xCoord = 0;
		yCoord = 0;
		score = 0;
		lives = 3;
	}
	// Constructor taking X and Y arguments of pacman location 
	public PacMan(int xCoorGiven, int yCoorGiven) {
		xCoord = xCoorGiven;
		yCoord = yCoorGiven;
		score = 0;
		lives = 3;
	}

	//Copy Constructor
	public PacMan(PacMan p){
		xCoord = p.xCoord;
		yCoord = p.yCoord;
		score = p.score;
		lives = p.lives;
	}
	//METHOD setter setXCoord
	public void setXCoord (int xCoord) {
		this.xCoord = xCoord;
	}
	//METHOD setter setYCoord
	public void setYCoord (int yCoord) {
		this.yCoord = yCoord;
	}
	//METHOD setter setScore
	public void setScore (int score) {
		this.score = score;
	}
	//METHOD setter setLives
	public void setLives (int lives) {
		this.lives = lives;
	}
	//Method getter xCoord
	public int getXCoord () {
		return xCoord;
	}
	//Method getter yCoord
	public int getYCoord () {
		return yCoord;
	}
	//Method getter getScore
	public int getScore () {
		return score;
	}
	//Method getter getLives
	public int getLives () {
		return lives;
	}
	//Method moveUp
	public void moveUp () {
		yCoord = yCoord - 1;
	}
	//Method moveDown
	public void moveDown () {
		yCoord = yCoord + 1;
	}
	//Method moveLeft
	public void moveLeft() {
		xCoord = xCoord - 1;	
	}
	//Method moveRight
	public void moveRight () {
		xCoord = xCoord + 1;
	}
	//Method die
	public void die () {
		lives = lives - 1;
	}
	//Method addPoint to score
	public void addPoint() {
		score = score + 1;
	}
}

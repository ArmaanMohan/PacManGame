package com.company;

public class PacMan {
	
	int xCoord ;
	int yCoord ;
	int score ;
	int lives ;

	public PacMan() {
		xCoord = 0;
		yCoord = 0;
		score = 0;
		lives = 3;
	}
	
	public PacMan(int xCoorGiven, int yCoorGiven) {
		xCoord = xCoorGiven;
		yCoord = yCoorGiven;
		score = 0;
		lives = 0;
	}
	
	public void setXCoord (int xCoord) {
		this.xCoord = xCoord;
	}
	
	public void setYCoord (int yCoord) {
		this.yCoord = yCoord;
	}
	
	public void setScore (int score) {
		this.score = score;
	}
	
	public void setLives (int lives) {
		this.lives = lives;
	}
	
	public int getXCoord () {
		return xCoord;
	}
	
	public int getYCoord () {
		return yCoord;
	}
	
	public int getScore () {
		return score;
	}
	
	public int getLives () {
		return lives;
	}
	
	public void moveUp () {
		yCoord = yCoord - 1;
	}
	
	public void moveDown () {
		yCoord = yCoord + 1;
	}
	
	public void moveLeft() {
		xCoord = xCoord - 1;	
	}
	
	public void moveRight () {
		xCoord = xCoord + 1;
	}
	
	public void die () {
		lives = lives - 1;
	}
	
	public void addPoint() {
		score = score + 1;
	}
}

package com.pacmangame.character;

public class PacMan extends Character {
	
	//Initializing instance variables
	private int score ;
	private int lives ;

	// Default constructor, no arguments
	public PacMan() {
		super(0,0);
		score = 0;
		lives = 3;
	}
	// Constructor taking X and Y arguments of pacman location 
	public PacMan(int xCoorGiven, int yCoorGiven) {
		super(xCoorGiven, yCoorGiven);
		score = 0;
		lives = 3;
	}

	//Copy Constructor
	public PacMan(PacMan p){
		super(p.getxCoord(), p.getyCoord());
		score = p.score;
		lives = p.lives;
	}

	//METHOD setter setScore
	public void setScore (int score) {
		this.score = score;
	}
	//METHOD setter setLives
	public void setLives (int lives) {
		this.lives = lives;
	}
	//Method getter getScore
	public int getScore () {
		return score;
	}
	//Method getter getLives
	public int getLives () {
		return lives;
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

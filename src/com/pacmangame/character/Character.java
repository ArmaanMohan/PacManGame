package com.pacmangame.character;

//This is the general character class - which contains information and methods for each character in the game (including the player him/herself)
public class Character {
	
	//instance variables which contain the x and y coordinate of the character
    private int xCoord;
    private int yCoord;

    public Character(int xCoord, int yCoord){
    	//this method sets the character's x and y coordinate based on the values that are provided as arguments
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }
    
    //getter that returns the x coordinate of the character
    public int getxCoord() {
        return xCoord;
    }
    
    //setter that sets the x coordinate of the character
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    //getter that returns the y coordinate of the character
    public int getyCoord() {
        return yCoord;
    }
    
    //setter that sets the y coordinate of the character
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    //Method moveUP
    // The purpose of this method is to move the character up one space 
    public void moveUp() {
        this.yCoord = this.yCoord - 1;
    }

    //Method moveDown
    public void moveDown() {
        this.yCoord = this.yCoord + 1;
    }

    //Method moveRight
    public void moveRight() {
        this.xCoord = this.xCoord + 1;
    }

    //Method moveLeft
    public void moveLeft() {
        this.xCoord = this.xCoord - 1;
    }
}

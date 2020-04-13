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
    /**
     *
     * @return xCoord;
     */

    public int getxCoord() {
        return xCoord;
    }

    
    //setter that sets the x coordinate of the character
    /**
     *
     * @param xCoord
     */
    
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }


    //getter that returns the y coordinate of the character
    /**
     *
     * @return yCoord
     */

    public int getyCoord() {
        return yCoord;
    }

    
    //setter that sets the y coordinate of the character
    /**
     *
     * @param yCoord
     */

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }


    //Method moveUP
    // The purpose of this method is to move the character up one space by subtracting 1 from its current y-value
    // Returns: nothing
    public void moveUp() {
        this.yCoord = this.yCoord - 1;
    }

    //Method moveDown
    // The purpose of this method is to move the character down one space by adding 1 to its current y-value
    // Returns: nothing
    public void moveDown() {
        this.yCoord = this.yCoord + 1;
    }

    //Method moveRight
    //The purpose of this method is to move the character to the right one space by adding 1 to its current x-value
    //Returns: nothing
    public void moveRight() {
        this.xCoord = this.xCoord + 1;
    }

    //Method moveLeft
    //The purpose of this method is to move the character to the left one space by subtracting 1 from its current x-value
    //Returns: nothing
    public void moveLeft() {
        this.xCoord = this.xCoord - 1;
    }
}












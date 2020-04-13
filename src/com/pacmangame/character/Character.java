package com.pacmangame.character;

public class Character {
    private int xCoord;
    private int yCoord;

    public Character(int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    /**
     *
     * @return xCoord;
     */
    public int getxCoord() {
        return xCoord;
    }

    /**
     *
     * @param xCoord
     */
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /**
     *
     * @return yCoord
     */
    public int getyCoord() {
        return yCoord;
    }

    /**
     *
     * @param yCoord
     */
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    //Method to move character up
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

package com.pacmangame.character;

public class Character {
    private int xCoord;
    private int yCoord;

    public Character(int xCoord, int yCoord){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    //Method moveUP
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

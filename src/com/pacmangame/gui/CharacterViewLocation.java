package com.pacmangame.gui;

import com.pacmangame.character.Character;
import javafx.scene.image.ImageView;


// ActiveObjectLocation is a helper class to track the location of objects within the GameView class
// An ActiveObjectLocation holds the x and y (column and row) position of active game pieces.
public class CharacterViewLocation {
    private ImageView objectView;
    private Character tracked;

    // Constructor: record the x and y values; also capture a unique name for the object
    public CharacterViewLocation(ImageView view, Character t){
        objectView = view;
        tracked = t;
    }

    // getter method to return the column (X) location
    public int getX() {
        return tracked.getxCoord();
    }

    // getter method to return the row (Y) location
    public int getY() {
        return tracked.getyCoord();
    }

    // Method relocate, moves the imageView to the new location
    public void relocate(double x, double y){
        objectView.relocate(x, y);
    }
    // Method matches, checks if the passed Character is the same as the Character associated with this ViewLocation
    public boolean matches(Character match){
        return tracked.toString().equals(match.toString());
    }

    public String toString() {
        return "CharacterViewLocation (" + getX() + "," + getY() + "), " + tracked.toString();
    }
}

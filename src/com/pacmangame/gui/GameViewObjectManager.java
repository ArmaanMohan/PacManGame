package com.pacmangame.gui;

import com.pacmangame.character.Character;

import java.util.ArrayList;

//Class that helps in the organization and movement of objects in lists (on the board)
public class GameViewObjectManager {
    //Instance variable, arrayList for the object locations
    private ArrayList<CharacterViewLocation> objectLocations;

    //Constructor, create a new arrayList containing the locations of the objects on the board
    public GameViewObjectManager(){
        objectLocations = new ArrayList<CharacterViewLocation>();
    }

    //Method addObject, adds and object to the arrayList containing the object locations
    public void addObject(CharacterViewLocation object) {
        System.out.println("GVOMGR.addObject: " + object);
        objectLocations.add(object);
    }

    //Method move, moves an object in the list (moves a ghost from one place to another for example)
    // The w and h parameters represent the tile size that each object occupies and converts from game
    // coordinates to JavaFX screen coordinates
    public boolean move(Character mvObj, double w, double h){
        boolean success = false;
        // Remove the object from the locations list and re-add it with its new location info
        for (CharacterViewLocation o : objectLocations) {
            if (o.matches(mvObj)){  // the list matches on the name of the Ghost/Point/Pacman object
                o.relocate(mvObj.getxCoord() * w, mvObj.getyCoord() * h);
                success = true;
                break;
            }
        }
        return success;
    }
}

package com.pacmangame.dependencies;

import java.io.IOException;
import java.util.ArrayList;

public class MapSelector {
    private String selectedMap;
    private boolean mapIsSelected;
    private ArrayList<String> possibleMaps;
    private String MapsFilePath;

    public MapSelector(String aMapsFilePath) {
        MapsFilePath = aMapsFilePath;
        possibleMaps = new ArrayList();
        mapIsSelected = false;
        loadMapList();
    }

    /**
     *
     * @param possibleMap
     */
    public void selectMap(String possibleMap){
        //Checks if map passed by argument is valid and sets that to the selected map if true
        if (possibleMaps.contains(possibleMap)){
           mapIsSelected = true;
           selectedMap = possibleMap;
        }
    }

    private void loadMapList() {
        //Loads list of possible maps from MapList file
        try{
            ToArray mapListReader = new ToArray(MapsFilePath+"MapList.txt");
            possibleMaps = mapListReader.getFileAsString();
        }
        //In the event that IOException is thrown, catches the exception and prints the io, then exits with error
        //code 1
        catch (IOException io){
            System.out.println("Unable to load file: " + io);
            System.exit(1);
        }

    }

    /**
     *
     * @return possibleMapsCopy
     */
    public ArrayList<String> getPossibleMaps() {
        //Creates a copy of the ArrayList of possible maps and returns it
        ArrayList<String> possibleMapsCopy = new ArrayList<>();
        for (String map : possibleMaps){
            possibleMapsCopy.add(map);
        }
        return possibleMapsCopy;
    }

    //Getter for the selectedMap
    public String getSelectedMap() {
        return selectedMap;
    }

    //Getter for the state of whether map is selected
    public boolean isMapIsSelected() {
        return mapIsSelected;
    }

}

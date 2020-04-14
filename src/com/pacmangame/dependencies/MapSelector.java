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

    //Method to set the selectedMap
    public void selectMap(String possibleMap){
        //Checking if the desired map is valid and if so, sets it as the selected map
        if (possibleMaps.contains(possibleMap)){
            mapIsSelected = true;
            selectedMap = possibleMap;
        }
    }

    //Method to load the possible maps into an ArrayList from the MapList.txt text file
    private void loadMapList() {
        try{
            //Uses toArray class to read from the file and place into an ArrayList
            ToArray mapListReader = new ToArray(MapsFilePath+"MapList.txt");
            possibleMaps = mapListReader.getFileAsString();
        }

        //Catches an IOException by printing the exception and exiting with error code 1
        catch (IOException io){
            System.out.println("Unable to load file: " + io);
            System.exit(1);
        }

    }

    //Getter for the possibleMaps instance variable
    public ArrayList<String> getPossibleMaps() {
        ArrayList<String> possibleMapsCopy = new ArrayList<>();
        for (String map : possibleMaps){
            possibleMapsCopy.add(map);
        }
        return possibleMapsCopy;
    }

    public String getSelectedMap() {
        return selectedMap;
    }

    public boolean isMapIsSelected() {
        return mapIsSelected;
    }

}

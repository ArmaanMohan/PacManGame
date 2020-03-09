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

    public void selectMap(String possibleMap){
        if (possibleMaps.contains(possibleMap)){
           mapIsSelected = true;
           selectedMap = possibleMap;
        }
    }

    private void loadMapList() {
        try{
            ToArray mapListReader = new ToArray(MapsFilePath+"MapList.txt");
            possibleMaps = mapListReader.getFileAsString();
        }
        catch (IOException io){
            System.out.println("Unable to load file: " + io);
            System.exit(1);
        }

    }

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

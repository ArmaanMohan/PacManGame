package com.pacmangame.dependencies;

import java.io.IOException;
import java.util.ArrayList;

public class MapSelector {
    private String selectedMap;
    private boolean mapIsSelected;
    private ArrayList<String> possibleMaps;
    private String MapsFilePath;

    public MapSelector(String aMapsFilePath) throws IOException {
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

    private void loadMapList() throws IOException {
        ToArray mapListReader = new ToArray(MapsFilePath+"MapList.txt");
        possibleMaps = mapListReader.getFileAsString();
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

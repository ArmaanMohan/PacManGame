package com.pacmangame;

import com.pacmangame.dependencies.MapSelector;
import java.io.IOException;

public class Test {


    public static void main(String [] args) throws IOException {
        MapSelector mapSelector = new MapSelector("src/com/pacmangame/map_elements/Maps/");
        mapSelector.selectMap("Map2");
        System.out.println(mapSelector.isMapIsSelected());

    }
}

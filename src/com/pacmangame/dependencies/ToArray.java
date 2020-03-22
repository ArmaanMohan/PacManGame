package com.pacmangame.dependencies;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

//Used to load the list of names and colors to randomly choose from
public class ToArray {

	//Instance  variables
    private ArrayList fileData;
    private String filepath;

    public ToArray(String filepath) {
        this.filepath = filepath;
        fileData = new ArrayList();
    }
    //Finds and opens the file
    public ArrayList getFileAsString() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line = br.readLine();

            //Add each line of the file to an array 
            while (line != null) {
                fileData.add(line);
                line = br.readLine();
            }
            //Creates and returns a copy of the array
            ArrayList fileDataCopy = new ArrayList();
            for (int i = 0; i < fileData.size(); i++) {
                fileDataCopy.add(fileData.get(i));
            }
            return fileDataCopy;
        }
    }
}


package com.pacmangame.dependencies;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ToArray {

    private static ArrayList fileData;
    static String filepath;

    public ToArray(String filepath) {
        this.filepath = filepath;
        fileData = new ArrayList();
    }

    public static ArrayList getFileAsString() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line = br.readLine();

            while (line != null) {
                fileData.add(line);
                line = br.readLine();
            }
            ArrayList fileDataCopy = new ArrayList();
            for (int i = 0; i < fileData.size(); i++) {
                fileDataCopy.add(fileData.get(i));
            }
            return fileDataCopy;
        }
    }
}


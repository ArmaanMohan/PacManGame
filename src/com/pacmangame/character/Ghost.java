package com.pacmangame.character;
import com.pacmangame.dependencies.ToArray;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;


public class Ghost extends Character{

//Instance Variables
	private String name;
	private String color;
	private ArrayList<String> names;
	//private ArrayList<String> colors;
	
//Constructor (initializing instance variables)
	public Ghost(int xCoord, int yCoord) throws IOException {
		super(xCoord, yCoord);

		//Assigns the files of colors and names to arraylists
		names = new ArrayList();
		ToArray nameList = new ToArray("src/com/pacmangame/character/GhostAssets/GhostNames.txt");
		names = nameList.getFileAsString();
		//colors = new ArrayList();
		//ToArray colorsList = new ToArray("src/com/pacmangame/character/GhostAssets/Colors.txt");
		//colors = colorsList.getFileAsString();

		//Assigns a random name and color to the ghost
		Random rand = new Random();
		this.name = names.get(rand.nextInt(names.size()));
		//Random rand2 = new Random();
		//this.color = colors.get(rand.nextInt(colors.size()));
	}
	
//Copy constructor
	public Ghost(Ghost G) {
		super(G.getxCoord(), G.getyCoord());
		name = G.getName();
	}

//Method Getter name
	public String getName() {
		return this.name;
	}


}






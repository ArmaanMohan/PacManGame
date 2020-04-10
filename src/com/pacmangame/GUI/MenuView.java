package com.pacmangame.GUI;

//import all necessary methods

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

/*
    Represents the home screen menu that is seen
    This is used by the MainUI as the home screen
 */

public class MenuView extends FlowPane {

    /*
    The details about the home/menu screen of the program
     */
    public MenuView() {
        // set the parameters from the parent
        super();
        // setting background colour
        this.setStyle("-fx-background-color: navy");

        /*
        Making the titles for the home screen menu
        Setting welcome title and details so it is yellow
         */
        Text welcome = new Text("Welcome to the game!");
        welcome.setFont(Font.font ("Verdana", 20));
        welcome.setFill(Color.YELLOW);
        //setting main game title in large, yellow font
        Text title = new Text("PACMAN");
        title.setFont(Font.font ("Verdana", FontWeight.BOLD, 45));
        title.setFill(Color.YELLOW);


        /*
        Setting the buttons for map selection
        getting array of map levels
         */
        ArrayList<String> theLevels = new ArrayList<String>();
        //adding names of the different maps to use
        theLevels.add("Basic Map");
        theLevels.add("Full Map");
        //getting all of the available maps
        ObservableList<String> levelOptions = FXCollections.observableArrayList(theLevels);
        //new label to indicate to user what is being asked of them and setting colour of text
        Label pickLevel = new Label("Please select your level of difficulty");
        pickLevel.setTextFill(Color.GOLD);
        //add the list of options to the home screen
        ComboBox<String> levelBox = new ComboBox<String>(levelOptions);
        //allowing for an action when the level is selected
        levelBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });


        /*
        Setting up the horizontal box to hold the levels in for ideal spacing in the centre for the drop down menu
         */
        HBox levels = new HBox(5);
        levels.setAlignment(Pos.CENTER);
        levels.getChildren().addAll(levelBox);

        /*
        Creating the button to start and play the game
        Button is made to indicate to the user what to do
         */

        Button playButton = new Button("CLICK TO PLAY");

        /*
        creating the full homescreen for the user to see
        Make it in a vertical box layout so that it flows down the page and everything is centered
         */
        VBox homeLayout = new VBox(15);
        homeLayout.setAlignment(Pos.CENTER);
        //add the welcome and pacman title, in addition to the title to pick a level and both buttons to select a map and play the game
        homeLayout.getChildren().addAll(welcome, title, pickLevel, levels, playButton);
        //set all parameters as to what was set above
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(homeLayout);

    }


}



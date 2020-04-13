package com.pacmangame.gui;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;



public class MenuView extends FlowPane {

    public Button playButton;
    public ComboBox pickLevel;

    public MenuView(ObservableList mapList) {

        super();

        //Setting background to desired blue color
        this.setStyle("-fx-background-color: #001aab");

        //home screen buttons
        Text welcome = new Text("Welcome to the game!");
        welcome.setFont(Font.font ("Verdana", 20));
        welcome.setFill(Color.YELLOW);

        Text title = new Text("PACMAN");
        title.setFont(Font.font ("Verdana", FontWeight.BOLD, 45));
        title.setFill(Color.YELLOW);


        //level buttons
        pickLevel = new ComboBox(mapList);
        pickLevel.setValue("Please select a map");
        //play button

        playButton = new Button("CLICK TO PLAY");

        // Home screen
        VBox homeLayout = new VBox(15);
        homeLayout.setAlignment(Pos.CENTER);
        homeLayout.getChildren().addAll(welcome, title, pickLevel, playButton);
        this.setAlignment(Pos.CENTER);

        this.getChildren().addAll(homeLayout);

    }

    public Button getPlayButton() {
        return playButton;
    }

    public String getSelectedMap(){
        return pickLevel.getValue().toString();
    }
}



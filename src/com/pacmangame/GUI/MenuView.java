package com.pacmangame.GUI;

import javafx.scene.layout.FlowPane;

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



public class MenuView extends FlowPane {

    public MenuView() {

        super();
        this.setStyle("-fx-background-color: navy");

        //home screen buttons
        Text welcome = new Text("Welcome to the game!");
        welcome.setFont(Font.font ("Verdana", 20));
        welcome.setFill(Color.YELLOW);

        Text title = new Text("PACMAN");
        title.setFont(Font.font ("Verdana", FontWeight.BOLD, 45));
        title.setFill(Color.YELLOW);


        //level buttons
        Label pickLevel = new Label("Please select your level of difficulty");
        pickLevel.setTextFill(Color.GOLD);
        Button normalLevel = new Button("Normal");
        Button hardLevel = new Button("Hard");
        normalLevel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final String selectedLevel = "easy";
            }
        } );
        hardLevel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final String selectedLevel = "hard";
            }
        } );



        HBox levels = new HBox(5);
        levels.setAlignment(Pos.CENTER);
        levels.getChildren().addAll(normalLevel, hardLevel);

        //play button

        Button playButton = new Button("CLICK TO PLAY");
//HERE WE NEED TO SET ONTO THE GAME SCREEN

        // Home screen
        VBox homeLayout = new VBox(15);
        homeLayout.setAlignment(Pos.CENTER);
        homeLayout.getChildren().addAll(welcome, title, pickLevel, levels, playButton);
        this.setAlignment(Pos.CENTER);

        this.getChildren().addAll(homeLayout);



    }


}



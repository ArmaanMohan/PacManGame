package com.pacmangame.GUI;

import javafx.scene.layout.FlowPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;


public class MenuView extends FlowPane {

    //instance variables?
    Stage window;
    Scene homeScreen;



    public MenuView() {


        //BACKGROUND!!!!!!!!!!!!!!!
        BackgroundFill backFill = new BackgroundFill(Color.NAVY, CornerRadii.EMPTY, Insets.EMPTY);
        Background theBack = new Background(backFill);

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
        homeLayout.setBackground(theBack);
        homeLayout.getChildren().addAll(welcome, title, pickLevel, levels, playButton);
        homeScreen = new Scene(homeLayout, 400, 400);



        //set home screen
        window.setScene(homeScreen);
        window.show();

    }


}



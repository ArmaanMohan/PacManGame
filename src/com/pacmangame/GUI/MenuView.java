package com.pacmangame.GUI;

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
        ArrayList<String> theLevels = new ArrayList<String>();
        theLevels.add("Easy");
        theLevels.add("Hard");
        ObservableList<String> levelOptions = FXCollections.observableArrayList(theLevels);
        Label pickLevel = new Label("Please select your level of difficulty");
        pickLevel.setTextFill(Color.GOLD);
        ComboBox<String> levelBox = new ComboBox<String>(levelOptions);
        levelBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });



        HBox levels = new HBox(5);
        levels.setAlignment(Pos.CENTER);
        levels.getChildren().addAll(levelBox);

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



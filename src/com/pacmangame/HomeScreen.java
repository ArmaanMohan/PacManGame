package com.pacmangame;

import javafx.application.Application;

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


public class HomeScreen extends Application {

    //instance variables?
    Stage window;
    Scene homeScreen, levelScreen, categoryScreen, gameScreenNormal, gameScreenHard;

    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        //BACKGROUND!!!!!!!!!!!!!!!
        BackgroundFill backFill = new BackgroundFill(Color.NAVY, CornerRadii.EMPTY, Insets.EMPTY);
        Background theBack = new Background(backFill);

        //home screen buttons
        Text title = new Text("PACMAN");
        title.setFont(Font.font ("Verdana", FontWeight.BOLD, 45));
        title.setFill(Color.YELLOW);
        Button firstButton = new Button("Click to play the game");
        firstButton.setOnAction(e -> window.setScene(categoryScreen));

        // Home screen
        VBox homeLayout = new VBox(15);
        homeLayout.setAlignment(Pos.CENTER);
        homeLayout.setBackground(theBack);
        homeLayout.getChildren().addAll(title, firstButton);
        homeScreen = new Scene(homeLayout, 400, 400);

        // category buttons
        Label pickCategory = new Label("Please select which category of game you would like to play");
        pickCategory.setTextFill(Color.GOLD);
        Button originalCategory = new Button("Original");
        Button uofcCategory = new Button("U of C vs. MRU");
        originalCategory.setOnAction(e -> window.setScene(levelScreen));
        uofcCategory.setOnAction(e -> window.setScene(levelScreen));

        HBox categories = new HBox(5);
        categories.setAlignment(Pos.CENTER);
        categories.getChildren().addAll(originalCategory, uofcCategory);

        //category screen
        VBox categoryLayout = new VBox(15);
        categoryLayout.setAlignment(Pos.CENTER);
        categoryLayout.setBackground(theBack);
        categoryLayout.getChildren().addAll(pickCategory, categories);
        categoryScreen = new Scene(categoryLayout, 400, 400);

        //level buttons
        Label pickLevel = new Label("Please select your level of difficulty");
        pickLevel.setTextFill(Color.GOLD);
        Button normalLevel = new Button("Normal");
        Button hardLevel = new Button("Hard");
        normalLevel.setOnAction(e -> window.setScene(gameScreenNormal));
        hardLevel.setOnAction(e -> window.setScene(gameScreenHard));

        HBox levels = new HBox(5);
        levels.setAlignment(Pos.CENTER);
        levels.getChildren().addAll(normalLevel, hardLevel);

        //level screen
        VBox levelLayout = new VBox(15);
        levelLayout.setAlignment(Pos.CENTER);
        levelLayout.setBackground(theBack);
        levelLayout.getChildren().addAll(pickLevel, levels);
        levelScreen = new Scene(levelLayout, 400, 400);

        //different game screens
        FlowPane root = new FlowPane();
        FlowPane root2 = new FlowPane();
        gameScreenHard = new Scene(root2,400, 400);
        gameScreenNormal = new Scene(root,400, 400);

        //set home screen
        window.setScene(homeScreen);
        window.setTitle("Pacman: The Game");
        window.show();

    }















}

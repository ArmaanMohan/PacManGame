package com.pacmangame;

import com.pacmangame.GUI.GameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import com.pacmangame.dependencies.Game;

public class PacManGUI extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("PacMan");
        BorderPane mainContainer = new BorderPane();
        Scene scene = new Scene(mainContainer, 1000, 750);
        GameView g = new GameView(new Game("EasyMap"), scene, null);
        //Temporary code to see testing for now
        HBox bottomTestButtons = new HBox(10);
        Button menuButton = new Button("Menu screen");
        Button gameButton = new Button("Game screen");
        bottomTestButtons.getChildren().addAll(menuButton, gameButton);
        gameButton.setOnAction(actionEvent -> mainContainer.setCenter(g));
        Rectangle sideRect = new Rectangle(100,650, Color.WHITE);
        Rectangle sideRect2 = new Rectangle(100,650, Color.WHITE);


        mainContainer.setLeft(sideRect);
        mainContainer.setRight(sideRect2);
        mainContainer.setBottom(bottomTestButtons);
        //End of test code

        window.setScene(scene);
        window.show();
    }
}

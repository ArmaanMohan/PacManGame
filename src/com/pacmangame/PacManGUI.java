package com.pacmangame;

import com.pacmangame.GUI.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import com.pacmangame.dependencies.Game;
import com.pacmangame.dependencies.Controller;

public class PacManGUI extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("PacMan");
        Game currentGame = new Game("Map1");
        AnchorPane gamePane = loadGameView(currentGame);
        Scene gameScene = new Scene(gamePane, 600, 600);
        Controller c = new Controller(currentGame);
        gamePane.onKeyPressedProperty();
        window.setScene(gameScene);
        window.show();
    }

    private AnchorPane loadGameView(Game currentGame){
        AnchorPane gamePane = new AnchorPane();
        GameView currentGameView = new GameView(currentGame, 600, 600);
        gamePane.getChildren().add(currentGameView);
        return gamePane;
    }
}

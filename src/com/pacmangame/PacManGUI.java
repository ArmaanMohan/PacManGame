package com.pacmangame;

import com.pacmangame.GUI.*;
import com.pacmangame.dependencies.MapSelector;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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
        MapSelector mapSelector = new MapSelector("src/com/pacmangame/map_elements/Maps/");
        ObservableList<String> mapList = FXCollections.observableArrayList(mapSelector.getPossibleMaps());
        MenuView menu = new MenuView(mapList);
        Scene menuScene = new Scene(menu, 600, 600);
        window.setScene(menuScene);
        window.show();
        Button playButton = menu.getPlayButton();
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (menu.getSelectedMap() == "Please select a map"){
                    Alert mapAlert = new Alert(Alert.AlertType.ERROR);
                    mapAlert.setHeaderText("Please select a map before continuing");
                    mapAlert.showAndWait();
                }
                else{
                    Game currentGame = new Game(menu.getSelectedMap());
                    AnchorPane gamePane = loadGameView(currentGame);
                    Scene gameScene = new Scene(gamePane, 600, 600);
                    window.setScene(gameScene);
                    Controller c = new Controller(currentGame);
                    gameScene.addEventHandler(KeyEvent.KEY_PRESSED, c);
                }

            }
        });

    }

    private AnchorPane loadGameView(Game currentGame){
        AnchorPane gamePane = new AnchorPane();
        GameView currentGameView = new GameView(currentGame, 600, 600);
        gamePane.getChildren().add(currentGameView);
        return gamePane;
    }

}

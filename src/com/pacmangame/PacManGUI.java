package com.pacmangame;

import com.pacmangame.gui.*;
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

        //Setting the window title
        window.setTitle("PacMan");

        //Creating an instance of mapSelector and giving the mapList to the menuView
        MapSelector mapSelector = new MapSelector("src/com/pacmangame/map_elements/Maps/");
        ObservableList<String> mapList = FXCollections.observableArrayList(mapSelector.getPossibleMaps());
        MenuView menu = new MenuView(mapList);

        //Creating the menu scene and setting the window to show that scene
        Scene menuScene = new Scene(menu, 600, 600);
        window.setScene(menuScene);
        window.show();

        //Getting the play button from the menuView and setting the action on click
        Button playButton = menu.getPlayButton();
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //If user has not selected a map, error pops up telling user to choose a map
                if (menu.getSelectedMap() == "Please select a map"){
                    Alert mapAlert = new Alert(Alert.AlertType.ERROR);
                    mapAlert.setHeaderText("Please select a map before continuing");
                    mapAlert.showAndWait();
                }
                //Otherwise, user has chosen a map
                else{
                    //Creating Game object and pane to hold the gameView
                    Game currentGame = new Game(menu.getSelectedMap());
                    AnchorPane gamePane = new AnchorPane();
                    GameView currentGameView = new GameView(currentGame, 600, 600);
                    gamePane.getChildren().add(currentGameView);

                    //Creating the scene for the gameView and setting the window to show it
                    Scene gameScene = new Scene(gamePane, 600, 600);
                    window.setScene(gameScene);

                    //Creating an object instance of the controller to handle button inputs and giving the controller
                    //the view to monitor (the gameView)
                    Controller c = new Controller(currentGame);
                    c.registerView(currentGameView);
                    gameScene.addEventHandler(KeyEvent.KEY_PRESSED, c);
                }

            }
        });

    }


}

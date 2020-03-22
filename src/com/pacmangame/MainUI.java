package com.pacmangame;


import com.pacmangame.dependencies.Game;
import com.pacmangame.dependencies.MapSelector;
import com.pacmangame.map_elements.Obstacle;
import com.pacmangame.map_elements.Point;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;

public class MainUI extends Application {
    private Game game;
    private Canvas oLayer, pLayer;
    ArrayList<String> keyInput = new ArrayList<String>();

    public MainUI() {
        MapSelector mapSelector = new MapSelector("src/com/pacmangame/map_elements/Maps/");
        mapSelector.selectMap("EasyMap");
        String selectedMap = mapSelector.getSelectedMap();
        game = new Game(selectedMap);
    }

    private double toX(int boardX){
        return oLayer.getWidth() * boardX / game.getCurrentMap().getGameBoard().getLength();
    }

    private double toY(int boardY){
        return oLayer.getHeight() * boardY / game.getCurrentMap().getGameBoard().getHeight();
    }

    private void createHandlers(Scene scene) {
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();
                        System.out.println(code + " pressed");
                        if(!keyInput.contains(code))
                            keyInput.add(code);
                    }
                }
        );
        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();
                        if(!keyInput.contains(code))
                            keyInput.remove(code);
                    }
                }
        );
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Pacman");
        Group root = new Group();
        Scene theScene = new Scene(root);
        oLayer = new Canvas(512, 512);
        pLayer = new Canvas(512, 512);
        root.getChildren().add(oLayer);
        root.getChildren().add(pLayer);
        pLayer.toFront();

        //Image brick = new Image("brick.png");
        GraphicsContext gc = oLayer.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        Iterator<Obstacle> iterator = game.getCurrentMap().getObstacleList().iterator();
        while(iterator.hasNext()){
            Obstacle o = iterator.next();
            double x = toX(o.getxCoord());
            double y = toY(o.getyCoord());
            System.out.println("place brick at " + x+","+y);
            //gc.drawImage(brick, x, y);
            gc.fillRoundRect(x + 5,y + 5,30,30,10,10);
        }

        gc = pLayer.getGraphicsContext2D();
        gc.setFill(Color.ORANGE);
        Iterator<Point> pIterator = game.getCurrentMap().getPointList().iterator();
        while(pIterator.hasNext()){
            Point p = pIterator.next();
            double x = toX(p.getxCoord());
            double y = toY(p.getyCoord());
            System.out.println("place point at " + x+","+y);
            //gc.drawImage(brick, x, y);
            gc.fillOval(x + 5, y + 5, 20, 20);
        }
        createHandlers(theScene);
        primaryStage.setScene(theScene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}

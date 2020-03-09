package com.pacmangame;


import com.pacmangame.character.Ghost;
import com.pacmangame.dependencies.Game;
import com.pacmangame.dependencies.MapSelector;
import com.pacmangame.map_elements.Obstacle;
import com.pacmangame.map_elements.Point;
import com.sun.corba.se.impl.orbutil.graph.Graph;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;

public class MainUI extends Application {
    private Game game;
    private Canvas oLayer, pLayer, pmLayer, gLayer;
    ArrayList<String> keyInput = new ArrayList<String>();
    private int WIDTH = 320;
    private int HEIGHT = 320;

    public MainUI() {
        MapSelector mapSelector = new MapSelector("src/com/pacmangame/map_elements/Maps/");
        mapSelector.selectMap("EasyMap");
        String selectedMap = mapSelector.getSelectedMap();
        game = new Game(selectedMap);
    }

    private double toX(int boardX){
        return ((oLayer.getWidth() * (boardX) / game.currentMap.getGameBoard().getLength()) );
    }

    private double toY(int boardY){
        return ((oLayer.getHeight() * (boardY) / game.currentMap.getGameBoard().getHeight()) );
    }

    private void createHandlers(Scene scene) {
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        String code = event.getCode().toString();
                        System.out.println(code + " pressed");
                        String inLower = code.toLowerCase();
                        if (game.continueGame()) {
                            if (game.isValidMove(inLower)) {
                                game.doMove(inLower);
                            }
                            GraphicsContext gc = pmLayer.getGraphicsContext2D();
                            gc.clearRect(0, 0, pmLayer.getWidth(), pmLayer.getHeight());
                            Image pman = new Image("Animations/dino.gif", 100, 0, true, false);
                            Image ghost = new Image("Animations/ghost.png", 100, 0, true, false);
                            for (Ghost g : game.ghostList) {
                                gc.drawImage(ghost, toX(g.getxCoord()) -10 , toY(g.getyCoord())-23);
                            }
                            gc.drawImage(pman, toX(game.player.getxCoord()) -10, toY(game.player.getyCoord())-23);
                            Image dot = new Image ("Animations/Dot2.0.jpg");
                            gc = pLayer.getGraphicsContext2D();
                            gc.clearRect(0, 0, pLayer.getWidth(), pLayer.getHeight());
                            Iterator<Point> pIterator = game.currentMap.getPointList().iterator();
                            while(pIterator.hasNext()) {
                                Point p = pIterator.next();
                                double x = toX(p.getxCoord());
                                double y = toY(p.getyCoord());
                                System.out.println("place point at " + p.getxCoord() + "" + p.getyCoord());
                                gc.drawImage(dot, x + 18, y + 18);
                            }
                            pmLayer.toFront();
                            if (!keyInput.contains(code))
                                keyInput.add(code);
                        }
                        else{
                            GraphicsContext gc = pmLayer.getGraphicsContext2D();
                            gc.clearRect(0, 0, pmLayer.getWidth(), pmLayer.getHeight());
                            gc = oLayer.getGraphicsContext2D();
                            gc.clearRect(0, 0, oLayer.getWidth(), oLayer.getHeight());
                            gc = pLayer.getGraphicsContext2D();
                            gc.clearRect(0, 0, pLayer.getWidth(), pLayer.getHeight());
                            Image gameOver = new Image("Animations/GameOver.jpg");
                            if (game.player.getLives() <= 0)
                                gc.drawImage(gameOver, 120, 120);
                            else
                                System.out.print("win!");
                        }
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
        oLayer = new Canvas(WIDTH, HEIGHT);
        pLayer = new Canvas(WIDTH, HEIGHT);
        pmLayer = new Canvas(WIDTH, HEIGHT);
        gLayer = new Canvas(WIDTH,HEIGHT);
        root.getChildren().add(oLayer);
        root.getChildren().add(pLayer);
        root.getChildren().add(pmLayer);
        pLayer.toFront();

        //Image brick = new Image("brick.png");
        GraphicsContext gc = oLayer.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        Iterator<Obstacle> iterator = game.currentMap.getObstacleList().iterator();
        while(iterator.hasNext()){
            Obstacle o = iterator.next();
            double x = toX(o.getxCoord());
            double y = toY(o.getyCoord());
            System.out.println("place brick at " + x+","+y);
            //gc.drawImage(brick, x, y);
            gc.fillRoundRect(x + 5,y + 5,55,55,10,10);
        }
        Image dot = new Image("Animations/Dot2.0.jpg");
        gc = pLayer.getGraphicsContext2D();
        gc.setFill(Color.ORANGE);
        Iterator<Point> pIterator = game.currentMap.getPointList().iterator();
        while(pIterator.hasNext()){
            Point p = pIterator.next();
            double x = toX(p.getxCoord());
            double y = toY(p.getyCoord());
            System.out.println("place point at " + p.getxCoord()+ "" + p.getyCoord());
            gc.drawImage(dot, x + 18, y + 18);
            //gc.fillOval(x + 5, y + 5, 20, 20);
        }
        Image pman = new Image ("Animations/dino.gif",100,0,
                true,false);
        gc = pmLayer.getGraphicsContext2D();
        gc.drawImage(pman, toX(game.currentMap.getGameBoard().getLength() / 2) -10,
                toY(game.currentMap.getGameBoard().getHeight() / 2) -23);
        Image ghost = new Image("Animations/ghost.png", 100, 0,
                true, false);
        for (Ghost g : game.ghostList){
            gc.drawImage(ghost, toX(g.getxCoord()) -10 , toY(g.getyCoord()) - 23);
        }

        createHandlers(theScene);
        primaryStage.setScene(theScene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}

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

    public MainUI() {
        MapSelector mapSelector = new MapSelector("src/com/pacmangame/map_elements/Maps/");
        mapSelector.selectMap("EasyMap");
        String selectedMap = mapSelector.getSelectedMap();
        game = new Game(selectedMap);
    }

    private double toX(int boardX){
        return ((oLayer.getWidth() * (boardX) / game.getCurrentMap().getGameBoard().getLength()) );
    }

    private double toY(int boardY){
        return ((oLayer.getHeight() * (boardY) / game.getCurrentMap().getGameBoard().getHeight()) );
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
                            for (Ghost g : game.getGhostList()) {
                                gc.drawImage(ghost, toX(g.getxCoord()), toY(g.getyCoord()));
                            }
                            gc.drawImage(pman, toX(game.getPlayer().getxCoord()), toY(game.getPlayer().getyCoord()));
                            Image dot = new Image ("Animations/Dot2.0.jpg");
                            gc = pLayer.getGraphicsContext2D();
                            gc.clearRect(0, 0, pLayer.getWidth(), pLayer.getHeight());
                            Iterator<Point> pIterator = game.getCurrentMap().getPointList().iterator();
                            while(pIterator.hasNext()) {
                                Point p = pIterator.next();
                                double x = toX(p.getxCoord());
                                double y = toY(p.getyCoord());
                                System.out.println("place point at " + p.getxCoord() + "" + p.getyCoord());
                                gc.drawImage(dot, x + 18, y + 18);
                            }
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
                            gc.drawImage(gameOver, 120, 120);
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
        oLayer = new Canvas(320, 320);
        pLayer = new Canvas(320, 320);
        pmLayer = new Canvas(320, 320);
        gLayer = new Canvas(320,320);
        root.getChildren().add(oLayer);
        root.getChildren().add(pLayer);
        root.getChildren().add(pmLayer);
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
            gc.fillRoundRect(x + 5,y + 5,55,55,10,10);
        }
        Image dot = new Image("Animations/Dot2.0.jpg");
        gc = pLayer.getGraphicsContext2D();
        gc.setFill(Color.ORANGE);
        Iterator<Point> pIterator = game.getCurrentMap().getPointList().iterator();
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
        gc.drawImage(pman, toX(game.getCurrentMap().getGameBoard().getLength() / 2) + 15,
                toY(game.getCurrentMap().getGameBoard().getHeight() / 2) + 15);
        Image ghost = new Image("Animations/ghost.png", 100, 0,
                true, false);
        for (Ghost g : game.getGhostList()){
            gc.drawImage(ghost, toX(g.getxCoord()), toY(g.getyCoord()));
        }

        createHandlers(theScene);
        primaryStage.setScene(theScene);
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}

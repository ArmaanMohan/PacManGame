package com.pacmangame.GUI;

import com.pacmangame.character.Ghost;
import com.pacmangame.dependencies.Game;
import com.pacmangame.map_elements.Obstacle;
import com.pacmangame.map_elements.Point;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.Scene;
import com.pacmangame.dependencies.Controller;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import javax.naming.ldap.Control;
import java.util.Iterator;


public class GameView extends Group {
    private Game game;
    private Controller controller;
    private Canvas oLayer, pLayer, pmLayer, gLayer;

    public GameView() {
        super();
    }

    public GameView(Game g, Scene s, Controller c) {
        Scene theScene = new Scene(this);
        oLayer = new Canvas(320, 320);
        pLayer = new Canvas(320, 320);
        pmLayer = new Canvas(320, 320);
        gLayer = new Canvas(320,320);
        getChildren().add(oLayer);
        getChildren().add(pLayer);
        getChildren().add(pmLayer);
        getChildren().add(gLayer);
        pLayer.toFront();

        game = g;
        controller = c;

        drawObstacle();
        drawGhost();
        drawPoint();
        drawDino();

       // theScene.getOnKeyPressed(controller.getEventHandler());
    }

    private void drawObstacle(){
        GraphicsContext gc = oLayer.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        Iterator<Obstacle> iterator = game.getCurrentMap().getObstacleList().iterator();
        while(iterator.hasNext()){
            Obstacle o = iterator.next();
            double x = toX(o.getxCoord());
            double y = toY(o.getyCoord());
            gc.fillRoundRect(x + 5,y + 5,55,55,10,10);
        }
    }

    private void drawDino(){
        Image pman = new Image ("Animations/dino.gif",100,0,
                true,false);
        GraphicsContext gc = pmLayer.getGraphicsContext2D();
        gc.drawImage(pman, toX(game.getCurrentMap().getGameBoard().getLength() / 2)-13,
                toY(game.getCurrentMap().getGameBoard().getHeight() / 2)-18);
    }

    private void drawPoint(){
        Image dot = new Image("Animations/Dot2.0.jpg");
        GraphicsContext gc = pLayer.getGraphicsContext2D();
        gc.setFill(Color.ORANGE);
        Iterator<Point> pIterator = game.getCurrentMap().getPointList().iterator();
        while(pIterator.hasNext()){
            Point p = pIterator.next();
            double x = toX(p.getxCoord());
            double y = toY(p.getyCoord());
            gc.drawImage(dot, x + 18, y + 18);
    }}

    private void drawGhost(){
        Image ghost = new Image("Animations/ghost.gif", 100, 0,
                true, false);
        for (Ghost g : game.getGhostList()){
            GraphicsContext gc = gLayer.getGraphicsContext2D();
            gc.drawImage(ghost, toX(g.getxCoord()) - 23, toY(g.getyCoord()) - 15);
        }
    }

    public void refresh(){
        GraphicsContext gc = pLayer.getGraphicsContext2D();
        gc.clearRect(0, 0, pLayer.getWidth(), pLayer.getHeight());
        drawPoint();

        gc = pmLayer.getGraphicsContext2D();
        gc.clearRect(0, 0, pmLayer.getWidth(), pmLayer.getHeight());
        drawDino();

        gc = gLayer.getGraphicsContext2D();
        gc.clearRect(0, 0, gLayer.getWidth(), gLayer.getHeight());
        drawGhost();

        pLayer.toFront();
    }

    public ImageView loadDinoImage(){
        Image dinoimg = new Image("com/pacmangame/GUI/Assets/Player.gif");
        ImageView dino = new ImageView(dinoimg);
        dino.setFitWidth(50);
        dino.setPreserveRatio(true);
        return dino;
    }

    public ImageView loadPoint(){
        Image pointimg = new Image("/com/pacmangame/GUI/Assets/Point.png");
        ImageView point = new ImageView(pointimg);
        point.setFitWidth(50);
        point.setPreserveRatio(true);
        return point;
    }

    private double toX(int boardX){
        return ((oLayer.getWidth() * (boardX) / game.getCurrentMap().getGameBoard().getLength()) );
    }

    private double toY(int boardY){
        return ((oLayer.getHeight() * (boardY) / game.getCurrentMap().getGameBoard().getHeight()) );
    }
}

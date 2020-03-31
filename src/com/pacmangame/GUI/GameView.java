package com.pacmangame.GUI;

import com.pacmangame.character.Ghost;
import com.pacmangame.character.PacMan;
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
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import javax.naming.ldap.Control;
import java.util.ArrayList;
import java.util.Iterator;


public class GameView extends StackPane {
    private Game game;
    private double scale;

    public GameView(Game g, double WIDTH, double HEIGHT) {
        super();
        game = g;
        scale = WIDTH / g.getCurrentMap().getGameBoard().getHeight();
        this.getChildren().addAll(drawOLayer(), drawGLayer(), drawPLayer(), drawPmLayer());

    }

    private GridPane drawOLayer() {
        GridPane oLayer = new GridPane();
        for (int x = 0; x < game.getCurrentMap().getGameBoard().getHeight(); x++) {
            for (int y = 0; y < game.getCurrentMap().getGameBoard().getHeight(); y++) {
                Rectangle blueRect = new Rectangle();
                blueRect.setFill(Color.BLUE);
                blueRect.setWidth(scale);
                blueRect.setHeight(scale);
                //blueRect.setStroke(Color.BLACK);
                oLayer.add(blueRect, x, y);
            }
        }

        ArrayList<Obstacle> obstacleList = game.getObstacleList();
        for (Obstacle o : obstacleList) {
            int x = o.getxCoord();
            int y = o.getyCoord();
            Rectangle obstacle = new Rectangle();
            obstacle.setFill(Color.BLACK);
            obstacle.setWidth(scale);
            obstacle.setHeight(scale);
            oLayer.add(obstacle, x, y);
        }
        return oLayer;
    }

    private GridPane drawGLayer() {
        GridPane gLayer = new GridPane();

        for (int x = 0; x < game.getCurrentMap().getGameBoard().getHeight(); x++) {
            for (int y = 0; y < game.getCurrentMap().getGameBoard().getHeight(); y++) {
                Rectangle transparentRect = new Rectangle();
                transparentRect.setFill(Color.TRANSPARENT);
                transparentRect.setWidth(scale);
                transparentRect.setHeight(scale);
                gLayer.add(transparentRect, x, y);
            }
        }
        ArrayList<Ghost> ghostList = game.getGhostList();
        for (Ghost g : ghostList) {
            int x = g.getxCoord();
            int y = g.getyCoord();
            gLayer.add(loadCougarImage(), x, y);
        }
        return gLayer;
    }

    private GridPane drawPLayer() {
        GridPane pLayer = new GridPane();

        for (int x = 0; x < game.getCurrentMap().getGameBoard().getHeight(); x++) {
            for (int y = 0; y < game.getCurrentMap().getGameBoard().getHeight(); y++) {
                Rectangle transparentRect = new Rectangle();
                transparentRect.setFill(Color.TRANSPARENT);
                transparentRect.setWidth(scale);
                transparentRect.setHeight(scale);
                pLayer.add(transparentRect, x, y);
            }
        }
        ArrayList<Point> ghostList = game.getPointsList();
        for (Point p : ghostList) {
            int x = p.getxCoord();
            int y = p.getyCoord();
            Circle point = new Circle();
            point.setFill(Color.YELLOW);
            point.setRadius(scale/5);
            pLayer.add(point, x, y);
        }
        pLayer.setGridLinesVisible(true);
        return pLayer;
    }

    private GridPane drawPmLayer(){
        GridPane pmLayer = new GridPane();
        for (int x = 0; x < game.getCurrentMap().getGameBoard().getHeight(); x++) {
            for (int y = 0; y < game.getCurrentMap().getGameBoard().getHeight(); y++) {
                Rectangle transparentRect = new Rectangle();
                transparentRect.setFill(Color.TRANSPARENT);
                transparentRect.setWidth(scale);
                transparentRect.setHeight(scale);
                pmLayer.add(transparentRect, x, y);
            }
        }
        PacMan player = game.getPlayer();
        int x = player.getxCoord();
        int y = player.getyCoord();
        pmLayer.add(loadDinoImage(), x, y);
        return pmLayer;
    }

    public void refresh(){
        this.getChildren().addAll(drawGLayer(), drawPLayer(), drawPmLayer());
    }

    private ImageView loadDinoImage(){
        Image dinoimg = new Image("com/pacmangame/GUI/Assets/Player.gif");
        ImageView dino = new ImageView(dinoimg);
        dino.setFitWidth(scale);
        dino.setPreserveRatio(true);
        return dino;
    }

    private ImageView loadCougarImage(){
        Image cougarimg = new Image("com/pacmangame/GUI/Assets/Cougar.gif");
        ImageView cougar = new ImageView(cougarimg);
        cougar.setFitWidth(scale);
        cougar.setPreserveRatio(true);
        return cougar;
    }

    private ImageView loadPoint(){
        Image pointimg = new Image("/com/pacmangame/GUI/Assets/Point.png");
        ImageView point = new ImageView(pointimg);
        point.setFitWidth(scale);
        point.setPreserveRatio(true);
        return point;
    }

}


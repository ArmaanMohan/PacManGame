package com.pacmangame.gui;

import com.pacmangame.character.Ghost;
import com.pacmangame.character.PacMan;
import com.pacmangame.dependencies.Game;
import com.pacmangame.map_elements.Obstacle;
import com.pacmangame.map_elements.Point;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;



public class GameView extends StackPane {
    private Game game;
    private double scale;
    private GridPane staticLayer, variableLayer;

    public GameView(Game g, double WIDTH, double HEIGHT) {
        super();
        game = g;
        scale = WIDTH / g.getCurrentMap().getGameBoard().getHeight();
        staticLayer = drawStaticLayer();
        variableLayer = drawVariableLayer();
        this.setStyle("-fx-background-color: #001aab");
        this.getChildren().addAll(staticLayer, variableLayer);

    }

    private GridPane drawStaticLayer() {
        GridPane staticLayer = new GridPane();
        for (int x = 0; x < game.getCurrentMap().getGameBoard().getHeight(); x++) {
            for (int y = 0; y < game.getCurrentMap().getGameBoard().getHeight(); y++) {
                Pane spring = new Pane();
                spring.setMinHeight(scale);
                spring.setMinWidth(scale);
                staticLayer.add(spring, x, y);
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
            staticLayer.add(obstacle, x, y);
        }
        return staticLayer;
    }

    private GridPane drawVariableLayer() {
        GridPane variableLayer = new GridPane();
        variableLayer.setAlignment(Pos.CENTER);
        for (int x = 0; x < game.getCurrentMap().getGameBoard().getHeight(); x++) {
            for (int y = 0; y < game.getCurrentMap().getGameBoard().getHeight(); y++) {
                Pane spring = new Pane();
                spring.setMinHeight(scale);
                spring.setMinWidth(scale);
                variableLayer.add(spring, x, y);
            }
        }
        ArrayList<Ghost> ghostList = game.getGhostList();
        for (Ghost g : ghostList) {
            int x = g.getxCoord();
            int y = g.getyCoord();
            variableLayer.add(loadCougarImage(), x, y);
        }

        ArrayList<Point> pointList = game.getPointsList();
        for (Point p : pointList) {
            int x = p.getxCoord();
            int y = p.getyCoord();
            StackPane circleContainer = new StackPane();
            Circle point = new Circle();
            point.setFill(Color.YELLOW);
            point.setRadius(scale/5);
            circleContainer.getChildren().add(point);
            variableLayer.add(circleContainer, x, y);
        }
        PacMan player = game.getPlayer();
        int x = player.getxCoord();
        int y = player.getyCoord();
        variableLayer.add(loadDinoImage(), x, y);

        return variableLayer;
    }

    public void refresh(){
        if (game.continueGame()) {
            this.getChildren().remove(variableLayer);
            variableLayer = drawVariableLayer();
            this.getChildren().addAll(variableLayer);
        }
        else{
            this.getChildren().clear();
            this.getChildren().add(new EndView(game));
        }
    }

    private ImageView loadDinoImage(){
        Image dinoimg = new Image("com/pacmangame/gui/Assets/Player.gif");
        ImageView dino = new ImageView(dinoimg);
        dino.setFitWidth(scale);
        dino.setPreserveRatio(true);
        return dino;
    }

    private ImageView loadCougarImage(){
        Image cougarimg = new Image("com/pacmangame/gui/Assets/Cougar.gif");
        ImageView cougar = new ImageView(cougarimg);
        cougar.setFitWidth(scale);
        cougar.setPreserveRatio(true);
        return cougar;
    }

}


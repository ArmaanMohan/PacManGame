package com.pacmangame.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class GameView extends FlowPane {
    public GameView() {
        super();
        this.setStyle("-fx-background-color:red");
        ImageView point = loadPoint();
        ImageView dino = loadDinoImage();

        this.getChildren().add(point);
        this.getChildren().add(dino);
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
}

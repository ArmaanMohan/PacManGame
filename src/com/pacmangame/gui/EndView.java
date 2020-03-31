package com.pacmangame.gui;


import com.pacmangame.dependencies.Game;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class EndView extends Pane {

    public EndView(Game g){
        super();
        this.setStyle("-fx-background-color: #001aab");
        if (g.getPlayer().getLives() > 0){
            this.getChildren().add(loadWinImage());
        }
        else{
            this.getChildren().add(loadLoseImage());
        }

    }

    private ImageView loadWinImage(){
        Image winimg = new Image("com/pacmangame/gui/Assets/WinScreen.gif");
        ImageView win = new ImageView(winimg);
        win.setFitWidth(600);
        win.setPreserveRatio(true);
        return win;
    }

    private ImageView loadLoseImage(){
        Image loseimg = new Image("com/pacmangame/gui/Assets/LoseScreen.gif");
        ImageView lose = new ImageView(loseimg);
        lose.setFitWidth(600);
        lose.setPreserveRatio(true);
        return lose;
    }
}

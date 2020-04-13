package com.pacmangame.gui;


import com.pacmangame.dependencies.Game;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class EndView extends Pane {

    //Constructor for the end screen
    public EndView(Game g){
        super();
        //Creates a screen with a blue background
        this.setStyle("-fx-background-color: #001aab");
        //If the user has won, adds the win image
        if (g.getPlayer().getLives() > 0){
            this.getChildren().add(loadWinImage());
        }
        //Otherwise user has lost, loads lose screen
        else{
            this.getChildren().add(loadLoseImage());
        }

    }

    /**
     *
     * @return win
     */
    //Loads the image for the win screen and sets it to the win variable
    private ImageView loadWinImage(){
        Image winimg = new Image("com/pacmangame/gui/Assets/WinScreen.gif");
        ImageView win = new ImageView(winimg);
        win.setFitWidth(600);
        win.setPreserveRatio(true);
        return win;
    }

    /**
     *
     * @return lose
     */
    //Loads the image for the lose screen and sets it to the lose variable
    private ImageView loadLoseImage(){
        Image loseimg = new Image("com/pacmangame/gui/Assets/LoseScreen.gif");
        ImageView lose = new ImageView(loseimg);
        lose.setFitWidth(600);
        lose.setPreserveRatio(true);
        return lose;
    }
}

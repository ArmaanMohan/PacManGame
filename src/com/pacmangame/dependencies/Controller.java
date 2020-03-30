package com.pacmangame.dependencies;
import java.awt.event.KeyListener;

import com.pacmangame.GUI.*;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Controller {
	
    public Controller(Game g, GameView gv)
    {
    	EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            	//check whether the user has entered a valid key: an arrow key or one of "WASD"
                if(event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W || event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S || event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A || event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D){

                	//if the user enters the up arrow key or W/w on the keyboard
                    if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
                    	g.doMove("UP"); //move the player up one location (logic behind whether this is a valid move or not is already implemented. If this is a valid move, the player will be moved to the new location.)
                    	gv.refresh(); //Refresh the gameboard
                    }
                    
                    if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
                    	g.doMove("DOWN"); //move the player down one location (logic behind whether this is a valid move or not is already implemented. If this is a valid move, the player will be moved to the new location.)
                    	gv.refresh(); //Refresh the gameboard
                    }
                    
                    if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                    	g.doMove("LEFT"); //move the player left one location (logic behind whether this is a valid move or not is already implemented. If this is a valid move, the player will be moved to the new location.)
                    	gv.refresh(); //Refresh the gameboard
                    }

                    if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                    	g.doMove("RIGHT"); //move the player right one location (logic behind whether this is a valid move or not is already implemented. If this is a valid move, the player will be moved to the new location.)
                    	gv.refresh(); //refresh the gameboard
                    }
                }
                
                //Do not do anything if the user has entered something else. Refresh the gameboard and leave as is
                else
                {            
                	gv.refresh();
                }
               
            }
        };
       
    }

}

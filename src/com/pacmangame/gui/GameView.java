package com.pacmangame.gui;

import com.pacmangame.character.Ghost;
import com.pacmangame.character.PacMan;
import com.pacmangame.dependencies.Game;
import com.pacmangame.map_elements.Obstacle;
import com.pacmangame.map_elements.Point;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;


//GameView class
//This class is responsible for the visual aspect of the game, drawing the original game board as well as
// updating the locations of the pacman, ghosts and points when called by the controller class
public class GameView extends StackPane {
    private Game game;
    private double scale;
    private Canvas pointCanvas;
    private Pane ghostPane;
    private double tileWidth, tileHeight;
    private GameViewObjectManager gvOMGR;
    private ImageView pacmanImage;
    private final Image cougarimg = new Image("com/pacmangame/gui/Assets/Cougar.gif");
    private final Image dinoimg = new Image("com/pacmangame/gui/Assets/Player.gif");


    //Constructor taking a game object as well as the dimensions of the board in pixels
    public GameView(Game g, double WIDTH, double HEIGHT) {
        super();
        //sets the instance variable to the game object passed
        game = g;

        //set the tile dimensions of each cell in the grid
        tileHeight = HEIGHT / g.getCurrentMap().getGameBoard().getHeight();
        tileWidth = WIDTH / g.getCurrentMap().getGameBoard().getLength();

        // Sets the scale of the GridPane depending on the dimensions of the map chosen
        scale = WIDTH / g.getCurrentMap().getGameBoard().getHeight();

        // Ghosts reside on a Pane; Ghosts are represented by ImageView objects that will be relocated
        // as the game progresses
        ghostPane = new Pane();
        ghostPane.setPrefSize(WIDTH, HEIGHT);

        // Points are static and reside on a Canvas
        pointCanvas = new Canvas(WIDTH, HEIGHT);
        pointCanvas.getGraphicsContext2D().setFill(Color.YELLOW);

        // Pacman resides on a Pane as it is represented as an ImageView object which is relocated as
        // the game progresses
        Pane pmPane = new Pane();
        pmPane.setPrefSize(WIDTH, HEIGHT);

        // Create the GameViewObjectManager for tracking the Ghosts and their associated ImageView objects
        gvOMGR = new GameViewObjectManager();

        // The Pacman ImageView resides on its own Pane
        pacmanImage = loadDinoImage();
        pacmanImage.setFitHeight(tileHeight);
        pacmanImage.setFitWidth(tileWidth);
        pmPane.getChildren().add(pacmanImage);

        // Draw the initial positions of all the game objects: ghosts, pacman and points
        drawActiveLayers();

        //Change the background colour of the board
        this.setStyle("-fx-background-color: #001aab");
        // Add all the layers to the Scene
        this.getChildren().addAll(drawStaticLayer(), pointCanvas, ghostPane, pmPane);

    }

    // Method drawStaticLayer, creates the GridPane with the unchanging components of the game (the size of the
    // overall baord and the obstacles)  Returns the foundation (static layer) of the board
    private GridPane drawStaticLayer() {
        //Creates a new GridPane layer
        GridPane staticLayer = new GridPane();
        //for every location on the board (going from top to bottom on every column that progresses right to left
        for (int x = 0; x < game.getCurrentMap().getGameBoard().getLength(); x++) {
            for (int y = 0; y < game.getCurrentMap().getGameBoard().getHeight(); y++) {
                //Create a new Pane for each of the aformentioned locations and set its width and height to the
                // correct size as determined by scale (generated in the constructor)
                Pane spring = new Pane();
                spring.setMinHeight(scale);
                spring.setMinWidth(scale);
                staticLayer.add(spring, x, y);
            }
        }

        //assign an obstacleList to the list containing the coordinates of the obstacles
        ArrayList<Obstacle> obstacleList = game.getObstacleList();
        // For every obstacle in the list
        for (Obstacle o : obstacleList) {
            //Get its x and y coordinates
            int x = o.getxCoord();
            int y = o.getyCoord();
            //Create a new, black rectangle with the appropriate size as determined by scale (generated in constructor)
            Rectangle obstacle = new Rectangle();
            obstacle.setFill(Color.BLACK);
            obstacle.setWidth(scale);
            obstacle.setHeight(scale);
            //Add each rectangle, obstacle to the staticLayer of tbe board
            staticLayer.add(obstacle, x, y);
        }
        return staticLayer;
    }

    //Method drawActiveLayers, this method draws the points, ghosts and pacman on their respective canvases
    private void drawActiveLayers() {
        //For each ghost in the list, add it to the GameViewObjectManager and draw it on screen
        CharacterViewLocation cvl;
        ImageView sprite;

        for (Ghost g : game.getGhostList()) {
            sprite = loadCougarImage();
            cvl = new CharacterViewLocation(sprite, g);
            gvOMGR.addObject(cvl);
            ghostPane.getChildren().add(sprite);
            cvl.relocate(g.getxCoord() * tileWidth, g.getyCoord() * tileHeight);
        }

        //For each point in the list, add it to the GameViewObjectManager and draw it on the screen
        for (Point p : game.getPointsList()){
            drawPoint(p);
        }
        // Next, draw PacMan on screen, add the pacman object to the GameViewObjectManager
        PacMan pacMan = game.getPlayer();
        drawPacman(pacMan);
    }

    //Method clearTile, clears the specific coordinate tile on a specific canvas
    private void clearTile(int x, int y, Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }

    // Draws the pacman on the pacman canvas at the specific location
    private void drawPacman(PacMan pacMan){
        // If there was a Point in the spot that PacMan is moving do... clear it from the Canvas
        clearTile(pacMan.getxCoord(), pacMan.getyCoord(), pointCanvas);
        pacmanImage.relocate(pacMan.getxCoord()*tileWidth, pacMan.getyCoord()*tileHeight);
    }
    // Draws the point (as a circle) on the point canvas with the appropriate size
    private void drawPoint(Point p){
        pointCanvas.getGraphicsContext2D().fillOval(p.getxCoord()*tileWidth + tileWidth*0.1,p.getyCoord()*tileHeight + tileHeight*0.1,tileWidth*0.5,tileHeight*0.5);
    }

    //Method called by the controller after objects in the game (pacman, ghost, points) have moved in some way
    //Assuming the game has not ended, this method clears the dynamic gridpane and calls on the drawVariableLayer
    //To re-draw the layer with the updated locations of the objects
    public void refresh(){
        CharacterViewLocation old;
        //If the game has not ended
        if (game.continueGame()) {
            //For each ghost in the list, add it to the GameViewObjectManager and draw it on screen
            for (Ghost g : game.getGhostList()) {
                gvOMGR.move(g, tileWidth, tileHeight);
            }
            // Next, update the position of PacMan on screen
            drawPacman(game.getPlayer());
        }
        //If the game has ended
        else{
            //Clear the board and progress to the endView screen
            this.getChildren().clear();
            this.getChildren().add(new EndView(game));
        }
    }

    //Method that loads the image of the dino used to represent pacman
    //Returns the imageView of the dino
    private ImageView loadDinoImage(){
        //Create a new image and fill it with the dino image we created
        ImageView dino = new ImageView(dinoimg);
        dino.setFitWidth(scale);
        dino.setPreserveRatio(true);
        //Return this dino imageView
        return dino;
    }

    //Method that loads the image of the cougar used to represent the ghosts
    //Returns the imageView of the cougar
    private ImageView loadCougarImage(){
        ImageView cougar = new ImageView(cougarimg);
        cougar.setFitWidth(scale);
        cougar.setPreserveRatio(true);
        //Return this cougar imageView
        return cougar;
    }

}


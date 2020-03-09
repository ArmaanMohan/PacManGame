import java.awt.event.KeyListener;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Main extends Application {
	@Override public void start(Stage primaryStage) {
		try {
			Pane p = new Pane();
			Scene scene = new Scene(p,500,500);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Image PacMan = new Image(new FileInputStream("src/application/PacManMoving.gif"));
			ImageView imageViewPacman = new ImageView(PacMan);
			imageViewPacman.setX(220); 
			imageViewPacman.setY(220);
			p.getChildren().add(imageViewPacman);
			imageViewPacman.setFitHeight(75);
			imageViewPacman.setPreserveRatio(true);
		    
			Image Bajwa = new Image(new FileInputStream("src/application/Bajwa.png"));
			ImageView imageViewBajwa = new ImageView(Bajwa);
			imageViewBajwa.setX(70);
			imageViewBajwa.setY(100);
			p.getChildren().add(imageViewBajwa);
			imageViewBajwa.setFitHeight(80);
			imageViewBajwa.setPreserveRatio(true);
			
			Thread.sleep(0);
			
			primaryStage.setTitle("PacMan");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//System.out.println("Hello");
			//.relocate
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		System.out.println("Hello");
		}
}
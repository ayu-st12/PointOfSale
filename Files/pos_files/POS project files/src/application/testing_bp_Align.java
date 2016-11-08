package application;

import sales.main_Window;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class testing_bp_Align extends Application{

	public static void main(String args[])
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		  primaryStage.setTitle("BorderPane Test");
		    StackPane bp = new StackPane();
		    bp.setAlignment(Pos.CENTER);
		    bp.getChildren().add(new Label("Hello"));
		    
		    Scene scene = new Scene(bp, 300, 200);
		    primaryStage.setScene(scene);
		    primaryStage.show();		
	}

}

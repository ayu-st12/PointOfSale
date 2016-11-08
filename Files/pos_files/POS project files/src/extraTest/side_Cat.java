package extraTest;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class side_Cat extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public Pane sidePane()
	{
		Pane side_cat = new Pane();
		side_cat.setId("sideP");
		side_cat.setPrefHeight(50.0);
		side_cat.setPrefWidth(50.0);
		side_cat.setLayoutY(0.0);
		side_cat.setLayoutX(0.0);
		//side_cat.setStyle("-fx-background-color: #7fffd4");
		return side_cat;
	}
}

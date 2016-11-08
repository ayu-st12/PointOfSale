package maintainence;

import register.closeCash;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class main_win extends Application{

	public static void main(String args[])
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		Parent page = FXMLLoader.load(main_win.class.getResource("maintenance.fxml"));
		
		 Scene scene = new Scene(page);
		 scene.getStylesheets().add(getClass().getResource("edit_sales.css").toExternalForm());
		 primaryStage.setScene(scene);
       primaryStage.setTitle("Frame 2.4: POS- Maintenance");
       
       primaryStage.show();
		 
		 primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
				System.exit(0);
			}
		});
		
	}

}

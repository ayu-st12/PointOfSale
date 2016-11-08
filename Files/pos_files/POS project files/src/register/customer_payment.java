package register;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class customer_payment extends Application{

	public static void main(String args[])
	{
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		Parent page = FXMLLoader.load(customer_payment.class.getResource("customer_payment.fxml"));
		
		 Scene scene = new Scene(page);
		 scene.getStylesheets().add(getClass().getResource("edit_sales.css").toExternalForm());
		 scene.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
		 primaryStage.setScene(scene);
         primaryStage.setTitle("Frame 1.3: POS- Customer Payment (Underdevelopement)");
         
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

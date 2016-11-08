package register;

import sales.main_Window;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class editSale extends Application{

	static ScrollPane tik;
	
	public static void main(String args[])
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		 
		 Parent page = FXMLLoader.load(editSale.class.getResource("edit_sales_test.fxml"));
		 
		 AnchorPane center = (AnchorPane)page.lookup("#center_editSale");
		 AnchorPane handle = (AnchorPane)center.lookup("#tableScroll");
		 tik	   = (ScrollPane)handle.lookup("#Scrollt");
		 
		 Scene scene = new Scene(page);
		 scene.getStylesheets().add(getClass().getResource("edit_sales.css").toExternalForm());
		 scene.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
		 primaryStage.setScene(scene);
         primaryStage.setTitle("Frame 1.1: POS- Edit Sales (Underdevelopement)");
         
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

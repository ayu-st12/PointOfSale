package customers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class C_main extends Application {
	AnchorPane root;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent page = FXMLLoader.load(getClass().getResource("C_main.fxml"));
			
			Scene scene = new Scene(page,1000,700);
			scene.getStylesheets().add(getClass().getResource("Customers_main.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
			scene.getStylesheets().add(getClass().getResource("/sales/mainsales.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}

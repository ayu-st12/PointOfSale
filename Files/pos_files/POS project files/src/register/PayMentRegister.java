package register;

import sales.*;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class PayMentRegister extends Application{

	public static Stage primaryStage1;
	public static Scene scene, scene2, scene3, scene4, scene5, scene6, scene7; 
	public static Parent page, page2, page3, page4, page5, page6, page7;
	
	public static void main(String args[])
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	
		 page = FXMLLoader.load(getClass().getResource("/sales/main_sales_test.fxml"));
		 page2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/register/edit_sales_test.fxml"));
		 page3 = FXMLLoader.load(getClass().getResource("/register/customer_payment.fxml"));
		 page4 = FXMLLoader.load(getClass().getResource("/register/payments_register.fxml"));
		 page5 = FXMLLoader.load(getClass().getResource("/register/close_cash.fxml"));
		 page6 = FXMLLoader.load(getClass().getResource("/customers/C_main.fxml"));
		 page7 = FXMLLoader.load(getClass().getResource("test.fxml"));
		 Parent page8 = FXMLLoader.load(getClass().getResource("test.fxml"));
		 
		
		 Scene scene4 = new Scene(page4);
		 primaryStage1 = primaryStage;	
		 primaryStage1.setScene(scene4);
         primaryStage1.setTitle("Frame 1: POS(Underdevelopement)");
         primaryStage1.setMaximized(true);
         scene.getStylesheets().add(getClass().getResource("edit_sales.css").toExternalForm());
	 
    //code for the hamburger
	
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

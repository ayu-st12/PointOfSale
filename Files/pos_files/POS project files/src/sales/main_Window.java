package sales;

//import sales.Main_TableView;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Optional;
import javax.swing.JTextField;
import com.jfoenix.controls.JFXButton;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.*;
import javafx.util.Duration;

public class main_Window extends Application{
	
	public static int i=0;
	static ScrollPane s;
	static TextField total;
	static TextField subtotal;
	static TextField subtax;
	static AnchorPane cen_top;
	public static Stage primaryStage1;
	public static Scene scene, scene11, scene2, scene3, scene4, scene5, scene6, scene7; 
	public static Parent page, page11, page2, page3, page4, page5, page6, page7;
	public static void main(String args[])
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		 main_Controller.count_load=0;
		 page = FXMLLoader.load(main_Window.class.getResource("/sales/main_sales_test.fxml"));
		 
		 s = (ScrollPane)page.lookup("#tbl_center");
		 cen_top = (AnchorPane)page.lookup("#total_pane");
		 
		 total = (TextField)cen_top.lookup("#tf_total");
		 subtotal = (TextField)cen_top.lookup("#tf_subtotal");
		 subtax = (TextField)cen_top.lookup("#tf_taxes");
		 
		 scene = new Scene(page);
		 scene.getStylesheets().add(getClass().getResource("mainsales.css").toExternalForm());
		 scene.getStylesheets().add(getClass().getResource("calculator.css").toExternalForm());
		 scene.getStylesheets().add(getClass().getResource("ham.css").toExternalForm());
		 
		 primaryStage1 = primaryStage;	
		 primaryStage1.setScene(scene);
         primaryStage1.setTitle("Frame 1: POS(Underdevelopement)");
         primaryStage1.setMaximized(true);
        
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

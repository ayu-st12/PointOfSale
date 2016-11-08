package register;

import com.jfoenix.controls.JFXButton;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ticket_design extends Application{
	
	public static void main(String args[])
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		String id = "12345";
		double total = 190.0;
		String date = "1 Sep 1995";
		String time = "1:09:10 P.M.";
		String role = "Administrator";
		
		AnchorPane fnl = item_ticket(id,total,date,time,role);
		
		Scene scene = new Scene(fnl);
		
		scene.getStylesheets().add(getClass().getResource("edit_sales.css").toExternalForm());
		 
		
		primaryStage.setScene(scene);
        primaryStage.setTitle("Ticket");
        primaryStage.show();
		
	}

	private AnchorPane item_ticket(String id,double total, String date, String time, String role) {
		
		AnchorPane t = new AnchorPane();
		t.setPrefSize(300.0, 245.0);
		t.setStyle("-fx-background: #fff");
		
		Label id_l = new Label("Ticket ID: "+id);
		id_l.setFont(new Font("Roboto Regular", 20));
		id_l.setLayoutX(36.0);
		id_l.setLayoutY(46.0);
		t.setLeftAnchor(id_l, 20.0);
		t.setTopAnchor(id_l, 40.0);
		
		Label id_rs = new Label("Rs: "+total);
		id_rs.setFont(new Font("Roboto Regular", 20));
		id_rs.setLayoutX(46.0);
		id_rs.setLayoutY(66.0);
		t.setLeftAnchor(id_rs, 20.0);
		t.setTopAnchor(id_rs, 70.0);
		
		JFXButton salesreturn = new JFXButton("Sales Return");
		salesreturn.setFont(new Font("Roboto Regular", 15));
		t.setLeftAnchor(salesreturn, 25.0);
		t.setBottomAnchor(salesreturn, 20.0);
		
		JFXButton refund = new JFXButton("Refund");
		refund.setFont(new Font("Roboto Regular", 15));
		t.setLeftAnchor(refund, 135.0);
		t.setBottomAnchor(refund, 20.0);
		
		JFXButton print = new JFXButton("Print");
		print.setFont(new Font("Roboto Regular", 15));
		t.setLeftAnchor(print, 215.0);
		t.setBottomAnchor(print, 20.0);
		t.setRightAnchor(print, 15.0);

		
		Label m1 = new Label(date + " "+ time);
		m1.setStyle("-fx-textFill: #948f8f");
		t.setLeftAnchor(m1, 25.0);
		t.setTopAnchor(m1, 110.0);
		
		Label m2 = new Label(role);
		m2.setStyle("-fx-textFill: #948f8f");
		t.setLeftAnchor(m2, 25.0);
		t.setTopAnchor(m2, 130.0);
		
		t.getChildren().addAll(id_l,id_rs,m1,m2,salesreturn,refund,print);
		
		return t;
	}

}

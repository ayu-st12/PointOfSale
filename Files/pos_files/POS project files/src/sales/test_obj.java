package sales;

/*
 * 
 * DO NOT TOUCH IT.
 * 
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sales.*;
import database.dbfunc;
import database.dbfunc_other;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class test_obj extends Application{
	
	product p = new product();
	
	static dbfunc d = new dbfunc();
	 static dbfunc_other op = new dbfunc_other();
	 static ResultSet rs;
	 static Connection co;
	 static Statement s;
	String p_name;
	
	public static void main(String args[])
	{
		launch(args);
	}
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
		
		VBox content_p = new VBox(5); 
		
		ScrollPane scroller_p = new ScrollPane(content_p);
		scroller_p.setFitToWidth(true);
		
		AnchorPane fnl_p = setup();
		content_p.getChildren().add(fnl_p);
		
		Scene scene = new Scene(scroller_p,824,250);
		scene.getStylesheets().add(getClass().getResource("mainsales.css").toExternalForm());
		primaryStage.setScene(scene);
        primaryStage.setTitle("Product");
        primaryStage.show();		
        
	}
	
	public AnchorPane setup() throws SQLException
	{
 		
	int t=10;
	int c_p=0;
	double left_p=0;
	double top_p=0;
	double wwidth = 824;
	double wheight = 250;
	Pane f = new Pane();
	int i=0;
	AnchorPane fnl_p = new AnchorPane();
	
	co = d.connect();
	s = co.createStatement();
	String query = "select name from product where cat='1007';";
	rs = s.executeQuery(query);
	
	while(rs.next()) 
	{	
		String name = rs.getString(1);
		System.out.println(name + " "+i);
		
		if(i==0){
		Pane ap = p.item(name);
		ap.setLayoutX(12.0);
		left_p = 12.0;
		fnl_p.getChildren().add(ap);
		c_p++; i++;
		}
		else
		{
			Pane ap = p.item(name);
			System.out.println(left_p + " "+ c_p);
			left_p = left_p+115;
			if(c_p>6)
			{
				if(c_p%7==0){
					left_p = 15;
					top_p = top_p + 135;
					ap.setLayoutY(top_p);
				}
				else{
					ap.setLayoutY(top_p);
					left_p = left_p + 0;
				}
			}
			//
			ap.setLayoutX(left_p);
			fnl_p.getChildren().add(ap);
			c_p++; i++;
		}
	}
	return fnl_p;
	
	}
	
}

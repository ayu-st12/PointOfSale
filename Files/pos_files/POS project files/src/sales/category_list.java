package sales;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sales.product;
import sales.test_obj;
import database.dbfunc;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class category_list extends Application{
	
	static dbfunc db = new dbfunc();
	int i=0,l=0;
	static String prod_id;
	public static void main(String args[])
	{
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		//Pane fnl = item();
		/*
		Scene scene = new Scene(fnl,400,400);
		scene.getStylesheets().add(getClass().getResource("mainsales.css").toExternalForm());
		primaryStage.setScene(scene);
        primaryStage.setTitle("Product");
        primaryStage.show();
        */
	}

	public Pane item(String name)
	{
		Pane category_l = new Pane();
		category_l.setId("cat_name");
		category_l.setPrefWidth(295.0);
		category_l.setLayoutX(2.0);;
		
		Pane child_p1 = new Pane();
		child_p1.setLayoutX(2.0);
		child_p1.setLayoutY(2.0);
		child_p1.setPrefHeight(50.0);
		child_p1.setPrefWidth(50.0);
		
		ImageView img = new ImageView();
		img.fitHeightProperty().set(35.0);
		img.fitWidthProperty().set(35.0);
		img.setLayoutX(7.0);
		img.setLayoutY(7.0);
		img.pickOnBoundsProperty().set(true);
		img.preserveRatioProperty().set(true);
		
		final Image image = new Image(product.class.getResourceAsStream("img/ic_chevron_right_grey600_36dp.png"));	
	    img.setImage(image);
		
		child_p1.getChildren().addAll(img);
		
		
		Pane child_p2 = new Pane();
		//child_p2.setId("labelH"+(++i));
		child_p2.setId("childp");
		child_p2.setPrefHeight(50.0);
		child_p2.setPrefWidth(150.0);
		child_p2.setLayoutX(52.0);
		child_p2.setLayoutY(2.0);
		
		Label p_name = new Label();
		//p_name.setId("val"+(++i));
		p_name.setId("label");
		p_name.setLayoutY(15.0);
		p_name.setLayoutX(-2.0);
		p_name.setPrefHeight(20.0);
		p_name.setText(name);
		p_name.setTextFill(Color.web("#616161"));
		p_name.setFont(new Font("Roboto Black", 16));

		child_p2.getChildren().addAll(p_name);
		
        category_l.getChildren().addAll(child_p1,child_p2);
        
        category_l.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				
				 Connection co;
				 Statement stmt;
				 String result; ResultSet rs1;
				 String id = null; String name= null;
				 try {
				 co = db.connect();
				 stmt = co.createStatement();
				
				// TODO Auto-generated method stub
				System.out.println("Category clicked");
				main_Controller r = new main_Controller();
				Pane m =  (Pane)category_l.lookup("#childp");
				Label l = (Label) m.lookup("#label");
				String s = l.getText();
				
				
				String query1 = "select id from categories where name='"+s+"';";
				 rs1 = stmt.executeQuery(query1);
				 while(rs1.next())
				 {
					prod_id = rs1.getString(1);
				 }
				 

				 System.out.println("Id of category is:" + prod_id);
					r.testfunc(s,prod_id);
					//r.set_product();
				 //System.out.println("Returned back! Try to call products");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			}) ;
		return category_l;
	}
}

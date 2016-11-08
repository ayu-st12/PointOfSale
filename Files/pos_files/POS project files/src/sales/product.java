package sales;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import database.dbfunc;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class product extends Application{

	static dbfunc db = new dbfunc();
	static String name_p;
	static double price;
	static double tax_val;
	static String taxcat;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public Pane item(String name)
	{
		Pane product_ap = new Pane();
		product_ap.setId("root");
		product_ap.setPrefWidth(100.0);
		product_ap.setPrefHeight(120.0);
		product_ap.setMaxWidth(170.0);
		product_ap.setLayoutY(5.0);
		product_ap.setLayoutX(5.0);
		//product_ap.setLeftAnchor(product_ap, 5.0);
		//product_ap.setTopAnchor(product_ap, 60.0);
		
		Pane child_p1 = new Pane();
		child_p1.setLayoutY(90.0);
		child_p1.setPrefHeight(30.0);
		child_p1.setPrefWidth(100.0);
		child_p1.setId("pane3");
		
		Label p_name = new Label();
		p_name.setAlignment(Pos.CENTER);
		p_name.setLayoutY(7.0);
		p_name.setPrefHeight(16.0);
		p_name.setPrefWidth(100.0);
		p_name.setText(name);
		p_name.setId("label_p");
		p_name.setTextFill(Color.WHITE);
		p_name.wrapTextProperty().set(true);
		p_name.setStyle("-fx-textAlignment: CENTER;");
		p_name.setFont(new Font("Roboto Black", 14));

		
		child_p1.getChildren().addAll(p_name);
		
		Pane child_p2 = new Pane();
		child_p2.setPrefHeight(90.0);
		child_p2.setPrefWidth(100.0);
		child_p2.setId("root_pane2");
		
		ImageView img = new ImageView();
		img.fitHeightProperty().set(50.0);
		img.fitWidthProperty().set(50.0);
		img.setLayoutX(25.0);
		img.setLayoutY(25.0);
		img.pickOnBoundsProperty().set(true);
		img.preserveRatioProperty().set(true);
		
		
		final Image image = new Image(product.class.getResourceAsStream("img/ic_shopping_grey600_48dp.png"));	
	    img.setImage(image);
        
        child_p2.getChildren().addAll(img);
	
		product_ap.getChildren().addAll(child_p1,child_p2);
        product_ap.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				Pane m =  (Pane)product_ap.lookup("#pane3");
				Label l = (Label) m.lookup("#label_p");
				String s = l.getText();
				System.out.println("Feed data to table, Product clicked is:"+s);
				
				Connection co;
				 Statement stmt;
				 String result; ResultSet rs1,rs2;
				 String id = null; String name= null;
				 try {
				 co = db.connect();
				 stmt = co.createStatement();
				 
				 String query1 = "select name,sp,taxcat from product where name='"+s+"';";
				 rs1 = stmt.executeQuery(query1);
				 while(rs1.next())
				 {
					name_p = rs1.getString(1);
					price = rs1.getDouble(2);
					taxcat = rs1.getString(3);		
				 }
				 
				 String query2 = "select rate from taxes where category='"+taxcat+"';";
				 rs2 = stmt.executeQuery(query2);
				 while(rs2.next())
				 { 
					tax_val = rs2.getDouble(1);
				}
				 
				 main_Controller r = new main_Controller();
				 r.prod_detail(name_p,price,tax_val);
				 
				 }catch(Exception e){
					 e.printStackTrace();
				 }
				
			}
        	
        });
        
		return product_ap;
	}
}

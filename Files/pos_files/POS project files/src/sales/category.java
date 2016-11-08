package sales;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import javafx.scene.control.TextField;
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
import extraTest.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sales.Fetch_Categories;
import sales.category_list;
import database.*;

public class category extends Application{
	
	private Fetch_Categories fc = new Fetch_Categories();
	static category_list c4 = new category_list();
	static cat_top ct = new cat_top();
	static side_Cat sp = new side_Cat();
	static dbfunc d = new dbfunc();
	ResultSet rs;
	
	ResultSet rs_catlist ;
	
	static int i=1;
	public static int child_cat=0;
	Pane ap;
	
	public ImageView iv;
	public Pane catp;
	static public Pane cat_top ;//= new Pane();
	static public Pane side_cat;
	static public VBox vb;
	double left=0;
	double top =0;
	AnchorPane fnl = new AnchorPane();	
	AnchorPane subitem1 = new AnchorPane();
	static AnchorPane a;
	
	
	public static void main(String args[])
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		cat_top = ct.catTop();
				
		side_cat = sp.sidePane();
		
		side_cat.setVisible(false);
		
		//STARTS HERE
		
		catp = (Pane) cat_top.lookup("#c1");
		iv = (ImageView) catp.lookup("#ClickSwing");
		System.out.println("ID:" + iv);
		
		iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				if(child_cat>0){
				System.out.println("image clicked");
				System.out.println("TOP PANE child count:"+child_cat);
				//effect(child_cat,iv, side_cat);
				System.out.println("Returned from func now decrementing child_cat:"+child_cat);
				child_cat--;
				side_cat.getChildren().clear();
				System.out.println("Decremented child_cat:"+child_cat);
				
				if(child_cat>0)
				{
					side_cat.setVisible(true);
				}
				
				else if(child_cat==0)
				{	
					side_cat.getChildren().clear();
					double angle=0.0;
					Rotate rotate = new Rotate();
					iv.getTransforms().add(rotate);
					angle += 180.0;
					rotate.setAngle(angle);
					rotate.setPivotX(iv.getFitWidth()/2);
					rotate.setPivotY(iv.getFitHeight()/2);
					Image img = new Image(getClass().getResource("img/ic_arrow_right_grey600_36dp.png").toExternalForm());
					//System.out.println(angle);
					iv.setImage(img);	
					side_cat.setVisible(false);
				} 
				}
			}
		});
		
		//ENDS HERE	
		fnl.setLeftAnchor(side_cat, 0.0);
		fnl.setTopAnchor(side_cat, 55.0);
		fnl.setBottomAnchor(side_cat, 0.0);
		fnl.setRightAnchor(side_cat, 0.0);
		
		fnl.getChildren().addAll(cat_top);
	
		
		VBox content = new VBox(5);
		content.setId("content");
		ScrollPane add_cat_list = new ScrollPane();
		
		add_cat_list.setContent(content);
		add_cat_list.setFitToWidth(true);
		
		
		rs_catlist = fc.fetch_cat();
		
		a = setup(rs_catlist);
		a.getChildren().add(side_cat);
		content.getChildren().add(a);
		
		Scene scene = new Scene(add_cat_list,300,200);
		scene.getStylesheets().add(getClass().getResource("mainsales.css").toExternalForm());
		primaryStage.setScene(scene);
        primaryStage.setTitle("Product");
        primaryStage.show();

	}
	
	//effect function for hamburger
	public void effect(int ch, ImageView s, Pane t){
		if( ch>0){
			//child_cat--;
			System.out.println("In effect() child_cat :"+ch);
			double angle=0.0;
			Rotate rotate = new Rotate();
			s.getTransforms().add(rotate);
			angle += 180.0;
			rotate.setAngle(angle);
			rotate.setPivotX(s.getFitWidth()/2);
			rotate.setPivotY(s.getFitHeight()/2);
			Image img = new Image(getClass().getResource("img/ic_arrow_right_grey600_36dp.png").toExternalForm());
			//System.out.println(angle);
			s.setImage(img);
			t.setVisible(true);
			}
	}
	
	public AnchorPane setup(ResultSet rs_catlist){
		int i=0;
		try {
			
			if(rs_catlist != null ){
				System.out.println("Got Values");
				
				while(rs_catlist.next()) 
				{	
					String id = rs_catlist.getString(1);
					String name = rs_catlist.getString(2);
					System.out.println(id +"  "+ name + " "+i);
					
					if(i==0){
					ap = c4.item(name);
					if(child_cat<=1)
					{
						ap.setLayoutY(65.0);
						top=65.0;
					}
						//ap.setTopAnchor(ap, 5.0);
					fnl.getChildren().add(ap);
					}
					else{
						ap = c4.item(name);
						top = top+60;
						ap.setLayoutY(top);
						//ap.setTopAnchor(ap, top);
						fnl.getChildren().add(ap);				
					}
					i++;
				}
				//fnl.getChildren().add(side_cat);
			}
			else{
				System.out.println("GOT NULL");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fnl;

	}
	
	public void testfunc(String main_cat) throws SQLException{
		
		if(cat_top!=null){
			System.out.println(main_cat);
			
			 
			 Connection co;
			 Statement s;
			 String result;
			 String id = null; String name= null;
			 
			 co = d.connect();
			 s = co.createStatement();
			
			 String query = "select id,name from categories where pid IN(select id from categories where name="+"'"+main_cat+"'"+");";
			 rs = s.executeQuery(query);
			 
			 AnchorPane p = setup(rs);
			 side_cat.getChildren().add(p);
			 
			 System.out.println(rs);			 
			 
			catp = (Pane) cat_top.lookup("#c1");
			System.out.println(cat_top);
			System.out.println(catp);
			iv = (ImageView) catp.lookup("#ClickSwing");
			child_cat++;
			System.out.println(iv);
			System.out.println("Category called child count:"+child_cat);
			effect(child_cat,iv, side_cat);
		}
		else
		{
			System.out.println("Not found");
		}
	}
	 
}

package sales;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.dbfunc;
import extraTest.cat_top;
import extraTest.side_Cat;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class category_system extends Application{

	static public Pane cat_top ;
	static cat_top ct = new cat_top();
	
	static side_Cat sp = new side_Cat();
	static public Pane side_cat;
	
	private Fetch_Categories fc = new Fetch_Categories();
	
	static dbfunc d = new dbfunc();
	
	static category_list c4 = new category_list();
	
	public static String name_db;
	public Pane catp;
	public ImageView iv;
	public static int child_cat=0;
	ResultSet rs_catlist ;
	static AnchorPane catlist;
	AnchorPane base = new AnchorPane();
	AnchorPane fnl;
	ResultSet rs;
	static Image img;
	static ScrollPane s; 

	public static void main(String args[])
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		s = complete();
		
		Scene scene = new Scene(s,300,200);
		scene.getStylesheets().add(getClass().getResource("mainsales.css").toExternalForm());
		primaryStage.setScene(scene);
        primaryStage.setTitle("Product");
        primaryStage.show();
	}
	
	public ScrollPane complete() throws Exception
	{
		cat_top = ct.catTop();
		side_cat = sp.sidePane();
		
		catp = (Pane) cat_top.lookup("#c1");
		iv = (ImageView) catp.lookup("#ClickSwing");
		
		//ADDING MOUSE CLICK EVENT TO TOP CATEGORY IMAGE(ARROW)
		iv.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				if(child_cat>0){
				//System.out.println("image clicked");
				//System.out.println("TOP PANE child count:"+child_cat);
				//System.out.println("Decrementing child_cat:"+child_cat);
				child_cat--;
				//System.out.println("Now Value of child_cat:"+child_cat);
				//side_cat.getChildren().clear();
				
				if(child_cat>0)
				{
					Connection co;
					 Statement s;
					 String result;
					 String id = null; String name= null;
					 try {
					 co = d.connect();
					 s = co.createStatement();
					 
					 String query = "select id,name from categories where pid IN(select pid from categories where name='"+name_db+"');";
					 
					 rs = s.executeQuery(query);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
					 AnchorPane p = setup(rs);
					 side_cat.getChildren().clear();
					 System.out.println("side category cleared: "+ name_db);
					 
					 String query1 = "select name from categories where id IN(select pid from categories where name='"+name_db+"');";
					 try {
						 co = d.connect();
						 s = co.createStatement();
						ResultSet rs1 = s.executeQuery(query1);
						while(rs1.next())
						{
							name_db = rs1.getString(1);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
					 side_cat.getChildren().add(p);
					 side_cat.setVisible(true);
					//catlist.setVisible(false);
				}
				
				else if(child_cat==0)
				{	
					catlist.setVisible(true);
					//side_cat.getChildren().clear();
					double angle=0.0;
					Rotate rotate = new Rotate();
					iv.getTransforms().add(rotate);
					angle += 180.0;
					rotate.setAngle(angle);
					rotate.setPivotX(iv.getFitWidth()/2);
					rotate.setPivotY(iv.getFitHeight()/2);
					Image img = new Image(getClass().getResource("img/ic_arrow_right_grey600_36dp.png").toExternalForm());
					iv.setImage(img);	
					side_cat.setVisible(false);
					//catlist.setVisible(true);
				} 
				}
			}
		});
		//MOUSE CLICK EVENT CLOSED HERE!
		
		VBox content = new VBox(5);
		content.setId("content");
		ScrollPane add_cat_list = new ScrollPane();
		add_cat_list.setContent(content);
		add_cat_list.setFitToWidth(true);
		
		content.getChildren().add(cat_top);
		
		rs_catlist = fc.fetch_cat();
		catlist = setup(rs_catlist);
		
		base.getChildren().add(catlist);
		
		base.setLeftAnchor(side_cat, 0.0);
		base.setTopAnchor(side_cat, 4.0);
		base.setBottomAnchor(side_cat, 0.0);
		base.setRightAnchor(side_cat, 0.0);
		
		base.getChildren().add(side_cat);
		
		//catlist.setVisible(false);
		side_cat.setVisible(false);
		
		content.getChildren().add(base);
		//a.setVisible(false);
		//content.getChildren().add(side_cat);
		return add_cat_list;

	}
	
	public void effect(int ch, ImageView s, Pane t){
		if( ch>0){
			//child_cat--;
			//System.out.println("In effect() child_cat :"+ch);
			
			if(child_cat<=1)
			{
				double angle=0.0;
				Rotate rotate = new Rotate();
				s.getTransforms().add(rotate);
				angle += 180.0;
				rotate.setAngle(angle);
				rotate.setPivotX(s.getFitWidth()/2);
				rotate.setPivotY(s.getFitHeight()/2);
				img = new Image(getClass().getResource("img/ic_arrow_right_grey600_36dp.png").toExternalForm());
				s.setImage(img);
			}
			t.setVisible(true);
			catlist.setVisible(false);
			}
	}
	
	public AnchorPane setup(ResultSet rs_catlist){
		int i=0;
		Pane ap;
		double left=0;
		double top =0;
		fnl = new AnchorPane();
		fnl.setId("m_cate");
		
		try {
			
			if(rs_catlist != null ){
				//System.out.println("Got Values");
				
				while(rs_catlist.next()) 
				{	
					String id = rs_catlist.getString(1);
					String name = rs_catlist.getString(2);
					System.out.println(id +"  "+ name + " "+i);
					
					if(i==0){
					ap = c4.item(name);
					if(child_cat<=1)
					{
						ap.setLayoutY(5.0);
						top=5.0;
					}
					fnl.getChildren().add(ap);
					}
					else{
						ap = c4.item(name);
						top = top+60;
						ap.setLayoutY(top);
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
			System.out.println("Category is: "+main_cat);
			
			 category_system.name_db = main_cat;
			 Connection co;
			 Statement s;
			 String result;
			 String id = null; String name= null;
			 
			 co = d.connect();
			 s = co.createStatement();
			
			 String query = "select id,name from categories where pid IN(select id from categories where name="+"'"+main_cat+"'"+");";
			 rs = s.executeQuery(query);
			 
			 AnchorPane p = setup(rs);
			 side_cat.getChildren().clear();
			 side_cat.getChildren().add(p);
			 
			 System.out.println(rs);			 
			 
			catp = (Pane) cat_top.lookup("#c1");
			System.out.println(cat_top);
			System.out.println(catp);
			iv = (ImageView) catp.lookup("#ClickSwing");
			child_cat++;
			System.out.println(iv);
			System.out.println("Category called child count:"+child_cat);
			//catlist.setVisible(false);
			effect(child_cat,iv, side_cat);
		}
		else
		{
			System.out.println("Not found");
		}
	}

	
}
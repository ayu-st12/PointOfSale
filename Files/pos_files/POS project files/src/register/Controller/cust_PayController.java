package register.Controller;

import database.dbfunc_other;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import register.CustomerTicket;
import sales.main5_xmlread;
import sales.main_Window;
import sales.main_Controller.SalesTable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import database.*;

public class cust_PayController {
	
	static JFXTextField val;
	static Statement stmt;
	static double debt;
	static double lim;
	static Label updateDebt;
	static String acid;
	
	static String g_sid,g_base,g_ttax,g_s_rs;
	static ScrollPane b; 
	static int mask2 =0;
	static int task1 =0,task2;
	static String name_p;
	static double price;
	static double tax_val;
	static String taxcat;
	static double unit=1;
	static double value;
	public static String tid;
	static int test = 0;
	public static double width;
	static int n,rows=0;
	int item = 9;
	static String name = null;
	static Double amt = null; 
	static Timestamp dt = null;
	final BooleanProperty firstTime = new SimpleBooleanProperty(true);
	static ObservableList<SalesTable> data_tbl;
	static public TableView<SalesTable> table_p ;
	static ArrayList<SalesTable> al=new ArrayList<SalesTable>();
	
	dbfunc db = new dbfunc();
	static dbfunc_other one = new dbfunc_other();
	//fullTicket ft = new fullTicket();
	
	static ScrollPane tick;
	static ResultSet rs1;
	static Connection co;
	static String ph_copy;
		
	 
	@FXML
	private VBox menu;
	static boolean j=false;
	static boolean k=false;
	static boolean l=false;
	static boolean o=false;
	
	@FXML
	private TextField phnoticket;
	
	@FXML
	private AnchorPane right_apane;
	
	@FXML
	private AnchorPane center_editSale;
	
	@FXML
	private ScrollPane Scrollt;
	
	@FXML
	private AnchorPane asearch;
	
	@FXML
	private Label date_top;
	
	@FXML
	private TextField skey;
	
	@FXML
	private TextField adv_tid;
	
	@FXML
	private DatePicker adv_start;
	
	@FXML
	private DatePicker adv_end;
	
	@FXML
	private TextField adv_cust;
	
	@FXML
	private ScrollPane tbl_center_edit;
	
	@FXML
	private ImageView hbg;
	
	@FXML
	private AnchorPane mbar;
	public static Map<String, String> data = new HashMap<String, String>();
	
	@FXML
	AnchorPane mbar_rest;
	
	@FXML
	AnchorPane mbar_total;
	
	
	@FXML
	private void initialize(){
	
		
		Translate tr = new Translate();
		mbar.getTransforms().add(tr);
		tr.setX(-310);
		mbar.toFront();
	
		System.out.println(hbg+"customer");
		hbg.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event){
				    System.out.println("image clicked");
					effect(hbg, mbar);
				}
			});
		
		Translate tr_mbar_total = new Translate();
		mbar_total.getTransforms().add(tr_mbar_total);
		tr_mbar_total.setX(-2000);
		

		mbar_rest.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e){
				effect(hbg,mbar);
			}
		});
		//creating the main menu
			//-------------------------------//
			//creating the head bar
			
			Pane id_pane = new Pane();
			id_pane.setPrefWidth(300);
			id_pane.setPrefHeight(140);
			id_pane.setId("id_pane");
			//head bar code ends
			
			//main menu code
			main5_xmlread xml = new main5_xmlread();
			boolean register_flag = false;
			boolean administration_flag = false;
			boolean utilities_flag = false;
			boolean system_flag = false;
			data = xml.return_func("eid"); //pass proper eid here from the login system.
			Map.Entry<String,String> ent = data.entrySet().iterator().next();
			String Type = ent.getValue();
			//System.out.println("Type: "+Type);
			if(Type.equals("1"))
				Type = "Administrator";
			else if(Type.equals("2"))
				Type = "Manager";
			else if(Type.equals("3"))
				Type = "Employee";
			if(Type.equals("4"))
				Type = "Guest";
			Label nam = new Label("Name");
			nam.setFont(Font.font ("Roboto", FontWeight.BOLD, 13));
			nam.setLayoutX(20);
			nam.setLayoutY(90);
			Label type = new Label(Type);
			type.setFont(Font.font ("Roboto", 13));
			type.setLayoutX(20);
			type.setLayoutY(110);
			ImageView profimg = new ImageView();
			Image prof = new Image(getClass().getResource("/sales/profile.png").toExternalForm());
			profimg.setImage(prof);
			profimg.setLayoutX(15);
			profimg.setLayoutY(20);
			//.getChildren().add(profimg);
			id_pane.getChildren().addAll(profimg, nam,type);
			
			for (Map.Entry<String, String> entry : data.entrySet()) {
				if(entry.getValue().equals("true")){
					if(entry.getKey().equals("sale")||entry.getKey().equals("edit_sale")||entry.getKey().equals("customer_payment")||entry.getKey().equals("payments")||entry.getKey().equals("close_cash")){
						register_flag=true;
					}
					if(entry.getKey().equals("customers")||entry.getKey().equals("stock")||entry.getKey().equals("sales")||entry.getKey().equals("maintenance")||entry.getKey().equals("presence_management")){
						administration_flag=true;
					}
					if(entry.getKey().equals("tools")){
						utilities_flag=true;
					}
					if(entry.getKey().equals("change_password")||entry.getKey().equals("configuration")||entry.getKey().equals("printers")||entry.getKey().equals("check_in_out")||entry.getKey().equals("logout")){
						system_flag=true;
					}
				}
			}
			create_register_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
			create_administration_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
			create_utilities_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
			create_system_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
			
			//main menu code ends
			mbar.getChildren().addAll(id_pane);
			//main menu bar  code ends here
	
		 
		 
		 
		 
		 
		 
		
	/***********************DISABLING END DATE PICKER***************/
		co = db.connect();
		try {
			stmt = co.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	/***********************DISABLING END DATE PICKER***************/	
		
	/***********************LOADING TICKETS START HERE***************/
		
		Scrollt.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				
				
				// TODO Auto-generated method stub
				width = Scrollt.getWidth();
				n = (int)Math.floor(width/300);
				System.out.println("Listener: "+n);
				
				
				try {
					
					if(test!=0){
							
						rs1.beforeFirst();
						
						System.out.println("Drawing");
						int c_p=0;
						double left_p=5.0;
						double top_p=5.0;
						int x = n;
						AnchorPane fnl_p = new AnchorPane();
						VBox content_p = new VBox(5); 
						int i=0;
						 
						Scrollt.setContent(content_p);
						Scrollt.setFitToWidth(true);
						System.out.println("here");
						while(rs1.next())
						 {
							datasetter1(rs1.getString(1));
							
							if(i==0){
								
								AnchorPane ap = item_ticket(name,debt,acid);
								ap.setLayoutX(left_p);
								ap.setLayoutY(top_p);
								ap.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 2, 0, 0, 1);" +
										"-fx-background-color: #fff;");
								fnl_p.getChildren().add(ap);
								c_p++;i++;
								}
								else
								{
									AnchorPane ap = item_ticket(name,debt,acid);
									//System.out.println(left_p + " "+ c_p);
									left_p = left_p+305;
									if(c_p>=x)
									{
										//System.out.println(" item & x "+c_p + " "+x);
										if(c_p % (x) ==0){
											left_p = 5;
											top_p = top_p +250;
											ap.setLayoutY(top_p);
										}
										else{
											ap.setLayoutY(top_p);
											left_p = left_p + 0;
										}
									}
									//
									ap.setLayoutX(left_p);
									ap.setLayoutY(top_p);
									ap.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 2, 0, 0, 1);" +
											"-fx-background-color: #fff;");
									fnl_p.getChildren().add(ap);
									c_p++;i++;
								}
							
						}
						content_p.getChildren().add(fnl_p);	
						 
							}
					else{
						System.out.println("tid is null at Listener");
					}
						}
						catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				
			}
        });
	
	/***********************LOADING TICKETS ENDS HERE******************/	
	
		
	/************************ADVANCED SEARCH VISIBILITY****************/
		
		asearch.setVisible(false);
		
	/************************ADVANCED SEARCH VISIBILTIY ENDS**********/	
		
	/************************FOCUS PROPERTY OF TEXTFEILD**************/	
		
		phnoticket.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
            	right_apane.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
            else{
            	firstTime.setValue(true);
            }
        });	
		
	/***************FOCUS PROPERTY OF TEXTFEILD ENDS HERE**************/
		
	/************SETTING TIMER CODE BEGINS HERE**************/
		java.util.Timer timer = new java.util.Timer();
		timer.schedule(new TimerTask() {
		    public void run() {
		         Platform.runLater(new Runnable() {
		            public void run() {
		                Date date = new Date();
		                final DateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		                String time = timeFormat.format(date);
		                date_top.setText(time);
		            }
		        });
		    }
		}, 100,100);
	
	/************SETTING TIMER CODE ENDS HERE**************/			

	}
	
	public void showAdvanced()
	{
		phnoticket.setText(null);
		asearch.setVisible(true);
	}
	
	public void closeAdvanced()
	{
		asearch.setVisible(false);
	}
	
	public void searchTicket() throws SQLException
	{
		test = 0;
		 Statement stmt1 = co.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		 
		 
		 if(phnoticket.getText()!=null)
		 {
			 test = 1;
			 
			 int c_p=0;
				double left_p=5.0;
				double top_p=5.0;
				int x = n;
				AnchorPane fnl_p = new AnchorPane();
				VBox content_p = new VBox(5); 
			 
			 String ph = phnoticket.getText();
			 ph_copy = ph;
			 String query1 = "select id from customers where phone='"+ph+"';";
			 
			 rs1 = stmt1.executeQuery(query1);
			 
			 if (!rs1.isBeforeFirst() ) {    
				 System.out.println("No data");
				 Alert alert=new Alert(AlertType.WARNING);
					alert.setTitle("Customer Not Found!");
					String s="No record found for Phone No: "+phnoticket.getText();
					alert.setContentText(s);
					alert.showAndWait();
					asearch.setVisible(false);
				}
			 else{
				 //CODE HERE
				 while(rs1.next()){
					 
					 datasetter1(rs1.getString(1));
					 System.out.println(rs1.getString(1));
				 		
				 
				 n = (int)Math.floor(width/300);
					System.out.println("Here n = "+n);
					
					Scrollt.setContent(content_p);
					Scrollt.setFitToWidth(true);
					
					AnchorPane ap = item_ticket(name,debt,acid);
					ap.setLayoutX(left_p);
					ap.setLayoutY(top_p);
					
					ap.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 2, 0, 0, 1);" +
							"-fx-background-color: #fff;");
					fnl_p.getChildren().add(ap);
					
			
			content_p.getChildren().add(fnl_p);	
		 
		 
		 asearch.setVisible(false);	

				 }
			}
		 }
		 else if(skey.getText()!=null)
		 {
			 test = 1;
			 
			 int c_p=0;
				double left_p=5.0;
				double top_p=5.0;
				int x = n;
				AnchorPane fnl_p = new AnchorPane();
				VBox content_p = new VBox(5); 
			 
			 System.out.println("here again");
			 String key = skey.getText();
			 String query1 = "select id from customers where searchkey='"+key+"';";
			 
			 rs1 = stmt1.executeQuery(query1);
			 
			 if (!rs1.isBeforeFirst() ) {    
				 System.out.println("No data");
				 Alert alert=new Alert(AlertType.WARNING);
					alert.setTitle("Customer Not Found!");
					String s="No record found for Phone No: "+phnoticket.getText();
					alert.setContentText(s);
					alert.showAndWait();
					asearch.setVisible(false);
				}
			 else{
				 //CODE HERE
				 while(rs1.next()){
					 
					 datasetter1(rs1.getString(1));
					 System.out.println(rs1.getString(1));
				 		
				 
				 n = (int)Math.floor(width/300);
					System.out.println("Here n = "+n);
					
				
					
					if(rs1.isFirst()){
						AnchorPane ap = item_ticket(name,debt,acid);
						ap.setLayoutX(left_p);
						ap.setLayoutY(top_p);
						
						ap.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 2, 0, 0, 1);" +
								"-fx-background-color: #fff;");
						fnl_p.getChildren().add(ap);
						c_p++;
					}
					else{
						AnchorPane ap = item_ticket(name,debt,acid);
						//System.out.println(left_p + " "+ c_p);
						left_p = left_p+305;
						if(c_p>=x)
						{
							//System.out.println(" item & x "+c_p + " "+x);
							if(c_p % (x) ==0){
								left_p = 5;
								top_p = top_p +250;
								ap.setLayoutY(top_p);
							}
							else{
								ap.setLayoutY(top_p);
								left_p = left_p + 0;
							}
						}
						//
						ap.setLayoutX(left_p);
						ap.setLayoutY(top_p);
						ap.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 2, 0, 0, 1);" +
								"-fx-background-color: #fff;");
						fnl_p.getChildren().add(ap);
						c_p++; 
					}	
		 
		 
		 asearch.setVisible(false);	

				 }
				 content_p.getChildren().add(fnl_p);	
				 Scrollt.setContent(content_p);
				 Scrollt.setFitToWidth(true);
				 asearch.setVisible(false);
			}
		 }
		 else
		 {
			 Alert alert=new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				String s = "Please Enter a correct Contact number!";
				alert.setContentText(s);
				alert.showAndWait();
		 }
		 
	}
	
	public void datasetter1(String id) throws SQLException
	{
		ResultSet rs2;
		Statement stmt2 = co.createStatement();;
		
		String query2 = "select name,currentdebt,maxdebt,id from customers where id='"+id+"';";
		 rs2 = stmt2.executeQuery(query2);
		 
		 while(rs2.next())
		 {
			name = rs2.getString(1);
			debt = rs2.getDouble(2);
			lim  = rs2.getDouble(3);
			acid = rs2.getString(4);
		 }
		 
		System.out.println(name);
		System.out.println(debt);
	}
	
	private AnchorPane item_ticket(String id,double total,String accid) {
		
		AnchorPane t = new AnchorPane();
		t.setPrefSize(300.0, 245.0);
		t.setStyle("-fx-background: #fff");
		
		Label id_l = new Label(id);
		id_l.setFont(new Font("Roboto Regular", 20));
		id_l.setLayoutX(36.0);
		id_l.setLayoutY(46.0);
		t.setLeftAnchor(id_l, 20.0);
		t.setTopAnchor(id_l, 40.0);
		
		Label id_rs = new Label("Current Debt:  Rs."+total);
		id_rs.setFont(new Font("Roboto Regular", 20));
		id_rs.setLayoutX(46.0);
		id_rs.setLayoutY(66.0);
		t.setLeftAnchor(id_rs, 20.0);
		t.setTopAnchor(id_rs, 70.0);
		
		Label cid = new Label();
		cid.setText("Account Number: "+ accid);
		cid.setStyle("-fx-text-fill: #9E9E9E;");
		t.setTopAnchor(cid, 110.0);
		t.setLeftAnchor(cid, 20.0);
		
		JFXButton salesreturn = new JFXButton("VIEW");
		salesreturn.setFont(new Font("Roboto Regular", 15));
		t.setLeftAnchor(salesreturn, 25.0);
		t.setBottomAnchor(salesreturn, 20.0);
		
		JFXButton refund = new JFXButton("EDIT");
		refund.setFont(new Font("Roboto Regular", 15));
		t.setLeftAnchor(refund, 105.0);
		t.setBottomAnchor(refund, 20.0);
		
		JFXButton print = new JFXButton("PAYMENTS");
		print.setFont(new Font("Roboto Regular", 15));
		t.setLeftAnchor(print, 170.0);
		t.setBottomAnchor(print, 20.0);
		t.setRightAnchor(print, 15.0);
		
		print.setOnAction(event->{
			System.out.println("Clear your debt baby");
			System.out.println(id);
			System.out.println(total);
			System.out.println(accid);	
			acid = accid;
			String query2 = "select id from customers where id='"+accid+"';";
			 Statement stmt1;
			try {
				stmt1 = co.createStatement();
				ResultSet rs2 = stmt1.executeQuery(query2);
				while(rs2.next()){
					datasetter1(rs2.getString(1));
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			AnchorPane ct = CustomerTicket.FullTicket(name,debt,lim,acid);
			
			ImageView img = (ImageView)ct.lookup("#cross");
			System.out.println(img);
			
			val = (JFXTextField)ct.lookup("#clearit");
			System.out.println(val);
			
			updateDebt = (Label)ct.lookup("#udebt");
			//System.out.println(val);
			
			img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent event) {
					// TODO Auto-generated method stub
					int c_p=0;
					double left_p=5.0;
					double top_p=5.0;
					int x = n;
					AnchorPane fnl_p = new AnchorPane();
					VBox content_p = new VBox(5); 
					int i=0;
					 
					Scrollt.setContent(content_p);
					Scrollt.setFitToWidth(true);
					System.out.println("here");
					
					try {
						rs1.beforeFirst();
						while(rs1.next())
						 {
							try {
								datasetter1(rs1.getString(1));
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							if(i==0){
								
								AnchorPane ap = item_ticket(name,debt,acid);
								ap.setLayoutX(left_p);
								ap.setLayoutY(top_p);
								ap.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 2, 0, 0, 1);" +
										"-fx-background-color: #fff;");
								fnl_p.getChildren().add(ap);
								c_p++;i++;
								}
								else
								{
									AnchorPane ap = item_ticket(name,debt,acid);
									//System.out.println(left_p + " "+ c_p);
									left_p = left_p+305;
									if(c_p>=x)
									{
										//System.out.println(" item & x "+c_p + " "+x);
										if(c_p % (x) ==0){
											left_p = 5;
											top_p = top_p +250;
											ap.setLayoutY(top_p);
										}
										else{
											ap.setLayoutY(top_p);
											left_p = left_p + 0;
										}
									}
									//
									ap.setLayoutX(left_p);
									ap.setLayoutY(top_p);
									ap.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 2, 0, 0, 1);" +
											"-fx-background-color: #fff;");
									fnl_p.getChildren().add(ap);
									c_p++;i++;
								}
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					content_p.getChildren().add(fnl_p);	
				}
				
			});
			
			Scrollt.setContent(ct);
		});
		
		t.getChildren().addAll(id_l,id_rs,cid,salesreturn,refund,print);
		
		return t;
	}

	public static void clearDebt(String aciid) {
		// TODO Auto-generated method stub
		System.out.println("Clear debt here");
		if(val.getText().length()<1)
		{
			Alert alert=new Alert(AlertType.WARNING);
			alert.setTitle("Customer Not Found!");
			String s="No Amount Entered!";
			alert.setContentText(s);
			alert.showAndWait();
		}
		else{
			double d = Double.parseDouble(val.getText());
			System.out.println("Into function");
			if(d>debt)
			{
				Alert alert=new Alert(AlertType.WARNING);
				alert.setTitle("Customer Not Found!");
				String s="Amount can't exceed current debt!";
				alert.setContentText(s);
				alert.showAndWait();	
			}
			else{
				System.out.println("Into function2");
				debt = debt - d;
				System.out.println(updateDebt);
				int r =	one.update_tbl("customers", "currentdebt", Double.toString(debt), "id", aciid);
				if(r==1)
				{
					Alert alert=new Alert(AlertType.WARNING);
					alert.setTitle("Sucess");
					String s="Debt Cleared!";
					alert.setContentText(s);
					alert.showAndWait();
					updateDebt.setText("Rs. "+Double.toString(debt));
					val.setText("");
				}
				else{
					Alert alert=new Alert(AlertType.WARNING);
					alert.setTitle("Failure!");
					String s="Query Failed!";
					alert.setContentText(s);
					alert.showAndWait();
				}
			}
		}
	}


	//effect function for hamburger
  	//effect function for hamburger
			public void effect(ImageView s, Pane t){
				j=false;
				k=false;
				l=false;
				o=false;
				if(main_Window.i%2==0){
	  				Translate tr_mbar_total = new Translate();
	  				mbar_total.getTransforms().add(tr_mbar_total);
	  				tr_mbar_total.setX(2000);
	  				main_Window.i+=1;
	  				System.out.println(sales.main_Window.i+" in effect main open");
	  				RotateTransition rt = new RotateTransition(Duration.millis(200),s);
	  				rt.setByAngle(180);
	  		        rt.setAutoReverse(true);
	  		        rt.play();
	  				Translate tr = new Translate();
	  				t.getTransforms().add(tr);
	  				tr.setX(310);
	  				tr.setY(0);
	  				Image img = new Image(getClass().getResource("/sales/back.png").toExternalForm());
	  				s.setImage(img);
	  				TranslateTransition tt = new TranslateTransition();
	  				tt.setNode(t);
	  				tt.fromXProperty().bind(t.translateXProperty().add(tr.getX()-620));
	  				tt.toXProperty().bind(t.translateXProperty().add(tr.getX()-310));
	  				tt.setDuration(new Duration(80));
	  				tt.play();
	  				TranslateTransition tmt = new TranslateTransition();
	  				tmt.setNode(mbar_total);
	  				tmt.fromXProperty().bind(mbar_total.translateXProperty().add(tr_mbar_total.getX()-4000));
	  				tmt.toXProperty().bind(mbar_total.translateXProperty().add(tr_mbar_total.getX()-2000));
	  				tmt.setDuration(new Duration(80));
	  				tmt.play();
	  			}
	  			else if(main_Window.i%2!=0){
	  				Translate tr_mbar_total = new Translate();
	  				mbar_total.getTransforms().add(tr_mbar_total);
	  				tr_mbar_total.setX(-2000);
	  				main_Window.i+=1;
	  				System.out.println(sales.main_Window.i+" in effect main close");
	  				RotateTransition rt = new RotateTransition(Duration.millis(200),s);
	  				rt.setByAngle(180);
	  		        rt.setAutoReverse(true);
	  		        rt.play();
	  				Translate tr = new Translate();
	  				t.getTransforms().add(tr);
	  				tr.setX(-310);
	  				tr.setY(0);
	  				Image img = new Image(getClass().getResource("/sales/ham1.png").toExternalForm());
	  				s.setImage(img);
	  				TranslateTransition tt = new TranslateTransition();
	  				tt.setNode(t);
	  				tt.fromXProperty().bind(t.translateXProperty().add(tr.getX()+620));
	  				tt.toXProperty().bind(t.translateXProperty().add(tr.getX()+310));
	  				tt.setDuration(new Duration(80));
	  				tt.play();
	  				TranslateTransition tmt = new TranslateTransition();
	  				tmt.setNode(mbar_total);
	  				tmt.fromXProperty().bind(mbar_total.translateXProperty().add(tr_mbar_total.getX()+4000));
	  				tmt.toXProperty().bind(mbar_total.translateXProperty().add(tr_mbar_total.getX()+2000));
	  				tmt.setDuration(new Duration(80));
	  				tmt.play();
	  			}
	  		}
	//submenu code for the main menu
	public void create_register_menu(boolean register_flag, boolean administration_flag, boolean utilities_flag, boolean system_flag, Map<String, String> data){
		if(register_flag){
			//i=0;
			HBox register_cat_pane = new HBox();
			register_cat_pane.setPrefWidth(300);
			register_cat_pane.setPrefHeight(60);
			register_cat_pane.setId("menu_cat");
			
			StackPane reg_img = new StackPane();
			StackPane reg_text = new StackPane();
			reg_img.setStyle("-fx-padding: 15 15 15 15;");
			reg_text.setStyle("-fx-padding: 5 5 5 0;");
			reg_img.setPrefWidth(60);
			reg_img.setPrefHeight(60);
			ImageView register_menu_image = new ImageView();
			Image img1 = new Image(getClass().getResource("/sales/register.png").toExternalForm(),30,30,false,false);
			Label reg_label = new Label("Register");
			reg_label.setFont(Font.font ("Noto", 17));
			register_menu_image.setImage(img1);
			reg_img.getChildren().add(register_menu_image);
			reg_text.getChildren().add(reg_label);
			register_cat_pane.getChildren().addAll(reg_img,reg_text);
			
			menu.getChildren().addAll(register_cat_pane);
			register_cat_pane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event){
					//System.out.println("tab clicked");
					if(j==false)
						j=true;
					else
						j=false;
					if(k==true&&l==false&&o==false)//and o is false
					{ k=false; j=true; o=false;}
					if(l==true&&k==false&&o==false)//and o is false
					{ l=false; j=true; o=false;}
					if(l==false&&k==false&&o==true)
					{ l=false; j=true; o=false;}
					//System.out.println("Inside j: "+j+k+l);
					if(j==true){
						menu.getChildren().clear();
						create_register_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						VBox reg_sub = new VBox();
						for (Map.Entry<String, String> entry : data.entrySet()) {
							if(entry.getValue().equals("true")){
								if(entry.getKey().equals("sale")||entry.getKey().equals("edit_sale")||entry.getKey().equals("customer_payment")||entry.getKey().equals("payments")||entry.getKey().equals("close_cash")){		
									Button reg1 = new Button();
									if(entry.getKey().equals("sale")){
										reg1.setText("Sale");
										reg1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
											public void handle(MouseEvent event){
												try{
													main_Window.page = FXMLLoader.load(main_Window.class.getResource("/sales/main_sales_test.fxml"));
													if(main_Window.primaryStage1.getScene().getRoot()!=main_Window.scene.getRoot()){
														sales.main_Window.scene=null;
														main_Window.scene = new Scene(main_Window.page,main_Window.primaryStage1.getWidth(),main_Window.primaryStage1.getHeight());
														sales.main_Window.primaryStage1.setScene(sales.main_Window.scene);
														sales.main_Window.scene.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
														sales.main_Window.scene.getStylesheets().add(getClass().getResource("/sales/mainsales.css").toExternalForm());
														sales.main_Window.scene.getStylesheets().add(getClass().getResource("/sales/calculator.css").toExternalForm());
														effect(hbg,mbar);
														System.out.println(sales.main_Window.i+" pay g!=0");
														sales.main_Controller.data_tbl.clear();
													}
												}
												catch(Exception e){
													System.out.println("hello");
												}
											}
											});
									}
									else if(entry.getKey().equals("edit_sale")){
										reg1.setText("Edit Sale");								
										reg1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
											public void handle(MouseEvent event){
												try {
													main_Window.page2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/register/edit_sales_test.fxml"));
												} catch (IOException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												try{
													if(main_Window.primaryStage1.getScene().getRoot()!=main_Window.scene2.getRoot()){
													main_Window.scene2.setRoot(main_Window.page2);
													main_Window.primaryStage1.setScene(main_Window.scene2);
													main_Window.scene2.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
													main_Window.scene2.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
													main_Window.primaryStage1.show();	
													effect(hbg,mbar);
													System.out.println(sales.main_Window.i+" g!=0");
												}
												}
													catch(Exception e){
														main_Window.scene2 = new Scene(main_Window.page2,main_Window.primaryStage1.getWidth(),main_Window.primaryStage1.getHeight());
														main_Window.primaryStage1.setScene(main_Window.scene2);
														main_Window.scene2.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
														main_Window.scene2.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
														main_Window.primaryStage1.show();
														effect(hbg,mbar);
														System.out.println(sales.main_Window.i+" g==0");
													}
											}
											});
									
									}
									else if(entry.getKey().equals("customer_payment")){
										reg1.setText("Customer Payment");
										reg1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
											public void handle(MouseEvent event){
												Alert alert1 = new Alert(AlertType.ERROR);
								    			alert1.setTitle("Error Dialog");
								    			alert1.setHeaderText("Customer Payment Error!!");
								    			alert1.setContentText("Already in Customer Payment");
								    			alert1.showAndWait();
											}
											});
									}else if(entry.getKey().equals("payments")){
										reg1.setText("Payments");
										reg1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
											public void handle(MouseEvent event){
												try{
													main_Window.page4 = FXMLLoader.load(getClass().getResource("/register/payments_register.fxml"));
												if(main_Window.primaryStage1.getScene().getRoot()!=main_Window.scene4.getRoot()){
													main_Window.scene4.setRoot(main_Window.page4);
													main_Window.primaryStage1.setScene(main_Window.scene4);
													main_Window.scene4.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
													main_Window.scene4.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
													main_Window.primaryStage1.show();	
													effect(hbg,mbar);
													System.out.println(sales.main_Window.i+" g!=0");
												}
												}
												catch(Exception e){
													main_Window.scene4 = new Scene(main_Window.page4,main_Window.primaryStage1.getWidth(),main_Window.primaryStage1.getHeight());
													main_Window.primaryStage1.setScene(main_Window.scene4);
													main_Window.scene4.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
													main_Window.scene4.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
													main_Window.primaryStage1.show();
													effect(hbg,mbar);
													System.out.println(sales.main_Window.i+" g==0");
												}
											}
											});
									}
									else if(entry.getKey().equals("close_cash")){
										reg1.setText("Close Cash");
										reg1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
											public void handle(MouseEvent event){
												try{
													main_Window.page5 = FXMLLoader.load(getClass().getResource("/register/close_cash.fxml"));
												if(main_Window.primaryStage1.getScene().getRoot()!=main_Window.scene5.getRoot()){
													main_Window.scene5.setRoot(main_Window.page5);
													main_Window.primaryStage1.setScene(main_Window.scene5);
													main_Window.scene5.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
													main_Window.scene5.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
													main_Window.primaryStage1.show();
													effect(hbg,mbar);
													System.out.println(sales.main_Window.i+" g!=0");
												}
												}
												catch(Exception e){	
													main_Window.scene5 = new Scene(main_Window.page5,main_Window.primaryStage1.getWidth(),main_Window.primaryStage1.getHeight());	
													main_Window.primaryStage1.setScene(main_Window.scene5);
													main_Window.scene5.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
													main_Window.scene5.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
													main_Window.primaryStage1.show();
													effect(hbg,mbar);
													System.out.println(sales.main_Window.i+" g==0");
												}
											}
											});
									}
									reg1.setPrefWidth(300);
									reg1.setPrefHeight(50);
									reg1.setAlignment(Pos.BASELINE_LEFT);
									reg1.setId("menu_subcat");
									//reg1.getChildren().add(new Label(entry.getKey()));
									reg_sub.getChildren().add(reg1);
								}
							}
						}
						menu.getChildren().add(reg_sub);
						//i=0;
						create_administration_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						create_utilities_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						create_system_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
					}
					//System.out.println(x);
					if(j==false){
						menu.getChildren().clear();
						create_register_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						create_administration_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						create_utilities_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						create_system_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						//System.out.println("Inside if");
					}
				}
			});
		}
		}
	
			public void create_administration_menu(boolean register_flag, boolean administration_flag, boolean utilities_flag, boolean system_flag, Map<String, String> data){
			if(administration_flag){
				//i=0;
				HBox administration_cat_pane = new HBox();
				administration_cat_pane.setPrefWidth(300);
				administration_cat_pane.setPrefHeight(60);
				administration_cat_pane.setId("menu_cat");
				
				StackPane ad_img = new StackPane();
				StackPane ad_text = new StackPane();
				ad_img.setStyle("-fx-padding: 15 15 15 15;");
				ad_text.setStyle("-fx-padding: 5 5 5 0;");
				ad_img.setPrefWidth(60);
				ad_img.setPrefHeight(60);
				ImageView ad_menu_image = new ImageView();
				Image img1 = new Image(getClass().getResource("/sales/administration.png").toExternalForm(),30,30,false,false);
				Label ad_label = new Label("Administration");
				ad_label.setFont(Font.font ("Noto", 17));
				ad_menu_image.setImage(img1);
				ad_img.getChildren().add(ad_menu_image);
				ad_text.getChildren().add(ad_label);
				administration_cat_pane.getChildren().addAll(ad_img,ad_text);
				
				menu.getChildren().addAll(administration_cat_pane);
				administration_cat_pane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event){
						//System.out.println("tab clicked");
						//System.out.println("Inside k: "+j+k+l);
						if(k==false)
							k=true;
						else
							k=false;
						if(j==true&&l==false&&o==false)//and o is false
						{ j=false; k=true; o=false;}
						if(l==true&&j==false&&o==false)//and o is false
						{ l=false; k=true; o=false;}
						if(l==false&&j==false&&o==true)
						{ l=false; k=true; o=false;}
						if(k==true){
							menu.getChildren().clear();
							create_register_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
							create_administration_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
							VBox ad_sub = new VBox();
							for (Map.Entry<String, String> entry : data.entrySet()) {
								if(entry.getValue().equals("true")){
									if(entry.getKey().equals("customers")||entry.getKey().equals("stock")||entry.getKey().equals("sales")||entry.getKey().equals("maintenance")||entry.getKey().equals("presence_management")){		
										Button ad1 = new Button();
										if(entry.getKey().equals("customers")){
											ad1.setText("Customers");
											ad1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
											public void handle(MouseEvent event){
												try{
													main_Window.page6 = (AnchorPane) FXMLLoader.load(getClass().getResource("/customers/C_main.fxml"));
													if(main_Window.primaryStage1.getScene().getRoot()!=main_Window.scene6.getRoot()){
													main_Window.scene6.setRoot(main_Window.page6);
													main_Window.primaryStage1.setScene(main_Window.scene6);
													main_Window.scene6.getStylesheets().add(getClass().getResource("/sales/application.css").toExternalForm());
													main_Window.scene6.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
													main_Window.scene6.getStylesheets().add(getClass().getResource("/sales/mainsales.css").toExternalForm());
													main_Window.primaryStage1.show();	
													effect(hbg,mbar);
													System.out.println(sales.main_Window.i+" g!=0");
												}
												}
													catch(Exception e){
														main_Window.scene6 = new Scene(main_Window.page6,main_Window.primaryStage1.getWidth(),main_Window.primaryStage1.getHeight());
														main_Window.primaryStage1.setScene(main_Window.scene6);
														main_Window.scene6.getStylesheets().add(getClass().getResource("/sales/application.css").toExternalForm());
														main_Window.scene6.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
														main_Window.scene6.getStylesheets().add(getClass().getResource("/sales/mainsales.css").toExternalForm());
														main_Window.primaryStage1.show();
														effect(hbg,mbar);
														System.out.println(sales.main_Window.i+" g==0");
													}
											}
											});
										}
										else if(entry.getKey().equals("stock")){
											ad1.setText("Stock");
											ad1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
												public void handle(MouseEvent event){
													try{
														main_Window.page7 = (AnchorPane) FXMLLoader.load(getClass().getResource("/sales/test.fxml"));
														if(main_Window.primaryStage1.getScene().getRoot()!=main_Window.scene7.getRoot()){
														main_Window.scene7.setRoot(main_Window.page7);
														main_Window.primaryStage1.setScene(main_Window.scene7);
														main_Window.scene7.getStylesheets().add(getClass().getResource("/sales/application.css").toExternalForm());
														main_Window.scene7.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
														main_Window.scene7.getStylesheets().add(getClass().getResource("/sales/mainsales.css").toExternalForm());
														main_Window.primaryStage1.show();	
														effect(hbg,mbar);
														System.out.println(sales.main_Window.i+" g!=0");
													}
													}
														catch(Exception e){
															main_Window.scene7 = new Scene(main_Window.page7,main_Window.primaryStage1.getWidth(),main_Window.primaryStage1.getHeight());
															main_Window.primaryStage1.setScene(main_Window.scene7);
															main_Window.scene7.getStylesheets().add(getClass().getResource("/sales/application.css").toExternalForm());
															main_Window.scene7.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
															main_Window.scene7.getStylesheets().add(getClass().getResource("/sales/mainsales.css").toExternalForm());
															main_Window.primaryStage1.show();
															effect(hbg,mbar);
															System.out.println(sales.main_Window.i+" g==0");
														}
												}
												});
										}
										else if(entry.getKey().equals("sales")){
											ad1.setText("Sales");{
												ad1.setText("Sales");
												ad1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
													public void handle(MouseEvent event){
														Alert alert1 = new Alert(AlertType.ERROR);
										    			alert1.setTitle("Error Dialog");
										    			alert1.setHeaderText("Work in Progress!!");
										    			alert1.setContentText("Contact Programmers for Details");
										    			alert1.showAndWait();
													}
													});
											}
											}
										else if(entry.getKey().equals("maintenance")){
											ad1.setText("Maintenance");{
												ad1.setText("Maintenance");
												ad1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
													public void handle(MouseEvent event){
														Alert alert1 = new Alert(AlertType.ERROR);
										    			alert1.setTitle("Error Dialog");
										    			alert1.setHeaderText("Work in Progress!!");
										    			alert1.setContentText("Contact Programmers for Details");
										    			alert1.showAndWait();
													}
													});
											}
											}
										else if(entry.getKey().equals("presence_management")){
											ad1.setText("Presence Management");
											ad1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
												public void handle(MouseEvent event){
													Alert alert1 = new Alert(AlertType.ERROR);
									    			alert1.setTitle("Error Dialog");
									    			alert1.setHeaderText("Work in Progress!!");
									    			alert1.setContentText("Contact Programmers for Details");
									    			alert1.showAndWait();
												}
												});
										}
										ad1.setPrefWidth(300);
										ad1.setPrefHeight(40);
										ad1.setAlignment(Pos.BASELINE_LEFT);	
										ad1.setId("menu_subcat");
										//reg1.getChildren().add(new Label(entry.getKey()));
										ad_sub.getChildren().add(ad1);
									}
								}
							}
							menu.getChildren().add(ad_sub);
							create_utilities_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
							create_system_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						}
						//System.out.println(x);
						if(k==false){
							menu.getChildren().clear();
							create_register_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
							create_administration_menu(register_flag, administration_flag, utilities_flag, system_flag,data);
							create_utilities_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
							create_system_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
							//System.out.println("Inside if");
						}
					}
				});
			}
			}
		

	public void create_utilities_menu(boolean register_flag, boolean administration_flag, boolean utilities_flag, boolean system_flag, Map<String, String> data){
		if(utilities_flag){
			//i=0;
			HBox utilities_cat_pane = new HBox();
			utilities_cat_pane.setPrefWidth(300);
			utilities_cat_pane.setPrefHeight(60);
			utilities_cat_pane.setId("menu_cat");
			
			StackPane ut_img = new StackPane();
			StackPane ut_text = new StackPane();
			ut_img.setStyle("-fx-padding: 15 15 15 15;");
			ut_text.setStyle("-fx-padding: 5 5 5 0;");
			ut_img.setPrefWidth(60);
			ut_img.setPrefHeight(60);
			ImageView ut_menu_image = new ImageView();
			Image img1 = new Image(getClass().getResource("/sales/utilities.png").toExternalForm(),30,30,false,false);
			Label ut_label = new Label("Utilities");
			ut_label.setFont(Font.font ("Noto", 17));
			ut_menu_image.setImage(img1);
			ut_img.getChildren().add(ut_menu_image);
			ut_text.getChildren().add(ut_label);
			utilities_cat_pane.getChildren().addAll(ut_img,ut_text);
			
			menu.getChildren().addAll(utilities_cat_pane);
			utilities_cat_pane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event){
					//System.out.println("tab clicked");
					if(l==false)
						l=true;
					else
						l=false;
					//System.out.println("Inside l: "+j+k+l);
					if(j==true&&k==false&&o==false)//and o is false
					{ j=false; l=true; o=false;}
					if(k==true&&j==false&&o==false)//and o is false
					{ k=false; l=true; o=false;}
					if(k==false&&j==false&&o==true)
					{ k=false; l=true; o=false;}
					if(l==true){
						menu.getChildren().clear();
						create_register_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						create_administration_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						create_utilities_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						VBox ut_sub = new VBox();
						for (Map.Entry<String, String> entry : data.entrySet()) {
							if(entry.getValue().equals("true")){
								if(entry.getKey().equals("tools")){		
									Button ut1 = new Button();
									if(entry.getKey().equals("tools")){
										ut1.setText("Tools");
										ut1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
											public void handle(MouseEvent event){
												Alert alert1 = new Alert(AlertType.ERROR);
								    			alert1.setTitle("Error Dialog");
								    			alert1.setHeaderText("Work in Progress!!");
								    			alert1.setContentText("Contact Programmers for Details");
								    			alert1.showAndWait();
											}
											});
									}
									ut1.setPrefWidth(300);
									ut1.setPrefHeight(40);
									ut1.setAlignment(Pos.BASELINE_LEFT);	
									ut1.setId("menu_subcat");
									//reg1.getChildren().add(new Label(entry.getKey()));
									ut_sub.getChildren().add(ut1);
								}
							}
						}
						menu.getChildren().add(ut_sub);
						create_system_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
					}
					//System.out.println(x);
					if(l==false){
						menu.getChildren().clear();
						create_register_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						create_administration_menu(register_flag, administration_flag, utilities_flag, system_flag,data);
						create_utilities_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						create_system_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						//System.out.println("Inside if");
					}
				}
			});
		}
		}
	
	public void create_system_menu(boolean register_flag, boolean administration_flag, boolean utilities_flag, boolean system_flag, Map<String, String> data){
		if(system_flag){
			HBox system_cat_pane = new HBox();
			system_cat_pane.setPrefWidth(300);
			system_cat_pane.setPrefHeight(60);
			system_cat_pane.setId("menu_cat");
			
			StackPane sys_img = new StackPane();
			StackPane sys_text = new StackPane();
			sys_img.setStyle("-fx-padding: 15 15 15 15;");
			sys_text.setStyle("-fx-padding: 5 5 5 0;");
			sys_img.setPrefWidth(60);
			sys_img.setPrefHeight(60);
			ImageView sys_menu_image = new ImageView();
			Image img1 = new Image(getClass().getResource("/sales/settings.png").toExternalForm(),30,30,false,false);
			Label sys_label = new Label("System");
			sys_label.setFont(Font.font ("Noto", 17));
			sys_menu_image.setImage(img1);
			sys_img.getChildren().add(sys_menu_image);
			sys_text.getChildren().add(sys_label);
			system_cat_pane.getChildren().addAll(sys_img,sys_text);
			
			menu.getChildren().addAll(system_cat_pane);
			system_cat_pane.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event){
					//System.out.println("tab clicked");
					if(o==false)
						o=true;
					else
						o=false;
					if(j==true&&k==false&&l==false)//and o is false
					{ j=false; o=true; l=false;}
					if(k==true&&j==false&&l==false)//and o is false
					{ k=false; o=true; j=false;}
					if(k==false&&j==false&&l==true)
					{ l=false; o=true; l=false;}
					if(o==true){
						menu.getChildren().clear();
						create_register_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						create_administration_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						create_utilities_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						create_system_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						VBox sys_sub = new VBox();
						for (Map.Entry<String, String> entry : data.entrySet()) {
							if(entry.getValue().equals("true")){
								if(entry.getKey().equals("change_password")||entry.getKey().equals("configuration")||entry.getKey().equals("printers")||entry.getKey().equals("check_in_out")||entry.getKey().equals("logout")){		
									Button sys1 = new Button();
									if(entry.getKey().equals("change_password")){
										sys1.setText("Change Password");
										sys1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
											public void handle(MouseEvent event){
												Alert alert1 = new Alert(AlertType.ERROR);
								    			alert1.setTitle("Error Dialog");
								    			alert1.setHeaderText("Work in Progress!!");
								    			alert1.setContentText("Contact Programmers for Details");
								    			alert1.showAndWait();
											}
											});
									}
									else if(entry.getKey().equals("configuration")){
										sys1.setText("Configuration");
										sys1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
											public void handle(MouseEvent event){
												Alert alert1 = new Alert(AlertType.ERROR);
								    			alert1.setTitle("Error Dialog");
								    			alert1.setHeaderText("Work in Progress!!");
								    			alert1.setContentText("Contact Programmers for Details");
								    			alert1.showAndWait();
											}
											});
									}
									else if(entry.getKey().equals("printers")){
										sys1.setText("Printers");
										sys1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
											public void handle(MouseEvent event){
												Alert alert1 = new Alert(AlertType.ERROR);
								    			alert1.setTitle("Error Dialog");
								    			alert1.setHeaderText("Work in Progress!!");
								    			alert1.setContentText("Contact Programmers for Details");
								    			alert1.showAndWait();
											}
											});
									}
									else if(entry.getKey().equals("check_in_out")){
										sys1.setText("Check In/Out");
										sys1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
											public void handle(MouseEvent event){
												Alert alert1 = new Alert(AlertType.ERROR);
								    			alert1.setTitle("Error Dialog");
								    			alert1.setHeaderText("Work in Progress!!");
								    			alert1.setContentText("Contact Programmers for Details");
								    			alert1.showAndWait();
											}
											});
									}
									else if(entry.getKey().equals("logout")){
										sys1.setText("LogOut");
										sys1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
											public void handle(MouseEvent event){
												Alert alert1 = new Alert(AlertType.ERROR);
								    			alert1.setTitle("Error Dialog");
								    			alert1.setHeaderText("Work in Progress!!");
								    			alert1.setContentText("Contact Programmers for Details");
								    			alert1.showAndWait();
											}
											});
									}
									sys1.setPrefWidth(300);
									sys1.setPrefHeight(40);
									sys1.setAlignment(Pos.BASELINE_LEFT);	
									sys1.setId("menu_subcat");
									//reg1.getChildren().add(new Label(entry.getKey()));
									sys_sub.getChildren().add(sys1);
								}
							}
						}
						menu.getChildren().add(sys_sub);
					}
					//System.out.println(x);
					if(o==false){
						menu.getChildren().clear();
						create_register_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						create_administration_menu(register_flag, administration_flag, utilities_flag, system_flag,data);
						create_utilities_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						create_system_menu(register_flag, administration_flag, utilities_flag, system_flag, data);
						//System.out.println("Inside if");
					}
				}
			});
		}
	}
}




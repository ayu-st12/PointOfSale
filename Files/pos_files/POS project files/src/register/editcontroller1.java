package register;

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

import sales.main5_xmlread;
import sales.main_Window;
import sales.main_Controller.SalesTable;

import com.jfoenix.controls.JFXButton;

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

public class editcontroller1 {
	
	private static Stage stag;
	
	static ImageView img_SR;
	static int size=0;
	static double calc=0;
	static AnchorPane a_SR;
	static AnchorPane ap_SR_COPY;
	static TextField back_copy;
	static ScrollPane b_SR;
	static ArrayList<SalesTable1> al_SR=new ArrayList<SalesTable1>();
	static ArrayList<SalesTable1> al_SR_C=new ArrayList<SalesTable1>();
	static ObservableList<SalesTable1> data_tbl_SR;
	static ObservableList<SalesTable1> data_tbl_SR_C;
	static String name_p_SR;
	static double price_SR;
	static double tax_val_SR;
	static String taxcat_SR;
	static double unit_SR=1;
	static double value_SR;
	static public TableView<SalesTable1> table_p_SR ;
	static public TableView<SalesTable1> table_p_SR_C ;
	static SalesTable1 s_ob1,s_ob2,s_ob2_C,s_ob3;
	static int i_tbl=-1;
	
	static Statement stmt;
	
	static String g_sid,g_base,g_ttax,g_s_rs;
	static TableView<SalesTable> t1,t2;
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
	static String tickid = null; 
	static Double amt = null; 
	static Timestamp dt = null;
	final BooleanProperty firstTime = new SimpleBooleanProperty(true);
	static ObservableList<SalesTable> data_tbl;
	static public TableView<SalesTable> table_p ;
	static ArrayList<SalesTable> al=new ArrayList<SalesTable>();
	
	dbfunc db = new dbfunc();
	//fullTicket ft = new fullTicket();
	
	static ScrollPane tick;
	static ResultSet rs1;
	static Connection co;
		
	@FXML
	AnchorPane mbar_rest;
	
	@FXML
	AnchorPane mbar_total;
	
	
	@FXML
	private TextField ticketfeild;
	
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
	
	public static Map<String, String> mdata = new HashMap<String, String>();
	static boolean j=false;
	static boolean k=false;
	static boolean l=false;
	static boolean o=false;
	@FXML
	VBox menu;
	@FXML
	private Pane mbar;
	static int is=1;
	@FXML
	private void initialize(){
			
		//hamburger code starts
		 //code for hamburger
		 Translate tr = new Translate();
			mbar.getTransforms().add(tr);
			tr.setX(-310);
		
		System.out.println(hbg);
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
		mdata = xml.return_func("eid"); //pass proper eid here from the login system.
		Map.Entry<String,String> ent = mdata.entrySet().iterator().next();
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
		Label name1 = new Label("Name");
		name1.setFont(Font.font ("Roboto", FontWeight.BOLD, 13));
		name1.setLayoutX(20);
		name1.setLayoutY(90);
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
		id_pane.getChildren().addAll(profimg, name1,type);
		
		for (Map.Entry<String, String> entry : mdata.entrySet()) {
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
		create_register_menu(register_flag, administration_flag, utilities_flag, system_flag, mdata);
		create_administration_menu(register_flag, administration_flag, utilities_flag, system_flag, mdata);
		create_utilities_menu(register_flag, administration_flag, utilities_flag, system_flag, mdata);
		create_system_menu(register_flag, administration_flag, utilities_flag, system_flag, mdata);
		
		//main menu code ends
		mbar.getChildren().addAll(id_pane);
		//main menu bar  code ends here
		
	
		//hamburger code ends
		
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
				//System.out.println("Listener: "+n);
		
				
				
		try {
										
			if(test!=0 && mask2!=1){
					
				rs1.beforeFirst();
				
				//System.out.println("Drawing");
				int c_p=0;
				double left_p=5.0;
				double top_p=5.0;
				int x = n;
				AnchorPane fnl_p = new AnchorPane();
				VBox content_p = new VBox(5); 
				int i=0;
				 
				Scrollt.setContent(content_p);
				Scrollt.setFitToWidth(true);
				//System.out.println("here");
				while(rs1.next())
				 {
					datasetter(rs1.getString(1));
					String dte = dt.toString();
					dte = dte.substring(0, 19);
					if(i==0){
						
						AnchorPane ap = item_ticket(tickid,amt,dte,name);
						ap.setLayoutX(left_p);
						ap.setLayoutY(top_p);
						ap.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 2, 0, 0, 1);" +
								"-fx-background-color: #fff;");
						fnl_p.getChildren().add(ap);
						c_p++;i++;
						}
						else
						{
							AnchorPane ap = item_ticket(tickid,amt,dte,name);
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
				//System.out.println("tid is null at Listener");
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
		
		ticketfeild.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
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
		ticketfeild.setText(null);
		asearch.setVisible(true);
	}
	
	public void closeAdvanced()
	{
		asearch.setVisible(false);
	}
	
	private AnchorPane item_ticket(String id,double total, String date, String role) {
		
		AnchorPane t = new AnchorPane();
		t.setPrefSize(300.0, 245.0);
		t.setStyle("-fx-background: #fff");
		
		Label id_l = new Label("Ticket ID: "+id);
		id_l.setId("tid");
		id_l.setFont(new Font("Roboto Regular", 20));
		id_l.setLayoutX(36.0);
		id_l.setLayoutY(46.0);
		t.setLeftAnchor(id_l, 20.0);
		t.setTopAnchor(id_l, 40.0);
		
		Label id_rs = new Label("Rs: "+total);
		id_rs.setId("trs");
		id_rs.setFont(new Font("Roboto Regular", 20));
		id_rs.setLayoutX(46.0);
		id_rs.setLayoutY(66.0);
		t.setLeftAnchor(id_rs, 20.0);
		t.setTopAnchor(id_rs, 70.0);
		
		JFXButton salesreturn = new JFXButton("Sales Return");
		salesreturn.setFont(new Font("Roboto Regular", 15));
		t.setLeftAnchor(salesreturn, 25.0);
		t.setBottomAnchor(salesreturn, 20.0);
		salesreturn.setOnAction(event->{
			try {
				
				//System.out.println("Ticket clicked");
				Label id_m =  (Label)t.lookup("#tid");
				//Label id_rs = (Label) t.lookup("#trs");
				Label id_dt = (Label) t.lookup("#tstamp");
				Label id_role = (Label) t.lookup("#role");
				String s_id = id_m.getText();
				String s_rs = id_rs.getText();
				String s_dt = id_dt.getText();
				String role1 = id_role.getText();
				//if(data_tbl_SR)
				if(data_tbl!=null)
					data_tbl.clear();
				g_s_rs = s_rs;
				s_id = s_id.substring(11);
				String customer = null;
				//System.out.println(s_id);
				g_sid = s_id;
				//System.out.println(s_rs);
				//System.out.println(s_dt);
				//System.out.println(role1);
				
				salesReturn();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		JFXButton refund = new JFXButton("Refund All");
		refund.setFont(new Font("Roboto Regular", 15));
		t.setLeftAnchor(refund, 135.0);
		t.setBottomAnchor(refund, 20.0);
		refund.setOnAction(event->{
			System.out.println("proceed to refund");
			try {
				completerefund();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		JFXButton print = new JFXButton("Print");
		print.setFont(new Font("Roboto Regular", 15));
		t.setLeftAnchor(print, 215.0);
		t.setBottomAnchor(print, 20.0);
		t.setRightAnchor(print, 15.0);

		
		Label m1 = new Label(date);
		m1.setId("tstamp");
		m1.setStyle("-fx-textFill: #948f8f");
		t.setLeftAnchor(m1, 25.0);
		t.setTopAnchor(m1, 110.0);
		
		Label m2 = new Label(role);
		m2.setId("role");
		m2.setStyle("-fx-textFill: #948f8f");
		t.setLeftAnchor(m2, 25.0);
		t.setTopAnchor(m2, 130.0);
		
		t.getChildren().addAll(id_l,id_rs,m1,m2,salesreturn,refund,print);
		
		t.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				
				 Connection co;
				 try {
				 co = db.connect();
				 stmt = co.createStatement();
				
				 if(data_tbl!=null)
					 data_tbl.clear();
				 
				// TODO Auto-generated method stub
				//System.out.println("Ticket clicked");
				Label id_m =  (Label)t.lookup("#tid");
				Label id_rs = (Label) t.lookup("#trs");
				Label id_dt = (Label) t.lookup("#tstamp");
				Label id_role = (Label) t.lookup("#role");
				String s_id = id_m.getText();
				String s_rs = id_rs.getText();
				String s_dt = id_dt.getText();
				String role = id_role.getText();
				
				g_s_rs = s_rs;
				s_id = s_id.substring(11);
				String customer1 = null;
				String customer = null;
				//System.out.println(s_id);
				g_sid = s_id;
				//System.out.println(s_rs);
				//System.out.println(s_dt);
				//System.out.println(role);
				
				
					ResultSet rs3; Statement stmt3 = co.createStatement();
					String query3 = "select costumer from tickets where ticketid='"+s_id+"';";
					 rs3 = stmt3.executeQuery(query3);
					
				 while(rs3.next())
				 {
					customer1 = rs3.getString(1);
				 }
				 
					ResultSet rs31; Statement stmt31 = co.createStatement();
					String query31 = "select name from customers where id='"+customer1+"';";
					 rs31 = stmt31.executeQuery(query31);


				 while(rs31.next())
				 {
					customer = rs31.getString(1);
				 }
				 
				 
				 ResultSet rs4; Statement stmt4 = co.createStatement();
					String query4 = "select base from taxline where receipt='"+s_id+"';";
					 rs4 = stmt4.executeQuery(query4);
					 double base1 = 0 ;
					 
					 //System.out.println(s_id);
					 
					 while(rs4.next())
					 {
						base1 = rs4.getDouble(1);
					 }
					 String base = Double.toString(base1);
					 g_base = base;
					 
					 ResultSet rs5; Statement stmt5 = co.createStatement(); String taxid = null;
						String query5 = "select taxid from taxline where receipt='"+s_id+"';";
						 rs5 = stmt5.executeQuery(query5);
						 double tax1 = 0 ;
						 
						 while(rs5.next())
						 {
							taxid = rs5.getString(1);
						 }
						 
						 ResultSet rs6; Statement stmt6 = co.createStatement(); 
							String query6 = "select rate from taxes where id='"+taxid+"';";
							 rs6 = stmt6.executeQuery(query6);
							 
							 while(rs6.next())
							 {
								tax1 = rs6.getDouble(1);
							 }	 
						 
						 
						 Double ttax1 = (base1*tax1)/100;
						 String ttax = Double.toString(ttax1);	 
						 g_ttax = ttax;
				//data_tbl = addData(name_p,price,unit,tax_val,value);
				
						 ResultSet rs7; Statement stmt7 = co.createStatement(); 
							String query7 = "select product,unit,price,taxid from ticketline where ticket='"+s_id+"';";
							 rs7 = stmt7.executeQuery(query7);
							 
							 
								
								 while(rs7.next())
								 {
									
									 name_p = rs7.getString(1);
									 price = rs7.getDouble(3);
									 unit = rs7.getInt(2);
									 
									 String tid = rs7.getString(4);

									 double taxval = 0;
									 ResultSet rs8; Statement stmt8 = co.createStatement(); 
										String query8 = "select rate from taxes where name='"+tid+"';";
										 rs8 = stmt8.executeQuery(query8);
										 
										 while(rs8.next())
										 {
											taxval = rs8.getDouble(1);
										 }	 
										 //System.out.println("TAX VAL IS:"+taxval);
									 tax_val = taxval;								 
									 
									 double tmp = unit*price;
									 double tax = (tmp*tax_val)/100;
									 value = tmp+tax;
									 //System.out.println("Name : "+name_p);
									 //System.out.println("Name : "+unit);
									 ////System.out.println("Name : "+price);
									 //System.out.println("Name : "+tax_val);
									 //System.out.println("Name : "+value);
									//tax1 = rs7.getDouble(1);
									 updateTable();
								 }	 
								 
							 
							 
						 
								 
					t1 = item(data_tbl);
				
				VBox content_p = new VBox(5);
				AnchorPane ap = fullTicket.FullTicket(s_id,"Administrator",base,ttax,s_rs,s_dt,"1-A",customer,"Credit",role);

				b = (ScrollPane)ap.lookup("#tbl_center_edit");
				
			
				ImageView img = (ImageView)ap.lookup("#cross");
				//System.out.println(fullTicket.b);
				
				img.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

					@Override
					public void handle(MouseEvent event) {
						// TODO Auto-generated method stub
						
						//System.out.println("here");
						ticketfeild.setText(null); 
						Scrollt.setContent(null);
						mask2 =0;
						
						data_tbl.clear();
						
						//System.out.println(b);
						//AnchorPane p = (AnchorPane)b.lookup(selector)
						//b.setContent(null);
						//data_tbl = null;
						//data_tbl.remove(t1.getSelectionModel().getSelectedItems().addAll(0,data_tbl));
						//t1.getSelectionModel().clearSelection();
						//t1.
						//t1 = null;
						
						try {
							rs1.beforeFirst();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						//System.out.println("Drawing");
						int c_p=0;
						double left_p=5.0;
						double top_p=5.0;
						int x = n;
						AnchorPane fnl_p = new AnchorPane();
						VBox content_p = new VBox(5); 
						int i=0;
						 
						Scrollt.setContent(content_p);
						Scrollt.setFitToWidth(true);
						//System.out.println("here");
						try {
							while(rs1.next())
							 {
								try {
									datasetter(rs1.getString(1));
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								String dte = dt.toString();
								dte = dte.substring(0, 19);
								if(i==0){
									
									AnchorPane ap = item_ticket(tickid,amt,dte,name);
									ap.setLayoutX(left_p);
									ap.setLayoutY(top_p);
									ap.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 2, 0, 0, 1);" +
											"-fx-background-color: #fff;");
									fnl_p.getChildren().add(ap);
									c_p++;i++;
									}
									else
									{
										AnchorPane ap = item_ticket(tickid,amt,dte,name);
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
					}) ;
				
				mask2 = 1;
				b.setContent(t1);
				b.setFitToHeight(true);
				b.setFitToWidth(true);
				
				content_p.getChildren().add(ap);
				Scrollt.setContent(content_p);
				Scrollt.setFitToWidth(true);
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 
				 
			}
			}) ;
		
		return t;
	}
	
	public void updateTable()
	{
		data_tbl = addData(name_p,price,unit,tax_val,value);
	}
	
	public TableView<SalesTable> item(ObservableList<SalesTable> data)
	   {
	   	 TableView<SalesTable> table  = new TableView<SalesTable>();
	   	 table_p = table;
	table.setItems(data);

 TableColumn<SalesTable, String> itemCol = new TableColumn<SalesTable, String>("Item");
 itemCol.setMinWidth(115);
 itemCol.setCellValueFactory(
         new PropertyValueFactory<>("itemName"));
 
 TableColumn<SalesTable, Double> priceCol = new TableColumn<SalesTable, Double>("Price");
 priceCol.setMinWidth(80);
 priceCol.setCellValueFactory(
         new PropertyValueFactory<>("itemPrice"));

 TableColumn<SalesTable, Double> unitsCol = new TableColumn<SalesTable, Double>("Units");
 unitsCol.setMinWidth(58);
 unitsCol.setCellValueFactory(
         new PropertyValueFactory<>("itemUnits"));
 
 TableColumn<SalesTable, Double> taxesCol = new TableColumn<SalesTable, Double>("Taxes");
 taxesCol.setMinWidth(85);
 taxesCol.setCellValueFactory(
         new PropertyValueFactory<>("itemTaxes"));

 TableColumn<SalesTable, Double> valueCol = new TableColumn<SalesTable, Double>("Value");
 valueCol.setMinWidth(80);
 valueCol.setCellValueFactory(
         new PropertyValueFactory<>("itemValue"));
 

 table.getColumns().addAll(itemCol, priceCol, unitsCol, taxesCol, valueCol);	     
 	
 
 return table;
	    }
	
	public void datasetter(String tic) throws SQLException
	{
		ResultSet rs2,rs3,rs4,rs10,rs11;
		
		 Statement stmt2 = co.createStatement();
		 Statement stmt3 = co.createStatement();
		 Statement stmt4 = co.createStatement();

		 Statement stmt10 = co.createStatement();
		 Statement stmt11 = co.createStatement();
		 
		 tickid = tic;
		/*
		System.out.println("Data present");
		 while(rs.next())
		 {
			tickid = rs.getString(1);
		 }
		 */
		 String query2 = "select amount from taxline where receipt='"+tic+"';";
		 rs2 = stmt2.executeQuery(query2);
		 
		 while(rs2.next())
		 {
			amt = rs2.getDouble(1);
		 }
		 Double d = amt;
		 g_s_rs = Double.toString(d);
		 //System.out.println("NEED TO ADD:::::******"+d+"And Getting"+g_s_rs);
		 
		 String query10 = "select base from taxline where receipt='"+tic+"';";
		 rs10 = stmt10.executeQuery(query10); Double base = null,b;
		 
		 while(rs10.next())
		 {
			base = rs10.getDouble(1);
		 }
		 b = base;
		 g_base = Double.toString(b);
		
		 ResultSet rs5; Statement stmt5 = co.createStatement(); String taxid = null;
			String query5 = "select taxid from taxline where receipt='"+tic+"';";
			 rs5 = stmt5.executeQuery(query5);
			 double tax1 = 0 ;
			 
			 while(rs5.next())
			 {
				taxid = rs5.getString(1);
			 }
			 
			 ResultSet rs6; Statement stmt6 = co.createStatement(); 
				String query6 = "select rate from taxes where id='"+taxid+"';";
				 rs6 = stmt6.executeQuery(query6);
				 
				 while(rs6.next())
				 {
					tax1 = rs6.getDouble(1);
				 }	 
			 
			 
			 Double ttax1 = (base*tax1)/100;
			 String ttax = Double.toString(ttax1);	 
			 g_ttax = ttax;
		
		 
		 String query3 = "select datetime from receipts where ticketid='"+tic+"';";
		 rs3 = stmt3.executeQuery(query3);
		 
		 while(rs3.next())
		 {
			dt = rs3.getTimestamp(1);
		 }
		 
		 String query4 = "select person from tickets where ticketid='"+tic+"';";
		 rs4 = stmt4.executeQuery(query4);
		 
		 while(rs4.next())
		 {
			name = rs4.getString(1);
		 }
		 
		 //System.out.println("Found ID is: "+tic);
		 //System.out.println("Found user is: "+name);
		 ///System.out.println("Found Amount is: "+amt);
		 //System.out.println("Found TimeStamp is: "+dt);
		
	}
	

	
	public ObservableList<SalesTable> addData(String n,double p,double u,double t,double v)
	{
		 
		   
		   ObservableList<SalesTable> data;
		   
	    	al.add(new SalesTable(n, p, u, t, v));
		   	
	        data=FXCollections.observableList(al);
			return data;
	}
	
	public void searchTicket() throws SQLException
	{
		 
		 Statement stmt1 = co.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		    int c_p=0;
			double left_p=5.0;
			double top_p=5.0;
			int x = n;
			AnchorPane fnl_p = new AnchorPane();
			VBox content_p = new VBox(5); 
			
			if(task2==2)
			{
				data_tbl.clear();
			}		 
			
			if(task1==2)
			{
				data_tbl_SR.clear();
			}
			task1 =0;
			
		if(ticketfeild.getText()!=null){
			tid = ticketfeild.getText(); 
			test = 1;
			
			 String query1 = "select ticketid from receipts where ticketid='"+tid+"';";
			 rs1 = stmt1.executeQuery(query1);
			 

			 if (!rs1.isBeforeFirst() ) {    
				 //System.out.println("No data");
				 Alert alert=new Alert(AlertType.WARNING);
					alert.setTitle("Warning");
					String s="No Ticket found for ID: "+ticketfeild.getText();
					alert.setContentText(s);
					alert.showAndWait();
				 asearch.setVisible(false);	
				}
			 else{
				 while(rs1.next())
				 {
					 datasetter(rs1.getString(1));
					 tid = tickid; g_sid=tickid;
					 n = (int)Math.floor(width/300);
						//System.out.println("Here n = "+n);
						
						Scrollt.setContent(content_p);
						Scrollt.setFitToWidth(true);
						
						String dte = dt.toString();
						 dte = dte.substring(0, 19);
						 
								AnchorPane ap = item_ticket(tickid,amt,dte,name);
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
		else if(adv_start.getValue()!=null)
		{
			int mark=0; 
			LocalDate sdate = adv_start.getValue();			
			LocalDate edate = adv_end.getValue();
			
			if(adv_end.getValue()==null)
			{
				//get all tickets from start till then.
			}
			else{
				if(sdate.compareTo(edate)<0){
					mark = 1;
				}
				
				if(mark == 1){
					
					test = 1;
					
					ResultSet rs2;
					rows = 0;
					//System.out.println(sdate);
					//System.out.println(edate);
					
					//System.out.println("Proceed to search Query");
					
					String query1 = "select ticketid from receipts where datetime between'"+sdate+"' AND '"+edate+"';";
					//Add query to generate tickets
					rs1 = stmt1.executeQuery(query1);
					
					//rs2 = rs1;
					 
					 while(rs1.next())
					 {
						 rows++;
						 //System.out.println(rs1.getString(1));
						 
						 datasetter(rs1.getString(1));
							// System.out.println(rs2.getString(1));
							//System.out.println("Function paremeter TicketID: "+ tickid );

							 String dte = dt.toString();
								dte = dte.substring(0, 19);
								if(rs1.isFirst()){
									
									AnchorPane ap = item_ticket(tickid,amt,dte,name);
									ap.setLayoutX(left_p);
									ap.setLayoutY(top_p);
									ap.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 2, 0, 0, 1);" +
											"-fx-background-color: #fff;");
									fnl_p.getChildren().add(ap);
									c_p++;
									}
									else
									{
										AnchorPane ap = item_ticket(tickid,amt,dte,name);
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
						 
					 }
					 
					 content_p.getChildren().add(fnl_p);	
					 Scrollt.setContent(content_p);
    				 Scrollt.setFitToWidth(true);
    				 asearch.setVisible(false);
				}
				else
				{
					System.out.println("Enter Dates Correctly");
				}	
			}
		}
		else
		{
			System.out.println("No search conditions!");
		}
	}
	
	public static class SalesTable {
		 
        private final SimpleStringProperty itemName;
        private final SimpleDoubleProperty itemPrice;
        private final SimpleDoubleProperty itemUnits;
        private final SimpleDoubleProperty itemTaxes;
        private final SimpleDoubleProperty itemValue;
 
        SalesTable(String iName, Double iPrice, Double iUnits, Double iTaxes, Double iValue) {
            this.itemName = new SimpleStringProperty(iName);
            this.itemPrice = new SimpleDoubleProperty(iPrice);
            this.itemUnits = new SimpleDoubleProperty(iUnits);
            this.itemTaxes = new SimpleDoubleProperty(iTaxes);
            this.itemValue = new SimpleDoubleProperty(iValue);
        }
 
        //GETTER AND SETTER METHODS FOR ITEM
        public String getItemName() {
            return itemName.get();
        }
 
        public void setItemName(String iName) {
            itemName.set(iName);
        }
 
      //GETTER AND SETTER METHODS FOR PRICE
        public Double getItemPrice() {
            return itemPrice.get();
        }
 
        public void setItemPrice(Double iPrice) {
            itemPrice.set(iPrice);
        }
        
      //GETTER AND SETTER METHODS FOR UNITS
        public Double getItemUnits() {
            return itemUnits.get();
        }
 
        public void setItemUnits(Double iUnits) {
             itemUnits.set(iUnits);
        }
        
      //GETTER AND SETTER METHODS FOR TAXES
        public Double getItemTaxes() {
            return itemTaxes.get();
        }
 
        public void setItemTaxes(Double iTaxes) {
             itemTaxes.set(iTaxes);
        }
        
      //GETTER AND SETTER METHODS FOR VALUE
        public Double getItemValue() {
            return itemValue.get();
        }
 
        public void setItemValue(Double iValue) {
             itemValue.set(iValue);
        }
    }

	public void salesReturn() throws Exception {
		// TODO Auto-generated method stub
		
		stag = new Stage();
	
		stag.setTitle("Sales Return");
		task1 = 2;
		
		if(data_tbl_SR!=null){
			data_tbl_SR.clear();
		}
		//System.out.println("g_sid"+g_sid);
		//System.out.println("g_base"+g_base);
		//System.out.println("g_ttax"+g_ttax);
		//System.out.println("g_s_rs"+g_s_rs);
		
		AnchorPane ap = salesreturn.FullTicket1(g_sid,"Administrator",g_base,g_ttax,g_s_rs);
		
		Scene s = new Scene(ap);
		
		AnchorPane ap_SR = (AnchorPane) s.lookup("#fullticekt_SR");
		//System.out.println(ap_SR); 
		ap_SR_COPY = ap_SR;
		
		img_SR = (ImageView) ap_SR.lookup("#cross_SR");
		//System.out.println(img_SR);
		
		TextField back = (TextField) ap_SR.lookup("#retur");
		//System.out.println(img_SR);
		back_copy = back;
		ScrollPane sp_SR = (ScrollPane) ap_SR.lookup("#tbl_center_edit_SR");
		//System.out.println(sp_SR);
		
		Label pri = (Label) ap_SR.lookup("#price_SR");
		Label ta = (Label) ap_SR.lookup("#tax_SR");
		Label tot = (Label) ap_SR.lookup("#total_SR");

		System.out.println("Sales return button clicked for: "+g_sid);
		
		ResultSet rs7; Statement stmt7 = co.createStatement(); 
		String query7 = "select product,unit,price,taxid from ticketline where ticket='"+g_sid+"';";
		 rs7 = stmt7.executeQuery(query7);
		 
		 
			
			 while(rs7.next())
			 {
				
				 name_p_SR = rs7.getString(1);
				 price_SR = rs7.getDouble(3);
				 unit_SR = rs7.getInt(2);
				 
				 String tid = rs7.getString(4);

				 double taxval = 0;
				 ResultSet rs8; Statement stmt8 = co.createStatement(); 
					String query8 = "select rate from taxes where name='"+tid+"';";
					 rs8 = stmt8.executeQuery(query8);
					 
					 while(rs8.next())
					 {
						taxval = rs8.getDouble(1);
					 }	 
					 //System.out.println("TAX VAL IS:"+taxval);
				 tax_val_SR = taxval;								 
				 
				 double tmp = unit_SR*price_SR;
				 double tax = (tmp*tax_val_SR)/100;
				 value_SR = tmp+tax;
				 //System.out.println("Name : "+name_p_SR);
				 //System.out.println("Name : "+unit_SR);
				 //System.out.println("Name : "+price_SR);
				 //System.out.println("Name : "+tax_val_SR);
				 //System.out.println("Name : "+value_SR);
				//tax1 = rs7.getDouble(1);
				 updateTable1();
			 }
		
		
		TableView<SalesTable1> t2 = item1(data_tbl_SR);
		
		//VBox content_p = new VBox(5);
		sp_SR.setContent(t2);
		sp_SR.setFitToHeight(true);
		sp_SR.setFitToWidth(true);
		
		JFXButton del = (JFXButton) ap_SR.lookup("#delete");
		//System.out.println(del);
		
		del.setOnMouseClicked(event ->{
			
			int c1 = 0;int c2 =0;
			
			if(i_tbl== -1)
			{
				System.out.println("Select some item");
			}else{
				System.out.println(i_tbl);
				s_ob3=table_p_SR.getItems().get(i_tbl);
				
				System.out.println("Id is: "+g_sid);
				
				ResultSet rs32; Statement stmt32;
				
				try {
					stmt32 = co.createStatement();
					String query32 = "select newticket from salesreturn where oldticket='"+g_sid+"'";
					rs32 = stmt32.executeQuery(query32);
					System.out.println("here");
					String newid = null;
					
					if(!rs32.isBeforeFirst()){
						System.out.println("No record for this");
						System.out.println("Final: Lets delete this");
						
						updateTable2(s_ob3.getItemName(),s_ob3.getItemPrice(),s_ob3.getItemUnits(),s_ob3.getItemTaxes(),s_ob3.getItemValue());
						TableView<SalesTable1> t3 = item2(data_tbl_SR_C);
						double v = s_ob3.getItemValue();
						calc = calc + v;
						data_tbl_SR.remove(i_tbl);
					    table_p_SR.getColumns().get(0).setVisible(false);
					    table_p_SR.getColumns().get(0).setVisible(true);	
					    
					    size = table_p_SR.getItems().size();
					    //System.out.println("Size of table :"+size);
					    
					    double p=0,p1=0,p_sum=0,u=0,t=0,t1=0,t_sum=0;
					    
					    for(int i=0;i<size;i++)
					    {
					    	s_ob2=table_p_SR.getItems().get(i);
					    	
					    	p=s_ob2.getItemPrice();						
							
							u=s_ob2.getItemUnits();	
							p1=p*u;										p_sum+=p1;
							t=(p* s_ob2.getItemTaxes() )/100;				t1=t*u;
							t_sum+=t1;
							
					    }
					    double done = p_sum + t_sum;
			
					    pri.setText("Rs. "+Double.toString(p_sum));
						ta.setText("Rs. "+Double.toString(t_sum));
						tot.setText("Rs. "+Double.toString(done));
						//System.out.println(back_copy);
						back_copy.setText(Double.toString(calc));
					}else{
						while(rs32.next()){
							newid = rs32.getString(1);
							System.out.println(newid);
							System.out.println("Check for:"+ s_ob3.getItemName());
							
							Statement stmt33 = co.createStatement();
							String query33 = "select product from returnrable where product='"+s_ob3.getItemName()+"' and id='"+newid+"';";
							ResultSet rs33 = stmt33.executeQuery(query33);
							if(!rs33.isBeforeFirst()){
								System.out.println("Not Deleted for id:"+newid);
							}
							else{
								System.out.println("Deleted for id:"+newid);
								c2 = 3;break;
							}
							
						}
						if(c2==3)
						{
							Alert alert=new Alert(AlertType.WARNING);
							alert.setTitle("Warning");
							String s1="This Product is already refunded!";
							alert.setContentText(s1);
							alert.showAndWait();
						
						}
						else if(c1 == 0 && c2 ==0){
							System.out.println("Final: Not Deleted yet");
							
							updateTable2(s_ob3.getItemName(),s_ob3.getItemPrice(),s_ob3.getItemUnits(),s_ob3.getItemTaxes(),s_ob3.getItemValue());
							TableView<SalesTable1> t3 = item2(data_tbl_SR_C);
							double v = s_ob3.getItemValue();
							calc = calc + v;
							data_tbl_SR.remove(i_tbl);
						    table_p_SR.getColumns().get(0).setVisible(false);
						    table_p_SR.getColumns().get(0).setVisible(true);	
						    
						    size = table_p_SR.getItems().size();
						    //System.out.println("Size of table :"+size);
						    
						    double p=0,p1=0,p_sum=0,u=0,t=0,t1=0,t_sum=0;
						    
						    for(int i=0;i<size;i++)
						    {
						    	s_ob2=table_p_SR.getItems().get(i);
						    	
						    	p=s_ob2.getItemPrice();						
								
								u=s_ob2.getItemUnits();	
								p1=p*u;										p_sum+=p1;
								t=(p* s_ob2.getItemTaxes() )/100;				t1=t*u;
								t_sum+=t1;
								
						    }
						    double done = p_sum + t_sum;
				
						    pri.setText("Rs. "+Double.toString(p_sum));
							ta.setText("Rs. "+Double.toString(t_sum));
							tot.setText("Rs. "+Double.toString(done));
							//System.out.println(back_copy);
							back_copy.setText(Double.toString(calc));
						}
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		img_SR.setOnMouseClicked(event->{
			data_tbl_SR.clear();
			stag.close();
		});
		
		calc=0;
		
		s.getStylesheets().add(getClass().getResource("edit_sales.css").toExternalForm());
		stag.setScene(s);
		stag.show();
	}
	
	public ObservableList<SalesTable1> addData1(String n,double p,double u,double t,double v)
	{
		 
		   
		   ObservableList<SalesTable1> data;
		   
	    	al_SR.add(new SalesTable1(n, p, u, t, v));
		   	
	        data=FXCollections.observableList(al_SR);
			return data;
	}
	
	public ObservableList<SalesTable1> addData2(String n,double p,double u,double t,double v)
	{
		 
		   
		   ObservableList<SalesTable1> data;
		   
	    	al_SR_C.add(new SalesTable1(n, p, u, t, v));
		   	
	        data=FXCollections.observableList(al_SR_C);
			return data;
	}
	
	public void updateTable2(String name,double price,double unit,double tax,double val)
	{
		data_tbl_SR_C = addData2(name,price,unit,tax,val);
	}
	
	public void updateTable1()
	{
		task1 = 1;
		data_tbl_SR = addData1(name_p_SR,price_SR,unit_SR,tax_val_SR,value_SR);
	}
	
	public static TableView<SalesTable1> item2(ObservableList<SalesTable1> data)
	   {
	   	 TableView<SalesTable1> table  = new TableView<SalesTable1>();
	   	 table_p_SR_C = table;
	table.setItems(data);

TableColumn<SalesTable1, String> itemCol = new TableColumn<SalesTable1, String>("Item");
itemCol.setMinWidth(115);
itemCol.setCellValueFactory(
      new PropertyValueFactory<>("itemName"));

TableColumn<SalesTable1, Double> priceCol = new TableColumn<SalesTable1, Double>("Price");
priceCol.setMinWidth(80);
priceCol.setCellValueFactory(
      new PropertyValueFactory<>("itemPrice"));

TableColumn<SalesTable1, Double> unitsCol = new TableColumn<SalesTable1, Double>("Units");
unitsCol.setMinWidth(58);
unitsCol.setCellValueFactory(
      new PropertyValueFactory<>("itemUnits"));

TableColumn<SalesTable1, Double> taxesCol = new TableColumn<SalesTable1, Double>("Taxes");
taxesCol.setMinWidth(85);
taxesCol.setCellValueFactory(
      new PropertyValueFactory<>("itemTaxes"));

TableColumn<SalesTable1, Double> valueCol = new TableColumn<SalesTable1, Double>("Value");
valueCol.setMinWidth(80);
valueCol.setCellValueFactory(
      new PropertyValueFactory<>("itemValue"));


table.getColumns().addAll(itemCol, priceCol, unitsCol, taxesCol, valueCol);	     


return table;
	    }
	
	public static TableView<SalesTable1> item1(ObservableList<SalesTable1> data)
	   {
	   	 TableView<SalesTable1> table  = new TableView<SalesTable1>();
	   	 table_p_SR = table;
	table.setItems(data);

 TableColumn<SalesTable1, String> itemCol = new TableColumn<SalesTable1, String>("Item");
 itemCol.setMinWidth(115);
 itemCol.setCellValueFactory(
         new PropertyValueFactory<>("itemName"));
 
 TableColumn<SalesTable1, Double> priceCol = new TableColumn<SalesTable1, Double>("Price");
 priceCol.setMinWidth(80);
 priceCol.setCellValueFactory(
         new PropertyValueFactory<>("itemPrice"));

 TableColumn<SalesTable1, Double> unitsCol = new TableColumn<SalesTable1, Double>("Units");
 unitsCol.setMinWidth(58);
 unitsCol.setCellValueFactory(
         new PropertyValueFactory<>("itemUnits"));
 
 TableColumn<SalesTable1, Double> taxesCol = new TableColumn<SalesTable1, Double>("Taxes");
 taxesCol.setMinWidth(85);
 taxesCol.setCellValueFactory(
         new PropertyValueFactory<>("itemTaxes"));

 TableColumn<SalesTable1, Double> valueCol = new TableColumn<SalesTable1, Double>("Value");
 valueCol.setMinWidth(80);
 valueCol.setCellValueFactory(
         new PropertyValueFactory<>("itemValue"));
 

 table.getColumns().addAll(itemCol, priceCol, unitsCol, taxesCol, valueCol);	     
 	
/* 
     table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

         @Override
         public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
             SalesTable1 selectedPerson = (SalesTable1) newValue;
            

             //System.out.println("OK");
             //System.out.println("INSIDE TABLE VIEW");
         }
     });
  */   
     table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
         //Check whether item is selected and set value of selected item to Label
         if (table.getSelectionModel().getSelectedItem() != null) {
         	
            //System.out.println("Sonething can be done");
            s_ob1 = table.getSelectionModel().getSelectedItem();
            s_ob2 = s_ob1;
            i_tbl =data_tbl_SR.indexOf(s_ob1);
            //i_tbl = -1;
            System.out.println("Index is :" + i_tbl);
            //i_tbl = -1;
            //System.out.println(s_ob1.getItemName());
            
         }
 });
 
 return table;
	    }

	public class SalesTable1 {
		 
	    private final SimpleStringProperty itemName;
	    private final SimpleDoubleProperty itemPrice;
	    private final SimpleDoubleProperty itemUnits;
	    private final SimpleDoubleProperty itemTaxes;
	    private final SimpleDoubleProperty itemValue;

	    SalesTable1(String iName, Double iPrice, Double iUnits, Double iTaxes, Double iValue) {
	        this.itemName = new SimpleStringProperty(iName);
	        this.itemPrice = new SimpleDoubleProperty(iPrice);
	        this.itemUnits = new SimpleDoubleProperty(iUnits);
	        this.itemTaxes = new SimpleDoubleProperty(iTaxes);
	        this.itemValue = new SimpleDoubleProperty(iValue);
	    }

	    //GETTER AND SETTER METHODS FOR ITEM
	    public String getItemName() {
	        return itemName.get();
	    }

	    public void setItemName(String iName) {
	        itemName.set(iName);
	    }

	  //GETTER AND SETTER METHODS FOR PRICE
	    public Double getItemPrice() {
	        return itemPrice.get();
	    }

	    public void setItemPrice(Double iPrice) {
	        itemPrice.set(iPrice);
	    }
	    
	  //GETTER AND SETTER METHODS FOR UNITS
	    public Double getItemUnits() {
	        return itemUnits.get();
	    }

	    public void setItemUnits(Double iUnits) {
	         itemUnits.set(iUnits);
	    }
	    
	  //GETTER AND SETTER METHODS FOR TAXES
	    public Double getItemTaxes() {
	        return itemTaxes.get();
	    }

	    public void setItemTaxes(Double iTaxes) {
	         itemTaxes.set(iTaxes);
	    }
	    
	  //GETTER AND SETTER METHODS FOR VALUE
	    public Double getItemValue() {
	        return itemValue.get();
	    }

	    public void setItemValue(Double iValue) {
	         itemValue.set(iValue);
	    }
	}

	public void doReturn() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Add entries to do SR");
		String gen_id=null; double back_copy1 = 0;
		if(data_tbl_SR_C!=null){
			int s = data_tbl_SR_C.size();
			System.out.println(data_tbl_SR_C.size());
			
			if(s>0){
				for(int i=0; i<s; i++){
					s_ob2_C=table_p_SR_C.getItems().get(i);
					System.out.println(s_ob2_C.getItemName());
					System.out.println(s_ob2_C.getItemPrice());
					System.out.println(s_ob2_C.getItemUnits());
					System.out.println(s_ob2_C.getItemTaxes());
					System.out.println(s_ob2_C.getItemValue());
					System.out.println("");
					
					if(i==0){
						 back_copy1 = Double.parseDouble(back_copy.getText());
						
						ResultSet rs6; Statement stmt6 = co.createStatement(); 
						String query6 = "select newTicket from salesreturn";
						 rs6 = stmt6.executeQuery(query6); String id = null;
						 
						 
						 while(rs6.next())
						 {
							 if(rs6.isLast())
								 id = rs6.getString(1);
						 }
						 
						 if(id==null)
						 {
							 System.out.println("Fetched ID is null");
							 gen_id = "R123";
						 }
						 else{
							 System.out.println(id);
							 String s1 = id.substring(1);
							 int n = Integer.parseInt(s1);
							 System.out.println(n);
							 n=n+1;
							 gen_id = "R"+n;
							 System.out.println(gen_id);
						 }
						 
						 if(gen_id!=null){
							 String query7 = "insert into salesreturn values("+"'"+g_sid+"'"+","+"'"+gen_id+"'"+","+back_copy1+");";
							 System.out.println(query7);
							 Statement stmt7 = co.createStatement();
							 int d = stmt7.executeUpdate(query7);
							 System.out.println("Data sent to salesreturn table"); 
						 }

						 String person = null;String customer1 = null; String customer=null;
						 
						 Statement stmt4 = co.createStatement();
						 String query4 = "select person from tickets where ticketid='"+g_sid+"';";
						 ResultSet rs4 = stmt4.executeQuery(query4);
						 
						 while(rs4.next())
						 {
							person = rs4.getString(1);
						 }
						 
							ResultSet rs3; Statement stmt3 = co.createStatement();
							String query3 = "select costumer from tickets where ticketid='"+g_sid+"';";
							 rs3 = stmt3.executeQuery(query3);
							
						 while(rs3.next())
						 {
							customer1 = rs3.getString(1);
						 }
					
						 String query7 = "insert into tickets values("+"'"+gen_id+"'"+","+"'"+gen_id+"'"+","+"1"+","+"'"+person+"'"+","+"'"+customer1+"'"+","+"1"+");";
						 System.out.println(query7);
						 Statement stmt7 = co.createStatement();
						 int d = stmt7.executeUpdate(query7);
						 System.out.println("Data sent to tickets table");
					}
					
					//System.out.println(s_ob2_C.getItemName());
					//System.out.println(s_ob2_C.getItemPrice());
					//System.out.println(s_ob2_C.getItemUnits());
					//System.out.println(s_ob2_C.getItemTaxes());
					//System.out.println(s_ob2_C.getItemValue());
					
					ResultSet rs32; Statement stmt32 = co.createStatement();
					String query32 = "select taxid from ticketline where ticket='"+g_sid+"' and product='"+s_ob2_C.getItemName()+"';";
					 rs32 = stmt32.executeQuery(query32);
					 
					 String taxid = null;
					
				 while(rs32.next())
				 {
					taxid = rs32.getString(1);
				 }
				System.out.println("");
				System.out.println("");
				System.out.println(taxid);
				
			 String query71 = "insert into returnrable values("+"'"+s_ob2_C.getItemName()+"'"+","+s_ob2_C.getItemUnits()+","+s_ob2_C.getItemPrice()+","+"'"+taxid+"'"+","+"'"+gen_id+"'"+");";
			 System.out.println(query71);
			 Statement stmt71 = co.createStatement();
			 int d = stmt71.executeUpdate(query71);
			 System.out.println("Data sent to returnrable table");
				}
			}
		
			Alert alert=new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			String s1="Refund Amount: Rs. "+ back_copy1;
			alert.setContentText(s1);
			alert.showAndWait();
		
			data_tbl_SR_C.clear();
			data_tbl_SR_C = null;
			stag.close();
		}
		else{
			Alert alert=new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			String s="Delete some items from the table above to refund!";
			alert.setContentText(s);
			alert.showAndWait();
		
		}
		

/*		double back_copy2 = Double.parseDouble(back_copy.getText());
		
		size = table_p_SR.getItems().size();
	    System.out.println("Size of table :"+size);
	 
	    if(size>0)
	    {
	    	
		    for(int i=0;i<size;i++)
		    {
		    	s_ob2=table_p_SR.getItems().get(i);
		    	
		    	System.out.println("for line number: "+i);
				System.out.println(s_ob2.getItemName());
				System.out.println(s_ob2.getItemPrice());
				System.out.println(s_ob2.getItemUnits());
				System.out.println(s_ob2.getItemTaxes());
				System.out.println(s_ob2.getItemValue());
				System.out.println("");
				
				if(i==0){
					double back_copy1 = Double.parseDouble(back_copy.getText());
					
					ResultSet rs6; Statement stmt6 = co.createStatement(); 
					String query6 = "select newTicket from salesreturn";
					 rs6 = stmt6.executeQuery(query6); String id = null;
					 String gen_id=null;
					 
					 while(rs6.next())
					 {
						 if(rs6.isLast())
							 id = rs6.getString(1);
					 }
					 
					 if(id==null)
					 {
						 System.out.println("Fetched ID is null");
						 gen_id = "R123";
					 }
					 else{
						 System.out.println(id);
						 String s = id.substring(1);
						 int n = Integer.parseInt(s);
						 System.out.println(n);
						 n=n+1;
						 gen_id = "R"+n;
						 System.out.println(gen_id);
					 }
					 
					 if(gen_id!=null){
						 String query7 = "insert into salesreturn values("+"'"+g_sid+"'"+","+"'"+gen_id+"'"+","+"'"+back_copy1+"'"+");";
						 Statement stmt7 = co.createStatement();
						 int d = stmt7.executeUpdate(query7);
						 System.out.println("Data sent to salesreturn table"); 
					 }

					 String person = null;String customer1 = null; String customer=null;
					 
					 Statement stmt4 = co.createStatement();
					 String query4 = "select person from tickets where ticketid='"+g_sid+"';";
					 ResultSet rs4 = stmt4.executeQuery(query4);
					 
					 while(rs4.next())
					 {
						person = rs4.getString(1);
					 }
					 
						ResultSet rs3; Statement stmt3 = co.createStatement();
						String query3 = "select costumer from tickets where ticketid='"+g_sid+"';";
						 rs3 = stmt3.executeQuery(query3);
						
					 while(rs3.next())
					 {
						customer1 = rs3.getString(1);
					 }
					 
					//	ResultSet rs31; Statement stmt31 = co.createStatement();
					//	String query31 = "select name from customers where id='"+customer1+"';";
					//	 rs31 = stmt31.executeQuery(query31);


					// while(rs31.next())
					// {
					//	customer = rs31.getString(1);
					// }

					//System.out.println("");
					//System.out.println("");
					//System.out.println("Data to be sent into tickets");
					 //System.out.println(gen_id);
					 //System.out.println(gen_id);
					 //System.out.println("1");
					 //System.out.println(person);
					 //System.out.println(customer);
					 //System.out.println("1");
					 String query7 = "insert into tickets values("+"'"+gen_id+"'"+","+"'"+gen_id+"'"+","+"1"+","+"'"+person+"'"+","+"'"+customer1+"'"+","+"1"+");";
					 System.out.println(query7);
					 Statement stmt7 = co.createStatement();
					 int d = stmt7.executeUpdate(query7);
					 System.out.println("Data sent to salesreturn table");
				}
				
				 
		    }
	    	
	    }
	    else{
	    	
	    }
	*/
	    
	}

	public void completerefund() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Yeah! Got here for id: "+g_sid);
		
		boolean proceed = false;
		double tat = 0; int c=0; int t=0;
		String gen_id=null;
		ArrayList<String> aln = new ArrayList<String>();
		
		 ResultSet rs63; Statement stmt63 = co.createStatement(); 
			String query63 = "select newTicket from salesreturn";
			 rs63 = stmt63.executeQuery(query63); String id1 = null;
			 
			 
			 while(rs63.next())
			 {
				 if(rs63.isLast())
					 id1 = rs63.getString(1);
			 }
			 
			 if(id1==null)
			 {
				 System.out.println("Fetched ID is null");
				 gen_id = "R123";
			 }
			 else{
				 //System.out.println(id1);
				 String s21 = id1.substring(1);
				 int n = Integer.parseInt(s21);
				 //System.out.println(n);
				 n=n+1;
				 gen_id = "R"+n;
				 System.out.println("New ID: "+gen_id); 
			 }
		
		ResultSet rs6; Statement stmt6 = co.createStatement(); 
		String query6 = "select newTicket from salesreturn where oldticket='"+g_sid+"';";
		 rs6 = stmt6.executeQuery(query6); String id = null;
		 
		 if(!rs6.isBeforeFirst()){
			 
			 System.out.println("Refund all bill for new id: "+gen_id);
			 
			Statement stmt621 = co.createStatement(); 
			String query621 = "select product,unit,price,taxid from ticketline where ticket='"+g_sid+"';";
			ResultSet rs621= stmt621.executeQuery(query621); 
			while(rs621.next()){
				System.out.println("Now refund rest data");
				Statement stmt64 = co.createStatement(); 
				String query64 = "insert into returnrable values("+"'"+rs621.getString(1)+"'"+","+rs621.getInt(2)+","+rs621.getDouble(3)+","+"'"+rs621.getString(4)+"'"+","+"'"+gen_id+"'"+");";
				int rs64= stmt64.executeUpdate(query64); 
				tat = tat + rs621.getDouble(3);
				System.out.println("Data inserted to returnrable");
				proceed = true;	
			}
			 
		 }else{
			 while(rs6.next()){
				 id = rs6.getString(1);
				 //System.out.println(id);
				 
				Statement stmt61 = co.createStatement(); 
				String query61 = "select product from returnrable where id='"+id+"';";
				ResultSet rs61= stmt61.executeQuery(query61); 
				while(rs61.next()){
					aln.add(rs61.getString(1));
				} 
			 }
			 
				Statement stmt62 = co.createStatement(); 
				String query62 = "select product,unit,price,taxid from ticketline where ticket='"+g_sid+"';";
				ResultSet rs62= stmt62.executeQuery(query62); 
				System.out.println("From Original bill: ");
				while(rs62.next()){
					if(aln.contains(rs62.getString(1))){
						System.out.println("Already refunded "+rs62.getString(1)); c=0;
					}else{
						System.out.println("Now refund rest data");
						Statement stmt64 = co.createStatement(); 
						String query64 = "insert into returnrable values("+"'"+rs62.getString(1)+"'"+","+rs62.getInt(2)+","+rs62.getDouble(3)+","+"'"+rs62.getString(4)+"'"+","+"'"+gen_id+"'"+");";
						int rs64= stmt64.executeUpdate(query64); 
						tat = tat + rs62.getDouble(3);
						System.out.println("Data inserted to returnrable");
						c = 1;
						proceed = true;
					}
				}
				aln.clear();
		 }
		 if(proceed)
			{

				 String query7 = "insert into salesreturn values("+"'"+g_sid+"'"+","+"'"+gen_id+"'"+","+tat+");";
				 System.out.println(query7);
				 Statement stmt7 = co.createStatement();
				 int d = stmt7.executeUpdate(query7);
				 System.out.println("Data sent to salesreturn table"); 

				 String person = null;String customer1 = null;
				 
				 Statement stmt4 = co.createStatement();
				 String query4 = "select person from tickets where ticketid='"+g_sid+"';";
				 ResultSet rs4 = stmt4.executeQuery(query4);
				 
				 while(rs4.next())
				 {
					person = rs4.getString(1);
				 }
				 
					ResultSet rs3; Statement stmt3 = co.createStatement();
					String query3 = "select costumer from tickets where ticketid='"+g_sid+"';";
					 rs3 = stmt3.executeQuery(query3);
					
				 while(rs3.next())
				 {
					customer1 = rs3.getString(1);
				 }
			
				 String query71 = "insert into tickets values("+"'"+gen_id+"'"+","+"'"+gen_id+"'"+","+"1"+","+"'"+person+"'"+","+"'"+customer1+"'"+","+"1"+");";
				 System.out.println(query71);
				 Statement stmt71 = co.createStatement();
				 int d1 = stmt71.executeUpdate(query71);
				 System.out.println("Data sent to tickets table");
				 t=1;
			}
		 if(c==0)
		 {

			 Alert alert=new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				String s="All items refunded";
				alert.setContentText(s);
				alert.showAndWait();
			 
		 }
		 
		 if(t==1)
		 {
			 Alert alert=new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				String s="Refund done for Rs. "+tat;
				alert.setContentText(s);
				alert.showAndWait();			 
		 }
		 
	}
	
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
													Alert alert1 = new Alert(AlertType.ERROR);
									    			alert1.setTitle("Error Dialog");
									    			alert1.setHeaderText("Edit Sales Error!!");
									    			alert1.setContentText("Already in Edit Sales");
									    			alert1.showAndWait();
												}
												});
										
										}
										else if(entry.getKey().equals("customer_payment")){
											reg1.setText("Customer Payment");
											reg1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
												public void handle(MouseEvent event){
													try{
													main_Window.page3 = FXMLLoader.load(getClass().getResource("/register/customer_payment.fxml"));	
													if(main_Window.primaryStage1.getScene().getRoot()!=main_Window.scene3.getRoot()){
														main_Window.scene3.setRoot(main_Window.page3);
														main_Window.primaryStage1.setScene(main_Window.scene3);
														main_Window.scene3.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
														main_Window.scene3.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
														main_Window.primaryStage1.show();	
														effect(hbg,mbar);
														System.out.println(sales.main_Window.i+" g!=0");
													}
													}
													 catch(Exception e){
														 	main_Window.scene3 = new Scene(main_Window.page3,main_Window.primaryStage1.getWidth(),main_Window.primaryStage1.getHeight());
															main_Window.primaryStage1.setScene(main_Window.scene3);
															main_Window.scene3.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
															main_Window.scene3.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
															main_Window.primaryStage1.show();
															effect(hbg,mbar);
													 }
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




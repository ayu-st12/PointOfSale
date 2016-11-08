package register.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import sales.Fetch_Categories;
import sales.main5_xmlread;
import sales.main_Controller;
import sales.main_Window;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import database.dbfunc;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class paymentRegisterController {

	ToggleGroup tgroup=new ToggleGroup();
	static int mode=0;
	
	static double amt = 0;
	static Timestamp s = null;
	
	static dbfunc db = new dbfunc();
	
	static Connection co = db.connect();
	
	@FXML
	private JFXTextField amnt;
	
	@FXML
	private JFXTextArea pay_notes;
	
	@FXML
	private RadioButton cashin;
	
	@FXML
	private RadioButton cashout;
	
	@FXML
	private Label date_top;
	
	@FXML
	private ImageView save;
	
	@FXML
	private AnchorPane mbar;
	public static Map<String, String> data = new HashMap<String, String>();
	static boolean j=false;
	static boolean k=false;
	static boolean l=false;
	static boolean o=false;
	
	@FXML
	ImageView hbg;
	
	@FXML
	AnchorPane mbar_rest;
	
	@FXML
	AnchorPane mbar_total;
	
	@FXML
	private VBox menu;
	
	@FXML
	private ImageView refresh;
	
	static int g=0;
	
	@FXML
	private void initialize() throws SQLException{
		
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
		
		System.out.println(sales.main_Window.i+" in payments initialize");
		//code for hamburger
		 Pane side = (Pane) mbar;
		 Translate tr = new Translate();
			side.getTransforms().add(tr);
			tr.setX(-310);
		
			 hbg.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event){
						//System.out.println("image clicked");
						effect(hbg, side);
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
		Label name = new Label("Name");
		name.setFont(Font.font ("Roboto", FontWeight.BOLD, 13));
		name.setLayoutX(20);
		name.setLayoutY(90);
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
		id_pane.getChildren().addAll(profimg, name,type);
		
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
		
		
		
		
		
		
		
		
		/*************************************************************/
		/*
		Statement stmt61 = co.createStatement(); 
		String query61 = "select start from cashinout where cid='"+0+"';";
		ResultSet rs61= stmt61.executeQuery(query61);
		if(rs61.isBeforeFirst()){
			while(rs61.next()){
				s = rs61.getTimestamp(1);
				String dte = (s.toString()).substring(0, 19);
				//LocalDate sdate = s.toLocalDateTime().toLocalDate();
				System.out.println(s);
				//System.out.println(sdate);
			}

			Statement stmt62 = co.createStatement(); 
			String query62 = "select money from receipts where datetime>='"+s+"';";
			ResultSet rs62= stmt62.executeQuery(query62);
			if(rs62.isBeforeFirst()){
				while(rs62.next()){
					amt = amt + rs62.getDouble(1);	
				}

				Statement stmt63 = co.createStatement(); String sale = "sale";
				String query63 = "insert into cashinout values('"+sale+"',"+amt+","+"to_timestamp('"+s+"', 'yyyy-mm-dd hh24:mi:ss'),"+0+");";
				System.out.println(query63);
				int d= stmt63.executeUpdate(query63);
				System.out.println("Data inserted");
			}
			else{
				System.out.println("Not sale in this datetime");
			}
		}
		else{
			System.out.println("No entry found");
		}
		*/
		/*************************************************************/
		
		/*************************************************************/
		cashin.setToggleGroup(tgroup);
		cashout.setToggleGroup(tgroup);
		tgroup.selectToggle(null);
		mode = 0;
		/*************************************************************/
		
		
		tgroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		      public void changed(ObservableValue<? extends Toggle> ov,
		          Toggle old_toggle, Toggle new_toggle) {
		        if (tgroup.getSelectedToggle() != null) {
		        	
		        	RadioButton chk = (RadioButton)tgroup.getSelectedToggle(); // Cast object to radio button
		        	String a=chk.getText();
	
			        if(a.equals("Cash In"))
			        {
			        	mode = 1;
			        	System.out.println("cash in");
			        	amnt.setText("");
			        	pay_notes.setText("");
			        }
			        if(a.equals("Cash Out"))
			        {
			        	mode = 2;
			        	System.out.println("cash out");
			        	amnt.setText("");
			        	pay_notes.setText("");
			        }
		        
		        }
		      }
		    });


		save.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if((amnt.getText().length()>0) && (mode==1 || mode==2))
				{
					if(mode==1){
					
						System.out.println("Save All");		
						
						String amt_s = amnt.getText();
						Double d = Double.parseDouble(amt_s);
						System.out.println(d);
						
						String note_s = pay_notes.getText();
						System.out.println(note_s);

						 java.util.Date date= new java.util.Date();
						 Timestamp s1 = (new Timestamp(date.getTime()));
						 String sale = "cash in";
						 
						 Statement stmt64;
						try {
							stmt64 = co.createStatement();
							String query64 = "insert into cashinout values('"+sale+"',"+d+","+"to_timestamp('"+s1+"', 'yyyy-mm-dd hh24:mi:ss'),"+0+",'"+note_s+"');";
							int rs64= stmt64.executeUpdate(query64);
							
							Alert alert=new Alert(AlertType.WARNING);
							alert.setTitle("Warning");
							String s3="Cash in done for Rs. "+d;
							alert.setContentText(s3);
							alert.showAndWait();
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							
					}
					
					if(mode==2){
						
						String amt_s = amnt.getText();
						Double d = Double.parseDouble(amt_s);
						System.out.println(d);
						
						String note_s = pay_notes.getText();
						System.out.println(note_s);	
						
						 java.util.Date date= new java.util.Date();
						 Timestamp s1 = (new Timestamp(date.getTime()));
						 String sale = "cash out";
						 
						 Statement stmt64;
						try {
							stmt64 = co.createStatement();
							String query64 = "insert into cashinout values('"+sale+"',"+d+","+"to_timestamp('"+s1+"', 'yyyy-mm-dd hh24:mi:ss'),"+0+",'"+note_s+"');";
							int rs64= stmt64.executeUpdate(query64);
							
							Alert alert=new Alert(AlertType.WARNING);
							alert.setTitle("Warning");
							String s3="Cash out done for Rs. "+d;
							alert.setContentText(s3);
							alert.showAndWait();							
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}						
						
					}
					
					mode = 0;
					tgroup.selectToggle(null);
					amnt.setText("");
					pay_notes.setText("");
				}
				else{
					
					Alert alert=new Alert(AlertType.WARNING);
					alert.setTitle("Enter a mode!");
					String s="Select a mode and Enter Amount!!";
					alert.setContentText(s);
					alert.showAndWait();
				}
				
			}
	});
		
		refresh.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				tgroup.selectToggle(null);
				amnt.setText("");
				pay_notes.setText("");
//				System.out.println("Reload");
			}
	});
		
		
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
																main_Window.primaryStage1.setScene(sales.main_Window.scene);
																main_Window.scene.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
																main_Window.scene.getStylesheets().add(getClass().getResource("/sales/mainsales.css").toExternalForm());
																main_Window.scene.getStylesheets().add(getClass().getResource("/sales/calculator.css").toExternalForm());
																effect(hbg,mbar);
																System.out.println(sales.main_Window.i+" pay g!=0");
															}
														}
														catch(Exception e){
															
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
														Alert alert1 = new Alert(AlertType.ERROR);
										    			alert1.setTitle("Error Dialog");
										    			alert1.setHeaderText("Payments Error!!");
										    			alert1.setContentText("Already in Payments");
										    			alert1.showAndWait();
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
}

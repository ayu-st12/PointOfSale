/*
 * Copyright (c) 2011, 2014 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package sales;

import database.dbfunc_other;
import register.*;
import register.Controller.*;
import sales.*;
import sales.Main_TableView.SalesTable;
import javafx.event.ActionEvent;
import database.sale;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.TimerTask;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTabPane;
import com.sun.javafx.scene.control.skin.TableViewSkinBase;

import database.dbfunc;
import extraTest.cat_top;
import extraTest.side_Cat;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
/**
 * Sample custom control hosting a text field and a button.
 */

public class main_Controller{

  // for checkout button
  @FXML
  private JFXButton btn_checkout;
  @FXML
  private AnchorPane dim_pane;
  @FXML
  private AnchorPane checkout_side_pane;

  @FXML
  private AnchorPane mbar_total;

  @FXML
  private AnchorPane mbar_rest;

  @FXML
  private Label label_num1;
  @FXML
  private Label label_num2;
  @FXML
  private Label label_result;

  @FXML
  private Button btn_one;
  @FXML
  private Button btn_two;
  @FXML
  private Button btn_three;
  @FXML
  private Button btn_four;
  @FXML
  private Button btn_five;
  @FXML
  private Button btn_six;
  @FXML
  private Button btn_seven;
  @FXML
  private Button btn_eight;
  @FXML
  private Button btn_nine;
  @FXML
  private Button btn_zero;
  @FXML
  private Button btn_add;
  @FXML
  private Button btn_subtract;
  @FXML
  private Button btn_multiply;
  @FXML
  private Button btn_equals;
  @FXML
  private Button btn_decimal;
  @FXML
  private Button btn_clear;
  @FXML
  private Button btn_bksp;
  @FXML
  private RadioButton rb_calculate;
  @FXML
  private RadioButton rb_bill;

  @FXML
  private AnchorPane right_box;

  @FXML
  private Label date_top;

  @FXML
  private Pane root;

  @FXML
  private AnchorPane center_pane;

  @FXML
  public ScrollPane itemlist_d_scroll;

  @FXML
  private ScrollPane add_cat_list;

  @FXML
  private JFXHamburger hamburger;

  @FXML
  private ScrollPane tbl_center;

  @FXML
  private TextField tf_subtotal;

  @FXML
  private TextField tf_taxes;

  @FXML
  private TextField tf_total;

  @FXML
  private AnchorPane total_pane;

  @FXML
  private AnchorPane search_pane;

  @FXML
  private Pane mbar;

  @FXML
  ImageView hbg;

  @FXML
  TabPane tpane;

  @FXML
  private ScrollPane menu_pane;

  @FXML
  private VBox menu;

  @FXML
  private AnchorPane rest;

  static int count_load;
  static int set_cat_other;
  public static AnchorPane base;
  public static int child_cat;
  static dbfunc db;
  static cat_top ct;
  public static boolean j;
  public static boolean k;
  public static boolean l;
  public static boolean o;
  public static Map<String, String> data;
  static side_Cat sp;
  static int unit;
  static ArrayList<SalesTable> al;
  static int i_tbl;
  static int handle_i_tbl;
  static double tax;
  static double subtotal;
  static double total;
  static TableView<SalesTable> t1;
  static Pane product_ap_listen;
  static main_Window center;
  static int size;
  static ToggleGroup tgroup;
  static int mode;
  public static int ayush;
  static int ticket_conf, reciept_conf, ticketline_conf, payments_conf,taxline_conf;
  static Stage pay_stage;
  static String ticket_id;
  static md5 objmd5;
  static String customer_id;
  static private IntegerProperty index;
  static String id_ticket;
  static private Model_Calculator m;
  static private Fetch_Categories fc;
  static private Main_SearchBar sb;
  static private category_system cat_sys;
  static int item;
  static double left;
  static double top;
  static int c, d;
  static private float num1;
  static private String op;
  static private boolean start;
  static private int c1;
  static double done;
  static TextField txt;
  static public Pane catp;
  static AnchorPane fnl;
  static ResultSet rs;
  static ResultSet rs_plist;
  static public String dte;
  static double width, width1;
  static int n;
  static ResultSet rs_catlist;
  public static VBox content_p;
  static public TableView<SalesTable> table_p;
  static SalesTable s_ob1, s_ob2;
  static String name_p;
  static double price;
  static double tax_val;
  static String taxcat;
  static double value;
  static double v;
  public static ObservableList<SalesTable> data_tbl;
  static public Pane cat_top;
  static public Pane side_cat;
  public static String name_db;
  public static ImageView iv;
  public static String prod_id;
  public static ResultSet rs_catlist_cat;
  public static AnchorPane catlist;
  public static Image img;
  static ScrollPane s;
  static VBox content_cat;
  
  public static void initialize_StaticAll() {
    set_cat_other = 0;
    img = null;
    s = null;
    catlist = null;
    cat_top = side_cat = null;
    data_tbl = null;
    table_p = null;
    iv = null;
    name_p = taxcat = name_db = prod_id = null;
    s_ob1 = s_ob2 = null;
    content_p = null;
    dte = null;
    fnl = null;
    rs = rs_catlist_cat = null;
    rs_plist = rs_catlist = null;
    catp = null;
    txt = null;
    done = width = price = tax_val = value = v = width1 = 0.0;
    ayush = n = 0;
    product_ap_listen = new Pane();
    center = new main_Window();
    size = 0;
    mode = 0;
    tgroup=new ToggleGroup();
    t1 = null;
    i_tbl = -1;
    handle_i_tbl = -1;
    tax = 0;
    subtotal = 0;
    total = 0;
    unit = 1;
    al = new ArrayList<SalesTable>();
    ct = new cat_top();
    j = false;
    k = false;
    l = false;
    o = false;
    data = new HashMap<String, String>();
    sp = new side_Cat();
    db = new dbfunc();
    child_cat = 0;
    base = new AnchorPane();
    // count_load = 1;
  }
  
  public static void initialize_NormalAll() {
    ticket_conf = 0;
    reciept_conf = 0;
    ticketline_conf = 0;
    payments_conf = 0;
    taxline_conf = 0;
    pay_stage = new Stage();
    ticket_id = null;
    objmd5 = new md5();
    customer_id = null;
    id_ticket = "T00000001";
    index = new SimpleIntegerProperty();
    m = new Model_Calculator();
    fc = new Fetch_Categories();
    sb = new Main_SearchBar();
    cat_sys = new category_system();
    item = 10;
    left = 0;
    top = 0;
    c = 0;
    d = 0;
    num1 = 0;
    op = "";
    start = true;
    c1 = 0;
  }

	

	
	@FXML
	private void initialize()
	{


    System.out.println("Controller LOADED!");
    if (prod_id == null && count_load > 0) {
      System.out.println("lets try count_load: " + count_load);
      content_cat.getChildren().remove(cat_top);
      base.getChildren().remove(catlist);
      base.getChildren().remove(side_cat);
      content_cat.getChildren().remove(base);
      child_cat = 0;
    }

    if (count_load == 0) {
      System.out.println("Count load is zero");
      initialize_StaticAll();
      initialize_NormalAll();

      count_load++;
    } else {
      System.out.println("No change ");

    }

    if (prod_id != null) {
      set_cat_other = 1;
      content_cat.getChildren().remove(cat_top);
      base.getChildren().remove(catlist);
      base.getChildren().remove(side_cat);
      content_cat.getChildren().remove(base);
      System.out.println("ID: " + prod_id + "  " + "count: " + child_cat);
    }

    // *******************************************************************************
		
		System.out.println(sales.main_Window.i+" in sales initialize");
		rb_bill.setSelected(true);
		//btn_bksp.setText("DEL");
		mode=2;
		
		if(mode==2)
		{
	    	btn_bksp.setText("DEL");
		}
		
		rb_calculate.selectedProperty().addListener(new ChangeListener<Boolean>() {
		    @Override
		    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
		        if (rb_calculate.isSelected()) { 
		    		btn_bksp.setText("BKSP");
		    		//i_tbl = -1;
		    		mode = 1;
		        }
		        else{
		        	btn_bksp.setText("DEL");
		        }
		    }
		});
		
		btn_bksp.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				if(mode==2)
				{				
					//System.out.println("Delete");
			        data_tbl.remove(i_tbl);
				    table_p.getColumns().get(0).setVisible(false);
				    table_p.getColumns().get(0).setVisible(true);
				}
			}
			}) ;
		
		/************UPDATING PRODUCT CODE BEGINS HERE**************/
		java.util.Timer total_u = new java.util.Timer();
		total_u.schedule(new TimerTask() {
		    public void run() {
		         Platform.runLater(new Runnable() {
		            public void run() {
		            	
		            	double p=0,p1=0,p_sum=0,t=0,t1=0,t_sum=0,v=0;
						int u=0;
		            	//System.out.println("Index is: "+handle_i_tbl);
		            	if(handle_i_tbl!=-1){
						size=table_p.getItems().size();
		            	}
							            	
						for(int i=0;i<size;i++){
							s_ob2=table_p.getItems().get(i);
							
							p=s_ob2.getItemPrice();						
										
							u=s_ob2.getItemUnits();	
							p1=p*u;										p_sum+=p1;
							t=(p* s_ob2.getItemTaxes() )/100;				t1=t*u;
							t_sum+=t1;
							v+=s_ob2.getItemValue();
						}
						//System.out.println("p: "+p);
						//System.out.println("p_sum= "+p_sum);
						//System.out.println("u: "+u);
						//System.out.println("t: "+t);
						
						done = p_sum + t_sum;
						
						center.subtotal.setText(Double.toString(p_sum));
				        center.subtax.setText(Double.toString(t_sum));
				        center.total.setText(Double.toString(done));
		            }
		        });
		    }
		}, 100,100);
	
	/************UPDATING PRODUCT UNITS CODE ENDS HERE**************/	

		/*************************************************************/
		rb_calculate.setToggleGroup(tgroup);
		rb_bill.setToggleGroup(tgroup);
	
		tgroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		      public void changed(ObservableValue<? extends Toggle> ov,
		          Toggle old_toggle, Toggle new_toggle) {
		        if (tgroup.getSelectedToggle() != null) {
		        	
		        	RadioButton chk = (RadioButton)tgroup.getSelectedToggle(); // Cast object to radio button
		            //System.out.println("Selected Radio Button - "+chk.getText());
		        	String a=chk.getText();
	
			        if(a.equals("Calculation Mode"))
			        {
			        	mode = 1;
			        	//System.out.println("Normal Calculation");
			        }
			        if(a.equals("Billing Mode"))
			        {
			        	mode = 2;
			        	//System.out.println("Billing Calculation");
			        }
		        
		        }
		      }
		    });

		
		/*********************SETTING PRODUCTS CODE HERE*************/
		
		java.util.Timer timer_prod = new java.util.Timer();
		timer_prod.schedule(new TimerTask() {
		    public void run() {
		         Platform.runLater(new Runnable() {
		            public void run() {
		            	if(prod_id!=null){	
		     		   width = itemlist_d_scroll.getWidth();
		    		   n = (int)Math.floor(width/130);
		    		   
		    		   if(n!=0)
		    			{
		    			   	int c_p=0;
		    				double left_p=0;
		    				double top_p=10;
		    				int x = n;
		    				AnchorPane fnl_p = new AnchorPane();
		    				content_p = new VBox(5); 
		    				
		    				itemlist_d_scroll.setContent(content_p);
		    				itemlist_d_scroll.setFitToWidth(true);
		    				int i=0;
		    				
		    				Connection co = db.connect();
		    				   Statement s;
		    				try {
		    					
		    					s = co.createStatement();
		    					String query = "select name from product where cat='"+prod_id+"';";
		    					rs_plist = s.executeQuery(query);
		    					
		    				} catch (SQLException e) {
		    					// TODO Auto-generated catch block
		    					e.printStackTrace();
		    				}
		    				
		    				try {
		    					while(rs_plist.next()) 
		    					{	
		    						String name = rs_plist.getString(1);
		    						
		    						if(i==0){
		    						Pane ap = item_p(name);
		    						ap.setLayoutX(12.0);
		    						left_p = 12.0;
		    						fnl_p.getChildren().add(ap);
		    						c_p++;i++;
		    						}
		    						else
		    						{
		    							Pane ap = item_p(name);
		    							left_p = left_p+115;
		    							if(c_p>x)
		    							{
		    								if(c_p % (x+1) ==0){
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
		    							c_p++;i++;
		    						}
		    					}
		    				} catch (SQLException e) {
		    					// TODO Auto-generated catch block
		    					e.printStackTrace();
		    				}
		    			
		    				content_p.getChildren().add(fnl_p);			
		    			}
		            	}
		            }
		        });
		    }
		}, 100,100);			
			
		/*********************SETTING PRODUCTS CODE END HERE*********/	

		//creating the left pane
		//changing tabs
		 VBox tb1 = new VBox();
		 	tb1.setSpacing(5);
		 	tb1.setPrefHeight(50);
			tb1.setPrefWidth(295);
			tb1.setLayoutY(50);
			Button btn1 = new Button("New Sale");
			btn1.setPrefWidth(295);
			btn1.setPrefHeight(50);
			btn1.setId("btn");
			btn1.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event){
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmation Dialog");
					alert.setHeaderText("New Sale");
					alert.setContentText("Are you sure want to clear the current sale?");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK){
						try{
						if(main_Controller.table_p==null){
						}
						
						else{
							main_Controller.table_p.getItems().get(0).getItemName().toString();
							main_Controller.data_tbl.clear();
						try {
				        	double angle=0.0;
				        	Rotate rotate = new Rotate();
				        	main_Controller mc = new main_Controller();
				        	Fetch_Categories fc = new Fetch_Categories();
				        	main_Controller.iv.getTransforms().add(rotate);
				        	angle+=180.0;
				        	rotate.setAngle(angle);
				        	rotate.setPivotX(mc.iv.getFitWidth() / 2);
				        	rotate.setPivotY(mc.iv.getFitHeight() / 2);
				        	mc.iv.setImage(mc.img);
				        	mc.side_cat.getChildren().clear();
				            mc.catlist.getChildren().clear();
				            main_Controller.rs_catlist_cat = fc.fetch_cat();
				            main_Controller.catlist = mc.setup(main_Controller.rs_catlist_cat);
				            main_Controller.base.getChildren().add(mc.catlist);
				            mc.ayush = 1;
				            mc.child_cat=0;
				            mc.prod_id = null;
				            mc.content_p.getChildren().clear();

				          } catch (Exception e1) {
				            // TODO Auto-generated catch block
				            e1.printStackTrace();
				          }
						}
						}
						catch(Exception e2){
			    			Alert alert1 = new Alert(AlertType.ERROR);
			    			alert1.setTitle("Error Dialog");
			    			alert1.setHeaderText("Error");
			    			alert1.setContentText("Already Empty! Nothing To Reset");
			    			alert1.showAndWait();
						}
					}
					
				}
			});
			tb1.getChildren().addAll(btn1);
		
		 VBox tb2 = new VBox();
		 	tb2.setPrefHeight(50);
			tb2.setPrefWidth(295);
			tb2.setLayoutY(50);
			tb2.setSpacing(5);
			JFXButton btn21 = new JFXButton("Add Customer");
			btn21.setPrefWidth(295);
			btn21.setPrefHeight(50);
			btn21.setId("btn");
			JFXButton btn22 = new JFXButton("Search Customer");
			btn22.setPrefWidth(295);
			btn22.setPrefHeight(50);
			btn22.setId("btn");
			tb2.getChildren().addAll(btn21,btn22);
		
		 VBox tb3 = new VBox();
			tb3.setPrefHeight(50);
			tb3.setPrefWidth(295);
			tb3.setLayoutY(50);
			tb3.setSpacing(5);
			JFXButton btn31 = new JFXButton("Product Search");
			btn31.setPrefWidth(295);
			btn31.setPrefHeight(50);
			btn31.setId("btn");
			JFXButton btn32 = new JFXButton("Remove");
			btn32.setPrefWidth(295);
			btn32.setPrefHeight(50);
			btn32.setId("btn");
			JFXButton btn33 = new JFXButton("Edit");
			btn33.setPrefWidth	(295);
			btn33.setPrefHeight(50);
			btn33.setId("btn");
			tb3.getChildren().addAll(btn31,btn32,btn33);
				
			Image img1 = new Image(getClass().getResource("/sales/sale.png").toExternalForm(),20,20,false,false);
			Image img2 = new Image(getClass().getResource("/sales/customer.png").toExternalForm(),20,20,false,false);
			Image img3 = new Image(getClass().getResource("/sales/product.png").toExternalForm(),20,20,false,false);
			ImageView i1 = new ImageView();
			ImageView i2 = new ImageView();
			ImageView i3 = new ImageView();
			i1.setImage(img1);
			i2.setImage(img2);
			i3.setImage(img3);
			Tab t1 = new Tab();
			t1.setText("Sale");
			Tab t2 = new Tab();
			t2.setText("Customer");
			Tab t3 = new Tab();
			t3.setText("Product");
			TabPane tp = tpane;
			tp.getTabs().add(t1);
			tp.getTabs().add(t2);
			tp.getTabs().add(t3);
			tp.getTabs().get(0).setGraphic(i1);
			tp.getTabs().get(0).setContent((Pane)tb1);
			tp.getTabs().get(1).setGraphic(i2);
			tp.getTabs().get(1).setContent((Pane)tb2);
			tp.getTabs().get(2).setGraphic(i3);
			tp.getTabs().get(2).setContent((Pane)tb3);
			
		 //tab pane code ends....	
		/*	
		//center table pane
		 Main_TableView mn = new Main_TableView();
		 AnchorPane center = (AnchorPane) page.lookup("#center_pane");
		 center.getChildren().add(mn.p);
		 System.out.println(mn.p);
		*/

		
		
		//creating the main menu
		
		//code for hamburger
		 Pane side = (Pane) mbar;
		 Translate tr = new Translate();
			side.getTransforms().add(tr);
			tr.setX(-310);
			
			Translate tr_mbar_total = new Translate();
			mbar_total.getTransforms().add(tr_mbar_total);
			tr_mbar_total.setX(-2000);
			
			mbar_rest.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e){
					effect_ham(hbg,mbar);
				}
			});
		
			rest.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event){
					Translate tr = new Translate();
					dim_pane.getTransforms().add(tr);
					tr.setX(1500);
					System.out.println(tr.getX()+" in rest");
					//dim_pane.toBack();
				}
			});
			 hbg.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event){
						//System.out.println("image clicked");
						effect_ham(hbg, mbar);
					}
				});
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
				Image prof = new Image(getClass().getResource("profile.png").toExternalForm());
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
				
				//checkout button code
				Translate tra = new Translate();
				dim_pane.getTransforms().add(tra);
				tra.setX(1500);
				System.out.println(tra.getX()+" in start");
				btn_checkout.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event){
						try{
						if(!data_tbl.isEmpty()){
							SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
							//generating a random ticket id
							ticket_id = (((objmd5.getMD5(sdfDate.format(new Date())).substring(2,11)).toUpperCase()));
							if(ticket_id!=null)
								create_checkout_pane(data_tbl);
						}
						else{
							Alert alert1 = new Alert(AlertType.ERROR);
			    			alert1.setTitle("Error");
			    			alert1.setHeaderText("Empty Cart");
			    			alert1.setContentText("Cannot Checkout with Empty Cart!");
			    			alert1.showAndWait();
						}
						}
						catch(Exception e){
							Alert alert1 = new Alert(AlertType.ERROR);
			    			alert1.setTitle("Error Dialog");
			    			alert1.setHeaderText("Error");
			    			alert1.setContentText("Cannot Checkout with Empty Cart!");
			    			alert1.showAndWait();
						}
					}
				});
				
		
				
	//creating auto barcode detector
	
	//ends here			
				
	/************SETTING SEARCH ITEM DYNAMICALLY CODE BEGINS HERE**************/
		search_pane.getChildren().add(sb.searchIconClicked());
	/************SETTING SEARCH ITEM DYNAMICALLY CODE ENDS HERE**************/			

		
		
	/************SETTING TOTAL FIELDS DYNAMICALLY CODE BEGINS HERE**************/
		/*
		total_pane.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {	
				width1 = itemlist_d_scroll.getWidth();
				int s = (int)(width1-10)/3;
				tf_subtotal.setPrefWidth(s);
				tf_total.setPrefWidth(s);
				tf_taxes.setPrefWidth(s);
			}
		}); */
	/************SETTING TOTAL FIELDS DYNAMICALLY CODE ENDS HERE**************/			
		
		
	/************SETTING CENTER PANE CODE BEGINS HERE**************/
		/*
		java.util.Timer table = new java.util.Timer();
		table.schedule(new TimerTask() {
		    public void run() {
		         Platform.runLater(new Runnable() {
		            public void run() {	*/
		
		            	TableView<SalesTable> t = item(data_tbl);
		            	tbl_center.setContent(t);
		            	tbl_center.setFitToHeight(true);
		            	tbl_center.setFitToWidth(true);
		  
		           /* }
		        });
		    }
		}, 100,100);	*/
	
	/************SETTING CENTER PANE CODE ENDS HERE**************/	
		
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
		
		
	/************SETTING PRODUCTS DYNAMICALLY (WHEN SCREEN SIZE CHANGES) CODE BEGINS HERE**************/
		
		
		itemlist_d_scroll.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				double width;int n;
				width = itemlist_d_scroll.getWidth();
				   //System.out.println("SCrollPane Width is: " + width);
				   n = (int)Math.floor(width/130);
				   //System.out.println(n);
				   
				   if(n!=0)
					{
					   	int c_p=0;
						double left_p=0;
						double top_p=10;
						int x = n;
						AnchorPane fnl_p = new AnchorPane();
						content_p = new VBox(5); 
						
						itemlist_d_scroll.setContent(content_p);
						itemlist_d_scroll.setFitToWidth(true);
						int i=0;
						
						Connection co = db.connect();
						   Statement s;
						try {
							
							s = co.createStatement();
							String query = "select name from product where cat='"+prod_id+"';";
							rs_plist = s.executeQuery(query);
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							System.out.println();
						}
						
						
						try {
							while(rs_plist.next()) 
							{	
								String name = rs_plist.getString(1);
								//System.out.println(name + " "+i);
								
								if(i==0){
								Pane ap = item_p(name);
								ap.setLayoutX(12.0);
								left_p = 12.0;
								fnl_p.getChildren().add(ap);
								c_p++;i++;
								}
								else
								{
									Pane ap = item_p(name);
									//System.out.println(left_p + " "+ c_p);
									left_p = left_p+115;
									if(c_p>x)
									{
										//System.out.println(" item & x "+c_p + " "+x);
										if(c_p % (x+1) ==0){
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
									c_p++;i++;
								}
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							System.out.println();
						}
					
						content_p.getChildren().add(fnl_p);			
					}
			}
        });
		
		
		
	/************SETTING PRODUCTS CODE ENDS HERE**************/	
				
	/************SETTING CATEGORY LIST DYNAMICALLY CODE BEGINS HERE**************/

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
					 co = db.connect();
					 s = co.createStatement();
					 
					 String query = "select id,name from categories where pid IN(select pid from categories where name='"+name_db+"');";
					 
					 rs = s.executeQuery(query);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
					 AnchorPane p = setup(rs);
					 side_cat.getChildren().clear();
					 //System.out.println("side category cleared: "+ name_db);
					 
					 String query1 = "select name from categories where id IN(select pid from categories where name='"+name_db+"');";
					 try {
						 co = db.connect();
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
					side_cat.getChildren().clear();
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
		
		  try {
		      rs_catlist_cat = fc.fetch_cat();
		    } catch (SQLException e1) {
		      // TODO Auto-generated catch block
		      e1.printStackTrace();
		    }
		
		content_cat = new VBox(5);
	    content_cat.setId("content");
	    add_cat_list.setContent(content_cat);
	    add_cat_list.setFitToWidth(true);

	    content_cat.getChildren().add(cat_top);

	    if (set_cat_other == 0) {
	      catlist = setup(rs_catlist_cat);
	      if (base != null && side_cat != null) {
	        base.getChildren().add(catlist);

	        base.setLeftAnchor(side_cat, 0.0);
	        base.setTopAnchor(side_cat, 4.0);
	        base.setBottomAnchor(side_cat, 0.0);
	        base.setRightAnchor(side_cat, 0.0);

	        base.getChildren().add(side_cat);
	        side_cat.setVisible(false);
	        content_cat.getChildren().add(base);
	      }
	    }

	    if (set_cat_other == 1) {

	      Connection co = db.connect();
	      Statement s;
	      try {
	        s = co.createStatement();
	        String query = "select id,name from categories where pid ="+"'"+prod_id+"'"+";";
	        System.out.println(query);
	        rs_catlist_cat = s.executeQuery(query);


	        if (!rs_catlist_cat.isBeforeFirst()) {
	          if (base != null && side_cat != null) {
	            base.getChildren().add(catlist);

	            base.setLeftAnchor(side_cat, 0.0);
	            base.setTopAnchor(side_cat, 4.0);
	            base.setBottomAnchor(side_cat, 0.0);
	            base.setRightAnchor(side_cat, 0.0);

	            base.getChildren().add(side_cat);
	            side_cat.setVisible(false);
	            content_cat.getChildren().add(base);
	          }
	        }
	        else{

	          catlist = setup(rs_catlist_cat);
	          if (base != null && side_cat != null) {
	            base.getChildren().add(catlist);

	            base.setLeftAnchor(side_cat, 0.0);
	            base.setTopAnchor(side_cat, 4.0);
	            base.setBottomAnchor(side_cat, 0.0);
	            base.setRightAnchor(side_cat, 0.0);

	            base.getChildren().add(side_cat);
	            side_cat.setVisible(false);
	            content_cat.getChildren().add(base);
	          }
	        }

	      } catch (SQLException e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	      }
	    }
			
			//catlist.setVisible(false);
						
		/************SETTING CATEGORY LIST CODE ENDS HERE**************/	
		
		
	/************SETTING Calculator CODE BEGINS HERE**************/
		
		label_num1.setText("");
		label_num2.setText("");
		
	/************SETTING Calculator CODE ENDS HERE**************/		
	}
	
	//code for checkout button
		public void create_checkout_pane(ObservableList<SalesTable> data_tbl){
			Translate tr = new Translate();
			dim_pane.getTransforms().add(tr);
			tr.setX(-1500);
			System.out.println(tr.getX()+" in checkout incoming");
			//dim_pane.toFront();
			dim_pane.setId("dim_pane");
			
			TranslateTransition tt = new TranslateTransition();
			tt.setNode(checkout_side_pane);
			tt.fromYProperty().bind(checkout_side_pane.translateXProperty().add(checkout_side_pane.getHeight()));
			tt.toYProperty().bind(checkout_side_pane.translateXProperty().add(checkout_side_pane.getHeight()-dim_pane.getHeight()));
			tt.setDuration(new Duration(100));
			tt.play();
			Label Checkout = new Label("Checkout");
			Checkout.setId("ck");
			Checkout.setPrefWidth(400);
			Checkout.setAlignment(Pos.CENTER);
			Checkout.setTextFill(Color.web("#ffffff"));
			Separator sep1 = new Separator();
			sep1.setLayoutX(20);
			sep1.setLayoutY(60);
			sep1.setPrefWidth(360);
			Label item = new Label("Item");
			item.setId("item");
			//item.setPrefWidth(200);
			item.setTextFill(Color.web("#ffffff"));
			item.setAlignment(Pos.CENTER_LEFT);
			item.setLayoutY(80);
			item.setLayoutX(20);
			Label price = new Label("Price");
			price.setId("price");
			//price.setPrefWidth(200);
			price.setTextFill(Color.web("#ffffff"));
			price.setAlignment(Pos.CENTER_RIGHT);
			price.setLayoutY(80);
			price.setLayoutX(350);
			ScrollPane final_item_list = new ScrollPane();
			VBox vb = new VBox();
			final_item_list.setPrefWidth(360);
			final_item_list.setPrefHeight(300);
			final_item_list.setLayoutY(100);
			final_item_list.setLayoutX(20);
			final_item_list.setId("final_item_list");
			final_item_list.setFitToWidth(true);
			JFXButton exit = new JFXButton("<<Continue Shopping");
			final_item_list_class ap = new final_item_list_class();
			checkout_side_pane.getChildren().clear();
			final_item_list.setContent(null);
			System.out.println(data_tbl.size());
			for(int i=0;i<data_tbl.size();i++){
				vb.getChildren().addAll(ap.final_item_list_values(table_p.getItems().get(i).getItemName().toString(),table_p.getItems().get(i).getItemValue().toString()));
			}
			final_item_list.setContent(vb);
			Separator sep2 = new Separator();
			sep2.setLayoutX(20);
			sep2.setLayoutY(400);
			sep2.setPrefWidth(360);
			Label total_label = new Label("Total");
			total_label.setLayoutY(410);
			total_label.setLayoutX(340);
			total_label.setTextFill(Color.web("#ffffff"));
			total_label.setAlignment(Pos.CENTER_RIGHT);
			Label tot = new Label(Double.toString(done));
			tot.setAlignment(Pos.CENTER_RIGHT);
			tot.setLayoutY(430);
			tot.setLayoutX(320);
			tot.setTextFill(Color.web("#ffffff"));
			exit.setLayoutY(490);
			exit.setLayoutX(20);
			exit.setId("exit");
			Button payment = new Button("Pay Now");
			payment.setPrefHeight(30);
			payment.setPrefWidth(100);
			payment.setId("payment");
			payment.setLayoutX(280);
			payment.setLayoutY(480);
			checkout_side_pane.getChildren().addAll(final_item_list,Checkout,sep1,item,price,exit,sep2,tot,total_label,payment);
			exit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event){
					Translate tr = new Translate();
					dim_pane.getTransforms().add(tr);
					tr.setX(1500);
					System.out.println(tr.getX()+" in exit");
					//dim_pane.toBack();				
				}
			});
			
			payment.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event){
					create_payment_window();				
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
				Image img1 = new Image(getClass().getResource("register.png").toExternalForm(),30,30,false,false);
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
													Alert alert1 = new Alert(AlertType.ERROR);
									    			alert1.setTitle("Error Dialog");
									    			alert1.setHeaderText("Already in Sales!!");
									    			alert1.setContentText("Press New Sales for starting a new Sale");
									    			alert1.showAndWait();
												}
												});
										}
										else if(entry.getKey().equals("edit_sale")){
											reg1.setText("Edit Sale");								
											reg1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
												public void handle(MouseEvent event){
													try{
														main_Window.page2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/register/edit_sales_test.fxml"));
														if(main_Window.primaryStage1.getScene().getRoot()!=main_Window.scene2.getRoot()){
														main_Window.scene2.setRoot(main_Window.page2);
														main_Window.primaryStage1.setScene(main_Window.scene2);
														main_Window.scene2.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
														main_Window.scene2.getStylesheets().add(getClass().getResource("ham.css").toExternalForm());
														main_Window.primaryStage1.show();	
														effect_ham(hbg,mbar);
														System.out.println(sales.main_Window.i+" g!=0");
													}
													}
														catch(Exception e){
															main_Window.scene2 = new Scene(main_Window.page2,main_Window.primaryStage1.getWidth(),main_Window.primaryStage1.getHeight());
															main_Window.primaryStage1.setScene(main_Window.scene2);
															main_Window.scene2.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
															main_Window.scene2.getStylesheets().add(getClass().getResource("ham.css").toExternalForm());
															main_Window.primaryStage1.show();
															effect_ham(hbg,mbar);
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
														main_Window.scene3.getStylesheets().add(getClass().getResource("ham.css").toExternalForm());
														main_Window.primaryStage1.show();	
														effect_ham(hbg,mbar);
														System.out.println(sales.main_Window.i+" g!=0");
													}
													}
													 catch(Exception e){
														 	main_Window.scene3 = new Scene(main_Window.page3,main_Window.primaryStage1.getWidth(),main_Window.primaryStage1.getHeight());
															main_Window.primaryStage1.setScene(main_Window.scene3);
															main_Window.scene3.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
															main_Window.scene3.getStylesheets().add(getClass().getResource("ham.css").toExternalForm());
															main_Window.primaryStage1.show();
															effect_ham(hbg,mbar);
													 }
												}
												});
										}
										else if(entry.getKey().equals("payments")){
											reg1.setText("Payments");
											reg1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
												public void handle(MouseEvent event){
													try{
														main_Window.page4 = FXMLLoader.load(getClass().getResource("/register/payments_register.fxml"));
													if(main_Window.primaryStage1.getScene().getRoot()!=main_Window.scene4.getRoot()){
														main_Window.scene4.setRoot(main_Window.page4);
														main_Window.primaryStage1.setScene(main_Window.scene4);
														main_Window.scene4.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
														main_Window.scene4.getStylesheets().add(getClass().getResource("ham.css").toExternalForm());
														main_Window.primaryStage1.show();	
														effect_ham(hbg,mbar);
														System.out.println(sales.main_Window.i+" g!=0");
													}
													}
													catch(Exception e){
														e.printStackTrace();
														main_Window.scene4 = new Scene(main_Window.page4,main_Window.primaryStage1.getWidth(),main_Window.primaryStage1.getHeight());
														main_Window.primaryStage1.setScene(main_Window.scene4);
														main_Window.scene4.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
														main_Window.scene4.getStylesheets().add(getClass().getResource("ham.css").toExternalForm());
														main_Window.primaryStage1.show();
														effect_ham(hbg,mbar);
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
														effect_ham(hbg,mbar);
														System.out.println(sales.main_Window.i+" g!=0");
													}
													}
													catch(Exception e){	
														main_Window.scene5 = new Scene(main_Window.page5,main_Window.primaryStage1.getWidth(),main_Window.primaryStage1.getHeight());	
														main_Window.primaryStage1.setScene(main_Window.scene5);
														main_Window.scene5.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
														main_Window.scene5.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
														main_Window.primaryStage1.show();
														effect_ham(hbg,mbar);
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
				Image img1 = new Image(getClass().getResource("administration.png").toExternalForm(),30,30,false,false);
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
													main_Window.page6 = (AnchorPane) FXMLLoader.load(getClass().getResource("/sales/test.fxml"));
													if(main_Window.primaryStage1.getScene().getRoot()!=main_Window.scene6.getRoot()){
													main_Window.scene6.setRoot(main_Window.page6);
													main_Window.primaryStage1.setScene(main_Window.scene6);
													main_Window.scene6.getStylesheets().add(getClass().getResource("/sales/application.css").toExternalForm());
													main_Window.scene6.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
													main_Window.scene6.getStylesheets().add(getClass().getResource("/sales/mainsales.css").toExternalForm());
													main_Window.primaryStage1.show();	
													effect_ham(hbg,mbar);
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
														effect_ham(hbg,mbar);
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
														effect_ham(hbg,mbar);
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
															effect_ham(hbg,mbar);
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
				Image img1 = new Image(getClass().getResource("utilities.png").toExternalForm(),30,30,false,false);
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
				Image img1 = new Image(getClass().getResource("settings.png").toExternalForm(),30,30,false,false);
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
	
	public int effect(int ch, ImageView s, Pane t){
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
		return 0;
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
					//System.out.println(id +"  "+ name + " "+i);
					
					if(i==0){
					ap = item_c(name);
					if(child_cat<=1)
					{
						ap.setLayoutY(5.0);
						top=5.0;
					}
					fnl.getChildren().add(ap);
					}
					else{
						ap = item_c(name);
						top = top+60;
						ap.setLayoutY(top);
						fnl.getChildren().add(ap);				
					}
					i++;
				}
				//fnl.getChildren().add(side_cat);
			}
			else{
				//System.out.println("GOT NULL");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fnl;
	}

	public void testfunc(String main_cat, String p_id) throws SQLException{
		
		if(cat_top!=null){
			//System.out.println(main_cat);
			
			
			main_Controller.name_db = main_cat;
			 Connection co;
			 Statement s;
			 String result;
			 String id = null; String name= null;
			 
			 co = db.connect();
			 s = co.createStatement();
			
			 String query = "select id,name from categories where pid IN(select id from categories where name="+"'"+main_cat+"'"+");";
			 rs = s.executeQuery(query);
			 
			 if (!rs.isBeforeFirst() ) {   
				 main_Controller.prod_id = p_id;
				 //System.out.println("PRODUCT: "+main_cat+" ID:"+prod_id); 
				} 
			 else{
			 AnchorPane p = setup(rs);
			 side_cat.getChildren().clear();
			 side_cat.getChildren().add(p);
			 
			 //System.out.println(rs);			 
			 
			catp = (Pane) cat_top.lookup("#c1");
			//System.out.println(cat_top);
			//System.out.println(catp);
			iv = (ImageView) catp.lookup("#ClickSwing");
			child_cat++;
			//System.out.println(iv);
			//System.out.println("Category called child count:"+child_cat);
			//catlist.setVisible(false);
			effect(child_cat,iv, side_cat);
			 }
		}
		else
		{
			//System.out.println("Not found");
		}
	}
	
	public void prod_detail(String n,double p,double t)
	{
		if(mode==2){
		
		handle_i_tbl = 1;	
			
		name_p = n;
		price = p;
		tax_val = t;

		subtotal+=price;
		//center.subtotal.setText(Double.toString(subtotal));
		
		
		double tax1 = price*(tax_val/100);
		tax+=tax1;
		//center.subtax.setText(Double.toString(tax));
		
		v = price + (price*tax_val)/100;
		
		value=(Long)Math.round(v*100)/100;
		total+=value;
		total=(Long)Math.round(total*100)/100;
		//center.total.setText(Double.toString(total));
		
		//System.out.println("Into MainController");
		
		data_tbl = addData(name_p,price,unit,tax_val,value);
		
		TableView<SalesTable> t1 = item(data_tbl);
    	//tbl_center.setContent(t);
    	//tbl_center.setFitToHeight(true);
    	//tbl_center.setFitToWidth(true);
		
		center.s.setContent(t1);
		center.s.setFitToHeight(true);
		center.s.setFitToWidth(true);
		
		//System.out.println("Name :"+name_p+" Price :"+price+" Tax :"+tax_val);
		}
		else{
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Product Entry : WARNING");
			String s = "Please select the 'Billing Mode' to add products!";
			alert.setContentText(s);
			alert.showAndWait();
		}
	}
	
	public ObservableList<SalesTable> addData(String n,double p,int u,double t,double v)
	{
		 
		   
		   ObservableList<SalesTable> data;
		   
	    	al.add(new SalesTable(n, p, u, t, v));
		   	
	        data=FXCollections.observableList(al);
			return data;
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
       	
       table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

           @Override
           public void changed(ObservableValue observable, Object oldvalue, Object newValue) {
               SalesTable selectedPerson = (SalesTable) newValue;
               setIndex(data.indexOf(newValue));

               System.out.println("OK");
               System.out.println("INSIDE TABLE VIEW");
           }
       });
       
       table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
           //Check whether item is selected and set value of selected item to Label
           if (table.getSelectionModel().getSelectedItem() != null) {
           	
              System.out.println("Sonething can be done");
              s_ob1 = table.getSelectionModel().getSelectedItem();
              s_ob2 = s_ob1;
              i_tbl =data_tbl.indexOf(s_ob1);

              System.out.println("Index is :" + i_tbl);
             
              System.out.println(s_ob1.getItemName());
              
           }
       });
       
       return table;
	    }
	    
	    
	    public static class SalesTable {
	 
	        private final SimpleStringProperty itemName;
	        private final SimpleDoubleProperty itemPrice;
	        private final SimpleIntegerProperty itemUnits;
	        private final SimpleDoubleProperty itemTaxes;
	        private final SimpleDoubleProperty itemValue;
	 
	        SalesTable(String iName, Double iPrice, Integer iUnits, Double iTaxes, Double iValue) {
	            this.itemName = new SimpleStringProperty(iName);
	            this.itemPrice = new SimpleDoubleProperty(iPrice);
	            this.itemUnits = new SimpleIntegerProperty(iUnits);
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
	        public Integer getItemUnits() {
	            return itemUnits.get();
	        }
	 
	        public void setItemUnits(Integer iUnits) {
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

	    
	  
	    
	    @FXML
		public void processNumbers(ActionEvent e){
	    	//PROCESSING NUMBERS FOR CALCULATION MODE
	    	if(mode==1){
	    	
	    		if(start){
	    			label_result.setText("");
	    			start=false;
	    		}
	    		//System.out.println("Start: "+start);
			
	    		String value=((Button)e.getSource()).getText();
					
	    		if(op.isEmpty())
	    		{
	    			if(value.equals("."))
	    				c++;
	    			if(c<=1){
	    				label_num1.setText(label_num1.getText() + value);
				
	    				if( ((Button)e.getSource()).equals(btn_bksp) ){
	    					String str=m.backspace_func(label_num1.getText() );
	    					label_num1.setText(str);
	    				}
	    			}
	    			else{
	    				label_num1.setText("");
	    				c=0;
	    			}
	    			//label_num1.setText(label_num1.getText() + value);
				
	    		}
	    		else if(!label_num1.getText().equals("") && c1==1)
	    		{
	    			if(value.equals("."))
					d++;
	    			if(d<=1){
	    				label_num2.setText(label_num2.getText() + value);
				
	    				if( ((Button)e.getSource()).equals(btn_bksp) ){
	    					String str=m.backspace_func(label_num2.getText() );
	    					label_num2.setText(str);
	    				}
				
	    			}
	    			else{
	    				label_num2.setText("");
	    				d=0;
	    			}
	    		}
	    	}
	    	//PROCESSING NUMBERS FOR BILLING MODE
	    	if(mode==2){

	    		if(start){
	    			label_result.setText("");
	    			start=false;
	    		}
	    		String value=((Button)e.getSource()).getText();
	    		
	    		if(value.equals("."))
    				c++;
    			if(c<=1){
    				label_num1.setText(label_num1.getText() + value);
			
    				if( ((Button)e.getSource()).equals(btn_bksp) ){
    					String str=m.backspace_func(label_num1.getText() );
    					label_num1.setText(str);
    				}
    			}
    			else{
    				label_num1.setText("");
    				c=0;
    			}	
	    	}
	    }
		
	    @FXML
		public void processOperators(ActionEvent e){
	    		    	
	    	String value= ((Button)e.getSource()).getText();
			//PROCESSING OPERATORS FOR CALCULATION MODE		
			if(mode == 1){
				if(i_tbl >=0)
				{
					op="";
					Alert alert=new Alert(AlertType.WARNING);
					alert.setTitle("Billing Warning");
					String s="No billing operation allowed";
					alert.setContentText(s);
					alert.showAndWait();
					i_tbl = -1;
					table_p.getSelectionModel().clearSelection();
					value="";
				}
				if(!(value.equals("="))){
					if(!op.isEmpty())
						return;
				
					op=value; c1=1;
					try{
					num1=Float.parseFloat(label_num1.getText());
					}catch(Exception exp){
						exp.getMessage();
					}
					//CHECKING 1ST INPUT
					
					//System.out.println("First Num: "+num1);
					//System.out.println("Op: "+op);
					label_result.setText("");
				
				}
				else{
					if(op.isEmpty())
						return;
					
					float num2=Float.parseFloat(label_num2.getText());
					//System.out.println("Second num: "+num2);
					float output=m.calculate(num1, num2, op);
					//System.out.println("Result: "+output);
					label_result.setText(String.valueOf(output));
				
					op="";
					start=true;
				}
				
				if(value.equals("CE")){
					label_num1.setText("");
					label_num2.setText("");
					label_result.setText("");
					num1=0;
					op="";
					start=false;
				}
			}
			//PROCESSING OPERATORS FOR BILLING MODE
			if(mode == 2)
			{
				//System.out.println("In mode 2");
				if(value.equals("CE")){
					label_num1.setText("");
				}
				
				if( ((Button)e.getSource()).equals(btn_add) ){

					double u=0;
					try{		
					String s2=label_num1.getText();
					u=Double.parseDouble(s2);
					}catch(Exception exp){
						//Alert alert = new Alert(AlertType.ERROR);
						//alert.setTitle("Units Updation : ERROR");
						//String s = "Please enter the valid no. of units to be added!";
						//alert.setContentText(s);
						//alert.showAndWait();
						u = 1;
					}
					
					int ux=s_ob1.getItemUnits();							
					ux+=u;
					double px=s_ob1.getItemPrice();							
					px=px*ux;							
					//double py=Double.valueOf(center.subtotal.getText());					
					//py=px;		
					double tx=s_ob1.getItemTaxes();							
					tx=(tx/100)*px;				
					//double ty=Double.valueOf(center.subtax.getText());						
					//ty=tx;		
					double vx=s_ob1.getItemValue();							
					vx=px+tx;					
					//double vy=Double.valueOf(center.total.getText());						
					//vy=vx;		
										
					s_ob1.setItemUnits(ux);
					s_ob1.setItemValue(vx);
			        
			        
				    table_p.getColumns().get(0).setVisible(false);
				    table_p.getColumns().get(0).setVisible(true);
				    label_num1.setText("");           

/*					String i_name="";
					String i_p;			double i_price=0;
					try{
					i_p=label_num1.getText();				
					i_price=Double.parseDouble(i_p);
					}catch(Exception exp){
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Product Insertion : ERROR");
						String s = "Please enter the amount of product to be added!";
						alert.setContentText(s);
						alert.showAndWait();					}
					
					double i_units=1.0;
					double i_taxes=8.0;
					double tax1=(i_price*i_taxes)/100;	
					tax1=(double)Math.round(tax1*100)/100;
					double i_v= i_price + tax1;
					double i_value=(double)Math.round(i_v*100)/100;
					
					data_tbl=addData(i_name, i_price, i_units, i_taxes, i_value);
					
					TableView<SalesTable> t1 = item(data_tbl);
			    	//tbl_center.setContent(t);
			    	//tbl_center.setFitToHeight(true);
			    	//tbl_center.setFitToWidth(true);
					
					center.s.setContent(t1);
					center.s.setFitToHeight(true);
					center.s.setFitToWidth(true);
					
					subtotal+=i_price;
					center.subtotal.setText(Double.toString(subtotal));
					tax+=tax1;
					center.subtax.setText(Double.toString(tax));
					total+=i_value;
					center.total.setText(Double.toString(total));
					System.out.println("Entry added!");
					label_num1.setText("");
					//start=false;
					c=0;
*/
				}
				
				if( ((Button)e.getSource()).equals(btn_multiply) ){
									}
				
				if( ((Button)e.getSource()).equals(btn_subtract) ){
					double px=s_ob1.getItemPrice();				
					int ux=s_ob1.getItemUnits();
					double tx=s_ob1.getItemTaxes();					
					double vx=s_ob1.getItemValue();
					int u=0;
					
					try{		
						String s2=label_num1.getText();
						u=Integer.parseInt(s2);
						}catch(Exception exp){
	/*						Alert alert = new Alert(AlertType.WARNING);
							alert.setTitle("Units Updation : WARNING");
							String s = "Please enter the valid no. of units to be removed!";
							alert.setContentText(s);
							alert.showAndWait();
	*/
							u=1;
						}
					
					if(ux>1 && u<ux)
					{
						ux=ux-u;
						px=px*ux;
						tx=(tx/100)*px;	
						vx=px+tx;
						s_ob1.setItemUnits(ux);
						s_ob1.setItemValue(vx);
						 table_p.getColumns().get(0).setVisible(false);
						 table_p.getColumns().get(0).setVisible(true);
						    label_num1.setText(""); 
						
					}
					else{
						Alert alert=new Alert(AlertType.WARNING);
						alert.setTitle("Unable to remove : WARNING");
						String s="There aren't sufficient units to be removed!";
						alert.setContentText(s);
						alert.showAndWait();
					}
				}
			}		}
		public Pane item_c(String name)
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
					//System.out.println("Category clicked");
					main_Controller r = new main_Controller();
					Pane m =  (Pane)category_l.lookup("#childp");
					Label l = (Label) m.lookup("#label");
					String s = l.getText();
					
					ayush=0;
					String query1 = "select id from categories where name='"+s+"';";
					 rs1 = stmt.executeQuery(query1);
					 while(rs1.next())
					 {
						prod_id = rs1.getString(1);
					 }
					 

					 //System.out.println("Id of category is:" + prod_id);
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
		
		public Pane item_p(String name)
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
			
			product_ap_listen = product_ap;

			product_ap.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event){
					
					Pane m =  (Pane)product_ap.lookup("#pane3");
					Label l = (Label) m.lookup("#label_p");
					String s = l.getText();
					//System.out.println("Feed data to table, Product clicked is:"+s);
					
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
		
		public final double getIndex() {
	        return index.get();
	    }

	    
	    public final void setIndex(Integer value) {
	        index.set(value);
	    }

	    
	    public IntegerProperty indexProperty() {
	        return index;
	    }
	    
	    public void create_payment_window(){
	    	AnchorPane pay_pane = new AnchorPane();
	    	create_payment_content(pay_pane);
	    	Scene pay_scene = new Scene(pay_pane,800,600);
	    	pay_scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	    	txt.requestFocus();
	    	pay_stage.setScene(pay_scene);
	    	//pay_stage.initModality(Modality.APPLICATION_MODAL);
	    	pay_stage.setTitle("Money");
	    	pay_stage.show();
	    	pay_stage.resizableProperty().setValue(Boolean.FALSE);
	    	pay_stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	            public void handle(WindowEvent we) {
	            	pay_stage.close();
	            	Translate tr = new Translate();
					dim_pane.getTransforms().add(tr);
					tr.setX(1500);
				}
	        });   
	    }
	    
	    public void create_payment_content(AnchorPane master){
	    	Label Amount = new Label(String.valueOf(done));
	    	Amount.setId("Amount");
	    	JFXButton pay_ok = new JFXButton("OK");
	    	master.setId("apcenter");
	    	AnchorPane heading = new AnchorPane();
	    	Label Payments = new Label("Payments");
	    	Payments.setId("ln");
	    	heading.getChildren().add(Payments);
	    	heading.setId("apcenter");
	    	heading.setTopAnchor(Payments, 10.0);
	    	heading.setLeftAnchor(Payments, 10.0);
	    	
	    	HBox keep = new HBox();
	    	AnchorPane kb = new AnchorPane();
	    	master.getChildren().add(kb);
	    	master.setRightAnchor(kb,0.0);
	    	master.setBottomAnchor(kb,200.0);
	    	master.setTopAnchor(kb,30.0);
	    	kb.setPrefWidth(350);
	    	kb.setId("apcenter");
	    	txt = new TextField();
	    	kb.getChildren().add(txt);
	    	kb.setBottomAnchor(txt,0.0);
	    	kb.setLeftAnchor(txt,80.0);
	    	//txt.setVisible(false);
	    	JFXTabPane options = new JFXTabPane();
	    	
	    	Tab Cash = new Tab();
	    	Cash.setText("Cash");
	    	options.getTabs().add(Cash);
	    	AnchorPane cash_pane = new AnchorPane();
	    	cash_pane.setId("apcenter");
	    	
	    	VBox giv_chg = new VBox();
	    	Label given = new Label("Given:");
	    	given.setId("head_pay");
	    	Label given_amt = new Label(Amount.getText());
	    	given_amt.setId("Amount_ipt");
	    	Label change = new Label("Change:");
	    	given.setId("head_pay");
	    	Label change_amt = new Label(Double.toString(Double.parseDouble(Amount.getText())-Double.parseDouble(given_amt.getText())));
	    	change_amt.setId("Amount_ipt");
	    	txt.textProperty().addListener((observable, oldValue, newValue) -> {
	    		if((txt.getText()=="")||(txt.getText()==null)||(txt.getText().trim().isEmpty())||(txt.getText().trim().equals(""))||((txt.getText().trim().compareTo(""))==0)){
	    			given_amt.textProperty().unbind();
	    			given_amt.setText(Amount.getText());	    		
	    		}
		    	else
	    		{	
	    		change_amt.setText(Double.toString(Double.parseDouble(given_amt.getText())-Double.parseDouble(Amount.getText())));
	    		given_amt.textProperty().bind(txt.textProperty());
	    		}
	    	if(Double.parseDouble(given_amt.getText())<Double.parseDouble(Amount.getText())){
	    		pay_ok.setDisable(true);
	    		change_amt.setText(Integer.toString(0));
	    	}
	    	else{
	    		change_amt.setText(Double.toString(Double.parseDouble(given_amt.getText())-Double.parseDouble(Amount.getText())));
	    		pay_ok.setDisable(false);
	    	}
	    	});
	    	Separator sep1 = new Separator();
	    	giv_chg.setSpacing(10);
	    	giv_chg.getChildren().addAll(given,given_amt,sep1,change,change_amt);
	    	cash_pane.getChildren().add(giv_chg);
	    	cash_pane.setTopAnchor(giv_chg,50.0);
	    	cash_pane.setLeftAnchor(giv_chg,60.0);
	    	cash_pane.setRightAnchor(giv_chg,60.0);
	    	Cash.setContent((AnchorPane) cash_pane);
	    	
	    	
	    	Tab Debt = new Tab();
	    	Debt.setText("Debt");
	    	Debt.setContent(new Label("Debt"));
	    	options.getTabs().add(Debt);
	    	

	    	keep.getChildren().addAll(options,kb);
	    	keep.setHgrow(options, Priority.ALWAYS);
	    	master.getChildren().addAll(Payments,keep);
	    	master.setTopAnchor(keep,40.0);
	    	master.setTopAnchor(heading,0.0);
	    	master.setLeftAnchor(keep,0.0);
	    	master.setLeftAnchor(heading,0.0);
	    	master.setRightAnchor(keep,0.0);
	    	master.setRightAnchor(heading,0.0);
	    	master.setBottomAnchor(heading,560.0);
	    	master.setBottomAnchor(keep,200.0);
	    	AnchorPane bottom_final = new AnchorPane();
	    	VBox container = new VBox();
	    	Label Final_Amount = new Label("Total Amount:");
	    	Final_Amount.setId("head_total");
	    	container.getChildren().addAll(Final_Amount,Amount);
	    	AnchorPane display = new AnchorPane();
	    	display.getChildren().addAll(container);
	    	bottom_final.setPrefHeight(200);
	    	master.getChildren().addAll(bottom_final);
	    	master.setBottomAnchor(bottom_final,0.0);
	    	master.setRightAnchor(bottom_final,0.0);
	    	master.setLeftAnchor(bottom_final,0.0);
	    	bottom_final.setId("bottom_final");
	    	bottom_final.getChildren().add(display);
	    	bottom_final.setTopAnchor(display, 60.0);
	    	bottom_final.setLeftAnchor(display, 60.0);
	    
	    	AnchorPane controls_ap = new AnchorPane();
	    	HBox controls = new HBox();
	    	controls_ap.getChildren().add(controls);
	    	pay_ok.setId("payment1");
	    	pay_ok.setPrefHeight(40);
			pay_ok.setPrefWidth(100);
			pay_ok.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e){
					do_transaction((options.getSelectionModel().getSelectedIndex()+1),Double.parseDouble(given_amt.getText()),Double.parseDouble(change_amt.getText()));
				}
			});
	    	JFXButton pay_cancel = new JFXButton("Cancel");
	    	pay_cancel.setPrefHeight(40);
			pay_cancel.setPrefWidth(100);
	    	pay_cancel.setId("payment1");
	    	pay_cancel.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event){
					pay_stage.close();
	            	Translate tr = new Translate();
					dim_pane.getTransforms().add(tr);
					tr.setX(1500);
				}
	    	});
	    	controls.setSpacing(25);
	    	controls.getChildren().addAll(pay_ok,pay_cancel);
	    	bottom_final.getChildren().add(controls_ap);
	    	bottom_final.setRightAnchor(controls_ap, 50.0);
	    	bottom_final.setTopAnchor(controls_ap, 80.0);
	    }
	    public void do_transaction(int tabno, double given, double change){
	    		if(customer_id!=null){
	    			//sale Sale = new sale(ticket_id, tabno, );
	    		}
	    		else{
	    			sale Sale = new sale(ticket_id, tabno, "Administrator",1,1);
	    			//creates object for the sale in which the customer name is not known
	    			dbfunc_other db = new dbfunc_other();
	    			ticket_conf = db.insert_5("tickets",Sale.ticket_id, String.valueOf(Sale.ticket_type), Sale.person, Sale.customer, String.valueOf(Sale.status));
	    			reciept_conf = db.insert_3("receipts",Sale.ticket_id, Sale.person,String.valueOf(done));
	    			for(int i=0;i<data_tbl.size();i++){
	    				if((db.insert_6("ticketline", Sale.ticket_id, String.valueOf(1), table_p.getItems().get(i).getItemName().toString(),table_p.getItems().get(i).getItemUnits().toString(),table_p.getItems().get(i).getItemValue().toString(),String.valueOf(1)))==1){
	    					ticketline_conf=1;
	    				}
	    				else{
	    					ticketline_conf=0;
	    					break;
	    				}
	    				//change the value of taxid in the main
	    			}
	    			payments_conf = db.insert_5("payments", Sale.ticket_id, String.valueOf(given),"good","good",String.valueOf(change));
	    			taxline_conf = db.insert_4("taxline", Sale.ticket_id, String.valueOf(subtotal), String.valueOf(1),String.valueOf(done));
	    		if(reciept_conf==1&&ticket_conf==1&&ticketline_conf==1&&payments_conf==1&&taxline_conf==1){
	    			Printer p = new Printer();
	    			if(data_tbl!=null)
	    			p.pin();
	    			Alert alert = new Alert(AlertType.INFORMATION);
	    			alert.setTitle("Information Dialog");
	    			alert.setHeaderText(null);
	    			alert.setContentText("Sale Done");
	    			alert.showAndWait();
	    			pay_stage.close();
	            	Translate tr = new Translate();
					dim_pane.getTransforms().add(tr);
					tr.setX(1500);
					dim_pane.toBack();
					data_tbl.clear();
			        try {
			        	double angle=0.0;
			        	Rotate rotate = new Rotate();
			        	iv.getTransforms().add(rotate);
			        	angle+=180.0;
			        	rotate.setAngle(angle);
			        	rotate.setPivotX(iv.getFitWidth() / 2);
			        	rotate.setPivotY(iv.getFitHeight() / 2);
			        	iv.setImage(img);
			        	side_cat.getChildren().clear();
			            catlist.getChildren().clear();
			            rs_catlist_cat = fc.fetch_cat();
			            catlist = setup(rs_catlist_cat);
			            base.getChildren().add(catlist);
			            ayush = 1;
			            child_cat=0;
			            prod_id = null;
			            content_p.getChildren().clear();

			          } catch (Exception e1) {
			            // TODO Auto-generated catch block
			            e1.printStackTrace();
			          }

	    		}
	    		else{
	    			Alert alert = new Alert(AlertType.ERROR);
	    			alert.setTitle("Error Dialog");
	    			alert.setHeaderText("System Error");
	    			alert.setContentText("Ooops, there was an error!");

	    			alert.showAndWait();
	    		}
	    		}
	    }
	    
	  //effect function for hamburger
	  		public void effect_ham(ImageView s, Pane t){
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
	  				Image img = new Image(getClass().getResource("back.png").toExternalForm());
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
	  				Image img = new Image(getClass().getResource("ham1.png").toExternalForm());
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

class final_item_list_class{
	public AnchorPane final_item_list_values(String i_name, String sprice){
    	Label item_name = new Label(i_name);
    	item_name.setPrefHeight(30);
    	item_name.setTextFill(Color.web("#ffffff"));
    	Label price = new Label(sprice);
    	price.setPrefHeight(30);
    	price.setTextFill(Color.web("#ffffff"));
    	AnchorPane newItem = new AnchorPane();
    	newItem.setPrefWidth(360);
    	newItem.setPrefHeight(30);
    	newItem.getChildren().addAll(item_name, price);
    	newItem.setLeftAnchor(item_name,1.0);
    	newItem.setRightAnchor(price,1.0);
    	item_name.setAlignment(Pos.CENTER_LEFT);
    	price.setAlignment(Pos.CENTER_RIGHT);
    	newItem.setId("newItem");
    	return newItem;
    }
}
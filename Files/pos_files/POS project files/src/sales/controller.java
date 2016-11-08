package sales;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import database.dbfunc_other;

public class controller{
	
	@FXML
	AnchorPane root;
	BorderPane root_main = new BorderPane();
	AnchorPane root_main_center = new AnchorPane();
	AnchorPane center_right = new AnchorPane();
	Stage myStage;
	double margin=0;
	AnchorPane center_left = new AnchorPane();
	HBox center = new HBox();
	AnchorPane root_main_top = new AnchorPane();
	AnchorPane lmain = new AnchorPane();
	AnchorPane rmain = new AnchorPane();
	boolean Product_flag=false;
	boolean Categories_flag=false;
	boolean Stockdiary_flag=false;
	Image pimg;
	boolean child_shift=false;
	HBox hold_search;
	AnchorPane search_main;
	boolean search_again=false;
	StackPane top = new StackPane();
	AnchorPane ap_top = new AnchorPane();
	AnchorPane ap_left_top = new AnchorPane();
	AnchorPane ap_center_top = new AnchorPane();
	AnchorPane ap_right_top = new AnchorPane();
	public static boolean j;
	public static boolean k;
	public static boolean l;
	public static boolean o;
	public static Map<String, String> data;
	String[] f1 = new String[15];
	String[] f2 = new String[7];
	String[] f3 = new String[7];
	ImageView hbg;
	@FXML
	private VBox menu;
	@FXML
	AnchorPane mbar;
	@FXML
	AnchorPane mbar_total;
	@FXML
	AnchorPane mbar_rest;
	dbfunc_other oth = new dbfunc_other();
	
	
	public void initialize(){
		mbar_total.toFront();
		root.setPrefWidth(1000);
		root.setPrefHeight(700);
		
		//creating image section
		Image img = new Image(getClass().getResource("ham1.png").toExternalForm(),35,35,false,false);
		hbg = new ImageView(img);
		hbg.setId("hbg");
		hbg.setLayoutX(10);
		hbg.setLayoutY(10);
		hbg.setFitWidth(35);
		hbg.setFitHeight(35);
		hbg.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				System.out.println("image clicked");
				effect(hbg, mbar);
			}
		});
		
		Pane headp = new Pane();
		headp.setPrefWidth(60);
		headp.setPrefHeight(60);
		headp.getChildren().add(hbg);
		headp.setId("head_pane");
		
		AnchorPane dt = new AnchorPane();
		Label dtt = new Label("Date Time");
		dt.getChildren().add(dtt);
		dt.setId("date_top");
		dt.setPrefWidth(225);
		dt.setPrefHeight(20);
		dt.setTopAnchor(dtt,0.0);
		dt.setBottomAnchor(dtt,0.0);
		dt.setLeftAnchor(dtt,0.0);
		dt.setRightAnchor(dtt,0.0);
		dtt.setAlignment(Pos.CENTER_LEFT);
		dtt.setTextFill(Color.WHITE);
		
		
		Label ttop = new Label("OpenLX Pos");
		ttop.setId("text_top");
		ttop.setTextFill(Color.WHITE);
		
		AnchorPane ver = new AnchorPane();
		Label vers = new Label("version 1.0");
		ver.getChildren().add(vers);
		ver.setId("date_top");
		ver.setPrefWidth(225);
		ver.setPrefHeight(20);
		ver.setTopAnchor(vers,0.0);
		ver.setBottomAnchor(vers,0.0);
		ver.setLeftAnchor(vers,0.0);
		ver.setRightAnchor(vers,0.0);
		vers.setAlignment(Pos.CENTER_RIGHT);
		vers.setTextFill(Color.WHITE);
		
		
		ap_top.getChildren().addAll(dt,ver);
		ap_top.setTopAnchor(ver,20.0);
		ap_top.setBottomAnchor(ver,20.0);
		ap_top.setRightAnchor(ver,15.0);
		
		ap_top.setTopAnchor(dt,15.0);
		ap_top.setBottomAnchor(dt,15.0);
		ap_top.setLeftAnchor(dt,80.0);
		
		top.getChildren().addAll(ttop,ap_top,headp);
		top.setId("top_sales");
		//top.setTopAnchor(ap_top,0.0);
		
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
		
		
		
		//borderPane config
		root_main.setPrefWidth(1000);
		root_main.setPrefHeight(700);
		root.setRightAnchor(root_main, 0.0);
		root.setLeftAnchor(root_main, 0.0);
		root.setTopAnchor(root_main, 0.0);
		root.setBottomAnchor(root_main, 0.0);
		
		//creating top pane
		root_main_top.setPrefHeight(60);
		root_main_top.setPrefWidth(root.getPrefWidth());
		root_main_top.setId("apcenter");
		
		//creating center pane
		root_main_center.setPrefHeight(640);
		root_main_center.setPrefWidth(root.getPrefWidth());
		//root_main_center.setId("apcenter");
		
		//creating content for center pane
		
		center.setSpacing(15);
		root_main_center.getChildren().add(center);
		root_main_center.setRightAnchor(center, 15.0);
		root_main_center.setLeftAnchor(center, 15.0);
		root_main_center.setBottomAnchor(center, 15.0);
		root_main_center.setTopAnchor(center, 15.0);
		//center.setId("apcenter");
		
		//adding children to hbox
		center_left.setPrefWidth(495);
		center_left.setPrefHeight(320);
		center.setHgrow(center_left, Priority.ALWAYS);
		//center_left.setId("apcenter");
		
		
		/*creating content for left pane
		JFXButton btn = new JFXButton("create right pane");
		center_left.getChildren().addAll(btn);
		btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				effect();
				
			}
		});*/
		
		center_right.setPrefWidth(495);
		center_right.setPrefHeight(320);
		center.setHgrow(center_right, Priority.ALWAYS);
		//center_right.setId("apcenter");
		center.getChildren().addAll(center_left,center_right);
		
		//drawing main scene
		drawcontent();
		
		root_main_top.getChildren().add(top);
		root_main_top.setRightAnchor(top,0.0);
		root_main_top.setLeftAnchor(top,0.0);
		root_main_top.setTopAnchor(top,0.0);
		root_main_top.setBottomAnchor(top,0.0);
		//adding content to border pane
		root_main.setTop(root_main_top);
		root_main.setCenter(root_main_center);
		
		//adding borderpane to the main pane
		root.getChildren().remove(mbar_total);
		root.getChildren().addAll(root_main,mbar_total);
		
		
		 Translate tr = new Translate();
			mbar.getTransforms().add(tr);
			tr.setX(-310);
			
			Translate tr_mbar_total = new Translate();
			mbar_total.getTransforms().add(tr_mbar_total);
			tr_mbar_total.setX(-2000);
			mbar_total.setId("tot");
			
			mbar_rest.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e){
					effect(hbg,mbar);
				}
			});
			
		
	}

	public void drawcontent(){
		AnchorPane l_main = create_left_menu();
		center_left.getChildren().add(l_main);
		center_left.setBottomAnchor(l_main,0.0);
		center_left.setTopAnchor(l_main,0.0);
		center_left.setLeftAnchor(l_main,0.0);
		center_left.setRightAnchor(l_main,0.0);
		AnchorPane r_main = create_right_menu();
		center_right.getChildren().add(r_main);
		center_right.setBottomAnchor(r_main,0.0);
		center_right.setTopAnchor(r_main,0.0);
		center_right.setLeftAnchor(r_main,0.0);
		center_right.setRightAnchor(r_main,0.0);
		//create_right_menu();
	}
	
	public AnchorPane create_left_menu(){
		lmain.setPrefHeight(320);
		lmain.setPrefWidth(495);
		lmain.setId("apcenter");
		Image img1 = new Image(getClass().getResource("ham1.png").toExternalForm(),150,150,false,false);
		create_image_head(lmain,img1);
		create_top_anchor_bar(lmain,"Stock");
		VBox list_main = new VBox();
		create_left_menu_main(list_main);
		list_main.setPrefHeight(370);
		list_main.setPrefWidth(480);
		lmain.getChildren().add(list_main);
		//lmain.setBottomAnchor(list_main,0.0);
		lmain.setLeftAnchor(list_main,25.0);
		lmain.setRightAnchor(list_main,25.0);
		lmain.setTopAnchor(list_main,250.0);
		list_main.setSpacing(10);
		return lmain;
	}
	
	public AnchorPane create_right_menu(){
		rmain.setPrefHeight(320);
		rmain.setPrefWidth(495);
		rmain.setId("apcenter");
		Image img1 = new Image(getClass().getResource("ham1.png").toExternalForm(),150,150,false,false);
		create_image_head(rmain,img1);
		create_top_anchor_bar(rmain,"Reports");
		VBox list_main = new VBox();
		create_right_menu_main(list_main);
		list_main.setPrefHeight(370);
		list_main.setPrefWidth(480);
		rmain.getChildren().add(list_main);
		rmain.setTopAnchor(list_main,250.0);
		rmain.setLeftAnchor(list_main,25.0);
		rmain.setRightAnchor(list_main,25.0);
		list_main.setSpacing(10);
		
		return rmain;
		
	}
	
	public void create_left_menu_main(VBox pane){
		VBox pd = new VBox();
		VBox ct = new VBox();
		VBox sd = new VBox();
		//Products
			Image img3 = new Image(getClass().getResource("ham1.png").toExternalForm(),45,45,false,false);
			AnchorPane Product = create_options_pane(img3, "Product");
			pd.getChildren().add(Product);
			pane.getChildren().add(pd);
			if(Product_flag==true){
				pd.setPrefHeight(150);
				Product_flag=false;
				create_product_sublist(pd,Product_flag);
				Product_flag=true;
			}
		//event handling for product
		Product.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				if(Product_flag==false&&Categories_flag==false){
					pd.setPrefHeight(150);
					create_product_sublist(pd,Product_flag);
					Product_flag=true;
				}
				else if(Product_flag==true){
					pd.setPrefHeight(50);
					Product_flag=false;
					pane.getChildren().clear();
					create_left_menu_main(pane);
				}
				else if(Categories_flag==true){
					pane.getChildren().clear();
					Product_flag=true;
					Categories_flag=false;
					create_left_menu_main(pane);
				}
			}
		});
		
		//Categories
				Image img4 = new Image(getClass().getResource("ham1.png").toExternalForm(),45,45,false,false);
				AnchorPane Categories = create_options_pane(img4, "Categories");
				ct.getChildren().add(Categories);
				pane.getChildren().add(ct);
				if(Categories_flag==true){
					ct.setPrefHeight(150);
					Categories_flag=false;
					create_category_sublist(ct,Categories_flag);
					Categories_flag=true;
				}
			//event handling for categories
				Categories.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event){
						if(Categories_flag==false&&Product_flag==false){
							ct.setPrefHeight(150);
							create_category_sublist(ct,Categories_flag);
							Categories_flag=true;
						}
						else if(Categories_flag==true){
							ct.setPrefHeight(50);
							Categories_flag=false;
							pane.getChildren().clear();
							create_left_menu_main(pane);
						}
						else if(Product_flag==true){
							pane.getChildren().clear();
							Categories_flag=true;
							Product_flag=false;
							create_left_menu_main(pane);
						}
					}
				});
		
			//Stock Diary
				Image img5 = new Image(getClass().getResource("ham1.png").toExternalForm(),45,45,false,false);
				AnchorPane Stockdiary = create_options_pane(img5, "Stock Diary");
				sd.getChildren().add(Stockdiary);
		
				
			//event handling for categories
				Stockdiary.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event){
						create_view_product_search_for_stock_diary();
					}
				});
						
				
		pane.getChildren().add(sd);
	}
	
	public void create_right_menu_main(VBox pane){
		
		//Inventory
			Image img3 = new Image(getClass().getResource("ham1.png").toExternalForm(),45,45,false,false);
			AnchorPane Inventory = create_options_pane(img3, "Inventory");
			
		//event handling for product
		Inventory.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				create_view_inventory_search();
			}
		});
		
		//Inventory Current
				Image img4 = new Image(getClass().getResource("ham1.png").toExternalForm(),45,45,false,false);
				AnchorPane Inventorycurrent = create_options_pane(img4, "Inventory Current");
				
				
			//event handling for categories
				Inventorycurrent.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event){
				
					}
				});
		
			//Report
				Image img5 = new Image(getClass().getResource("ham1.png").toExternalForm(),45,45,false,false);
				AnchorPane Report = create_options_pane(img5, "Report");
				
				
			//event handling for categories
				Report.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event){
					
					}
				});
						
				
		pane.getChildren().addAll(Inventory,Inventorycurrent,Report);
	}
	
	public void create_top_anchor_bar(AnchorPane pane, String head){
		AnchorPane top = new AnchorPane();
		top.setPrefHeight(60);
		top.setPrefWidth(495);
		pane.setTopAnchor(top,0.0);
		pane.setLeftAnchor(top,0.0);
		pane.setRightAnchor(top,0.0);
		top.setId("top");
		Label top_heading = new Label(head);
		top_heading.setPrefHeight(60);
		top_heading.setPrefWidth(495);
		top_heading.setAlignment(Pos.CENTER);
		top.getChildren().add(top_heading);
		top.setTopAnchor(top_heading,0.0);
		top.setLeftAnchor(top_heading,0.0);
		top.setRightAnchor(top_heading,0.0);
		top.setBottomAnchor(top_heading,0.0);
		top_heading.setId("head");
		pane.getChildren().add(top);
	}
	
	public void create_image_head(AnchorPane pane, Image img){
		AnchorPane main_bar = new AnchorPane();
		StackPane img_pane = new StackPane();
		main_bar.setPrefHeight(200);
		main_bar.setPrefWidth(495);
		pane.setTopAnchor(main_bar,0.0);
		pane.setLeftAnchor(main_bar,0.0);
		pane.setRightAnchor(main_bar,0.0);
		main_bar.setId("mainbar");
		ImageView icon = new ImageView();
		icon.setImage(img);
		img_pane.getChildren().add(icon);
		main_bar.getChildren().add(img_pane);
		main_bar.setBottomAnchor(img_pane,-35.0);
		main_bar.setLeftAnchor(img_pane, 0.0);
		main_bar.setRightAnchor(img_pane, 0.0);
		pane.getChildren().addAll(main_bar);
	}
	
	public AnchorPane create_options_pane(Image img, String o_name){
		AnchorPane option_pane = new AnchorPane();
		option_pane.setPrefHeight(50);
		option_pane.setPrefWidth(450);
		option_pane.setId("apcenter");
		AnchorPane image_pane = new AnchorPane();
		image_pane.setPrefHeight(50);
		image_pane.setPrefWidth(50);
		image_pane.getChildren().add(new ImageView(img));
		option_pane.getChildren().add(image_pane);
		option_pane.setTopAnchor(image_pane,2.0);
		option_pane.setLeftAnchor(image_pane,0.0);
		option_pane.setBottomAnchor(image_pane,0.0);
		AnchorPane label_pane = new AnchorPane();
		label_pane.setPrefHeight(50);
		label_pane.setPrefWidth(370);
		Label ln = new Label(o_name);
		ln.setId("ln");
		ln.setPrefWidth(370);
		ln.setPrefHeight(50);
		ln.setAlignment(Pos.CENTER_LEFT);
		label_pane.getChildren().add(ln);
		option_pane.getChildren().add(label_pane);
		option_pane.setTopAnchor(label_pane,0.0);
		option_pane.setLeftAnchor(label_pane,70.0);
		option_pane.setBottomAnchor(label_pane,0.0);
		return option_pane;
	}
	
	public AnchorPane create_suboptions_pane(String o_name){
		AnchorPane option_pane = new AnchorPane();
		option_pane.setPrefHeight(50);
		option_pane.setPrefWidth(450);
		option_pane.setId("apcenter");
		AnchorPane label_pane = new AnchorPane();
		label_pane.setPrefHeight(50);
		label_pane.setPrefWidth(370);
		Label ln = new Label(o_name);
		ln.setId("ln");
		ln.setPrefWidth(370);
		ln.setPrefHeight(50);
		ln.setAlignment(Pos.CENTER_LEFT);
		label_pane.getChildren().add(ln);
		option_pane.getChildren().add(label_pane);
		option_pane.setTopAnchor(label_pane,0.0);
		option_pane.setLeftAnchor(label_pane,70.0);
		option_pane.setBottomAnchor(label_pane,0.0);
		return option_pane;
	}
	
	public void create_product_sublist(VBox Prod, boolean Product_flag){
		
			AnchorPane AddProduct = new AnchorPane();
			AddProduct = create_suboptions_pane("Add Products");
			AddProduct.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event){
					create_add_product_pane();
				}
			});
			AnchorPane ViewProduct = new AnchorPane();
			ViewProduct = create_suboptions_pane("View Products");
			ViewProduct.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				public void handle(MouseEvent event){
					create_view_product_search();
				}
			});
		if(Product_flag==false)
		{	
			Prod.getChildren().addAll(AddProduct,ViewProduct);
		}
		else{
			Prod.getChildren().clear();//removeAll(AddProduct,ViewProduct);
		}
	}
	
	public void create_add_product_pane(){
		AnchorPane add_product_pane = new AnchorPane();
		add_product_pane.setId("apcenter");
		root_main_center.getChildren().add(add_product_pane);
		root_main_center.setTopAnchor(add_product_pane,15.0);
		root_main_center.setBottomAnchor(add_product_pane,15.0);
		root_main_center.setLeftAnchor(add_product_pane,15.0);
		root_main_center.setRightAnchor(add_product_pane,15.0);
		
		//top bar
		AnchorPane top = new AnchorPane();
		top.setPrefHeight(40);
		top.setPrefWidth(495);
		add_product_pane.setTopAnchor(top,0.0);
		add_product_pane.setLeftAnchor(top,0.0);
		add_product_pane.setRightAnchor(top,0.0);
		top.setId("top");
		Label top_heading = new Label("Add Product");
		top_heading.setPrefHeight(40);
		top_heading.setPrefWidth(495);
		top_heading.setAlignment(Pos.CENTER);
		top.getChildren().add(top_heading);
		top.setTopAnchor(top_heading,0.0);
		top.setLeftAnchor(top_heading,0.0);
		top.setRightAnchor(top_heading,0.0);
		top.setBottomAnchor(top_heading,0.0);
		top_heading.setId("head");		
		
		
		//close button
		Image img = new Image(getClass().getResource("close.png").toExternalForm(),25,25,false,false);
		ImageView close_image = new ImageView(img);
		Button close_btn = new Button("",close_image);
		close_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
			
				root_main_center.getChildren().remove(add_product_pane);
			}
		});
		close_btn.setId("closebutton");
		top.getChildren().add(close_btn);
		top.setRightAnchor(close_btn,0.0);
		top.setTopAnchor(close_btn,2.0);
		
		HBox hbox_for_form_vbox = new HBox();
		hbox_for_form_vbox.setSpacing(50);
		add_product_pane.setTopAnchor(hbox_for_form_vbox, 50.0);
		add_product_pane.setLeftAnchor(hbox_for_form_vbox, 50.0);
		add_product_pane.setBottomAnchor(hbox_for_form_vbox, 10.0);
		add_product_pane.setRightAnchor(hbox_for_form_vbox, 50.0);
		//hbox_for_form_vbox.setId("apcenter");
		//anchorpane for form section1
		AnchorPane form_section_1 = new AnchorPane();
		//form_section_1.setId("apcenter");
		AnchorPane form_section_2 = new AnchorPane();
		//form_section_2.setId("apcenter");
		AnchorPane form_section_3 = new AnchorPane();
		//form_section_3.setId("apcenter");
		
		//creating form
		VBox form = new VBox();
		hbox_for_form_vbox.setHgrow(form, Priority.ALWAYS);
		form.setSpacing(10);
		AnchorPane formfeild1 = create_form_feild("References");
		form.setVgrow(formfeild1, Priority.ALWAYS);
		AnchorPane formfeild2 = create_form_feild("Bar Code");
		form.setVgrow(formfeild2, Priority.ALWAYS);
		AnchorPane formfeild3 = create_form_feild("Name");
		form.setVgrow(formfeild3, Priority.ALWAYS);
		AnchorPane formfeild4 = create_form_feild("Category");
		form.setVgrow(formfeild4, Priority.ALWAYS);
		AnchorPane formfeild5 = create_form_feild("Tax Category");
		form.setVgrow(formfeild5, Priority.ALWAYS);
		AnchorPane formfeild6 = create_form_feild("Sell Price");
		form.setVgrow(formfeild6, Priority.ALWAYS);
		AnchorPane formfeild7 = create_form_feild("Buy Price");
		form.setVgrow(formfeild7, Priority.ALWAYS);
		AnchorPane formfeild8 = create_form_feild("Discount");
		form.setVgrow(formfeild8, Priority.ALWAYS);
		AnchorPane formfeild9 = create_form_feild("Stock");
		form.setVgrow(formfeild9, Priority.ALWAYS);
		form.getChildren().addAll(formfeild1,formfeild2,formfeild3,formfeild4,formfeild5,formfeild6,formfeild7,formfeild8,formfeild9);
		form_section_1.getChildren().add(form);
		form_section_1.setTopAnchor(form, 20.0);
		form_section_1.setBottomAnchor(form, 60.0);
		
		//CREATING FORM SECTION 2
		VBox form2 = new VBox();
		form2.setSpacing(20);
		form_section_2.getChildren().add(form2);
		form_section_2.setTopAnchor(form2, 20.0);
		form_section_2.setBottomAnchor(form2, 60.0);
			//text area
		AnchorPane area = new AnchorPane();
		JFXTextArea farea=new JFXTextArea();
		farea.setPromptText("Notes");
		farea.setPrefWidth(300);
		farea.setPrefHeight(150);
		farea.setId("ref");
		area.getChildren().add(farea);
		area.setTopAnchor(farea, 10.0);
		area.setLeftAnchor(farea, 18.0);
		area.setBottomAnchor(farea, 0.0);
		area.setRightAnchor(farea, 10.0);
		
		
		AnchorPane formfeild21 = create_form_feild("Sell Price with Taxes");
		form2.setVgrow(formfeild21, Priority.ALWAYS);
		AnchorPane formfeild22 = create_form_feild("Sell Price with Discount");
		form2.setVgrow(formfeild22, Priority.ALWAYS);
		AnchorPane formfeild23 = create_form_feild("Sell Price with Margin");
		form2.setVgrow(formfeild23, Priority.ALWAYS);
		JFXButton Save = new JFXButton("Save");
		Save.setPrefWidth(300);
		Save.setPrefHeight(50);
		Save.setId("save");
		form2.setMargin(Save, new Insets(0,0,0,18));
		Save.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				int i=0, j=0, k=0;
				outer:for (Node node : form.getChildren()) {
					if (node instanceof AnchorPane) {
						for(Node x:((AnchorPane) node).getChildren()){
							if(x instanceof JFXTextField){
								if((((JFXTextField)x).getText()!=null)&&(!((JFXTextField)x).getText().isEmpty())){
								f1[i]=((JFXTextField)x).getText();
								i++;
								}
								else{
									j=1;
									break outer;
								}
							}
						}
					}
			}
				outer:for (Node node : form2.getChildren()) {
					if (node instanceof AnchorPane) {
						for(Node x:((AnchorPane) node).getChildren()){
							if(x instanceof JFXTextField){
								if((((JFXTextField)x).getText()!=null)&&(!((JFXTextField)x).getText().isEmpty())){
								f1[i]=((JFXTextField)x).getText();
								i++;
								}
								else{
									k=1;
									break outer;
								}
							}
						}
					}
					
					if(k==1||j==1){
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("Blank Field");
						alert.setContentText("Blank Fields are not Accepted");
						alert.showAndWait();
						
					}
			}
			if(oth.insert_15("product", f1[0],f1[1],"1",f1[2],f1[6],f1[5],f1[3],f1[4],"1","1",null,f1[7],"No",f1[8],"NO")==1){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Congratulations");
				alert.setContentText("Product Added Successfully");
				alert.showAndWait();
				
				for (Node node : form.getChildren()) {
				    if (node instanceof AnchorPane) {
				    	for(Node x:((AnchorPane) node).getChildren()){
							if(x instanceof JFXTextField){
								((JFXTextField)x).setText("");
							}
				    	}
				    }
				}
				for (Node node : form2.getChildren()) {
				    if (node instanceof AnchorPane) {
				    	for(Node x:((AnchorPane) node).getChildren()){
							if(x instanceof JFXTextField){
								((JFXTextField)x).setText("");
							}
							if(x instanceof JFXTextArea){
								((JFXTextArea)x).setText("");
							}
				    	}
				    }
				}
							
			}
			      
			else{
				Alert alert = new Alert(AlertType.ERROR	);
				alert.setTitle("Error");
				alert.setHeaderText("Fatal Error");
				alert.setContentText("Something Went Wrong. Please Resubmit");
				alert.showAndWait();
			}
			}
			});
		
		JFXButton New = new JFXButton("New");
		New.setPrefWidth(300);
		New.setPrefHeight(50);
		New.setId("new");
		New.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				for (Node node : form.getChildren()) {
				    if (node instanceof AnchorPane) {
				    	for(Node x:((AnchorPane) node).getChildren()){
							if(x instanceof JFXTextField){
								((JFXTextField)x).setText("");
							}
				    	}
				    }
				}
				for (Node node : form2.getChildren()) {
				    if (node instanceof AnchorPane) {
				    	for(Node x:((AnchorPane) node).getChildren()){
							if(x instanceof JFXTextField){
								((JFXTextField)x).setText("");
							}
							if(x instanceof JFXTextArea){
								((JFXTextArea)x).setText("");
							}
				    	}
				    }
				}
			}
		});
		form2.setMargin(New, new Insets(0,0,0,18));
		form2.getChildren().addAll(area,formfeild21,formfeild22,formfeild23,Save,New);
		
		Separator vsep = new Separator();
		vsep.setOrientation(Orientation.VERTICAL);
		
		
		AnchorPane image_section = new AnchorPane();
		//image_section.setId("apcenter");
		image_section.setPrefHeight(270);
		image_section.setPrefWidth(400);
		VBox form3 = new VBox();
		
		//image display area
		pimg = new Image(getClass().getResource("ham1.png").toExternalForm(),100,100,false,false);
		ImagePattern pat = new ImagePattern(pimg);
		Circle clip = new Circle(200,120,100);
		clip.setFill(pat);
		
		StackPane prod_img_label_sp = new StackPane();
		Label prod_img = new Label("Product Image");
		prod_img.setId("ref");
		prod_img_label_sp.getChildren().add(prod_img);
		prod_img_label_sp.setAlignment(prod_img,Pos.CENTER);
		image_section.setBottomAnchor(prod_img_label_sp,10.0);
		image_section.setLeftAnchor(prod_img_label_sp,0.0);
		image_section.setRightAnchor(prod_img_label_sp,0.0);
		
		//creating image selector
		Button image_select = new Button();
		image_select.setId("image_select");
		image_select.setPrefWidth(40);
		image_select.setPrefHeight(40);
		image_section.setRightAnchor(image_select,100.0);
		image_section.setTopAnchor(image_select,150.0);
		image_select.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				FileChooser product_image = new FileChooser();
				configureFileChooser(product_image);
				product_image.setTitle("Select Product Image");
				File new_pro_image = product_image.showOpenDialog(myStage);
				if(new_pro_image!=null){
					//openFile(new_pro_image);
					System.out.println(new_pro_image);
					
					BufferedImage imga = null;
					try {
						imga = ImageIO.read(new_pro_image);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ImagePattern pat = new ImagePattern(SwingFXUtils.toFXImage(imga,null));
					clip.setFill(pat);
					
				}
				
			}
		});
		
		
		image_section.getChildren().addAll(clip,prod_img_label_sp,image_select);
		AnchorPane keyboard_section = new AnchorPane();
		//keyboard_section.setId("apcenter");
		form3.getChildren().addAll(image_section,keyboard_section);
		form3.setVgrow(image_section, Priority.ALWAYS);
		form_section_3.getChildren().add(form3);
		hbox_for_form_vbox.getChildren().addAll(form_section_1,form_section_2,vsep,form_section_3);
		add_product_pane.getChildren().addAll(top,hbox_for_form_vbox);	
	}
	
	public void create_view_product_search(){
		AnchorPane search_pane = new AnchorPane();
		create_top_anchor_bar(search_pane, "Search Product");
		search_pane.setId("apcenter");
		center_right.getChildren().add(search_pane);
		center_right.setTopAnchor(search_pane, 0.0);
		center_right.setBottomAnchor(search_pane, 0.0);
		center_right.setLeftAnchor(search_pane, 0.0);
		center_right.setRightAnchor(search_pane, 0.0);
		
		//close button
				Image img = new Image(getClass().getResource("close.png").toExternalForm(),30,30,false,false);
				ImageView close_image = new ImageView(img);
				Button close_btn = new Button("",close_image);
				close_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event){
						center_right.getChildren().remove(search_pane);
					}
				});
				close_btn.setId("closebutton");
				search_pane.getChildren().add(close_btn);
				search_pane.setRightAnchor(close_btn,3.0);
				search_pane.setTopAnchor(close_btn,8.0);
		
		AnchorPane search_bar = new AnchorPane();
		search_bar.setPrefHeight(50);
		search_bar.setPrefWidth(200);
		search_bar.setId("apcenter");
		Image search_image = new Image(getClass().getResource("ham1.png").toExternalForm(),30,30,false,false);
		ImageView search_bar_image = new ImageView();
		search_bar_image.setImage(search_image);
		search_bar.getChildren().addAll(search_bar_image);
		search_bar.setLeftAnchor(search_bar_image,10.0);
		search_bar.setTopAnchor(search_bar_image,10.0);
		search_bar.setBottomAnchor(search_bar_image,10.0);
		
		TextField search_query = new TextField();
		search_query.setPromptText("Enter Product BarCode");
		search_query.setAlignment(Pos.CENTER_LEFT);
		search_bar.getChildren().add(search_query);
		search_query.setId("search_query");
		search_bar.setTopAnchor(search_query,0.0);
		search_bar.setBottomAnchor(search_query,0.0);
		search_bar.setLeftAnchor(search_query,50.0);
		search_bar.setRightAnchor(search_query,30.0);
		
		search_pane.getChildren().add(search_bar);
		search_pane.setTopAnchor(search_bar,100.0);
		search_pane.setLeftAnchor(search_bar,60.0);
		search_pane.setRightAnchor(search_bar,60.0);
		
		JFXButton search_button = new JFXButton("Search");
		search_button.setId("new");
		search_pane.getChildren().addAll(search_button);
		search_pane.setRightAnchor(search_button,60.0);
		search_pane.setTopAnchor(search_button,180.0);
		search_button.setPrefWidth(150);
		search_button.setPrefHeight(40);
		
		search_button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				String req_prod = search_query.getText();
				if(req_prod.isEmpty()){
					Alert alert = new Alert(AlertType.ERROR	);
					alert.setTitle("Error");
					alert.setHeaderText("Empty String");
					alert.setContentText("Please Enter Product BarCode");
					alert.showAndWait();
				}
				else{
					ResultSet rs = oth.fetchn("product", "code", req_prod);
					create_view_product_pane(rs);
				}
			}
		});
	}
	
	public void create_view_product_pane(ResultSet result){
		AnchorPane add_product_pane = new AnchorPane();
		add_product_pane.setId("apcenter");
		root_main_center.getChildren().add(add_product_pane);
		root_main_center.setTopAnchor(add_product_pane,15.0);
		root_main_center.setBottomAnchor(add_product_pane,15.0);
		root_main_center.setLeftAnchor(add_product_pane,15.0);
		root_main_center.setRightAnchor(add_product_pane,15.0);
		
		//top bar
		AnchorPane top = new AnchorPane();
		top.setPrefHeight(40);
		top.setPrefWidth(495);
		add_product_pane.setTopAnchor(top,0.0);
		add_product_pane.setLeftAnchor(top,0.0);
		add_product_pane.setRightAnchor(top,0.0);
		top.setId("top");
		Label top_heading = new Label("View Product");
		top_heading.setPrefHeight(40);
		top_heading.setPrefWidth(495);
		top_heading.setAlignment(Pos.CENTER);
		top.getChildren().add(top_heading);
		top.setTopAnchor(top_heading,0.0);
		top.setLeftAnchor(top_heading,0.0);
		top.setRightAnchor(top_heading,0.0);
		top.setBottomAnchor(top_heading,0.0);
		top_heading.setId("head");		
		
		//close button
		Image img = new Image(getClass().getResource("close.png").toExternalForm(),25,25,false,false);
		ImageView close_image = new ImageView(img);
		Button close_btn = new Button("",close_image);
		close_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
			
				root_main_center.getChildren().remove(add_product_pane);
			}
		});
		close_btn.setId("closebutton");
		top.getChildren().add(close_btn);
		top.setRightAnchor(close_btn,0.0);
		top.setTopAnchor(close_btn,2.0);
		
		HBox hbox_for_form_vbox = new HBox();
		hbox_for_form_vbox.setSpacing(50);
		add_product_pane.setTopAnchor(hbox_for_form_vbox, 50.0);
		add_product_pane.setLeftAnchor(hbox_for_form_vbox, 50.0);
		add_product_pane.setBottomAnchor(hbox_for_form_vbox, 10.0);
		add_product_pane.setRightAnchor(hbox_for_form_vbox, 50.0);
		//hbox_for_form_vbox.setId("apcenter");
		//anchorpane for form section1
		AnchorPane form_section_1 = new AnchorPane();
		//form_section_1.setId("apcenter");
		AnchorPane form_section_2 = new AnchorPane();
		//form_section_2.setId("apcenter");
		AnchorPane form_section_3 = new AnchorPane();
		//form_section_3.setId("apcenter");
		try{
			result.next();
		//creating form
		VBox form = new VBox();
		hbox_for_form_vbox.setHgrow(form, Priority.ALWAYS);
		form.setSpacing(10);
		AnchorPane formfeild1 = show_form_feild(result.getString(1));
		form.setVgrow(formfeild1, Priority.ALWAYS);
		AnchorPane formfeild2 = show_form_feild(result.getString(2));
		form.setVgrow(formfeild2, Priority.ALWAYS);
		AnchorPane formfeild3 = show_form_feild(result.getString(4));
		form.setVgrow(formfeild3, Priority.ALWAYS);
		AnchorPane formfeild4 = show_form_feild(result.getString(7));
		form.setVgrow(formfeild4, Priority.ALWAYS);
		AnchorPane formfeild5 = show_form_feild(result.getString(8));
		form.setVgrow(formfeild5, Priority.ALWAYS);
		AnchorPane formfeild6 = show_form_feild(result.getString(6));
		form.setVgrow(formfeild6, Priority.ALWAYS);
		AnchorPane formfeild7 = show_form_feild(result.getString(5));
		form.setVgrow(formfeild7, Priority.ALWAYS);
		AnchorPane formfeild8 = show_form_feild(result.getString(12));
		form.setVgrow(formfeild8, Priority.ALWAYS);
		AnchorPane formfeild9 = show_form_feild(result.getString(14));
		form.setVgrow(formfeild9, Priority.ALWAYS);
		form.getChildren().addAll(formfeild1,formfeild2,formfeild3,formfeild4,formfeild5,formfeild6,formfeild7,formfeild8,formfeild9);
		form_section_1.getChildren().add(form);
		form_section_1.setTopAnchor(form, 20.0);
		form_section_1.setBottomAnchor(form, 60.0);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		//CREATING FORM SECTION 2
		VBox form2 = new VBox();
		form2.setSpacing(20);
		form_section_2.getChildren().add(form2);
		form_section_2.setTopAnchor(form2, 20.0);
		form_section_2.setBottomAnchor(form2, 60.0);
			//text area
		AnchorPane area = new AnchorPane();
		JFXTextArea farea=new JFXTextArea();
		farea.setPromptText("Notes");
		farea.setPrefWidth(300);
		farea.setPrefHeight(150);
		farea.setId("ref");
		farea.setDisable(true);
		area.getChildren().add(farea);
		area.setTopAnchor(farea, 10.0);
		area.setLeftAnchor(farea, 18.0);
		area.setBottomAnchor(farea, 0.0);
		area.setRightAnchor(farea, 10.0);
		
		
		AnchorPane formfeild21 = show_form_feild("Sell Price with Taxes");
		form2.setVgrow(formfeild21, Priority.ALWAYS);
		AnchorPane formfeild22 = show_form_feild("Sell Price with Discount");
		form2.setVgrow(formfeild22, Priority.ALWAYS);
		AnchorPane formfeild23 = show_form_feild("Sell Price with Margin");
		form2.setVgrow(formfeild23, Priority.ALWAYS);
		JFXButton Edit = new JFXButton("Edit");
		Edit.setPrefWidth(300);
		Edit.setPrefHeight(50);
		Edit.setId("edit");
		form2.setMargin(Edit, new Insets(0,0,0,18));
		JFXButton Delete = new JFXButton("Delete");
		Delete.setPrefWidth(300);
		Delete.setPrefHeight(50);
		Delete.setId("delete");
		form2.setMargin(Delete, new Insets(0,0,0,18));
		form2.getChildren().addAll(area,formfeild21,formfeild22,formfeild23,Edit,Delete);
		
		Separator vsep = new Separator();
		vsep.setOrientation(Orientation.VERTICAL);
		
		
		AnchorPane image_section = new AnchorPane();
		//image_section.setId("apcenter");
		image_section.setPrefHeight(270);
		image_section.setPrefWidth(400);
		VBox form3 = new VBox();
		
		//image display area
		pimg = new Image(getClass().getResource("ham1.png").toExternalForm(),100,100,false,false);
		ImagePattern pat = new ImagePattern(pimg);
		Circle clip = new Circle(200,120,100);
		clip.setFill(pat);
		
		StackPane prod_img_label_sp = new StackPane();
		Label prod_img = new Label("Product Image");
		prod_img.setId("ref");
		prod_img_label_sp.getChildren().add(prod_img);
		prod_img_label_sp.setAlignment(prod_img,Pos.CENTER);
		image_section.setBottomAnchor(prod_img_label_sp,10.0);
		image_section.setLeftAnchor(prod_img_label_sp,0.0);
		image_section.setRightAnchor(prod_img_label_sp,0.0);
		
		//creating image selector
				
		image_section.getChildren().addAll(clip,prod_img_label_sp);
		AnchorPane keyboard_section = new AnchorPane();
		//keyboard_section.setId("apcenter");
		form3.getChildren().addAll(image_section,keyboard_section);
		form3.setVgrow(image_section, Priority.ALWAYS);
		form_section_3.getChildren().add(form3);
		hbox_for_form_vbox.getChildren().addAll(form_section_1,form_section_2,vsep,form_section_3);
		add_product_pane.getChildren().addAll(top,hbox_for_form_vbox);
	}
	
	public static void configureFileChooser(
	        final FileChooser fileChooser) {      
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
            new File(System.getProperty("user.home"))
        );                 
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
	}
	
	public void create_category_sublist(VBox Prod, boolean Product_flag){
		
		AnchorPane Createcategory = new AnchorPane();
		Createcategory = create_suboptions_pane("Create Category");
		Createcategory.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				create_add_category_pane();
			}
		});
		AnchorPane Viewcategory = new AnchorPane();
		Viewcategory = create_suboptions_pane("View Category");
		Viewcategory.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				create_view_category_search();
			}
		});
	if(Product_flag==false)
	{	
		Prod.getChildren().addAll(Createcategory,Viewcategory);
	}
	else{
		Prod.getChildren().clear();//removeAll(AddProduct,ViewProduct);
	}
	}
	
	public void create_add_category_pane(){
		AnchorPane add_product_pane = new AnchorPane();
		add_product_pane.setId("apcenter");
		center_right.getChildren().add(add_product_pane);
		center_right.setTopAnchor(add_product_pane,0.0);
		center_right.setBottomAnchor(add_product_pane,0.0);
		center_right.setLeftAnchor(add_product_pane,0.0);
		center_right.setRightAnchor(add_product_pane,0.0);
		
		//top bar
		AnchorPane top = new AnchorPane();
		top.setPrefHeight(40);
		top.setPrefWidth(495);
		add_product_pane.setTopAnchor(top,0.0);
		add_product_pane.setLeftAnchor(top,0.0);
		add_product_pane.setRightAnchor(top,0.0);
		top.setId("top");
		Label top_heading = new Label("Add Category");
		top_heading.setPrefHeight(40);
		top_heading.setPrefWidth(495);
		top_heading.setAlignment(Pos.CENTER);
		top.getChildren().add(top_heading);
		top.setTopAnchor(top_heading,0.0);
		top.setLeftAnchor(top_heading,0.0);
		top.setRightAnchor(top_heading,0.0);
		top.setBottomAnchor(top_heading,0.0);
		top_heading.setId("head");		
		
		
		//close button
		Image img_cl = new Image(getClass().getResource("close.png").toExternalForm(),25,25,false,false);
		ImageView close_image = new ImageView(img_cl);
		Button close_btn = new Button("",close_image);
		close_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				center_right.getChildren().remove(add_product_pane);
			}
		});
		close_btn.setId("closebutton");
		top.getChildren().add(close_btn);
		top.setLeftAnchor(close_btn,0.0);
		top.setTopAnchor(close_btn,2.0);
		
		//Save button
				Image img_sv = new Image(getClass().getResource("close.png").toExternalForm(),25,25,false,false);
				ImageView save_image = new ImageView(img_sv);
				Button save_btn = new Button("",save_image);
				
		save_btn.setId("closebutton");
		top.getChildren().add(save_btn);
		top.setRightAnchor(save_btn,0.0);
		top.setTopAnchor(save_btn,2.0);
				
		VBox Vbox_for_form_vbox = new VBox();
		Vbox_for_form_vbox.setSpacing(50);
		add_product_pane.setTopAnchor(Vbox_for_form_vbox, 50.0);
		add_product_pane.setLeftAnchor(Vbox_for_form_vbox, 50.0);
		add_product_pane.setBottomAnchor(Vbox_for_form_vbox, 10.0);
		add_product_pane.setRightAnchor(Vbox_for_form_vbox, 50.0);
		//hbox_for_form_vbox.setId("apcenter");
		//anchorpane for form section1
		AnchorPane form_section_1 = new AnchorPane();
		
		//creating form
		VBox form = new VBox();
		Vbox_for_form_vbox.setVgrow(form, Priority.ALWAYS);
		form.setSpacing(10);
		AnchorPane formfeild1 = create_form_feild("Name");
		form.setVgrow(formfeild1, Priority.ALWAYS);
		AnchorPane formfeild2 = create_form_feild("Category");
		form.setVgrow(formfeild2, Priority.ALWAYS);
		AnchorPane formfeild3 = create_form_feild("Text Tip Message");
		form.setVgrow(formfeild3, Priority.ALWAYS);
		JFXCheckBox cb = new JFXCheckBox("In Catelogue");
		form.setMargin(cb, new Insets(15,0,0,10));
		form.getChildren().addAll(formfeild1,formfeild2,formfeild3,cb);
		form_section_1.getChildren().add(form);
		form_section_1.setTopAnchor(form, 20.0);
		form_section_1.setBottomAnchor(form, 10.0);
		form_section_1.setRightAnchor(form,0.0);
		form_section_1.setLeftAnchor(form,0.0);
		AnchorPane kb = new AnchorPane();
		kb.setPrefHeight(300);
		kb.setId("apcenter");
		
		save_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				int i=0, j=0;
				outer:for (Node node : form.getChildren()) {
					if (node instanceof AnchorPane) {
						for(Node x:((AnchorPane) node).getChildren()){
							if(x instanceof JFXTextField){
								if((((JFXTextField)x).getText()!=null)&&(!((JFXTextField)x).getText().isEmpty())){
								f2[i]=((JFXTextField)x).getText();
								i++;
								}
								else{
									j=1;
									break outer;
								}
							}
						}
					}
			}
				if(j==1){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Blank Field");
					alert.setContentText("Blank Fields are not Accepted");
					alert.showAndWait();
					
				}
				
			if((oth.insert_4cat("categories", f2[0],"true","default",f2[1])==1)&&j==0){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Congratulations");
				alert.setContentText("Category Added Successfully");
				alert.showAndWait();
				
				for (Node node : form.getChildren()) {
				    if (node instanceof AnchorPane) {
				    	for(Node x:((AnchorPane) node).getChildren()){
							if(x instanceof JFXTextField){
								((JFXTextField)x).setText("");
							}
				    	}
				    }
				}			
			}
			      
			else{
				Alert alert = new Alert(AlertType.ERROR	);
				alert.setTitle("Error");
				alert.setHeaderText("Fatal Error");
				alert.setContentText("Something Went Wrong. Please Resubmit");
				alert.showAndWait();
			}
			}
		});
		
		Vbox_for_form_vbox.getChildren().addAll(form_section_1,kb);
		add_product_pane.getChildren().addAll(top,Vbox_for_form_vbox);	
	}
	
	public void create_view_category_search(){
		AnchorPane search_pane = new AnchorPane();
		create_top_anchor_bar(search_pane, "Search Category");
		search_pane.setId("apcenter");
		center_right.getChildren().add(search_pane);
		center_right.setTopAnchor(search_pane, 0.0);
		center_right.setBottomAnchor(search_pane, 0.0);
		center_right.setLeftAnchor(search_pane, 0.0);
		center_right.setRightAnchor(search_pane, 0.0);
		
		//close button
				Image img = new Image(getClass().getResource("close.png").toExternalForm(),30,30,false,false);
				ImageView close_image = new ImageView(img);
				Button close_btn = new Button("",close_image);
				close_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event){
					
						center_right.getChildren().remove(search_pane);
					}
				});
				close_btn.setId("closebutton");
				search_pane.getChildren().add(close_btn);
				search_pane.setRightAnchor(close_btn,3.0);
				search_pane.setTopAnchor(close_btn,8.0);
		
		AnchorPane search_bar = new AnchorPane();
		search_bar.setPrefHeight(50);
		search_bar.setPrefWidth(200);
		search_bar.setId("apcenter");
		Image search_image = new Image(getClass().getResource("ham1.png").toExternalForm(),30,30,false,false);
		ImageView search_bar_image = new ImageView();
		search_bar_image.setImage(search_image);
		search_bar.getChildren().addAll(search_bar_image);
		search_bar.setLeftAnchor(search_bar_image,10.0);
		search_bar.setTopAnchor(search_bar_image,10.0);
		search_bar.setBottomAnchor(search_bar_image,10.0);
		
		TextField search_query = new TextField();
		search_query.setPromptText("Enter Category Id");
		search_query.setAlignment(Pos.CENTER_LEFT);
		search_bar.getChildren().add(search_query);
		search_query.setId("search_query");
		search_bar.setTopAnchor(search_query,0.0);
		search_bar.setBottomAnchor(search_query,0.0);
		search_bar.setLeftAnchor(search_query,50.0);
		search_bar.setRightAnchor(search_query,30.0);
		
		search_pane.getChildren().add(search_bar);
		search_pane.setTopAnchor(search_bar,100.0);
		search_pane.setLeftAnchor(search_bar,60.0);
		search_pane.setRightAnchor(search_bar,60.0);
		
		JFXButton search_button = new JFXButton("Search");
		search_button.setId("new");
		search_pane.getChildren().addAll(search_button);
		search_pane.setRightAnchor(search_button,60.0);
		search_pane.setTopAnchor(search_button,180.0);
		search_button.setPrefWidth(150);
		search_button.setPrefHeight(40);
		
		search_button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				String req_cat = search_query.getText();
				if(req_cat.isEmpty()){
					Alert alert = new Alert(AlertType.ERROR	);
					alert.setTitle("Error");
					alert.setHeaderText("Empty String");
					alert.setContentText("Please Enter Category Id");
					alert.showAndWait();
				}
				else{
					ResultSet rsc = oth.fetchn("categories", "id", req_cat);
					create_view_category_pane(rsc);
				}
			}
		});
	}
	
	public void create_view_category_pane(ResultSet rsc){
		AnchorPane add_product_pane = new AnchorPane();
		add_product_pane.setId("apcenter");
		center_right.getChildren().add(add_product_pane);
		center_right.setTopAnchor(add_product_pane,0.0);
		center_right.setBottomAnchor(add_product_pane,0.0);
		center_right.setLeftAnchor(add_product_pane,0.0);
		center_right.setRightAnchor(add_product_pane,0.0);
		
		//top bar
		AnchorPane top = new AnchorPane();
		top.setPrefHeight(40);
		top.setPrefWidth(495);
		add_product_pane.setTopAnchor(top,0.0);
		add_product_pane.setLeftAnchor(top,0.0);
		add_product_pane.setRightAnchor(top,0.0);
		top.setId("top");
		Label top_heading = new Label("View Category");
		top_heading.setPrefHeight(40);
		top_heading.setPrefWidth(495);
		top_heading.setAlignment(Pos.CENTER);
		top.getChildren().add(top_heading);
		top.setTopAnchor(top_heading,0.0);
		top.setLeftAnchor(top_heading,0.0);
		top.setRightAnchor(top_heading,0.0);
		top.setBottomAnchor(top_heading,0.0);
		top_heading.setId("head");		
		
		
		//close button
		Image img_cl = new Image(getClass().getResource("close.png").toExternalForm(),25,25,false,false);
		ImageView close_image = new ImageView(img_cl);
		Button close_btn = new Button("",close_image);
		close_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				center_right.getChildren().remove(add_product_pane);
			}
		});
		close_btn.setId("closebutton");
		top.getChildren().add(close_btn);
		top.setLeftAnchor(close_btn,0.0);
		top.setTopAnchor(close_btn,2.0);
				
		VBox Vbox_for_form_vbox = new VBox();
		Vbox_for_form_vbox.setSpacing(50);
		add_product_pane.setTopAnchor(Vbox_for_form_vbox, 50.0);
		add_product_pane.setLeftAnchor(Vbox_for_form_vbox, 50.0);
		add_product_pane.setBottomAnchor(Vbox_for_form_vbox, 10.0);
		add_product_pane.setRightAnchor(Vbox_for_form_vbox, 50.0);
		//hbox_for_form_vbox.setId("apcenter");
		//anchorpane for form section1
		AnchorPane form_section_1 = new AnchorPane();
		
		//creating form
		try{
		rsc.next();
		VBox form = new VBox();
		Vbox_for_form_vbox.setVgrow(form, Priority.ALWAYS);
		form.setSpacing(10);
		AnchorPane formfeild1 = show_form_feild(rsc.getString(1));
		form.setVgrow(formfeild1, Priority.ALWAYS);
		AnchorPane formfeild2 = show_form_feild(rsc.getString(4));
		form.setVgrow(formfeild2, Priority.ALWAYS);
		AnchorPane formfeild3 = show_form_feild("Text Tip Message");
		form.setVgrow(formfeild3, Priority.ALWAYS);
		JFXCheckBox cb = new JFXCheckBox("In Catelogue");
		form.setMargin(cb, new Insets(15,0,0,10));
		form_section_1.getChildren().add(form);
		form_section_1.setTopAnchor(form, 20.0);
		form_section_1.setBottomAnchor(form, 10.0);
		form_section_1.setRightAnchor(form,0.0);
		form_section_1.setLeftAnchor(form,0.0);
		
		JFXButton Edit = new JFXButton("Edit");
		Edit.setPrefWidth(150);
		Edit.setPrefHeight(40);
		Edit.setId("edit");
		Vbox_for_form_vbox.setMargin(Edit, new Insets(35,0,0,10));
		JFXButton Delete = new JFXButton("Delete");
		Delete.setPrefWidth(150);
		Delete.setPrefHeight(40);
		Delete.setId("delete");
		Vbox_for_form_vbox.setMargin(Delete, new Insets(-50,0,0,170));
		form.getChildren().addAll(formfeild1,formfeild2,formfeild3,cb,Edit,Delete);
		Vbox_for_form_vbox.getChildren().addAll(form_section_1);
		add_product_pane.getChildren().addAll(top,Vbox_for_form_vbox);	
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		}
	
	public AnchorPane create_form_feild(String feildName){
		AnchorPane feild = new AnchorPane();
		JFXTextField ffeild=new JFXTextField();
		ffeild.setPromptText(feildName);
		ffeild.setPrefWidth(300);
		ffeild.setPrefHeight(30);
		ffeild.setId("ref");
		feild.getChildren().add(ffeild);
		feild.setTopAnchor(ffeild, 10.0);
		feild.setLeftAnchor(ffeild, 10.0);
		feild.setBottomAnchor(ffeild, 0.0);
		feild.setRightAnchor(ffeild, 0.0);
		return feild;
	}
	
	public AnchorPane show_form_feild(String feildName){
		AnchorPane feild = new AnchorPane();
		Label ffeild=new Label(feildName);
		ffeild.setPrefWidth(300);
		ffeild.setPrefHeight(30);
		ffeild.setId("ref");
		feild.getChildren().add(ffeild);
		feild.setTopAnchor(ffeild, 10.0);
		feild.setLeftAnchor(ffeild, 10.0);
		feild.setBottomAnchor(ffeild, 0.0);
		feild.setRightAnchor(ffeild, 0.0);
		return feild;
	}
	
	public void create_view_product_search_for_stock_diary(){
		AnchorPane search_pane = new AnchorPane();
		create_top_anchor_bar(search_pane, "Stock Diary");
		search_pane.setId("apcenter");
		center_right.getChildren().add(search_pane);
		center_right.setTopAnchor(search_pane, 0.0);
		center_right.setBottomAnchor(search_pane, 0.0);
		center_right.setLeftAnchor(search_pane, 0.0);
		center_right.setRightAnchor(search_pane, 0.0);
		
		//close button
				Image img = new Image(getClass().getResource("close.png").toExternalForm(),30,30,false,false);
				ImageView close_image = new ImageView(img);
				Button close_btn = new Button("",close_image);
				close_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event){
						if(child_shift){
							root_main_center.getChildren().remove(search_pane);
							child_shift=false;
						}
						else{
							center_right.getChildren().remove(search_pane);
						}
					}
				});
				close_btn.setId("closebutton");
				
		hold_search = new HBox();		
		//hold_search.setId("apcenter");
		search_main = new AnchorPane();
		hold_search.getChildren().add(search_main);
		hold_search.setHgrow(search_main, Priority.ALWAYS);
		//search_main.setId("apcenter");
		AnchorPane search_bar = new AnchorPane();
		search_bar.setPrefHeight(50);
		search_bar.setPrefWidth(200);
		search_bar.setId("apcenter");
		Image search_image = new Image(getClass().getResource("ham1.png").toExternalForm(),30,30,false,false);
		ImageView search_bar_image = new ImageView();
		search_bar_image.setImage(search_image);
		search_bar.getChildren().addAll(search_bar_image);
		search_bar.setLeftAnchor(search_bar_image,10.0);
		search_bar.setTopAnchor(search_bar_image,10.0);
		search_bar.setBottomAnchor(search_bar_image,10.0);
		
		TextField search_query = new TextField();
		search_query.setPromptText("Enter Product BarCode");
		search_query.setAlignment(Pos.CENTER_LEFT);
		search_bar.getChildren().add(search_query);
		search_query.setId("search_query");
		search_bar.setTopAnchor(search_query,0.0);
		search_bar.setBottomAnchor(search_query,0.0);
		search_bar.setLeftAnchor(search_query,50.0);
		search_bar.setRightAnchor(search_query,30.0);
		
		search_main.getChildren().add(search_bar);
		search_main.setTopAnchor(search_bar,0.0);
		search_main.setLeftAnchor(search_bar,60.0);
		search_main.setRightAnchor(search_bar,60.0);
		
		JFXButton search_button = new JFXButton("Search");
		search_button.setId("new");
		search_main.getChildren().addAll(search_button);
		search_main.setRightAnchor(search_button,60.0);
		search_main.setTopAnchor(search_button,70.0);
		search_button.setPrefWidth(150);
		search_button.setPrefHeight(40);
		
		search_pane.getChildren().add(hold_search);
		search_pane.setRightAnchor(hold_search,0.0);
		search_pane.setLeftAnchor(hold_search,0.0);
		search_pane.setTopAnchor(hold_search,100.0);
		search_pane.setBottomAnchor(hold_search,00.0);
		search_pane.getChildren().add(close_btn);
		search_pane.setRightAnchor(close_btn,3.0);
		search_pane.setTopAnchor(close_btn,8.0);

		search_button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				String req_prod = search_query.getText();
				if(req_prod.isEmpty()){
					Alert alert = new Alert(AlertType.ERROR	);
					alert.setTitle("Error");
					alert.setHeaderText("Empty String");
					alert.setContentText("Please Enter Product Barcode");
					alert.showAndWait();
				}
				else{
					ResultSet rssc = oth.fetchn("product", "code", req_prod);
				
				if(!child_shift){
					child_shift = true;
					center_right.getChildren().remove(search_pane);
					root_main_center.getChildren().add(search_pane);
					root_main_center.setTopAnchor(search_pane,15.0);
					root_main_center.setBottomAnchor(search_pane,15.0);
					root_main_center.setLeftAnchor(search_pane,15.0);
					root_main_center.setRightAnchor(search_pane,15.0);
					show_search_result(search_main, rssc);
					search_again=true;
				}
				else if(search_again){
					show_search_result(search_main, rssc);
				}
			}}
		});
	}
	
	public void show_search_result(AnchorPane p, ResultSet rssc){
		AnchorPane results = new AnchorPane();
		results.setId("apcenter");
		hold_search.getChildren().clear();
		hold_search.getChildren().addAll(results,p);
		hold_search.setHgrow(results, Priority.ALWAYS);
		hold_search.setHgrow(p, Priority.ALWAYS);
		hold_search.setMargin(results, new Insets(0,0,35,35));
		VBox res_value = new VBox();
		res_value.setSpacing(10);
		try{
		rssc.next();
		AnchorPane res_val1 = create_result_value(rssc.getString(4),res_value, rssc);
		res_value.getChildren().addAll(res_val1);
		results.getChildren().add(res_value);
		results.setTopAnchor(res_value,10.0);
		results.setLeftAnchor(res_value,30.0);
		results.setRightAnchor(res_value,30.0);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
	}
	
	public AnchorPane create_result_value(String Cat,VBox host, ResultSet rssc){
		AnchorPane res = new AnchorPane();
		res = create_suboptions_pane(Cat);
		res.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				create_update_stock_pane(Cat,host,rssc);
			}
		});
		return res;
	}
	
	public void create_update_stock_pane(String cat, VBox host, ResultSet rssc){
		host.getChildren().clear();
		host.setPrefWidth(400);
		try{
		AnchorPane feild1 = show_form_feild("Date");
		host.getChildren().add(feild1);
		AnchorPane feild2 = show_form_feild(rssc.getString(4));
		host.getChildren().add(feild2);
		AnchorPane feild3 = show_form_feild(rssc.getString(2));
		host.getChildren().add(feild3);
		AnchorPane feild4 = show_form_feild(rssc.getString(1));
		host.getChildren().add(feild4);
		AnchorPane feild5 = create_form_feild("Units");
		host.getChildren().add(feild5);
		AnchorPane feild6 = create_form_feild("Price");
		host.getChildren().add(feild6);
		AnchorPane feild7 = create_form_feild("Reason");
		host.getChildren().add(feild7);
		AnchorPane feild8 = create_form_feild("Discount");
		host.getChildren().add(feild8);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		JFXButton submit = new JFXButton("Submit");
		host.getChildren().add(submit);
		submit.setId("edit");
		submit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				int i=0, j=0;
				outer:for (Node node : host.getChildren()) {
					if (node instanceof AnchorPane) {
						for(Node x:((AnchorPane) node).getChildren()){
							if(x instanceof JFXTextField){
								if((((JFXTextField)x).getText()!=null)&&(!((JFXTextField)x).getText().isEmpty())){
								f3[i]=((JFXTextField)x).getText();
								i++;
								}
								else{
									j=1;
									break outer;
								}
							}
						}
					}
			}
				if(j==1){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Blank Field");
					alert.setContentText("Blank Fields are not Accepted");
					alert.showAndWait();
					
				}
			try {
				int uts = Integer.parseInt(f3[0])+Integer.parseInt(oth.fetch1("stockunit", "product","code",rssc.getString(2)));
				System.out.println(Integer.parseInt(f3[0]));
				System.out.println(Integer.parseInt(oth.fetch1("stockunit", "product","code",rssc.getString(2))));
				System.out.println(uts);
				if((oth.insert_4("stockdiary",oth.fetch1("id", "product","code", rssc.getString(2)),f3[0],"Administrator", String.valueOf(0))==1)&&(j==0)){
					oth.update_tbl("product","stockunit",String.valueOf(uts),"code",rssc.getString(2));
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setHeaderText("Congratulations");
					alert.setContentText("Stock Diary Updated");
					alert.showAndWait();
					
					for (Node node : host.getChildren()) {
					    if (node instanceof AnchorPane) {
					    	for(Node x:((AnchorPane) node).getChildren()){
								if(x instanceof JFXTextField){
									((JFXTextField)x).setText("");
								}
					    	}
					    }
					}			
				}
				      
				else{
					Alert alert = new Alert(AlertType.ERROR	);
					alert.setTitle("Error");
					alert.setHeaderText("Fatal Error");
					alert.setContentText("Something Went Wrong. Please Resubmit");
					alert.showAndWait();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		});
		search_again=true;
	}
	

	public void create_view_inventory_search(){
		AnchorPane search_pane = new AnchorPane();
		create_top_anchor_bar(search_pane, "Inventory Report");
		search_pane.setId("apcenter");
		center_left.getChildren().add(search_pane);
		center_left.setTopAnchor(search_pane, 0.0);
		center_left.setBottomAnchor(search_pane, 0.0);
		center_left.setLeftAnchor(search_pane, 0.0);
		center_left.setRightAnchor(search_pane, 0.0);
		
		//close button
				Image img = new Image(getClass().getResource("close.png").toExternalForm(),30,30,false,false);
				ImageView close_image = new ImageView(img);
				Button close_btn = new Button("",close_image);
				close_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event){
					
						center_left.getChildren().remove(search_pane);
					}
				});
				close_btn.setId("closebutton");
				search_pane.getChildren().add(close_btn);
				search_pane.setLeftAnchor(close_btn,3.0);
				search_pane.setTopAnchor(close_btn,8.0);
		
		AnchorPane search_bar = new AnchorPane();
		search_bar.setPrefHeight(50);
		search_bar.setPrefWidth(200);
		search_bar.setId("apcenter");
		Image search_image = new Image(getClass().getResource("ham1.png").toExternalForm(),30,30,false,false);
		ImageView search_bar_image = new ImageView();
		search_bar_image.setImage(search_image);
		search_bar.getChildren().addAll(search_bar_image);
		search_bar.setLeftAnchor(search_bar_image,10.0);
		search_bar.setTopAnchor(search_bar_image,10.0);
		search_bar.setBottomAnchor(search_bar_image,10.0);
		
		TextField search_query = new TextField();
		search_query.setPromptText("Enter Product Id");
		search_query.setAlignment(Pos.CENTER_LEFT);
		search_bar.getChildren().add(search_query);
		search_query.setId("search_query");
		search_bar.setTopAnchor(search_query,0.0);
		search_bar.setBottomAnchor(search_query,0.0);
		search_bar.setLeftAnchor(search_query,50.0);
		search_bar.setRightAnchor(search_query,30.0);
		
		search_pane.getChildren().add(search_bar);
		search_pane.setTopAnchor(search_bar,100.0);
		search_pane.setLeftAnchor(search_bar,60.0);
		search_pane.setRightAnchor(search_bar,60.0);
		
		JFXButton search_button = new JFXButton("Search");
		search_button.setId("new");
		search_pane.getChildren().addAll(search_button);
		search_pane.setRightAnchor(search_button,60.0);
		search_pane.setTopAnchor(search_button,180.0);
		search_button.setPrefWidth(150);
		search_button.setPrefHeight(40);
		
		search_button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				//form validation code here
				create_view_inventory_report_pane();
				
			}
		});
	}
	
	public void create_view_inventory_report_pane(){
		AnchorPane report_pane = new AnchorPane();
		create_top_anchor_bar(report_pane, "Inventory Report");
		report_pane.setId("apcenter");
		center_right.getChildren().add(report_pane);
		center_right.setTopAnchor(report_pane, 0.0);
		center_right.setBottomAnchor(report_pane, 0.0);
		center_right.setLeftAnchor(report_pane, 0.0);
		center_right.setRightAnchor(report_pane, 0.0);
		
		//close button
				Image img = new Image(getClass().getResource("close.png").toExternalForm(),30,30,false,false);
				ImageView close_image = new ImageView(img);
				Button close_btn = new Button("",close_image);
				close_btn.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event){
					
						center_right.getChildren().remove(report_pane);
					}
				});
				close_btn.setId("closebutton");
				report_pane.getChildren().add(close_btn);
				report_pane.setRightAnchor(close_btn,3.0);
				report_pane.setTopAnchor(close_btn,8.0);
		
		
	}
	
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
													try{
														main_Window.page2 = (AnchorPane) FXMLLoader.load(getClass().getResource("/register/edit_sales_test.fxml"));
														if(main_Window.primaryStage1.getScene().getRoot()!=main_Window.scene2.getRoot()){
														main_Window.scene2.setRoot(main_Window.page2);
														main_Window.primaryStage1.setScene(main_Window.scene2);
														main_Window.scene2.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
														main_Window.scene2.getStylesheets().add(getClass().getResource("ham.css").toExternalForm());
														main_Window.primaryStage1.show();	
														effect(hbg,mbar);
														System.out.println(sales.main_Window.i+" g!=0");
													}
													}
														catch(Exception e){
															main_Window.scene2 = new Scene(main_Window.page2,main_Window.primaryStage1.getWidth(),main_Window.primaryStage1.getHeight());
															main_Window.primaryStage1.setScene(main_Window.scene2);
															main_Window.scene2.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
															main_Window.scene2.getStylesheets().add(getClass().getResource("ham.css").toExternalForm());
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
													Alert alert1 = new Alert(AlertType.ERROR);
									    			alert1.setTitle("Error Dialog");
									    			alert1.setHeaderText("Stock Error!!");
									    			alert1.setContentText("Already in Stocks");
									    			alert1.showAndWait();
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
}
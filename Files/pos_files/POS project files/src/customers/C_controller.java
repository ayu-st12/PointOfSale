package customers; 


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.imageio.ImageIO;









import sales.main5_xmlread;
import sales.main_Window;


//import javax.swing.text.Document;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import database.costumer_func;
import database.dbfunc;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class C_controller {
	@FXML
	AnchorPane ap_root;									//root
	BorderPane bp_root_main = new BorderPane();			//root_main
	AnchorPane ap_main_center = new AnchorPane();		//root_main_center
	AnchorPane ap_main_top = new AnchorPane();			//root_main_top
	AnchorPane ap_right = new AnchorPane();			//empty center_right AnchorPane that will serve as Base-support panes
	AnchorPane ap_left = new AnchorPane();			//empty center_left AnchorPane that will serve as Base-support panes

	Stage stage;										//myStage
	HBox hb_center = new HBox();						//center
	AnchorPane left_main = new AnchorPane();			//lmain
	AnchorPane right_main = new AnchorPane();			//rmain
	Image cimg;

	AnchorPane add_cust, acform_section_1, acform_section_2, acform_section_3;
	HBox hbox_for_acform_vbox;
	VBox form, form2, form3;
	ImageView iv_done;
	AnchorPane p_ac_id, p_fname, p_cust_tc, p_visible, p_cred_limit, p_phone, p_addmore;
	CheckBox visible;
	JFXButton done_add_cust,addmore;
	AnchorPane p_email, p_addr1, p_addr2, p_city, p_skey, p_lname, p_state, p_country, p_zipcode, p_notes;
	JFXTextArea notes;
	boolean iswide=false;
	
	AnchorPane search_cust, search_note, p_searchbox, p_search_btn, close_view, vcform_section_1, vcform_section_2, vcform_section_3;
	HBox hbox_for_vcform_vbox;
	VBox scform, vcform1, vcform2, vcform3;
	VBox content_p=new VBox();
	ImageView search;
	AnchorPane v_profile_pic, v_ac_id, v_fname, v_lname, v_phone, v_cust_tc, v_email;
	Image cv_img;
	AnchorPane v_addr1, v_addr2, v_city, v_state, v_country, v_zip, v_skey, v_cred_limit, v_visible, v_notes;
	CheckBox cb_visible;
	JFXTextArea jta_notes;
	AnchorPane v_curdebt, v_totalsales, v_transaction, v_editbtn;
	ComboBox<String> searchlist;
	TextField c_search;
	JFXButton search_btn;
	ScrollPane sp_result;
	
	AnchorPane edit_cust, ecform_section_1, ecform_section_2, ecform_section_3;
	HBox hbox_for_ecform_vbox;
	VBox ecform1, ecform2, ecform3;
	Image ce_img;
	AnchorPane e_profile_pic, e_ac_id, e_fname, e_lname, e_phone, e_cust_tc, e_email;
	AnchorPane e_addr1, e_addr2, e_city, e_state, e_country, e_zip, e_skey, e_cred_limit, e_visible, e_notes;
	CheckBox edit_visible;
	JFXTextArea edit_notes;
	AnchorPane e_curdebt, e_totalsales, e_transaction, e_savebtn;
	Date udate;
	String uid,ufn,uln,uname,us_key,utaxcat,umaxdebt,uaddr,uaddr2,upostal,ucty,uregion,ucntry,umail,ucontact,unt,utime;
	boolean uvsble;
	Byte[] uimg=null;
	
	//Objects used for fetching data from the frame
	JFXTextField tf = new JFXTextField();
	CheckBox cb=new CheckBox();
	TextArea ta=new TextArea();
	Node child;

	Connection c;
	Statement s;
	ResultSet rs;
	dbfunc obj=new dbfunc();
	
	Date date;
	String id,fn,ln,name,s_key,taxcat,maxdebt,addr,addr2,postal,cty,region,cntry,mail,contact,nt,time;
	boolean vsble;
	Byte[] img=null;
	int count=0, count_cd=0;
	String s_keyword;
	static ArrayList<Transaction> al=new ArrayList<Transaction>();
	static ObservableList<Transaction> transact_tbl;
	double totsales, tbl_tot;
	int tbl_tid,tbl_qty;
	String tbl_date, tbl_prod;
	
	//Variables used for fetching key from values in HashMap(for viewing customer details)
	Map<String, String> myMap = new HashMap<String, String>();	
	AnchorPane ap_id;
	Label l;
	String name_val,id_val;
	String v1,v2,v3,v4,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15,v16,v17;	
	double v5,v21;
	boolean v18;
	Timestamp v19;
	byte[] v20;
	
	AnchorPane cd_report, cust_debtors, p_cb_searchbox, p_cb_search_btn;
	VBox cbform;
	ComboBox<String> cb_searchlist;
	JFXButton cb_search_btn;
	TextField cb_search;
	TableView<DebtorsReport> tv_cdebt_report;
	static ArrayList<DebtorsReport> al2=new ArrayList<DebtorsReport>();
	static ObservableList<DebtorsReport> debtors_tbl;
	String tbl_id,tbl_name,tbl_addr;
	double tbl_cdebt;
	
	AnchorPane cust_diary, p_cd_searchbox, p_cd_search_btn;
	VBox cdform;
	ComboBox<String> cd_searchlist;
	JFXButton cd_search_btn;
	TextField cd_search;
	
	AnchorPane cust_list, p_cl_searchbox, p_cl_search_btn;
	VBox clform;
	ComboBox<String> cl_searchlist;
	JFXButton cl_search_btn;
	TextField cl_search;
	
	AnchorPane sales_by_cust,p_sbc_searchbox, p_sbc_search_btn;
	VBox sbcform;
	ComboBox<String> sbc_searchlist;
	JFXButton sbc_search_btn;
	TextField sbc_search;
	
	ImageView hbg;
	@FXML
	private VBox menu;
	@FXML
	AnchorPane mbar;
	@FXML
	AnchorPane mbar_total;
	@FXML
	AnchorPane mbar_rest;
	
	StackPane top = new StackPane();
	AnchorPane ap_top = new AnchorPane();
	AnchorPane ap_left_top = new AnchorPane();
	AnchorPane ap_center_top = new AnchorPane();
	AnchorPane ap_right_top = new AnchorPane();
	public static boolean j;
	public static boolean k;
	public static boolean m;
	public static boolean o;
	public static Map<String, String> data;
	
	
	
	public void initialize(){
		//top bar
		
		mbar_total.toFront();
		ap_root.setPrefWidth(1000);
		ap_root.setPrefHeight(700);
		
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
		ap_main_top.getChildren().add(top);
		ap_main_top.setRightAnchor(top,0.0);
		ap_main_top.setLeftAnchor(top,0.0);
		ap_main_top.setTopAnchor(top,0.0);
		ap_main_top.setBottomAnchor(top,0.0);
		//adding content to border pane
		bp_root_main.setTop(ap_main_top);
		
		
		
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
			
		
		
		//top bar code ends here 
		
		
		c=obj.connect();
		try {
			s=c.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Root Anchor Pane
		ap_root.setPrefWidth(1000);
		ap_root.setPrefHeight(700);
		
		//Border Pane over that AnchorPane(ap_root)
		bp_root_main.setPrefWidth(1000);
		bp_root_main.setPrefHeight(700);
		ap_root.setRightAnchor(bp_root_main, 0.0);
		ap_root.setLeftAnchor(bp_root_main, 0.0);
		ap_root.setTopAnchor(bp_root_main, 0.0);
		ap_root.setBottomAnchor(bp_root_main, 0.0);
		
		//Top section of Border Pane
		ap_main_top.setPrefHeight(60);
		ap_main_top.setPrefWidth(ap_root.getPrefWidth());
		ap_main_top.setId("apcenter");
		
		//Center section of Border Pane
		ap_main_center.setPrefHeight(640);
		ap_main_center.setPrefWidth(ap_root.getPrefWidth());
	
		//HBox to be placed over the centered AnchorPane(ap_main_center)
		hb_center.setSpacing(15);
		ap_main_center.getChildren().add(hb_center);
		ap_main_center.setRightAnchor(hb_center, 15.0);
		ap_main_center.setLeftAnchor(hb_center, 15.0);
		ap_main_center.setBottomAnchor(hb_center, 15.0);
		ap_main_center.setTopAnchor(hb_center, 15.0);
		
		//Left AnchorPane over HBox
		ap_left.setPrefWidth(495);
		ap_left.setPrefHeight(320);
		hb_center.setHgrow(ap_left, Priority.ALWAYS);
		
		//Right AnchorPane over HBox
		ap_right.setPrefWidth(495);
		ap_right.setPrefHeight(320);
		hb_center.setHgrow(ap_right, Priority.ALWAYS);
		
		hb_center.getChildren().addAll(ap_left,ap_right);
		
		drawcontent();
		
		bp_root_main.setTop(ap_main_top);
		bp_root_main.setCenter(ap_main_center);
		ap_root.getChildren().remove(mbar_total);
		ap_root.getChildren().addAll(bp_root_main,mbar_total);
	}

	public void drawcontent(){
		
		AnchorPane l_main = create_left_menu();
		ap_left.getChildren().add(l_main);
		ap_left.setBottomAnchor(l_main,0.0);
		ap_left.setTopAnchor(l_main,0.0);
		ap_left.setLeftAnchor(l_main,0.0);
		ap_left.setRightAnchor(l_main,0.0);
		
		AnchorPane r_main = create_right_menu();
		ap_right.getChildren().add(r_main);
		ap_right.setBottomAnchor(r_main,0.0);
		ap_right.setTopAnchor(r_main,0.0);
		ap_right.setLeftAnchor(r_main,0.0);
		ap_right.setRightAnchor(r_main,0.0);
	}

	//Creates the left AnchorPane for Customers heading
	public AnchorPane create_left_menu(){
		left_main.setPrefHeight(320);
		left_main.setPrefWidth(495);
		left_main.setId("apcenter");
		Image img1 = new Image(getClass().getResource("customer_icon.png").toExternalForm(),150,150,false,false);
		
		create_image_head(left_main,img1);
		create_top_anchor_bar(left_main,"Customers");
		
		VBox list_main = new VBox();
		
		create_left_menu_main(list_main);
		
		list_main.setPrefHeight(370);
		list_main.setPrefWidth(480);
		left_main.getChildren().add(list_main);
		left_main.setLeftAnchor(list_main,25.0);
		left_main.setRightAnchor(list_main,25.0);
		left_main.setTopAnchor(list_main,250.0);
		list_main.setSpacing(10);
		
		return left_main;
	}

	//Creates the right AnchorPane for Reports heading
	public AnchorPane create_right_menu(){
		right_main.setPrefHeight(320);
		right_main.setPrefWidth(495);
		right_main.setId("apcenter");
		Image img1 = new Image(getClass().getResource("customer_icon.png").toExternalForm(),150,150,false,false);
		
		create_image_head(right_main,img1);
		create_top_anchor_bar(right_main,"Reports");
		
		VBox list_main = new VBox();
		
		create_right_menu_main(list_main);
		
		list_main.setPrefHeight(370);
		list_main.setPrefWidth(480);
		right_main.getChildren().add(list_main);
		right_main.setTopAnchor(list_main,250.0);
		right_main.setLeftAnchor(list_main,25.0);
		right_main.setRightAnchor(list_main,25.0);
		list_main.setSpacing(10);
		
		return right_main;
	}

	public void create_left_menu_main(VBox pane){
		VBox ac = new VBox();
		VBox sc = new VBox();
		//Add Customer
		Image img3 = new Image(getClass().getResource("ham1.png").toExternalForm(),45,45,false,false);
		AnchorPane AddCust = create_options_pane(img3, "Add Customer");
		ac.getChildren().add(AddCust);
		pane.getChildren().add(ac);

		//event handling for product
		AddCust.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				/*
				AnchorPane test1 = new AnchorPane();
				ap_right.getChildren().addAll(test1);
				test1.setId("apcenter");
				ap_right.setTopAnchor(test1, 0.0);
				ap_right.setBottomAnchor(test1, 0.0);
				ap_right.setLeftAnchor(test1, 0.0);
				ap_right.setRightAnchor(test1, 0.0);
				create_top_anchor_bar(test1, "test1");
				ap_right.getChildren().addAll(new Label("test1"));
				//close button
				Image img = new Image(getClass().getResource("ic_close_white_36dp.png").toExternalForm(),25,25,false,false);
				ImageView iv_close = new ImageView(img);
				Button close_add_cust = new Button("",iv_close);
				close_add_cust.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event){
					
						ap_right.getChildren().remove(test1);
					}
				});
				test1.getChildren().addAll(close_add_cust);
				*/
				add();
			}
		});
				
		//Search Customer
		Image img4 = new Image(getClass().getResource("ham1.png").toExternalForm(),45,45,false,false);
		AnchorPane SearchCust = create_options_pane(img4, "Search Customer");
		sc.getChildren().add(SearchCust);
		pane.getChildren().add(sc);

		//event handling for product
		SearchCust.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				/*
				AnchorPane test1 = new AnchorPane();
				ap_right.getChildren().addAll(test1);
				test1.setId("apcenter");
				ap_right.setTopAnchor(test1, 0.0);
				ap_right.setBottomAnchor(test1, 0.0);
				ap_right.setLeftAnchor(test1, 0.0);
				ap_right.setRightAnchor(test1, 0.0);
				create_top_anchor_bar(test1, "test2");
				ap_right.getChildren().addAll(new Label("test2"));
				//close button
				Image img = new Image(getClass().getResource("ic_close_white_36dp.png").toExternalForm(),25,25,false,false);
				ImageView iv_close = new ImageView(img);
				Button close_add_cust = new Button("",iv_close);
				close_add_cust.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event){
					
						ap_right.getChildren().remove(test1);
					}
				});
				test1.getChildren().addAll(close_add_cust);
				*/
				search();
			}
		});
		
	}

	public void create_right_menu_main(VBox pane){
		
		//Customer Debtors
			Image img3 = new Image(getClass().getResource("ham1.png").toExternalForm(),45,45,false,false);
			AnchorPane CustDebtors = create_options_pane(img3, "Customer Debtors");
			
		//event handling for product
		CustDebtors.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				customer_debtors();
			}
		});
		
		//Customers Diary Current
		Image img4 = new Image(getClass().getResource("ham1.png").toExternalForm(),45,45,false,false);
		AnchorPane CustDiary = create_options_pane(img4, "Customers Diary");
						
		//event handling for categories
		CustDiary.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				customers_diary();
			}
		});
		
		//Customers List
		Image img5 = new Image(getClass().getResource("ham1.png").toExternalForm(),45,45,false,false);
		AnchorPane CustList = create_options_pane(img5, "Customers List");
						
		//event handling for categories
		CustList.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				customers_list();
			}
		});
		
		//Sales by Customers
		Image img6 = new Image(getClass().getResource("ham1.png").toExternalForm(),45,45,false,false);
		AnchorPane SalesByCust = create_options_pane(img5, "Sales by Customers");
						
		//event handling for categories
		SalesByCust.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				sales_by_cust();
			}
		});
								
		pane.getChildren().addAll(CustDebtors, CustDiary, CustList, SalesByCust);
	}

	public void effect(){
		AnchorPane right_top = new AnchorPane();
		right_top.setPrefHeight(320);
		right_top.setPrefWidth(495);
		right_top.setId("apcenter");
	
		JFXButton exit = new JFXButton("Exit");
	
		right_top.getChildren().add(exit);
	
		ap_right.getChildren().add(right_top);
		ap_right.setBottomAnchor(right_top,0.0);
		ap_right.setRightAnchor(right_top,0.0);
		ap_right.setTopAnchor(right_top,0.0);
		ap_right.setLeftAnchor(right_top,0.0);
	
		exit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				ap_right.getChildren().remove(right_top);
			
			}
		});
	}

	public void create_top_anchor_bar(AnchorPane pane, String head){
		
		AnchorPane top = new AnchorPane();
		top.setPrefHeight(40);
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

	public AnchorPane create_form_field(String fieldName){
		AnchorPane field = new AnchorPane();
		JFXTextField ffield=new JFXTextField();
		ffield.setPromptText(fieldName);
		ffield.setPrefWidth(300);
		ffield.setPrefHeight(30);
		ffield.setId("ref");
		
		field.getChildren().add(ffield);
		field.setTopAnchor(ffield, 10.0);
		field.setLeftAnchor(ffield, 10.0);
		field.setBottomAnchor(ffield, 0.0);
		field.setRightAnchor(ffield, 0.0);
		return field;
	}

	public AnchorPane create_edit_form_field(String fieldName){
		AnchorPane field = new AnchorPane();
		JFXTextField ffield=new JFXTextField();
		ffield.setText(fieldName);
		ffield.setPrefWidth(300);
		ffield.setPrefHeight(25);
		ffield.setId("ref");
		
		field.getChildren().add(ffield);
		field.setTopAnchor(ffield, 10.0);
		field.setLeftAnchor(ffield, 10.0);
		field.setBottomAnchor(ffield, 0.0);
		field.setRightAnchor(ffield, 0.0);
		return field;
	}
	
	public AnchorPane create_editleft_form_field(String fieldName){
		AnchorPane field = new AnchorPane();
		JFXTextField ffield=new JFXTextField();
		ffield.setText(fieldName);
		ffield.setPrefWidth(300);
		ffield.setPrefHeight(25);
		ffield.setId("left_form");
		
		field.getChildren().add(ffield);
		field.setTopAnchor(ffield, 10.0);
		field.setLeftAnchor(ffield, 10.0);
		field.setBottomAnchor(ffield, 0.0);
		field.setRightAnchor(ffield, 0.0);
		return field;
	}
	
	public AnchorPane show_form_field(String fieldName){
		AnchorPane field = new AnchorPane();
		Label ffield=new Label(fieldName);
		ffield.setPrefWidth(300);
		ffield.setPrefHeight(30);
		ffield.setId("ref");
		field.getChildren().add(ffield);
		field.setTopAnchor(ffield, 10.0);
		field.setLeftAnchor(ffield, 10.0);
		field.setBottomAnchor(ffield, 0.0);
		field.setRightAnchor(ffield, 0.0);
		return field;
	}

	public AnchorPane show_left_form_field(String fieldName){
		AnchorPane field = new AnchorPane();
		Label ffield=new Label(fieldName);
		ffield.setPrefWidth(300);
		ffield.setPrefHeight(30);
		ffield.setId("left_form");
		field.getChildren().add(ffield);
		field.setTopAnchor(ffield, 10.0);
		field.setLeftAnchor(ffield, 10.0);
		field.setBottomAnchor(ffield, 0.0);
		field.setRightAnchor(ffield, 0.0);
		return field;
	}
	
	public AnchorPane create_add_pane(){
		AnchorPane apane=new AnchorPane();
		
		//top bar
		AnchorPane ac_top = new AnchorPane();
		ac_top.setPrefHeight(40);
		ac_top.setPrefWidth(95);
		apane.setTopAnchor(ac_top,0.0);
		apane.setLeftAnchor(ac_top,0.0);
		apane.setRightAnchor(ac_top,0.0);
		ac_top.setId("top");
		
		Label add_cust_menu = new Label("Add Customer");
		add_cust_menu.setPrefHeight(40);
		add_cust_menu.setPrefWidth(495);
		add_cust_menu.setAlignment(Pos.CENTER);
		ac_top.getChildren().add(add_cust_menu);
		ac_top.setTopAnchor(add_cust_menu,0.0);
		ac_top.setLeftAnchor(add_cust_menu,0.0);
		ac_top.setRightAnchor(add_cust_menu,0.0);
		ac_top.setBottomAnchor(add_cust_menu,0.0);
		add_cust_menu.setId("head");		

		//partial save button
		iv_done=new ImageView(getClass().getResource("ic_done_white_36dp.png").toExternalForm());
		iv_done.setFitHeight(25);
		iv_done.setFitWidth(25);
		
		done_add_cust=new JFXButton("",iv_done);
		done_add_cust.setPrefHeight(30);
		done_add_cust.setPrefWidth(30);
		done_add_cust.setId("done_add_cust");
		ac_top.getChildren().add(done_add_cust);
		ac_top.setLeftAnchor(done_add_cust, 0.0);
		ac_top.setTopAnchor(done_add_cust, 2.0);
		
		//close button
		Image img = new Image(getClass().getResource("ic_close_white_36dp.png").toExternalForm(),25,25,false,false);
		ImageView iv_close = new ImageView(img);
		Button close_add_cust = new Button("",iv_close);
		close_add_cust.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
			
				
				if(iswide==false){
					ap_right.getChildren().remove(apane);
				}
				else {
				ap_main_center.getChildren().remove(apane);
				iswide=false;
				}
			}
		});
		close_add_cust.setId("closebutton");
		ac_top.getChildren().add(close_add_cust);
		ac_top.setRightAnchor(close_add_cust,0.0);
		ac_top.setTopAnchor(close_add_cust,2.0);

		hbox_for_acform_vbox = new HBox();
		hbox_for_acform_vbox.setSpacing(50);
		apane.setTopAnchor(hbox_for_acform_vbox, 50.0);
		apane.setLeftAnchor(hbox_for_acform_vbox, 50.0);
		apane.setBottomAnchor(hbox_for_acform_vbox, 10.0);
		apane.setRightAnchor(hbox_for_acform_vbox, 50.0);
		
		acform_section_1 = new AnchorPane();					//anchorpane for form section1
		acform_section_2 = new AnchorPane();					//anchorpane for form section2
		acform_section_3 = new AnchorPane();					//anchorpane for form section3
		
		//creating form
		form = new VBox();
		hbox_for_acform_vbox.setHgrow(form, Priority.ALWAYS);
		form.setSpacing(10);
		
		p_ac_id = create_form_field("Account ID");
		form.setVgrow(p_ac_id, Priority.NEVER);
		
		p_fname = create_form_field("First Name");
		form.setVgrow(p_fname, Priority.NEVER);
		
		p_cust_tc = create_form_field("Customer Tax Category");
		form.setVgrow(p_cust_tc, Priority.NEVER);
		
		p_visible=new AnchorPane();
		p_visible.setPrefWidth(300);
		p_visible.setPrefHeight(30);
		
		visible=new CheckBox("Visible");
		visible.setPrefWidth(80);
		visible.setPrefHeight(15);
		visible.setSelected(true);
		visible.setId("visible");
		visible.setStyle("-fx-font-style: 'Noto Sans';");
		
		p_visible.getChildren().add(visible);
		p_visible.setTopAnchor(visible, 10.0);
		p_visible.setLeftAnchor(visible, 20.0);
		
		p_cred_limit = create_form_field("Credit Limit");
		form.setVgrow(p_cred_limit, Priority.NEVER);
		
		p_phone = create_form_field("Phone");
		form.setVgrow(p_phone, Priority.NEVER);
		
		p_addmore=new AnchorPane();
		p_addmore.setPrefWidth(250);
		p_addmore.setPrefHeight(45);
		p_addmore.setLayoutX(105);
		p_addmore.setLayoutY(580);
		
		JFXButton addmore=new JFXButton();
		addmore.setPrefWidth(250);
		addmore.setPrefHeight(45);
		addmore.setText("<< Add More Info");
		addmore.setId("addmore");
		addmore.setStyle("-fx-font-style: 'Noto Sans';");
		
		p_addmore.getChildren().add(addmore);
		p_addmore.setTopAnchor(addmore, 165.0);
		p_addmore.setLeftAnchor(addmore, 35.0);
		
		form.getChildren().addAll(p_ac_id, p_fname, p_cust_tc, p_visible, p_cred_limit, p_phone, p_addmore);
		
		acform_section_1.getChildren().add(form);
		acform_section_1.setTopAnchor(form, 20.0);
		acform_section_1.setBottomAnchor(form, 60.0);
		
		hbox_for_acform_vbox.getChildren().add(acform_section_1);
		hbox_for_acform_vbox.setAlignment(Pos.TOP_CENTER);
		
		apane.getChildren().addAll(ac_top,hbox_for_acform_vbox);
		
		done_add_cust.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){
				save_partial_details();
			}
		});
		
		addmore.setOnAction(event->{
			iswide=true;
			ap_right.getChildren().remove(apane);
			ap_main_center.getChildren().add(apane);
			ap_main_center.setTopAnchor(apane, 15.0);
			ap_main_center.setRightAnchor(apane, 15.0);
			ap_main_center.setBottomAnchor(apane, 25.0);
			ap_main_center.setLeftAnchor(apane, 15.0);
			hbox_for_acform_vbox.setAlignment(Pos.CENTER_LEFT);
			
			p_email = create_form_field("Email");
			form.setVgrow(p_email, Priority.NEVER);

			p_addr1 = create_form_field("Address Line 1");
			form.setVgrow(p_addr1, Priority.NEVER);

			p_addr2 = create_form_field("Address Line 2");
			form.setVgrow(p_addr2, Priority.NEVER);
			
			p_city = create_form_field("City");
			form.setVgrow(p_city, Priority.NEVER);

			form.getChildren().addAll(p_email, p_addr1, p_addr2, p_city);
			form.getChildren().remove(p_addmore);
			
			//Second Form
			form2 = new VBox();
			form2.setSpacing(20);
			acform_section_2.getChildren().add(form2);
			acform_section_2.setTopAnchor(form2, 20.0);
			acform_section_2.setBottomAnchor(form2, 60.0);
			
			p_skey = create_form_field("Search Key");
			form2.setVgrow(p_skey, Priority.NEVER);

			p_lname = create_form_field("Last Name");
			form2.setVgrow(p_lname, Priority.NEVER);
			
			p_state = create_form_field("State");
			form2.setVgrow(p_state, Priority.NEVER);

			p_country = create_form_field("Country");
			form2.setVgrow(p_country, Priority.NEVER);

			p_zipcode = create_form_field("Zipcode");
			form2.setVgrow(p_zipcode, Priority.NEVER);

			p_notes = new AnchorPane();
			notes=new JFXTextArea();
			notes.setPromptText("Notes");
			notes.setPrefWidth(300);
			notes.setPrefHeight(150);
			notes.setId("ref");
			
			p_notes.getChildren().add(notes);
			p_notes.setTopAnchor(notes, 10.0);
			p_notes.setLeftAnchor(notes, 18.0);
			p_notes.setBottomAnchor(notes, 0.0);
			p_notes.setRightAnchor(notes, 10.0);
			
			JFXButton save = new JFXButton("Save");
			save.setPrefWidth(300);
			save.setPrefHeight(50);
			save.setId("save");
			form2.setMargin(save, new Insets(0,0,0,18));
			
			save.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
				public void handle(MouseEvent me){
					save_customer();
				}
			});
			
			JFXButton new_cust= new JFXButton("New");
			new_cust.setPrefWidth(300);
			new_cust.setPrefHeight(50);
			new_cust.setId("new_cust");
			form2.setMargin(new_cust, new Insets(0,0,0,18));
			
			new_cust.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
				public void handle(MouseEvent me){
					new_customer();
				}
			});
			
			form2.getChildren().addAll(p_skey, p_lname, p_state, p_country, p_zipcode, p_notes, save, new_cust);
			
			Separator vsep = new Separator();
			vsep.setOrientation(Orientation.VERTICAL);
			
			AnchorPane ap_image_section = new AnchorPane();
			ap_image_section.setPrefHeight(270);
			ap_image_section.setPrefWidth(400);
			form3 = new VBox();
			
			//image display area
			cimg = new Image(getClass().getResource("customer_icon.png").toExternalForm(),100,100,false,false);
			ImagePattern pat = new ImagePattern(cimg);
			Circle clip = new Circle(200,120,100);
			clip.setFill(pat);
			
			StackPane cust_img_label = new StackPane();
			Label cust_img = new Label("Profile Picture");
			cust_img.setId("ref");
			cust_img_label.getChildren().add(cust_img);
			cust_img_label.setAlignment(cust_img,Pos.CENTER);
			ap_image_section.setBottomAnchor(cust_img_label,10.0);
			ap_image_section.setLeftAnchor(cust_img_label,0.0);
			ap_image_section.setRightAnchor(cust_img_label,0.0);
			
			//creating image selector
			Button image_select = new Button();
			image_select.setId("image_select");
			image_select.setPrefWidth(40);
			image_select.setPrefHeight(40);
			ap_image_section.setRightAnchor(image_select,100.0);
			ap_image_section.setTopAnchor(image_select,150.0);
			
			image_select.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				FileChooser customer_pic = new FileChooser();
				configureFileChooser(customer_pic);
				customer_pic.setTitle("Select Profile Picture");
				File new_cust_image = customer_pic.showOpenDialog(stage);
				if(new_cust_image!=null){
					//openFile(new_pro_image);
					BufferedImage imga = null;
					try {
						imga = ImageIO.read(new_cust_image);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ImagePattern pat = new ImagePattern(SwingFXUtils.toFXImage(imga,null));
					clip.setFill(pat);
								
				}
							
				}
			});
			
			ap_image_section.getChildren().addAll(clip,cust_img_label,image_select);
			AnchorPane keyboard_section = new AnchorPane();
			//keyboard_section.setId("apcenter");
			form3.getChildren().addAll(ap_image_section,keyboard_section);
			form3.setVgrow(ap_image_section, Priority.ALWAYS);
			acform_section_3.getChildren().add(form3);
			
			hbox_for_acform_vbox.getChildren().addAll(acform_section_2, acform_section_3);

		});

		return apane;
	}
	
	public void add(){
		add_cust = create_add_pane();
		add_cust.setId("apcenter");
		
		ap_right.getChildren().add(add_cust);
		ap_right.setTopAnchor(add_cust,0.0);
		ap_right.setBottomAnchor(add_cust,0.0);
		ap_right.setLeftAnchor(add_cust,0.0);
		ap_right.setRightAnchor(add_cust,0.0);
		
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

	public void save_partial_details(){
		try{			
			child=p_ac_id.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			id=tf.getText();
			
			child=p_fname.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			fn=tf.getText();
			
			child=p_cust_tc.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			taxcat=tf.getText();
			
			child=p_visible.getChildren().get(0);
			if(child instanceof CheckBox)
				cb=(CheckBox) child;
			if(cb.isSelected())
				vsble=true;
			else
				vsble=false;
			
			child=p_cred_limit.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			maxdebt=tf.getText();
			
			child=p_phone.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			contact=tf.getText();
			
			date = new Date();
        	final DateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        	time = timeFormat.format(date);
			
			costumer_func.insert_costumer(id, null, fn, taxcat, maxdebt, null, null, null, null, null, null, fn, null, null, contact, null, null, vsble, time, img);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void save_customer(){
		try {
			child=p_ac_id.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			id=tf.getText();

			child=p_skey.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			s_key=tf.getText();

			child=p_fname.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			fn=tf.getText();
			
			child=p_lname.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			ln=tf.getText();
			
			name=fn+" "+ln;
		
			child=p_cust_tc.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			taxcat=tf.getText();
			
			child=p_cred_limit.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			maxdebt=tf.getText();
		
			child=p_addr1.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			addr=tf.getText();
			
			child=p_addr2.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			addr2=tf.getText();
		
			child=p_zipcode.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			postal=tf.getText();
			
			child=p_city.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			cty=tf.getText();
			
			child=p_state.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			region=tf.getText();
			
			child=p_country.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			cntry=tf.getText();
		
			child=p_email.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			mail=tf.getText();
		
			child=p_phone.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			contact=tf.getText();
		
			child=p_fname.getChildren().get(0);
			if(child instanceof TextArea)
				ta=(TextArea) child;
			nt=ta.getText();
		
			child=p_visible.getChildren().get(0);
			if(child instanceof CheckBox)
				cb=(CheckBox) child;
			if(cb.isSelected())
				vsble=true;
			else
				vsble=false;
			
			date = new Date();
        	final DateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        	time = timeFormat.format(date);
                
			costumer_func.insert_costumer(id, s_key, name, taxcat, maxdebt, addr, addr2, postal, cty, region, cntry, fn, ln, mail, contact, null, nt, vsble, time, img);
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void new_customer(){
		JFXTextField tf1=new JFXTextField();			JFXTextField tf2=new JFXTextField();
		JFXTextField tf3=new JFXTextField();			JFXTextField tf4=new JFXTextField();
		JFXTextField tf5=new JFXTextField();			JFXTextField tf6=new JFXTextField();
		JFXTextField tf7=new JFXTextField();			JFXTextField tf8=new JFXTextField();
		JFXTextField tf9=new JFXTextField();			JFXTextField tf10=new JFXTextField();
		JFXTextField tf11=new JFXTextField();			JFXTextField tf12=new JFXTextField();
		JFXTextField tf13=new JFXTextField();			JFXTextField tf14=new JFXTextField();
		
		child=p_ac_id.getChildren().get(0);
		if(child instanceof JFXTextField)
			tf1=(JFXTextField) child;
		
		child=p_skey.getChildren().get(0);
		if(child instanceof JFXTextField)
			tf2=(JFXTextField) child;
		
		child=p_fname.getChildren().get(0);
		if(child instanceof JFXTextField)
			tf3=(JFXTextField) child;
		
		child=p_lname.getChildren().get(0);
		if(child instanceof JFXTextField)
			tf4=(JFXTextField) child;
		
		name=fn+" "+ln;
	
		child=p_cust_tc.getChildren().get(0);
		if(child instanceof JFXTextField)
			tf5=(JFXTextField) child;
		
		child=p_cred_limit.getChildren().get(0);
		if(child instanceof JFXTextField)
			tf6=(JFXTextField) child;
		
		child=p_addr1.getChildren().get(0);
		if(child instanceof JFXTextField)
			tf7=(JFXTextField) child;
		
		child=p_addr2.getChildren().get(0);
		if(child instanceof JFXTextField)
			tf8=(JFXTextField) child;
		
		child=p_zipcode.getChildren().get(0);
		if(child instanceof JFXTextField)
			tf9=(JFXTextField) child;
		
		child=p_city.getChildren().get(0);
		if(child instanceof JFXTextField)
			tf10=(JFXTextField) child;
		
		child=p_state.getChildren().get(0);
		if(child instanceof JFXTextField)
			tf11=(JFXTextField) child;
		
		child=p_country.getChildren().get(0);
		if(child instanceof JFXTextField)
			tf12=(JFXTextField) child;
		
		child=p_email.getChildren().get(0);
		if(child instanceof JFXTextField)
			tf13=(JFXTextField) child;
		
		child=p_phone.getChildren().get(0);
		if(child instanceof JFXTextField)
			tf14=(JFXTextField) child;
		
		child=p_fname.getChildren().get(0);
		if(child instanceof TextArea)
			ta=(TextArea) child;
		
		if( (tf1.getText() !=null || !tf1.getText().trim().isEmpty()) || (tf2.getText() !=null || !tf2.getText().trim().isEmpty()) || (tf3.getText() !=null || !tf3.getText().trim().isEmpty()) || (tf4.getText() !=null || !tf4.getText().trim().isEmpty()) || (tf5.getText() !=null || !tf5.getText().trim().isEmpty())
				 || (tf6.getText() !=null || !tf6.getText().trim().isEmpty()) || (tf7.getText() !=null || !tf7.getText().trim().isEmpty()) || (tf8.getText() !=null || !tf8.getText().trim().isEmpty()) || (tf9.getText() !=null || !tf9.getText().trim().isEmpty()) || (tf10.getText() !=null || !tf10.getText().trim().isEmpty())
				 || (tf11.getText() !=null || !tf11.getText().trim().isEmpty()) || (tf12.getText() !=null || !tf12.getText().trim().isEmpty()) || (tf13.getText() !=null || !tf13.getText().trim().isEmpty()) || (tf14.getText() !=null || !tf14.getText().trim().isEmpty()) || (ta.getText() !=null || !ta.getText().trim().isEmpty())){
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Add New Customer : CONFIRMATION");
				String s = "Current information will be removed! Are you sure?";
				alert.setContentText(s);	
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					tf1.clear();					tf1.setPromptText("Account ID");
					tf2.clear();					tf2.setPromptText("Search Key");
					tf3.clear();					tf3.setPromptText("First Name");
					tf4.clear();					tf4.setPromptText("Last Name");
					tf5.clear();					tf5.setPromptText("Customer Tax Category");
					tf6.clear();					tf6.setPromptText("Credit Limit");
					tf7.clear();					tf7.setPromptText("Address Line 1");
					tf8.clear();					tf8.setPromptText("Address Line 2");
					tf9.clear();					tf9.setPromptText("Zipcode");
					tf10.clear();					tf10.setPromptText("City");
					tf11.clear();					tf11.setPromptText("State");
					tf12.clear();					tf12.setPromptText("Country");
					tf13.clear();					tf13.setPromptText("Email");
					tf14.clear();					tf14.setPromptText("Phone");
					ta.clear();						ta.setPromptText("Notes");
				} else {
				    // do nothing
				}
			}
	}

	public AnchorPane create_search_pane(){

		AnchorPane spane=new AnchorPane();
		
		//top bar
		AnchorPane sc_top = new AnchorPane();
		sc_top.setPrefHeight(40);
		sc_top.setPrefWidth(95);
		spane.setTopAnchor(sc_top,0.0);
		spane.setLeftAnchor(sc_top,0.0);
		spane.setRightAnchor(sc_top,0.0);
		sc_top.setId("top");
	
		Label search_cust_menu = new Label("Search Customer");
		search_cust_menu.setPrefHeight(40);
		search_cust_menu.setPrefWidth(495);
		search_cust_menu.setAlignment(Pos.CENTER);
		sc_top.getChildren().add(search_cust_menu);
		sc_top.setTopAnchor(search_cust_menu,0.0);
		sc_top.setLeftAnchor(search_cust_menu,0.0);
		sc_top.setRightAnchor(search_cust_menu,0.0);
		sc_top.setBottomAnchor(search_cust_menu,0.0);
		search_cust_menu.setId("head");		

		//close button
		Image img = new Image(getClass().getResource("ic_close_white_36dp.png").toExternalForm(),25,25,false,false);
		ImageView iv_close = new ImageView(img);
		Button close_search_cust = new Button("",iv_close);
		close_search_cust.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
					ap_right.getChildren().removeAll(spane);
					ap_left.getChildren().remove(sp_result);
					count=0;
				}
		});
		close_search_cust.setId("closebutton");
		sc_top.getChildren().add(close_search_cust);
		sc_top.setRightAnchor(close_search_cust,0.0);
		sc_top.setTopAnchor(close_search_cust,2.0);
		
		scform=new VBox();
		scform.setSpacing(10);
		
		spane.setTopAnchor(scform, 70.0);
		spane.setLeftAnchor(scform, 50.0);
			
		search_note=show_form_field("Search by: (Select from the drop-down list)");
		scform.setVgrow(search_note, Priority.NEVER);
			
		p_searchbox=new AnchorPane();
		p_searchbox.setPrefWidth(300);
		p_searchbox.setPrefHeight(40);
		p_searchbox.setId("p_searchbox");
		AnchorPane.setLeftAnchor(p_searchbox, 70.0);
		scform.setVgrow(p_searchbox, Priority.NEVER);
			
		search=new ImageView(getClass().getResource("ic_search_black_24dp.png").toExternalForm());
		search.setId("search");
		search.setFitWidth(40.0);
		search.setFitHeight(40.0);
		p_searchbox.setLeftAnchor(search, 0.0);
		p_searchbox.setTopAnchor(search, 2.0);
		p_searchbox.setRightAnchor(search, 260.0);
		p_searchbox.setBottomAnchor(search, 0.0);
			
		searchlist=new ComboBox<String>();
		searchlist.setId("searchlist");
		searchlist.setPrefWidth(20.0);
		searchlist.setPrefHeight(40.0);
        searchlist.getItems().setAll("Mobile No.","Name","Account ID");
        searchlist.setVisibleRowCount(3);
        p_searchbox.setTopAnchor(searchlist, 0.0);
        p_searchbox.setRightAnchor(searchlist, 0.0);
        p_searchbox.setBottomAnchor(searchlist, 1.0);

        c_search=new TextField();
        c_search.setPromptText("Enter Mobile No.");
        searchlist.getSelectionModel().selectFirst();
		c_search.setAlignment(Pos.CENTER_LEFT);
		c_search.setPadding(new Insets(0,0,0,10));
		c_search.setFont(Font.font("Noto Sans", 18));
		c_search.setPrefWidth(240.0);	
		c_search.setPrefHeight(40.0);
		c_search.setId("c_search");
		p_searchbox.setTopAnchor(c_search, 0.0);
		p_searchbox.setRightAnchor(c_search, 50.0);
		p_searchbox.setBottomAnchor(c_search, 0.0);
		p_searchbox.setLeftAnchor(c_search, 40.0);
		searchlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

		@Override
		public void changed(ObservableValue<? extends String> observable,
				String oldValue, String newValue) {
			if(newValue!=null){
				switch(newValue){
				case "Mobile No.":{ c_search.clear();
									c_search.setPromptText("Enter Mobile No.");
								  }
								  break;
				case "Name":	  { c_search.clear();
							  	    c_search.setPromptText("Enter Name");
								  }
							      break;
				case "Account ID":{ c_search.clear();
									c_search.setPromptText("Enter Account ID");
								  }
								  break;
				default:		  break;
				}
			}	
		}
		});

		p_searchbox.getChildren().addAll(search,c_search,searchlist);
		/*p_searchbox.setLeftAnchor(c_search, 90.0);
		p_searchbox.setLeftAnchor(search, 90.0);
		p_searchbox.setLeftAnchor(searchlist, 90.0);
		*/
		p_search_btn=new AnchorPane();
		p_search_btn.setPrefWidth(200);
		p_search_btn.setPrefHeight(30);
		p_search_btn.setId("p_search_btn");
		scform.setVgrow(p_search_btn, Priority.NEVER);
		
		search_btn=new JFXButton();
		search_btn.setText("Search");
		search_btn.setPrefWidth(200);
		search_btn.setPrefHeight(30);
		search_btn.setId("search_btn");
		search_btn.setStyle("-fx-font-style: 'Noto Sans';");
				
		p_search_btn.getChildren().add(search_btn);
		p_search_btn.setLeftAnchor(search_btn, 100.0);
		
		scform.getChildren().addAll(search_note, p_searchbox, p_search_btn);
		scform.setVgrow(search_note, Priority.ALWAYS);
		scform.setVgrow(p_searchbox, Priority.ALWAYS);
		scform.setVgrow(p_search_btn, Priority.ALWAYS);
		
		spane.setLeftAnchor(scform, 75.0);
		spane.setRightAnchor(scform, 75.0);
		spane.getChildren().addAll(sc_top, scform);
		
		search_btn.setOnAction(event->{
			//if(count==0)
			//{	
				perform_search();
				count++;
				
			//}else{
			//	count = 1;
			//}	
		});

		return spane;
	}
	
	public void search(){
		
		search_cust=create_search_pane();
		search_cust.setId("apcenter");
		
		ap_right.getChildren().add(search_cust);
		ap_right.setTopAnchor(search_cust,0.0);
		ap_right.setBottomAnchor(search_cust,0.0);
		ap_right.setLeftAnchor(search_cust,0.0);
		ap_right.setRightAnchor(search_cust,0.0);
	}
	
	public void perform_search(){
		AnchorPane ap=new AnchorPane();	
		String name_ans=null;		String id_ans=null;

		content_p.getChildren().clear();

		sp_result=new ScrollPane();
		sp_result.setId("sp_result");
		sp_result.setFitToWidth(true);
		sp_result.setFitToHeight(true);
		
		ap_left.getChildren().add(sp_result);
		ap_left.setTopAnchor(sp_result, 0.0);
		ap_left.setRightAnchor(sp_result, 0.0);
		ap_left.setBottomAnchor(sp_result, 0.0);
		ap_left.setLeftAnchor(sp_result, 0.0);
		
		content_p.setPadding(new Insets(4.0, 4.0, 4.0, 4.0));
		content_p.setId("apcenter");
		s_keyword=c_search.getText();
		
		try{
			String query="SELECT id,name FROM customers WHERE phone='"+s_keyword+"' ORDER BY name;";
			rs=s.executeQuery(query);
			
			while(rs.next()){
				id_ans=rs.getString(1);
				name_ans=rs.getString(2);
				myMap.put(id_ans, name_ans);
				ap=show_results(name_ans);
				content_p.getChildren().add(ap);
			}
			
			rs.beforeFirst();
									
		}catch(Exception exp){
			exp.getMessage();
		}
		
		sp_result.setContent(content_p);
	}
	
	public AnchorPane show_results(String x){
		int top=60;
		
		AnchorPane p_result=new AnchorPane();
		p_result.setPrefWidth(600);
		p_result.setPrefHeight(50);
		p_result.setId("p_result");
		VBox.setMargin(p_result, new Insets(6.0,0.0,6.0,0.0));
		top=top+90;
		
		Label res1=new Label();
		res1.setText(x);
		res1.setId("res1");
		res1.setStyle("-fx-font-style: 'Noto Sans';");
		p_result.setTopAnchor(res1, 8.0);
		p_result.setLeftAnchor(res1, 50.0);
		
		ImageView imgview1=new ImageView(getClass().getResource("ic_person_black_36dp.png").toExternalForm());
		imgview1.setId("imgview1");
		AnchorPane.setTopAnchor(imgview1, 6.0);
		AnchorPane.setRightAnchor(imgview1, 552.0);
		AnchorPane.setBottomAnchor(imgview1, 4.0);
		AnchorPane.setLeftAnchor(imgview1, 4.0);
		
		p_result.getChildren().addAll(res1,imgview1);
    	
		p_result.addEventHandler(MouseEvent.MOUSE_PRESSED,
	            new EventHandler<MouseEvent>(){

	            public void handle(MouseEvent e) {
	            	
	            	ap_id=(AnchorPane) e.getSource();
	            	l=(Label)ap_id.lookup("#res1");
	            	name_val=l.getText();
	            	for (java.util.Map.Entry<String, String> entry : myMap.entrySet()) {
	                    if (Objects.equals(name_val, entry.getValue())) {
	                    	id_val=entry.getKey();
	                    }
	                }
	            	view_customer(id_val);
	             }
	     });
		
		return p_result;
	}
	
	public void view_customer(String id){
		try{
			String query="SELECT * FROM customers WHERE id='"+id+"'";
			rs=s.executeQuery(query);
			
			while(rs.next()){
				v1=rs.getString(1);	//id
				v2=rs.getString(2);
				v3=rs.getString(3);
				v4=rs.getString(4);
				v5=rs.getDouble(5);
				v6=rs.getString(6);
				v7=rs.getString(7);
				v8=rs.getString(8);
				v9=rs.getString(9);
				v10=rs.getString(10);
				v11=rs.getString(11);
				v12=rs.getString(12);
				v13=rs.getString(13);
				v14=rs.getString(14);
				v15=rs.getString(15);
				v16=rs.getString(16);
				v17=rs.getString(17);
				v18=rs.getBoolean(18);
				v19=rs.getTimestamp(19);
				v20=rs.getBytes(20);
				v21=rs.getDouble(21);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		close_view=new AnchorPane();
		close_view.setId("apcenter");
		
		ap_main_center.getChildren().add(close_view);
		ap_main_center.setTopAnchor(close_view, 15.0);
		ap_main_center.setRightAnchor(close_view, 15.0);
		ap_main_center.setBottomAnchor(close_view, 15.0);
		ap_main_center.setLeftAnchor(close_view, 15.0);
		
		//top bar
		AnchorPane vc_top = new AnchorPane();
		vc_top.setPrefHeight(40);
		vc_top.setPrefWidth(95);
		close_view.setTopAnchor(vc_top,0.0);
		close_view.setLeftAnchor(vc_top,0.0);
		close_view.setRightAnchor(vc_top,0.0);
		vc_top.setId("top");
				
		Label view_cust_menu = new Label("View Customer");
		view_cust_menu.setPrefHeight(40);
		view_cust_menu.setPrefWidth(495);
		view_cust_menu.setAlignment(Pos.CENTER);
		vc_top.getChildren().add(view_cust_menu);
		vc_top.setTopAnchor(view_cust_menu,0.0);
		vc_top.setLeftAnchor(view_cust_menu,0.0);
		vc_top.setRightAnchor(view_cust_menu,0.0);
		vc_top.setBottomAnchor(view_cust_menu,0.0);
		view_cust_menu.setId("head");		
		
		//close button
		Image img = new Image(getClass().getResource("ic_close_white_36dp.png").toExternalForm(),25,25,false,false);
		ImageView iv_close = new ImageView(img);
		Button close_view_cust = new Button("",iv_close);
		close_view_cust.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event){
					
				ap_main_center.getChildren().remove(close_view);
			}
		});
		
		close_view_cust.setId("closebutton");
		vc_top.getChildren().add(close_view_cust);
		vc_top.setRightAnchor(close_view_cust,0.0);
		vc_top.setTopAnchor(close_view_cust,2.0);
		
		hbox_for_vcform_vbox = new HBox();
		hbox_for_vcform_vbox.setSpacing(40);
		close_view.setTopAnchor(hbox_for_vcform_vbox, 50.0);
		close_view.setLeftAnchor(hbox_for_vcform_vbox, 50.0);
		close_view.setBottomAnchor(hbox_for_vcform_vbox, 10.0);
		close_view.setRightAnchor(hbox_for_vcform_vbox, 50.0);
		
		vcform_section_1 = new AnchorPane();	//anchorpane for form section1	
		vcform_section_1.setId("ap_left_view");
		vcform_section_2 = new AnchorPane();	//anchorpane for form section2
		vcform_section_3 = new AnchorPane();	//anchorpane for form section3
		
		vcform1 = new VBox();
		hbox_for_vcform_vbox.setHgrow(vcform1, Priority.ALWAYS);
		vcform1.setSpacing(10);
		
		v_profile_pic = new AnchorPane();
		v_profile_pic.setPrefHeight(270);
		v_profile_pic.setPrefWidth(400);
		
		//image display area
		cv_img = new Image(getClass().getResource("customer_icon.png").toExternalForm(),100,100,false,false);
		ImagePattern pat = new ImagePattern(cv_img);
		Circle clip = new Circle(200,120,100);
		clip.setFill(pat);
		
		StackPane cust_img_label = new StackPane();
		Label cust_img = new Label("Profile Picture");
		cust_img.setId("left_form");
		cust_img_label.getChildren().add(cust_img);
		cust_img_label.setAlignment(cust_img,Pos.CENTER);
		v_profile_pic.setBottomAnchor(cust_img_label,10.0);
		v_profile_pic.setLeftAnchor(cust_img_label,0.0);
		v_profile_pic.setRightAnchor(cust_img_label,0.0);

		v_profile_pic.getChildren().addAll(clip,cust_img_label);

		v_ac_id=show_left_form_field(v1);
		v_ac_id.setId("view_ac_id");
		v_ac_id.setStyle("-fx-font-style: 'Noto Sans';");
		vcform1.setVgrow(v_ac_id, Priority.NEVER);
		
		v_fname=show_left_form_field(v12);
		v_fname.setId("view_fname");
		v_fname.setStyle("-fx-font-style: 'Noto Sans';");
		vcform1.setVgrow(v_fname, Priority.NEVER);
		
		v_lname=show_left_form_field(v13);
		v_lname.setId("view_lname");
		v_lname.setStyle("-fx-font-style: 'Noto Sans';");
		vcform1.setVgrow(v_lname, Priority.NEVER);
		
		v_phone=show_left_form_field(v15);
		v_phone.setId("view_phone");
		v_phone.setStyle("-fx-font-style: 'Noto Sans';");
		vcform1.setVgrow(v_phone, Priority.NEVER);
		
		v_cust_tc=show_left_form_field(v4);
		v_cust_tc.setId("view_cust_tc");
		v_cust_tc.setStyle("-fx-font-style: 'Noto Sans';");
		vcform1.setVgrow(v_cust_tc, Priority.NEVER);
		
		v_email=show_left_form_field(v14);
		v_email.setId("view_email");
		v_email.setStyle("-fx-font-style: 'Noto Sans';");
		vcform1.setVgrow(v_email, Priority.NEVER);
		
		vcform1.getChildren().addAll(v_profile_pic, v_ac_id, v_fname, v_lname, v_phone, v_cust_tc, v_email);
		
		vcform_section_1.getChildren().add(vcform1);
		vcform_section_1.setTopAnchor(vcform1, 20.0);
		vcform_section_1.setBottomAnchor(vcform1, 60.0);
		
		//VBox section 2
		vcform2 = new VBox();
		hbox_for_vcform_vbox.setHgrow(vcform2, Priority.ALWAYS);
		vcform2.setSpacing(10);
		
		v_addr1=show_form_field(v6);
		v_addr1.setId("view_addr_l1");
		v_addr1.setStyle("-fx-font-style: 'Noto Sans';");
		vcform2.setVgrow(v_addr1, Priority.NEVER);
		
		v_addr2=show_form_field(v7);
		v_addr2.setId("view_addr_l2");
		v_addr2.setStyle("-fx-font-style: 'Noto Sans';");
		vcform2.setVgrow(v_addr2, Priority.NEVER);
		
		v_city=show_form_field(v9);
		v_city.setId("view_city");
		v_city.setStyle("-fx-font-style: 'Noto Sans';");
		vcform2.setVgrow(v_city, Priority.NEVER);
		
		v_state=show_form_field(v10);
		v_state.setId("view_state");
		v_state.setStyle("-fx-font-style: 'Noto Sans';");
		vcform2.setVgrow(v_state, Priority.NEVER);
		
		v_country=show_form_field(v11);
		v_country.setId("view_country");
		v_country.setStyle("-fx-font-style: 'Noto Sans';");
		vcform2.setVgrow(v_country, Priority.NEVER);
		
		v_zip=show_form_field(v8);
		v_zip.setId("view_zipcode");
		v_zip.setStyle("-fx-font-style: 'Noto Sans';");
		vcform2.setVgrow(v_zip, Priority.NEVER);
		
		v_skey=show_form_field(v2);
		v_skey.setId("view_skey");
		v_skey.setStyle("-fx-font-style: 'Noto Sans';");
		vcform2.setVgrow(v_skey, Priority.NEVER);
		
		v_cred_limit=show_form_field(Double.toString(v5));
		v_cred_limit.setId("view_cred_limit");
		v_cred_limit.setStyle("-fx-font-style: 'Noto Sans';");
		vcform2.setVgrow(v_cred_limit, Priority.NEVER);
		
		v_visible=new AnchorPane();
		v_visible.setId("view_visible");
		v_visible.setStyle("-fx-font-style: 'Noto Sans';");
		cb_visible=new CheckBox("Visible"){
			@Override
			public void arm() {
				// intentionally do nothing
			}
		};
		
		if(v18==true)
			cb_visible.setSelected(true);
		else
			cb_visible.setSelected(false);

		v_visible.getChildren().add(cb_visible);
		v_visible.setTopAnchor(cb_visible, 10.0);
		v_visible.setLeftAnchor(cb_visible, 10.0);

		v_notes=new AnchorPane();
		jta_notes=new JFXTextArea();
		jta_notes.setPrefWidth(300);
		jta_notes.setPrefHeight(150);
		jta_notes.setText(v17);
		jta_notes.setId("view_notes");
		jta_notes.setStyle("-fx-font-style: 'Noto Sans';");
		jta_notes.setEditable(false);
		
		v_notes.getChildren().add(jta_notes);
		v_notes.setTopAnchor(jta_notes, 10.0);
		v_notes.setLeftAnchor(jta_notes, 18.0);
		v_notes.setBottomAnchor(jta_notes, 0.0);
		v_notes.setRightAnchor(jta_notes, 10.0);
		
		vcform2.getChildren().addAll(v_addr1, v_addr2, v_city, v_state, v_country, v_zip, v_skey, v_cred_limit, v_visible, v_notes);

		vcform_section_2.getChildren().add(vcform2);
		vcform_section_2.setTopAnchor(vcform2, 20.0);
		vcform_section_2.setBottomAnchor(vcform2, 60.0);

		//VBox section 3
		vcform3=new VBox();
		hbox_for_vcform_vbox.setHgrow(vcform3, Priority.ALWAYS);
		vcform3.setSpacing(10);
		
		v_curdebt=show_form_field(Double.toString(v21));
		v_curdebt.setId("view_curdebt");
		v_curdebt.setStyle("-fx-font-style: 'Noto Sans';");
		vcform3.setVgrow(v_curdebt, Priority.NEVER);
		
		try{
			String query="SELECT receipts.amount FROM taxline,receipts,tickets,customers WHERE customers.id='"+v1+"' AND customers.id=tickets.costumer AND tickets.ticketid=receipts.ticketid AND receipts.ticketid=taxline.receipt";
			rs=s.executeQuery(query);
			while(rs.next()){
				totsales+=rs.getDouble(1);
			}
		}catch(Exception e){
			e.getMessage();
		}
		v_totalsales=show_form_field(Double.toString(totsales));
		v_totalsales.setId("view_totsales");
		v_totalsales.setStyle("-fx-font-style: 'Noto Sans';");
		vcform3.setVgrow(v_totalsales, Priority.NEVER);
		
		v_transaction=new AnchorPane();
		v_transaction.setPrefWidth(390);
		v_transaction.setPrefHeight(200);
		
		Label view_transaction=new Label("Transactions:");
		view_transaction.setPrefWidth(390);
		view_transaction.setPrefHeight(30);
		view_transaction.setId("view_transaction");
		view_transaction.setStyle("-fx-font-style: 'Noto Sans';");
		
		ScrollPane sp_transaction=new ScrollPane();
		sp_transaction.setPrefWidth(390);
		sp_transaction.setPrefHeight(340);
		v_transaction.setTopAnchor(sp_transaction, 30.0);
		
		TableView<Transaction> tv_transaction=new TableView<Transaction>();
		try{
			String q1="SELECT ticketid FROM tickets,customers WHERE id='"+v1+"' AND tickets.costumer=customers.id";
			rs=s.executeQuery(q1);
			while(rs.next()){
				tbl_tid=rs.getInt(1);
			}
			
			String q2="SELECT datetime FROM receipts WHERE tickets.costumer='"+v1+"' AND receipts.ticketid=tickets.ticketid";
			rs=s.executeQuery(q2);
			while(rs.next()){
				tbl_date=rs.getString(1);
			}
			
			String q3="SELECT product,unit,price FROM ticketline WHERE tickets.ticketid='"+tbl_tid+"' AND ticketline.ticket=tickets.ticketid";
			rs=s.executeQuery(q3);
			while(rs.next()){
				tbl_prod=rs.getString(1);
				tbl_qty=rs.getInt(2);
				tbl_tot=rs.getDouble(3);
			}
		}catch(Exception e){
			e.getMessage();
		}
		
		transact_tbl=addData(tbl_tid,tbl_date,tbl_prod,tbl_qty,tbl_tot);
		tv_transaction=item(transact_tbl);
		
		sp_transaction.setContent(tv_transaction);
		v_transaction.getChildren().addAll(view_transaction,sp_transaction);
		
		v_editbtn=new AnchorPane();
		v_editbtn.setPrefWidth(260);
		v_editbtn.setPrefHeight(40);
		
		JFXButton editbtn=new JFXButton();
		editbtn.setText("Edit");
		editbtn.setPrefWidth(260);
		editbtn.setPrefHeight(40);
		editbtn.setId("editbtn");
		editbtn.setStyle("-fx-font-style: 'Noto Sans';");
		
		v_editbtn.getChildren().add(editbtn);
		
		editbtn.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				edit_customer();
			}
			
		});
		
		vcform3.getChildren().addAll(v_curdebt, v_totalsales, v_transaction, v_editbtn);
		
		vcform_section_3.getChildren().add(vcform3);
		vcform_section_3.setTopAnchor(vcform3, 20.0);
		vcform_section_3.setBottomAnchor(vcform3, 60.0);
		
		hbox_for_vcform_vbox.getChildren().addAll(vcform_section_1, vcform_section_2, vcform_section_3);
		
		close_view.getChildren().addAll(vc_top,hbox_for_vcform_vbox);
	}
	
	public ObservableList<Transaction> addData(int t,String d,String p,int q,double tot)
	{	   
		   ObservableList<Transaction> data;	   
	    	al.add(new Transaction(t, d, p, q, tot));
	        data=FXCollections.observableList(al);
			return data;
	}
	
	@SuppressWarnings("unchecked")
	public TableView<Transaction> item(ObservableList<Transaction> data)
	{
	   	 TableView<Transaction> table  = new TableView<Transaction>();
	   	 table.setItems(data);

	   	 TableColumn<Transaction, Integer> ticketIdCol = new TableColumn<Transaction, Integer>("Ticket ID");
	   	 ticketIdCol.setMinWidth(60);
	   	 ticketIdCol.setCellValueFactory(new PropertyValueFactory<>("TicketId"));
    
	   	 TableColumn<Transaction, String> dateCol = new TableColumn<Transaction, String>("Date");
	   	 dateCol.setMinWidth(80);
	   	 dateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));

	   	 TableColumn<Transaction, String> productsCol = new TableColumn<Transaction, String>("Products");
	   	 productsCol.setMinWidth(80);
	   	 productsCol.setCellValueFactory(new PropertyValueFactory<>("Products"));
    
	   	 TableColumn<Transaction, Integer> quantityCol = new TableColumn<Transaction, Integer>("Quantity");
	   	 quantityCol.setMinWidth(60);
	   	 quantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));

	   	 TableColumn<Transaction, Double> totalCol = new TableColumn<Transaction, Double>("Total");
	   	 totalCol.setMinWidth(80);
	   	 totalCol.setCellValueFactory(new PropertyValueFactory<>("Total"));    
   
	   	 table.getColumns().addAll(ticketIdCol, dateCol, productsCol, quantityCol, totalCol);	     
    
	   	 return table;
    }
	
	public static class Transaction{
		
		private final SimpleIntegerProperty TicketId;
		private final SimpleStringProperty Date;
		private final SimpleStringProperty Products;
		private final SimpleIntegerProperty Quantity;
		private final SimpleDoubleProperty Total;
		
		Transaction(int cTicketId, String cDate, String cProducts, int cQuantity, double cTotal){
			this.TicketId=new SimpleIntegerProperty(cTicketId);
			this.Date=new SimpleStringProperty(cDate);
			this.Products=new SimpleStringProperty(cProducts);
			this.Quantity=new SimpleIntegerProperty(cQuantity);
			this.Total=new SimpleDoubleProperty(cTotal);
		}
		
		//GETTER AND SETTER METHODS FOR TICKETID
		public int getTicketId(){
			return TicketId.get();
		}
		public void setTicketId(int cTicketId){
			TicketId.set(cTicketId);
		}
		
		//GETTER AND SETTER METHODS FOR DATE
		public String getDate(){
			return Date.get();
		}
		public void setDate(String cDate){
			Date.set(cDate);
		}
		
		//GETTER AND SETTER METHODS FOR PRODUCTS
		public String getProducts(){
			return Products.get();
		}
		public void setProducts(String cProducts){
			Products.set(cProducts);
		}

		//GETTER AND SETTER METHODS FOR QUANTITY
		public int getQuantity(){
			return Quantity.get();
		}
		public void setQuantity(int cQuantity){
			Quantity.set(cQuantity);
		}

		//GETTER AND SETTER METHODS FOR TOTAL
		public double getTotal(){
			return Total.get();
		}
		public void setTotal(double cTotal){
			Total.set(cTotal);
		}
		
	}

	public AnchorPane create_edit_customer(){
		AnchorPane editpane=new AnchorPane();
		
		//top bar
		AnchorPane ec_top = new AnchorPane();
		ec_top.setPrefHeight(40);
		ec_top.setPrefWidth(95);
		editpane.setTopAnchor(ec_top,0.0);
		editpane.setLeftAnchor(ec_top,0.0);
		editpane.setRightAnchor(ec_top,0.0);
		ec_top.setId("top");
		
		Label edit_cust_menu = new Label("Edit Customer:  "+v3);
		edit_cust_menu.setPrefHeight(40);
		edit_cust_menu.setPrefWidth(495);
		edit_cust_menu.setAlignment(Pos.CENTER);
		ec_top.getChildren().add(edit_cust_menu);
		ec_top.setTopAnchor(edit_cust_menu,0.0);
		ec_top.setLeftAnchor(edit_cust_menu,0.0);
		ec_top.setRightAnchor(edit_cust_menu,0.0);
		ec_top.setBottomAnchor(edit_cust_menu,0.0);
		edit_cust_menu.setId("head");		

		//close button
		Image img = new Image(getClass().getResource("ic_close_white_36dp.png").toExternalForm(),25,25,false,false);
		ImageView iv_close = new ImageView(img);
		Button close_edit_cust = new Button("",iv_close);
		close_edit_cust.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
					ap_main_center.getChildren().removeAll(editpane);
					//ap_left.getChildren().remove(sp_result);
					//count=0;
				}
		});
		close_edit_cust.setId("closebutton");
		ec_top.getChildren().add(close_edit_cust);
		ec_top.setRightAnchor(close_edit_cust,0.0);
		ec_top.setTopAnchor(close_edit_cust,2.0);
		
		hbox_for_ecform_vbox = new HBox();
		hbox_for_ecform_vbox.setSpacing(40);
		editpane.setTopAnchor(hbox_for_ecform_vbox, 50.0);
		editpane.setLeftAnchor(hbox_for_ecform_vbox, 50.0);
		editpane.setBottomAnchor(hbox_for_ecform_vbox, 10.0);
		editpane.setRightAnchor(hbox_for_ecform_vbox, 50.0);
		
		ecform_section_1 = new AnchorPane();	//anchorpane for form section1	
		ecform_section_1.setId("ap_left_view");
		ecform_section_2 = new AnchorPane();	//anchorpane for form section2
		ecform_section_3 = new AnchorPane();	//anchorpane for form section3
		
		ecform1 = new VBox();
		hbox_for_ecform_vbox.setHgrow(ecform1, Priority.ALWAYS);
		ecform1.setSpacing(5);
		
		e_profile_pic = new AnchorPane();
		e_profile_pic.setPrefHeight(270);
		e_profile_pic.setPrefWidth(400);
		
		//image display area
		ce_img = new Image(getClass().getResource("customer_icon.png").toExternalForm(),100,100,false,false);
		ImagePattern pat = new ImagePattern(ce_img);
		Circle clip = new Circle(200,100,100);
		clip.setFill(pat);
		
		StackPane cust_img_label = new StackPane();
		Label cust_img = new Label("Profile Picture");
		cust_img.setId("left_form");
		cust_img_label.getChildren().add(cust_img);
		cust_img_label.setAlignment(cust_img,Pos.CENTER);
		e_profile_pic.setBottomAnchor(cust_img_label,0.0);
		e_profile_pic.setLeftAnchor(cust_img_label,0.0);
		e_profile_pic.setRightAnchor(cust_img_label,0.0);
		
		//creating image selector
		Button image_select = new Button();
		image_select.setId("image_select");
		image_select.setPrefWidth(40);
		image_select.setPrefHeight(40);
		e_profile_pic.setRightAnchor(image_select,100.0);
		e_profile_pic.setTopAnchor(image_select,150.0);
		
		image_select.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event){
			FileChooser customer_pic = new FileChooser();
			configureFileChooser(customer_pic);
			customer_pic.setTitle("Select Profile Picture");
			File new_cust_image = customer_pic.showOpenDialog(stage);
			if(new_cust_image!=null){
				//openFile(new_pro_image);
							
				BufferedImage imga = null;
				try {
					imga = ImageIO.read(new_cust_image);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImagePattern pat = new ImagePattern(SwingFXUtils.toFXImage(imga,null));
				clip.setFill(pat);					
			}
					
			}
		});
		
		e_profile_pic.getChildren().addAll(clip, cust_img_label, image_select);

		e_ac_id=show_left_form_field(v1);
		e_ac_id.setId("view_editac_id");
		e_ac_id.setStyle("-fx-font-style: 'Noto Sans';");
		ecform1.setVgrow(e_ac_id, Priority.NEVER);
		
		e_fname=create_editleft_form_field(v12);
		e_fname.setId("edit_left_fields");
		e_fname.setStyle("-fx-font-style: 'Noto Sans';");
		ecform1.setVgrow(e_fname, Priority.NEVER);
		
		e_lname=create_editleft_form_field(v13);
		e_lname.setId("edit_left_fields");
		e_lname.setStyle("-fx-font-style: 'Noto Sans';");
		ecform1.setVgrow(e_lname, Priority.NEVER);
		
		e_phone=create_editleft_form_field(v15);
		e_phone.setId("edit_left_fields");
		e_phone.setStyle("-fx-font-style: 'Noto Sans';");
		ecform1.setVgrow(e_phone, Priority.NEVER);
		
		e_cust_tc=create_editleft_form_field(v4);
		e_cust_tc.setId("edit_left_fields");
		e_cust_tc.setStyle("-fx-font-style: 'Noto Sans';");
		ecform1.setVgrow(e_cust_tc, Priority.NEVER);
		
		e_email=create_editleft_form_field(v14);
		e_email.setId("edit_left_fields");
		e_email.setStyle("-fx-font-style: 'Noto Sans';");
		ecform1.setVgrow(e_email, Priority.NEVER);
		
		ecform1.getChildren().addAll(e_profile_pic, e_ac_id, e_fname, e_lname, e_phone, e_cust_tc, e_email);
		
		ecform_section_1.getChildren().add(ecform1);
		ecform_section_1.setTopAnchor(ecform1, 20.0);
		ecform_section_1.setBottomAnchor(ecform1, 60.0);
		
		//VBox section 2
		ecform2 = new VBox();
		hbox_for_ecform_vbox.setHgrow(ecform2, Priority.ALWAYS);
		ecform2.setSpacing(5);
		
		e_addr1=create_edit_form_field(v6);
		e_addr1.setId("edit_fields");
		e_addr1.setStyle("-fx-font-style: 'Noto Sans';");
		ecform2.setVgrow(e_addr1, Priority.NEVER);
		
		e_addr2=create_edit_form_field(v7);
		e_addr2.setId("edit_fields");
		e_addr2.setStyle("-fx-font-style: 'Noto Sans';");
		ecform2.setVgrow(e_addr2, Priority.NEVER);
		
		e_city=create_edit_form_field(v9);
		e_city.setId("edit_fields");
		e_city.setStyle("-fx-font-style: 'Noto Sans';");
		ecform2.setVgrow(e_city, Priority.NEVER);
		
		e_state=create_edit_form_field(v10);
		e_state.setId("edit_fields");
		e_state.setStyle("-fx-font-style: 'Noto Sans';");
		ecform2.setVgrow(e_state, Priority.NEVER);
		
		e_country=create_edit_form_field(v11);
		e_country.setId("edit_fields");
		e_country.setStyle("-fx-font-style: 'Noto Sans';");
		ecform2.setVgrow(e_country, Priority.NEVER);
		
		e_zip=create_edit_form_field(v8);
		e_zip.setId("edit_fields");
		e_zip.setStyle("-fx-font-style: 'Noto Sans';");
		ecform2.setVgrow(e_zip, Priority.NEVER);
		
		e_skey=create_edit_form_field(v2);
		e_skey.setId("edit_filds");
		e_skey.setStyle("-fx-font-style: 'Noto Sans';");
		ecform2.setVgrow(e_skey, Priority.NEVER);
		
		e_cred_limit=create_edit_form_field(Double.toString(v5));
		e_cred_limit.setId("edit_fields");
		e_cred_limit.setStyle("-fx-font-style: 'Noto Sans';");
		ecform2.setVgrow(e_cred_limit, Priority.NEVER);
		
		e_visible=new AnchorPane();
		e_visible.setId("edit_fields");
		e_visible.setStyle("-fx-font-style: 'Noto Sans';");
		edit_visible=new CheckBox("Visible");
		
		if(v18==true)
			edit_visible.setSelected(true);
		else
			edit_visible.setSelected(false);
		
		e_visible.getChildren().add(edit_visible);
		e_visible.setTopAnchor(edit_visible, 10.0);
		e_visible.setLeftAnchor(edit_visible, 10.0);

		e_notes=new AnchorPane();
		edit_notes=new JFXTextArea();
		edit_notes.setPrefWidth(300);
		edit_notes.setPrefHeight(100);
		edit_notes.setText(v17);
		edit_notes.setId("edit_fields");
		edit_notes.setStyle("-fx-font-style: 'Noto Sans';");
		
		e_notes.getChildren().add(edit_notes);
		e_notes.setTopAnchor(edit_notes, 10.0);
		e_notes.setLeftAnchor(edit_notes, 18.0);
		e_notes.setBottomAnchor(edit_notes, 0.0);
		e_notes.setRightAnchor(edit_notes, 10.0);
		
		ecform2.getChildren().addAll(e_addr1, e_addr2, e_city, e_state, e_country, e_zip, e_skey, e_cred_limit, e_visible, e_notes);

		ecform_section_2.getChildren().add(ecform2);
		ecform_section_2.setTopAnchor(ecform2, 20.0);
		ecform_section_2.setBottomAnchor(ecform2, 60.0);

		//VBox section 3
		ecform3=new VBox();
		hbox_for_ecform_vbox.setHgrow(ecform3, Priority.ALWAYS);
		ecform3.setSpacing(10);
		
		e_curdebt=create_edit_form_field(Double.toString(v21));
		e_curdebt.setId("edit_fields");
		e_curdebt.setStyle("-fx-font-style: 'Noto Sans';");
		ecform3.setVgrow(e_curdebt, Priority.NEVER);
	
		e_totalsales=create_edit_form_field(Double.toString(totsales));
		e_totalsales.setId("edit_fields");
		e_totalsales.setStyle("-fx-font-style: 'Noto Sans';");
		ecform3.setVgrow(e_totalsales, Priority.NEVER);
		
		e_transaction=new AnchorPane();
		e_transaction.setPrefWidth(390);
		e_transaction.setPrefHeight(200);
		
		Label view_transaction=new Label("Transactions:");
		view_transaction.setPrefWidth(390);
		view_transaction.setPrefHeight(30);
		view_transaction.setId("view_transaction");
		
		ScrollPane sp_transaction=new ScrollPane();
		sp_transaction.setPrefWidth(390);
		sp_transaction.setPrefHeight(340);
		e_transaction.setTopAnchor(sp_transaction, 30.0);
		
		TableView<Transaction> tv_transaction=new TableView<Transaction>();
		transact_tbl=addData(tbl_tid,tbl_date,tbl_prod,tbl_qty,tbl_tot);
		tv_transaction=item(transact_tbl);
		
		sp_transaction.setContent(tv_transaction);
		e_transaction.getChildren().addAll(view_transaction,sp_transaction);
		
		e_savebtn=new AnchorPane();
		e_savebtn.setPrefWidth(260);
		e_savebtn.setPrefHeight(40);
		
		JFXButton savebtn=new JFXButton();
		savebtn.setText("Save");
		savebtn.setPrefWidth(260);
		savebtn.setPrefHeight(40);
		savebtn.setId("editbtn");
		
		e_savebtn.getChildren().add(savebtn);
		
		savebtn.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				update_customer(v1);
			}
		});
		
		ecform3.getChildren().addAll(e_curdebt, e_totalsales, e_transaction, e_savebtn);

		ecform_section_3.getChildren().add(ecform3);
		ecform_section_3.setTopAnchor(ecform3, 20.0);
		ecform_section_3.setBottomAnchor(ecform3, 60.0);

		hbox_for_ecform_vbox.getChildren().addAll(ecform_section_1, ecform_section_2, ecform_section_3);
		
		editpane.getChildren().addAll(ec_top, hbox_for_ecform_vbox);
		
		return editpane;
	}
	
	public void edit_customer(){
		edit_cust=create_edit_customer();
		edit_cust.setId("apcenter");
		
		ap_main_center.getChildren().add(edit_cust);
		ap_main_center.setTopAnchor(edit_cust, 15.0);
		ap_main_center.setRightAnchor(edit_cust, 15.0);
		ap_main_center.setBottomAnchor(edit_cust, 15.0);
		ap_main_center.setLeftAnchor(edit_cust, 15.0);
	}
	
	public void update_customer(String id){
		try{
			child=e_ac_id.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			uid=tf.getText();

			child=e_skey.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			us_key=tf.getText();

			child=e_fname.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			ufn=tf.getText();
			
			child=e_lname.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			uln=tf.getText();
			
			uname=ufn+" "+uln;
		
			child=e_cust_tc.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			utaxcat=tf.getText();
			
			child=e_cred_limit.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			umaxdebt=tf.getText();
		
			child=e_addr1.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			uaddr=tf.getText();
			
			child=e_addr2.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			uaddr2=tf.getText();
		
			child=e_zip.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			upostal=tf.getText();
			
			child=e_city.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			ucty=tf.getText();
			
			child=e_state.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			uregion=tf.getText();
			
			child=e_country.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			ucntry=tf.getText();
		
			child=e_email.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			umail=tf.getText();
		
			child=e_phone.getChildren().get(0);
			if(child instanceof JFXTextField)
				tf=(JFXTextField) child;
			ucontact=tf.getText();
		
			child=e_notes.getChildren().get(0);
			if(child instanceof TextArea)
				ta=(TextArea) child;
			unt=ta.getText();
		
			child=e_visible.getChildren().get(0);
			if(child instanceof CheckBox)
				cb=(CheckBox) child;
			if(cb.isSelected())
				uvsble=true;
			else
				uvsble=false;
			
        	String query="UPDATE customers SET id='"+uid+"',searchkey='"+us_key+"',name='"+uname+"',taxcat='"+utaxcat+"',maxdebt="+umaxdebt+",address='"+uaddr+"',addr2='"+uaddr2+"',city='"+ucty+"',postal='"+upostal+"',region='"+uregion+"',country='"+ucntry+"',fn='"+ufn+"',ln='"+uln+"',mail='"+umail+"',phone='"+ucontact+"',notes='"+unt+"',visible="+uvsble+" WHERE id='"+id+"';";
			s.executeUpdate(query);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	public AnchorPane create_customer_debtors(){
		AnchorPane cbpane=new AnchorPane();
		
		//top bar
		AnchorPane cb_top = new AnchorPane();
		cb_top.setPrefHeight(40);
		cb_top.setPrefWidth(95);
		cbpane.setTopAnchor(cb_top,0.0);
		cbpane.setLeftAnchor(cb_top,0.0);
		cbpane.setRightAnchor(cb_top,0.0);
		cb_top.setId("top");
	
		Label search_custdebt_menu = new Label("Customer Debtors");
		search_custdebt_menu.setPrefHeight(40);
		search_custdebt_menu.setPrefWidth(495);
		search_custdebt_menu.setAlignment(Pos.CENTER);
		cb_top.getChildren().add(search_custdebt_menu);
		cb_top.setTopAnchor(search_custdebt_menu,0.0);
		cb_top.setLeftAnchor(search_custdebt_menu,0.0);
		cb_top.setRightAnchor(search_custdebt_menu,0.0);
		cb_top.setBottomAnchor(search_custdebt_menu,0.0);
		search_custdebt_menu.setId("head");		

		//close button
		Image img = new Image(getClass().getResource("ic_close_white_36dp.png").toExternalForm(),25,25,false,false);
		ImageView iv_close = new ImageView(img);
		Button close_cust_debt = new Button("",iv_close);
		close_cust_debt.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
					ap_left.getChildren().removeAll(cbpane);
					ap_right.getChildren().remove(cd_report);
					//ap_left.getChildren().remove(sp_result);
					//count=0;
				}
		});
		close_cust_debt.setId("closebutton");
		cb_top.getChildren().add(close_cust_debt);
		cb_top.setRightAnchor(close_cust_debt,0.0);
		cb_top.setTopAnchor(close_cust_debt,2.0);
		
		cbform=new VBox();
		cbform.setSpacing(10);
		
		cbpane.setTopAnchor(cbform, 70.0);
		cbpane.setRightAnchor(cbform, 50.0);
		cbpane.setBottomAnchor(cbform, 0.0);
		cbpane.setLeftAnchor(cbform, 50.0);
			
		search_note=show_form_field("Search by: (Select from the drop-down list)");
		cbform.setVgrow(search_note, Priority.NEVER);
			
		p_cb_searchbox=new AnchorPane();
		p_cb_searchbox.setPrefWidth(300);
		p_cb_searchbox.setPrefHeight(40);
		p_cb_searchbox.setId("p_searchbox");
		AnchorPane.setLeftAnchor(p_cb_searchbox, 70.0);
		cbform.setVgrow(p_cb_searchbox, Priority.NEVER);
			
		search=new ImageView(getClass().getResource("ic_search_black_24dp.png").toExternalForm());
		search.setId("search");
		search.setFitWidth(40.0);
		search.setFitHeight(40.0);
		p_cb_searchbox.setLeftAnchor(search, 0.0);
		p_cb_searchbox.setTopAnchor(search, 2.0);
		p_cb_searchbox.setRightAnchor(search, 260.0);
		p_cb_searchbox.setBottomAnchor(search, 0.0);
			
		cb_searchlist=new ComboBox<String>();
		cb_searchlist.setId("searchlist");
		cb_searchlist.setStyle("-fx-font-style: 'Noto Sans';");
		cb_searchlist.setPrefWidth(20.0);
		cb_searchlist.setPrefHeight(40.0);
        cb_searchlist.getItems().setAll("Mobile No.","Name","Account ID");
        cb_searchlist.setVisibleRowCount(3);
        p_cb_searchbox.setTopAnchor(cb_searchlist, 0.0);
        p_cb_searchbox.setRightAnchor(cb_searchlist, 0.0);
        p_cb_searchbox.setBottomAnchor(cb_searchlist, 1.0);

        cb_search=new TextField();
        cb_search.setPromptText("Enter Mobile No.");
        cb_searchlist.getSelectionModel().selectFirst();
		cb_search.setAlignment(Pos.CENTER_LEFT);
		cb_search.setPadding(new Insets(0,0,0,10));
		cb_search.setFont(Font.font("Noto Sans", 18));
		cb_search.setPrefWidth(240.0);	
		cb_search.setPrefHeight(40.0);
		cb_search.setId("c_search");
		p_cb_searchbox.setTopAnchor(cb_search, 0.0);
		p_cb_searchbox.setRightAnchor(cb_search, 50.0);
		p_cb_searchbox.setBottomAnchor(cb_search, 0.0);
		p_cb_searchbox.setLeftAnchor(cb_search, 40.0);
		cb_searchlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

		@Override
		public void changed(ObservableValue<? extends String> observable,
				String oldValue, String newValue) {
			if(newValue!=null){
				switch(newValue){
				case "Mobile No.":{ cb_search.clear();
									cb_search.setPromptText("Enter Mobile No.");
								  }
								  break;
				case "Name":	  { cb_search.clear();
							  	    cb_search.setPromptText("Enter Name");
								  }
							      break;
				case "Account ID":{ cb_search.clear();
									cb_search.setPromptText("Enter Account ID");
								  }
								  break;
				default:		  break;
				}
			}	
		}
		});

		p_cb_searchbox.getChildren().addAll(search,cb_search,cb_searchlist);
		/*p_searchbox.setLeftAnchor(c_search, 90.0);
		p_searchbox.setLeftAnchor(search, 90.0);
		p_searchbox.setLeftAnchor(searchlist, 90.0);
		*/
		p_cb_search_btn=new AnchorPane();
		p_cb_search_btn.setPrefWidth(200);
		p_cb_search_btn.setPrefHeight(30);
		p_cb_search_btn.setId("p_search_btn");
		cbform.setVgrow(p_cb_search_btn, Priority.NEVER);
		
		cb_search_btn=new JFXButton();
		cb_search_btn.setText("Run Report");
		cb_search_btn.setPrefWidth(200);
		cb_search_btn.setPrefHeight(30);
		cb_search_btn.setId("search_btn");
		
		p_cb_search_btn.getChildren().add(cb_search_btn);
		p_cb_search_btn.setRightAnchor(cb_search_btn, 180.0);

		cbform.getChildren().addAll(search_note, p_cb_searchbox, p_cb_search_btn);
		
		cbpane.getChildren().addAll(cb_top, cbform);
		
		cb_search_btn.setOnAction(event->{
			//run_report_btn.setVisible(false);
			if(debtors_tbl!=null)
				debtors_tbl.clear();
			
			if(count_cd==0)
			{
				run_cd_report(cb_search.getText());
				count_cd++;				
			}	
			count_cd=0;
		});
		
		return cbpane;
	}
	
	public void customer_debtors(){
		cust_debtors=create_customer_debtors();
		cust_debtors.setId("apcenter");
		
		ap_left.getChildren().add(cust_debtors);
		ap_left.setTopAnchor(cust_debtors,0.0);
		ap_left.setBottomAnchor(cust_debtors,0.0);
		ap_left.setLeftAnchor(cust_debtors,0.0);
		ap_left.setRightAnchor(cust_debtors,0.0);
	}

	public void run_cd_report(String cd_search){
		cd_report=new AnchorPane();
		cd_report.setId("apcenter");
		
		ap_right.setTopAnchor(cd_report, 0.0);
		ap_right.setRightAnchor(cd_report, 0.0);
		ap_right.setBottomAnchor(cd_report, 0.0);
		ap_right.setLeftAnchor(cd_report, 0.0);
		
		AnchorPane ap_lower=new AnchorPane();
		ap_lower.setPrefWidth(498);
		ap_lower.setPrefHeight(150);
		ap_lower.setId("ap_lower");
		cd_report.setLeftAnchor(ap_lower, 0.0);
		cd_report.setBottomAnchor(ap_lower, 0.0);
		cd_report.setRightAnchor(ap_lower, 0.0);
		
		AnchorPane ap_cd_title=new AnchorPane();
		ap_cd_title.setPrefWidth(250);
		ap_cd_title.setPrefHeight(50);
		ap_cd_title.setId("ap_cd_title");
		cd_report.setLeftAnchor(ap_cd_title, 0.0);
		
		Label cd_report_title=new Label();
		cd_report_title.setText("CUSTOMER DEBTORS");
		cd_report_title.setPrefWidth(250);
		cd_report_title.setPrefHeight(40);
		cd_report_title.setId("cd_report_title");
		cd_report_title.setStyle("-fx-font-style: 'Noto Sans';");
		ap_cd_title.setTopAnchor(cd_report_title, 10.0);
		
		ap_cd_title.getChildren().add(cd_report_title);
		
		AnchorPane ap_cd_timestamp=new AnchorPane();
		ap_cd_timestamp.setPrefWidth(200);
		ap_cd_timestamp.setPrefHeight(60);
		ap_cd_timestamp.setId("ap_cd_timestamp");
		cd_report.setRightAnchor(ap_cd_timestamp, 0.0);
		
		Label printed=new Label("Printed");
		printed.setPrefWidth(200);
		printed.setPrefHeight(25);
		printed.setId("printed");
		printed.setStyle("-fx-font-style: 'Noto Sans';");
		ap_cd_timestamp.setTopAnchor(printed, 10.0);
		
		Label timestamp=new Label();
		date = new Date();
    	final DateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
    	time = timeFormat.format(date);
    	timestamp.setText(time);
    	timestamp.setPrefWidth(200);
    	timestamp.setPrefHeight(25);
    	timestamp.setId("timestamp");
    	timestamp.setStyle("-fx-font-style: 'Noto Sans';");
    	ap_cd_timestamp.setTopAnchor(timestamp, 35.0);

    	ap_cd_timestamp.getChildren().addAll(printed, timestamp);
    	
    	JFXButton cd_print=new JFXButton("Print");
    	cd_print.setPrefWidth(100);
    	cd_print.setPrefHeight(40);
    	cd_print.setId("cd_print");
    	cd_print.setStyle("-fx-font-style: 'Noto Sans';");
    	cd_report.setBottomAnchor(cd_print, 10.0);
    	cd_report.setRightAnchor(cd_print, 170.0);
    	
    	JFXButton cd_save_pdf=new JFXButton("Save as PDF");
    	cd_save_pdf.setPrefWidth(150);
    	cd_save_pdf.setPrefHeight(40);
    	cd_save_pdf.setId("cd_save_pdf");
    	cd_save_pdf.setStyle("-fx-font-style: 'Noto Sans';");
    	cd_report.setBottomAnchor(cd_save_pdf, 10.0);
    	cd_report.setRightAnchor(cd_save_pdf, 10.0);
    	
    	Separator sepUpper=new Separator(Orientation.HORIZONTAL);
    	sepUpper.setMinWidth(490);
    	sepUpper.setId("sepUpper");
    	cd_report.setTopAnchor(sepUpper, 40.0);
		cd_report.setRightAnchor(sepUpper, 4.0);
		cd_report.setLeftAnchor(sepUpper, 4.0);
		
		ap_lower.getChildren().addAll(ap_cd_title, ap_cd_timestamp, cd_print, cd_save_pdf);
			
		ScrollPane sp_cdebt_report=new ScrollPane();
		sp_cdebt_report.setPrefWidth(360);
		sp_cdebt_report.setPrefHeight(400);
		sp_cdebt_report.setStyle("-fx-background-color:transparent;");
		sp_cdebt_report.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		sp_cdebt_report.setFitToWidth(true);
		sp_cdebt_report.setFitToHeight(true);
		cd_report.setRightAnchor(sp_cdebt_report, 20.0);
		cd_report.setBottomAnchor(sp_cdebt_report, 200.0);
		cd_report.setLeftAnchor(sp_cdebt_report, 20.0);
		
		tv_cdebt_report=new TableView<DebtorsReport>();
		tv_cdebt_report.setId("tv_cdebt_report");
		tv_cdebt_report.getStyleClass().add("table-column");
		try{
			String query;
			c=obj.connect();
			s=c.createStatement();

			if(!cd_search.isEmpty())
				query="SELECT id,name,address,curdebt FROM customers WHERE phone='"+cd_search+"' ORDER BY name;";
			else
				query="SELECT id,name,address,curdebt FROM customers ORDER BY name;";
			rs=s.executeQuery(query);		
			while(rs.next()){
				tbl_id=rs.getString(1);
				tbl_name=rs.getString(2);
				tbl_addr=rs.getString(3);
				tbl_cdebt=rs.getDouble(4);

				debtors_tbl=addData_DR(tbl_id,tbl_name,tbl_addr,tbl_cdebt);

			}
		}catch(Exception e){
			e.getMessage();
		}
		
		tv_cdebt_report=item_DR(debtors_tbl);
		sp_cdebt_report.setContent(tv_cdebt_report);
		sp_cdebt_report.fitToHeightProperty();
		
    	Separator sepLower=new Separator(Orientation.HORIZONTAL);
    	sepLower.setMinWidth(490);
    	sepLower.setId("sepLower");
		cd_report.setRightAnchor(sepLower, 4.0);
		cd_report.setBottomAnchor(sepLower, 180.0);
		cd_report.setLeftAnchor(sepLower, 4.0);

		cd_report.getChildren().addAll(ap_lower, sepUpper, sp_cdebt_report, sepLower);

		ap_right.getChildren().add(cd_report);
		
/*		cd_save_pdf.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
				try{
					BufferedImage bufImage=SwingFXUtils.fromFXImage(cd_report.snapshot(new SnapshotParameters(), null), null);
					FileOutputStream out=new FileOutputStream(new File("../temp.jpg"));
					javax.imageio.ImageIO.write(bufImage, "jpg", out);
					out.flush();
					out.close();
					
					com.itextpdf.text.Image image =com.itextpdf.text.Image.getInstance("../temp.jpg");
				    Document doc = new Document(new com.itextpdf.text.Rectangle(image.getScaledWidth(), image.getScaledHeight()));
				    FileOutputStream fos = new FileOutputStream(pdfFile);
				    PdfWriter.getInstance(doc, fos);
				    doc.open();
				    doc.newPage();
				    image.setAbsolutePosition(0, 0);
				    doc.add(image);
				    fos.flush();
				    doc.close();
				    fos.close();
				    
				}catch(Exception e){
					e.printStackTrace();
				}
			
			}
		});
*/		
	}
	
	public ObservableList<DebtorsReport> addData_DR(String a,String n,String ad,double cd)
	{	   
		   ObservableList<DebtorsReport> data_DR;	 
	    	al2.add(new DebtorsReport(a, n, ad, cd));
	        data_DR=FXCollections.observableList(al2);
			return data_DR;
	}

	@SuppressWarnings("unchecked")
	public TableView<DebtorsReport> item_DR(ObservableList<DebtorsReport> data_DR){
		TableView<DebtorsReport> table_DR  = new TableView<DebtorsReport>();
	   	table_DR.setItems(data_DR);
	   	 
	   	TableColumn<DebtorsReport, String> accountIdCol = new TableColumn<DebtorsReport, String>("Account ID");
	   	accountIdCol.setStyle("-fx-alignment: CENTER-LEFT;");
	   	accountIdCol.setMinWidth(90);
	   	accountIdCol.setCellValueFactory(new PropertyValueFactory<DebtorsReport, String>("AccountID"));

	   	TableColumn<DebtorsReport, String> nameCol = new TableColumn<DebtorsReport, String>("Name");
		nameCol.setStyle("-fx-alignment: CENTER;");
	   	nameCol.setMinWidth(190);
		nameCol.setCellValueFactory(new PropertyValueFactory<DebtorsReport, String>("Name"));
		 
		TableColumn<DebtorsReport, String> addrCol = new TableColumn<DebtorsReport, String>("Address");
		addrCol.setStyle("-fx-alignment: CENTER;");
		addrCol.setMinWidth(200);
		addrCol.setCellValueFactory(new PropertyValueFactory<DebtorsReport, String>("Address"));

		TableColumn<DebtorsReport, Double> curDebtCol = new TableColumn<DebtorsReport, Double>("Current Debt");
		curDebtCol.setStyle("-fx-alignment: CENTER-RIGHT;");
		curDebtCol.setMinWidth(110);
		curDebtCol.setCellValueFactory(new PropertyValueFactory<DebtorsReport, Double>("CurDebt"));
		 
		table_DR.getColumns().addAll(accountIdCol, nameCol, addrCol, curDebtCol);
		
		return table_DR;
	}
	
	public static class DebtorsReport{
		
		private final SimpleStringProperty AccountID;
		private final SimpleStringProperty Name;
		private final SimpleStringProperty Address;
		private final SimpleDoubleProperty CurDebt;
		
		DebtorsReport(String cd_acid,String cd_name,String cd_addr_l1,double cd_curdebt){
			this.AccountID=new SimpleStringProperty(cd_acid);
			this.Name=new SimpleStringProperty(cd_name);
			this.Address=new SimpleStringProperty(cd_addr_l1);
			this.CurDebt=new SimpleDoubleProperty(cd_curdebt);
		}
		
		//GETTER AND SETTER METHODS FOR ACCOUNT ID
		public String getAccountID(){
			return AccountID.get();
		}
		public void setAccountID(String cd_acid){
			AccountID.set(cd_acid);
		}
		
		//GETTER AND SETTER METHODS FOR NAME
		public String getName(){
			return Name.get();
		}
		public void setName(String cd_name){
			Name.set(cd_name);
		}
		
		//GETTER AND SETTER METHODS FOR ADDRESS
		public String getAddress(){
			return Address.get();
		}
		public void setAddress(String cd_addr_l1){
			Address.set(cd_addr_l1);
		}
		
		//GETTER AND SETTER METHODS FOR CURRENT DEBT
		public double getCurDebt(){
			return CurDebt.get();
		}
		public void setCurDebt(double cd_curdebt){
			CurDebt.set(cd_curdebt);
		}
	}

	public AnchorPane create_customers_diary(){
		AnchorPane cdpane=new AnchorPane();
		
		//top bar
		AnchorPane cd_top = new AnchorPane();
		cd_top.setPrefHeight(40);
		cd_top.setPrefWidth(95);
		cdpane.setTopAnchor(cd_top,0.0);
		cdpane.setLeftAnchor(cd_top,0.0);
		cdpane.setRightAnchor(cd_top,0.0);
		cd_top.setId("top");
		
		Label search_custdiary_menu = new Label("Customers Diary");
		search_custdiary_menu.setPrefHeight(40);
		search_custdiary_menu.setPrefWidth(495);
		search_custdiary_menu.setAlignment(Pos.CENTER);
		cd_top.getChildren().add(search_custdiary_menu);
		cd_top.setTopAnchor(search_custdiary_menu,0.0);
		cd_top.setLeftAnchor(search_custdiary_menu,0.0);
		cd_top.setRightAnchor(search_custdiary_menu,0.0);
		cd_top.setBottomAnchor(search_custdiary_menu,0.0);
		search_custdiary_menu.setId("head");		

		//close button
		Image img = new Image(getClass().getResource("ic_close_white_36dp.png").toExternalForm(),25,25,false,false);
		ImageView iv_close = new ImageView(img);
		Button close_cust_diary = new Button("",iv_close);
		close_cust_diary.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
					ap_left.getChildren().removeAll(cdpane);
					//ap_left.getChildren().remove(sp_result);
					//count=0;
				}
		});
		close_cust_diary.setId("closebutton");
		cd_top.getChildren().add(close_cust_diary);
		cd_top.setRightAnchor(close_cust_diary,0.0);
		cd_top.setTopAnchor(close_cust_diary,2.0);
		
		cdform=new VBox();
		cdform.setSpacing(10);
		
		cdpane.setTopAnchor(cdform, 70.0);
		cdpane.setRightAnchor(cdform, 50.0);
		cdpane.setBottomAnchor(cdform, 0.0);
		cdpane.setLeftAnchor(cdform, 50.0);
			
		search_note=show_form_field("Search by: (Select from the drop-down list)");
		cdform.setVgrow(search_note, Priority.NEVER);
			
		p_cd_searchbox=new AnchorPane();
		p_cd_searchbox.setPrefWidth(300);
		p_cd_searchbox.setPrefHeight(40);
		p_cd_searchbox.setId("p_searchbox");
		AnchorPane.setLeftAnchor(p_cd_searchbox, 70.0);
		cbform.setVgrow(p_cd_searchbox, Priority.NEVER);
			
		search=new ImageView(getClass().getResource("ic_search_black_24dp.png").toExternalForm());
		search.setId("search");
		search.setFitWidth(40.0);
		search.setFitHeight(40.0);
		p_cd_searchbox.setLeftAnchor(search, 0.0);
		p_cd_searchbox.setTopAnchor(search, 2.0);
		p_cd_searchbox.setRightAnchor(search, 260.0);
		p_cd_searchbox.setBottomAnchor(search, 0.0);
			
		cd_searchlist=new ComboBox<String>();
		cd_searchlist.setId("searchlist");
		cd_searchlist.setPrefWidth(20.0);
		cd_searchlist.setPrefHeight(40.0);
        cd_searchlist.getItems().setAll("Mobile No.","Name","Account ID");
        cd_searchlist.setVisibleRowCount(3);
        p_cd_searchbox.setTopAnchor(cd_searchlist, 0.0);
        p_cd_searchbox.setRightAnchor(cd_searchlist, 0.0);
        p_cd_searchbox.setBottomAnchor(cd_searchlist, 1.0);

        cd_search=new TextField();
        cd_search.setPromptText("Enter Mobile No.");
        cd_searchlist.getSelectionModel().selectFirst();
		cd_search.setAlignment(Pos.CENTER_LEFT);
		cd_search.setPadding(new Insets(0,0,0,10));
		cd_search.setFont(Font.font("Noto Sans", 18));
		cd_search.setPrefWidth(240.0);	
		cd_search.setPrefHeight(40.0);
		cd_search.setId("c_search");
		p_cd_searchbox.setTopAnchor(cd_search, 0.0);
		p_cd_searchbox.setRightAnchor(cd_search, 50.0);
		p_cd_searchbox.setBottomAnchor(cd_search, 0.0);
		p_cd_searchbox.setLeftAnchor(cd_search, 40.0);
		cd_searchlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

		@Override
		public void changed(ObservableValue<? extends String> observable,
				String oldValue, String newValue) {
			if(newValue!=null){
				switch(newValue){
				case "Mobile No.":{ cd_search.clear();
									cd_search.setPromptText("Enter Mobile No.");
								  }
								  break;
				case "Name":	  { cd_search.clear();
							  	    cd_search.setPromptText("Enter Name");
								  }
							      break;
				case "Account ID":{ cd_search.clear();
									cd_search.setPromptText("Enter Account ID");
								  }
								  break;
				default:		  break;
				}
			}	
		}
		});

		p_cd_searchbox.getChildren().addAll(search,cd_search,cd_searchlist);
		/*p_searchbox.setLeftAnchor(c_search, 90.0);
		p_searchbox.setLeftAnchor(search, 90.0);
		p_searchbox.setLeftAnchor(searchlist, 90.0);
		*/
		p_cd_search_btn=new AnchorPane();
		p_cd_search_btn.setPrefWidth(200);
		p_cd_search_btn.setPrefHeight(30);
		p_cd_search_btn.setId("p_search_btn");
		cdform.setVgrow(p_cd_search_btn, Priority.NEVER);
		
		cd_search_btn=new JFXButton();
		cd_search_btn.setText("Run Report");
		cd_search_btn.setPrefWidth(200);
		cd_search_btn.setPrefHeight(30);
		cd_search_btn.setId("search_btn");
		
		p_cd_search_btn.getChildren().add(cd_search_btn);
		p_cd_search_btn.setRightAnchor(cd_search_btn, 180.0);

		cdform.getChildren().addAll(search_note, p_cd_searchbox, p_cd_search_btn);

		cdpane.getChildren().addAll(cd_top, cdform);
		
		return cdpane;
	}
	
	public void customers_diary(){
		cust_diary=create_customers_diary();
		cust_diary.setId("apcenter");
		
		ap_left.getChildren().add(cust_diary);
		ap_left.setTopAnchor(cust_diary,0.0);
		ap_left.setBottomAnchor(cust_diary,0.0);
		ap_left.setLeftAnchor(cust_diary,0.0);
		ap_left.setRightAnchor(cust_diary,0.0);
	}

	public AnchorPane create_customers_list(){
		AnchorPane clpane=new AnchorPane();
		
		//top bar
		AnchorPane cl_top = new AnchorPane();
		cl_top.setPrefHeight(40);
		cl_top.setPrefWidth(95);
		clpane.setTopAnchor(cl_top,0.0);
		clpane.setLeftAnchor(cl_top,0.0);
		clpane.setRightAnchor(cl_top,0.0);
		cl_top.setId("top");
				
		Label search_custlist_menu = new Label("Customers List");
		search_custlist_menu.setPrefHeight(40);
		search_custlist_menu.setPrefWidth(495);
		search_custlist_menu.setAlignment(Pos.CENTER);
		cl_top.getChildren().add(search_custlist_menu);
		cl_top.setTopAnchor(search_custlist_menu,0.0);
		cl_top.setLeftAnchor(search_custlist_menu,0.0);
		cl_top.setRightAnchor(search_custlist_menu,0.0);
		cl_top.setBottomAnchor(search_custlist_menu,0.0);
		search_custlist_menu.setId("head");		

		//close button
		Image img = new Image(getClass().getResource("ic_close_white_36dp.png").toExternalForm(),25,25,false,false);
		ImageView iv_close = new ImageView(img);
		Button close_cust_list = new Button("",iv_close);
		close_cust_list.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
					ap_left.getChildren().removeAll(clpane);
					//ap_left.getChildren().remove(sp_result);
					//count=0;
				}
		});
		close_cust_list.setId("closebutton");
		cl_top.getChildren().add(close_cust_list);
		cl_top.setRightAnchor(close_cust_list,0.0);
		cl_top.setTopAnchor(close_cust_list,2.0);
				
		clform=new VBox();
		clform.setSpacing(10);
		
		clpane.setTopAnchor(clform, 70.0);
		clpane.setRightAnchor(clform, 50.0);
		clpane.setBottomAnchor(clform, 0.0);
		clpane.setLeftAnchor(clform, 50.0);
				
		search_note=show_form_field("Search by: (Select from the drop-down list)");
		clform.setVgrow(search_note, Priority.NEVER);
				
		p_cl_searchbox=new AnchorPane();
		p_cl_searchbox.setPrefWidth(300);
		p_cl_searchbox.setPrefHeight(40);
		p_cl_searchbox.setId("p_searchbox");
		AnchorPane.setLeftAnchor(p_cl_searchbox, 70.0);
		clform.setVgrow(p_cl_searchbox, Priority.NEVER);
			
		search=new ImageView(getClass().getResource("ic_search_black_24dp.png").toExternalForm());
		search.setId("search");
		search.setFitWidth(40.0);
		search.setFitHeight(40.0);
		p_cl_searchbox.setLeftAnchor(search, 0.0);
		p_cl_searchbox.setTopAnchor(search, 2.0);
		p_cl_searchbox.setRightAnchor(search, 260.0);
		p_cl_searchbox.setBottomAnchor(search, 0.0);
			
		cl_searchlist=new ComboBox<String>();
		cl_searchlist.setId("searchlist");
		cl_searchlist.setPrefWidth(20.0);
		cl_searchlist.setPrefHeight(40.0);
        cl_searchlist.getItems().setAll("Mobile No.","Name","Account ID");
        cl_searchlist.setVisibleRowCount(3);
        p_cl_searchbox.setTopAnchor(cl_searchlist, 0.0);
        p_cl_searchbox.setRightAnchor(cl_searchlist, 0.0);
        p_cl_searchbox.setBottomAnchor(cl_searchlist, 1.0);
        
        cl_search=new TextField();
        cl_search.setPromptText("Enter Mobile No.");
        cl_searchlist.getSelectionModel().selectFirst();
		cl_search.setAlignment(Pos.CENTER_LEFT);
		cl_search.setPadding(new Insets(0,0,0,10));
		cl_search.setFont(Font.font("Noto Sans", 18));
		cl_search.setPrefWidth(240.0);	
		cl_search.setPrefHeight(40.0);
		cl_search.setId("c_search");
		p_cl_searchbox.setTopAnchor(cl_search, 0.0);
		p_cl_searchbox.setRightAnchor(cl_search, 50.0);
		p_cl_searchbox.setBottomAnchor(cl_search, 0.0);
		p_cl_searchbox.setLeftAnchor(cl_search, 40.0);
		cl_searchlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
				String oldValue, String newValue) {
			if(newValue!=null){
				switch(newValue){
				case "Mobile No.":{ cl_search.clear();
									cl_search.setPromptText("Enter Mobile No.");
								  }
								  break;
				case "Name":	  { cl_search.clear();
							  	    cl_search.setPromptText("Enter Name");
								  }
							      break;
				case "Account ID":{ cl_search.clear();
									cl_search.setPromptText("Enter Account ID");
								  }
								  break;
				default:		  break;
				}
			}	
		}
		});

		p_cl_searchbox.getChildren().addAll(search,cl_search,cl_searchlist);
		/*p_searchbox.setLeftAnchor(c_search, 90.0);
		p_searchbox.setLeftAnchor(search, 90.0);
		p_searchbox.setLeftAnchor(searchlist, 90.0);
		*/
		p_cl_search_btn=new AnchorPane();
		p_cl_search_btn.setPrefWidth(200);
		p_cl_search_btn.setPrefHeight(30);
		p_cl_search_btn.setId("p_search_btn");
		clform.setVgrow(p_cl_search_btn, Priority.NEVER);
		
		cl_search_btn=new JFXButton();
		cl_search_btn.setText("Run Report");
		cl_search_btn.setPrefWidth(200);
		cl_search_btn.setPrefHeight(30);
		cl_search_btn.setId("search_btn");
		
		p_cl_search_btn.getChildren().add(cl_search_btn);
		p_cl_search_btn.setRightAnchor(cl_search_btn, 180.0);
		
		clform.getChildren().addAll(search_note, p_cl_searchbox, p_cl_search_btn);

		clpane.getChildren().addAll(cl_top, clform);
		
		return clpane;
	}
	
	public void customers_list(){
		cust_list=create_customers_list();
		cust_list.setId("apcenter");
		
		ap_left.getChildren().add(cust_list);
		ap_left.setTopAnchor(cust_list,0.0);
		ap_left.setBottomAnchor(cust_list,0.0);
		ap_left.setLeftAnchor(cust_list,0.0);
		ap_left.setRightAnchor(cust_list,0.0);
	}

	public AnchorPane create_sales_by_cust(){
		AnchorPane sbcpane=new AnchorPane();
		
		//top bar
		AnchorPane sbc_top = new AnchorPane();
		sbc_top.setPrefHeight(40);
		sbc_top.setPrefWidth(95);
		sbcpane.setTopAnchor(sbc_top,0.0);
		sbcpane.setLeftAnchor(sbc_top,0.0);
		sbcpane.setRightAnchor(sbc_top,0.0);
		sbc_top.setId("top");
				
		Label search_sbc_menu = new Label("Sales by Customer");
		search_sbc_menu.setPrefHeight(40);
		search_sbc_menu.setPrefWidth(495);
		search_sbc_menu.setAlignment(Pos.CENTER);
		sbc_top.getChildren().add(search_sbc_menu);
		sbc_top.setTopAnchor(search_sbc_menu,0.0);
		sbc_top.setLeftAnchor(search_sbc_menu,0.0);
		sbc_top.setRightAnchor(search_sbc_menu,0.0);
		sbc_top.setBottomAnchor(search_sbc_menu,0.0);
		search_sbc_menu.setId("head");		

		//close button
		Image img = new Image(getClass().getResource("ic_close_white_36dp.png").toExternalForm(),25,25,false,false);
		ImageView iv_close = new ImageView(img);
		Button close_sbc = new Button("",iv_close);
		close_sbc.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event){
					ap_left.getChildren().removeAll(sbcpane);
					//ap_left.getChildren().remove(sp_result);
					//count=0;
				}
		});
		close_sbc.setId("closebutton");
		sbc_top.getChildren().add(close_sbc);
		sbc_top.setRightAnchor(close_sbc,0.0);
		sbc_top.setTopAnchor(close_sbc,2.0);
				
		sbcform=new VBox();
		sbcform.setSpacing(10);
		
		sbcpane.setTopAnchor(sbcform, 70.0);
		sbcpane.setRightAnchor(sbcform, 50.0);
		sbcpane.setBottomAnchor(sbcform, 0.0);
		sbcpane.setLeftAnchor(sbcform, 50.0);
				
		search_note=show_form_field("Search by: (Select from the drop-down list)");
		sbcform.setVgrow(search_note, Priority.NEVER);
				
		p_sbc_searchbox=new AnchorPane();
		p_sbc_searchbox.setPrefWidth(300);
		p_sbc_searchbox.setPrefHeight(40);
		p_sbc_searchbox.setId("p_searchbox");
		AnchorPane.setLeftAnchor(p_sbc_searchbox, 70.0);
		sbcform.setVgrow(p_sbc_searchbox, Priority.NEVER);
			
		search=new ImageView(getClass().getResource("ic_search_black_24dp.png").toExternalForm());
		search.setId("search");
		search.setFitWidth(40.0);
		search.setFitHeight(40.0);
		p_sbc_searchbox.setLeftAnchor(search, 0.0);
		p_sbc_searchbox.setTopAnchor(search, 2.0);
		p_sbc_searchbox.setRightAnchor(search, 260.0);
		p_sbc_searchbox.setBottomAnchor(search, 0.0);
			
		sbc_searchlist=new ComboBox<String>();
		sbc_searchlist.setId("searchlist");
		sbc_searchlist.setPrefWidth(20.0);
		sbc_searchlist.setPrefHeight(40.0);
		sbc_searchlist.getItems().setAll("Mobile No.","Name","Account ID");
		sbc_searchlist.setVisibleRowCount(3);
        p_sbc_searchbox.setTopAnchor(sbc_searchlist, 0.0);
        p_sbc_searchbox.setRightAnchor(sbc_searchlist, 0.0);
        p_sbc_searchbox.setBottomAnchor(sbc_searchlist, 1.0);
        
        sbc_search=new TextField();
        sbc_search.setPromptText("Enter Mobile No.");
        sbc_searchlist.getSelectionModel().selectFirst();
        sbc_search.setAlignment(Pos.CENTER_LEFT);
        sbc_search.setPadding(new Insets(0,0,0,10));
        sbc_search.setFont(Font.font("Noto Sans", 18));
        sbc_search.setPrefWidth(240.0);	
        sbc_search.setPrefHeight(40.0);
        sbc_search.setId("c_search");
		p_sbc_searchbox.setTopAnchor(sbc_search, 0.0);
		p_sbc_searchbox.setRightAnchor(sbc_search, 50.0);
		p_sbc_searchbox.setBottomAnchor(sbc_search, 0.0);
		p_sbc_searchbox.setLeftAnchor(sbc_search, 40.0);
		sbc_searchlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
				String oldValue, String newValue) {
			if(newValue!=null){
				switch(newValue){
				case "Mobile No.":{ sbc_search.clear();
									sbc_search.setPromptText("Enter Mobile No.");
								  }
								  break;
				case "Name":	  { sbc_search.clear();
									sbc_search.setPromptText("Enter Name");
								  }
							      break;
				case "Account ID":{ sbc_search.clear();
									sbc_search.setPromptText("Enter Account ID");
								  }
								  break;
				default:		  break;
				}
			}	
		}
		});

		p_sbc_searchbox.getChildren().addAll(search,sbc_search,sbc_searchlist);
		/*p_searchbox.setLeftAnchor(c_search, 90.0);
		p_searchbox.setLeftAnchor(search, 90.0);
		p_searchbox.setLeftAnchor(searchlist, 90.0);
		*/
		p_sbc_search_btn=new AnchorPane();
		p_sbc_search_btn.setPrefWidth(200);
		p_sbc_search_btn.setPrefHeight(30);
		p_sbc_search_btn.setId("p_search_btn");
		sbcform.setVgrow(p_sbc_search_btn, Priority.NEVER);
		
		sbc_search_btn=new JFXButton();
		sbc_search_btn.setText("Run Report");
		sbc_search_btn.setPrefWidth(200);
		sbc_search_btn.setPrefHeight(30);
		sbc_search_btn.setId("search_btn");
		
		p_sbc_search_btn.getChildren().add(sbc_search_btn);
		p_sbc_search_btn.setRightAnchor(sbc_search_btn, 180.0);
		
		sbcform.getChildren().addAll(search_note, p_sbc_searchbox, p_sbc_search_btn);

		sbcpane.getChildren().addAll(sbc_top, sbcform);
		
		return sbcpane;
	}
	
	public void sales_by_cust(){
		sales_by_cust=create_sales_by_cust();
		sales_by_cust.setId("apcenter");
		
		ap_left.getChildren().add(sales_by_cust);
		ap_left.setTopAnchor(sales_by_cust,0.0);
		ap_left.setBottomAnchor(sales_by_cust,0.0);
		ap_left.setLeftAnchor(sales_by_cust,0.0);
		ap_left.setRightAnchor(sales_by_cust,0.0);
	}
	
	
	//submenu code for the main menu

	public void effect(ImageView s, Pane t){
		j=false;
		k=false;
		m=false;
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
						if(k==true&&m==false&&o==false)//and o is false
						{ k=false; j=true; o=false;}
						if(m==true&&k==false&&o==false)//and o is false
						{ m=false; j=true; o=false;}
						if(m==false&&k==false&&o==true)
						{ m=false; j=true; o=false;}
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
						if(j==true&&m==false&&o==false)//and o is false
						{ j=false; k=true; o=false;}
						if(m==true&&j==false&&o==false)//and o is false
						{ m=false; k=true; o=false;}
						if(m==false&&j==false&&o==true)
						{ m=false; k=true; o=false;}
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
												Alert alert1 = new Alert(AlertType.ERROR);
								    			alert1.setTitle("Error Dialog");
								    			alert1.setHeaderText("Customers Error!!");
								    			alert1.setContentText("Already in Customers");
								    			alert1.showAndWait();
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
					if(m==false)
						m=true;
					else
						m=false;
					//System.out.println("Inside l: "+j+k+l);
					if(j==true&&k==false&&o==false)//and o is false
					{ j=false; m=true; o=false;}
					if(k==true&&j==false&&o==false)//and o is false
					{ k=false; m=true; o=false;}
					if(k==false&&j==false&&o==true)
					{ k=false; m=true; o=false;}
					if(m==true){
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
					if(m==false){
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
					if(j==true&&k==false&&m==false)//and o is false
					{ j=false; o=true; m=false;}
					if(k==true&&j==false&&m==false)//and o is false
					{ k=false; o=true; j=false;}
					if(k==false&&j==false&&m==true)
					{ m=false; o=true; m=false;}
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


package register.Controller;

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
import java.util.TimerTask;

import sales.*;
import sales.main_Controller.SalesTable;

import com.jfoenix.controls.JFXButton;

import database.dbfunc;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class closeCashController {

	static ArrayList<String> aln=new ArrayList<String>();
	static ArrayList<cashFlowTable> al=new ArrayList<cashFlowTable>();
	static ArrayList<TaxTable> al1=new ArrayList<TaxTable>();
	static public TableView<cashFlowTable> table_p ;
	static public TableView<TaxTable> table_p1 ;
	static ObservableList<cashFlowTable> data_tbl;
	static ObservableList<TaxTable> data_tbl1;
	static TaxTable s_ob1,s_ob2;
	static int i_tbl=-1;
	static TaxTable s_ob1t,s_ob2t;
	static int i_tblt=-1;
	static String time;
	static Timestamp t = null;
	static double amt_in=0;
	static double amt_out=0;
	static double amt_sale = 0;
	
	static String name_p =null;
	static double price_p = 0;
	
	static TableView<TaxTable> t2 = null ;
	TableView<cashFlowTable> t1;
	
	static String gen_id=null;
	
	static int transac = 0;
	static String taxcat_p = null;
	static double taxes_p = 0;
	static double net_p = 0;
	static double taxes_p1 = 0;
	static double net_p1 = 0;
	static double rate_p = 0;
	
	static dbfunc db = new dbfunc();
	static Connection co = db.connect();
	
	@FXML
	private Label seq;
	
	@FXML
	private Label date_top;
	
	@FXML
	private Label startD;
	
	@FXML
	private Label eDate,net,taxes;
	
	@FXML
	private Label total,money,trans,sales,drawer;
	
	@FXML
	private ScrollPane cashflow;
	
	@FXML
	private JFXButton print;
	
	@FXML
	private JFXButton closecash;
	
	@FXML
	private ScrollPane taxcategory;
	
	@FXML
	AnchorPane mbar_rest;
	
	@FXML
	AnchorPane mbar_total;
	
	
	@FXML
	private ImageView hbg;
	
	@FXML
	private Pane mbar;
	
	@FXML
	private VBox menu;
	
	public static Map<String, String> mdata = new HashMap<String, String>();
	static boolean j=false;
	static boolean k=false;
	static boolean l=false;
	static boolean o=false;
	
	
	@FXML
	private void initialize() throws SQLException{
		
		//hamburger code starts
		 //code for hamburger
		 Translate tr = new Translate();
			mbar.getTransforms().add(tr);
			tr.setX(-310);
		
		//System.out.println(hbg);
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
		
		
		
		
		
	/************SETTING TIMER CODE BEGINS HERE**************/
		java.util.Timer timer = new java.util.Timer();
		timer.schedule(new TimerTask() {
		    public void run() {
		         Platform.runLater(new Runnable() {
		            public void run() {
		                Date date = new Date();
		                final DateFormat timeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		                time = timeFormat.format(date);
		                date_top.setText(time);
		            }
		        });
		    }
		}, 100,100);
	
	/************SETTING TIMER CODE ENDS HERE**************/
		
		print.setOnMouseClicked(event->{
			System.out.println("Send to Printer");
		});
		
		closecash.setOnMouseClicked(event->{
			System.out.println("Do close cash");
			
			ResultSet rs6; Statement stmt6;
			try {
				
				stmt6 = co.createStatement();
				String query6 = "select id from closedcash";
				rs6 = stmt6.executeQuery(query6); String id = null;
				 
				 
				 while(rs6.next())
				 {
					 if(rs6.isLast())
						 id = rs6.getString(1);
				 }
				 
				 if(id==null)
				 {
					 System.out.println("Fetched ID is null");
					 gen_id = "CC123";
					 System.out.println("Generated is: "+gen_id);
				 }
				 else{
					 System.out.println(id);
					 String s1 = id.substring(2);
					 int n = Integer.parseInt(s1);
					 System.out.println(n);
					 n=n+1;
					 gen_id = "CC"+n;
					 System.out.println("Generated is: "+gen_id);
				 }
				 
				 if(t!=null && gen_id!=null){
					 co.setAutoCommit(false);

					 Statement stmt7 = co.createStatement();
					 String query7 = "update cashinout set cid ='"+gen_id+"' where start>='"+t+"';";
					 int rs7 = stmt7.executeUpdate(query7); 
					 
					java.util.Date date= new java.util.Date();
					Timestamp s1 = (new Timestamp(date.getTime()));
					 
					 Statement stmt8 = co.createStatement();
					 String query8 = "insert into closedcash values ('"+gen_id+"',"+Double.parseDouble(total.getText())+","+Double.parseDouble(money.getText())+","+Integer.parseInt(trans.getText())+","+Integer.parseInt(sales.getText())+","+Double.parseDouble(net.getText())+","+Double.parseDouble(taxes.getText())+","+"to_timestamp('"+s1+"', 'yyyy-mm-dd hh24:mi:ss')"+");";
					 int rs8 = stmt8.executeUpdate(query8); 
					 
					 if(data_tbl!=null)
						 data_tbl.clear();
					 if(data_tbl1!=null)
						 data_tbl1.clear();
					 
					 co.commit();
					 co.setAutoCommit(true);
					 
						Alert alert=new Alert(AlertType.WARNING);
						alert.setTitle("Warning");
						String s3="Close Cash done";
						alert.setContentText(s3);
						alert.showAndWait();
				 }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			
		});
		
		//data_tbl1 = addData1(taxcat_p,taxes_p,net_p);
		//data_tbl = addData(name_p,price_p);
		Statement stmt60 = co.createStatement(); 
		String query60 = "select transaction from closedcash;";
		ResultSet rs60= stmt60.executeQuery(query60);
		if(rs60.isBeforeFirst()){
			while(rs60.next()){
				if(rs60.isLast()){
					trans.setText(Integer.toString(rs60.getInt(1)+1));
				}
			}
		}else{
			trans.setText("1");
		}
		
		Statement stmt61 = co.createStatement(); 
		String query61 = "select start from cashinout where cid='"+0+"';";
		ResultSet rs61= stmt61.executeQuery(query61);
		if(rs61.isBeforeFirst())
		{
			while(rs61.next()){
				if(rs61.isFirst()){
					System.out.println("Got Value : "+rs61.getTimestamp(1));
					t = rs61.getTimestamp(1);
					startD.setText(rs61.getTimestamp(1).toString().substring(0, 11));
					java.util.Date date= new java.util.Date();
					Timestamp s1 = (new Timestamp(date.getTime()));
					eDate.setText(s1.toString().substring(0, 11));					
				}
			}
		}else{
			java.util.Date date= new java.util.Date();
			Timestamp s1 = (new Timestamp(date.getTime()));
			startD.setText(s1.toString().substring(0, 11));
			eDate.setText(s1.toString().substring(0, 11));
		}
		
		if(t!=null){
			Statement stmt64 = co.createStatement(); String type1 = "cash in";
			String query64 = "select amount from cashinout where start>='"+t+"' and type='"+type1+"';";
			ResultSet rs64= stmt64.executeQuery(query64);
			if(rs64.isBeforeFirst()){
				while(rs64.next()){
					amt_in = amt_in + rs64.getDouble(1);
				}
				data_tbl = addData("cash in",amt_in);
			}
			
			Statement stmt65 = co.createStatement(); String type2 = "cash out";
			String query65 = "select amount from cashinout where start>='"+t+"' and type='"+type2+"';";
			ResultSet rs65= stmt65.executeQuery(query65);
			if(rs65.isBeforeFirst()){
				while(rs65.next()){
					amt_out = amt_out + rs65.getDouble(1);
				}
				data_tbl = addData("cash out",amt_out);
			}
			
			Statement stmt66 = co.createStatement(); 
			String query66 = "select money,ticketid from receipts where datetime>='"+t+"';";
			ResultSet rs66= stmt66.executeQuery(query66);
			if(rs66.isBeforeFirst())
			{
				while(rs66.next()){
					transac++;
					amt_sale = amt_sale + rs66.getDouble(1);
					aln.add(rs66.getString(2));
				}
				System.out.println("Total Amount: "+amt_sale);
				sales.setText(Integer.toString(transac));
				System.out.println("Total Transaction: "+transac);
				data_tbl = addData("sales",amt_sale);
			}
			
			for(int i=0;i<aln.size();i++)
			{
				//data_tbl1 = addData1(taxcat_p,taxes_p,net_p);
				String id = aln.get(i);
				
				Statement stmt67 = co.createStatement(); 
				String query67 = "select taxid,amount from taxline where receipt='"+id+"';";
				ResultSet rs67= stmt67.executeQuery(query67);
				if(rs67.isBeforeFirst()){
					while(rs67.next()){

						Statement stmt68 = co.createStatement(); 
						String query68 = "select category,rate from taxes where id='"+rs67.getString(1)+"';";
						ResultSet rs68= stmt68.executeQuery(query68);
						while(rs68.next()){
							taxcat_p = rs68.getString(1);
							rate_p = rs68.getDouble(2);
						}
						
						taxes_p = (rs67.getDouble(2)*rate_p)/100;
						net_p = rs67.getDouble(2) - taxes_p;
						data_tbl1 = addData1(taxcat_p,taxes_p,net_p);
					}
				}				
			}
			
			total.setText(Double.toString(amt_in + amt_out + amt_sale));
			money.setText(Double.toString(amt_in + amt_sale));
			
		}
		
		t2= item1(data_tbl1);
		taxcategory.setContent(t2);
		taxcategory.setFitToHeight(true);
		taxcategory.setFitToWidth(true);
	
		t1 = item(data_tbl);
		cashflow.setContent(t1);
		cashflow.setFitToHeight(true);
		cashflow.setFitToWidth(true);
		
		if(t!=null && data_tbl1!=null){
			for(int j=0 ; j<t2.getItems().size() ; j++)
			{
				s_ob2=t2.getItems().get(j);
				net_p1 = net_p1 + s_ob2.getNET();
				taxes_p1 = taxes_p1 + s_ob2.getTAXES();
			}
			
		}
		if(net_p1!=0 || taxes_p1!=0){
			net.setText(Double.toString(net_p1));
			taxes.setText(Double.toString(taxes_p1));
		}
		
	}	
	
	public TableView<cashFlowTable> item(ObservableList<cashFlowTable> data)
	   {
	   	 TableView<cashFlowTable> table  = new TableView<cashFlowTable>();
	   	table_p = table;
	table.setItems(data);

    TableColumn<cashFlowTable, String> paymentCol = new TableColumn<cashFlowTable, String>("Payment");
    paymentCol.setMinWidth(150);
    paymentCol.setCellValueFactory(
            new PropertyValueFactory<>("Payment"));
    
    TableColumn<cashFlowTable, Double> moneyCol = new TableColumn<cashFlowTable, Double>("Money");
    moneyCol.setMinWidth(200);
    moneyCol.setCellValueFactory(
            new PropertyValueFactory<>("Money"));    
   
    table.getColumns().addAll(paymentCol, moneyCol);	     
    

    table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
        //Check whether item is selected and set value of selected item to Label
        if (table.getSelectionModel().getSelectedItem() != null) {
        	
           //System.out.println("Sonething can be done");
           //s_ob1 = table.getSelectionModel().getSelectedItem();
          // s_ob2 = s_ob1;
           //i_tbl =data_tbl.indexOf(s_ob1);

           //System.out.println("Index is :" + i_tbl);
           //System.out.println(s_ob1.getPayment());
           //System.out.println(s_ob1.getMoney());
           
           //System.out.println(s_ob1.getItemName());
           
        }
    });
    
    return table;
	    }
	
	public ObservableList<cashFlowTable> addData(String n,double p)
	{
		 
		   
		   ObservableList<cashFlowTable> data;
		   
	    	al.add(new cashFlowTable(n, p));
		   	
	        data=FXCollections.observableList(al);
			return data;
	}
	
    public static class cashFlowTable {
   	 
        private final SimpleStringProperty Payment;
        private final SimpleDoubleProperty Money;

 
        cashFlowTable(String Payment, Double Money) {
            this.Payment = new SimpleStringProperty(Payment);
            this.Money = new SimpleDoubleProperty(Money);
        }
 
        //GETTER AND SETTER METHODS FOR ITEM
        public String getPayment() {
            return Payment.get();
        }
 
        public void setPayment(String iName) {
        	Payment.set(iName);
        }
 
      //GETTER AND SETTER METHODS FOR PRICE
        public Double getMoney() {
            return Money.get();
        }
 
        public void setMoney(Double iPrice) {
        	Money.set(iPrice);
        }
    }
    
    public TableView<TaxTable> item1(ObservableList<TaxTable> data1)
	   {
    	System.out.println("item1");
	   	 TableView<TaxTable> table  = new TableView<TaxTable>();
	   	table_p1 = table;
	   	table.setItems(data1);

	    TableColumn<TaxTable, String> paymentCol = new TableColumn<TaxTable, String>("Tax Category");
	    paymentCol.setMinWidth(150);
	    paymentCol.setCellValueFactory(
	            new PropertyValueFactory<>("TAXCATEGORY"));
	    
	    TableColumn<TaxTable, Double> moneyCol = new TableColumn<TaxTable, Double>("Taxes");
	    moneyCol.setMinWidth(100);
	    moneyCol.setCellValueFactory(
	            new PropertyValueFactory<>("TAXES"));    
	    
	    TableColumn<TaxTable, Double> NET = new TableColumn<TaxTable, Double>("Net Amt");
	    NET.setMinWidth(100);
	    NET.setCellValueFactory(
	            new PropertyValueFactory<>("NET"));    
	   
	    table.getColumns().addAll(paymentCol, moneyCol,NET); 

 table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
     //Check whether item is selected and set value of selected item to Label
     if (table.getSelectionModel().getSelectedItem() != null) {
     	
        System.out.println("Sonething can be done");
        s_ob1t = table.getSelectionModel().getSelectedItem();
        s_ob2t = s_ob1t;
        i_tblt =data_tbl1.indexOf(s_ob1t);

        System.out.println("Index is :" + i_tblt);
        System.out.println(s_ob1t.getTAXCATEGORY());
        System.out.println(s_ob1t.getTAXES());
        
        System.out.println(s_ob1t.getNET());
        
     }
 });
 
 return table;
	    }
    
    public ObservableList<TaxTable> addData1(String n,Double p,Double net)
	{
		 
		   System.out.println("here");
		   ObservableList<TaxTable> data1;
		   System.out.println(n + p + net);
	    	al1.add(new TaxTable(n, p,net));
		   	
	        data1=FXCollections.observableList(al1);
			return data1;
	}

    public static class TaxTable {
      	 
        private final SimpleStringProperty TAXCATEGORY;
        private final SimpleDoubleProperty TAXES;
        private final SimpleDoubleProperty NET;

 
        TaxTable(String ategory, Double tes,Double nt) {
            this.TAXCATEGORY = new SimpleStringProperty(ategory);
            this.TAXES = new SimpleDoubleProperty(tes);
            this.NET = new SimpleDoubleProperty(nt);
        }
 
        //GETTER AND SETTER METHODS FOR TAX CATEGORY
        public String getTAXCATEGORY() {
            return TAXCATEGORY.get();
        }
 
        public void setTAXCATEGORY(String iName) {
        	TAXCATEGORY.set(iName);
        }
 
      //GETTER AND SETTER METHODS FOR TAXES
        public Double getTAXES() {
            return TAXES.get();
        }
 
        public void setTAXES(Double iPrice) {
        	TAXES.set(iPrice);
        }
        
        //GETTER AND SETTER METHODS FOR NET
        public Double getNET() {
            return NET.get();
        }
 
        public void setNET(Double iPrice) {
        	NET.set(iPrice);
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
				System.out.println(main_Window.i+" in effect main open");
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
				System.out.println(main_Window.i+" in effect main close");
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
  															main_Window.scene=null;
  															main_Window.scene = new Scene(main_Window.page,main_Window.primaryStage1.getWidth(),main_Window.primaryStage1.getHeight());
  															main_Window.primaryStage1.setScene(main_Window.scene);
  															main_Window.scene.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
  															main_Window.scene.getStylesheets().add(getClass().getResource("/sales/mainsales.css").toExternalForm());
  															main_Window.scene.getStylesheets().add(getClass().getResource("/sales/calculator.css").toExternalForm());
  															effect(hbg,mbar);
  															System.out.println(main_Window.i+" pay g!=0");
  															main_Controller.data_tbl.clear();
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
														main_Window.scene2.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
														main_Window.primaryStage1.show();	
														effect(hbg,mbar);
														System.out.println(main_Window.i+" g!=0");
													}
													}
														catch(Exception e){
															main_Window.scene2 = new Scene(main_Window.page2,main_Window.primaryStage1.getWidth(),main_Window.primaryStage1.getHeight());
															main_Window.primaryStage1.setScene(main_Window.scene2);
															main_Window.scene2.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
															main_Window.scene2.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
															main_Window.primaryStage1.show();
															effect(hbg,mbar);
															System.out.println(main_Window.i+" g==0");
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
  														System.out.println(main_Window.i+" g!=0");
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
  														System.out.println(main_Window.i+" g!=0");
  													}
  													}
  													catch(Exception e){
  														main_Window.scene4 = new Scene(main_Window.page4,main_Window.primaryStage1.getWidth(),main_Window.primaryStage1.getHeight());
  														main_Window.primaryStage1.setScene(main_Window.scene4);
  														main_Window.scene4.getStylesheets().add(getClass().getResource("/register/edit_sales.css").toExternalForm());
  														main_Window.scene4.getStylesheets().add(getClass().getResource("/sales/ham.css").toExternalForm());
  														main_Window.primaryStage1.show();
  														effect(hbg,mbar);
  														System.out.println(main_Window.i+" g==0");
  													}
  												}
  												});
  										}
  										else if(entry.getKey().equals("close_cash")){
  											reg1.setText("Close Cash");
  											reg1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
  												public void handle(MouseEvent event){
  													Alert alert1 = new Alert(AlertType.ERROR);
  									    			alert1.setTitle("Error Dialog");
  									    			alert1.setHeaderText("Close Cash Error!!");
  									    			alert1.setContentText("Already in Close Cash");
  									    			alert1.showAndWait();
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

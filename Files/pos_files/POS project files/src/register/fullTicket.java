package register;

import java.beans.EventHandler;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class fullTicket extends Application{
	
	static AnchorPane a;
	static ScrollPane b;
	static ArrayList<SalesTable> al=new ArrayList<SalesTable>();
	static ObservableList<SalesTable> data_tbl;
	static String name_p;
	static double price;
	static double tax_val;
	static String taxcat;
	static double unit=1;
	static double value;
	static public TableView<SalesTable> table_p ;
	
	static editcontroller1 cont = new editcontroller1();
	

	public static void main(String args[])
	{
		launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		AnchorPane ap = FullTicket("T123","Administrator","Rs. 10000","Rs. 1000","Rs. 11000","17 May, 2016","1-A","Ayush Vardhan","Credit","Raj");
		
		Scene scene = new Scene(ap);
		
		
		scene.getStylesheets().add(getClass().getResource("edit_sales.css").toExternalForm());
		 
		
		primaryStage.setScene(scene);
        primaryStage.setTitle("Ticket");
        primaryStage.show();
		
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
           

            //System.out.println("OK");
            //System.out.println("INSIDE TABLE VIEW");
        }
    });
    
    table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
        //Check whether item is selected and set value of selected item to Label
        if (table.getSelectionModel().getSelectedItem() != null) {
        	
           

           //System.out.println("Index is :" + i_tbl);
           
           //System.out.println(s_ob1.getItemName());
           
        }
    });
    
    return table;
	    }
	
	static AnchorPane FullTicket(String no,String rol, String totalNoTax, String tx,String ttt,String datev,String termv,String namev,String accv,String servev)
	{
		AnchorPane t = new AnchorPane();
		t.setId("fullticekt");
		t.setPrefSize(670, 635);
		
		ImageView img = new ImageView();
		img.setId("cross");
		img.fitHeightProperty().set(29.0);
		img.fitWidthProperty().set(31.0);
		img.setLayoutX(456.0);
		img.setLayoutY(14.0);
		img.pickOnBoundsProperty().set(true);
		img.preserveRatioProperty().set(true);
		t.setTopAnchor(img, 2.0);
		t.setRightAnchor(img, 2.0);
		
		final Image image = new Image(fullTicket.class.getResourceAsStream("ic_clear_black_24dp.png"));	
	    img.setImage(image);
	    
	    Pane p_1 = new Pane();
	    p_1.setId("line_b");
	    p_1.setLayoutX(66.0);	 
	    p_1.setLayoutY(246.0);	
	    p_1.setPrefHeight(2.0);
	    p_1.setPrefWidth(200.0);
	    t.setBottomAnchor(p_1, 200.0);
	    t.setLeftAnchor(p_1, 35.0);
	    t.setRightAnchor(p_1, 35.0);
	    
	    Pane p_2 = new Pane();
	    p_2.setId("line_t");
	    p_2.setLayoutX(76.0);	 
	    p_2.setLayoutY(256.0);
	    p_2.setPrefHeight(2.0);
	    p_2.setPrefWidth(200.0);
	    t.setTopAnchor(p_2, 200.0);
	    t.setLeftAnchor(p_2, 35.0);
	    t.setRightAnchor(p_2, 35.0);
	    
	    AnchorPane in = new AnchorPane();
	    in.setId("fullTd");
	    in.setLayoutX(21.0);
	    in.setLayoutY(421.0);
	    in.setPrefSize(200.0, 130.0);
	    t.setBottomAnchor(in, 3.0);
	    t.setLeftAnchor(in, 0.0);
	    t.setRightAnchor(in, 0.0);
	    
	    Label rno = new Label();
	    rno.setLayoutX(31.0);
	    rno.setLayoutY(84.0);
	    rno.setText("Receipt No:");
	    rno.setTextFill(Color.WHITE);
	    rno.setFont(new Font("Roboto Regular", 25));
	    
	    in.setBottomAnchor(rno, 65.0);
	    in.setLeftAnchor(rno, 30.0);
	    
	    Label rNo = new Label();
	    rNo.setLayoutX(169.0);
	    rNo.setLayoutY(35.0);
	    rNo.setPrefHeight(30.0);
	    rNo.setPrefWidth(134.0);
	    rNo.setText(no);
	    rNo.setTextFill(Color.WHITE);
	    rNo.setFont(new Font("Roboto Regular", 20));
	    
	    in.setBottomAnchor(rNo, 65.0);
	    in.setLeftAnchor(rNo, 170.0);
	    
		JFXButton salesreturn = new JFXButton("SALES RETURN");
		salesreturn.setId("salesRF");
		salesreturn.setLayoutX(409.0);
		salesreturn.setLayoutY(110.0);
		salesreturn.setFont(new Font("Roboto Bold", 15));
		in.setRightAnchor(salesreturn, 230.0);
		in.setBottomAnchor(salesreturn, 25.0);
		
		salesreturn.setOnAction(event->{
			System.out.println("Hello");
			try {
				cont.salesReturn();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		JFXButton refund = new JFXButton("REFUND ALL");
		refund.setId("refundF");
		refund.setLayoutX(399.0);
		refund.setLayoutY(100.0);
		refund.setFont(new Font("Roboto Bold", 15));
		in.setRightAnchor(refund, 125.0);
		in.setBottomAnchor(refund, 25.0);
		refund.setOnAction(event->{
			System.out.println("Move to refunding part");
			try {
				cont.completerefund();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		JFXButton print = new JFXButton("PRINT");
		print.setId("printF");
		print.setLayoutX(389.0);
		print.setLayoutY(90.0);
		print.setFont(new Font("Roboto Bold", 15));
		in.setBottomAnchor(print, 25.0);
		in.setRightAnchor(print, 40.0);
		
		Label role = new Label();
		role.setId("roleF");
	    role.setLayoutX(30.0);
	    role.setLayoutY(68.0);
	    role.setPrefHeight(30.0);
	    role.setPrefWidth(134.0);
	    role.setText(rol);
	    role.setStyle("-fx-text-fill: #eeeeee;");
	    role.setFont(new Font("Roboto Regular", 15));
	    in.setBottomAnchor(role, 35.0);
	    
	    in.getChildren().addAll(rno,rNo,print,refund,salesreturn,role);
	    
		Label not = new Label();
		not.setId("nettax");
	    not.setLayoutX(35.0);
	    not.setLayoutY(468.0);
	    not.setText("Net off Tax");
	    not.setStyle("-fx-text-fill: #757575;");
	    not.setFont(new Font("Roboto Regular", 14));
	    t.setBottomAnchor(not, 140.0);
	    t.setLeftAnchor(not, 40.0);
	    
		Label total = new Label();
		total.setId("nettax11");
		total.setLayoutX(175.0);
		total.setLayoutY(488.0);
		total.setText("Total");
		total.setStyle("-fx-text-fill: #757575;");
		total.setFont(new Font("Roboto Regular", 14));
	    t.setBottomAnchor(total, 140.0);
	    t.setLeftAnchor(total, 550.0);
	    
		Label tax = new Label();
		tax.setId("nettax1");
		tax.setLayoutX(165.0);
		tax.setLayoutY(478.0);
		tax.setText("Tax");
		tax.setStyle("-fx-text-fill: #757575;");
		tax.setFont(new Font("Roboto Regular", 14));
	    t.setBottomAnchor(tax, 140.0);
	    t.setLeftAnchor(tax, 280.0);
	    
		Label totF = new Label();
		totF.setId("nettax2");
		totF.setLayoutX(45.0);
		totF.setLayoutY(478.0);
		totF.setText("Rs. "+totalNoTax);
		totF.setFont(new Font("Roboto Bold", 14));
	    t.setBottomAnchor(totF, 165.0);
	    t.setLeftAnchor(totF, 40.0);
	    
	    Label taxF = new Label();
	    taxF.setId("nettax21");
	    taxF.setLayoutX(55.0);
	    taxF.setLayoutY(488.0);
	    taxF.setText("Rs. "+tx);
	    taxF.setFont(new Font("Roboto Bold", 14));
	    t.setBottomAnchor(taxF, 165.0);
	    t.setLeftAnchor(taxF, 280.0);
	    
	    Label totalF = new Label();
	    totalF.setId("nettax22");
	    totalF.setLayoutX(55.0);
	    totalF.setLayoutY(488.0);
	    totalF.setText(ttt);
	    totalF.setFont(new Font("Roboto Bold", 14));
	    t.setBottomAnchor(totalF, 165.0);
	    t.setLeftAnchor(totalF, 550.0);
	    
		ImageView img1 = new ImageView();
		img1.fitHeightProperty().set(30.0);
		img1.fitWidthProperty().set(30.0);
		img1.setLayoutX(35.0);
		img1.setLayoutY(17.0);
		img1.pickOnBoundsProperty().set(true);
		img1.preserveRatioProperty().set(true);
		t.setTopAnchor(img1, 30.0);
		t.setLeftAnchor(img1, 35.0);
		
		final Image image1 = new Image(fullTicket.class.getResourceAsStream("XMLID_56_.png"));	
		img1.setImage(image1);
		
		ImageView img2 = new ImageView();
		img2.fitHeightProperty().set(30.0);
		img2.fitWidthProperty().set(30.0);
		img2.setLayoutX(45.0);
		img2.setLayoutY(27.0);
		img2.pickOnBoundsProperty().set(true);
		img2.preserveRatioProperty().set(true);
		t.setTopAnchor(img2, 90.0);
		t.setLeftAnchor(img2, 35.0);
		
		final Image image2 = new Image(fullTicket.class.getResourceAsStream("XMLID_56_.png"));	
		img2.setImage(image2);
		
		ImageView img3 = new ImageView();
		img3.fitHeightProperty().set(30.0);
		img3.fitWidthProperty().set(30.0);
		img3.setLayoutX(55.0);
		img3.setLayoutY(37.0);
		img3.pickOnBoundsProperty().set(true);
		img3.preserveRatioProperty().set(true);
		t.setTopAnchor(img3, 150.0);
		t.setLeftAnchor(img3, 35.0);
		
		final Image image3 = new Image(fullTicket.class.getResourceAsStream("XMLID_56_.png"));	
		img3.setImage(image3);
		
		ImageView img4 = new ImageView();
		img4.fitHeightProperty().set(30.0);
		img4.fitWidthProperty().set(30.0);
		img4.setLayoutX(45.0);
		img4.setLayoutY(27.0);
		img4.pickOnBoundsProperty().set(true);
		img4.preserveRatioProperty().set(true);
		t.setTopAnchor(img4, 30.0);
		t.setRightAnchor(img4, 300.0);
		
		final Image image4 = new Image(fullTicket.class.getResourceAsStream("XMLID_56_.png"));	
		img4.setImage(image4);
		
		ImageView img5 = new ImageView();
		img5.fitHeightProperty().set(30.0);
		img5.fitWidthProperty().set(30.0);
		img5.setLayoutX(55.0);
		img5.setLayoutY(37.0);
		img5.pickOnBoundsProperty().set(true);
		img5.preserveRatioProperty().set(true);
		t.setTopAnchor(img5, 90.0);
		t.setRightAnchor(img5, 300.0);
		
		final Image image5 = new Image(fullTicket.class.getResourceAsStream("XMLID_56_.png"));	
		img5.setImage(image5);
	    
		Label dateF = new Label();
		dateF.setId("nettax3");
		dateF.setLayoutX(70.0);
		dateF.setLayoutY(49.0);
		dateF.setText("Date");
		dateF.setStyle("-fx-text-fill: #757575;");
		dateF.setFont(new Font("Roboto Regular", 14));
	    t.setTopAnchor(dateF, 50.0);
	    t.setLeftAnchor(dateF, 80.0);
	    
	    Label termF = new Label();
	    termF.setId("nettax31");
	    termF.setLayoutX(80.0);
		termF.setLayoutY(59.0);
		termF.setText("Terminal");
		termF.setStyle("-fx-text-fill: #757575;");
		termF.setFont(new Font("Roboto Regular", 14));
	    t.setTopAnchor(termF, 110.0);
	    t.setLeftAnchor(termF, 80.0);
	    
	    Label custF = new Label();
	    custF.setId("nettax311");
	    custF.setLayoutX(90.0);
	    custF.setLayoutY(69.0);
	    custF.setText("Customer");
	    custF.setStyle("-fx-text-fill: #757575;");
	    custF.setFont(new Font("Roboto Regular", 14));
	    t.setTopAnchor(custF, 170.0);
	    t.setLeftAnchor(custF, 80.0);
	    
	    Label Accf = new Label();
	    Accf.setId("nettax32");
	    Accf.setLayoutX(390.0);
	    Accf.setLayoutY(49.0);
	    Accf.setText("Account");
	    Accf.setStyle("-fx-text-fill: #757575;");
	    Accf.setFont(new Font("Roboto Regular", 14));
	    t.setTopAnchor(Accf, 50.0);
	    t.setRightAnchor(Accf, 230.0);
	    
	    Label serve = new Label();
	    serve.setId("nettax312");
	    serve.setLayoutX(390.0);
	    serve.setLayoutY(129.0);
	    serve.setText("Served by");
	    serve.setStyle("-fx-text-fill: #757575;");
	    serve.setFont(new Font("Roboto Regular", 14));
	    t.setTopAnchor(serve, 110.0);
	    t.setRightAnchor(serve, 220.0);
	    
	    Label dateV = new Label();
	    dateV.setId("nettax23");
	    dateV.setLayoutX(75.0);
	    dateV.setLayoutY(28.0);
	    dateV.setText(datev);
	    dateV.setFont(new Font("Roboto Bold", 14));
	    t.setTopAnchor(dateV, 30.0);
	    t.setLeftAnchor(dateV, 80.0);
	    
	    Label termV = new Label();
	    termV.setId("nettax231");
	    termV.setLayoutX(85.0);
	    termV.setLayoutY(88.0);
	    termV.setText(termv);
	    termV.setFont(new Font("Roboto Bold", 14));
	    t.setTopAnchor(termV, 90.0);
	    t.setLeftAnchor(termV, 80.0);
	    
	    Label nameV = new Label();
	    nameV.setId("nettax2311");
	    nameV.setLayoutX(95.0);
	    nameV.setLayoutY(98.0);
	    nameV.setText(namev);
	    nameV.setFont(new Font("Roboto Bold", 14));
	    t.setTopAnchor(nameV, 150.0);
	    t.setLeftAnchor(nameV, 80.0);
	    
	    Label accV = new Label();
	    accV.setId("nettax23111");
	    accV.setLayoutX(385.0);
	    accV.setLayoutY(29.0);
	    accV.setText(accv);
	    accV.setFont(new Font("Roboto Bold", 14));
	    t.setTopAnchor(accV, 30.0);
	    t.setRightAnchor(accV, 245.0);
	    
	    Label serveV = new Label();
	    serveV.setId("nettax23112");
	    serveV.setLayoutX(395.0);
	    serveV.setLayoutY(108.0);
	    serveV.setText(servev);
	    serveV.setFont(new Font("Roboto Bold", 14));
	    t.setTopAnchor(serveV, 90.0);
	    t.setRightAnchor(serveV, 262.0);
	    
	    ScrollPane center = new ScrollPane();
	    center.setId("tbl_center_edit");
	    center.setLayoutX(66.0);
	    center.setLayoutY(218.0);
	    center.setPrefSize(200.0, 200.0);
	    t.setLeftAnchor(center, 70.0);
	    t.setRightAnchor(center, 70.0);
		
	    
	    t.getChildren().addAll(img,p_1,p_2,in,not,tax,total,totF,taxF,totalF,img1,img2,img3,img4,img5,dateF,termF,custF,Accf,serve,dateV);
		t.getChildren().addAll(termV,nameV,accV,serveV,center);
		
	    return t;
	}
	
	public ObservableList<SalesTable> addData(String n,double p,double u,double t,double v)
	{
		 
		   
		   ObservableList<SalesTable> data;
		   
	    	al.add(new SalesTable(n, p, u, t, v));
		   	
	        data=FXCollections.observableList(al);
			return data;
	}

}

class SalesTable {
	 
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

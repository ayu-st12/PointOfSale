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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class salesreturn extends Application{

	

	
	static AnchorPane a_SR;
	static ScrollPane b_SR;
	//static ArrayList<SalesTable1> al_SR=new ArrayList<SalesTable1>();
	//static ObservableList<SalesTable1> data_tbl_SR;
	static String name_p_SR;
	static double price_SR;
	static double tax_val_SR;
	static String taxcat_SR;
	static double unit_SR=1;
	static double value_SR;
	//static public TableView<SalesTable1> table_p_SR ;
	
	static editcontroller1 cont = new editcontroller1();
	
	public static void main(String args[])
	{
		launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		AnchorPane ap = FullTicket1("T123","Administrator","10000","1000","11000");
		
		Scene scene = new Scene(ap);
		
		
		scene.getStylesheets().add(getClass().getResource("edit_sales.css").toExternalForm());
		 
		
		primaryStage.setScene(scene);
        primaryStage.setTitle("Sales Return Voucher");
        primaryStage.show();
		
	}
	/*
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
	    }*/
	
	static AnchorPane FullTicket1(String no,String rol, String totalNoTax, String tx,String ttt)
	{
		AnchorPane t = new AnchorPane();
		t.setId("fullticekt_SR");
		t.setPrefSize(670, 635);
		
		ImageView img = new ImageView();
		img.setId("cross_SR");
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
	    p_1.setId("line_b_SR");
	    p_1.setLayoutX(66.0);	 
	    p_1.setLayoutY(246.0);	
	    p_1.setPrefHeight(2.0);
	    p_1.setPrefWidth(200.0);
	    t.setBottomAnchor(p_1, 200.0);
	    t.setLeftAnchor(p_1, 35.0);
	    t.setRightAnchor(p_1, 35.0);
	    
	    Pane p_2 = new Pane();
	    p_2.setId("line_t_SR");
	    p_2.setLayoutX(76.0);	 
	    p_2.setLayoutY(256.0);
	    p_2.setPrefHeight(2.0);
	    p_2.setPrefWidth(200.0);
	    t.setTopAnchor(p_2, 100.0);
	    t.setLeftAnchor(p_2, 35.0);
	    t.setRightAnchor(p_2, 35.0);
	    
	    AnchorPane in = new AnchorPane();
	    in.setId("fullTd_SR");
	    in.setLayoutX(21.0);
	    in.setLayoutY(421.0);
	    in.setPrefSize(200.0, 130.0);
	    t.setBottomAnchor(in, 3.0);
	    t.setLeftAnchor(in, 0.0);
	    t.setRightAnchor(in, 0.0);
	    
	    Label rno = new Label();
	    rno.setLayoutX(31.0);
	    rno.setLayoutY(84.0);
	    rno.setText("Old Receipt No:");
	    rno.setTextFill(Color.WHITE);
	    rno.setFont(new Font("Roboto Regular", 25));
	    
	    in.setBottomAnchor(rno, 65.0);
	    in.setLeftAnchor(rno, 30.0);
	    
	    Label title = new Label();
	    title.setLayoutX(32.0);
	    title.setLayoutY(70.0);
	    title.setText("SALES RETURN");
	    title.setTextFill(Color.BLACK);
	    title.setFont(new Font("Roboto Regular", 25));
	    
	    Label repay = new Label();
	    //repay.setLayoutX(232.0);
	    //repay.setLayoutY(70.0);
	    repay.setText("Repay: ");
	    repay.setTextFill(Color.BLACK);
	    repay.setFont(new Font("Roboto Regular", 20));
	    
		t.setTopAnchor(repay, 349.0);
		t.setRightAnchor(repay, 80.0);
		
		TextField ramnt = new TextField();
		ramnt.setId("retur");
		ramnt.setPrefWidth(135.0);
		ramnt.setText("0");
		ramnt.setStyle("-fx-text-fill: #757575;");
		ramnt.setFont(new Font("Roboto Regular", 14));
	    t.setTopAnchor(ramnt, 375.0);
	    t.setRightAnchor(ramnt, 10.0);
	    
	    Label rNo = new Label();
	    //rNo.setId(value);
	    rNo.setLayoutX(169.0);
	    rNo.setLayoutY(35.0);
	    rNo.setPrefHeight(30.0);
	    rNo.setPrefWidth(134.0);
	    rNo.setText(no);
	    rNo.setTextFill(Color.WHITE);
	    rNo.setFont(new Font("Roboto Regular", 20));
	    
	    in.setBottomAnchor(rNo, 65.0);
	    in.setLeftAnchor(rNo, 210.0);
	    
		JFXButton salesreturn = new JFXButton("CONFIRM");
		salesreturn.setId("salesRF_SR");
		salesreturn.setLayoutX(409.0);
		salesreturn.setLayoutY(110.0);
		salesreturn.setFont(new Font("Roboto Bold", 15));
		in.setRightAnchor(salesreturn, 125.0);
		in.setBottomAnchor(salesreturn, 25.0);
		
		salesreturn.setOnAction(event->{
			System.out.println("Sales Return Confirmed!");
			try {
				cont.doReturn();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		JFXButton print = new JFXButton("PRINT");
		print.setId("printF_SR");
		print.setLayoutX(389.0);
		print.setLayoutY(90.0);
		print.setFont(new Font("Roboto Bold", 15));
		in.setBottomAnchor(print, 25.0);
		in.setRightAnchor(print, 40.0);
		
		JFXButton Delete = new JFXButton("Delete Item");
		Delete.setId("delete");
		Delete.setLayoutX(389.0);
		Delete.setLayoutY(90.0);
		Delete.setFont(new Font("Roboto Bold", 15));
		t.setTopAnchor(Delete, 135.0);
		t.setRightAnchor(Delete, 40.0);
		
		Label role = new Label();
		role.setId("roleF_SR");
	    role.setLayoutX(30.0);
	    role.setLayoutY(68.0);
	    role.setPrefHeight(30.0);
	    role.setPrefWidth(134.0);
	    role.setText(rol);
	    role.setStyle("-fx-text-fill: #eeeeee;");
	    role.setFont(new Font("Roboto Regular", 15));
	    in.setBottomAnchor(role, 35.0);
	    
	    in.getChildren().addAll(rno,rNo,print,salesreturn,role);
	    
		Label not = new Label();
		not.setId("nettax_SR");
	    not.setLayoutX(35.0);
	    not.setLayoutY(468.0);
	    not.setText("Net off Tax");
	    not.setStyle("-fx-text-fill: #757575;");
	    not.setFont(new Font("Roboto Regular", 14));
	    t.setBottomAnchor(not, 140.0);
	    t.setLeftAnchor(not, 40.0);
	    
		Label total = new Label();
		total.setId("nettax11_SR");
		total.setLayoutX(175.0);
		total.setLayoutY(488.0);
		total.setText("Total");
		total.setStyle("-fx-text-fill: #757575;");
		total.setFont(new Font("Roboto Regular", 14));
	    t.setBottomAnchor(total, 140.0);
	    t.setLeftAnchor(total, 550.0);
	    
		Label tax = new Label();
		tax.setId("nettax1_SR");
		tax.setLayoutX(165.0);
		tax.setLayoutY(478.0);
		tax.setText("Tax");
		tax.setStyle("-fx-text-fill: #757575;");
		tax.setFont(new Font("Roboto Regular", 14));
	    t.setBottomAnchor(tax, 140.0);
	    t.setLeftAnchor(tax, 280.0);
	    
		Label totF = new Label();
		totF.setId("price_SR");
		totF.setLayoutX(45.0);
		totF.setLayoutY(478.0);
		totF.setText("Rs. "+totalNoTax);
		totF.setFont(new Font("Roboto Bold", 14));
	    t.setBottomAnchor(totF, 165.0);
	    t.setLeftAnchor(totF, 40.0);
	    
	    Label taxF = new Label();
	    taxF.setId("tax_SR");
//	    taxF.setId("nettax21_SR");
	    taxF.setLayoutX(55.0);
	    taxF.setLayoutY(488.0);
	    taxF.setText("Rs. "+tx);
	    taxF.setFont(new Font("Roboto Bold", 14));
	    t.setBottomAnchor(taxF, 165.0);
	    t.setLeftAnchor(taxF, 280.0);
	    
	    Label totalF = new Label();
	    totalF.setId("total_SR");
	    totalF.setLayoutX(55.0);
	    totalF.setLayoutY(488.0);
	    totalF.setText("Rs. "+ttt);
	    totalF.setFont(new Font("Roboto Bold", 14));
	    t.setBottomAnchor(totalF, 165.0);
	    t.setLeftAnchor(totalF, 550.0);
	    
	    ScrollPane center = new ScrollPane();
	    center.setId("tbl_center_edit_SR");
	   // center.setLayoutX(66.0);
	    //center.setLayoutY(218.0);
	    center.setPrefSize(200.0, 200.0);
	    t.setTopAnchor(center, 130.0);
	    t.setBottomAnchor(center, 230.0);
	    t.setLeftAnchor(center, 35.0);
	    t.setRightAnchor(center, 150.0);
		
	    
	    t.getChildren().addAll(img,p_1,p_2,in,not,tax,total,totF,taxF,totalF);
		t.getChildren().addAll(center,title,Delete,repay,ramnt);
		
	    return t;
	}
	/*
	public ObservableList<SalesTable1> addData1(String n,double p,double u,double t,double v)
	{
		 
		   
		   ObservableList<SalesTable1> data;
		   
	    	al_SR.add(new SalesTable1(n, p, u, t, v));
		   	
	        data=FXCollections.observableList(al_SR);
			return data;
	}
*/
} 

class SalesTable2 {
	 
    private final SimpleStringProperty itemName;
    private final SimpleDoubleProperty itemPrice;
    private final SimpleDoubleProperty itemUnits;
    private final SimpleDoubleProperty itemTaxes;
    private final SimpleDoubleProperty itemValue;

    SalesTable2(String iName, Double iPrice, Double iUnits, Double iTaxes, Double iValue) {
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

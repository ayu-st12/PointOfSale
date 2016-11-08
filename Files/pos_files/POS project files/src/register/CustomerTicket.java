package register;

import java.beans.EventHandler;
import java.util.ArrayList;

import register.Controller.cust_PayController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

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

public class CustomerTicket extends Application{
	
	static AnchorPane a;
	static ScrollPane b;
	static String name_p;
	static double price;
	static double tax_val;
	static String taxcat;
	static double unit=1;
	static double value;
	

	public static void main(String args[])
	{
		launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		AnchorPane ap = FullTicket("Ayush Vardhan",1000.0,2000.0,"1");
		
		Scene scene = new Scene(ap);
		
		
		scene.getStylesheets().add(getClass().getResource("edit_sales.css").toExternalForm());
		 
		
		primaryStage.setScene(scene);
        primaryStage.setTitle("Ticket");
        primaryStage.show();
		
	}
	
	public static AnchorPane FullTicket(String Name,Double Debt,Double limit,String accid)
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
	    t.setTopAnchor(p_1, 380.0);
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
	    rno.setText(Name);
	    rno.setTextFill(Color.WHITE);
	    rno.setFont(new Font("Roboto Regular", 25));
	    
	    in.setBottomAnchor(rno, 65.0);
	    in.setLeftAnchor(rno, 30.0);
	    
		JFXButton salesreturn = new JFXButton("PAY");
		salesreturn.setId("salesRF");
		salesreturn.setLayoutX(389.0);
		salesreturn.setLayoutY(90.0);
		salesreturn.setFont(new Font("Roboto Bold", 15));
		in.setBottomAnchor(salesreturn, 35.0);
		in.setRightAnchor(salesreturn, 40.0);
		
		salesreturn.setOnAction(event->{
			System.out.println("Hello");
			cust_PayController.clearDebt(accid);
		});
		
		Label cid = new Label();
		cid.setText("Account Number: "+ accid);
		cid.setStyle("-fx-text-fill: #fff;");
		t.setTopAnchor(cid, 70.0);
		t.setLeftAnchor(cid, 35.0);
		
		JFXButton refund = new JFXButton("SAVE");
		refund.setId("refundF");
		refund.setLayoutX(399.0);
		refund.setLayoutY(100.0);
		refund.setFont(new Font("Roboto Bold", 15));
		in.setRightAnchor(refund, 100.0);
		in.setBottomAnchor(refund, 35.0);
		
		Label role = new Label();
		role.setId("roleF");
	    role.setLayoutX(30.0);
	    role.setLayoutY(68.0);
	    role.setPrefHeight(30.0);
	    role.setPrefWidth(134.0);
	    role.setText("");
	    role.setStyle("-fx-text-fill: #eeeeee;");
	    role.setFont(new Font("Roboto Regular", 15));
	    in.setBottomAnchor(role, 35.0);
	    
	    in.getChildren().addAll(rno,refund,salesreturn,role,cid);
	    
	    Label totalF = new Label();
	    totalF.setId("nettax22");
	    totalF.setLayoutX(55.0);
	    totalF.setLayoutY(488.0);
	    totalF.setText("");
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
		img4.setLayoutX(55.0);
		img4.setLayoutY(37.0);
		img4.pickOnBoundsProperty().set(true);
		img4.preserveRatioProperty().set(true);
		t.setTopAnchor(img4, 220.0);
		t.setLeftAnchor(img4, 35.0);
		
		final Image image4 = new Image(fullTicket.class.getResourceAsStream("XMLID_56_.png"));	
		img4.setImage(image4);
		
		ImageView img5 = new ImageView();
		img5.fitHeightProperty().set(30.0);
		img5.fitWidthProperty().set(30.0);
		img5.setLayoutX(55.0);
		img5.setLayoutY(37.0);
		img5.pickOnBoundsProperty().set(true);
		img5.preserveRatioProperty().set(true);
		t.setTopAnchor(img5, 400.0);
		t.setLeftAnchor(img5, 35.0);
		
		final Image image5 = new Image(fullTicket.class.getResourceAsStream("XMLID_56_.png"));	
		img5.setImage(image5);
		
		Label dateF = new Label();
		dateF.setId("nettax3");
		dateF.setLayoutX(70.0);
		dateF.setLayoutY(49.0);
		dateF.setText("Credit Limit");
		dateF.setStyle("-fx-text-fill: #757575;");
		dateF.setFont(new Font("Roboto Regular", 14));
	    t.setTopAnchor(dateF, 50.0);
	    t.setLeftAnchor(dateF, 80.0);
	    
	    Label termF = new Label();
	    termF.setId("nettax31");
	    termF.setLayoutX(80.0);
		termF.setLayoutY(59.0);
		termF.setText("Current Debt");
		termF.setStyle("-fx-text-fill: #757575;");
		termF.setFont(new Font("Roboto Regular", 14));
	    t.setTopAnchor(termF, 110.0);
	    t.setLeftAnchor(termF, 80.0);
	    
	    Label custF = new Label();
	    custF.setId("nettax311");
	    custF.setLayoutX(90.0);
	    custF.setLayoutY(69.0);
	    custF.setText("Debt Date");
	    custF.setStyle("-fx-text-fill: #757575;");
	    custF.setFont(new Font("Roboto Regular", 14));
	    t.setTopAnchor(custF, 170.0);
	    t.setLeftAnchor(custF, 80.0);
		
	    JFXTextArea center = new JFXTextArea();
	    center.setStyle("	-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.7), 2, 0, 0, 0);"+
	    		"-fx-background-color: #607d8b;");
	    center.setPrefSize(300.0, 100.0);
	    t.setTopAnchor(center, 250.0);
	    t.setLeftAnchor(center, 80.0);
	    t.setRightAnchor(center, 80.0);
	    
	    JFXTextField clear = new JFXTextField();
	    clear.setId("clearit");
	    clear.setStyle("	-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.7), 2, 0, 0, 0);"+
	    		"-fx-background-color: #fff;");
	    clear.setPrefWidth(200.0);
	    t.setTopAnchor(clear, 430.0);
	    t.setLeftAnchor(clear, 80.0);
	    
	    
	    Label amtPay = new Label();
	    amtPay.setId("nettax2");
	    amtPay.setLayoutX(45.0);
	    amtPay.setLayoutY(478.0);
	    amtPay.setText("Enter Amount to Pay");
	    amtPay.setFont(new Font("Roboto Bold", 14));
	    t.setTopAnchor(amtPay, 400.0);
	    t.setLeftAnchor(amtPay, 80.0);
	    
	    Label notes = new Label();
	    notes.setId("nettax2");
	    notes.setLayoutX(45.0);
	    notes.setLayoutY(478.0);
	    notes.setText("Notes");
	    notes.setFont(new Font("Roboto Bold", 14));
	    t.setTopAnchor(notes, 220.0);
	    t.setLeftAnchor(notes, 80.0);
	    
	    Label date = new Label();
	    date.setId("nettax2");
	    date.setLayoutX(45.0);
	    date.setLayoutY(478.0);
	    date.setText("5th July 2016");
	    date.setFont(new Font("Roboto Bold", 14));
	    t.setTopAnchor(date, 150.0);
	    t.setLeftAnchor(date, 80.0);
	    
	    Label debt = new Label();
	    debt.setId("udebt");
	    debt.setLayoutX(45.0);
	    debt.setLayoutY(478.0);
	    debt.setText("Rs. "+Double.toString(Debt));
	    debt.setFont(new Font("Roboto Bold", 14));
	    t.setTopAnchor(debt, 90.0);
	    t.setLeftAnchor(debt, 80.0);
	    
	    Label lmt = new Label();
	    lmt.setId("nettax2");
	    lmt.setLayoutX(45.0);
	    lmt.setLayoutY(478.0);
	    lmt.setText("Rs. "+Double.toString(limit));
	    lmt.setFont(new Font("Roboto Bold", 14));
	    t.setTopAnchor(lmt, 30.0);
	    t.setLeftAnchor(lmt, 80.0);
	    
	    t.getChildren().addAll(img,p_1,p_2,in,totalF,img1,img2,img3,img4,img5,dateF,termF,custF);
		t.getChildren().addAll(center,amtPay,clear,notes,date,debt,lmt);
		
	    return t;
	}


}

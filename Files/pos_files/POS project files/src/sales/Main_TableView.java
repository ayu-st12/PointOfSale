package sales;

import java.util.ArrayList;

import sales.Main_TableView.SalesTable;
import application.DeleteSelectedRow.Person;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main_TableView extends Application{
	
	   	static private TableView<SalesTable> table_p = null  ;
	    private IntegerProperty index = new SimpleIntegerProperty();
	    final Button delButton = new Button("Delete");
	    final Button unitButton = new Button("Units");
	    static TableView<SalesTable> t1=null;
	    
	    static SalesTable s;
	    static String name_p;
		static double price;
		static double tax_val;
		static String taxcat;
		static double unit=1;
		static double value;
	    
	    public AnchorPane p = new AnchorPane();
	    static ObservableList<SalesTable> data_tbl;
	    static ArrayList<SalesTable> al=new ArrayList<SalesTable>();
	    
	    public static void main(String[] args) {
	        launch(args);
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
	 
		@Override
	    public void start(Stage stage) {

	    	ScrollPane sp=new ScrollPane();
	    	sp.setFitToHeight(true);
	    	sp.setFitToWidth(true);
	    	
	    	 
	    	
	    	data_tbl = addData("Ayushi",100.0,unit,0.0,100);
	    	t1 = item(data_tbl);
	    	
	    	data_tbl = addData("Vardhan",200.0,2,0.0,200);
	    	t1 = item(data_tbl);
	    	
	    	data_tbl = addData("Yash",300.0,unit,0.0,200);
	    	t1 = item(data_tbl);
	    	
	    	data_tbl = addData("Ishan",400.0,unit,0.0,200);
	    	t1 = item(data_tbl);
				    	
	        sp.setContent(t1);
	        
	        indexProperty().addListener(new ChangeListener() {

	            @Override
	            public void changed(ObservableValue o, Object oldVal,
	                    Object newVal) {
	                System.out.println("Index has changed!");
	                System.out.println("Inside indexPropoerty() listener");
	            }
	        });
	        
	        delButton.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent e) {

	                data_tbl.remove(index.get());
	                
	            }
	        });
	        
	        unitButton.setOnAction(new EventHandler<ActionEvent>() {

	            @Override
	            public void handle(ActionEvent e) {
	            	
	            	int st=0;
	            	int t=0;
	            	int size  = table_p.getItems().size();
	                System.out.println("Size is:"+ size);
	                
	                for(int i=0;i<size;i++)
	                {
		                s = table_p.getItems().get(i);
	                	System.out.println(s.getItemName() + s.getItemPrice() + s.getItemValue());
	                	st +=  s.getItemPrice();
	                	t += s.getItemValue();
	                }
	                
	                System.out.println("Subtotal :"+st+ " Total: "+t);
	                
	            }
	        });
	        
	        p.setBottomAnchor(unitButton, 0.0);
	        p.getChildren().addAll(sp,delButton,unitButton);
	        Scene scene=new Scene(p,470,260);	
	        stage.setScene(scene);
	        stage.show();     
	        
	        
	       
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
		               s = table.getSelectionModel().getSelectedItem();
		               
		               
		               System.out.println(s.getItemName());
		               
		            }
		        });
		        
		        return table;
		    }
	    
		public ObservableList<SalesTable> addData(String n,double p,double u,double t,double v)
		{
			 
			   
			   ObservableList<SalesTable> data;
			   
		    	al.add(new SalesTable(n, p, u, t, v));
			   	
		        data=FXCollections.observableList(al);
				return data;
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
}
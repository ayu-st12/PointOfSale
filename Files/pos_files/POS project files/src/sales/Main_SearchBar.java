package sales;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main_SearchBar extends Application {

	AnchorPane p;
	ImageView iv_search;
	ImageView iv_close;
	TextField tf;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try{

		AnchorPane p1=searchIconClicked();
			
		Scene scene=new Scene(p1,600,400);
	
		primaryStage.setTitle("POS Search Bar");
		primaryStage.setScene(scene);
		primaryStage.show();
		}
		catch(Exception e){
		e.printStackTrace();
		}
	}
	
	@FXML
	public AnchorPane searchIconClicked(){
		
		p=new AnchorPane();
		p.setLayoutX(2.0);
		p.setLayoutY(2.0);
		p.setPrefHeight(50.0);
		p.setPrefWidth(50.0);
		
		Image i=new Image(getClass().getResource("ic_search_black_24dp.png").toExternalForm());
		iv_search=new ImageView(i);
		iv_search.setLayoutX(560);
		iv_search.setLayoutY(5);
		iv_search.setFitHeight(32.0);
		iv_search.setFitWidth(32.0);
		
		p.getChildren().add(iv_search);
		
		iv_search.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
				
					furtherEvents();
					event.consume();
				}
		});
		/*
		*/
		return p;
	}
	
	public void furtherEvents(){
		//TEXTFIELD APPEARED
		tf=new TextField();
		tf.setLayoutX(352);
		tf.setLayoutY(5);
		tf.setPrefWidth(210);
		tf.setPrefHeight(4);
		
		iv_search.setLayoutX(320);
		iv_search.setLayoutY(5);
		p.getChildren().add(tf);
		
		onContentSearched();
		
		//CLOSE ICON APPEARED
		Image i2=new Image(getClass().getResource("ic_close_black_24dp.png").toExternalForm());
		iv_close=new ImageView(i2);
		iv_close.setLayoutX(560);
		iv_close.setLayoutY(5);
		iv_close.setFitHeight(32.0);
		iv_close.setFitWidth(32.0);
		p.getChildren().add(iv_close);

		iv_close.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				tf.setPrefWidth(0);
				tf.setVisible(false);
				//tf.setLayoutX(1020);
				iv_search.setLayoutX(560);
				iv_search.setLayoutY(5);
				
				iv_close.setVisible(false);
				event.consume();
			}
			
		});
	}
	
	public void checkTextFieldFocussed(){
		tf.focusedProperty().addListener(new ChangeListener<Boolean>()
				{
				    @Override
				    public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
				    {
				        if (newPropertyValue)
				        {
				            tf.setStyle("-fx-background-color: #a9a9a9 , white , white;"
				            		+ "-fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
				        }
				        else
				        {
				            tf.setStyle("-fx-background-color: #a9a9a9 , white , white;"
				            		+ "-fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;");
				        }
				    }
				});
	}
	
	public void onContentSearched(){
		tf.setOnKeyPressed(new EventHandler<KeyEvent>()
			    {
			        @Override
			        public void handle(KeyEvent ke)
			        {
			            if (ke.getCode().equals(KeyCode.ENTER))
			            {
			                System.out.println("Query Searched: "+tf.getText());
			            }
			        }
			    });

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}

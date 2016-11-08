package extraTest;

import sales.product;
import javafx.application.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class cat_top extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public static Pane catTop()
	{
		Pane cat_top = new Pane();
		cat_top.setId("cat_name");
		cat_top.setPrefSize(300.0, 50.0);
		cat_top.setLayoutY(0.0);
		

		Pane child_p1 = new Pane();
		child_p1.setId("c1");
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
		img.setId("ClickSwing");
		
		final Image image = new Image(product.class.getResourceAsStream("img/ic_arrow_right_grey600_36dp.png"));	
	    img.setImage(image);
		
		child_p1.getChildren().addAll(img);
		
		Pane child_p2 = new Pane();
		child_p2.setPrefHeight(50.0);
		child_p2.setPrefWidth(150.0);
		child_p2.setLayoutX(52.0);
		child_p2.setLayoutY(2.0);
		
		Label p_name = new Label();
		p_name.setId("val");
		p_name.setLayoutY(15.0);
		p_name.setLayoutX(-2.0);
		p_name.setPrefHeight(20.0);
		p_name.setText("CATEGORY");
		p_name.setTextFill(Color.web("#616161"));
		p_name.setFont(new Font("Roboto Black", 16));

		child_p2.getChildren().addAll(p_name);
		
		cat_top.getChildren().addAll(child_p1,child_p2);
		
		return cat_top;
	}

}

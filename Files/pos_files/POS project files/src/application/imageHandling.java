package application;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import database.*;

import javax.imageio.ImageIO;

/**
 * @web http://java-buddy.blogspot.com/
 */
public class imageHandling extends Application {
    
	dbfunc db = new dbfunc();
    ImageView myImageView;
    
    @Override
    public void start(Stage primaryStage) {
        
        Button btnLoad = new Button("Load");
        btnLoad.setOnAction(btnLoadEventListener);
        
        myImageView = new ImageView();        
        
        VBox rootBox = new VBox();
        rootBox.getChildren().addAll(btnLoad, myImageView);
        
        Scene scene = new Scene(rootBox, 300, 300);
        
        primaryStage.setTitle("java-buddy.blogspot.com");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    EventHandler<ActionEvent> btnLoadEventListener
    = new EventHandler<ActionEvent>(){

        @Override
        public void handle(ActionEvent t) {
            FileChooser fileChooser = new FileChooser();
            
            //Set extension filter
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.*)", "*.*");
            fileChooser.getExtensionFilters().addAll(extFilterJPG);
             
            //Show open file dialog
            File file = fileChooser.showOpenDialog(null);
                      
            try {
            	FileInputStream fis = new FileInputStream(file);
            	//RenderedImage renderedImage = SwingFXUtils.fromFXImage(file, null);
            	//File file = new File("myPhoto.png");
                //BufferedImage bufferedImage = ImageIO.read(file);
                //ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //ImageIO.write( bufferedImage, "jpg", baos );
                //baos.flush();
                //byte[] imageInByte = baos.toByteArray();
                //System.out.println(imageInByte);
                //baos.close();
                
                Connection co = db.connect();
                String q = "insert into test values (?)";
      
                PreparedStatement s = co.prepareStatement(q);
                s.setBinaryStream(1, fis, (int) file.length());
                int rt = s.executeUpdate();
                
                if(rt==1)
                {
                	System.out.println("Inserted");
                }
                System.out.println("here");
                String q1 = "select image from test";
                System.out.println("here2");
                PreparedStatement s1 = co.prepareStatement(q1);
                System.out.println("here3");
                ResultSet rs =s1.executeQuery();
                System.out.println("here4");
                while(rs.next())
                {
                	InputStream image=rs.getBinaryStream("image");
                	BufferedImage bImageFromConvert = ImageIO.read(image);
                    Image image1 = SwingFXUtils.toFXImage(bImageFromConvert, null);
                    myImageView.setImage(image1);
                }
                
            } catch (IOException ex) {
                Logger.getLogger(imageHandling.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
    };
}
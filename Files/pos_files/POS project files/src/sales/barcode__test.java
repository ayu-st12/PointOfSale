package sales;

import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.Instant;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class barcode__test {

	
	public static void main(String args[])
	{


		 JTextField textField = new JTextField();

	      textField.addKeyListener(new Keychecker());

	      JFrame jframe = new JFrame();

	      jframe.add(textField);

	      jframe.setSize(400, 350);

	      jframe.setVisible(true);

		
	}
}



class Keychecker extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent event) {

  char ch = event.getKeyChar();

  System.out.println(event.getKeyChar());
  
  if (event.getKeyCode() == KeyEvent.VK_ENTER)
  {
    System.out.println("ENTER key pressed");
  }

  }
   }

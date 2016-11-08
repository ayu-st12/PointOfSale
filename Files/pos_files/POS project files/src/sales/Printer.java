package sales;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.print.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class Printer implements Printable{
	String bill;
	@Override
    public int print(Graphics g, PageFormat pf, int page) throws
                                                        PrinterException {
 
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }
 
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 8);
        g2d.setFont(f);
        int y=5;
        String []tp = createbill();
        for(int i=0;i<tp.length;i++){
        	g.drawString(tp[i],70,300+y);
        	y+=15;
        }
 
        return PAGE_EXISTS;
    }
 
	public String[] createbill(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    DateFormat timeFormat = new SimpleDateFormat("HH:mm");
	      //get current date time with Date()
	      Date date = new Date();
	      Date time = new Date();
	      String Date = dateFormat.format(date);
	      String Time = timeFormat.format(time);
	      String Header = 
	            "        ****Super Market****       \n"
	            + "Date: "+Date+"       Time: "+Time+"\n"
	            + "Ticket Id: "+main_Controller.ticket_id+"\n"
	            + "----------------------------------------------\n"
	            + "Name          Qty      Rate          Amt\n"
	            + "----------------------------------------------\n";

	      String amt  =    
	            "\n \n \nTotal Amount = "+  main_Controller.done   +"\n"
	            + "Tax ="   +  "tacs"    + "\n"
	            + "*********************************\n"
	            + "Thank you. \n";

	      bill = Header;
	      int i = 0;
	      for(i=0; i<(main_Controller.data_tbl.size()); i++){
	         String name =     ""+ main_Controller.table_p.getItems().get(i).getItemName().toString();
	         String qty =      ""+ main_Controller.table_p.getItems().get(i).getItemUnits().toString();
	         String rate =     ""+ main_Controller.table_p.getItems().get(i).getItemValue().toString();
	         String amount =   ""+ Double.toString(Double.parseDouble(main_Controller.table_p.getItems().get(i).getItemUnits().toString())*Double.parseDouble(main_Controller.table_p.getItems().get(i).getItemValue().toString()));

	         if(name.length() > 12)
	         {
	             name = name.substring(0, 12)+"  ";
	         }
	         else
	         {
	             for(int j= name.length()-12; j<= name.length(); j++);
	             {
	                 name = name+" ";
	             } 
	         }


	         if(qty.length()<=5)
	         {
	             for(int j= 0; j<= qty.length()-5; j++);
	             {
	                qty = qty+" ";
	             }
	         }

	         rate = rate;
	         String items = 
	             name+"     "+qty+"       "+rate+"      "+amount+"\n";

	         bill = bill+ items;       
	      }

	     bill = bill+amt;
	     String []toprint = bill.split("\n");
	     System.out.println(toprint);
		return toprint;
	}
    public void pin(){
         PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
             try {
                  job.print();
             } catch (PrinterException ex) {
              /* The job did not successfully complete */
             }
    }
}

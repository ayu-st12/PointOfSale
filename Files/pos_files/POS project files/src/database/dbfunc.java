/*										VARIABLE & FUNCTIONS DOCUMENTATION
 * 
 * 
 * 			Variable-Name		Type								Purpose
 * -------------------------------------------------------------------------------------------------
 * 			c					Connection			for creating connection
 * 			e					Exception			for handling exceptionss
 * 
 * 
 *  		Function-Name		Attributes					Type								Purpose
 * --------------------------------------------------------------------------------------------------------------------
 * 			connect				c							Connection			returning connection variable		
 * 			close_conn			c							Connection			Closing connection to database
 */

//file tested

package database;

import java.sql.*;

public class dbfunc implements db_constant{
	  static Connection c;
	  static{    
		      try {
			         Class.forName(driver_name);
			         c = DriverManager.getConnection(db_url,user,pass);
			         
			         if(c!=null)
					      System.out.println("Opened database successfully");
			         
			      } catch (Exception e) {
			         e.printStackTrace();
			         System.err.println(e.getClass().getName()+": "+e.getMessage());
			         System.exit(0);
			      }
	  }
	  public Connection connect()
	  {		
			return c;
	  }
	   
	   public static void close_conn()
	   {
		   try {
				c.close();
				System.out.println("Database Connection closed");
			} catch (SQLException e) {
				 e.printStackTrace();
			}
	   }
}
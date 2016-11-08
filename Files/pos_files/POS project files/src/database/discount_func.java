/*										VARIABLE & FUNCTIONS DOCUMENTATION
 * 
 * 
 * 			Variable-Name		Type								Purpose
 * -------------------------------------------------------------------------------------------------
 * 		 	productid			String				to fetch discount from product table
 * 		 	cost				double 				for holding MRP of product from sales window
 * 		 	discount			double				for holding discount from sales window
 * 			c					Connection			for creating connection
 * 			c1					Connection			for getting the return type connection from jdbc
 * 		  	query				String				fire a query
 * 		   	rs					ResultSet			for getting resultset from JDBC
 * 			d					int					for holding discount value fetched for product
 * 			ob					dbfunc				for closing and opening database
 * 			ob1					dbfunc_other		for handling other database functions
 * 
 * 			t					Used to handle query, which kind of query it is? ExecuteQuery[0]/ExecuteUpdate[1]
 * 
 *  		Function-Name		Attributes					Type								Purpose
 * --------------------------------------------------------------------------------------------------------------------
 * 			discount			productid,cost,discount		double				handling discount for single product		
 * 			discount			cost,discount				double				handling discount for total bill.
 */

package database;

import java.sql.Connection;

import database.dbfunc;
import database.dbfunc_other;

public class discount_func {
	
	public static void main(String args[])
	{
		System.out.println("All Ok");
	}
		
	// For discount on single product
	public double discount(String productid,double cost, double discount)
	{
		//dbfunc ob = new dbfunc();
		//dbfunc_other ob1 = new dbfunc_other();
		//Connection c = null; Connection c1 = null;
		//c1 = ob.connect(c);
		//String query = "select discount from product where id='productid';";
		//int t = 0;
		//ResultSet rs = ob1.FireQ(c1,query,t);
		//int d = rs.getInt("discount");
		//ob.close(c1);
		//cost = cost - (cost*d)/100.00;
		
		cost = cost - (cost*discount)/100.00;
		return cost;  //it will work as price there on sales window.
	}

	// For discount on bill
	public double discount(double cost,double discount)
	{
		
		cost = cost - (cost*discount)/100.00;
		return cost;
		
	}
}

package sales;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.*;

public class Fetch_Categories {
	
	static dbfunc d = new dbfunc();
	 static dbfunc_other op = new dbfunc_other();
	 static ResultSet rs;
	 static Connection co;
	 static Statement s;
	String result;
	
	private static String id_category;
	private static String name_category;
	
	public static void main(String args[]) throws SQLException
	{
		//fetch_cat();
	}
	
	public static ResultSet fetch_cat() throws SQLException
	{
		co = d.connect();
		s = co.createStatement();
		String query = "Select id,name from categories where pid IS NULL;";
		rs = s.executeQuery(query);
		if(rs!=null)
		{
			return rs;
		}
		else{
			System.out.println("Not entered to loop");
		}
		return rs;     
	}
}

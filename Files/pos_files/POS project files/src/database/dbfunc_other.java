/*										VARIABLE & FUNCTIONS DOCUMENTATION
 * 
 * 
 * 			Variable-Name		Type								Purpose
 * -------------------------------------------------------------------------------------------------
 * 			c					Connection			for creating connection
 * 			e					Exception			for handling exceptions
 * 		  	query				String				fire a query
 * 			t					Used to handle query, which kind of query it is? ExecuteQuery[0]/ExecuteUpdate[1]
 * 
 *  		Function-Name		Attributes					Type								Purpose
 * --------------------------------------------------------------------------------------------------------------------
 * 			FireQ				c,Query						void			Fire any query recieved in Query		
 * 			check_table			c							void			check if table exists
 * 			create_table		c							void			create tables
 */

package database;

import database.dbfunc;

import java.sql.*;


public class dbfunc_other {
	
	static dbfunc d = new dbfunc();
	ResultSet rs;
	Connection co;
	Statement s;
	String result;
	   
	   // FETCHING ONE ATTRIBUTES FROM A TABLE WITHOUT USING WHERE CONDITION (tested)   
	   public ResultSet fetch1wc(String sel_col, String table){
		   
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "Select " + sel_col+ " from "+table ;
			rs = s.executeQuery(query);
			if(rs!=null)
				return rs;
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return null;
	 } 

	   // FETCHING TWO ATTRIBUTES FROM A TABLE WITHOUT USING WHERE CONDITION (tested)
	   public ResultSet fetch2wc(String sel_col1, String sel_col2, String table)
		{
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "Select " + sel_col1 + "," + sel_col2 + " from "+table ;
			rs = s.executeQuery(query);
			if(rs!=null)
				return rs;
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return null;
		}  
	   
	   // FETCHING ONE ATTRIBUTES FROM A TABLE USING WHERE CONDITION (tested)	   
	   public String fetch1(String sel_col, String table, String where_col, String cond_val){
		   
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "Select " + sel_col+ " from "+table+" where "+where_col+" = "+ "'"+cond_val+"'";
			rs = s.executeQuery(query);
			if(rs!=null){
				while(rs.next())
				{
					return rs.getString(1);
				}
		   }
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return null;
	 } 
	   
	   // FETCHING TWO ATTRIBUTES FROM A TABLE USING WHERE CONDITION (tested)
	   public ResultSet fetch2(String sel_col1, String sel_col2, String table, String where_col, String cond_val)
		{
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "Select " + sel_col1 + "," + sel_col2 + " from "+table+" where "+where_col+" = "+ "'"+cond_val+"'";
			rs = s.executeQuery(query);
			if(rs!=null)
				return rs;
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return null;
		}
	   
	   // FETCHING ALL TABLE USING A WHERE CONDITION (tested)
	   public ResultSet fetchn( String table, String where_col, String cond_val)
		{
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "Select * " + " from "+table+" where "+where_col+" = "+ "'"+cond_val+"'";
			rs = s.executeQuery(query);
			if(rs!=null)
				return rs;
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return null;
		}

	   // FETCHING A TABLE WITHOUT A WHERE CONDITION (tested)
	   public ResultSet fetcht( String table)
		{
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "Select * " + " from "+table ;
			rs = s.executeQuery(query);
			if(rs!=null)
				return rs;
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return null;
		}

	   //DELETE A ROW (tested)
	   public int delete_row(String table, String col,String val)
		{
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "delete from "+ table + " where "+ col +"=" + "'"+val+"'";
			int n = s.executeUpdate(query);
			if(n==0) //nothing updated
				return 0;
			else
				return 1;
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return 0;
		   
		}
	   
	   //UPDATING TABLE (tested)
	   public int update_tbl(String table, String update_col, String update_val, String row, String rowval){
		   
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "update "+ table +" set "+ update_col +"=" + "'"+ update_val + "'"+" where "+ row +"=" + "'"+rowval+"'";
			int n = s.executeUpdate(query);
			if(n==0) //nothing updated
				return 0;
			else
				return 1;
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return 0;
	 }
	   
	   //Executing Query TABLE
	   public ResultSet ExecuteQ(String query){
		   
		   co = d.connect();
		   try {
			s = co.createStatement();
			ResultSet n = s.executeQuery(query);
			if(n==null) //nothing updated
				return null;
			else
				return n;
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return null;
	 }	   

	   //Executing Update TABLE
	   public int ExecuteU(String query){
		   
		   co = d.connect();
		   try {
			s = co.createStatement();
			int n = s.executeUpdate(query);
			if(n==0) //nothing updated
				return 0;
			else
				return 1;
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return 0;
	 }	   
	   
	   //UPDATING 2 FEILDS IN A TABLE
	   public int update_tbl_a2(String table, String update_col1, String update_val1,String update_col2, String update_val2, String row, String rowval){
		   
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "update "+ table +" set "+ update_col1 +"=" + "'"+ update_val1 +"," +update_col2 +"=" + "'"+ update_val2+ "'"+ " where "+ row +"=" + "'"+rowval+"'";
			int n = s.executeUpdate(query);
			if(n==0) //nothing updated
				return 0;
			else
				return 1;
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return 0;
	 }

	 //INSERTING DATA INTO TABLE WITH 6 ATTRIBUTES
	   public int insert_6(String table, String attr1, String attr2, String attr3, String attr4, String attr5,String attr6)
		{
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "insert into "+table+ " values ('"+ attr1 +"','"+attr2 +"','"+attr3+"','"+attr4+"','"+attr5+"','"+attr6+"');";
			int n = s.executeUpdate(query);
			if(n==0) //nothing updated
				return 0;
			else{
				//System.out.println("Data Inserted"+table);
				return 1;
			}
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return 0;
		}
	   
	   // INSERTING DATA INTO TABLE WITH 7 ATTRIBUTES
	   public int insert_7(String table, String attr1, String attr2, String attr3, String attr4, String attr5,String attr6,String attr7)
		{
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "insert into "+table+ "values ("+ attr1 +","+attr2 +","+attr3+","+attr4+","+attr5+","+attr6+","+attr7+ ")";
			int n = s.executeUpdate(query);
			if(n==0) //nothing updated
				return 0;
			else{
				//System.out.println("Data Inserted"+table);
				return 1;
			}
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return 0;
		}
	   
	   //Inserting 15 values
	   
	   public int insert_15(String table, String attr1, String attr2, String attr3, String attr4, String attr5,String attr6,String attr7,String attr8,String attr9,String attr10,String attr11,String attr12,String attr13,String attr14,String attr15)
		{
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "insert into "+table+ " values ('"+ attr1 +"','"+attr2 +"','"+attr3+"','"+attr4+"',"+Double.parseDouble(attr5)+",'"+attr6+"','"+attr7+"','"+attr8+"',"+Double.parseDouble(attr9)+",'"+attr10+"','"+attr11+"','"+attr12+"','"+attr13+"','"+attr14+"','"+attr15+ "')";
			int n = s.executeUpdate(query);
			if(n==0) //nothing updated
				return 0;
			else{
				//System.out.println("Data Inserted"+table);
				return 1;
			}
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return 0;
		}


	   // INSERTING DATA INTO TABLE WITH 5 ATTRIBUTES
	   public int insert_5(String table, String attr1, String attr2, String attr3, String attr4, String attr5)
		{
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "insert into "+table+" values ('"+attr1+"' ,'"+attr2 +"' ,'"+attr3+"' ,'"+attr4+"' ,'"+attr5+"' ); " ;
			int n = s.executeUpdate(query);
			if(n==0) //nothing updated
				return 0;
			else{
				//System.out.println("Data Inserted"+table);
				return 1;
			}
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return 0;
		}

	   
	   
	   // INSERTING DATA INTO TABLE WITH 3 ATTRIBUTES
	   public int insert_3(String table, String attr1, String attr2, String attr3)
		{
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "insert into "+table+" values ('"+attr1+"' ,'"+attr2 +"' ,'"+attr3+"'); " ;
			int n = s.executeUpdate(query);
			if(n==0) //nothing updated
				return 0;
			else{
				//System.out.println("Data Inserted"+table);
				return 1;
			}
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return 0;
		}
	   
	   public int insert_4(String table, String attr1, String attr2, String attr3, String attr4)
		{
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "insert into "+table+" values ('"+attr1+"' ,'"+attr2 +"' ,'"+attr3+"','"+attr4+"'); " ;
			int n = s.executeUpdate(query);
			if(n==0) //nothing updated
				return 0;
			else{
				//System.out.println("Data Inserted"+table);
				return 1;
			}
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return 0;
		}
	   
	   public int insert_4cat(String table, String attr1, String attr2, String attr3, String attr4)
		{
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "insert into "+table+" values ('"+attr1+"' ,'"+attr2 +"' ,"+attr3+",'"+attr4+"'); " ;
			int n = s.executeUpdate(query);
			if(n==0) //nothing updated
				return 0;
			else{
				//System.out.println("Data Inserted"+table);
				return 1;
			}
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return 0;
		}
	   /*

	   
	  
	   // INSERTING DATA INTO TABLE WITH 16 ATTRIBUTES
	   public int insert_16(String table, String attr1, String attr2, String attr3, String attr4, String attr5,String attr6,String attr7,String attr8,String attr9,String attr10,String attr11,String attr12,String attr13,String attr14,String attr15,String attr16)
		{
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "insert into "+table+ "values ("+ attr1 +","+attr2 +","+attr3+","+attr4+","+attr5+","+attr6+","+attr7+","+attr8+","+attr9+","+attr10+","+attr11+","+attr12+","+attr13+","+attr14+","+attr15+","+attr16+ ")";
			int n = s.executeUpdate(query);
			if(n==0) //nothing updated
				return 0;
			else{
				System.out.println("Data Inserted");
				return 1;
			}
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return 0;
		}	   	   
	   
	   // INSERTING DATA INTO TABLE WITH 20 ATTRIBUTES
	   public int insert_20(String table, String attr1, String attr2, String attr3, String attr4, String attr5,String attr6,String attr7,String attr8,String attr9,String attr10,String attr11,String attr12,String attr13,String attr14,String attr15,String attr16,String attr17,String attr18,String attr19,String attr20)
		{
		   co = d.connect();
		   try {
			s = co.createStatement();
			String query = "insert into "+table+ "values ("+ attr1 +","+attr2 +","+attr3+","+attr4+","+attr5+","+attr6+","+attr7+","+attr8+","+attr9+","+attr10+","+attr11+","+attr12+","+attr13+","+attr14+","+attr15+","+attr16+","+attr17+","+attr18+","+attr19+","+attr20+ ")";
			int n = s.executeUpdate(query);
			if(n==0) //nothing updated
				return 0;
			else{
				System.out.println("Data Inserted");
				return 1;
			}
		   }
		   catch (Exception e){
			   System.out.println(e.getMessage());
		   }
		   return 0;
		}	   	   
	   */  
}


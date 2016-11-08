package database;

import java.util.Date;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import database.dbfunc_other;

public class test {
	static dbfunc_other d = new dbfunc_other();
	static int n;
	public static void testdb() throws SQLException{
		n = d.delete_row("people", "id", "121");
		if(n!=0){
			System.out.println(n);
			 }
	}
	
	public static void main(String args[]) throws Exception
	{
/*		String a = "Aysuh";
		byte[] bytesOfMessage = a.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(bytesOfMessage);
		System.out.print(thedigest);
*/	
		
//		insert_costumer(true,,null);
/*		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dte = dateFormat.format(date); //2014/08/06 15:59:48
*/		
		//insert_costumer("121","S123","AYUSH","STANDARD","10000","Patna","Kankarbagh","800026","patna","Kankarbagh","India","Ayush","Vardhan","ayushvardha@gmail.com","8941096130","9934310407","GOOD MAN",true,dte,null);
//	insert_product("121","R121",null,"UTF-8","HAMAM",100,200,"STANDARD","STANDARD",1200,100,null,10,true,100,true);
		//insert into product values(121,'R121',null,'UTF-8','HAMAM',100,200,'STANDARD','STANDARD',10000,10,null,10,true,10,true);
	//delete_product("121");
		//delete_tax("121");
		//insert_category("121","Aysuh",null,null,"121",true);
		//insert_tax("121","VAT","STANDARD","STANDARD",null,10,true);
		//check_cred("E121","1234");
		//insert_closedcash("121",1200,"AYUSH",0,"2016-12-12 10:31:02","2016-12-12 10:32:04");
		//delete_payments("T121");
	}
}

package database;

import java.sql.Date;

import database.dbfunc_other;

public class costumer_func {
	static dbfunc_other d = new dbfunc_other();
	// format of Date will be: 2014-03-11 19:24:11
	public static void insert_costumer(String id,String skey, String name, String taxcat,String maxdebt,String addr,String addr2, String postal,String city,String region,String country,String fn, String ln,String mail,String phone, String phone2,String notes,Boolean visible,String dte, Byte[] img) throws Exception
	{
		String query = "insert into customers values("+id+","+ "'" +skey+ "'" +","+ "'" +name+ "'" +","+ "'" +taxcat+ "'" +","+maxdebt+","+ "'" +addr+ "'" +","+ "'" +addr2+ "'" +","+ "'" +postal+ "'" +","+ "'" +city+ "'" +","+ "'" +region+ "'" +","+ "'" +country+ "'" +","+ "'" +fn+ "'" +","+ "'" +ln+ "'" +","+ "'" +mail+ "'" +","+phone+","+phone2+","+ "'" +notes+ "'" +","+visible+","+ "'" +dte+ "'" +","+img+")";
		int s = d.ExecuteU(query);
		if(s==1)
		{
			System.out.println("Insertion done");
		}
	}
}

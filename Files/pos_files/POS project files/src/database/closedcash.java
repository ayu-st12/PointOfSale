package database;

import database.dbfunc_other;

public class closedcash {
	
	static dbfunc_other d = new dbfunc_other();
	
	public static void insert_closedcash(String id, double money,String host,double host_seq, String time1, String time2) throws Exception
	{
		String query = "insert into closedcash values(" + id +","+ money+","+ "'" +host+"'"  +","+host_seq+","+"'" +time1+"'"  +","+"'" +time2+"'"  +")";
		int s = d.ExecuteU(query);
		if(s==1)
		{
			System.out.println("Insertion done");
		}
	}
	
	public static void delete_closedcash(String ccid) throws Exception
	{
		d.delete_row("closedcash", "id", ccid);
		System.out.println("Closed Cash Deleted");
	}
}

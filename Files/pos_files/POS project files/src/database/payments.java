package database;

import database.dbfunc_other;

public class payments {
	static dbfunc_other d = new dbfunc_other();
	
	public static void insert_payments(String id, String receipts,double total,String notes, String returnmsg, double tendered) throws Exception
	{
		String query = "insert into payments values(" + "'"+ id +  "'" +","+ "'" +receipts+"'" +","+ total +","+"'" +notes+"'" +","+"'" +returnmsg+"'"  +","+tendered +")";
		int s = d.ExecuteU(query);
		if(s==1)
		{
			System.out.println("Payment Insertion done");
		}
	}
	
	public static void delete_payments(String payid) throws Exception
	{
		d.delete_row("payments", "id", payid);
		System.out.println("Closed Cash Deleted");
	}
}

package database; 
//(tested)

import java.security.MessageDigest;

import database.dbfunc_other;

public class tax_func {
	static dbfunc_other d = new dbfunc_other();

	public static void insert_tax(String id, String name,String category,String cust_category,String pid, double rate, Boolean ratecascade) throws Exception
	{
		String query = "insert into taxes values(" + id +","+ "'" +name+ "'"+","+"'"+category+"'"+","+"'"+cust_category+"'"+","+ pid +","+rate+","+ratecascade +")";
		int s = d.ExecuteU(query);
		if(s==1)
		{
			System.out.println("Insertion done");
		}
	}
	
	public static void insert_taxline(String id, String receipt,double base,String taxid, double amount) throws Exception
	{
		String query = "insert into taxline values(" + id +","+ "'" +receipt+ "'"+","+ base +","+taxid+","+amount +")";
		int s = d.ExecuteU(query);
		if(s==1)
		{
			System.out.println("Insertion done");
		}
	}
	
	public static void delete_taxline(String tid) throws Exception
	{
		d.delete_row("taxline", "id", tid);
		System.out.println("Tax Deleted");
	}
	
	public void delete_tax(String tid)
	{
		d.delete_row("taxes", "id", tid);
		System.out.println("Tax Deleted");
	}	

}

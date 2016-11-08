package database;

import java.security.MessageDigest;

import database.dbfunc_other;

public class category_func {
	static dbfunc_other d = new dbfunc_other();
	
	public static void insert_category(String id, String name, String pid, Byte[] img, String taxid,Boolean cat_showname) throws Exception
	{
		String query = "insert into categories values(" + id +","+ "'" +name+ "'"+","+pid+","+img+","+"'"+taxid+"'"+","+cat_showname +")";
		int s = d.ExecuteU(query);
		if(s==1)
		{
			System.out.println("Insertion done");
		}
	}
	
	public void delete_category(String cid)
	{
		d.delete_row("categories", "id", cid);
		System.out.println("Category Deleted");
	}	

}

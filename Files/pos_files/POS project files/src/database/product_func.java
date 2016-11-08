package database;

import java.security.MessageDigest;

import database.dbfunc_other;

public class product_func {
	static dbfunc_other d = new dbfunc_other();
	
	public static void insert_product(String id,String ref, byte[] code, String codetype,String name,double cp,double sp,String cat, String taxcat, double stockcost, double stockvol,Byte[] img, double discount, boolean warranty, double stockunit, boolean isservice) throws Exception
	{
		String query = "insert into product values(" + id +","+ "'" +ref+ "'" +","+code+","+ "'" +codetype+ "'"+","+ "'"+name+ "'"+","+cp +","+sp+","+ "'"+cat+ "'"+","+"'"+taxcat+ "'"+","+ stockcost + ","+ stockvol +","+ img +","+ discount +","+ warranty +","+ stockunit +","+ isservice +")";
		int s = d.ExecuteU(query);
		if(s==1)
		{
			System.out.println("Insertion done");
		}
	}	
	public void delete_product(String pid)
	{
		d.delete_row("product", "id", pid);
		System.out.println("Product Deleted");
	}	

}

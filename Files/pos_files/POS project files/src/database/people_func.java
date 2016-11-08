package database; 
//tested
import database.dbfunc_other;
import java.security.*;

public class people_func {
	static dbfunc_other d = new dbfunc_other();
	public static String g_eid;
	
	public static void insert_people(String id,String eid, String name, String pass,String roles,Boolean visible, Byte[] img) throws Exception
	{
		String pwd = pass;
		byte[] bytesOfMessage = pwd.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] password = md.digest(bytesOfMessage);
		
		String query = "insert into people values(" + id +","+ "'" +eid+ "'"+","+ "'" +name+ "'"+","+ "'" +password+ "'"+","+roles+","+visible +","+ img +")";
		int s = d.ExecuteU(query);
		if(s==1)
		{
			System.out.println("Insertion done");
		}
	}
	
	public void delete_people(String eid)
	{
		d.delete_row("people", "eid", eid);
		System.out.println("Deleted");
	}	
}

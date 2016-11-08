package database;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import database.dbfunc_other;
import database.product_func;

public class login {
	dbfunc_other d = new dbfunc_other();
	
	public void check_cred(String eid,String pass) throws UnsupportedEncodingException, NoSuchAlgorithmException
	{
		String pwd = pass;
		byte[] bytesOfMessage = pwd.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] password = md.digest(bytesOfMessage);		
		
		String pass1 = d.fetch1("pass", "people", "eid", eid);
		if(password.equals(pass1));
		{
			System.out.println("Login done!!");
			people_func.g_eid = eid; 
		}
	}
}

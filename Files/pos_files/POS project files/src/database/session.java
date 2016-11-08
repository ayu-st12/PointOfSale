/*										VARIABLE & FUNCTIONS DOCUMENTATION
 * 
 * 
 * 			Variable-Name		Type								Purpose
 * -------------------------------------------------------------------------------------------------
 * 			c					Connection			for creating connection
 * 			c1					Connection			for getting the return type connection from jdbc
 * 			e					Exception			for handling exceptions
 * 		  	query				String				fire a query
 * 			act					int[DOCUMENTATION]	login/logout integer identifier
 * 			id					String				get role ID
 * 			ob					dbfunc				for closing and opening database
 * 			ob1					dbfunc_other		for handling other database functions
 * 			rs					ResultSet			for getting resultset from JDBC
 * 			d					int					for holding discount value fetched for product
 * 			t1					Used to handle query, which kind of query it is? ExecuteQuery[0]/ExecuteUpdate[1]
 * 
 * 
 * 
 *  		Function-Name		Attributes					Type								Purpose
 * --------------------------------------------------------------------------------------------------------------------
 * 			StartSession		id,act						void			Starts a session for a role		
 * 			logout				id,act						void			Closes a session for a role
 * 			
 */

package database;
import database.people_func;
import java.sql.ResultSet;

import database.dbfunc;
import database.dbfunc_other;

public class session {

	dbfunc_other d = new dbfunc_other();

	public int checksession(String empid)
	{
		String id;
		id = d.fetch1("id", "people", "eid", empid);
		
		int act;
		act = Integer.parseInt(d.fetch1("action", "session", "id",id));
		if(act==0)
		{
			System.out.println("Logged Out");
			return 0;
		}
		if(act==1)
		{
			System.out.println("Logged In");
			return 1;
		}
		return 3;
	}
	
	public void StartSession(String empid)
	{
		String id;
		id = d.fetch1("id", "people", "eid", empid);
		
		int act;
		act = Integer.parseInt(d.fetch1("action", "session", "id",id));
		
		if(act==0)
		{	
			act = d.update_tbl("session", "action", "1", "id", id);
			System.out.println("Logged Out");
		}
		
		if(act==1)
		{
			System.out.println("Already Logged In");
		}
				
	}
	
	public void logout(String empid)
	{
		String id;
		id = d.fetch1("id", "people", "eid", empid);
		
		int act;
		act = Integer.parseInt(d.fetch1("action", "session", "id",id));
		if(act==1)
		{
			act = d.update_tbl("session", "action", "0", "id", id);
			people_func.g_eid = null;
			System.out.println("Logged Out");
			// CLOSE ALL THE FRAMES
		}
		if(act==0)
		{
			System.out.println("Already Logged Out");
		}		
	}
}

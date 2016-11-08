package database;
import database.dbfunc_other;

public class sale {
	
	static dbfunc_other dbo = new dbfunc_other();
	
	public String ticket_id; //fetch and store ticket id from ticket table
	public int ticket_type; //fetch and store ticket type from ticket table
	public String person; //fetch and store from people table
	public String customer; //fetch and store from customer table
	public int status; //fetch and store from from ticket table
	public int line; //fetch and store from ticket line table
	public String product_nm; //fetch and store from ticket line table
	public int units; //fetch and store from ticket line table
	public double prce; //fetch and store from ticket line table
	public String taxid; //fetch and store from ticket line table
	public double taxprice;
	//anything extra

	public sale(String tid,int tpe, String lid, int st, int ln, String pid, int un, String cond, String val){
		this.ticket_id = tid; //generates a random ticket id 
		this.ticket_type = tpe; //default value is 0 for sale
		this.person = dbo.fetch1("name", "people", "id", lid);
		//here name is the name of the person who is billing and 'people' is the name of the table
		//from which the value is fetched. Finally 'id' is the 'login id' or username of the person
	    this.customer = dbo.fetch1("name", "customer", cond, val);
		//this is null because a billing can be done for unknown customer also.
		this.status = st; //this sets the status of the ticket
		this.line = ln; //this sets the counter number
		this.product_nm = dbo.fetch1("name", "product", "id", pid);
		//this sets the product name by fetching from product table with the help of pid
		//retrieved from get_pid function
		this.units = un; //this sets the number of units
		this.prce = Double.parseDouble(dbo.fetch1("price", "product", "id", pid));
		//this sets the product S.price fetched from the product table
		this.taxid = dbo.fetch1("taxcat", "product", "id", pid);
		//this sets the product taxcat to calculate tax
		this.taxprice = cal_tax();
		//this calculate tax with the help of taxid variable
	}
	
	public sale(String tid,int tpe, String lid, int st, int ln){
		this.ticket_id = tid; //generates a random ticket id 
		this.ticket_type = tpe; //default value is 0 for sale
		this.person = lid;
		//here name is the name of the person who is billing and 'people' is the name of the table
		//from which the value is fetched. Finally 'id' is the 'login id' or username of the person
	    this.customer = "Guest";
		//this is null because a billing can be done for unknown customer also.
		this.status = st; //this sets the status of the ticket
		this.line = ln; //this sets the counter number
	}
	
	public String generate_id(){
		
		String ret_id = "testid"; //generate a random id here;
		return ret_id;
	}
	
	public double cal_tax(){
		double tax;
		double t_rate = Double.parseDouble(dbo.fetch1("rate", "taxes", "id", this.taxid));//fetching rate from tax table
		tax = this.prce * t_rate;
		return tax;
	}
}

/*
public void delete_sale(String tic_id){
	//delete sale from delete table
	using tic_id
}

public void new_sale(){
	sale s = new sale(int tpe, String lid, int st, int ln, String pid, int un);
*/

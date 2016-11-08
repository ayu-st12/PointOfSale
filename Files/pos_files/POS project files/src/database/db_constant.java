/*										VARIABLE & FUNCTIONS DOCUMENTATION
 * 
 * 
 * 			Variable-Name		Type						Purpose
 * -------------------------------------------------------------------------------------------------
 * 			db_url				String			link to database connection
 * 			user				String			setting username for database connection
 * 			pass				String			setting password for database connection
 * 			driver_name			String			setting driver name for POSTGRE-SQL
 */

package database;

public interface db_constant {
	 
	   String db_url = "jdbc:postgresql://localhost:5432/proto1";
	   String user = "root";
	   String pass = "root";
	   String driver_name = "org.postgresql.Driver";
}

package export;
import java.sql.*;
import java.util.*;
import javax.servlet.http.Cookie;
/** 
 * @author Pandeeswaran
 */
import database.DatabaseConnection;

public class ReUsable {

    // HashMap change ...
    
	public HashMap<String, String> resultSetToHashMap(ResultSet rs)
			throws SQLException {

		ResultSetMetaData md = rs.getMetaData();
		HashMap<String, String> result = new HashMap<String, String>();
		while (rs.next()) {

			for (int i = 1; i <= md.getColumnCount(); ++i) {
				result.put(md.getColumnName(i) + "", rs.getObject(i) + "");
			}
		}
		return result;
	}

    // ArrayList change ...
    
	public ArrayList<HashMap<String, String>> resultSetToArrayList(ResultSet rs)
			throws SQLException {
        DatabaseConnection data = new DatabaseConnection();
        Connection connection = data.connect();
        ResultSetMetaData md = rs.getMetaData();
		ArrayList<HashMap<String, String>> out = new ArrayList< HashMap<String, String> >();
		while (rs.next()) {
			HashMap<String, String> result = new HashMap<String, String>();
			for (int i = 1; i <= md.getColumnCount(); ++i) {
				
				result.put(md.getColumnName(i) + "", rs.getObject(i) + "");
				if((md.getColumnName(i)+"").equals("sender") || (md.getColumnName(i)+"").equals("class_creater")) {
				    Statement stmt = null;
        			try {
        				stmt = connection.createStatement();
        				String Query = "select name,image from userdetails where user_id = "
        						+ "'" + rs.getObject(i) + "'";
        				System.out.println(Query);
        				ResultSet resultset = stmt.executeQuery(Query);
        				while(resultset.next()){
            				result.put("name" , resultset.getObject(1) + "");
            				System.out.println(resultset.getObject(2) + " : " + resultset.getObject(2).getClass() );
            				result.put("image" , resultset.getObject(2) + "");
        				}
        			}
        			catch (SQLException e) {
        			    System.out.println(e);
        			} 
        			
				}
			}
			out.add(result);
		}
		return out;
	}

    // Cookie setUp 
    
    public String CookieCreate () {
                char[] letters={'q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m'};
				StringBuilder ans = new StringBuilder();
					for(int i = 0 ; i < 25 ; i++){
						ans.append((int)(Math.random()*30)+""+letters[(int)(Math.random()*letters.length)]);
					}
	    return ans.toString();
    }
    
    
    // all user ID 
    
    public ArrayList<String> resultSetToUserID(ResultSet rs)
			throws SQLException {

		ResultSetMetaData md = rs.getMetaData();
		ArrayList<String> result = new ArrayList<String>();
		while (rs.next()) {

			for (int i = 1; i <= md.getColumnCount(); ++i) {
				result.add(rs.getObject(i) + "");
			}
		}
		return result;
	}
}
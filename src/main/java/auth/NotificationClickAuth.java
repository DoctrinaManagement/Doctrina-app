package auth;

import java.io.*;
import javax.servlet.*;

import database.DatabaseConnection;
import export.ReUsable;

import java.util.*; // import javax.servlet.http.HttpServletRequest;
import javax.servlet.*;
import java.sql.*;
/** 
 * @author Pandi
 */
public class NotificationClickAuth implements Filter {

	public void init(FilterConfig arg0) throws ServletException {}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		PrintWriter writer = response.getWriter();
		DatabaseConnection data = new DatabaseConnection();
		Connection connection = data.connect();
		Statement stmt = null;
		try {
		    if ((request.getParameter("user_id")+"") != "" && (request.getParameter("user_id")+"") != null) {
    			stmt = connection.createStatement();
    			String msg = "'" + request.getParameter("message") + "'";
    			String user = "'" + request.getParameter("user_id") + "'";
    			ResultSet rs = stmt
    					.executeQuery("select * from notification where message = "
    							+ msg+"AND user_id="+user+";");
    
    			ReUsable get = new ReUsable();
    			HashMap<String, String> details = get.resultSetToHashMap(rs);
    
    			if (details.size() != 0 && details.get("user_id").equals((request.getParameter("user_id")+"")) && details.get("message").equals((request.getParameter("message")+""))) {
    				chain.doFilter(request, response);
    			} else {
    				writer.write("404");
    			}
		    }
		} catch (SQLException e) {
			
		}
	}

	public void destroy() {
	}

}
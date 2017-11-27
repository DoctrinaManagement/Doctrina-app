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
public class GetClassroomAuth implements Filter {

	public void init(FilterConfig arg0) throws ServletException {}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		PrintWriter writer = response.getWriter();
		DatabaseConnection data = new DatabaseConnection();
		Connection connection = data.connect();
		Statement stmt = null;
		ReUsable get = new ReUsable();
		try {
		   stmt = connection.createStatement();
		   String course_id = "'"+request.getParameter("course_id")+"'";
		   ResultSet rs = stmt
					.executeQuery("select * from courses where course_id = "+course_id+";");
		   HashMap<String, String> details = get.resultSetToHashMap(rs);
		   if(details.size() != 0) {
			   chain.doFilter(request, response);
		   }
		   else {
			   writer.write("404");
		   }
		   
		} catch (SQLException e) {
			
		}
	}

	public void destroy() {}
	
}
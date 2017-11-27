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
public class CreateClassroomAuth implements Filter {

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
		   String user_id = "'"+request.getParameter("user_id")+"'";
		   ResultSet rs = stmt
					.executeQuery("select role from userdetails where user_id ="+user_id+";");
		   HashMap<String, String> details = get.resultSetToHashMap(rs);
		   
		   if(details.get("role").equals("Teacher")) {
			   String course_id = "'"+request.getParameter("course_id")+"'";
			   rs = stmt
						.executeQuery("select * from courses where course_id = "+course_id+";");
			    details = get.resultSetToHashMap(rs);
			    
			   if( details.size() != 0 && request.getParameter("classroom_name") != "" 
					   && request.getParameter("classroom_description") != "" ) {
				   
				   chain.doFilter(request, response);
			   }
			   else {
				   writer.write("404");
			   }
		   }
		   else {	
			   writer.write("You can't access this");
		   }
		   
		} catch (SQLException e) {
			
		}
	}

	public void destroy() {
	}

}
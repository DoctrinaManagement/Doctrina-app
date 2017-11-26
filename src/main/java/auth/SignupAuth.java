package auth;

import java.io.*;
import javax.servlet.*;

import database.DatabaseConnection;
import export.ReUsable;

import java.util.*; // import javax.servlet.http.HttpServletRequest;
import javax.servlet.*;
import java.sql.*;
/** 
 * @author Basheer
 */
public class SignupAuth implements Filter {

	public void init(FilterConfig arg0) throws ServletException {}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		PrintWriter writer = response.getWriter();
		DatabaseConnection data = new DatabaseConnection();
		Connection connection = data.connect();
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			String user = "'" + request.getParameter("user_id") + "'";
			ResultSet rs = stmt
					.executeQuery("select * from userdetails where user_id = "
							+ user);

			ReUsable get = new ReUsable();
			HashMap<String, String> details = get.resultSetToHashMap(rs);

			if (details.size() == 0) {
				if (request.getParameter("role").equals("teacher")
						|| request.getParameter("role").equals("student")) {
						    
					chain.doFilter(request, response);
				} 
				else {
					writer.write("Wrong role");
				}
			} 
			else {
				writer.write("Your Account is already exists in DOCTRINA");
			}
		} 
		catch (SQLException e) {
            System.out.println(e);
		}
	}

	public void destroy() {
	}

}
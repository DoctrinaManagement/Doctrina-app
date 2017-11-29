package notification;

import java.io.*;
import javax.servlet.http.*; // import javax.servlet.ServletException;

import database.DatabaseConnection;
import export.ReUsable;

import java.util.*; // import javax.servlet.http.HttpServletRequest;
import javax.servlet.*;
import java.sql.*;
/** 
 * @author Basheer
 */
public class NotificationSettings extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		DatabaseConnection data = new DatabaseConnection();
		Connection connection = data.connect();
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			String user = "'"+request.getParameter("user_id")+"'";
			String Query = "delete * from settings where user_id ="+user+";";
			stmt.executeUpdate(Query);
			String[] course_id = (request.getParameterValues("course_id")+"").split(",");
			
			for(String course_idValues : course_id) {
				Query = "insert into settings values ("+user+",'"+course_idValues+"');";
			}
			writer.write("save successfully");
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}
}

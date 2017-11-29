package classroom;

import java.io.*;
import javax.servlet.http.*; // import javax.servlet.ServletException;

import database.DatabaseConnection;
import export.ReUsable;

import java.util.*; // import javax.servlet.http.HttpServletRequest;
import javax.servlet.*;
import java.sql.*;

public class CreateClassroom extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		DatabaseConnection data = new DatabaseConnection();
		Connection connection = data.connect();
		Statement stmt = null;
		HttpSession session = request.getSession(); 
		String userId = "";
		try {
			stmt = connection.createStatement();
			//Getting values to QUERY ...
			String user = "'" + request.getParameter("user_id") + "'";
			String classroom_name = "'" + request.getParameter("classroom_name") + "'";
			String classroom_description = "'" + request.getParameter("classroom_description") + "'";
			String course = "'" + request.getParameter("course_id") + "'";
			// Add values to DATABASE ... 
		    stmt.executeUpdate("insert into classroom(classroom_name, classroom_description, course, class_creater) values("+
		    			classroom_name +","+	classroom_description + "," + course + "," + user +");");
		    
		    
		    
		    // insert notification
		    String message = "'A new classroom <b>"+request.getParameter("classroom_name")+"</b> has been created.'";
		    ResultSet rs = stmt.executeQuery("select user_id from settings where course_id ="+course+";");
		    ReUsable get = new ReUsable();
		    ArrayList<String> user_id = get.resultSetToUserID(rs);
		    for(String id : user_id) {
		    	id = "'"+id+"'";
		    	stmt.executeUpdate("insert into notification values("+id+","+message+",'true',"+user+");");
		    }
		    writer.write("classroom has been created.");
		} 
		catch (SQLException e) 
		{
			System.out.println("CreateClassroom - " + e.getStackTrace());
		}
	}
}
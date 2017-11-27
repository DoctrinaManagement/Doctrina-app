package classroom;

import java.io.*;
import javax.servlet.http.*; // import javax.servlet.ServletException;

import com.google.gson.Gson;

import database.DatabaseConnection;
import export.ReUsable;

import java.util.*; // import javax.servlet.http.HttpServletRequest;
import javax.servlet.*;
import java.sql.*;

/** 
 * @author Pandi
 */
public class GetClassroom extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		DatabaseConnection data = new DatabaseConnection();
		Connection connection = data.connect();
		Statement stmt = null;
		ReUsable get = new ReUsable();
			
		try {
			String course_id = "'"+request.getParameter("course_id")+"'";
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from classroom where course ="+course_id+";");
			ArrayList<HashMap<String, String>> classroom = get.resultSetToArrayList(rs);
			String json = new Gson().toJson(classroom);
			writer.write(json);

		} catch (SQLException e) {
			System.out.println("GetClassroom -"+e.getStackTrace());
		}

	}
}
package sign;
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
public class Signup extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();
		DatabaseConnection data = new DatabaseConnection();
		Connection connection = data.connect();
		Statement stmt = null;
		HttpSession session = request.getSession();
	    ReUsable get = new ReUsable();
		String userId = "";
		try {
			stmt = connection.createStatement();
			userId = "'" + request.getParameter("user_id") + "'";
			String name = "'" + request.getParameter("name") + "'";
			String emailId = "'" + request.getParameter("email_id") + "'";
			String image = "'" + request.getParameter("image") + "'";
			String role = "'" + request.getParameter("role") + "'";
			String notification = "'"
					+ "Welcome to Doctrina. Learn Anything easy and better ..."
					+ "'";


			stmt.executeUpdate("insert into userdetails values(" + userId + ","
					+ name + "," + emailId + "," + image + "," + role + ");");
			ResultSet rs = stmt.executeQuery("select * from userdetails where user_id = "
					+ userId);
			HashMap<String, String> details = get.resultSetToHashMap(rs);
			String status = "'"+"true"+"'";
			String sender = "'"+"1"+"'";
			String Query = "insert into notification values(" + userId+ "," + notification + "," + status +","+sender +");";
			stmt.executeUpdate(Query);

			// Session setUP ...
			for (String values : details.keySet()) {
				session.setAttribute(values, details.get(values));
			}
			
			// cookie create ...
//			String cookie = get.CookieCreate();
//			Cookie cookies=new Cookie("Name", cookie);//creating cookie object  
//            response.addCookie(cookies);//adding cookie in the response  
//            
//            cookie = "'"+cookie+"'";
//            String user_id = "'"+details.get("user_id")+"'";
//            
//            try {
//                stmt.executeUpdate("insert into cookie values("+cookie+","+user_id+");");
//            }
//            catch (SQLException e) {
//                System.out.println(e);
//            }
			writer.write("ok");
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
	}
}

package sign;

import java.io.*;
import javax.servlet.http.*; // import javax.servlet.ServletException;

import database.DatabaseConnection;
import export.ReUsable;

import java.util.*; // import javax.servlet.http.HttpServletRequest;
import javax.servlet.*;
import java.sql.*;

/** 
 * @author Pandi
 */
public class Signin extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		DatabaseConnection data = new DatabaseConnection();
		Connection connection = data.connect();
		Statement stmt = null;
		HttpSession session = request.getSession();
		ReUsable get = new ReUsable();
			
		try {
			System.out.println(request.getParameter("user_id"));
			stmt = connection.createStatement();
			String user = "'" + request.getParameter("user_id") + "'";
			ResultSet rs = stmt
					.executeQuery("select * from userdetails where user_id = "
							+ user);

			HashMap<String, String> details = get.resultSetToHashMap(rs);
			if(details.get("image").equals(request.getParameter("image")+"") == false || details.get("name").equals(request.getParameter("name")+"") == false) {
				String userId = "'" + request.getParameter("user_id") + "'";
				String name = "'" + request.getParameter("name") + "'";
				String emailId = "'" + request.getParameter("email_id") + "'";
				String image = "'" + request.getParameter("image") + "'";
				stmt.executeUpdate("update userdetails set name="+name+",email_id="+emailId+",image="+image+";");
				rs = stmt
						.executeQuery("select * from userdetails where user_id = "
								+ user);
				details = get.resultSetToHashMap(rs);
			}

			// Session setup

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
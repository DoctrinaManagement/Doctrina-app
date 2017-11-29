package notification;

import java.io.*;
import java.util.*;
import javax.websocket.*;
import javax.websocket.server.*;
import java.sql.*;
import com.google.gson.Gson;
import javax.websocket.server.ServerEndpoint;

import database.DatabaseConnection;
import export.ReUsable;
//@ApplicationScoped
@ServerEndpoint("/websocket/{id}")
public class Notification {
	private static HashMap<String, Session> clientId = new HashMap<String, Session>();

	@OnOpen
	public void onOpen(@PathParam("id") String id, Session session) {
		System.out.println(session.getId() + " has opened a connection");
		clientId.put(id, session);
		System.out.println(clientId);
	}

	@OnMessage
	public void onMessage(String id, Session session) throws IOException {
	   DatabaseConnection data = new DatabaseConnection();
	   Connection connection = data.connect();
	   Statement stmt = null;
	   ReUsable get = new ReUsable();
	   
	   if(id.equals("all")) {
		   for(String users : clientId.keySet()) {
	            try {
	   	            try{
	   	                stmt = connection.createStatement();
	       				String Query = "select * from notification where user_id = "
	       						+ "'" + users + "'";
	
	       				ResultSet rs = stmt.executeQuery(Query);
	       				HashMap<String, ArrayList<HashMap<String, String>>> jsonObj = new HashMap<String, ArrayList<HashMap<String, String>>>();
	       				jsonObj.put("notifications", get.resultSetToArrayList(rs));
	       				String json = new Gson().toJson(jsonObj);
	       				clientId.get(users).getBasicRemote().sendText(json);
	   	            }
	   	            catch (SQLException e) {
	       				System.out.println(e);
	       			}
	            } catch (IOException ex) {
       			ex.printStackTrace();
       		}
   	            
	        }
	   }
	   else {
		   try {
			   	stmt = connection.createStatement();
				String Query = "select * from notification where user_id = "
						+ "'" + id + "'";
				System.out.println(Query);
				ResultSet rs = stmt.executeQuery(Query);
				HashMap<String, ArrayList<HashMap<String, String>>> jsonObj = new HashMap<String, ArrayList<HashMap<String, String>>>();
				jsonObj.put("notifications", get.resultSetToArrayList(rs));
				String json = new Gson().toJson(jsonObj);
				clientId.get(id).getBasicRemote().sendText(json);
			   
		   } catch (SQLException e) {
			   System.out.println(e.getMessage());
		   } catch (IOException e) {
			   System.out.println(e.getMessage());
		   }
	   }
	}

	@OnClose
	public void onClose(Session session, @PathParam("id") String id) {
		clientId.remove(id);
	}
}
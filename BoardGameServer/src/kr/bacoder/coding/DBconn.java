package kr.bacoder.coding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DBconn {
	private String userName 	= "root";
	private String password 	= "789gagul";
	private String dbms 		= "mysql";
	private String dbName 		= "new_schema";
	private String serverName 	= "35.194.236.5";
	private int portNumber 		= 3306;
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", this.userName);
	    connectionProps.put("password", this.password);

	    if (this.dbms.equals("mysql")) {
	        conn = DriverManager.getConnection(
	                   "jdbc:" + this.dbms + "://" +
	                   this.serverName +
	                   ":" + this.portNumber + "/" +
	                   this.dbName + "?" +
	                   "useSSL=false",
	                   connectionProps);
	    } else if (this.dbms.equals("derby")) {
	        conn = DriverManager.getConnection(
	                   "jdbc:" + this.dbms + ":" +
	                   this.dbName +
	                   ";create=true",
	                   connectionProps);
	    }
	    System.out.println("Connected to database");
	    return conn;
	}
	
	public String getData() throws SQLException, ClassNotFoundException{
		JSONObject resultObj = new JSONObject();
		Connection conn;
		conn = getConnection();
		
		Statement stmt = conn.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM Map");
		
		JSONArray array = new JSONArray();
		while(rs.next()){
			int id = rs.getInt("idMap");
			String name = rs.getString("name");
//			System.out.println("id::::"+id +"::::name::::"+name);
			JSONObject item = new JSONObject();
			item.put("id", id);
			item.put("name", name);
			array.add(item);
		}
		resultObj.put("list", array);
		
		return resultObj.toJSONString();
	}
}

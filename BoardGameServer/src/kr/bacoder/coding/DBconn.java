package kr.bacoder.coding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.bacoder.coding.bean.Person;

public class DBconn {
	Logger logger = Logger.getLogger(DBconn.class.getSimpleName());
	
	private String userName 	= "dev";
	private String password 	= "789gagul";
	private String dbms 		= "mysql";
	private String dbName 		= "new_schema";
	private String serverName 	= "35.234.23.104";
	private int portNumber 		= 3306;
	
	public Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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
	    }
	    return conn;
	}
	public int insertPerson(Person person) throws SQLException {
		Connection conn;
		conn = getConnection();
		String sql = "INSERT INTO Person "
				+ "(name, address, email, phone, password, uniqueId) "
				+ "VALUES (?,?,?,?,?,?)";
		logger.info(sql);
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, person.getName());
		pstmt.setString(2, person.getAddress());
		pstmt.setString(3, person.getEmail());
		pstmt.setString(4, person.getPhone());
		pstmt.setString(5, person.getPassword());
		pstmt.setString(6, person.getUniqueId());
		return pstmt.executeUpdate();
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
			int xLoc = rs.getInt("x_loc");
			int yLoc = rs.getInt("y_loc");
			int price = rs.getInt("price");
			
			JSONObject item = new JSONObject();
			item.put("id", id);
			item.put("name", name);
			item.put("xLoc", xLoc);
			item.put("yLoc", yLoc);
			item.put("price", price);
			array.add(item);
		}
		resultObj.put("list", array);
		
		return resultObj.toJSONString();
	}
}

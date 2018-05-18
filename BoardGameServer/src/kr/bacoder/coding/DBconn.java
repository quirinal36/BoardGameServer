package kr.bacoder.coding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.bacoder.coding.bean.AndroidVersionInfo;
import kr.bacoder.coding.bean.Person;

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
	public String getData(String tableName) throws SQLException, ClassNotFoundException{
		JSONObject resultObj = new JSONObject();
		Connection conn;
		conn = getConnection();
		
		Statement stmt = conn.createStatement();
		ResultSet rs;
		rs = stmt.executeQuery("SELECT * FROM " + tableName);
		
		JSONArray array = new JSONArray();
		if(tableName.equals("Person")){
			while(rs.next()){
				array.add(getPerson(rs));
			}
		}else if(tableName.equals("Android_Version")){
			while(rs.next()){
				array.add(getAndroidVer(rs));
			}
		}
		resultObj.put("list", array);
		
		return resultObj.toJSONString();
	}
	private JSONObject getAndroidVer(ResultSet rs) throws SQLException{
		JSONObject item = new JSONObject();
		item.put(AndroidVersionInfo.alphabet, rs.getString(AndroidVersionInfo.alphabet));
		item.put(AndroidVersionInfo.version_name, rs.getString(AndroidVersionInfo.version_name));
		item.put(AndroidVersionInfo.version_name_kor, rs.getString(AndroidVersionInfo.version_name_kor));
		item.put(AndroidVersionInfo.version_num, rs.getDouble(AndroidVersionInfo.version_num));
		item.put(AndroidVersionInfo.year_key, rs.getInt(AndroidVersionInfo.year_key));
		return item;
	}
	private JSONObject getPerson(ResultSet rs) throws SQLException{
		JSONObject item = new JSONObject();
		item.put(Person.NAME_KEY, rs.getString(Person.NAME_KEY));
		item.put(Person.AGE_KEY, rs.getInt(Person.AGE_KEY));
		item.put(Person.SEX_KEY, rs.getString(Person.SEX_KEY));
		item.put(Person.ADDRESS_KEY, rs.getString(Person.ADDRESS_KEY));
		item.put(Person.SKILL_KEY, rs.getString(Person.SKILL_KEY));
		item.put(Person.FAMILY_KEY, rs.getInt(Person.FAMILY_KEY));
		item.put(Person.COMPANY_KEY, rs.getString(Person.COMPANY_KEY));
		return item;
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

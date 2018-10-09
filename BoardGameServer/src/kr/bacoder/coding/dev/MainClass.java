package kr.bacoder.coding.dev;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.Person;

public class MainClass {
	static Logger logger = Logger.getLogger(MainClass.class.getSimpleName());
	
	public static void main(String[] args) throws SQLException {
		DBconn dbConn = new DBconn();
		Connection conn = dbConn.getConnection();
		if(conn != null){
			Statement stmt = conn.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery("SELECT * FROM Person");
			
			while(rs.next()){
				Person person = new Person();
				person.setAge(rs.getInt("age"));
				person.setAddress(rs.getString("address"));
				person.setCompany(rs.getString("Company"));
				person.setName(rs.getString("name"));
				person.setSex(rs.getString("Sex"));
				person.setFamily(rs.getInt("family"));
				logger.info(person.toString());
			}
		}
	}
}
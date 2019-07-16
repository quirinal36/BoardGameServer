package kr.bacoder.coding.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.json.simple.JSONObject;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.SooMacro;

public class SooMacroControl {
	Logger logger = Logger.getLogger(getClass().getSimpleName());

	public String getLatestVerFileName(SooMacro macro) throws SQLException {
		String filename = "0";
		int verNumber = 0;
		try(Connection conn = new DBconn().getConnection()){
			PreparedStatement pstmt = conn.prepareStatement("SELECT filename, version_number FROM soo_macro_version WHERE version_number = (SELECT MAX(version_number) FROM soo_macro_version)");
			
		//	logger.info("SELECT * FROM Person WHERE phone =? and uniqueId = ?");
//			logger.info("phone: " + phone);
//			logger.info("deviceId: " + deviceId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				verNumber = rs.getInt("version_number");
//				logger.info("latestVerNumber: "+verNumber);
//				logger.info("prviousVer :"+macro.getPreviousVer());

				if(macro.getPreviousVer() != verNumber) {  //최신버전이 아니면 filename출력 
					filename = rs.getString("filename");				
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return verNumber+","+filename;
	}
}

package kr.bacoder.coding.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.json.simple.JSONObject;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.Photo;
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
					macro.setFileName(filename);
					macro.setDownloadVer(verNumber);
					addMacroLog(macro);
					increaseDownloadCount(macro);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return verNumber+","+filename;
	}
	
	public int addMacroLog(SooMacro macro) {
		int result = 0;
		try(Connection conn = new DBconn().getConnection()){
			String sql = "INSERT INTO soo_macro_logs "
					+ "(ip, downloader, filename, downloadVer, previousVer) "
					+ "VALUES (?,?,?,?,?)";
						
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, macro.getIpAddress());
			pstmt.setString(2, macro.getDownloader());
			pstmt.setString(3, macro.getFileName());
			pstmt.setInt(4, macro.getDownloadVer());
			pstmt.setInt(5, macro.getPreviousVer());
			result= pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int increaseDownloadCount(SooMacro macro) {
		int result = 0;
		try(Connection conn =  new DBconn().getConnection()){
			StringBuilder sql = new StringBuilder();
			
			sql.append("UPDATE soo_macro_version SET downloadCount = downloadCount + 1 WHERE version_number = ?");
			
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, macro.getDownloadVer());
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
//			setErrorMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}

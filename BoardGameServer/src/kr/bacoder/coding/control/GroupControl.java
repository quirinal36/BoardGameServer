package kr.bacoder.coding.control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.bacoder.coding.DBconn;
import kr.bacoder.coding.bean.Group;

public class GroupControl {

	public List<Group> getGroupListAll(int userId, int userLevel){
		DBconn db = new DBconn();
		List<Group> list = new ArrayList<>();
		try (Connection conn = db.getConnection()){
			final String sql = new StringBuilder()
					.append("SELECT + " +
							"			groupList.id as id," + 
							"			groupList.groupType as groupType," + 
							"			groupList.parentGroupId as parentGroupId," + 
							"			(SELECT sublist.groupName From Group_list sublist WHERE sublist.id = groupList.parentGroupId ) AS parentGroupName," + 
							"			(SELECT sublist.groupIconUrl From Group_list sublist WHERE sublist.id = groupList.parentGroupId ) AS parentGroupIconUrl," + 
							"			groupList.groupName as groupName," + 
							"			groupList.groupText as groupText," + 
							"			groupList.groupIconUrl as groupIconUrl," + 
							"			groupList.groupDetailPhotoUrl1 as groupDetailPhotoUrl1," + 
							"			groupList.groupDetailPhotoUrl2 as groupDetailPhotoUrl2," + 
							"			groupList.groupDetailPhotoUrl3 as groupDetailPhotoUrl3," + 
							"			groupList.adminUserId as adminUserId," + 
							"			groupList.presidentUserId as presidentUserId," + 
							"			groupList.secret as secret," + 
							"			groupList.createdTime as createdTime," + 
							"			groupList.defaultUserLevel as defaultUserLevel," + 
							"			groupList.accessLevel," + 
							"			(SELECT count(*) FROM Group_user user WHERE (user.id = groupList.id AND user.userId = ?)) AS joinCount" + 
							"		FROM Group_list groupList" + 
							"		WHERE" + 
							"			groupList.secret = '0'" + 
							"			AND" + 
							"			? >= groupList.accessLevel").toString();
//			System.out.print(sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, userLevel);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Group group = new Group();
				group.setId(rs.getInt("id"));
				group.setGroupType(rs.getInt("groupType"));
				group.setParentGroupId(rs.getInt("parentGroupId"));
				group.setParentGroupName(rs.getString("parentGroupName"));
				group.setParentGroupIconUrl(rs.getString("parentGroupIconUrl"));
				group.setGroupName(rs.getString("groupName"));				
				group.setGroupText(rs.getString("groupText"));
				group.setGroupIconUrl(rs.getString("groupIconUrl"));
				group.setGroupDetailPhotoUrl1(rs.getString("groupDetailPhotoUrl1"));
				group.setGroupDetailPhotoUrl2(rs.getString("groupDetailPhotoUrl2"));
				group.setGroupDetailPhotoUrl3(rs.getString("groupDetailPhotoUrl3"));
				group.setAdminUserId(rs.getInt("adminUserId"));
				group.setPresidentUserId(rs.getInt("presidentUserId"));
				group.setSecret(rs.getInt("secret"));
				group.setCreatedTime(rs.getDate("createdTime"));
				group.setDefaultUserLevel(rs.getInt("defaultUserLevel"));
				group.setAccessLevel(rs.getInt("accessLevel"));
				group.setJoinCount(rs.getInt("joinCount"));
				
				list.add(group);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Group> getGroupListMy(int userId, int userLevel){
		DBconn db = new DBconn();
		List<Group> list = new ArrayList<>();
		try (Connection conn = db.getConnection()){
			final String sql = new StringBuilder()
					.append("SELECT + " +
							"			groupList.id as id," + 
							"			groupList.groupType as groupType," + 
							"			groupList.parentGroupId as parentGroupId," + 
							"			(SELECT sublist.groupName From Group_list sublist WHERE sublist.id = groupList.parentGroupId ) AS parentGroupName," + 
							"			(SELECT sublist.groupIconUrl From Group_list sublist WHERE sublist.id = groupList.parentGroupId ) AS parentGroupIconUrl," + 
							"			groupList.groupName as groupName," + 
							"			groupList.groupText as groupText," + 
							"			groupList.groupIconUrl as groupIconUrl," + 
							"			groupList.groupDetailPhotoUrl1 as groupDetailPhotoUrl1," + 
							"			groupList.groupDetailPhotoUrl2 as groupDetailPhotoUrl2," + 
							"			groupList.groupDetailPhotoUrl3 as groupDetailPhotoUrl3," + 
							"			groupList.adminUserId as adminUserId," + 
							"			groupList.presidentUserId as presidentUserId," + 
							"			groupList.secret as secret," + 
							"			groupList.createdTime as createdTime," + 
							"			groupList.defaultUserLevel as defaultUserLevel," + 
							"			groupList.accessLevel," + 
							"			(SELECT count(*) FROM Group_user user WHERE (user.id = groupList.id AND user.userId = ?)) AS joinCount" + 
							"		FROM Group_list groupList" + 
							"       LEFT JOIN Group_user user ON groupList.id = user.groupId" +
                            "       WHERE user.userId = ?" +
                            "       GROUP BY groupList.id").toString();
//			System.out.print(sql);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, userId);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Group group = new Group();
				group.setId(rs.getInt("id"));
				group.setGroupType(rs.getInt("groupType"));
				group.setParentGroupId(rs.getInt("parentGroupId"));
				group.setParentGroupName(rs.getString("parentGroupName"));
				group.setParentGroupIconUrl(rs.getString("parentGroupIconUrl"));
				group.setGroupName(rs.getString("groupName"));				
				group.setGroupText(rs.getString("groupText"));
				group.setGroupIconUrl(rs.getString("groupIconUrl"));
				group.setGroupDetailPhotoUrl1(rs.getString("groupDetailPhotoUrl1"));
				group.setGroupDetailPhotoUrl2(rs.getString("groupDetailPhotoUrl2"));
				group.setGroupDetailPhotoUrl3(rs.getString("groupDetailPhotoUrl3"));
				group.setAdminUserId(rs.getInt("adminUserId"));
				group.setPresidentUserId(rs.getInt("presidentUserId"));
				group.setSecret(rs.getInt("secret"));
				group.setCreatedTime(rs.getDate("createdTime"));
				group.setDefaultUserLevel(rs.getInt("defaultUserLevel"));
				group.setAccessLevel(rs.getInt("accessLevel"));
				group.setJoinCount(rs.getInt("joinCount"));
				
				list.add(group);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}

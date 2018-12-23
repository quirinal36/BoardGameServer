package kr.bacoder.coding.control;

public class Controller {
	
	protected boolean hasString(String input) {
		if(input!=null && input.length()>0) {
			return true;
		}else {
			return false;
		}
	}
	
	protected void appendSql(StringBuilder sql, String key) {
		sql.append(key).append("=?,");
	}
}

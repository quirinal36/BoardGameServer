package kr.bacoder.coding.bean;

public class SearchInfo {
	int day;

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	public void setDay(String day) {
		try {
			this.day = Integer.parseInt(day);
		}catch(NumberFormatException e) {
			this.day = 1;
		}
	}
	
}

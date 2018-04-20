package kr.bacoder.coding.bean;
/**
 * 클래스 Family
 * @author leehg
 *
 */
public class Family {
	int num;
	String father;
	String mother;
	
	/**
	 * 생성자
	 */
	public Family() {
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getMother() {
		return mother;
	}
	public void setMother(String mother) {
		this.mother = mother;
	}
}

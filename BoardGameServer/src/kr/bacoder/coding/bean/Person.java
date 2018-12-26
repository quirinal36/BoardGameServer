package kr.bacoder.coding.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 클래스 Person
 * @author leehg
 *
 */
public class Person {
	public static final String NAME_KEY = "name";
	public static final String AGE_KEY = "age";
	public static final String SEX_KEY = "sex";
	public static final String ADDRESS_KEY = "address";
	public static final String FAMILY_KEY = "family";
	public static final String COMPANY_KEY = "company";
	public static final String BIRTH_KEY = "birth";
	public static final String PHONE_KEY = "phone";
	public static final String EMAIL_KEY = "email";
	public static final String PHOTO_KEY = "photo";
	public static final String UNIQUE_ID_KEY = "uniqueId";
	public static final String DEPARTMENT_KEY = "department";
	public static final String USER_LEVEL_KEY = "userLevel";
	public static final String NUM_KEY = "NUM";
	
	private int id;
	private String name;
	private int age;
	private String sex;
	private String address;
	private int family;
	private String company;
	private String email;
	private String phone;
	private String password;
	private String uniqueId;
	private String photo;
	private String department;
	private String birth;
	private int userLevel;
	
	/**
	 * 생성자
	 */
	public Person() {
		this.userLevel = 0;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setId(String id) {
		try {
			this.id = Integer.parseInt(id);
		}catch(NumberFormatException e) {
			
		}
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getFamily() {
		return family;
	}
	public void setFamily(int family) {
		this.family = family;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	
	public void setUserLevel(String userLevel) {
		try {
			this.userLevel = Integer.parseInt(userLevel);
		}catch(NumberFormatException e) {
			this.userLevel = 0;
		}
	}
	
	public int getUserLevel() {
		return userLevel;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}

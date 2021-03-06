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
	public static final String PHOTO_ID_KEY = "photoId";
	public static final String UNIQUE_ID_KEY = "uniqueId";
	public static final String PASSWORD_KEY = "password";
	public static final String DEPARTMENT_KEY = "department";
	public static final String USER_LEVEL_KEY = "userLevel";
	public static final String NUM_KEY = "NUM";
	public static final String R_TOKEN_KEY = "rToken";
	public static final String A_TOKEN_KEY = "aToken";
	public static final String UUID_KEY = "uuid";
	public static final String FROM_KEY = "from";
	public static final String PROFILE_URL_KEY = "profileUrl";
	public static final String COMPANY_ID_KEY = "company_id";
	public static final String DEPARTMENT_ID_KEY = "department_id";
	public static final String POSITION_KEY = "position";


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
	private int photoId;
	private String department;
	private String birth;
	private int userLevel;
	private String rToken;
	private String uuid;
	private String from;
	private String profileUrl;
	private String aToken;
	private int companyId;
	private int departmentId;
	private String position;
	
	
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
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
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
	
	public String getrToken() {
		return rToken;
	}

	public void setrToken(String rToken) {
		this.rToken = rToken;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getProfileUrl() {
		return profileUrl;
	}


	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}


	public String getaToken() {
		return aToken;
	}


	public void setaToken(String aToken) {
		this.aToken = aToken;
	}


	public int getCompanyId() {
		return companyId;
	}


	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}


	public int getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}

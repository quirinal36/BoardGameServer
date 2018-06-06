package kr.bacoder.coding.bean;

public class Picnic {
	int id;
	String name;
	String date;
	String location;
	
	public Picnic() {
		super();
	}
	public Picnic(int id) {
		super();
		this.id = id;
	}
	public Picnic(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Picnic(int id, String name, String date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
	}
	public Picnic(int id, String name, String date, String location) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.location = location;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Picnic [id=" + id + ", name=" + name + ", date=" + date + ", location=" + location + "]";
	}
}

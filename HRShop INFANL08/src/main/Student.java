package main;

public class Student {

	private String name, id, password, klas, ingeschreven;
	
	public Student(String id, String name, String password, String klas, String ingeschreven) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.klas = klas;
		this.ingeschreven = ingeschreven;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKlas() {
		return klas;
	}

	public void setKlas(String klas) {
		this.klas = klas;
	}

	public String getIngeschreven() {
		return ingeschreven;
	}

	public void setIngeschreven(String ingeschreven) {
		this.ingeschreven = ingeschreven;
	}
}

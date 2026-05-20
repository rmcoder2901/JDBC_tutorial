package jdbc.com.loginPage;

public class User {

	private int id;
	private String pwd;
	private String name;
	private long ph;
	private String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPh() {
		return ph;
	}

	public void setPh(long ph) {
		this.ph = ph;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User(int id, String pwd, String name, long ph, String role) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.ph = ph;
		this.role = role;
	}

}

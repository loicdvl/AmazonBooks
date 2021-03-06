package model;

public class User {
	
	private int id;
	private String login;
	private String password;
	private String name;
	
	public User(int id, String login, String password, String name) {
		
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
        if (obj==this) {
            return true;
        }
        if (obj instanceof User) {
        	User other = (User) obj;
            if (id != other.getId()) { return false; }
            if (!login.equals(other.getLogin())) { return false; }
            if (!password.equals(other.getPassword())) { return false; }
            if (!name.equals(other.getName())) { return false; }
            return true;
        }
        return false;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", name=" 
				+ name + "]";
	}

	
}

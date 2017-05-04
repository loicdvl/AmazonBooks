
public class User {
	
	private int id;
	private String login;
	private String password;
	private String name;
	private Command basket;
	
	public User(int id, String login, String password, String name,Command b) {
		
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
		this.basket = b;
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
	public Command getBasket() {
		return basket;
	}

	public void setBasket(Command basket) {
		this.basket = basket;
	}

	
}

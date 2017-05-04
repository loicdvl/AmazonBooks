import java.util.ArrayList;
import java.util.Map;

public class Command {
	
	private User user;
	private Map<Book,Integer> books;
	
	public Command(User user, Map<Book, Integer> books) {
		this.user = user;
		this.books = books;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Map<Book, Integer> getBooks() {
		return books;
	}
	public void setBooks(Map<Book, Integer> books) {
		this.books = books;
	}
	
	
}

package model;

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
	
	@Override
	public boolean equals(Object obj) {
        if (obj==this) {
            return true;
        }
        if (obj instanceof Command) {
        	Command other = (Command) obj;
        	
            if (!user.equals(other.getUser())) { return false; }
            if (!books.equals(other.getBooks())) { return false; }
            return true;
        }
        return false;
    }

	@Override
	public String toString() {
		return "Command [user=" + user + ", books=" + books + "]";
	}
	
	
}

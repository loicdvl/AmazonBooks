import java.util.Map;

public class HistoricalCommand {	
	private int idHistorical;
	private Map<Book,Integer> books;
	private int idUser;
	
	public HistoricalCommand(int idHistorical, Map<Book, Integer> books, int idUser) {
		this.idHistorical = idHistorical;
		this.books = books;
		this.idUser = idUser;
	}
	
	public int getIdHistorical() {
		return idHistorical;
	}
	public void setIdHistorical(int idHistorical) {
		this.idHistorical = idHistorical;
	}
	public Map<Book, Integer> getBooks() {
		return books;
	}
	public void setBooks(Map<Book, Integer> books) {
		this.books = books;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
}

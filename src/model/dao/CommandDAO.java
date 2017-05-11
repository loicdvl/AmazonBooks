package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import configuration.DBUtil;
import model.Book;
import model.Command;
import model.User;

public class CommandDAO {
	
	public Connection connection;
	private Statement statement;

	public CommandDAO() {
		connection = DBUtil.getConnection();
	}

	public Command findById(int iduser) {
		
		String query = "SELECT * FROM command c, useraccount u, book b WHERE c.iduser = u.iduser "
						+ "AND b.idbook=c.idbook AND"+ " c.iduser = " + iduser + ";";
		ResultSet rs = null;
		User user = null;
		Map<Book,Integer> lesLivres = new HashMap<Book,Integer>();
		Book book = null;
		
		try {

			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			
			while (rs.next()) {
				user = new User(rs.getInt("iduser"), rs.getString("login"), 
								rs.getString("password"), rs.getString("name"));
				
				book = new Book(rs.getInt("idbook"), rs.getString("title"), 
						 rs.getString("author"), rs.getFloat("price"),
						 rs.getString("image"), rs.getString("description"));
				
				lesLivres.put(book, rs.getInt("qte"));
				
			}

		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		} finally {
			DBUtil.close(rs);
			DBUtil.close(statement);
		}
		
		Command cmd = new Command(user,lesLivres);
		
		return cmd;
	}

	public void create(Command obj) {
		
		try {

			for(Map.Entry<Book,Integer> book : obj.getBooks().entrySet()){
				
				statement = connection.createStatement();
        		
				String query = "INSERT INTO command VALUES (" + obj.getUser().getId() + "," 
						   + book.getKey().getId() + "," + book.getValue() + ");";
        		
        		statement.executeUpdate(query);
			}	
		}
		catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}
		finally {
			DBUtil.close(statement);
		}
		
	}
	
	public void createEntry(int idBook, int idUser, int quantity) {
		
		try {

				
			statement = connection.createStatement();
    		
			String query = "INSERT INTO command VALUES (" + idUser + "," 
					   + idBook + "," + quantity + ");";
    		
    		statement.executeUpdate(query);
		}
		catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}
		finally {
			DBUtil.close(statement);
		}
		
	}

	public void update(Command obj) {
				
		try {
			
			Command cmdDatabase = findById(obj.getUser().getId());
			
			for(Map.Entry<Book,Integer> newCmdBooks : obj.getBooks().entrySet()){

				for(Map.Entry<Book,Integer> oldCmdBooks : cmdDatabase.getBooks().entrySet()){

		            if(newCmdBooks.getKey().equals(oldCmdBooks.getKey())){
		            	
		            	if(newCmdBooks.getValue() != oldCmdBooks.getValue()){
		            		
		        			statement = connection.createStatement();

		            		String query = "UPDATE command SET qte = "+ newCmdBooks.getValue() 
		            					   + " WHERE idbook = " + newCmdBooks.getKey().getId() 
		            					   + " AND iduser = " + obj.getUser().getId() + ";" ;
		            		
		            		statement.executeUpdate(query);
			                
			            }		                
		            }
		        }
	        }		
		}
		catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}
		finally {
			DBUtil.close(statement);
		}
	}
	
	public void updateEntry(int idBook, int idUser, int quantity) {
		
		try {
		            		
			statement = connection.createStatement();

    		String query = "UPDATE command SET qte = "+ quantity 
    					   + " WHERE idbook = " + idBook
    					   + " AND iduser = " + idUser + ";" ;
    		
    		statement.executeUpdate(query);
			                	
		}
		catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}
		finally {
			DBUtil.close(statement);
		}
	}
	
	public void deleteCommandsContainingUser(int idUser) {
		
		String query = "DELETE FROM command WHERE iduser = " + idUser + ";" ;

		try {
		    statement = connection.createStatement();
		    statement.executeUpdate(query);
		}
		catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}
		finally {
		    DBUtil.close(statement);
		}
	}
	
	public void deleteCommandsContainingBook(Book  obj) {
		
		String query = "DELETE FROM command WHERE idbook = " + obj.getId() + ";" ;

		try {
		    statement = connection.createStatement();
		    statement.executeUpdate(query);
		}
		catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}
		finally {
		    DBUtil.close(statement);
		}
	}

	public void deleteBookFromCommand(int idUser, int idBook) {
		
		String query = "DELETE FROM command WHERE iduser = " + idUser + 
					   "AND idbook = "+ idBook +";" ;

		try {
		    statement = connection.createStatement();
		    statement.executeUpdate(query);
		}
		catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		}
		finally {
		    DBUtil.close(statement);
		}
	}
}

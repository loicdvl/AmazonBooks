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
		
		String query = "SELECT * FROM command c, useraccount u WHERE c.iduser = u.iduser AND"
					   + " iduser = " + iduser + ";";
		ResultSet rs = null;
		User user = null;
		Map<Book,Integer> lesLivres = new HashMap<Book,Integer>();
		Book b = null;
		
		try {

			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			
			while (rs.next()) {
				user = new User(rs.getInt("iduser"), rs.getString("login"), 
								rs.getString("password"), rs.getString("name"));
				
				b = new Book(rs.getInt("idbook"), rs.getString("title"), 
						 rs.getString("author"), rs.getFloat("price"),
						 rs.getString("image"), rs.getString("description"));
				
				lesLivres.put(b, rs.getInt("qte"));
				
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
		// TODO Auto-generated method stub
		
	}

	public void update(Command obj) {
		// TODO Auto-generated method stub
		
	}
	
	public void deleteCommandsContainingUser(User  obj) {
		
		String query = "DELETE FROM command WHERE iduser = " + obj.getId() + ";" ;

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

	public void deleteBookFromCommand(Command obj, Book book) {
		
		String query = "DELETE FROM command WHERE iduser = " + obj.getUser().getId() + 
					   "AND idbook = "+ book.getId() +";" ;

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

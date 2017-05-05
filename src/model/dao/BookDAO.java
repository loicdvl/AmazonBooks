package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.DBUtil;
import model.Book;

public class BookDAO {

	public Connection connection;
	private Statement statement;

	public BookDAO() {
		connection = DBUtil.getConnection();
	}

	public Book findById(int id) {
		
		String query = "SELECT * FROM book WHERE idbook = " + id + ";";
		ResultSet rs = null;
		Book b = null;
		
		try {

			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			
			while (rs.next()) {
				b = new Book(rs.getInt("idbook"), rs.getString("title"), 
							 rs.getString("author"), rs.getFloat("price"),
							 rs.getString("image"), rs.getString("description"));
			}

		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		} finally {
			DBUtil.close(rs);
			DBUtil.close(statement);
		}

		return b;
	}

	public void create(Book obj) {
		
		String query = "INSERT INTO book VALUES (" + obj.getId() + ",'" 
					   + obj.getTitle() + "','" + obj.getAuthor() + "'," 
					   + obj.getPrice() + ",'" + obj.getImage() + "','" 
					   + obj.getDescription() + "');";

		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		} finally {
			DBUtil.close(statement);
		}
	}

	public void update(Book obj) {
		
		String query = "UPDATE book SET title = '" + obj.getTitle() 
					   + "', author = '" +  obj.getAuthor() + "', price = " 
					   + obj.getPrice() + ", image = '" + obj.getImage() 
					   + "', description = '" + obj.getDescription() 
					   + "' WHERE idbook = " + obj.getId() + ";" ;
		
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

	public void delete(Book obj) {
		
		new CommandDAO().deleteCommandsContainingBook(obj);
		
		String query = "DELETE FROM book WHERE idbook = " + obj.getId() + ";" ;

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

	public int getMaxId() {
		
		String query = "SELECT max(idbook) FROM book;";
		ResultSet rs = null;
		int id = 0;

		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(query);

			while (rs.next()) {

				id = rs.getInt("max");
			}
		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		} finally {
			DBUtil.close(rs);
			DBUtil.close(statement);
		}

		return id;
	}
}

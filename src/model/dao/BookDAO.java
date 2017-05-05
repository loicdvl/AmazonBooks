package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.DBUtil;
import model.Book;

public class BookDAO implements DAO<Book> {
	
	public Connection connection;
	private Statement statement;


    public BookDAO() {
    	connection = DBUtil.getConnection();
    }


	@Override
	public Book findById(int id) {
		String query = "SELECT * FROM book WHERE idbook = " + id + ";";
        ResultSet rs = null;
        Book b = null;
        try {

            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
            	b = new Book(rs.getInt("idbook"),rs.getString("title"),
            			rs.getString("author"),rs.getFloat("price"),
            			rs.getString("image"),rs.getString("description"));
                
            }

        }
        catch (SQLException e) {
            System.out.println("Erreur SQL :" + e);
        }
        finally {

            DBUtil.close(rs);
            DBUtil.close(statement);
        }

        return b;
	}

	@Override
	public void create(Book obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Book obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Book obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxId() {
		// TODO Auto-generated method stub
		return 0;
	}

}

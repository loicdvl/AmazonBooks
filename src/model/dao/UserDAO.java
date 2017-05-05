package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.DBUtil;
import model.Functions;
import model.User;

public class UserDAO{
	
	public Connection connection;
	private Statement statement;

	public UserDAO() {
		connection = DBUtil.getConnection();
	}

	public User findById(int id) {
		
		String query = "SELECT * FROM useraccount WHERE iduser = " + id + ";";
		ResultSet rs = null;
		User user = null;
		
		try {

			statement = connection.createStatement();
			rs = statement.executeQuery(query);
			
			while (rs.next()) {
				user = new User(rs.getInt("iduser"), rs.getString("login"), 
								rs.getString("password"), rs.getString("name"));
			}

		} catch (SQLException e) {
			System.out.println("Erreur SQL :" + e);
		} finally {
			DBUtil.close(rs);
			DBUtil.close(statement);
		}

		return user;
	}

	public void create(User obj) {
		
		String query = "INSERT INTO useraccount VALUES (" + obj.getId() + ",'" 
					   + Functions.echapStringSql(obj.getLogin()) + "','" 
					   + Functions.echapStringSql(obj.getPassword()) + "','" 
					   + Functions.echapStringSql(obj.getName()) + "');";

	try {
		statement = connection.createStatement();
		statement.executeUpdate(query);

	} catch (SQLException e) {
		System.out.println("Erreur SQL :" + e);
	} finally {
		DBUtil.close(statement);
	}
		
	}

	public void update(User obj) {
		
		String query = "UPDATE useraccount SET login = '" + Functions.echapStringSql(obj.getLogin())
					   + "', password = '" +  Functions.echapStringSql(obj.getPassword()) 
					   + "', name = '" + Functions.echapStringSql(obj.getName()) + "' WHERE iduser = " 
					   + obj.getId() + ";" ;

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

	public void delete(User obj) {
		
		try {
			
		    statement = connection.createStatement();
		    
			String queryDelete = "DELETE FROM command WHERE iduser = " + obj.getId() + ";" ;
		    statement.executeUpdate(queryDelete);
		
			statement = connection.createStatement();

		    String query = "DELETE FROM useraccount WHERE iduser = " + obj.getId() + ";" ;		
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
		
		String query = "SELECT max(iduser) FROM useraccount;";
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

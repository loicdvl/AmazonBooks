package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *  Classe utilitaire pour la base de données
 *  @author Corentin
 */
public class DBUtil {

	private static Connection conn;
	
    public static Connection getConnection() {
        if( conn != null )
            return conn;
       
        try {
            String driver = "org.postgresql.Driver" ;
            String url = "jdbc:postgresql://localhost:5432/bookenaton" ;
            String user = "postgres" ;
            String password ="55555" ;
            Class.forName( driver );
            conn = DriverManager.getConnection( url, user, password );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return conn;
    }
 
    public static void close( Connection c ) {
        if( c == null )
            return;
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
            }
        }
    }
}

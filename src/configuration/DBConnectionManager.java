package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *  Classe servant à paramétrer le driver pour se connecter à la BDD
 *  @author Benoit
 */
public class DBConnectionManager {
    private Connection connection;
    
    // Paramétrage du driver de la base de données
    public DBConnectionManager(String dbURL, String user, String pwd) throws ClassNotFoundException, SQLException{
        Class.forName("org.postgresql.Driver");
        this.connection = DriverManager.getConnection(dbURL, user, pwd);
    }
     
    public Connection getConnection(){
        return this.connection;
    }
}

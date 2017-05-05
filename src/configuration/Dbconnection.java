package configuration;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Dbconnection implements ServletContextListener {


		@Override
		public void contextDestroyed(ServletContextEvent servletContextEvent) {
			 Connection con = (Connection) servletContextEvent.getServletContext().getAttribute("DBConnection");
		        try {
		            con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			
		}

		@Override
		public void contextInitialized(ServletContextEvent servletContextEvent) {
			 ServletContext ctx = servletContextEvent.getServletContext();
	         
		        // initialisation de la connexion à la base de données
		        String dbURL = "jdbc:postgresql://localhost:5432/bookenaton";
		        String user = "postgres";
		        String pwd = "55555";
		         
		        try {
		            DBConnectionManager connectionManager = new DBConnectionManager(dbURL, user, pwd);
		            ctx.setAttribute("DBConnection", connectionManager.getConnection());
		            System.out.println("DB Connection initialized successfully.");
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			
		}
	}

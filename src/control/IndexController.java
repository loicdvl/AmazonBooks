package control;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Book;
import model.Command;
import model.User;
import model.dao.BookDAO;
import model.dao.CommandDAO;
import model.dao.UserDAO;

/**
 *  Servlet servant de controleur pour aiguiller les différentes vues jsp
 *  @author Benoit
 */
@WebServlet("/")
public class IndexController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String choix = request.getParameter("choix");
		String page = "";
		if(choix != null){
			switch(choix){
			
			case "profile" :
				int iduser = Integer.valueOf(request.getParameter("iduser"));
				User user = new UserDAO().findById(iduser);
				request.setAttribute("user", user);
	            page = "profile.jsp";
				break;
				
			case "basket" :
				int iduser2 = Integer.valueOf(request.getParameter("iduser"));
				User user2 = new UserDAO().findById(iduser2);
				Command cmd = new CommandDAO().findById(iduser2);
				request.setAttribute("command", cmd);
				request.setAttribute("user", user2);
	            page = "basket.jsp";
				break;
			}
		} else {
			page = "login.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		String page ="";
		
		String loginEntered = request.getParameter("login");
		String passwordEntered = request.getParameter("password");
		
		if(loginEntered.equals("") || passwordEntered.equals("")){
			
			request.setAttribute("erreur", "Il faut remplir les deux champs.");
			page = "/login.jsp";
			
		} else {
			
			User user = new UserDAO().findByLogin(loginEntered);
			
			if(user == null) {
				
				request.setAttribute("erreur", "Ce login n'existe pas.");
				page = "/login.jsp";
				
			} else {
				if(!passwordEntered.equals(user.getPassword())) {
					
					request.setAttribute("erreur", "Mot de passe éroné.");
					page = "/login.jsp";
					
				} else {
					
					request.setAttribute("user", user);
					
					ArrayList<Book> books = new BookDAO().getAllBooks();
					request.setAttribute("books", books);
					page = "/index.jsp";
					
				}
			}
			
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}
}

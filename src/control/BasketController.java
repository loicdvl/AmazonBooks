package control;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Command;
import model.User;
import model.dao.CommandDAO;
import model.dao.UserDAO;

/**
 *  Servlet servant de controleur pour aiguiller les différentes vues jsp
 *  @author Benoit
 */
@WebServlet("/basket")
public class BasketController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasketController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String choix = request.getParameter("choix");
		String page = "";
		
		switch(choix){
		
		case "add" :
			
			int idBook = Integer.valueOf(request.getParameter("idBook"));
			int idUser = Integer.valueOf(request.getParameter("idUser"));
			int quantity = Integer.valueOf(request.getParameter("quantity"));

			new CommandDAO().createEntry(idBook,idUser,quantity);
			
			User user = new UserDAO().findById(idUser);
			
			request.setAttribute("user", user);

            page = "index.jsp";
			break;
			
		case "update" :
			
			int idBookUpdate = Integer.valueOf(request.getParameter("idBook"));
			int idUserUpdate = Integer.valueOf(request.getParameter("idUser"));
			int quantityUpdate = Integer.valueOf(request.getParameter("quantity"));

			new CommandDAO().updateEntry(idBookUpdate,idUserUpdate,quantityUpdate);
			
			
			User userUpdate = new UserDAO().findById(idUserUpdate);
			Command cmdUpdate = new CommandDAO().findById(idUserUpdate);

			request.setAttribute("command", cmdUpdate);
			request.setAttribute("user", userUpdate);
			
            page = "basket.jsp";
			break;
			
		case "delete" :
			
			int idBookDelete = Integer.valueOf(request.getParameter("idBook"));
			int idUserDelete = Integer.valueOf(request.getParameter("idUser"));
			
			new CommandDAO().deleteBookFromCommand(idUserDelete, idBookDelete);
			
			
			User userDelete = new UserDAO().findById(idUserDelete);
			Command cmdDelete = new CommandDAO().findById(idUserDelete);
			
			request.setAttribute("command", cmdDelete);
			request.setAttribute("user", userDelete);

            page = "basket.jsp";
			break;
			
		case "checkout" :
			
			int idUserCheckout = Integer.valueOf(request.getParameter("idBook"));
			
			new CommandDAO().deleteCommandsContainingUser(idUserCheckout);
			
			
			User userCheckout = new UserDAO().findById(idUserCheckout);
			request.setAttribute("user", userCheckout);
			
            page = "index.jsp";
			break;
		}
		
		
		request.getRequestDispatcher(page).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

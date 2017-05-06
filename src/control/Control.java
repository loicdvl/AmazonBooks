package control;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.dao.BookDAO;
import model.dao.CommandDAO;
import model.dao.UserDAO;

/**
 *  Servlet servant de controleur pour aiguiller les différentes vues jsp
 *  @author Benoit
 */
@WebServlet("/")
public class Control extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Control() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*String choix = request.getParameter("choix");
		String page = "";
		
		switch(choix){
		
			/*case "semestre" :
				String idfiliere = request.getParameter("idfiliere");
				Filiere fil = filiereDao.findById(Integer.valueOf(idfiliere));
				request.setAttribute("fil", fil);
	            page = "semestre.jsp";
				break;
			case "suppression" : 
				String idfiliere = request.getParameter("idfiliere");
				Filiere fil = filiereDao.findById(Integer.valueOf(idfiliere));
				filiereDao.delete(fil);
				page = "index.jsp";

				break;
			case "modification" : 
				String idfiliere = request.getParameter("idfiliere");
				Filiere fil = filiereDao.findById(Integer.valueOf(idfiliere));
				request.setAttribute("fil", fil);
	            page = "modifFiliere.jsp";
				
				break;
			case "ajout" : 
				page = "ajoutFiliere.jsp";
				break;
				
			default:
	            page = "index.jsp";
	            break;
		}
		

		*/
		request.getRequestDispatcher("index.jsp").forward(request, response);

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
			page = "/index.jsp";
			
		} else {
			
			User user = new UserDAO().findByLogin(loginEntered);
			
			if(user == null) {
				
				request.setAttribute("erreur", "Ce login n'existe pas.");
				page = "/index.jsp";
				
			} else {
				if(!passwordEntered.equals(user.getPassword())) {
					
					request.setAttribute("erreur", "Mot de passe éroné.");
					page = "/index.jsp";
					
				} else {
					
					request.setAttribute("user", user);
					page = "/accueil.jsp";
					
				}
			}
			
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}
}

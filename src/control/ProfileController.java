package control;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.dao.UserDAO;

/**
 *  Servlet servant de controleur pour aiguiller les différentes vues jsp
 *  @author Benoit
 */
@WebServlet("/profile")
public class ProfileController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileController() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int iduser = Integer.valueOf(request.getParameter("iduser"));
		String name = request.getParameter("name");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		User user = new User(iduser, login, password, name);
		
		new UserDAO().update(user);
		
		request.setAttribute("user", user);
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}
}

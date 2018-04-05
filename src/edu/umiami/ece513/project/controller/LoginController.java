package edu.umiami.ece513.project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import edu.umiami.ece513.project.dao.PersonDAOImplementation;
import edu.umiami.ece513.project.vo.Comments;
import edu.umiami.ece513.project.vo.Person;
import edu.umiami.ece513.project.vo.PersonDeclarations;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PersonDeclarations pd = new PersonDAOImplementation();
		String submitType = request.getParameter("submit");
		String userName = request.getParameter("username");
		String pwd = request.getParameter("password");
		String pwd2 = request.getParameter("password2");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		Person p = pd.getPerson(userName, pwd);

		if (submitType.equals("Login")) {
			/*
			 * String userName=request.getParameter("username"); String
			 * pwd=request.getParameter("password"); Person p=pd.getPerson(userName, pwd);
			 */
			if (p != null && p.getFname() != null) {
				HttpSession session = request.getSession();
				session.setAttribute("Uss", userName);
				request.setAttribute("message", "Hi " + p.getFname() + " ! Welcome !");
				request.getRequestDispatcher("main.jsp").forward(request, response);
			} else {
				request.setAttribute("message",
						"Username or Password is incorrect. If you do not have an account yet, please register.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} else if (submitType.equals("Register")) {
			/*
			 * String userName=request.getParameter("username"); String
			 * pwd=request.getParameter("password"); String
			 * pwd2=request.getParameter("password2"); String
			 * fname=request.getParameter("fname"); String
			 * lname=request.getParameter("lname"); Person p=pd.getPerson(userName, pwd);
			 */
			if (pd.checkPerson(userName)) {
				request.setAttribute("failMessage", "Registration Failed. Your Username already exists.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
			if (pwd.equals(pwd2)) {
				p.setUsername(userName);
				p.setPwd(pwd);
				p.setFname(fname);
				p.setLname(lname);
				pd.insertPerson(p);
				request.setAttribute("successMessage", "Registration Succeed. Now you can log in.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				request.setAttribute("failMessage", "Registration Failed. Your Password did not match.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		} else if (submitType.equals("Insert Comment")) {
			HttpSession session = request.getSession();
			Comments c = new Comments();
			String comment = request.getParameter("comment");
			c.setUsername("" + session.getAttribute("Uss"));
			c.setComment(comment);
			pd.insertComment(c);
			request.setAttribute("messageAdded", "The comment was added to your list of comments !");
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} else if (submitType.equals("Show Comments")) {
			HttpSession session = request.getSession();
			request.setAttribute("messageList",
					"This is the list of comments you added: " + pd.getComments("" + session.getAttribute("Uss")));
			request.getRequestDispatcher("list.jsp").forward(request, response);
		} else if (submitType.equals("Log Out")) {
			HttpSession session = request.getSession();
			session.removeAttribute("Uss");
			session.invalidate();
			request.setAttribute("message", "You have successfully logged out !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else if (submitType.equals("Insert More Comments")) {
			HttpSession session = request.getSession();
			request.setAttribute("message", "Hello again " + session.getAttribute("Uss"));
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}
	}

}
package login.sumit.registration;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//here
    public login() {
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersonDB pd=new PersonDAImplementation();
		String submitType=request.getParameter("submit");
		
		if(submitType.equals("Login")) {
			String userName=request.getParameter("username");
			String pwd=request.getParameter("password");
			Person p=pd.getPerson(userName, pwd);
			if(p!=null && p.getFname()!=null){
				request.setAttribute("message", "Hi " + p.getFname() +  " ! Welcome !");
				request.getRequestDispatcher("main.jsp").forward(request, response);
			}
			else {
				request.setAttribute("message", "Username or Password is incorrect. If you do not have an account yet, please register.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else if(submitType.equals("Register")) {
			String userName=request.getParameter("username");
			String pwd=request.getParameter("password");
			String pwd2=request.getParameter("password2");
			String fname=request.getParameter("fname");
			String lname=request.getParameter("lname");
			Person p=pd.getPerson(userName, pwd);
			if(pd.checkPerson(userName)) {
				request.setAttribute("failMessage", "Registration Failed. Your Username already exists.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
			if(pwd.equals(pwd2)) {
				p.setUsername(userName);
				p.setPwd(pwd);
				p.setFname(fname);
				p.setLname(lname);
				pd.insertPerson(p);
				request.setAttribute("successMessage", "Registration Succeed. Now you can log in.");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			else {
				request.setAttribute("failMessage", "Registration Failed. Your Password did not match.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		}else if(submitType.equals("Insert Comment")){
			Comments c=new Comments();
			String comment=request.getParameter("comment");
			c.setComment(comment);
			pd.insertComment(c);
			request.setAttribute("messageAdded", "The comment was added to your list of comments !");
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}else if(submitType.equals("Show Comments")) {
			request.setAttribute("messageList", "This is the list of comments you added: " + pd.getComments());
			request.getRequestDispatcher("list.jsp").forward(request, response);
		}else if(submitType.equals("Log Out")){
			request.setAttribute("message", "You have successfully logged out !");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
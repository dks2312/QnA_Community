package Test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.MemberDAO;
import DB.MemberVo;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDAO userDAO = new MemberDAO();
		boolean isMember = userDAO.selected(new MemberVo(id, pw, null));
		
		if(isMember) response.sendRedirect("index.html");
		else response.sendRedirect("login_page.html");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
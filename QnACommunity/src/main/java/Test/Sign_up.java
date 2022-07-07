package Test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DB.MemberDAO;
import DB.MemberVo;

@WebServlet("/sign_up")
public class Sign_up extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String id = null;
	String pw = null;
	String idCheck = null;
	String pwCheck = null;
	String nicName = null;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		id = request.getParameter("id");
		pw = request.getParameter("pw");
		idCheck = request.getParameter("id_check");
		pwCheck = request.getParameter("pw_check");
		nicName = request.getParameter("nic_name");
		
		MemberDAO userDAO = new MemberDAO();
		userDAO.insert(new MemberVo(id, pw, nicName));

		response.sendRedirect("login_page.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

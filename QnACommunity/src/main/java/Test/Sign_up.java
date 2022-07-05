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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String pwCheck = request.getParameter("pw_check");
		String nicName = request.getParameter("nic_name");
		
		if(!pw.equals(pwCheck))
			System.out.println("비밀번호 체크하세요.");
		
		MemberDAO userDAO = new MemberDAO();
		boolean isInsert = userDAO.insert(new MemberVo(id, pw, nicName));
		
		if(isInsert) response.sendRedirect("sign_up_page.html");
		else response.sendRedirect("login_page.html");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

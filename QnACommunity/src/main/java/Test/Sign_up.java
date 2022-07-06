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
		

		boolean isValidat = validation(userDAO);
		
		if(isValidat) userDAO.insert(new MemberVo(id, pw, nicName));

		response.sendRedirect("sign_up_page.html");
//		response.sendRedirect("login_page.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
	
	private boolean validation(MemberDAO userDAO) {
		boolean ret = true;
		
		// 비밀번호 필드와 비밀번호 확인 필드의 값이 다를 때
		if(!pw.equals(pwCheck)) {	System.out.println("비밀번호가 맞지 않습니다");	ret = false;}
		
		// 입력받는 데이터의 크기가 클 때
		if (id.getBytes().length > userDAO.columnData(0).size){		System.out.println("아이디의 길이가 너무 큽니다");		ret = false;}
		if (pw.getBytes().length > userDAO.columnData(1).size){		System.out.println("비밀번호의 길이가 너무 큽니다");	ret = false;}
		if (nicName.getBytes().length > userDAO.columnData(2).size){System.out.println("닉네임의 길이가 너무 큽니다");		ret = false;}
		
		return ret;
	}
}

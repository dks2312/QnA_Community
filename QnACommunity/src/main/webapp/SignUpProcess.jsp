<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "DB.MemberDAO" %>
<%@ page import = "DB.MemberVo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String nicName = request.getParameter("nic_name");
	
	MemberDAO memberDAO = new MemberDAO();
	MemberVo memberVo = new MemberVo(id, pw, nicName);
	
	if(memberDAO.selected(memberVo) == null){
		memberDAO.insert(memberVo);
		
		response.sendRedirect("LogIn.jsp");
	}
	else{
		request.setAttribute("SignUpErrMsg", "중복된 아이디가 존재합니다.");
		request.getRequestDispatcher("SignUp.jsp").forward(request, response);
	}
	
	%>
</body>
</html>
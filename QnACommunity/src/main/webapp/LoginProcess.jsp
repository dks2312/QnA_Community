<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DB.MemberDAO"%>
<%@ page import="DB.MemberVo"%>
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
	
	MemberDAO memberDAO = new MemberDAO();
	MemberVo user = memberDAO.selected(new MemberVo(id, pw));

	if(user != null){
		session.setAttribute("UserId", user.getId());
		session.setAttribute("UserName", user.getNickName());
		
		response.sendRedirect("Index.jsp");
	}else{
		request.setAttribute("LoginErrMsg", "아이디가 존재하지 않습니다.");
		request.getRequestDispatcher("LogIn.jsp").forward(request, response);
	}
%>
</body>
</html>
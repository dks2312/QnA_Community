<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DB.MemberDAO"%>
<%@ page import="DB.MemberVO"%>
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
	
	MemberDAO memberDAO = new MemberDAO(application);
	MemberVO member = memberDAO.selected(id, pw);
	memberDAO.close();
	
	
	if(member != null){
		session.setAttribute("User", member);
		request.setAttribute("LoginMsg", member.getNickName() +"님 환영합니다. 성공적으로 로그인 되셨습니다.");
		response.sendRedirect("Index.jsp");
	}else{
		request.setAttribute("LoginErrMsg", "로그인 정보가 맞지 않습니다.");
		request.getRequestDispatcher("LogIn.jsp").forward(request, response);
	}
%>
</body>
</html>
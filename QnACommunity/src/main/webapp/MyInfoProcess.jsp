<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DB.MemberVO" %>
<%@ page import="DB.MemberDAO" %>
<% 
	
	String name = request.getParameter("changeName");
	MemberVO user =(MemberVO)session.getAttribute("User");
	
	if(!(name == null || name.equals(user.getNickName()) || name.equals(""))){
		MemberDAO dao = new MemberDAO();
		dao.reName(user.getId(), name);
		
		// 바뀐 값 갱신
		session.setAttribute("User", dao.selected(user.getId(), user.getPassword()));
	}
	
	response.sendRedirect("MyInfo.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문 있습니다!!!</title>
</head>
<body>
</body>
</html>
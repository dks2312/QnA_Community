<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DB.MemberVO" %>
<%@ page import="DB.MemberDAO" %>
<% 
	String name = request.getParameter("changeName");
	MemberVO user =(MemberVO)session.getAttribute("User");
	MemberDAO dao = new MemberDAO();
	
	if(!(name == null || name.equals(user.getNickName()) || name.equals(""))
		&& null == dao.validateMamberIPN(user.getId(), user.getPassword(), name)){
		
		dao.reName(user.getId(), name);
		session.setAttribute("User", dao.selected(user.getId(), user.getPassword())); // 바뀐 값 갱신
	}
	
	dao.close();
	
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
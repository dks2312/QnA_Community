<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DB.PostDAO" %>
<%@ page import="DB.PostVO" %>
<%@ page import="DB.MemberVO" %>
<% 
	if(session.getAttribute("User") == null){
		request.setAttribute("PostEditerErrMsg", "로그인을 한 후 이용할 수 있습니다");
		request.getRequestDispatcher("PostBoard.jsp").forward(request, response);
	}

	String title = request.getParameter("post_title_input");
	String category = request.getParameter("category");
	String content = request.getParameter("editordata");
	MemberVO user = (MemberVO)session.getAttribute("User");
	
	
	PostDAO postDAO = new PostDAO();
	postDAO.insert(category, title, content, user.getId());
	
	response.sendRedirect("PostBoard.jsp");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
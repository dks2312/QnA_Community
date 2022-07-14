<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DB.CommentDAO" %>
<%@ page import="DB.CommentVO" %>
<%@ page import="DB.MemberVO" %>
<% 
	if(session.getAttribute("User") == null){
		request.setAttribute("PostEditerErrMsg", "로그인을 한 후 이용할 수 있습니다");
		request.getRequestDispatcher("PostBoard.jsp").forward(request, response);
	}


	long num = Long.parseLong(request.getParameter("num"));
	String content = request.getParameter("comment_text");
	
	MemberVO user = (MemberVO)session.getAttribute("User");
	
	CommentDAO commentDAO = new CommentDAO();
	commentDAO.insert(num, user.getId(), content);
	
	request.getRequestDispatcher("Post.jsp?num="+ num).forward(request, response);

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
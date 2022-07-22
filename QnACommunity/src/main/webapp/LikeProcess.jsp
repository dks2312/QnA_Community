<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DB.PostDAO" %>
<%@ page import="DB.PostVO" %>
<%@ page import="DB.CommentDAO" %>
<%@ page import="DB.CommentVO" %>
<%@ page import="DB.MemberVO" %>
<% 
	if(session.getAttribute("User") == null){
		request.setAttribute("UserErr", "로그인을 한 후 이용할 수 있습니다");
		request.getRequestDispatcher("PostBoard.jsp").forward(request, response);
		return;
	}

	String likeTable = request.getParameter("likeTB");
	long num = Long.parseLong(request.getParameter("likeNum"));
	String backPostNum = request.getParameter("backPostNum");
	MemberVO user = (MemberVO)session.getAttribute("User");
	boolean isLike = false;
	
	switch(likeTable){
	case "POST":
		PostDAO postDAO = new PostDAO(application);
		postDAO.likeAction(num, user.getId());
		postDAO.close();
		break;
	case "COMMENT":
		CommentDAO comDAO = new CommentDAO(application);
		comDAO.likeAction(num, user.getId());
		comDAO.close();
		break;
	}
	
	request.setAttribute("num", request.getParameter("backPostNum"));
	request.getRequestDispatcher("Post.jsp").forward(request, response);
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
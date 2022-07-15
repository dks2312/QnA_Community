<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DB.CommentDAO" %>
<%@ page import="DB.CommentVO" %>
<%@ page import="DB.MemberVO" %>
<% 	
	if(session.getAttribute("User") == null){
		request.setAttribute("PostEditerErrMsg", "로그인을 한 후 이용할 수 있습니다");
		request.getRequestDispatcher("PostBoard.jsp").forward(request, response);
		return;
	}


	CommentDAO commentDAO = new CommentDAO(application);
	
	request.setCharacterEncoding("UTF-8");	// 입력된 값의 인코딩 방식 변경
	
	long num = Long.parseLong(request.getParameter("num"));
	String content = request.getParameter("comment_text");
	MemberVO user = (MemberVO)session.getAttribute("User");
	
	commentDAO.insert(num, user.getId(), content);	// 댓글을 DB에 저장
	commentDAO.close();
	
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
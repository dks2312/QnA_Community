<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DB.PostDAO" %>
<%@ page import="DB.PostVO" %>
<%@ page import="DB.CommentDAO" %>
<%@ page import="DB.CommentVO" %>
<%@ page import="DB.MemberVO" %>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	String num = request.getParameter("num");
	long numL = Long.parseLong(num);
	
	PostDAO postDao = new PostDAO();
	PostVO post = postDao.selectPost(num);
	
	CommentDAO comDao = new CommentDAO();
	Queue<CommentVO> commentQ = comDao.commentList(numL);
	
	String userName = "익명의사용자";
	if(session.getAttribute("User") != null){
		userName = ((MemberVO)session.getAttribute("User")).getNickName();
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/index.css">
<link rel="stylesheet" href="./css/public/frame.css">
<link rel="stylesheet" href="./css/post_page.css">
<title>질문 있습니다!!!</title>
</head>
<body>
	<div id="wrap">
		<div class="header">
			<div class="back_btn">
				<a href="./PostBoard.jsp">뒤로가기</a>
			</div>
			<input type="text" class="search">
		</div>

		<div class="post">
			<div class="post_title"><%= post.getTitle() %></div>
			<hr>
			<div class="post_body">
				<%= post.getContent() %>
			</div>
			<hr>
			<button>하트 : <%= post.getLikeCount() %></button>
			<button>댓글 수 : <%= post.getCommentCount() %></button>
		</div>
		<%
		while(!commentQ.isEmpty()){
			CommentVO com = commentQ.poll();
		%>
			<div class="comment">
				<div class="comment_info">
					<div class="user_name"><%= com.getWriterName() %></div>
					<div class="date"><%= com.getDate() %></div>
					<button class="love">하트 : <%= com.getListCount() %></button>
				</div>
				<hr>
				<div class="comment_body"><%= com.getContent() %></div>
			</div>
		<%	
		}
		%>
		<div class="comment_add">
			<div class="comment_info">
				<div class="user_name"><%= userName %></div>
				<div class="date"><%= new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %></div>
				<button class="add">작성</button>
			</div>
			<hr>
			<textarea name="" class="comment_textFiled" ></textarea>
		</div>
		<footer class="footer"> Lorem ipsum, dolor sit amet
			consectetur adipisicing elit. Dolores odio debitis dignissimos saepe,
			repudiandae deleniti qui rem? Veniam, ratione! Error cum perspiciatis
			a expedita molestiae repellendus autem ipsam animi magni? </footer>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DB.PostDAO" %>
<%@ page import="DB.PostVO" %>
<%
	String num = request.getParameter("num");
	
	PostDAO dao = new PostDAO();
	PostVO post = dao.selectPost(num);
	

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

		<div class="comment_list">
			<div class="comment">
				<div class="comment_info">
					<div class="user_name">user1</div>
					<div class="date">2022-06-28</div>
					<button class="love">하트 : 1</button>
				</div>
				<hr>
				<div class="comment_body">질문을 많이 하면 됩니다</div>

			</div>
		</div>

		<div class="comment_add">
			<div class="comment_info">
				<div class="user_name">user1</div>
				<div class="date">2022-06-28</div>
				<button class="add">작성</button>
			</div>
			<hr>
			<textarea name="" id="comment_textFiled" cols="123" rows="9"></textarea>
		</div>

		<footer class="footer"> Lorem ipsum, dolor sit amet
			consectetur adipisicing elit. Dolores odio debitis dignissimos saepe,
			repudiandae deleniti qui rem? Veniam, ratione! Error cum perspiciatis
			a expedita molestiae repellendus autem ipsam animi magni? </footer>
	</div>
</body>
</html>
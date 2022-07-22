<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="DB.PostDAO" %>
<%@ page import="DB.PostVO" %>
<%@ page import="DB.CommentDAO" %>
<%@ page import="DB.CommentVO" %>
<%@ page import="DB.MemberVO" %>
<%@ page import="utils.BoardPage" %>
<%
	if(session.getAttribute("User") == null){
		request.setAttribute("UserErr", "로그인을 한 후 이용할 수 있습니다");
		request.getRequestDispatcher("Index.jsp").forward(request, response);
		return;
	}

	PostDAO postDAO = new PostDAO(application);
	CommentDAO commentDAO = new CommentDAO(application);
	
	String userId = ((MemberVO)session.getAttribute("User")).getId();
	String search = request.getParameter("search");
	String type = request.getParameter("type");
	
	if(search == null) search = "";
	if(type == null) type = "cp";

	
	Queue<PostVO> postList = new LinkedList<PostVO>();
	Queue<CommentVO> commentList = new LinkedList<CommentVO>();
	
	switch(type){
	case "cp":	postList = postDAO.userSearch(userId);			break;
	case "lp":	postList = postDAO.likeSearch(userId);			break;
	case "cc":	commentList = commentDAO.userSearch(userId);	break;
	case "lc": 	commentList = commentDAO.likeSearch(userId);	break;
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/post_board_page.css">
<link rel="stylesheet" href="./css/myPostBoard.css">
<link rel="stylesheet" href="./css/public/frame.css">
<title>질문 있습니다!!!</title>
</head>
<body>
	<div id="wrap">
		<jsp:include page="./header.jsp"/>

		<div class="content">

			<form method="get" action="myPostBoard.jsp">
				<div class="search_bar">
					<div class="back_btn">
						<a href="./Index.jsp">뒤로가기</a>
					</div>
					<div class="search_pane">
						<input class="search" type="text" name="search" value="<%= (search==null)?"":search %>">
						<button type="submit">검색</button>
					</div>
				</div>
			</form>
			
			<%
			if(type.equals("cp") || type.equals("lp") && !(postList == null || postList.isEmpty())){
			%> 
			<div class="post_board">
				<div class="post_tilte">
					<div class="post_category">카테고리</div>
					<div class="post_title">제목</div>
					<div class="post_view">조회수</div>
					<div class="post_comment_count">댓글</div>
					<div class="post_like_count">좋아요</div>
					<div class="post_date_created">작성일</div>
				</div>
				<%
				int likeCount = 0;
				int commentCount = 0;
				
				while(!postList.isEmpty()){
					PostVO post = postList.poll();
					
					likeCount = postDAO.getLikeCount(post.getNum());
					commentCount = postDAO.getCommentCount(post.getNum());
				%>
					<div class="post">
						<div class="post_category"><%= post.getCartegory() %></div>
						<div class="post_title">
							<a href="./Post.jsp?num=<%= post.getNum() %>"><%= post.getTitle() %></a>
						</div>
						<div class="post_view">조회수 : <%= post.getVisitCount() %></div>
						<div class="post_comment_count">댓글 : <%= commentCount %></div>
						<div class="post_like_count">좋아요 : <%= likeCount %></div>
						<div class="post_date_created"><%= post.getSimlpDate() %></div>
					</div>
				<%
				}
				postDAO.close();	
				%>
			</div>
				<%
				}else if(type.equals("cc") || type.equals("lc") && !(commentList == null || commentList.isEmpty())){
					while(!commentList.isEmpty()){
						CommentVO com = commentList.poll();
				%>
						<div class="comment">
							<div class="comment_info">
								<div class="user_name"><a href="PostBorad.jsp?num=<%= com.getPost_FK() %>"><%= com.getWriterName() %></a></div>
								<div class="date"><%= com.getSimlpDate() %></div>
								
								<a class="love">
									하트 : <%= commentDAO.getLikeCount(com.getNum()) %>
								</a>
							</div>
							<hr>
							<div class="comment_body"><%= com.getContent() %></div>
						</div>
				<%	
					}
					commentDAO.close();
				}else{
				%>
				<div class="no_post">게시물을 찾을 수 없습니다</div>
				<%
				}
				%>
		</div>
		<jsp:include page="./footer.html"/>
	</div>
</body>
</html>
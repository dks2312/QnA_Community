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
	String num = (String)((request.getAttribute("num")==null)?request.getParameter("num"):request.getAttribute("num"));
	long numL = Long.parseLong(num);
	
	// 조회수 증가, 게시글 정보 불러오기
	PostDAO postDao = new PostDAO(application);
	postDao.updateVisitCount(numL);	
	PostVO post = postDao.selectPost(numL);	
	int likeCount = postDao.getLikeCount(numL);
	int commentCount = postDao.getCommentCount(numL);
	String content = postDao.contentStr(post.getContent());
	postDao.close();
	
	// 게시글 댓글 불러오기
	CommentDAO comDao = new CommentDAO(application);
	Queue<CommentVO> commentQ = comDao.commentList(numL);
	
	String userName = "익명의사용자";
	String userId = null;
	if(session.getAttribute("User") != null){
		MemberVO user = (MemberVO)session.getAttribute("User");
		userId = user.getId();
		userName = user.getNickName();
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
	<% if(request.getAttribute("likeErrMsg") != null){%>
	  <script>
	    alert("<%= request.getAttribute("likeErrMsg") %>");
	  </script>
	<%}%>
	
	<div id="wrap">
		<jsp:include page="./header.jsp"/>
		
		<div class="content">
			<div class="search_bar">
				<div class="back_btn">
					<a href="./PostBoard.jsp">뒤로가기</a>
				</div>
			</div>
	
			<div class="post">
				<div class="post_title">제목 : <%= post.getTitle() %></div>
				<hr>
				<div class="post_body"><%= content %></div>
				<hr>
				<a href="LikeProcess.jsp?likeTB=POST&likeNum=<%= numL %>&backPostNum=<%= numL %>">
					하트 : <%= likeCount %>
				</a>
				<button>댓글 수 : <%= commentCount %></button>
			</div>
			<%
			while(!commentQ.isEmpty()){
				CommentVO com = commentQ.poll();
				
			%>
				<div class="comment">
					<div class="comment_info">
						<div class="user_name"><%= com.getWriterName() %></div>
						<div class="date"><%= com.getSimlpDate() %></div>
						
						<a class="love" href="LikeProcess.jsp?likeTB=COMMENT&likeNum=<%= com.getNum() %>&backPostNum=<%= numL %>">
							하트 : <%= comDao.getLikeCount(com.getNum()) %>
						</a>
					</div>
					<hr>
					<div class="comment_body"><%= com.getContent() %></div>
				</div>
			<%	
			}
			comDao.close();
			%>
			<div class="comment_add">
				<div class="comment_info">
					<div class="user_name"><%= userName %></div>
					<div class="date"><%= new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()) %></div>
					<button class="add" form="new_comment_form">작성</button>
				</div>
				<hr>
				<form method="post" onsubmit="return validateForm(this)" id="new_comment_form" action="CommentUpLoadProcess.jsp?num=<%= numL %>">
					<textarea name="comment_text" class="comment_textFiled" ></textarea>
				</form>
			</div>
		</div>
		
		<jsp:include page="./footer.html"/>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="DB.PostDAO" %>
<%@ page import="DB.PostVO" %>
<%
PostDAO postDAO = new PostDAO();

	Map<String, Object> param = new HashMap<String, Object>();
	String searchTitle = request.getParameter("searchTitle");
	String searchSort = request.getParameter("searchSort");
	String searchCategory = request.getParameter("searchCategory");
	
	if(searchTitle != null) param.put("searchTitle", searchTitle);
	if(searchSort != null) param.put("searchSort", searchSort);
	if(searchCategory != null) param.put("searchCategory", searchCategory);
	
	Queue<PostVO> postList = postDAO.selectList(param);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/post_board_page.css">
<link rel="stylesheet" href="./css/public/frame.css">
<title>질문 있습니다!!!</title>
</head>
<body>

	<div id="wrap">
		<form method="get">
			<header class="header">
				<div class="back_btn">
					<a href="./Index.jsp">뒤로가기</a>
				</div>
				<div class="search_pane">
					<input class="search" type="text" name="searchTitle">
				</div>
				<button>검색</button>
			</header>
	
			<div class="board_set">
				<div class="seting">
					<div class="category_selected">
						<label for="category_items">카테고리</label> 
						<select name="searchCategory" id="category_items">
							<option value="all">전체</option>
							<option value="quest">질문</option>
							<option value="error">오류&amp;에러</option>
						</select>
					</div>
					<div class="sort_selected">
						<label for="sort_items">정렬</label> 
						<select name="searchSort" id="sort_items">
							<option value="NUM">기본</option>
							<option value="POST_DATE">최신순</option>
							<option value="LIKE_COUNT">인기순</option>
						</select>
					</div>
				</div>
	
				<div class="post_write">
					<a href="./PostEditer.jsp">글 작성하기</a>
				</div>
			</div>
		</form>


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
			if(postList == null || postList.isEmpty()){
			%> 
				<div class="no_post">등록된 게시물이 없습니다</div>
			<%
			} else{
				for(PostVO post : postList){
			%>
					<div class="post">
						<div class="post_category"><%= post.getCartegory() %></div>
						<div class="post_title">
							<a href="./Post.jsp?num= <%= post.getNum() %>"><%= post.getTitle() %></a>
						</div>
						<div class="post_view">조회수 : <%= post.getVisitCount() %></div>
						<div class="post_comment_count">댓글 : <%= post.getCommentCount() %></div>
						<div class="post_like_count">좋아요 : <%= post.getLikeCount() %></div>
						<div class="post_date_created"><%= post.getPostDate() %></div>
					</div>
			<%
				}
			}
			%>
		</div>
		<div class="post_board_number">
			<div class="page_first_btn">처음</div>
			<div class="page_number_btn">1</div>
			<div class="page_number_btn">2</div>
			<div class="page_number_btn">3</div>
			<div class="page_number_btn">4</div>
			<div class="page_number_btn">5</div>
			<div class="page_number_btn">6</div>
			<div class="page_number_btn">7</div>
			<div class="page_number_btn">8</div>
			<div class="page_number_btn">9</div>
			<div class="page_number_btn">10</div>
			<div class="page_last_btn">마지막</div>
		</div>
		<footer class="footer"> Lorem ipsum dolor sit amet
			consectetur adipisicing elit. Ipsa, tenetur at! Quia necessitatibus
			doloremque voluptas in nulla, qui natus? Quia rerum recusandae omnis
			nisi sit a aliquid harum ipsum cumque. </footer>
	</div>
</body>
</html>
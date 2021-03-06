<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="DB.PostDAO" %>
<%@ page import="DB.PostVO" %>
<%@ page import="utils.BoardPage" %>
<%
	PostDAO postDAO = new PostDAO(application);

	Map<String, Object> param = new HashMap<String, Object>();
	
	String search = request.getParameter("search");
	String searchSort = request.getParameter("searchSort");
	String searchCategory = request.getParameter("searchCategory");
	
	if(search == null) search = "";
	if(searchSort == null) searchSort = "NUM";
	if(searchCategory == null) searchCategory = "all";
	
	param.put("search", search);
	param.put("searchSort", searchSort);
	param.put("searchCategory", searchCategory);
	
	int totalCount = postDAO.selectCount(param);
	
	// 페이지 처리 부분
	// 전체 페이지 수 계산
	int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
	int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
	int totalPage = (int)Math.ceil((double)totalCount / pageSize);	// 페이지 수 = (게시글 전체 개수)/(한 페이지당 보여지는 개수) 반드시 올림해야 됨
	
	// 현재 페이지 확인
	int pageNum = 1;
	String pageTemp = request.getParameter("pageNum");
	if(pageTemp != null && !pageTemp.equals("")) pageNum = Integer.parseInt(pageTemp);
	
	// 목록에 출력할 게시물 범위 계산
	int start = (pageNum - 1) * pageSize + 1;
	int end = pageNum * pageSize;
	param.put("start", start);
	param.put("end", end);
	
	
	Queue<PostVO> postList = postDAO.selectListPage(param);
	
	System.out.println("a : "+ pageContext.getAttribute("a"));
	pageContext.setAttribute("a", search);
	
	
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
	<% if(request.getAttribute("PostEditerErrMsg") != null){%>
	  <script>
	    alert("<%=request.getAttribute("PostEditerErrMsg") %>");
	  </script>
	<%}%>

	<div id="wrap">
		<jsp:include page="./header.jsp"/>

		<div class="content">

			<form method="get" action="PostBoard.jsp">
				<div class="search_bar">
					<div class="back_btn">
						<a href="./Index.jsp">뒤로가기</a>
					</div>
					<div class="search_pane">
						<input class="search" type="text" name="search" value="<%= (search==null)?"":search %>">
						<button type="submit">검색</button>
					</div>
				</div>

				<div class="board_set">
					<div class="seting">
						<div class="category_selected">
							<label for="category_items">카테고리</label> 
							<select name="searchCategory" id="category_items" onchange="this.form.submit()">
								<option value="all">전체</option>
								<option value="질문" <%= searchCategory.equals("질문")?"selected":"" %>>질문</option>
								<option value="에러" <%= searchCategory.equals("에러")?"selected":"" %>>에러</option>
								<option value="자유" <%= searchCategory.equals("자유")?"selected":"" %>>자유</option>
							</select>
						</div>
						<div class="sort_selected">
							<label for="sort_items">정렬</label> 
							<select name="searchSort" id="sort_items" onchange="this.form.submit()">
								<option value="NUM">기본</option>
								<option value="POST_DATE" <%= searchSort.equals("POST_DATE")?"selected":"" %>>최신순</option>
								<option value="LIKE_COUNT" <%= searchSort.equals("LIKE_COUNT")?"selected":"" %>>인기순</option>
								<option value="VISIT_COUNT" <%= searchSort.equals("VISIT_COUNT")?"selected":"" %>>조회순</option>
							</select>
						</div>
					</div>
		
					<div class="post_write">
						<a href="./PostEditer.jsp">새 글 작성하기</a>
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
					int virtualNumber = 0;
					int countNum = 0;
					int likeCount = 0;
					int commentCount = 0;
					
					for(PostVO post : postList){
						virtualNumber = totalCount - (((pageNum - 1) * pageSize) + countNum++);
						
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
					<div class="post_board_number">
					<%= BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, request.getRequestURI(), param) %>
					</div>
				<%
				}
				%>
		</div>
		<jsp:include page="./footer.html"/>
	</div>
</body>
</html>
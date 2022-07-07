<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<header class="header">
			<div class="back_btn">
				<a href="./Index.jsp">뒤로가기</a>
			</div>
			<div class="search_pane">
				<input class="search" type="text">
			</div>
		</header>

		<div class="board_set">
			<div class="seting">
				<div class="category_selected">
					<label for="category_items">카테고리</label> <select name="cat" id="category_items">
						<option value="all">전체</option>
						<option value="quest">질문</option>
						<option value="error">오류&에러</option>
					</select>
				</div>
				<div class="sort_selected">
					<label for="sort_items">정렬</label> <select name="sort"	id="sort_items">
						<option value="latest">최신순</option>
						<option value="popular">인기순</option>
					</select>
				</div>
			</div>

			<div class="post_write">
				<a href="./PostEditer.jsp">글 작성하기</a>
			</div>
		</div>


		<div class="post_board">
			<div class="post_tilte">
				<div class="post_category">카테고리</div>
				<div class="post_title">제목</div>
				<div class="post_view">조회수</div>
				<div class="post_comment_count">댓글</div>
				<div class="post_like_count">좋아요</div>
				<div class="post_date_created">작성일</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./Post.jsp">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./Post.jsp">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./Post.jsp">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./Post.jsp">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./Post.jsp">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./post_page.html">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./post_page.html">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./post_page.html">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./post_page.html">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./post_page.html">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./post_page.html">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./post_page.html">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./post_page.html">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./post_page.html">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./post_page.html">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./post_page.html">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./post_page.html">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./post_page.html">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./post_page.html">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
			<div class="post">
				<div class="post_category">질문</div>
				<div class="post_title">
					<a href="./post_page.html">질문이 없는게 질문이에요</a>
				</div>
				<div class="post_view">조회수 : 1</div>
				<div class="post_comment_count">댓글 : 3</div>
				<div class="post_like_count">좋아요 : 1</div>
				<div class="post_date_created">1일 전</div>
			</div>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/post_editer_page.css">
<link rel="stylesheet" href="./css/public/frame.css">

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- 텍스트 에디터 summernote를 사용하기 위해 js, css파일 연결. -->

<script src="./js/summernote/summernote-lite.js"></script>
<script src="./js/summernote/lang/summernote-ko-KR.js"></script>
<script src="./js/summernote/summernote-start.js"></script>
<link rel="stylesheet" href="./css/summernote/summernote-lite.css">

<title>질문 있습니다!!!</title>
</head>
<body>
	<div id="wrap">
		<header class="header">
			<div class="back_btn">
				<a href="./PostBoard.jsp">뒤로가기</a>
			</div>
			<div class="search_pane"></div>
			<button class="action_btn">글 게시하기</button>
		</header>
		<div class="post_property">
			<div class="post_title">
				<label for="post_title_input">제목</label> 
				<input type="text" name="post_title_input">
			</div>

			<div class="post_category_selected">
				<label for="post_category_items">카테고리</label> 
				<select name="cat" id="post_category_items">
					<option value="none">선택</option>
					<option value="quest">질문</option>
					<option value="error">오류&에러</option>
				</select>
			</div>
		</div>

		<form method="post">
			<textarea id="summernote" name="editordata"></textarea>
		</form>

		<footer class="footer"> Lorem ipsum dolor sit amet
			consectetur adipisicing elit. Ipsa, tenetur at! Quia necessitatibus
			doloremque voluptas in nulla, qui natus? Quia rerum recusandae omnis
			nisi sit a aliquid harum ipsum cumque. </footer>
	</div>
</body>
</html>
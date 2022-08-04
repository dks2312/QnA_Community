<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DB.MemberVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/index.css">
<link rel="stylesheet" href="./css/public/frame.css">
<title>질문 있습니다!!!</title>
</head>
<body>
	<div id="wrap">
		<jsp:include page="./header.jsp"/>
		
		<div class="content">
			<div class="content_menu">
				<div class="btn ">
					<a href="./PostBoard.jsp">게시판</a>
				</div>
				<div class="btn">
					<a href="./Character.jsp">일정</a>
				</div>
				<div class="btn">
				<%if(session.getAttribute("User") == null) {%>
					<a href="./LogIn.jsp">로그인</a>
				<%} else {%>
					<a class="logout_popup_on">로그아웃</a>
				<%} %>
				</div>
			</div>
		</div>
		
		<jsp:include page="./footer.html"/>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.lang.Exception"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/index.css">
<link rel="stylesheet" href="./css/public/frame.css">
<title>질문 있습니다!!!</title>
</head>
<body>
	<%
		Calendar nowTime = Calendar.getInstance();
		Calendar targetTime = Calendar.getInstance();
		try {
			targetTime.setTime((new SimpleDateFormat("yyyy-MM-dd")).parse("2022-10-26"));
			
			//String studentTime = (targetTime.getTime() - nowTime.getTime()) / 1000 / (24*60*60);
		} catch (Exception e) {e.printStackTrace();}
		String studentTime = null;
		String studentDate = null;
	%>

	<div id="wrap">
		<div class="title">
			<div class="title_left">
				<img src="./images/javaTitle.png" alt="QnA">
			</div>
			<div class="title_right">
				<div class="title_day">남은 수업 일수 : <%= studentDate %></div>
				<div class="title_time">남은 수업 시간 : <%= studentTime %></div>
				<% if(session.getAttribute("UserId") != null) {%><div class="title_wellcom"><%= session.getAttribute("UserName") %> 님 어서오세요</div><%} %>
			</div>
		</div>
		<hr>
		<div class="content_menu">
			<div class="btn ">
				<a href="./PostBoard.jsp">게시판</a>
			</div>
			<div class="btn off">
				<a href="#">잡담</a>
			</div>
			<div class="btn">
				<a href="./Character.jsp">일정</a>
			</div>
			<div class="btn">
				<%if(session.getAttribute("UserId") == null) {%>
					<a href="./LogIn.jsp">로그인</a>
				<%} else {%>
					<a href="./LogOutProcess.jsp">로그아웃</a>
				<%} %>
				
			</div>
		</div>
	</div>
</body>
</html>
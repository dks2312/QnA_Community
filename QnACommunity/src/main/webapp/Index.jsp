<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="DB.MemberVO"%>
<%@ page import="DB.BasicDAO"%>
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
		String strDay = "2022-10-26";	// 성그아 웹개발 과정 종료일
	
		String diffStudentTime = null;
		String diffStudentDay = null;
		String diffStudentFoodTime = null;
		
		try {
			Date nowDate = new Date();
			Date studentDayDate = new SimpleDateFormat("yyyy-MM-dd").parse(strDay);
			
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			
			cal.set(Calendar.HOUR_OF_DAY , 18);
			cal.set(Calendar.MINUTE, 20);
			Date studentTimeDate = cal.getTime();
			
			cal.set(Calendar.HOUR_OF_DAY , 13);
			Date studentFoodTimeDate = cal.getTime();
			
			long diffDaySec = (studentDayDate.getTime() - nowDate.getTime()) / 1000;
			long diffTimeSec = (studentTimeDate.getTime() - nowDate.getTime()) / 1000;
			long diffFoodTimeSec = (studentFoodTimeDate.getTime() - nowDate.getTime()) / 1000;
			
			diffStudentDay = ((diffDaySec >= 0) ? (diffDaySec/(24*60*60)+"일 남았습니다") : "과정이 종료되었습니다");		
			diffStudentTime = ((diffTimeSec >= 0) ? ((diffTimeSec/(60*60))%24 +"시 "+ (diffTimeSec/60)%60) +"분 남았습니다" : "수업이 끝났습니다");
			diffStudentFoodTime = ((diffFoodTimeSec >= 0) ? "점심 시간까지 "+ ((diffFoodTimeSec/(60*60))%24 +"시 "+ (diffFoodTimeSec/60)%60) +"분 남았습니다" : "");
		} catch (ParseException e) {
		    e.printStackTrace();
		}   
		
		String userName = "익명의 사용자";
		if(session.getAttribute("User") != null){
			userName = ((MemberVO)session.getAttribute("User")).getNickName();
		}
	%>

	<div id="wrap">
		<div class="title">
			<div class="title_left">
				<img src="./images/javaTitle.png" alt="QnA">
			</div>
			<div class="title_right">
				<div class="foodTime"><%= diffStudentFoodTime %></div>
				<div>남은 수업 일수 : <%= diffStudentDay %></div>
				<div>남은 수업 시간 : <%= diffStudentTime %></div>
				<div><%= userName %> 님 어서오세요</div>
			</div>
		</div>
		<hr>
		<div class="content_menu">
			<div class="btn ">
				<a href="./PostBoard.jsp?search=">게시판</a>
			</div>
			<div class="btn off">
				<a href="#">잡담</a>
			</div>
			<div class="btn">
				<a href="./Character.jsp">일정</a>
			</div>
			<div class="btn">
				<%if(session.getAttribute("User") == null) {%>
					<a href="./LogIn.jsp">로그인</a>
				<%} else {%>
					<a href="./LogOutProcess.jsp">로그아웃</a>
				<%} %>
				
			</div>
		</div>

		<footer class="footer"> Lorem ipsum dolor sit amet
			consectetur adipisicing elit. Ipsa, tenetur at! Quia necessitatibus
			doloremque voluptas in nulla, qui natus? Quia rerum recusandae omnis
			nisi sit a aliquid harum ipsum cumque. 
		</footer>
	</div>
</body>
</html>
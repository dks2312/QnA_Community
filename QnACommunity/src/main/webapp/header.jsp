<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.ParseException"%>
<%@ page import="DB.MemberVO"%>
<%@ page import="DB.BasicDAO"%>
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
		
		diffStudentDay = ((diffDaySec >= 0) ? (diffDaySec/(24*60*60)+"일") : "종료되었습니다");		
		diffStudentTime = ((diffTimeSec >= 0) ? ((diffTimeSec/(60*60))%24 +"시 "+ (diffTimeSec/60)%60) +"분" : "끝났습니다");
		diffStudentFoodTime = ((diffFoodTimeSec >= 0) ? ((diffFoodTimeSec/(60*60))%24 +"시 "+ (diffFoodTimeSec/60)%60) +"분" : "지났습니다");
	} catch (ParseException e) {
	    e.printStackTrace();
	} 
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./css/header.css">
    <script defer src="./js/header.js"></script>
    <title>질문 있습니다!!!</title>
</head>
<body>
	<% 
		if(request.getAttribute("UserErr") != null){
			request.setAttribute("UserErr", null);
	%>
	  <script>
	    /* alert("<%=request.getAttribute("UserErr") %>"); */
	    alert("로그인이 필요한 서비스입니다");
	  </script>
	<%}%>
    <header>
        <a class="logo">
            <img src="./images/javaTitle.png" alt="QnA커뮤니티">
        </a>

        <nav>
            <ul class="main_menu">
                <li class="itme"><a href="Index.jsp">홈</a></li>
                <li class="itme_sub time">시간
                    <ul class="sub_menu">
                        <li><div class="label">남은 수업 일수:</div><div class="value"><%= diffStudentDay %></div></li>
                        <li><div class="label">남은 수업 시간:</div><div class="value"><%= diffStudentTime %></div></li>
                        <li><div class="label">남은 점심 시간:</div><div class="value"><%= diffStudentFoodTime %></div></li>
                    </ul>
                </li>
                <%if(session.getAttribute("User") == null) {%>
					<li class="itme">마이페이지</li>
				<%} else {%>
					<li class="itme_sub">마이페이지
	                    <ul class="sub_menu">
	                        <li><a href="./MyInfo.jsp">회원정보</a></li>
	                        <li><a href="#">작성한 게시글</a></li>
	                        <li><a href="#">작성한 댓글</a></li>
	                        <li><a href="#">좋아요한 게시글</a></li>
	                        <li><a href="#">좋아요한 댓글</a></li>
	                    </ul>
	                </li>
				<%} %>
				
				<%if(session.getAttribute("User") == null) {%>
					<li class="itme_sub login">로그인
						<ul class="sub_menu">
							<li><a href="./LogIn.jsp">로그인</a></li>
							<li><a href="./SignUp.jsp">회원가입</a></li>
						</ul>
					</li>
				<%} else {%>
					<li class="itme_sub"><%= ((MemberVO)session.getAttribute("User")).getNickName()%>
	                    <ul class="sub_menu">
	                        <li><a href="./LogOutProcess.jsp">로그아웃</a></li>
	                    </ul>
	                </li>
				<%} %>
            </ul>
        </nav>
        
        
    </header>
    <hr>
</body>
</html>
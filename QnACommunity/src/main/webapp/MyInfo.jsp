<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DB.MemberVO" %>
<% 
	String state = request.getParameter("state");
	MemberVO user =(MemberVO)session.getAttribute("User");

	if(state != null){
		switch(state){
		case "back":
			System.out.println("뒤로가기");
			break;
		case "change":
			System.out.println("닉넴변경");
			break;
		default:
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/myInfo.css">
<link rel="stylesheet" href="./css/public/frame.css">
<title>질문 있습니다!!!</title>
</head>
<body>
	<div id="wrap">
		<jsp:include page="./header.jsp"/>
		
		<div class="content">
			<div class="mid_content">
				<div class="wellcom"><h1>안녕하세요 <%= user.getNickName() %>님</h1></div>
				
				<table>
					<caption><h2>나의 정보</h2></caption>
					<tr>
						<th class="title">닉네임</th>
						<td class="tb_content"><%= user.getNickName() %></td>
					</tr>
					<tr>
						<th class="title">아이디</th>
						<td class="tb_content"><%= user.getId() %></td>
					</tr>
					<tr>
						<th class="title">비밀번호</th>
						<td class="tb_content"><%= user.getPassword() %></td>
					</tr>
				</table>

				<div class="ok_info"><a href="MyInfo.jsp?state=back">확인</a></div>
				<div class="re_info"><a href="MyInfo.jsp?state=change">닉네임 수정</a></div>
			</div>
		</div>
		
		<jsp:include page="./footer.html"/>
	</div>
</body>
</html>
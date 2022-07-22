<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DB.MemberVO" %>
<% 
	if(session.getAttribute("User") == null){
		request.setAttribute("UserErr", "로그인을 한 후 이용할 수 있습니다");
		request.getRequestDispatcher("Index.jsp").forward(request, response);
		return;
	}

	MemberVO user =(MemberVO)session.getAttribute("User");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/myInfo.css">
<link rel="stylesheet" href="./css/public/frame.css">
<title>질문 있습니다!!!</title>
<script> 

	function reName(){
		var returnName = prompt('변경할 닉네임을 입력해주세요');
		
		var URL = "MyInfoProcess.jsp?changeName="+ returnName;
		location.href = URL;
	}
</script>
</head>
<body>
	<div id="wrap">
		<jsp:include page="./header.jsp"/>
		
		<div class="content">
			<div class="mid_content">
				<div class="wellcom"><h1>안녕하세요 <%= user.getNickName() %>님</h1></div>
				
				<div class="layout_info">
					<div></div>
					<table>
						<caption>나의 정보</caption>
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
					<div>
						<div class="re_info" onclick="reName()">닉네임 수정</div>
					</div>
				</div>

				<div class="ok_info"><a href="Index.jsp">확인</a></div>
				
			</div>
		</div>
		
		<jsp:include page="./footer.html"/>
	</div>
</body>
</html>
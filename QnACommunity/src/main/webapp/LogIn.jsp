<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/index.css">
<link rel="stylesheet" href="./css/public/frame.css">
<link rel="stylesheet" href="./css/login_page.css">
<title>질문 있습니다!!!</title>
</head>
<body>
	<% if(request.getAttribute("LoginErrMsg") != null){%>
	  <script>
	    alert("<%=request.getAttribute("LoginErrMsg") %>");
	  </script>
	<%}%>
	
	<script>
		function validateForm(form){			
			if(!form.id.value){
				alert("아이디를 입력하세요");
				return false;
			}
			
			if(form.pw.value == ""){
				alert("비밀번호를 입력하세요");
				return false;
			}
		}
	</script>
	
	
	<div id="wrap">
		<div class="header">
			<div class="back_btn">
				<a href="./Index.jsp">뒤로가기</a>
			</div>

		</div>

		<h1>로그인</h1>

		<form action="LoginProcess.jsp" method="post" onsubmit="return validateForm(this)">
			<table class="login_table">
				<tr>
					<td class="lab"><label for="id_filed">아이디 : </label></td>
					<td class="inp"><input name="id" id="id_filed" type="text"></td>
				</tr>
				<tr>
					<td class="lab"><label for="pw_filed">비밀번호 : </label></td>
					<td class="inp"><input name="pw" id="pw_filed" type="password"></td>
				</tr>
			</table>

			<div class="submit_btns">
				<button id="login_btn" type="submit">로그인</button>
				<button id="register_btn" type="button" onclick="location.href='./SignUp.jsp'">회원가입</button>
			</div>
		</form>

		<footer class="footer"> Lorem ipsum, dolor sit amet
			consectetur adipisicing elit. Dolores odio debitis dignissimos saepe,
			repudiandae deleniti qui rem? Veniam, ratione! Error cum perspiciatis
			a expedita molestiae repellendus autem ipsam animi magni? </footer>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/index.css">
<link rel="stylesheet" href="./css/public/frame.css">
<link rel="stylesheet" href="./css/sign_up_page.css">
<title>질문 있습니다!!!</title>
</head>
<body>
	<% if(request.getAttribute("SignUpErrMsg") != null){%>
	  <script>
	    alert("<%=request.getAttribute("SignUpErrMsg") %>");
	  </script>
	<%}%>		
	
	<script>
		function validateForm(form){			
			if(!form.id.value){
				alert("아이디를 입력하세요");
				return false;
			}
			
			if(!form.pw.value){
				alert("비밀번호를 입력하세요");
				return false;
			}
			
			if(form.pw.value != form.pw_check.value){
				alert("비밀번호를 다시 확인해주세요");
				return false;
			}
		}
	</script>


	<div id="wrap">
		<jsp:include page="./header.jsp"/>
		
		<div class="content">
			<div class="header">
				<div class="back_btn">
					<a href="./LogIn.jsp">뒤로가기</a>
				</div>
			</div>
	
			<div class="sub_title">
				<h1>회원가입</h1>
			</div>
			
	
			<form action="SignUpProcess.jsp" method="post" onsubmit="return validateForm(this);">
				<button type="button" class="id_check off" id="id_check_helf">중복체크</button>
				<table class="sign_up_table">
					<tr>
						<td class="lab"><label for="id_filed">아이디 : </label></td>
						<td class="inp"><input id="id_filed" name="id" type="text"></td>
					</tr>
					<tr>
						<td class="lab"><label for="pw_filed">비밀번호 : </label></td>
						<td class="inp"><input id="pw_filed" name="pw" type="password"></td>
					</tr>
					<tr>
						<td class="lab"><label for="pw_check_filed">비밀번호 확인 : </label></td>
						<td class="inp"><input id="pw_check_filed" name="pw_check" type="password"></td>
					</tr>
					<tr>
						<td class="lab"><label for="nic_name_filed">닉네임 : </label></td>
						<td class="inp"><input id="nic_name_filed" name="nic_name" type="text"></td>
					</tr>
				</table>
	
				<div class="submit">
					<button type="submit" id="Register">가입하기</button>
				</div>
			</form>
		</div>
		
		<jsp:include page="./footer.html"/>
	</div>
</body>
</html>
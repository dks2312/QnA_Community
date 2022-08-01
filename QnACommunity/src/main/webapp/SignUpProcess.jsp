<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "DB.MemberDAO" %>
<%@ page import = "DB.MemberVO" %>
<%@ page import = "java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String nicName = request.getParameter("nic_name");
		
		// 입력 제한 기능 만들기
		MemberDAO memberDAO = new MemberDAO(application);
		
		
		String msg = memberDAO.validateMamberIPN(id, pw, nicName);
		
		if(msg.length() > 1){
			request.setAttribute("SignUpErrMsg", msg);
			request.getRequestDispatcher("SignUp.jsp").forward(request, response);
			return;
		}
		
		// 회원가입한 정보를 조합(?)
		MemberVO memberVo = new MemberVO(id, pw, nicName);
		MemberVO isUser = memberDAO.selected(id, pw);
		
		
		if(isUser != null ){
			memberDAO.close();
			
			request.setAttribute("SignUpErrMsg", "중복된 아이디가 존재합니다");
			request.getRequestDispatcher("SignUp.jsp").forward(request, response);
			return;
		}
		else{
			memberDAO.insert(memberVo);
			memberDAO.close();
			
			request.setAttribute("LoginMsg", "회원가입 성공");
		}
	%>
	
<script>
	alert("환영합니다!");
	location.href='LogIn.jsp';
</script>
</body>
</html>
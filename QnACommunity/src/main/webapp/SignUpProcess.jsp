<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "DB.MemberDAO" %>
<%@ page import = "DB.MemberVO" %>
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
		
		// 회원가입한 정보를 조합(?)
		MemberVO memberVo = new MemberVO(id, pw, nicName);
		
		// 회원가입한 ID가 이미 존재하는지 검사
		MemberDAO memberDAO = new MemberDAO(application);
		MemberVO isUser = memberDAO.selected(id, pw);
		memberDAO.close();
		
		
		if(isUser == null){
			memberDAO.insert(memberVo);
			response.sendRedirect("LogIn.jsp");
		}
		else{
			request.setAttribute("SignUpErrMsg", "중복된 아이디가 존재합니다.");
			request.getRequestDispatcher("SignUp.jsp").forward(request, response);
		}
	%>
</body>
</html>
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
		Map<String, Integer> map = memberDAO.tableInfo();
		
		String msg = "";
		if(id.getBytes().length > map.get("ID")){
			msg += "아이디가 "+ id.getBytes().length +"Byte로 너무 깁니다 (최대:"+ map.get("ID") +")\\n";
		}
		if(pw.getBytes().length > map.get("PASSWORD")){
			msg += "비밀번호가 "+ pw.getBytes().length +"Byte로 너무 깁니다 (최대:"+ map.get("PASSWORD") +")\\n";
		}
		if(nicName.getBytes().length > map.get("NICK_NAME")){
			msg += "닉네임이 "+ nicName.getBytes().length +"Byte로 너무 깁니다 (최대:"+ map.get("NICK_NAME") +")\\n";
		}
		
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
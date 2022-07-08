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
	<%
		session.invalidate();
		response.sendRedirect("./Index.jsp");
	%>
</body>
</html>
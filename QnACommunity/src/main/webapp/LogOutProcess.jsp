<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String isOut = request.getParameter("isOut");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문 있습니다!!!</title>

</head>
<body>
	<%
		session.invalidate();
		response.sendRedirect("./Index.jsp");
	%>
</body>
</html>
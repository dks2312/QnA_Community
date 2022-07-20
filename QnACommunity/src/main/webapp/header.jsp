<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./css/header.css">
    <script defer src="./js/header.js"></script>
    <title>질문 있습니다!!!</title>
</head>
<body>
    <header>
        <a class="logo">
            <img src="./images/javaTitle.png" alt="QnA커뮤니티">
        </a>

        <nav>
            <ul class="main_menu">
                <div><a href="Index.jsp">홈</a></div>
                <li class="time">시간
                    <ul class="sub_menu">
                        <li><div class="label">남은 수업 일수:</div><div class="value">99일</div></li>
                        <li><div class="label">남은 수업 시간:</div><div class="value">10:10</div></li>
                        <li><div class="label">남은 점심 시간:</div><div class="value">지났습니다</div></li>
                    </ul>
                </li>
                <li>마이페이지
                    <ul class="sub_menu">
                        <li><a href="#">회원정보</a></li>
                        <li><a href="#">작성한 게시글</a></li>
                        <li><a href="#">작성한 댓글</a></li>
                        <li><a href="#">좋아요한 게시글</a></li>
                        <li><a href="#">좋아요한 댓글</a></li>
                    </ul>
                </li>
                <li>로그인
                    <ul class="sub_menu">
                        <li><a href="./LogIn.jsp">로그인</a></li>
                        <li><a href="./SignUp.jsp">회원가입</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <hr>
    </header>
</body>
</html>
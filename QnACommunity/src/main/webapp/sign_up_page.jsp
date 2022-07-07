<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./css/public/frame.css">
    <link rel="stylesheet" href="./css/sign_up_page.css">
      <script type="text/javascript" src="./js/testJS.js"></script>
    <title>질문 있습니다!!!</title>
</head>
<body>
    <div id="wrap">
        <div class="header">
            <div class="back_btn"><a href="./login_page.html">뒤로가기</a></div>
            
        </div>

        <h1>회원가입</h1>
       

        <form action="./sign_up.jsp" method="post">
            <button type="button" class="id_check" id="id_check_helf">중복체크</button>
            <table class="sign_up_table">
                <tr>
                    <td class="lab"><label for="id_filed">아이디 : </label></td>
                    <td class="inp"><input id="id_filed" name="id" type="text"></td>
                </tr>
                <tr>
                    <td class="lab"><label for="pw_filed">비밀번호 : </label></td>
                    <td class="inp"><input id="pw_filed" name="pw" type="password" ></td>
                </tr>
                <tr>
                    <td class="lab"><label for="pw_check_filed">비밀번호 확인 : </label></td>
                    <td class="inp"><input id="pw_check_filed" name="pw_check" type="password"></td>
                </tr>
                <tr>
                    <td class="lab"><label for="nic_name_filed">닉네임 : </label></td>
                    <td class="inp"><input id="nic_name_filed" name="nic_name"  type="text"></td>
                </tr>
            </table>

            <div class="submit">
                <button id="Register">가입하기</button>
            </div>
        </form>

        <footer class="footer">
            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Dolores odio debitis dignissimos saepe, repudiandae deleniti qui rem? Veniam, ratione! Error cum perspiciatis a expedita molestiae repellendus autem ipsam animi magni?
        </footer>
    </div>
</body>
</html>
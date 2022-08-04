<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<html>
<head>
<title>질문 있어요!!!</title>
<script>
var webSocket = new WebSocket("<%= application.getInitParameter("CHAT_ADDR") %>/ChatingServer");
var chatWindow, chatMessage, chatId;
// 채팅창이 열리면 대화창, 메시지 입력창, 대화명 표시란으로 사용할 DOM 객체 저장
window.onload = function() {
    chatWindow = document.getElementById("chatWindow");
    chatMessage = document.getElementById("chatMessage");
    chatId = "<%= request.getParameter("chatId") %>"; 
}
// 메시지 전송
function sendMessage() {
    // 대화창에 표시
    chatWindow.innerHTML += ""
                        +"<div class='my msg'>"
                        +"  <div class='text'>"+ chatMessage.value +"</div>"
                        +"</div>";
    webSocket.send(chatId + '|' + chatMessage.value);  // 서버로 전송
    chatMessage.value = "";  // 메시지 입력창 내용 지우기
    chatWindow.scrollTop = chatWindow.scrollHeight;  // 대화창 스크롤
}
// 서버와의 연결 종료
function disconnect() {
    webSocket.close();
}
// 엔터 키 입력 처리
function enterKey() {
    if (window.event.keyCode == 13) {  // 13은 'Enter' 키의 코드값
        sendMessage();
    }
}
// 웹소켓 서버에 연결됐을 때 실행
webSocket.onopen = function(event) {   
    chatWindow.innerHTML += "웹소켓 서버에 연결되었습니다.<br/>";
};
// 웹소켓이 닫혔을 때(서버와의 연결이 끊겼을 때) 실행
webSocket.onclose = function(event) {
    chatWindow.innerHTML += "웹소켓 서버가 종료되었습니다.<br/>";
};
// 에러 발생 시 실행
webSocket.onerror = function(event) { 
    alert(event.data);
    chatWindow.innerHTML += "채팅 중 에러가 발생하였습니다.<br/>";
}; 
// 메시지를 받았을 때 실행
webSocket.onmessage = function(event) { 
    var message = event.data.split("|");  // 대화명과 메시지 분리
    var sender = message[0];   // 보낸 사람의 대화명
    var content = message[1];  // 메시지 내용
    if (content != "") {
        chatWindow.innerHTML += ""
                            +"<div class='msg'>"
                            +"  <div class='name'>"+ sender +"</div>"
                            +"  <div class='text'>"+ content +"</div>"
                            +"</div>";
    }
    chatWindow.scrollTop = chatWindow.scrollHeight; 
};
</script>
<style>  /* 대화창 스타일 지정 */
#warp{
    width:500px; 
    height:600px; 
}
#closeBtn{
    margin-bottom:3px; 
    position:relative; 
    top:2px; 
    left:-2px;
}
#chatWindow{
    border:1px solid black; 
    width:90%; 
    height:500px; 
    overflow:scroll; 
    padding:5px;
    background-color: #b5e2ff; 
    display: flex;
    flex-direction: column;
    align-items: center;
}
#inputFiled{
    width: 90%; 
    height:30px; 
    display: grid; 
    grid-template-columns: 1fr 100px
}
#sendBtn{
    height:100%; 
    position:relative; 
    top:2px; 
    left:-2px;
}
#chatMessage{height:100%}
.my{text-align: right;}
.my .text{float:right;}
.msg{width: 100%; margin: 5;}
.name{font-size: 15px;}
.text{width:300px; background-color: yellow; padding: 2px;}
</style>
</head>

<body>  <!-- 대화창 UI 구조 정의 --> 
	<div id="warp">
	    <button id="closeBtn" onclick="disconnect();">채팅 종료</button>
	    <div id="chatWindow"></div>
	    <div id="inputFiled">
	        <input type="text" id="chatMessage" onkeyup="enterKey();">
	        <button id="sendBtn" onclick="sendMessage();">전송</button>
	    </div>  
    </div>  
</body>
</html>
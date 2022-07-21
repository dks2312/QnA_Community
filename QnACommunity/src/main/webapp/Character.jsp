<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/public/frame.css">
<title>질문 있습니다!!!</title>

<link href='fullcalendar/main.css' rel='stylesheet' />
<script src='fullcalendar/main.js'></script>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
    //   initialView: 'dayGridMonth'
		events:[
			{
					title : '데이터 입력 시작',
					start : '2022-07-21',
					allDay : true
			},
			{
				title : '휴강',
				start : '2022-08-12',
				allDay : true
			},
			{
				title : '휴강',
				start : '2022-08-15',
				allDay : true
			},
			{
				title : '휴강',
				start : '2022-09-09',
				allDay : true
			},
			{
				title : '휴강',
				start : '2022-09-12',
				allDay : true
			},
			{
				title : '휴강',
				start : '2022-10-03',
				allDay : true
			},
			{
				title : '휴강',
				start : '2022-10-07',
				allDay : true
			},
			{
				title : '휴강',
				start : '2022-10-10',
				allDay : true
			}
		]
    });
    
    calendar.render();
  });
</script>
</head>
<body>
	<div id="wrap">
		<jsp:include page="./header.jsp"/>
		
		<div class="content">
			<div class="header">
				<div class="back_btn">
					<a href="./Index.jsp">뒤로가기</a>
					<span style="color: red; font-size: 10px; opacity: 0.5;"> 정확하지 않을 수 있습니다</span>
				</div>
			</div>
	
			<div id="calendar"></div>
	
			<a href="https://www.hrd.go.kr/hrdp/co/pcobo/PCOBO0100P.do?tracseId=AIG20210000336819&tracseTme=1&crseTracseSe=C0061&trainstCstmrId=#" 
				style="
					width: 300px;
					height: 50px;
					text-align: center;
					line-height: 50px;
					border: 1px solid black; 
					border-radius: 10px;
					background-color: black;
					color: white;
					position: absolute;
					bottom: 100px;
					right: 50px;
					z-index: 999999;
				">
				성남 컴퓨터그린아카데미 더 자세히보기
			</a>
			
			<jsp:include page="./footer.html"/>
		</div>
	</div>
</body>
</html>
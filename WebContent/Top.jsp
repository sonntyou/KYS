<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,model.ReservContents" %>
<!DOCTYPE html>
<html  lang="ja">
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.1/jquery.min.js"></script>
<title>会議室予約システム</title>
<link href="test.css" rel="stylesheet" type="text/css">

<style type="text/css">
.reserv {display: block;}

</style>




</head>
<body>
<div class="sitebody">
<%
	int width = 150;
	int leftref = 434;
	int topref= 157;
List<ReservContents> reservlist = (List<ReservContents>)session.getAttribute("reservlist");
int size =reservlist.size();
%>

<div class="reservform">
<h3>予約フォーム</h3>

<form action ="/KYS/TopCtrl" method ="post">
	タイトル：<input type="text" name="title" placeholder="無題" value="無題"><br>
	予約場所：<select name="locate" size="1">
				<option value="1">会議室</option>
				<option value="2">応接室</option>
				<option value="3">リフレッシュルーム</option>
			</select><br>
開始時間：
<select name="year">
${timechoices.yearchoices}
</select> 年
<select name="month">
${timechoices.monthchoices }
</select> 月
<select name="day">
${timechoices.daychoices }
</select> 日
<select name="shour">
${timechoices.hourchoices }
</select> 時
<select name="sminute">
${timechoices.minutechoices}
</select> 分<br>

終了時間：
<select name="fhour">
${timechoices.hourchoices}
</select> 時
<select name="fminute">
${timechoices.minutechoices}
</select> 分<br>

メールアドレス1：<input type="text" name="mail1" value=""required>@level-five.jp<br>
メールアドレス2：<input type="text" name="mail2" value="">@level-five.jp<br>
メールアドレス3:<input type="text" name="mail3" value="">@level-five.jp<br>

<input type="submit" value="予約">
</form>
</div>


<div class="dayreserv">
	<p>${today.today}の予定</p>

		<table class="table" border="1">
			<tr>
 				<th class="timetitle" id="time"></th>
 				<th class="memori0"> </th>
 				<th width=<%=width%>>会議室</th>
 				<th width =<%=width%>>応接室</th>
 				<th width =<%=width%>>リフレッシュ</th>
			</tr>
			<tr>
 				<td class="time"rowspan="2">9:00</td>
 				<td class="memori"></td>
 				<td rowspan="26"></td>
 				<td rowspan="26"></td>
 				<td rowspan="26"></td>
			</tr>
			<tr><td class="memori0"></td></tr>
			<tr>
 				<td class="time" rowspan="2">10:00</td>
			<td class="memori"></td>
			</tr>
			<tr><td class="memori0"></td></tr>
			<tr>
 				<td class="time" rowspan="2">11:00</td>
			<td class="memori"></td>
			</tr>
			<tr><td class="memori0"></td></tr>
			<tr>
 				<td class="time" rowspan="2">12:00</td>
			<td class="memori"></td>
			</tr>
			<tr><td class="memori0"></td></tr>
			<tr>
 				<td class="time" rowspan="2">13:00</td>
			<td class="memori"></td>
			</tr>
			<tr><td class="memori0"></td></tr>
			<tr>
 				<td class="time" rowspan="2">14:00</td>
			<td class="memori"></td>
			</tr>
			<tr><td class="memori0"></td></tr>
			<tr>
 				<td class="time" rowspan="2">15:00</td>
			<td class="memori"></td>
			</tr>
			<tr><td class="memori0"></td></tr>
			<tr>
 				<td class="time" rowspan="2">16:00</td>
			<td class="memori"></td>
			</tr>
			<tr><td class="memori0"></td></tr>
			<tr>
 				<td class="time" rowspan="2">17:00</td>
			<td class="memori"></td>
			</tr>
			<tr><td class="memori0"></td></tr>
			<tr>
 				<td class="time" rowspan="2">18:00</td>
			<td class="memori"></td>
			</tr>
			<tr><td class="memori0"></td></tr>
			<tr>
 				<td class="time" rowspan="2">19:00</td>
			<td class="memori"></td>
			</tr>
			<tr><td class="memori0"></td></tr>
			<tr>
 				<td class="time" rowspan="2">20:00</td>
			<td class="memori"></td>
			</tr>
			<tr><td class="memori0"></td></tr>
			<tr>
 				<td class="time" rowspan="2">21:00</td>
 			<td class="memori"></td>
 			</tr>
 			<tr><td class="memori0"></td></tr>

</table>
</div>
<div class="calender">
${calendar.calendar}
</div>

<div>
<%for(int i = 0;i<size;i++) {
String title = reservlist.get(i).getTitle();
String sttime = reservlist.get(i).getSttime();
String endtime = reservlist.get(i).getEndtime();
int resourceid = reservlist.get(i).getResourceid();
//始まりの時間と分
String convshour=sttime.substring(11,13);
String convsminute=sttime.substring(14,16);
//終わりの時間と分
String convfhour=endtime.substring(11,13);
String convfminute=endtime.substring(14,16);
//始まりの時間と分をint型に
int intshour= Integer.parseInt(convshour);
int intsminute= Integer.parseInt(convsminute);
//終わりのの時間と分をint型に
int intfhour= Integer.parseInt(convfhour);
int intfminute= Integer.parseInt(convfminute);
//左端の値
int leftside = (resourceid - 1) * 150+leftref;
//上端と下端の値
int px=120;
int topside = (intshour-9)*px + intsminute*px/60+topref;
int height = ((intfhour*60+intfminute)-(intshour*60+intsminute))*px/60;
%>


	<a class="reserv" href="#" style="position:absolute; left:<%=leftside%>px; top:<%=topside%>px; height:<%=height%>px; width:<%=width%>px;">
		<%=title %>
		<span class="fukidasipop">
			場所：応接室<br>
			時間：9:00～10:00<br>
			予約者：飯野、猪野、田中<br>
			<br>
			削除パスワード:<br>
			<input type="password" name="deletepass">
			<input type="submit" value="削除">
		</span>
	</a>


<%} %>

</div>
  </div>
</body>
</html>
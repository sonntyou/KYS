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
	int leftref = 436;
	int topref= 157;
List<ReservContents> reservlist = (List<ReservContents>)session.getAttribute("reservlist");
int size =reservlist.size();
%>

<div class="reservform">
<h3>予約フォーム</h3>

<form action ="/KYS/ReservCtrl" method ="post">
	タイトル：<input type="text" name="title" placeholder="無題"><br>
	予約場所：<select name="resourceid" size="1">
				<option value="1">会議室</option>
				<option value="2">応接室</option>
				<option value="3">リフレッシュルーム</option>
			</select><br>
開始時間：
<select name="styear">
${timechoices.yearchoices}
</select> 年
<select name="stmonth">
${timechoices.monthchoices }
</select> 月
<select name="stday">
${timechoices.daychoices }
</select> 日
<select name="sthour">
${timechoices.hourchoices }
</select> 時
<select name="stminute">
${timechoices.minutechoices}
</select> 分<br>

終了時間：
<select name="endyear">
${timechoices.yearchoices}
</select> 年
<select name="endmonth">
${timechoices.monthchoices }
</select> 月
<select name="endday">
${timechoices.daychoices }
</select> 日
<select name="endhour">
${timechoices.hourchoices}
</select> 時
<select name="endminute">
${timechoices.minutechoices}
</select> 分<br>


	<ol id="mail_list">
		<li><input type="text" name="mail_0" required>@level-five.jp</li>
	</ol>
	<input type="button" value="アドレス追加" id="btn_add">

		<!-- JavaScript -->
		<script src="http://code.jquery.com/jquery.min.js"></script>
		<script src="script.js"></script>

<input type="submit" value="予約">
</form>
</div>


<div class="dayreserv">
	<p>${selectdatetime.year}年${selectdatetime.month}月${selectdatetime.day}日の予定</p>

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
String sttime = reservlist.get(i).getStdatetime();
String endtime = reservlist.get(i).getEnddatetime();
String resource = reservlist.get(i).getResource();

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
			場所：<%=resource%><br>
			時間：<%=convshour%>:<%=convsminute%>～<%=convfhour%>:<%=convfminute%><br>
			予約者：

			<%
			String reserver ="";
			for(int j = 0; j < reservlist.get(i).getReserverlist().size();j++){
			%>
				<%=reservlist.get(i).getReserverlist().get(j) %>
			<%}%>
			<br>
			<br>
			mail:<br>
			<form action="/KYS/DeleteCtrl" method="Post">
			<input type="text" name="mail" required>@level-five.jp
			<input type="submit" value="削除" onClick="return confirm('本当に削除しますか？')">
			</form>
		</span>
	</a>


<%} %>

</div>
  </div>
</body>
</html>
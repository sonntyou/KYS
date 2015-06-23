<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Judge"; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<%HttpSession session = request.getSession();%>
<%Judge judge=new Judge(); %>
<%Judge judge=(Judge)session.getAttribute(""); %>
<% if(judge=0){ %>
予期せぬエラーです。
<%}else if(judge==1){ %>
エラーはありません。
<%}else if(judge==2){ %>
メールアドレスが不正です。
<%}else if(judge==3){%>
名前が空欄です
<%}else if(judge==4){%>
予約が重複しています。他の時間か場所を指定してください。
<%}else if(judge==5){%>
予約が完了しました。
<%}else if(judge==6){ %>
削除が完了しました
<%}else if(judge==7){%>
予約時間が不正です。
<%}else if(judge==8){ %>
INSERTが正しく行われませんでした。
<%}else if(judge==9){ %>
SQLExceptionがキャッチされました。
<%}else if(judge==10){ %>
ClassNotFoundExceptionがキャッチされました。
<%}else if(judge==11){ %>
データベースが正しく閉じられませんでした。
<%}else if(judge==12){ %>
削除が行われませんでした。
<%}; %>


</body>
</html>
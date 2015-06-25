<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Judge" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<%%>
<%Judge judge=new Judge(); %>
<%judge=(Judge)session.getAttribute("judge"); %>
<% if(judge.getJudge()==0){ %>
正常です。
<%}else if(judge.getJudge()==1){ %>
エラーはありません。
<%}else if(judge.getJudge()==2){ %>
メールアドレスが不正です。
<%}else if(judge.getJudge()==3){%>
名前が空欄です
<%}else if(judge.getJudge()==4){%>
予約が重複しています。他の時間か場所を指定してください。
<%}else if(judge.getJudge()==5){%>
予約が完了しました。
<%}else if(judge.getJudge()==6){ %>
削除が完了しました
<%}else if(judge.getJudge()==7){%>
予約時間が不正です。
<%}else if(judge.getJudge()==8){ %>
INSERTが正しく行われませんでした。
<%}else if(judge.getJudge()==9){ %>
SQLExceptionがキャッチされました。
<%}else if(judge.getJudge()==10){ %>
ClassNotFoundExceptionがキャッチされました。
<%}else if(judge.getJudge()==11){ %>
データベースが正しく閉じられませんでした。
<%}else if(judge.getJudge()==12){ %>
削除が行われませんでした。
<%}else if(judge.getJudge()==13){ %>
登録されていない方が入力されました。管理者に問い合わせてください。
<%}else if(judge.getJudge()==14){ %>
予約者本人のメールアドレスを入力してください。
<%}else if(judge.getJudge()==15){ %>
内部的なエラーが発生しました。管理者に問い合わせてください。
<%}else if(judge.getJudge()==16){ %>
過去の時間帯が選択されました。
<%}; %>


</body>
</html>
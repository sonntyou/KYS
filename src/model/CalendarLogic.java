
package model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CalendarLogic {




    private String calendar;
    private final String stylesheet=
            "";



    private final String[] youbi = {"SUN", "MON","TUE","WED","THU","FRI","SAT"};



    //formに関する文字列
    private String hidden="hidden";
    private final String endform = "</form>";
    private final String endtable = "</table>";
    private final String tr = "<tr>";
    private final String endtr = "</tr>";
    private final String th = "<th>";
    private final String endth = "</th>";
    private final String td = "<td>";
    private final String endtd = "</td>";





    public CalendarLogic(){

        //現在の日付の取得
        String currentdate = LocalDate.now().toString();
//        System.out.println(currentdate);

        //年月日を切り取りint型に変換
        String currentyear = currentdate.substring(0,4);
        String currentmonth = currentdate.substring(5,7);
        String currentday = currentdate.substring(8,10);

        int intcurrentyear= Integer.parseInt(currentyear);
        int intcurrentmonth= Integer.parseInt(currentmonth);
        int intcurrentday= Integer.parseInt(currentday);

        //月の最終日を取得し、int型に変換
        LocalDate localdate = LocalDate.of(intcurrentyear,intcurrentmonth,intcurrentday);
        YearMonth ym = YearMonth.from(localdate);
        LocalDate lastdate = ym.atEndOfMonth();
        String Stringlastdate = lastdate.format(DateTimeFormatter.ofPattern("dd"));
        int intlastdate = Integer.parseInt(Stringlastdate);

        //月の1日の曜日を取得
        LocalDate firstlocaldate = LocalDate.of(intcurrentyear, intcurrentmonth, 1);
        String firstdayofweek = firstlocaldate.getDayOfWeek().toString();

        //月の最終日の曜日を取得
        LocalDate lastlocaldate = LocalDate.of(intcurrentyear, intcurrentmonth, intlastdate);
        String lastdayofweek = lastlocaldate.getDayOfWeek().toString();

        //曜日を数字に変換
        DayOfWeekToInt dayofweektoint = new DayOfWeekToInt();
        int intfirstdayofweek = dayofweektoint.getIntDayOfWeek(firstdayofweek);
        int intlastdayofweek = dayofweektoint.getIntDayOfWeek(lastdayofweek);


        /*
         * ボタンや曜日の部分のコードの作成
         */

        //inputボタンの作成
        String leftbuttonform=getLeftButtonForm(currentyear,currentmonth);
        String rightbuttonform=getRightButtonForm(currentyear,currentmonth);

        String nengappi = currentyear+"年"+currentmonth+"月";


        List<String> daylist = new ArrayList<String>();

        //1日の曜日におおじて半角スペースを挿入していく
        for(int i =1; i<intfirstdayofweek;i++){
            daylist.add(" ");
        }

        //月の日数分だけdaybuttonformを入れていく
        for(int i = 1 ; i <= intlastdate; i++){
            String day;
            if(i < 10){
                day ="0"+String.valueOf(i);
            }else{
                day =String.valueOf(i);
            }
            String daybutton = getDayButtonForm(currentyear,currentmonth,day,i);
            daylist.add(daybutton);
        }

        //最終日の曜日におおじて半角スペースを挿入していく
        for(int i =0; i<(7-intlastdayofweek);i++){
            daylist.add(" ");
        }

        //カレンダーテーブルを作成

        calendar=getTable()+tr;
        calendar += th + leftbuttonform +endth;
        calendar += "<th colspan=\"5\">"+nengappi + endth;
        calendar += th + rightbuttonform + endth;
        calendar += endtr;

        for(int i = 1; i <= youbi.length;i++){
            if(i%7==1){
                calendar += tr;
            }
            calendar += th + youbi[i-1] + endth;
            if(i%7==0){
                calendar += endtr;
            }

        }

        for(int i = 1; i <= daylist.size();i++){
            if(i%7==1){
                calendar += tr;
            }
            calendar += td + daylist.get(i-1) + endtd;
            if(i%7==0){
            calendar += endtr;
            }
        }
        calendar += endtable;

    }

    public CalendarLogic(String currentyear, String currentmonth ,String prenext){

        int intcurrentyear= Integer.parseInt(currentyear);
        int intcurrentmonth= Integer.parseInt(currentmonth);
        int intcurrentday= 1;

        if(prenext.equals("previous")){
            if(intcurrentmonth == 1){
                intcurrentyear += -1;
                intcurrentmonth = 12;
            }else{
                intcurrentmonth += -1;
            }
        }else if (prenext.equals("next")){
            if(intcurrentmonth == 12){
                intcurrentyear += 1;
                intcurrentmonth =1;
            }else{
                intcurrentmonth += 1;
            }
        }

        currentyear = String.valueOf(intcurrentyear);

        if(intcurrentmonth < 10){
            currentmonth ="0"+String.valueOf(intcurrentmonth);
        }else{
            currentmonth =String.valueOf(intcurrentmonth);
        }



        //月の最終日を取得し、int型に変換
        LocalDate localdate = LocalDate.of(intcurrentyear,intcurrentmonth,intcurrentday);
        YearMonth ym = YearMonth.from(localdate);
        LocalDate lastdate = ym.atEndOfMonth();
        String Stringlastdate = lastdate.format(DateTimeFormatter.ofPattern("dd"));
        int intlastdate = Integer.parseInt(Stringlastdate);

        //月の1日の曜日を取得
        LocalDate firstlocaldate = LocalDate.of(intcurrentyear, intcurrentmonth, 1);
        String firstdayofweek = firstlocaldate.getDayOfWeek().toString();

        //月の最終日の曜日を取得
        LocalDate lastlocaldate = LocalDate.of(intcurrentyear, intcurrentmonth, intlastdate);
        String lastdayofweek = lastlocaldate.getDayOfWeek().toString();

        //曜日を数字に変換
        DayOfWeekToInt dayofweektoint = new DayOfWeekToInt();
        int intfirstdayofweek = dayofweektoint.getIntDayOfWeek(firstdayofweek);
        int intlastdayofweek = dayofweektoint.getIntDayOfWeek(lastdayofweek);


        /*
         * ボタンや曜日の部分のコードの作成
         */

        //inputボタンの作成
        String leftbuttonform=getLeftButtonForm(currentyear,currentmonth);
        String rightbuttonform=getRightButtonForm(currentyear,currentmonth);

        String nengappi = currentyear+"年"+currentmonth+"月";


        List<String> daylist = new ArrayList<String>();

        //1日の曜日におおじて半角スペースを挿入していく
        for(int i =1; i<intfirstdayofweek;i++){
            daylist.add(" ");
        }

        //月の日数分だけdaybuttonformを入れていく
        for(int i = 1 ; i <= intlastdate; i++){
            String day;
            if(i < 10){
                day ="0"+String.valueOf(i);
            }else{
                day =String.valueOf(i);
            }
            String daybutton = getDayButtonForm(currentyear,currentmonth,day,i);
            daylist.add(daybutton);
        }

        //最終日の曜日におおじて半角スペースを挿入していく
        for(int i =0; i<(7-intlastdayofweek);i++){
            daylist.add(" ");
        }

        //カレンダーテーブルを作成

        calendar=getTable()+tr;
        calendar += th + leftbuttonform +endth;
        calendar += "<th colspan=\"5\">"+nengappi + endth;
        calendar += th + rightbuttonform + endth;
        calendar += endtr;

        for(int i = 1; i <= youbi.length;i++){
            if(i%7==1){
                calendar += tr;
            }
            calendar += th + youbi[i-1] + endth;
            if(i%7==0){
                calendar += endtr;
            }

        }

        for(int i = 1; i <= daylist.size();i++){
            if(i%7==1){
                calendar += tr;
            }
            calendar += td + daylist.get(i-1) + endtd;
            if(i%7==0){
            calendar += endtr;
            }
        }
        calendar += endtable;

    }


    //ゲッター
    //EL式で呼び出すためには、呼び出したい変数の頭文字だけしか大文字にはしてはいけない。
    public String getCalendar(){
        return calendar;
    }

    public String getStylesheet(){
        return stylesheet;
    }


    //フォーム作成のためのメソッドたち
    private String getSubmit(String id,String value){
        return "<input id=\""+id+"\" type=\"submit\" value=\""+value+"\">";
    }

    private String getSubmit(String id,int value){
        return "<input id=\""+id+"\" type=\"submit\" value=\""+value+"\">";
    }

    //inputフォームを返すメソッド
    private String getInput(String id,String type, String name, String value ){
        return "<input id=\""+id+"\" type=\""+type+"\" name=\""+name+"\" value=\""+value+"\">";
    }

    private String getForm(String destination){
        return "<form action ="+destination+" method =\"post\">";
    }
    private String getTable(){
        return "<table id=\"calendartable\" border=\"1\">";
    }

    //前の月に戻るボタンのフォームを返すメソッド
    private String getLeftButtonForm(String currentyear, String currentmonth){
        String destination = getMainDestination();
        String form =getForm(destination);
        String idleft = "left",valueleft = "前" ;

        String yearhidden = getInput("",hidden,"year",currentyear);
        String monthhidden = getInput("",hidden,"month",currentmonth);
        String lefthidden = getInput("",hidden,"prenext","previous");
        String leftbutton = getSubmit(idleft,valueleft);

        return form+lefthidden+yearhidden+monthhidden+leftbutton+endform;
    }

    //次の月に進むボタンのフォームを返すメソッド
    private String getRightButtonForm(String currentyear, String currentmonth){
        String destination = getMainDestination();
        String form =getForm(destination);

        String yearhidden = getInput("",hidden,"year",currentyear);
        String monthhidden = getInput("",hidden,"month",currentmonth);
        String righthidden = getInput("",hidden,"prenext","next");
        String rightbutton = getSubmit("right","次");

        return form+righthidden+yearhidden+monthhidden+rightbutton+endform;
    }

    //一日ごとのボタンを返すメソッド
    private String getDayButtonForm(String currentyear, String currentmonth, String day,int intday){
        String destination = getDayDestination();
        String form =getForm(destination);

        String yearhidden = getInput("",hidden,"year",currentyear);
        String monthhidden = getInput("",hidden,"month",currentmonth);
        String dayhidden = getInput("",hidden,"day",day);

        String daybutton = getSubmit("day",intday);

        return form+yearhidden+monthhidden+dayhidden+daybutton+endform;

    }

    private String getMainDestination(){
        return "/KYS/MainCtrl";
    }

    private String getDayDestination(){
        return "/KYS/DayCtrl";
    }
}

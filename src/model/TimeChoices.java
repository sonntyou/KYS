package model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class TimeChoices {

    private String minutechoices,hourchoices,daychoices,monthchoices,yearchoices;


    public TimeChoices(Today todaytime){
        /*
        Mapは参照型
        以下の処理に加えて、Today.javaから現在の年月日を取得し、todayに代入。
    */
        String today=todaytime.getToday()+" "+todaytime.getTodayTime();

    //substring(インデックス,何文字目か)
    int year= Integer.parseInt(today.substring(0,4));
    int month= Integer.parseInt(today.substring(5,7));
    int day= Integer.parseInt(today.substring(8,10));
    int chkday =day;    //月の処理のところでどうしても必要なのでやむを得なく追加
    int hour= Integer.parseInt(today.substring(11,13));
    int minute= Integer.parseInt(today.substring(14,16));

    //プルダウンメニューに選択肢を表示するために必要な文字列
    String optionst = "<option value=";
    String optionend ="</option>";

    //現在の分を初期選択肢にするための処理
    Map<Integer,String> minutemap = new HashMap<Integer, String>();
    for(int i = 0; i<=45;i=i+15){
        String mchoice;
        if(i<10){
        mchoice= optionst + "\"0"+i+"\">"+i+optionend;
        }else{
        mchoice= optionst + "\""+i+"\">"+i+optionend;
        }
        minutemap.put(i,mchoice);
    }
    if((hour >= 21 && minute >0)|| hour < 9 ){
        minute = 0;
    }else if(minute==0){
        //そのまま
    }else if(minute <= 15){
        minute = 15;
    }else if(minute <= 30){
        minute = 30;
    }else if(minute <=45){
        minute = 45;
    }else if(minute >45){
        //45分を超えていたら時間を繰り上げる。
        minute =0;
        hour++;
    }
    String selectedminute;
    if(minute<10){
        selectedminute = optionst + "\"0"+minute+"\" selected>"+minute+optionend;
    }else{
        selectedminute = optionst + "\""+minute+"\" selected>"+minute+optionend;
    }
    minutemap.put(minute, selectedminute);

    String minutechoices = "";
    for(int i = 0; i <=45; i=i+15){
        minutechoices += minutemap.get(i);
    }

    this.minutechoices=minutechoices;

    /*  現在の時間を初期選択肢にするための処理
        分の繰り上がり処理を行ってから時間の処理をするべきだから
        分の後に処理をする必要がある。
    */
    Map<Integer,String> hourmap = new HashMap<Integer, String>();
    for(int i = 9; i<=21;i++){
         String hchoice;
        if(i<10){
            hchoice=optionst + "\"0"+i+"\">"+i+optionend;
        }else{
            hchoice=optionst + "\""+i+"\">"+i+optionend;
        }
        hourmap.put(i,hchoice);
    }
    if(hour <=9){
        hour=9;
    }else if(hour >= 21){
        hour=9;
        chkday++;
    }
    String selectedhour;
    if(hour<10){
        selectedhour = optionst + "\"0"+ hour +"\" selected>"+ hour +optionend;
    }else{
        selectedhour = optionst + "\""+ hour +"\" selected>"+ hour +optionend;
    }
    hourmap.put(hour, selectedhour);
    String hourchoices = "";
    for(int i = 9; i <=21; i++){
        hourchoices += hourmap.get(i);
    }

    this.hourchoices=hourchoices;

    //現在の日を初期選択肢にするための処理
        //アクセスした月の最終日(うるう年考慮)をintlastDateに格納
    LocalDate localdate = LocalDate.of(year,month,day);
    YearMonth ym = YearMonth.from(localdate);
    LocalDate lastDate = ym.atEndOfMonth();
    String StringlastDate = lastDate.format(DateTimeFormatter.ofPattern("dd"));
    int intlastDate = Integer.parseInt(StringlastDate);
    /*
        たとえば、2015年6月30日22:00にアクセスしたとすると
        直前の時間の処理でhourは9:00になり、chkdayが+1されている。
        そうすると6月31日になるわけだが、それは存在しない。
        存在しない年月日をLocalDateに入れるとエラーを吐くので、
        そのために、dayとは別にchkdayを設けた。
        存在しない場合は、monthを+1し、dayを1にする。
        プルダウンメニューに欲しい項目は、新しい月の日数なので
        ここで、またintlastDateを再評価する。
    */
    if(chkday > intlastDate ){
        day=1;
        if(month+1 >12){    //学習ポイント！前置++と後置++と+1の違い
            year++;
            month=1;
        }else{
            month++;
        }
        localdate = LocalDate.of(year,month,day);
        ym = YearMonth.from(localdate);
        lastDate = ym.atEndOfMonth();
        StringlastDate = lastDate.format(DateTimeFormatter.ofPattern("dd"));
        intlastDate = Integer.parseInt(StringlastDate);
    }else{
        day=chkday;
    }
    Map<Integer,String> daymap = new HashMap<Integer, String>();
    for(int i = 1; i<=intlastDate;i++){
         String dchoice;
        if(i<10){
            dchoice=optionst + "\"0"+i+"\">"+i+optionend;
        }else{
            dchoice=optionst + "\""+i+"\">"+i+optionend;
        }
        daymap.put(i,dchoice);
    }
    String selectedday;
    if(day<10){
        selectedday = optionst + "\"0"+ day +"\" selected>"+ day + optionend;
    }else{
        selectedday = optionst + "\""+ day +"\" selected>"+ day + optionend;
    }
    daymap.put(day, selectedday);

    String daychoices = "";
    for(int i = 1; i <=intlastDate; i++){
        daychoices += daymap.get(i);
    }

    this.daychoices=daychoices;

    //現在の月を初期選択肢にするための処理
    Map<Integer,String> monthmap = new HashMap<Integer, String>();
    for(int i = 1; i<=12;i++){
        String monchoice;
        if(i<10){
            monchoice=optionst + "\"0"+i+"\">"+i+optionend;
        }else{
            monchoice=optionst + "\""+i+"\">"+i+optionend;
        }
        monthmap.put(i,monchoice);
    }
    String selectedmonth;
    if(month<10){
        selectedmonth = optionst + "\"0"+ month +"\" selected>"+ month + optionend;
    }else{
        selectedmonth = optionst + "\""+ month +"\" selected>"+ month + optionend;
    }
    monthmap.put(month, selectedmonth);

    String monthchoices = "";
    for(int i = 1; i <=12; i++){
        monthchoices += monthmap.get(i);
    }

    this.monthchoices=monthchoices;

    //現在の年を初期選択肢にするための処理
    Map<Integer,String> yearmap = new HashMap<Integer, String>();
    for(int i = year; i<=(year+10);i++){
        String ychoice=optionst + "\""+i+"\">"+i+optionend;
        yearmap.put(i,ychoice);
    }
    String selectedyear = optionst + "\""+year+"\" selected>"+year+optionend;
    yearmap.put(year, selectedyear);

    String yearchoices = "";
    for(int i = year; i <=(year+10); i++){
        yearchoices += yearmap.get(i);
    }

    this.yearchoices=yearchoices;

    }


    public String getMinutechoices() {
        return minutechoices;
    }


    public String getHourchoices() {
        return hourchoices;
    }


    public String getDaychoices() {
        return daychoices;
    }


    public String getMonthchoices() {
        return monthchoices;
    }


    public String getYearchoices() {
        return yearchoices;
    }
}

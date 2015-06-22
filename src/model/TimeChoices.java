package model;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class TimeChoices {

    private String intminutechoices,inthourchoices,intdaychoices,intmonthchoices,intyearchoices;


    public TimeChoices(SelectDateTime selecttime){
        /*
        Mapは参照型
        以下の処理に加えて、Tointday.javaから現在の年月日を取得し、tointdayに代入。
    */

        String year = selecttime.getYear();
        String month = selecttime.getMonth();
        String day = selecttime.getDay();
        String hour = selecttime.getHour();
        String minute = selecttime.getMinute();

    //substring(インデックス,何文字目か)
    int intyear= Integer.parseInt(year);
    int intmonth= Integer.parseInt(month);
    int intday= Integer.parseInt(day);
    int chkintday =intday;    //月の処理のところでどうしても必要なのでやむを得なく追加
    int inthour= Integer.parseInt(hour);
    int intminute= Integer.parseInt(minute);

    //プルダウンメニューに選択肢を表示するために必要な文字列
    String optionst = "<option value=";
    String optionend ="</option>";

    //現在の分を初期選択肢にするための処理
    Map<Integer,String> intminutemap = new HashMap<Integer, String>();
    for(int i = 0; i<=45;i=i+15){
        String mchoice;
        if(i<10){
        mchoice= optionst + "\"0"+i+"\">"+i+optionend;
        }else{
        mchoice= optionst + "\""+i+"\">"+i+optionend;
        }
        intminutemap.put(i,mchoice);
    }
    if((inthour >= 21 && intminute >0)|| inthour < 9 ){
        intminute = 0;
    }else if(intminute==0){
        //そのまま
    }else if(intminute <= 15){
        intminute = 15;
    }else if(intminute <= 30){
        intminute = 30;
    }else if(intminute <=45){
        intminute = 45;
    }else if(intminute >45){
        //45分を超えていたら時間を繰り上げる。
        intminute =0;
        inthour++;
    }
    String selectedintminute;
    if(intminute<10){
        selectedintminute = optionst + "\"0"+intminute+"\" selected>"+intminute+optionend;
    }else{
        selectedintminute = optionst + "\""+intminute+"\" selected>"+intminute+optionend;
    }
    intminutemap.put(intminute, selectedintminute);

    String intminutechoices = "";
    for(int i = 0; i <=45; i=i+15){
        intminutechoices += intminutemap.get(i);
    }

    this.intminutechoices=intminutechoices;

    /*  現在の時間を初期選択肢にするための処理
        分の繰り上がり処理を行ってから時間の処理をするべきだから
        分の後に処理をする必要がある。
    */
    Map<Integer,String> inthourmap = new HashMap<Integer, String>();
    for(int i = 9; i<=21;i++){
         String hchoice;
        if(i<10){
            hchoice=optionst + "\"0"+i+"\">"+i+optionend;
        }else{
            hchoice=optionst + "\""+i+"\">"+i+optionend;
        }
        inthourmap.put(i,hchoice);
    }
    if(inthour <=9){
        inthour=9;
    }else if(inthour >= 21){
        inthour=9;
        chkintday++;
    }
    String selectedinthour;
    if(inthour<10){
        selectedinthour = optionst + "\"0"+ inthour +"\" selected>"+ inthour +optionend;
    }else{
        selectedinthour = optionst + "\""+ inthour +"\" selected>"+ inthour +optionend;
    }
    inthourmap.put(inthour, selectedinthour);
    String inthourchoices = "";
    for(int i = 9; i <=21; i++){
        inthourchoices += inthourmap.get(i);
    }

    this.inthourchoices=inthourchoices;

    //現在の日を初期選択肢にするための処理
        //アクセスした月の最終日(うるう年考慮)をintlastDateに格納
    LocalDate localdate = LocalDate.of(intyear,intmonth,intday);
    YearMonth ym = YearMonth.from(localdate);
    LocalDate lastDate = ym.atEndOfMonth();
    String StringlastDate = lastDate.format(DateTimeFormatter.ofPattern("dd"));
    int intlastDate = Integer.parseInt(StringlastDate);
    /*
        たとえば、2015年6月30日22:00にアクセスしたとすると
        直前の時間の処理でinthourは9:00になり、chkintdayが+1されている。
        そうすると6月31日になるわけだが、それは存在しない。
        存在しない年月日をLocalDateに入れるとエラーを吐くので、
        そのために、intdayとは別にchkintdayを設けた。
        存在しない場合は、intmonthを+1し、intdayを1にする。
        プルダウンメニューに欲しい項目は、新しい月の日数なので
        ここで、またintlastDateを再評価する。
    */
    if(chkintday > intlastDate ){
        intday=1;
        if(intmonth+1 >12){    //学習ポイント！前置++と後置++と+1の違い
            intyear++;
            intmonth=1;
        }else{
            intmonth++;
        }
        localdate = LocalDate.of(intyear,intmonth,intday);
        ym = YearMonth.from(localdate);
        lastDate = ym.atEndOfMonth();
        StringlastDate = lastDate.format(DateTimeFormatter.ofPattern("dd"));
        intlastDate = Integer.parseInt(StringlastDate);
    }else{
        intday=chkintday;
    }
    Map<Integer,String> intdaymap = new HashMap<Integer, String>();
    for(int i = 1; i<=intlastDate;i++){
         String dchoice;
        if(i<10){
            dchoice=optionst + "\"0"+i+"\">"+i+optionend;
        }else{
            dchoice=optionst + "\""+i+"\">"+i+optionend;
        }
        intdaymap.put(i,dchoice);
    }
    String selectedintday;
    if(intday<10){
        selectedintday = optionst + "\"0"+ intday +"\" selected>"+ intday + optionend;
    }else{
        selectedintday = optionst + "\""+ intday +"\" selected>"+ intday + optionend;
    }
    intdaymap.put(intday, selectedintday);

    String intdaychoices = "";
    for(int i = 1; i <=intlastDate; i++){
        intdaychoices += intdaymap.get(i);
    }

    this.intdaychoices=intdaychoices;

    //現在の月を初期選択肢にするための処理
    Map<Integer,String> intmonthmap = new HashMap<Integer, String>();
    for(int i = 1; i<=12;i++){
        String monchoice;
        if(i<10){
            monchoice=optionst + "\"0"+i+"\">"+i+optionend;
        }else{
            monchoice=optionst + "\""+i+"\">"+i+optionend;
        }
        intmonthmap.put(i,monchoice);
    }
    String selectedintmonth;
    if(intmonth<10){
        selectedintmonth = optionst + "\"0"+ intmonth +"\" selected>"+ intmonth + optionend;
    }else{
        selectedintmonth = optionst + "\""+ intmonth +"\" selected>"+ intmonth + optionend;
    }
    intmonthmap.put(intmonth, selectedintmonth);

    String intmonthchoices = "";
    for(int i = 1; i <=12; i++){
        intmonthchoices += intmonthmap.get(i);
    }

    this.intmonthchoices=intmonthchoices;

    //現在の年を初期選択肢にするための処理
    Map<Integer,String> intyearmap = new HashMap<Integer, String>();
    for(int i = intyear; i<=(intyear+10);i++){
        String ychoice=optionst + "\""+i+"\">"+i+optionend;
        intyearmap.put(i,ychoice);
    }
    String selectedintyear = optionst + "\""+intyear+"\" selected>"+intyear+optionend;
    intyearmap.put(intyear, selectedintyear);

    String intyearchoices = "";
    for(int i = intyear; i <=(intyear+10); i++){
        intyearchoices += intyearmap.get(i);
    }

    this.intyearchoices=intyearchoices;

    }


    public String getMinutechoices() {
        return intminutechoices;
    }


    public String getHourchoices() {
        return inthourchoices;
    }


    public String getDaychoices() {
        return intdaychoices;
    }


    public String getMonthchoices() {
        return intmonthchoices;
    }


    public String getYearchoices() {
        return intyearchoices;
    }
}

/*
 * SelectTimeは、アクセス時は現在時刻を格納し、
 * カレンダーの日付をクリックしたときは、その日付を格納します。
 *
 * 形式：
 *
 * String datetime = yyyy-MM-dd HH:mm:ss
 * String date = yyyy-MM-dd
 * String time = HH:mm
 * String year = yyyy
 * String month = MM
 * String day = dd
 * String hour = HH
 * String minute = mm
 * String second = ss
 */


package model;

import java.io.Serializable;

public class SelectDateTime implements Serializable {
	private String datetime,date,time,year,month,day,hour,minute,second;
	private String convdatetime,convdate,convtime;

	public SelectDateTime(){

	}

	public void setConvdatetime(String convdatetime) {
		this.convdatetime = convdatetime;
	}

	public void setConvdate(String convdate) {
		this.convdate = convdate;
	}

	public void setConvtime(String convtime) {
		this.convtime = convtime;
	}

	public SelectDateTime(String datetime){
		this.datetime=datetime;
		this.date=datetime.substring(0,10);
		this.time=datetime.substring(11,16);
	    this.year= datetime.substring(0,4);
	    this.month= datetime.substring(5,7);
	    this.day=datetime.substring(8,10);
	    this.hour= datetime.substring(11,13);
	    this.minute= datetime.substring(14,16);
	    this.second=datetime.substring(17,19);

	    this.convdatetime = year+month+day+hour+minute+second;
	    this.convdate = year+month+day;
	    this.convtime = hour+minute+second;


	}

	public SelectDateTime(String date, String time){
		this.datetime=date+" "+time;
		this.date=date;
		this.time=time;
	    this.year= datetime.substring(0,4);
	    this.month= datetime.substring(5,7);
	    this.day=datetime.substring(8,10);
	    this.hour= datetime.substring(11,13);
	    this.minute= datetime.substring(14,16);
	    this.second=datetime.substring(17,19);

	    this.convdatetime = year+month+day+hour+minute+second;
	    this.convdate = year+month+day;
	    this.convtime = hour+minute+second;



	}

	public SelectDateTime(String year,String month,String day,String hour, String minute, String second){
		this.datetime=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
		this.date=year+"-"+month+"-"+day;
		this.time=hour+":"+minute+":"+second;
	    this.year=year;
	    this.month=month;
	    this.day=day;
	    this.hour=hour;
	    this.minute=minute;
	    this.second=second;

	    this.convdatetime = year+month+day+hour+minute+second;
	    this.convdate = year+month+day;
	    this.convtime = hour+minute+second;


	}

	public SelectDateTime(String date, String hour, String minute ,String second){
		this.datetime=date+" "+hour+":"+minute+":"+second;
		this.date=date;
		this.time=hour+":"+minute+":"+second;
	    this.year= date.substring(0,4);
	    this.month= date.substring(5,7);
	    this.day= date.substring(8,10);
	    this.hour=hour;
	    this.minute=minute;
	    this.second=second;

	    this.convdatetime = year+month+day+hour+minute+second;
	    this.convdate = year+month+day;
	    this.convtime = hour+minute+second;


	}


	public String getDatetime() {
		return datetime;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public String getDay() {
		return day;
	}

	public String getHour() {
		return hour;
	}

	public String getMinute() {
		return minute;
	}

	public String getSecond() {
		return second;
	}

	public String getConvdatetime() {
		return convdatetime;
	}

	public String getConvdate() {
		return convdate;
	}

	public String getConvtime() {
		return convtime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public void setSecond(String second) {
		this.second = second;
	}

}

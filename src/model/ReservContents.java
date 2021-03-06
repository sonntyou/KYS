/*
 * ～datetime = yyyy-MM-dd HH:mm:ss
 *
 */


package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReservContents implements Serializable {

	/*
	 * 予約情報
	 * 予約ごとの情報を格納します。
	 */
	//予約ごとに振られるユニークなIDが入ります。
	private int reservid;
	//予約対象(場所など)に振られるユニークなIDが入ります。
	private int resourceid;
	//予約対象(場所など)の名前が入ります。
	private String resource;
	//題名が入ります。
	private String title;
	//予約の開始時間が入ります。
	//形式 : yyyy-MM-dd HH:mm:ss
	private String stdatetime;
	//予約の終了時間が入ります。
	//形式 : yyyy-MM-dd HH:mm:ss
	private String enddatetime;

	/*
	 * アカウント情報
	 * 予約ごとの担当者全員の情報をリストとして保存します。
	 */
	//IDのリストです。
	private List<Integer> reserveridlist;
	//名前のリストです。
	private List<String> reserverlist;
	//メールのリストです。
	private List<String> maillist;

	public String getStyear() {
		return styear;
	}

	public String getEndyear() {
		return endyear;
	}

	public String getStmonth() {
		return stmonth;
	}

	public String getEndmonth() {
		return endmonth;
	}

	public String getStday() {
		return stday;
	}

	public String getEndday() {
		return endday;
	}

	/*
	 * 予約テーブル表示用変数
	 * JSP内での計算を減らすために用意してある変数
	 */
	//枠の大きさの条件判定に使用
	//形式 : yyyyMMddHHmmss
	private long longstconvdatetime;
	private long longendconvdatetime;

	//予約ごとのの大きさを決めるのに使用
	//形式 : HH
	private int intsthour;
	private int intendhour;
	//形式 : mm
	private int intstminute;
	private int intendminute;

	//予約ごとの詳細ポップアップの時間表示に使用
	//yyyy
	private String styear;
	private String endyear;
	//MM
	private String stmonth;
	private String endmonth;
	//dd
	private String stday;
	private String endday;

	//形式 : HH
	private String sthour;
	private String endhour;
	//
	private String stminute;
	private String endminute;

	/*
	 * 削除後のリスト更新のために使用
	 */
	//形式：
	private String selectdate;

	//コンストラクタ
	public ReservContents(){
		reserveridlist = new ArrayList<Integer>();
		reserverlist = new ArrayList<String>();
		maillist = new ArrayList<String>();
	}

	//ゲッター
	public int getReservid() {
		return reservid;
	}

	public int getResourceid() {
		return resourceid;
	}
	public String getStdatetime() {
		return stdatetime;
	}

	public String getEnddatetime() {
		return enddatetime;
	}


	public List<Integer> getReserveridlist() {
		return reserveridlist;
	}

	public String getTitle() {
		return title;
	}


	public List<String> getReserverlist() {
		return reserverlist;
	}

	public String getResource() {
		return resource;
	}

	public List<String> getMaillist() {
		return maillist;
	}

	public long getLongstconvdatetime() {
		return longstconvdatetime;
	}

	public long getLongendconvdatetime() {
		return longendconvdatetime;
	}

	public String getSthour() {
		return sthour;
	}

	public String getEndhour() {
		return endhour;
	}

	public String getStminute() {
		return stminute;
	}

	public String getEndminute() {
		return endminute;
	}

	public int getIntsthour() {
		return intsthour;
	}

	public int getIntendhour() {
		return intendhour;
	}

	public int getIntstminute() {
		return intstminute;
	}

	public int getIntendminute() {
		return intendminute;
	}


	public String getSelectdate() {
		return selectdate;
	}




	//セッター
	public void setReservid(int reservid) {
		this.reservid = reservid;
	}

	public void setResourceid(int resourceid) {
		this.resourceid = resourceid;
	}

	public void setReserveridlist(int reserverid) {
		this.reserveridlist.add(reserverid);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setReserverlist(String reserver) {
		this.reserverlist.add(reserver);
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public void setStdatetime(String stdatetime) {
		//予約格納の際に必ず呼び出される。
		this.stdatetime = stdatetime;

	    String year= stdatetime.substring(0,4);
	    String month= stdatetime.substring(5,7);
	    String day=stdatetime.substring(8,10);
	    String hour= stdatetime.substring(11,13);
	    String minute= stdatetime.substring(14,16);
	    String second=stdatetime.substring(17,19);

	    this.selectdate=year+"-"+month+"-"+day;
	    
	    this.styear=year;
	    this.stmonth=month;
	    this.stday=day;
	    this.sthour = hour;
	    this.stminute = minute;
	    this.intsthour = Integer.parseInt(sthour);
	    this.intstminute = Integer.parseInt(stminute);

	    String stconvdatetime = year+month+day+hour+minute+second;
	    this.longstconvdatetime = Long.parseLong(stconvdatetime);


	}

	public void setEnddatetime(String enddatetime) {
		//予約格納の際に必ず呼び出される。
		this.enddatetime = enddatetime;

	    String year= enddatetime.substring(0,4);
	    String month= enddatetime.substring(5,7);
	    String day=enddatetime.substring(8,10);
	    String hour= enddatetime.substring(11,13);
	    String minute= enddatetime.substring(14,16);
	    String second=enddatetime.substring(17,19);

	    this.endyear=year;
	    this.endmonth=month;
	    this.endday=day;
	    this.endhour = hour;
	    this.endminute = minute;
	    this.intendhour = Integer.parseInt(endhour);
	    this.intendminute = Integer.parseInt(endminute);


	    String endconvdatetime = year+month+day+hour+minute+second;
	    this.longendconvdatetime = Long.parseLong(endconvdatetime);

	}

	public void setReserveridlist(List<Integer> reserveridlist) {
		this.reserveridlist = reserveridlist;
	}

	public void setReserverlist(List<String> reserverlist) {
		this.reserverlist = reserverlist;
	}

	public void setMaillist(List<String> maillist) {
		this.maillist = maillist;
	}

	public void setMaillist(String mail){
		this.maillist.add(mail);
	}

}

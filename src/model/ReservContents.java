package model;

import java.io.Serializable;

public class ReservContents implements Serializable {
	int reservid,locateid,accountid;
	String title,sttime,endtime,reserver,locate;

	public ReservContents(){
	}

	//ゲッター
	public int getReservid() {
		return reservid;
	}

	public int getLocateid() {
		return locateid;
	}

	public int getAccountid() {
		return accountid;
	}

	public String getTitle() {
		return title;
	}

	public String getSttime() {
		return sttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public String getReserver() {
		return reserver;
	}

	public String getLocate() {
		return locate;
	}

	//セッター
	public void setReservid(int reservid) {
		this.reservid = reservid;
	}

	public void setLocateid(int locateid) {
		this.locateid = locateid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSttime(String sttime) {
		this.sttime = sttime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public void setReserver(String reserver) {
		this.reserver = reserver;
	}

	public void setLocate(String locate) {
		this.locate = locate;
	}


}

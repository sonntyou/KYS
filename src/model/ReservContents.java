package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReservContents implements Serializable {
	private int reservid,resourceid;
	private String title,sttime,stdate,endtime,enddate,resource;
	List<Integer> reserveridlist;
	List<String> reserverlist;

	//コンストラクタ
	public ReservContents(){
		reserveridlist = new ArrayList<Integer>();
		reserverlist = new ArrayList<String>();
	}

	//ゲッター
	public int getReservid() {
		return reservid;
	}

	public int getResourceid() {
		return resourceid;
	}

	public List<Integer> getReserveridlist() {
		return reserveridlist;
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

	public List<String> getReserverlist() {
		return reserverlist;
	}

	public String getResource() {
		return resource;
	}

	public String getStdate() {
		return stdate;
	}

	public String getEnddate() {
		return enddate;
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

	public void setSttime(String sttime) {
		this.sttime = sttime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public void setReserverlist(String reserver) {
		this.reserverlist.add(reserver);
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public void setStdate(String stdate) {
		this.stdate = stdate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}



}

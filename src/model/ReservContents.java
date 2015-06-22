/*
 * ～datetime = yyyy-MM-dd HH:mm:ss
 *
 */


package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReservContents implements Serializable {
	private int reservid,resourceid;
	private String title,stdatetime,enddatetime,resource;

	List<Integer> reserveridlist;
	List<String> reserverlist;
	List<String> maillist;



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
		this.stdatetime = stdatetime;
	}

	public void setEnddatetime(String enddatetime) {
		this.enddatetime = enddatetime;
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

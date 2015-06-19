package model;

import java.io.Serializable;

public class Today implements Serializable {
	private String today, todaytime;

	public Today(){

	}

	public Today(String today){
		this.today=today;
	}

	//セッター
	public void setToday(String today){
		this.today=today;
	}

	public void setTodayTime(String todaytime){
		this.todaytime=todaytime;
	}

	//ゲッター
	public String getToday(){
		return today;
	}

	public String getTodayTime(){
		return todaytime;
	}

}

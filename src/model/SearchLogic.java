package model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dao.SearchDAO;

public class SearchLogic {

	public List<ReservContents> execute(){

		ZonedDateTime now = ZonedDateTime.now();
		String currentdate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		SearchDAO searchdao=new SearchDAO();
		return searchdao.search(currentdate);
	}

	public List<ReservContents> execute(String date){
		SearchDAO searchdao=new SearchDAO();
		return searchdao.search(date);
	}
}

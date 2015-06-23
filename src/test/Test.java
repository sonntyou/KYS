package test;

import java.util.ArrayList;
import java.util.List;

import model.ReservContents;
import model.SearchLogic;

public class Test {

public static void main(String[] args) {

	SearchLogic searchlogic = new SearchLogic();
	List<ReservContents> reservlist = new ArrayList<ReservContents>();
	reservlist = searchlogic.execute();

	for(int i = 0; i < reservlist.size();i++){
		System.out.println(reservlist.get(i).getTitle());
		System.out.println(reservlist.get(i).getReservid());

		for(int j = 0; j<reservlist.get(i).getMaillist().size();j++){
		System.out.println(reservlist.get(i).getMaillist().get(j));
		}
	}

}

}

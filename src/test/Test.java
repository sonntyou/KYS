package test;

import model.ReservContents;
import dao.ReservDAO;

public class Test {

public static void main(String[] args) {


	ReservContents reservcontents =new ReservContents();

reservcontents.setAccountid(1);
reservcontents.setLocateid(1);
reservcontents.setSttime("2015-06-17 12:00");
reservcontents.setTitle("会議");
reservcontents.setEndtime("2015-06-17 12:30");

ReservDAO reservdao=new ReservDAO();

int test= reservdao.reserv(reservcontents);

System.out.println(test);

}

}

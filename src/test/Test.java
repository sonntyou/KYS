package test;

import model.ReservContents;
import dao.ReservDAO;

public class Test {

public static void main(String[] args) {


	ReservContents reservcontents =new ReservContents();

reservcontents.setReserveridlist(1);
reservcontents.setReserveridlist(2);
reservcontents.setReserveridlist(3);

reservcontents.setResourceid(1);
reservcontents.setSttime("2015-09-17 16:00:00");
reservcontents.setTitle("会議");
reservcontents.setEndtime("2015-09-17 16:30:00");

ReservDAO reservdao=new ReservDAO();

int test= reservdao.reserv(reservcontents);

System.out.println(test);

}

}

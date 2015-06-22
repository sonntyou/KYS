package model;

import dao.ReservDAO;

public class ReservLogic {
	public int execute(ReservContents reservcontents){
		ReservDAO reservdao = new ReservDAO();
		return reservdao.reserv(reservcontents);
	}
}

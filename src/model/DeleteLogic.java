package model;

import dao.DeleteDAO;

public class DeleteLogic {
	public int execute(int reservid, String mail){
		DeleteDAO deletedao = new DeleteDAO();
		return deletedao.delete(reservid, mail);
	}
}

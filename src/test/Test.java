package test;

import dao.DeleteDAO;

public class Test {

	public static void main(String[] args) {
		DeleteDAO deletedao = new DeleteDAO();
		System.out.println(deletedao.delete(24,"tanaka"));


	}

}

package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

	//Opera��es staticas para instanciar os Daos
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
		
		
		
	}
	
}

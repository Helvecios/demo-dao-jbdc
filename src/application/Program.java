package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		//Instanciando um SellerDao
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("==== Teste 1: SellerById ====");
		Seller seller = sellerDao.findById(3);
					
		System.out.println(seller);
	}

}

package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		//Instanciando um SellerDao
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("==== Teste 1: Seller findById ====");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println();
		System.out.println("==== Teste 2: Seller findByDepartment ====");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		//For_each Para cada Seller obj na minha list, imprimir na tela o obj
		for (Seller obj : list) {
			System.out.println(obj);
		}
	}

}

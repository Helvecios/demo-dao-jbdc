package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
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
		
		System.out.println();
		System.out.println("==== Teste 3: Seller findAll ====");
		list = sellerDao.findAll();
		//For_each Para cada Seller obj na minha list, imprimir na tela o obj
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println();
		System.out.println("==== Teste 4: Seller insert ====");
		//Instanciando um novo vendedor
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
		//Inserir o novo vendedor no BD
		sellerDao.insert(newSeller);
		System.out.println("Inserted! new seller id = " + newSeller.getId());
		
		System.out.println();
		System.out.println("==== Teste 5: Seller update ====");
		seller = sellerDao.findById(1); //Vendedor a ser atualizado "1"
		seller.setName("Martha Waine"); //Nome a ser atualizado, com Id = 1
		sellerDao.update(seller); //Atualiza o nome do vendedor
		System.out.println("Update completed!");
		
		System.out.println();
		System.out.println("==== Teste 6: Seller delete ====");
		System.out.println("Enter id for delete test: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed!");
				
		sc.close();		
		
	}
	
}

package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//Instanciando um DepartmentDao
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		//Procura pelo Id do departamento
		System.out.println("==== Teste 1: Department findById ====");
		Department department = departmentDao.findById(1);
		System.out.println(department);
		
		//Lista todos departamentos
		System.out.println("\n=== TEST 2: findAll =======");
		List<Department> list = departmentDao.findAll();
		for (Department d : list) {
			System.out.println(d);
		}
	
		//Inserido um novo departamento
		System.out.println();
		System.out.println("==== Teste 3: Department insert ====");
		//Instanciando um novo departamento
		Department newDepartment = new Department(7, "Music");
		//Inserir o novo departamento no BD
		departmentDao.insert(newDepartment);
		System.out.println("Inserted! new department id = " + newDepartment.getId());
		
		//Alterando o nome do departamento
		System.out.println("\n=== TEST 4: update =======");
		Department dep2 = departmentDao.findById(1);
		dep2.setName("Food");
		departmentDao.update(dep2);
		System.out.println("Update completed");
		
		//Deletando um departamento
		System.out.println("\n=== TEST 5: delete =======");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Delete completed");
		
		sc.close();		
		
	}
	
}

package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDao {
	
	//Implementação do Dao de Seller
	void insert(Seller obj); //Operação para inserir o objeto no BD 
	void update(Seller obj); //Operação para utualizar o objeto no BD 
	void deleteById(Integer id);
	Seller findById(Integer id);
	List<Seller> findAll(); //Operação para listar todos vendedores
	List<Seller> findByDepartment(Department department); //Operação para listar todos vendedores de um determinado departamento
}

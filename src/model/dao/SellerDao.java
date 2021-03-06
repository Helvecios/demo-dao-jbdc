package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDao {
	
	//Implementa��o do Dao de Seller
	void insert(Seller obj); //Opera��o para inserir o objeto no BD 
	void update(Seller obj); //Opera��o para utualizar o objeto no BD 
	void deleteById(Integer id);
	Seller findById(Integer id);
	List<Seller> findAll(); //Opera��o para listar todos vendedores
	List<Seller> findByDepartment(Department department); //Opera��o para listar todos vendedores de um determinado departamento
}

package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
	
	//Implementação do Dao de Department
	void insert(Department obj); //Operação para inserir o objeto no BD 
	void update(Department obj); //Operação para utualizar o objeto no BD 
	void deleteById(Integer id);
	Department findById(Integer id);
	List<Department> findAll();

}


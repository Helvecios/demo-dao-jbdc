package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
	
	//Implementa��o do Dao de Department
	void insert(Department obj); //Opera��o para inserir o objeto no BD 
	void update(Department obj); //Opera��o para utualizar o objeto no BD 
	void deleteById(Integer id);
	Department findById(Integer id);
	List<Department> findAll();

}


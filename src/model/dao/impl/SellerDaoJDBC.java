package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

//Implementa a classe SellerDao
public class SellerDaoJDBC implements SellerDao {

	// Para fazer a conexão com o banco de dados
	private Connection conn;

	// Construtor
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	// Métodos
	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			st.setInt(1,  id);
			rs = st.executeQuery(); //Consultar no BD
			if (rs.next()) {
				Department dep = new Department(); //Instanciar o departamento
				dep.setId(rs.getInt("DepartmentId")); //Pega o iD do departamento
				dep.setName(rs.getString("DepName")); //Pega o nome do departamento
				Seller obj = new Seller(); //Instaciando o vendedor
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				obj.setEmail(rs.getString("Email"));
				obj.setBaseSalary(rs.getDouble("BaseSalary"));
				obj.setBirthDate(rs.getDate("BirthDate"));
				obj.setDepartment(dep); //É o objeto criado acima 
				return obj;
			}
			return null;
		}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally { //Para fechar o Statment e o ResultSet
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			}
		}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

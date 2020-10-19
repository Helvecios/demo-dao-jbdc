package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		PreparedStatement st = null;
		try {
			st =conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS); //Devolve o Id do novo vendedor insetido
		
			st.setString(1,  obj.getName());
			st.setString(2,  obj.getEmail());
			st.setDate(3,  new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4,  obj.getBaseSalary());
			st.setInt(5,  obj.getDepartment().getId());
			
			int rowsAffected = st.executeUpdate(); //Como SQL para inserir os dado no BD
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs); //Fecha o ResultSet do "if"
					
			}
			else {
				throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
			}
				
		}
		
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st); //Fecha o Statement
		}
	}
	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}
	//Operação para listar todos vendedores de um determinado Id
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
				Department dep = instantiateDepartment(rs); //Instancia o departamento e chama função "instantiateDepartment"
				Seller obj = instantiateSeller(rs, dep); //Intancia o vendedor e chama função "instantiateSeller"
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

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller(); //Instaciando o vendedor
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep); //É o objeto criado acima
		return obj;
	}

	//Criando funções para serem reaproveitadas
	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department(); //Instanciar o departamento
		dep.setId(rs.getInt("DepartmentId")); //Pega o iD do departamento
		dep.setName(rs.getString("DepName")); //Pega o nome do departamento
		return dep;
				
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"ORDER BY Name");
			
			rs = st.executeQuery(); //Consultar no BD
			
			List<Seller> list = new ArrayList<>();//Cria uma lista para colocar todos os vendedores do departamento escolhido
			Map<Integer, Department> map = new HashMap<>();//Para que o departamento seja único criar o map
			
			while (rs.next()) {
				
				//Testa se o departamento ja existe
				Department dep = map.get(rs.getInt("DepartmentID")); //Instancia o departamento e chama função "instantiateDepartment"
				if(dep == null) { //Se o departamento não existir instancia um novo departamento
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentID"), dep);//Guardar o Id do departamento, no map
				}
								
				Seller obj = instantiateSeller(rs, dep); //Intancia o vendedor e chama função "instantiateSeller"
				list.add(obj); //Inclui na lista todos os vendedores do departamento escolhido
			}
			return list;
		}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally { //Para fechar o Statment e o ResultSet
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			}
		
	}
	//Operação para listar todos vendedores de um determinado departamento
	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"WHERE DepartmentId = ? "
					+"ORDER BY Name");
			
			st.setInt(1, department.getId());
			rs = st.executeQuery(); //Consultar no BD
			
			List<Seller> list = new ArrayList<>();//Cria uma lista para colocar todos os vendedores do departamento escolhido
			Map<Integer, Department> map = new HashMap<>();//Para que o departamento seja único criar o map
			
			while (rs.next()) {
				
				//Testa se o departamento ja existe
				Department dep = map.get(rs.getInt("DepartmentID")); //Instancia o departamento e chama função "instantiateDepartment"
				if(dep == null) { //Se o departamento não existir instancia um novo departamento
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentID"), dep);//Guardar o Id do departamento, no map
				}
								
				Seller obj = instantiateSeller(rs, dep); //Intancia o vendedor e chama função "instantiateSeller"
				list.add(obj); //Inclui na lista todos os vendedores do departamento escolhido
			}
			return list;
		}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
			finally { //Para fechar o Statment e o ResultSet
				DB.closeStatement(st);
				DB.closeResultSet(rs);
			}
		
	}

}

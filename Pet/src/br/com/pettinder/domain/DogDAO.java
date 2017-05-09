package br.com.pettinder.domain;


import br.com.pettinder.domain.Dog;
import br.com.pettinder.domain.BaseDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class DogDAO extends BaseDAO{
	public Dog getDogById(Long id) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("select * from dog where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				Dog d = CreateDog(rs);
				rs.close();
				return d;

			}   

		}finally {
			if(stmt != null){
				stmt.close();
			}
			if(conn !=null){
				conn.close();
			}
		}
		return null;
	}
	public List<Dog> findByName(String name) throws SQLException{
		List<Dog> dogs  = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("select * from dog where lower(nome) like ?");
			stmt.setString(1, "%" + name.toLowerCase() +"%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				Dog d = CreateDog(rs);
				dogs.add(d);

			}   
			rs.close();
		}finally {
			if(stmt != null){
				stmt.close();
			}
			if(conn !=null){
				conn.close();
			}
		}
		return dogs;
	}
	public List<Dog> getDogs()throws SQLException{
		List<Dog> dogs  = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("select * from dog");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				Dog d = CreateDog(rs);
				dogs.add(d);

			}   
			rs.close();
		}finally {
			if(stmt != null){
				stmt.close();
			}
			if(conn !=null){
				conn.close();
			}
		}
		return dogs;
	}
	public Dog CreateDog(ResultSet rs) throws SQLException{
		Dog d =  new Dog();
		d.setId(rs.getLong("id"));
		d.setIdade(rs.getString("idade"));
		d.setNome(rs.getString("nome"));
		d.setRaca(rs.getString("raca"));
		d.setTemperamento(rs.getString("temperamento"));
		return d;
	}

	public void save(Dog d)throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (d.getId()==null) {
				stmt = conn.prepareStatement(
						"insert into dog (nome,idade,raca,temperamento) VALUES(?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement("update dog set nome=?,idade=?,raca=?,temperamento=? where id=?");
			}
			stmt.setString(1, d.getNome());
			stmt.setString(2, d.getIdade());
			stmt.setString(3, d.getRaca());
			stmt.setString(4, d.getTemperamento());

			if (d.getId() != null) {
				// Update
				stmt.setLong(5, d.getId());
			}
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir o Cão");
			}
			// Se inseriu, ler o id auto incremento
			if (d.getId() == null) {
				Long id = getGeneretedId(stmt);
				d.setId(id);
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	public static Long getGeneretedId(Statement stmt)throws SQLException{
		ResultSet rs = stmt.getGeneratedKeys();
		if(rs.next()){
			Long id = rs.getLong(1);
			return id;
		}
		return 0L;

	}  
	public boolean delete(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("Delete from dog where id=?");
			stmt.setLong(1, id);
			int count = stmt.executeUpdate();
			boolean ok = count>0;
			return ok;
		}finally {
			if(stmt != null){
				stmt.close();
			}
			if(conn !=null){
				conn.close();
			}
		}
	}
	


}
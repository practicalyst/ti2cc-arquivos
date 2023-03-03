package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Musica;

public class MusicaDAO extends DAO {
	
	public MusicaDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(Musica musica) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO musicas (codigo, nome, autor) "
				       + "VALUES ("+ musica.getCodigo()+ ", '" + musica.getNome() + "', '"  
				       + musica.getAutor() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Musica Musica(int codigo) {
		Musica musica = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM musicas WHERE codigo=" + codigo;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 musica = new Musica(rs.getInt("codigo"), rs.getString("nome"), rs.getString("autor"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return musica;
	}
	
	
	public List<Musica> get() {
		return get("");
	}

	
	public List<Musica> getOrderByCodigo() {
		return get("codigo");		
	}
	
	
	public List<Musica> getOrderByName() {
		return get("nome");		
	}
		
	private List<Musica> get(String orderBy) {	
	
		List<Musica> musicas = new ArrayList<Musica>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM musicas" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Musica u = new Musica(rs.getInt("codigo"), rs.getString("nome"), rs.getString("autor"));
	            musicas.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return musicas;
	}

	public boolean update(Musica musica) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE musicas SET nome= '" + musica.getNome() + "', autor= '"  
				       + musica.getAutor() + "' WHERE codigo = " + musica.getCodigo()+ ";";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM musicas WHERE codigo = " + codigo;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
}
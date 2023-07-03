package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO extends GenericDAO{
	// INSERT -----------------------------------------------------------//
	public String GetPapel(String email) {
		String sql = "SELECT * FROM LOGIN WHERE LOGIN.email = ?";
		String papel = null;
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement stat = conn.prepareStatement(sql);
			
			stat.setString(1, email);
			ResultSet res = stat.executeQuery();
			if (res.next()) {
				papel = res.getString("papel");
			}
			res.close();
			stat.close();
			
			conn.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return papel;
	}
	
	// INSERT -----------------------------------------------------------//
		public String GetSenha(String email) {
			String sql = "SELECT * FROM LOGIN WHERE LOGIN.email = ?";
			String senha = null;
			
			try {
				Connection conn = this.getConnection();
				PreparedStatement stat = conn.prepareStatement(sql);
				
				stat.setString(1, email);
				ResultSet res = stat.executeQuery();
				if (res.next()) {
					senha = res.getString("senha");
				}
				res.close();
				stat.close();
				
				conn.close();
			}
			catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
			return senha;
		}
}
	
	
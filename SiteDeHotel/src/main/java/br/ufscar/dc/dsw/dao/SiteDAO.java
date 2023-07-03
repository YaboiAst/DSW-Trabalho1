package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Site_Reservas_Beans;

public class SiteDAO extends GenericDAO{
	// INSERT -----------------------------------------------------------//
	public void Insert(Site_Reservas_Beans site) {
		String sqlLogin = "INSERT INTO LOGIN (email, senha, nome) VALUES (?, ?, ?)";
		String sqlSite = "INSERT INTO SITE (userid, url, telefone) VALUES (?, ? ,?)";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statLogin = conn.prepareStatement(sqlLogin);
			PreparedStatement statSite = conn.prepareStatement(sqlSite);
			
			statLogin.setString(1, site.getEmail());
			statLogin.setString(2, site.getSenha()); 
			statLogin.setString(3, site.getNome()); 
			statLogin.executeUpdate();
			statLogin.close();
			
			statSite.setString(1, site.getEmail()); 
			statSite.setString(2, site.getUrl()); 
			statSite.setString(3, site.getTelefone());
			statSite.executeUpdate();
			statSite.close();
			
			conn.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// UPDATE -----------------------------------------------------------//
	public void Update(Site_Reservas_Beans site) {
		String sqlLogin = "UPDATE LOGIN SET senha = ?, nome = ? WHERE email = ?" ;
		String sqlSite = "UPDATE SITE SET url = ?, telefone = ? WHERE userid = ?";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statLogin = conn.prepareStatement(sqlLogin);
			PreparedStatement statSite = conn.prepareStatement(sqlSite);;
			
			statLogin.setString(3, site.getEmail());
			statLogin.setString(1, site.getSenha()); 
			statLogin.setString(2, site.getNome()); 
			statLogin.executeUpdate();
			statLogin.close();
			
			statSite.setString(3, site.getEmail()); 
			statSite.setString(1, site.getUrl()); 
			statSite.setString(2, site.getTelefone());
			statSite.executeUpdate();
			statSite.close();
			
			conn.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// REMOVE -----------------------------------------------------------//
	public void Remove(Site_Reservas_Beans site) {
		String sqlLogin = "DELETE FROM LOGIN WHERE email = ? ";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statLogin = conn.prepareStatement(sqlLogin);
			
			statLogin.setString(1, site.getEmail());
			statLogin.executeUpdate();
			statLogin.close();
			
			conn.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// GET    -----------------------------------------------------------//
	public Site_Reservas_Beans Get(String email) {
		Site_Reservas_Beans hotel = null;
		String sql = "SELECT * FROM SITE INNER JOIN LOGIN WHERE SITE.userid = LOGIN.email";
		sql += "AND SITE.userid = ?";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement stat = conn.prepareStatement(sql);
			
			stat.setString(1, email);
			ResultSet res = stat.executeQuery();
			if (res.next()) {
				String mail = res.getString("email");
				String senha = res.getString("senha");
				String nome = res.getString("nome");
				
				String url = res.getString("url");
				String telefone = res.getString("telefone");
				
				hotel = new Site_Reservas_Beans(mail, senha, nome, url, telefone);
			}
			res.close();
			stat.close();
			
			conn.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return hotel;
	}
	
	// GET    -----------------------------------------------------------//
	public Site_Reservas_Beans GetByUrl(String url) {
		Site_Reservas_Beans hotel = null;
		String sql = "SELECT * FROM SITE INNER JOIN LOGIN WHERE SITE.userid = LOGIN.email";
		sql += "AND SITE.url = ?";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement stat = conn.prepareStatement(sql);
			
			stat.setString(1, url);
			ResultSet res = stat.executeQuery();
			if (res.next()) {
				String email = res.getString("email");
				String senha = res.getString("senha");
				String nome = res.getString("nome");
				
				String telefone = res.getString("telefone");
				
				hotel = new Site_Reservas_Beans(email, senha, nome, url, telefone);
			}
			res.close();
			stat.close();
			
			conn.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return hotel;
	}
	
	// LIST   -----------------------------------------------------------//
	public List<Site_Reservas_Beans> GetList() {
		List<Site_Reservas_Beans> site = new ArrayList<>();
		String sql = "SELECT * FROM SITE INNER JOIN LOGIN WHERE SITE.userid = LOGIN.email";
		
		try {
			Connection conn = this.getConnection();
			Statement stat = conn.createStatement();
			
			ResultSet res = stat.executeQuery(sql);
			while (res.next()) {
				String mail = res.getString("email");
				String senha = res.getString("senha");
				String nome = res.getString("nome");
				
				String url = res.getString("url");
				String telefone = res.getString("telefone");
				
				Site_Reservas_Beans siteUnit = new Site_Reservas_Beans(mail, senha, nome, url, telefone);
				site.add(siteUnit);
			}
			res.close();
			stat.close();
			
			conn.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return site;
	}
}

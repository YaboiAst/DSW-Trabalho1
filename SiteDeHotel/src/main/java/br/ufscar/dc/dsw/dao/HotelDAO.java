package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Hotel_Beans;

public class HotelDAO extends GenericDAO{
	// INSERT -----------------------------------------------------------//
	public void Insert(Hotel_Beans hotel) {
		String sqlLogin = "INSERT INTO LOGIN (email, senha, nome) VALUES (?, ?, ?)";
		String sqlHotel = "INSERT INTO HOTEL (userid, cnpj, cidade) VALUES (?, ? ,?)";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statLogin = conn.prepareStatement(sqlLogin);
			PreparedStatement statHotel = conn.prepareStatement(sqlHotel);;
			
			statLogin.setString(1, hotel.getEmail());
			statLogin.setString(2, hotel.getSenha()); 
			statLogin.setString(3, hotel.getNome()); 
			statLogin.executeUpdate();
			statLogin.close();
			
			statHotel.setString(1, hotel.getEmail()); 
			statHotel.setString(2, hotel.getCnpj()); 
			statHotel.setString(3, hotel.getCidade());
			statHotel.executeUpdate();
			statHotel.close();
			
			conn.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// UPDATE -----------------------------------------------------------//
	public void Update(Hotel_Beans hotel) {
		String sqlLogin = "UPDATE LOGIN SET senha = ?, nome = ? WHERE email = ?" ;
		String sqlHotel = "UPDATE HOTEL SET cnpj = ?, cidade = ? WHERE userid = ?";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statLogin = conn.prepareStatement(sqlLogin);
			PreparedStatement statHotel = conn.prepareStatement(sqlHotel);;
			
			statLogin.setString(3, hotel.getEmail());
			statLogin.setString(1, hotel.getSenha()); 
			statLogin.setString(2, hotel.getNome()); 
			statLogin.executeUpdate();
			statLogin.close();
			
			statHotel.setString(3, hotel.getEmail()); 
			statHotel.setString(1, hotel.getCnpj()); 
			statHotel.setString(2, hotel.getCidade());
			statHotel.executeUpdate();
			statHotel.close();
			
			conn.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// REMOVE -----------------------------------------------------------//
	public void Remove(Hotel_Beans hotel) {
		String sqlLogin = "DELETE FROM LOGIN WHERE email = ? ";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statLogin = conn.prepareStatement(sqlLogin);
			
			statLogin.setString(1, hotel.getEmail());
			statLogin.executeUpdate();
			statLogin.close();
			
			conn.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// GET    -----------------------------------------------------------//
	public Hotel_Beans Get(String email) {
		Hotel_Beans hotel = null;
		String sql = "SELECT * FROM HOTEL INNER JOIN LOGIN WHERE HOTEL.userid = LOGIN.email";
		sql += "AND HOTEL.userid = ?";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement stat = conn.prepareStatement(sql);
			
			stat.setString(1, email);
			ResultSet res = stat.executeQuery();
			if (res.next()) {
				String mail = res.getString("email");
				String senha = res.getString("senha");
				String nome = res.getString("nome");
				
				String cnpj = res.getString("cnpj");
				String cidade = res.getString("cidade");
				
				hotel = new Hotel_Beans(mail, senha, nome, cnpj, cidade);
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
	public Hotel_Beans GetByCnpj(String cnpj) {
		Hotel_Beans hotel = null;
		String sql = "SELECT * FROM HOTEL INNER JOIN LOGIN WHERE HOTEL.userid = LOGIN.email";
		sql += "AND HOTEL.cnpj = ?";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement stat = conn.prepareStatement(sql);
			
			stat.setString(1, cnpj);
			ResultSet res = stat.executeQuery();
			if (res.next()) {
				String email = res.getString("email");
				String senha = res.getString("senha");
				String nome = res.getString("nome");
				
				String cidade = res.getString("cidade");
				
				hotel = new Hotel_Beans(email, senha, nome, cnpj, cidade);
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
	public List<Hotel_Beans> GetList() {
		List<Hotel_Beans> hotel = new ArrayList<>();
		String sql = "SELECT * FROM HOTEL INNER JOIN LOGIN WHERE HOTEL.userid = LOGIN.email";
		
		try {
			Connection conn = this.getConnection();
			Statement stat = conn.createStatement();
			
			ResultSet res = stat.executeQuery(sql);
			while (res.next()) {
				String mail = res.getString("email");
				String senha = res.getString("senha");
				String nome = res.getString("nome");
				
				String cnpj = res.getString("cnpj");
				String cidade = res.getString("cidade");
				
				Hotel_Beans hotelUnit = new Hotel_Beans(mail, senha, nome, cnpj, cidade);
				hotel.add(hotelUnit);
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
	
	// GET POR CIDADE  --------------------------------------------------//
		public List<Hotel_Beans> GetByCity(String cidade) {
			List<Hotel_Beans> hotel = new ArrayList<>();
			String sql = "SELECT * FROM HOTEL INNER JOIN LOGIN WHERE HOTEL.userid = LOGIN.email";
			sql += "AND HOTEL.cidade = ?";
			
			try {
				Connection conn = this.getConnection();
				PreparedStatement stat = conn.prepareStatement(sql);
				
				stat.setString(1, cidade);
				ResultSet res = stat.executeQuery();
				while (res.next()) {
					String mail = res.getString("email");
					String senha = res.getString("senha");
					String nome = res.getString("nome");
					
					String cnpj = res.getString("cnpj");
					//String cidad = res.getString("cidade");
					
					Hotel_Beans hotelUnit = new Hotel_Beans(mail, senha, nome, cnpj, cidade);
					hotel.add(hotelUnit);
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
}

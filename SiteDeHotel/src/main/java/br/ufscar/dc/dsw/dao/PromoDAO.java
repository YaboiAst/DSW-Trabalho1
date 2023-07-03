package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import br.ufscar.dc.dsw.domain.Promocao_Beans;


public class PromoDAO extends GenericDAO{
	// INSERT -----------------------------------------------------------//
	public void Insert(Promocao_Beans promo) {
		String sqlPromo = "INSERT INTO PROMO (preco, dataInicio, dataFim, urlPromo, cnpjPromo) VALUES (?, ?, ?, ?, ?)";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statPromo = conn.prepareStatement(sqlPromo);
			
			statPromo.setFloat(1, promo.getPreco());
			statPromo.setObject(2, promo.getDataInicio()); 
			statPromo.setObject(3, promo.getDataFim());
			statPromo.setString(4, promo.getSite().getUrl());
			statPromo.setString(5, promo.getHotel().getCnpj()); 
			statPromo.executeUpdate();
			statPromo.close();
			
			
			conn.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// UPDATE -----------------------------------------------------------//
	public void Update(Promocao_Beans promo) {
		String sqlPromo = "UPDATE PROMO SET preco = ?, dataInicio = ?, dataFim = ?";
		sqlPromo += "WHERE urlPromo = ? AND cnpjPromo = ?" ;
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statPromo = conn.prepareStatement(sqlPromo);
			
			statPromo.setFloat(1, promo.getPreco());
			statPromo.setObject(2, promo.getDataInicio()); 
			statPromo.setObject(3, promo.getDataFim());
			statPromo.setString(4, promo.getSite().getUrl());
			statPromo.setString(5, promo.getHotel().getCnpj()); 
			statPromo.executeUpdate();
			statPromo.close();
			
			conn.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// REMOVE -----------------------------------------------------------//
	public void Remove(Promocao_Beans promo) {
		String sqlPromo = "DELETE FROM PROMO WHERE urlPromo = ? AND cnpjPromo = ?";
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement statPromo = conn.prepareStatement(sqlPromo);
			
			statPromo.setString(1, promo.getSite().getUrl());
			statPromo.setString(2, promo.getHotel().getCnpj());;
			statPromo.executeUpdate();
			statPromo.close();
			
			conn.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// GET    -----------------------------------------------------------//
	public Promocao_Beans Get(String cnpj, String url) {
		Promocao_Beans promo = null;
		String sql = "SELECT * FROM PROMO";
		sql += "WHERE PROMO.urlPromo = ? AND PROMO.cnpjPromo = ?";
		
		HotelDAO hotel = new HotelDAO();
		SiteDAO site = new SiteDAO();
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement stat = conn.prepareStatement(sql);
			
			stat.setString(1, url);
			stat.setString(2, cnpj);
			ResultSet res = stat.executeQuery();
			if (res.next()) {
				float preco = res.getFloat("preco");
				Date dataInicio = res.getObject("dataInicio", Date.class);
				Date dataFim = res.getObject("dataFim", Date.class);
				
				promo = new Promocao_Beans(preco, dataInicio, dataFim, hotel.GetByCnpj(cnpj), site.GetByUrl(url));
			}
			res.close();
			stat.close();
			
			conn.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return promo;
	}
	// LIST   -----------------------------------------------------------//
	public List<Promocao_Beans> GetList() {
		List<Promocao_Beans> site = new ArrayList<>();
		String sql = "SELECT * FROM PROMO";
		HotelDAO hotelDao = new HotelDAO();
		SiteDAO siteDao = new SiteDAO();
		
		try {
			Connection conn = this.getConnection();
			Statement stat = conn.createStatement();
			
			ResultSet res = stat.executeQuery(sql);
			while (res.next()) {
				float preco = res.getFloat("preco");
				Date dataInicio = res.getObject("dataInicio", Date.class);
				Date dataFim = res.getObject("dataFim", Date.class);
				
				String url = res.getString("urlPromo");
				String cnpj = res.getString("cnpjPromo");
				
				Promocao_Beans siteUnit = new Promocao_Beans(preco, dataInicio, dataFim, hotelDao.GetByCnpj(cnpj), siteDao.GetByUrl(url));
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
	
	//GET POR HOTEL   -----------------------------------------------------------//
	public List<Promocao_Beans> GetByHotel(String cnpj) {
		List<Promocao_Beans> site = new ArrayList<>();
		String sql = "SELECT * FROM PROMO WHERE PROMO.cnpj = ?";
		
		HotelDAO hotelDao = new HotelDAO();
		SiteDAO siteDao = new SiteDAO();
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement stat = conn.prepareStatement(sql);
			
			stat.setString(1, cnpj);
			ResultSet res = stat.executeQuery();
			while (res.next()) {
				float preco = res.getFloat("preco");
				Date dataInicio = res.getObject("dataInicio", Date.class);
				Date dataFim = res.getObject("dataFim", Date.class);
				
				String url = res.getString("urlPromo");
				
				Promocao_Beans siteUnit = new Promocao_Beans(preco, dataInicio, dataFim, hotelDao.GetByCnpj(cnpj), siteDao.GetByUrl(url));
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
	
	//GET POR SITE   -----------------------------------------------------------//
	public List<Promocao_Beans> GetBySite(String url) {
		List<Promocao_Beans> site = new ArrayList<>();
		String sql = "SELECT * FROM PROMO WHERE PROMO.url = ?";
		
		HotelDAO hotelDao = new HotelDAO();
		SiteDAO siteDao = new SiteDAO();
		
		try {
			Connection conn = this.getConnection();
			PreparedStatement stat = conn.prepareStatement(sql);
			
			stat.setString(1, url);
			ResultSet res = stat.executeQuery();
			while (res.next()) {
				float preco = res.getFloat("preco");
				Date dataInicio = res.getObject("dataInicio", Date.class);
				Date dataFim = res.getObject("dataFim", Date.class);
				
				String cnpj = res.getString("cnpj");
				
				Promocao_Beans siteUnit = new Promocao_Beans(preco, dataInicio, dataFim, hotelDao.GetByCnpj(cnpj), siteDao.GetByUrl(url));
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


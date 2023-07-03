package br.ufscar.dc.dsw.domain;

import java.util.Date;

public class Promocao_Beans {
	private float preco;
	private Date dataInicio;
	private Date dataFim;
	private Hotel_Beans hotel;
	private Site_Reservas_Beans site;
	
	public Promocao_Beans(float p, Date inicio, Date fim, Hotel_Beans h, Site_Reservas_Beans s) {
		this.preco = p;
		this.dataInicio = inicio;
		this.dataFim = fim;
		this.hotel = h;
		this.site = s;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Hotel_Beans getHotel() {
		return hotel;
	}

	public Site_Reservas_Beans getSite() {
		return site;
	}
	
	

}

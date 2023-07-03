package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.SiteDAO;
import br.ufscar.dc.dsw.domain.Site_Reservas_Beans;
import br.ufscar.dc.dsw.dao.PromoDAO;
import br.ufscar.dc.dsw.domain.Promocao_Beans;

import br.ufscar.dc.dsw.util.Erros;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/site/*")
public class SiteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SiteDAO siteDao;
	Site_Reservas_Beans site;
	PromoDAO promoDao;
	
	public void init() {
		siteDao = new SiteDAO();
		promoDao = new PromoDAO();
	}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
    	Erros err = new Erros();
    	site = (Site_Reservas_Beans) request.getAttribute("usuario");
    	
    	try{
    		lista(request, response);
    	}
    	catch (IOException e) {
    		err.add("IOException");
    	}
    	
    }
    
    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Promocao_Beans> listaPromos = promoDao.GetBySite(site.getUrl());
        request.setAttribute("listaHoteis", listaPromos);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/site/lista.jsp");
        dispatcher.forward(request, response);
    }
    
    
    
}

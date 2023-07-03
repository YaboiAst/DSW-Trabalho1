package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.HotelDAO;
import br.ufscar.dc.dsw.domain.Hotel_Beans;
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

@WebServlet(urlPatterns = "/hotel/*")
public class HotelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HotelDAO hotelDao;
	Hotel_Beans hotel;
	PromoDAO promoDao;
	
	public void init() {
		hotelDao = new HotelDAO();
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
    	hotel = (Hotel_Beans) request.getAttribute("usuario");
    	
    	// Navigation
    	String action = request.getPathInfo();
    	if (action == null) {
    		action = "";
    	}
    	
    	try {
    		switch (action) {
    			case "/cadastro":
    				apresentaFormCadastro(request, response);
    				break;
    			case "/atualizacao":
    				apresentaFormEdicao(request, response);
    				break;
    			case "/remocao":
    				remove(request, response);
    				break;
    			case "/lista":
    			default:
    				lista(request, response);
    				break;
    		}
    	}
    	catch (RuntimeException | IOException | ServletException e) {
            err.add("Exceção");
    		throw new ServletException(e);
        }
    }
    
    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Promocao_Beans> listaPromos = promoDao.GetByHotel(hotel.getCnpj());
        request.setAttribute("listaHoteis", listaPromos);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/hotel/lista.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	RequestDispatcher dispatcher = null;
    	dispatcher= request.getRequestDispatcher("/hotel/formsPromo.jsp");
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String url = request.getAttribute("url").toString();
    	RequestDispatcher dispatcher = null;
    	
        Promocao_Beans promo = promoDao.Get(url, hotel.getCnpj());
        request.setAttribute("promocao", promo);
        dispatcher = request.getRequestDispatcher("/hotel/formsPromo.jsp");
        dispatcher.forward(request, response);
    }
    
    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
    	String url = request.getAttribute("url").toString();
    	
        Promocao_Beans p = promoDao.Get(url, hotel.getCnpj());
        promoDao.Remove(p);
        
        response.sendRedirect("/admin");
    }
    
    
    
}

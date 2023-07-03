package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.HotelDAO;
import br.ufscar.dc.dsw.domain.Hotel_Beans;
import br.ufscar.dc.dsw.dao.SiteDAO;
import br.ufscar.dc.dsw.domain.Site_Reservas_Beans;
import br.ufscar.dc.dsw.util.Erros;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/*.jsp")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HotelDAO hotelDao;
	SiteDAO siteDao;
	
	public void init() {
		hotelDao = new HotelDAO();
		siteDao = new SiteDAO();
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
    	
    	// Navigation
    	String action = request.getPathInfo();
    	if (action == null) {
    		action = "";
    	}
    	
    	try {
    		switch (action) {
    			case "/hotel/cadastro":
    				request.setAttribute("cadastro", "HOT");
    				apresentaFormCadastro(request, response);
    				break;
    			case "/hotel/atualizacao":
    				request.setAttribute("cadastro", "HOT");
    				apresentaFormEdicao(request, response);
    				break;
    			case "/hotel/remocao":
    				request.setAttribute("cadastro", "HOT");
    				remove(request, response);
    				break;
    			case "/site/cadastro":
    				request.setAttribute("cadastro", "SIT");
    				apresentaFormCadastro(request, response);
    				break;
    			case "/site/atualizacao":
    				request.setAttribute("cadastro", "SIT");
    				apresentaFormEdicao(request, response);
    				break;
    			case "/site/remocao":
    				request.setAttribute("cadastro", "SIT");
    				remove(request, response);
    				break;
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
        List<Hotel_Beans> listaHoteis = hotelDao.GetList();
        request.setAttribute("listaHoteis", listaHoteis);
        List<Site_Reservas_Beans> listaSites = siteDao.GetList();
        request.setAttribute("listaSites", listaSites);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/lista.jsp");
        dispatcher.forward(request, response);
    }
    
    private void apresentaFormCadastro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	RequestDispatcher dispatcher = null;
    	
    	if(request.getAttribute("cadastro".toString()) == "HOT") {
    		dispatcher= request.getRequestDispatcher("/formsHotel.jsp");
    	}
    	if(request.getAttribute("cadastro".toString()) == "SIT") {
    		dispatcher= request.getRequestDispatcher("/formsSite.jsp");
    	}
    	
        dispatcher.forward(request, response);
    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String login = request.getAttribute("login").toString();
    	RequestDispatcher dispatcher = null;
    	
        if(request.getAttribute("cadastro".toString()) == "HOT") {
            Hotel_Beans hotel = hotelDao.Get(login);
            request.setAttribute("hotel", hotel);
            dispatcher = request.getRequestDispatcher("/formsHotel.jsp");
        }
        else if(request.getAttribute("cadastro".toString()) == "SIT") {
            Site_Reservas_Beans site = siteDao.Get(login);
            request.setAttribute("site", site);
            dispatcher = request.getRequestDispatcher("/formsSite.jsp");
        }
        dispatcher.forward(request, response);
    }
    
    private void remove(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String login = request.getAttribute("login").toString();

        if(request.getAttribute("cadastro".toString()) == "HOT") {
        	Hotel_Beans h = hotelDao.Get(login);
        	hotelDao.Remove(h);
        }
        if(request.getAttribute("cadastro".toString()) == "SIT") {
        	Site_Reservas_Beans s = siteDao.Get(login);
        	siteDao.Remove(s);
        }
        
        response.sendRedirect("/admin");
    }
    
    
    
}

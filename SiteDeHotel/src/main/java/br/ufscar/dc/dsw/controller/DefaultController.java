package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ClienteDAO;
import br.ufscar.dc.dsw.util.Erros;

import br.ufscar.dc.dsw.dao.HotelDAO;
import br.ufscar.dc.dsw.domain.Hotel_Beans;
import br.ufscar.dc.dsw.dao.SiteDAO;
import br.ufscar.dc.dsw.domain.Site_Reservas_Beans;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/index.jsp, /login.jsp")
public class DefaultController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HotelDAO hotelDao;
	
	@Override
	public void init() {
		hotelDao = new HotelDAO();
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
    			case "/login":
    				RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
    				dispatcher.forward(request, response);
    				break;
    			case "/noAuth":
    			default:
    				lista(request, response);
    				break;
    		}
    	}
    	catch (RuntimeException | IOException | ServletException e) {
            err.add("Exceção");
    		throw new ServletException(e);
        }
    	
    	// Login
    	if(request.getParameter("Ok") != null) {
    		String login = request.getParameter("email");
    		String senha = request.getParameter("Senha");
    		if(login == null) {
    			err.add("Login inválido!");
    		}
    		if(senha == null) {
    			err.add("Senha inválida!");
    		}
    		
    		if(!err.isExisteErros()) {
    			ClienteDAO cliente = new ClienteDAO();
    			String papel = cliente.GetPapel(login);
    			if(papel == null) {
    				err.add("Login não existe");
    			}
    			else if(!cliente.GetSenha(login).equals(senha)){
    				err.add("Senha incorreta");
    			}
    			else {
    				try {
	    				switch(papel) {
	    					case "ADM":
	    						request.getSession().setAttribute("usuario", "ADM");
	    						response.sendRedirect("/admin/");
	    						break;
	    					case "HOT":
	    						HotelDAO h = new HotelDAO();
	    						Hotel_Beans hotel = h.Get(login);
	    						request.getSession().setAttribute("usuario", hotel);
	    						response.sendRedirect("/hotel/");
	    						break;
	    					case "SIT":
	    						SiteDAO s = new SiteDAO();
	    						Site_Reservas_Beans site = s.Get(login);
	    						request.getSession().setAttribute("usuario", site);
	    						response.sendRedirect("/site/");
	    						break;
	    					default:
	    						err.add("Inconsistência de autorização");
	    				}
    				}
    				catch (RuntimeException | IOException  e) {
    		            err.add("Exceção");
    		    		//throw new Exception(e);
    		        }
    			}
    		}
    	}
    	else {
    		request.getSession().invalidate();

    		request.setAttribute("mensagens", err);

    		String URL = "/index.jsp";
    		RequestDispatcher rd = request.getRequestDispatcher(URL);
    		try {
    			rd.forward(request, response);
    		}
    		catch(IOException e) {
    			err.add("Erro?");
    		}
    		
    	}	
    }
    
    private void lista(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	List<Hotel_Beans> listaHoteis = new ArrayList<>();
    	String filtro = request.getAttribute("cidade").toString();
    	
    	if(filtro != null ) {
    		listaHoteis = hotelDao.GetByCity(filtro);
    	}
    	else {
    		listaHoteis = hotelDao.GetList();
    	}
         
        request.setAttribute("hoteis", listaHoteis);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/listaHoteis.jsp");
        dispatcher.forward(request, response);
    }
}

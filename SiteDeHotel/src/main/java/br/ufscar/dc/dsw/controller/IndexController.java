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

@WebServlet(name = "Index", urlPatterns = {"/SiteDeHotel, /index.jsp, /doLogin.jsp"})
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HotelDAO hotelDao;
	
	@Override
	public void init() {
		hotelDao = new HotelDAO();
	}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	Erros err = new Erros();
    	
//    	// Navigation
//    	String action = request.getPathInfo();
//    	if (action == null) {
//    		action = "";
//    	}
//    	
//    	try {
//    		switch (action) {
//    			case "/login":
//    				RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
//    				dispatcher.forward(request, response);
//    				break;
//    			case "/noAuth":
//    			default:
//    				lista(request, response);
//    				break;
//    		}
//    	}
//    	catch (RuntimeException | IOException | ServletException e) {
//            err.add("Exceção");
//    		throw new ServletException(e);
//        }
    	
    	System.out.println("Recebeu o POST");
    	
    	// Login
    	if(request.getParameter("Ok") != null) {
    		System.out.println("Recebeu Formulário");
    		String email = request.getParameter("email");
    		String senha = request.getParameter("senha");
    		if(email == null || email.isEmpty()) {
    			err.add("Login inválido!");
    		}
    		if(senha == null || senha.isEmpty()) {
    			err.add("Senha inválida!");
    		}
    		
    		if(!err.isExisteErros()) {
    			ClienteDAO cliente = new ClienteDAO();
    			String papel = cliente.GetPapel(email);
    			if(papel != null) {
    				if(!cliente.GetSenha(email).equals(senha)) {
    					if(papel.equals("ADM")) {
    						System.out.println("É ADM");
    						request.getSession().setAttribute("usuario", "ADM");
    						response.sendRedirect("admin/");
    					}
    					else if(papel.equals("HOT")) {
    						HotelDAO h = new HotelDAO();
    						Hotel_Beans hotel = h.Get(email);
    						request.getSession().setAttribute("usuario", hotel);
    						response.sendRedirect("hotel/");
    					}
    					else if(papel.equals("SIT")) {
    						SiteDAO s = new SiteDAO();
    						Site_Reservas_Beans site = s.Get(email);
    						request.getSession().setAttribute("usuario", site);
    						response.sendRedirect("site/");
    					}
    				}
    				else {
    					err.add("Senha incorreta");
    				}
    		}
    		else {
    			err.add("Usuário não encontrado");
    		}
    	}
    		
    	request.getSession().invalidate();

    	request.setAttribute("mensagens", err);

    	String URL = "/login.jsp";
    	RequestDispatcher rd = request.getRequestDispatcher(URL);
    	rd.forward(request, response);
    }
}
    
    public void lista(HttpServletRequest request, HttpServletResponse response)
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

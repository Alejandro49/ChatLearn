package principal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.util.*;

/**
* Servlet implementation class ServletAnunciarme
*/
@WebServlet(name="ServletAnunciarme", urlPatterns={"/html/anunciarme"})
public class ServletAnunciarme extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAnunciarme() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idioma = request.getParameter("IDIOMA");
		String userName = null;
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					//do something
					userName = cookie.getValue();
				}
			}
		}

		ChatDao dao = new ChatDao();
		try {
			if(dao.insertChat(idioma, userName)) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				try {
					out.println("<html><head><title>Chat Anunciado Correctamente! </title></head><body>");
					out.println("<h1>" + "Chat Anunciado Correctamente!"   + "</h1>");
					out.println(" <input type=\"button\" onclick=\"location.href='anunciarme.html'\"class=\"btn btn-outline-secondary\" value=\"Volver\">");
					out.println("</body></html>");
					} finally{out.close();}
			}
			
			else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				try {
					out.println("<html><head><title>Anuncio de Chat Fallido </title></head><body>");
					out.println("<h1>" + "Anuncio de Chat Fallido"   + "</h1>");
					out.println(" <input type=\"button\" onclick=\"location.href='anunciarme.html'\"class=\"btn btn-outline-secondary\" value=\"Volver\">");
					out.println("</body></html>");
					} finally{out.close();}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
	}
}
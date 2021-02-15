package principal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Servlet implementation class ServletBuscarChat
 */
@WebServlet(name="ServletBuscarChat", urlPatterns={"/html/buscarChat"})
public class ServletBuscarChat extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ArrayList<Chat> arrayChats = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBuscarChat() {
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
		ChatDao dao = new ChatDao();
		try {
			this.arrayChats=dao.getChat(idioma);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mostrarListaChats(response, idioma);
	}


	public void mostrarListaChats(HttpServletResponse response, String idioma) {
		// se establece el idioma de los chats
		response.setContentType("text/html");
		// se obtiene un PrintWriter donde escribir (s�lo para mandar texto)
		PrintWriter out=null;
		try {
			out=response.getWriter();
		} 
		catch (IOException io) {
			System.out.println("Se ha producido una excepcion");
		}
		
		// se manda el array de chats
		out.println("<html>");
		out.println("");
		out.println("<head>");
		out.println("<title> B�squeda de Chats</title>");
		out.println("<meta name=\"GENERATOR\" " +
		"content=\"Microsoft FrontPage 3.0\">");
		out.println("</head>");
		out.println("");
		out.println("<body>");
		out.println("");
		out.println("<H2 align=\"center\">Lista de chats en " +
		idioma + "</H2>");
		out.println("<div align=\"center\"><center>");
		out.println("");
		out.println("<table border=\"1\" width=\"70%\">");
		out.println("<tr>");
		out.println("<td width=\"25%\" bgcolor=\"#808080\">"+
				"<font color=\"#FFFFFF\"> Link </font></td>");
		out.println("<td width=\"25%\" bgcolor=\"#808080\">"+
		"<font color=\"#FFFFFF\"> Chat ID </font></td>");
		out.println("<td width=\"25%\" bgcolor=\"#808080\">"+
		"<font color=\"#FFFFFF\"> Idioma </font></td>");
		out.println("<td width=\"25%\" bgcolor=\"#808080\">"+
		"<font color=\"#FFFFFF\"> Profesor </font></td>");
		out.println("</tr>");
		
		
		// Datos del chat por filas
		
		Chat chat=new Chat();
		
		for (int i=0; i<arrayChats.size();i++) {
		chat=arrayChats.get(i);
		out.println("<tr>");
		out.println("<td width=\"25%\">"+"<a href=\"https://video-6553-dwmdqw.twil.io/index.html\">Link</a> </td>");
		out.println("<td width=\"25%\">"+chat.getId()+"</td>");
		out.println("<td width=\"25%\">"+chat.getIdioma()+"</td>");
		out.println("<td width=\"25%\">"+chat.getProfesor()+"</td>");
		out.println("</tr>");
		}
		out.println("</table>");
		out.println("</center></div>");
		out.println("</body>");
		out.println("</html>");
		
		// se fuerza la descarga del buffer y se cierra el PrintWriter
		out.flush();
		out.close();
		} // fin del m�todo mostrarChats()
		

		} // fin de la clase ListaChats
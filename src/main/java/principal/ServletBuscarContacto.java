package principal;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet(name="BuscarContacto", urlPatterns={"/html/buscarContacto"})
public class ServletBuscarContacto extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ArrayList<String> array = new ArrayList<String>();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBuscarContacto() {
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

        String userBuscado = request.getParameter("userContacto");

        UsuarioDao dao = new UsuarioDao();
        try {
            array = dao.buscarContacto(userBuscado);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        mostrarListaUsuarios(response,userBuscado);
    }


        public void mostrarListaUsuarios(HttpServletResponse response, String userBuscado) {
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
            out.println("<title> Búsqueda de Usuarios</title>");
            out.println("<meta name=\"GENERATOR\" " +
                    "content=\"Microsoft FrontPage 3.0\">");
            out.println("</head>");
            out.println("");
            out.println("<body>");
            out.println("");
            out.println("<H2 align=\"center\">Lista de Usuarios con " +
                    userBuscado + "</H2>");
            out.println("<div align=\"center\"><center>");
            out.println("");
            out.println("<table border=\"1\" width=\"70%\">");
            out.println("<tr>");
            out.println("<td width=\"25%\" bgcolor=\"#808080\">"+
                    "<font color=\"#FFFFFF\"> Usuario </font></td>");


            for (int i=0; i<array.size();i++) {
                String usuario;
                usuario=array.get(i);
                out.println("<tr>");
                out.println("<td width=\"25%\">"+usuario+"</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("</center></div>");
            out.println("</body>");
            out.println("</html>");

            // se fuerza la descarga del buffer y se cierra el PrintWriter
            out.flush();
            out.close();
        }


    }








